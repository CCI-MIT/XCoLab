if (typeof(XCoLab) == 'undefined')
    throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
    throw new Error("XCoLab.modeling isn't defined");

(function () {
    var serieErrorPolicy = {
        NO_DISPLAY_WITH_MSG: 'NO_DISPLAY_WITH_MSG',
        NORMAL: 'NORMAL',
        DISPLAY_AVAILBLE_NO_MSG: 'DISPLAY_AVAILBLE_NO_MSG'
    };


    function DefaulChartRenderer() {
    }

    DefaulChartRenderer.prototype.getName = function (output) {
        return output.name;
    };

    DefaulChartRenderer.prototype.getOrder = function () {
        return 1 << 30;
    };

    DefaulChartRenderer.prototype.canRender = function (output) {
        return output.chartType == 'TIME_SERIES';
    };


    DefaulChartRenderer.prototype.render = function (container, output, modelingWidget, parent) {

        var errorMessages = [];
        var errorMessagesContainer = jQuery("<ul class='chartMessagePlaceholder' style='display: none;'></ul>").appendTo(container);
        var chartWrapper = jQuery("<div class='chartContainer'></div>").appendTo(container);
        var chartContainer = jQuery("<div class='chartPlaceholder '></div>").appendTo(chartWrapper);
        var legendContainer = jQuery("<div></div>").appendTo(chartWrapper);

        var valuesCombined = [];


        var indexMin = parseInt(output.index.metaData.min[0]);
        var indexMax = parseInt(output.index.metaData.max[0]);

        var min = null;
        var max = null;

        var xaxisTicks = null;
        if (isNaN(indexMin) || isNaN(indexMax)) {
            indexMin = null;
            indexMax = null;
        }
        else {
            xaxisTicks = [];
            // as requested by Robert we should start from year that has 0 at the end (ie 2010)
            for (var i = indexMin + (10 - indexMin % 10); i <= indexMax; i += 10) {
                xaxisTicks.push(i);
            }
        }

        var yaxis = {labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer};
        var xaxis = {
            autoscale: false,
            tickOptions: {formatString: '%d'},
            ticks: xaxisTicks,
            labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer,
            label: 'Year'
        };

        var plotSeries = [];
        var seriesWithErrors = {rangeErrorPolicy: [], invalidErrorPolicy: []};
        var confidenceIntervalsByDescribedId = {};

        output.series = output.series.sort(function (s1, s2) {
            if (('associatedMetaDataId' in s1) && !('associatedMetaDataId' in s2)) return -1;
            if (('associatedMetaDataId' in s2) && !('associatedMetaDataId' in s1)) return 1;
            return s1.order - s2.order;
        });

        jQuery.each(output.series, function (idx, singleSerie) {

            var shouldShow = true;

            jQuery.each(['rangeErrorPolicy', 'invalidErrorPolicy'], function (i, errorPolicyName) {
                if (!singleSerie[errorPolicyName]) return;
                seriesWithErrors[errorPolicyName].push(singleSerie.name);

                var errorPolicy = singleSerie[errorPolicyName];
                if (errorPolicy.policy != serieErrorPolicy.NO_DISPLAY_WITH_MSG &&
                    errorPolicy.policy != serieErrorPolicy.NORMAL &&
                    jQuery.trim(errorPolicy.message).length > 0) {
                    errorMessages.push(errorPolicy.message);
                }
                if (errorPolicy.policy == serieErrorPolicy.NO_DISPLAY_WITH_MSG) shouldShow = false;

            });
            var onlyEmptyOrZeros = true;
            for (var i = 0; i < this.variable.values.length; i++) {
                var val = parseFloat(this.variable.values[i][1]);
                if (val >= 0.001 || val <= -0.001) {
                    onlyEmptyOrZeros = false;
                }
            }

            shouldShow = shouldShow & (!onlyEmptyOrZeros);

            if (shouldShow) {

                yaxis.label = singleSerie.variable.metaData.units[1];
                var parseFunc = null;
                if (singleSerie.variable.metaData.profiles[1] == 'java.lang.Integer') parseFunc = parseInt;
                else parseFunc = parseFloat;

                if (singleSerie.variable.metaData.units[1].toLowerCase().indexOf("percent") >= 0 || singleSerie.variable.metaData.units[1].toLowerCase().indexOf("%") >= 0) {
                    yaxis.tickOptions = {formatString: "%d %%"};
                    oldParseFunc = parseFunc;
                    parseFunc = function (num) {
                        return 100 * oldParseFunc(num);
                    }
                }

                var localMin = parseFunc(singleSerie.variable.metaData.min[1]);
                var localMax = parseFunc(singleSerie.variable.metaData.max[1]);

                if (max == null || max < localMax) max = localMax;
                if (min == null || min > localMin) min = localMin;

                var val = [];
                for (var i = 0; i < this.variable.values.length; i++) {
                    val.push([parseInt(this.variable.values[i][0]), parseFunc(this.variable.values[i][1])]);
                }
                if ('associatedMetaDataId' in singleSerie) {
                    // value of this serie is describing a confidence interval of different serie, it shouldn't
                    // be shown in ordinary way, described serie should handle drawing this properly
                    if (!(singleSerie.associatedMetaDataId in confidenceIntervalsByDescribedId)) {
                        confidenceIntervalsByDescribedId[singleSerie.associatedMetaDataId] = [];
                    }
                    confidenceIntervalsByDescribedId[singleSerie.associatedMetaDataId].push(val);
                }
                else {
                    var dataLabel = singleSerie.name;
                    var dataUnit = singleSerie.variable.metaData.units[1];
                    var labelFormatString = singleSerie.labelFormatString;
                    if (singleSerie.variable.metaData.id in confidenceIntervalsByDescribedId && confidenceIntervalsByDescribedId[singleSerie.variable.metaData.id].length == 2) {
                        // we have a serie that has confidence interval defined
                        var valConf1 = confidenceIntervalsByDescribedId[singleSerie.variable.metaData.id][0];
                        var valConf2 = confidenceIntervalsByDescribedId[singleSerie.variable.metaData.id][1];

                        var intervalVal = [];

                        for (var i = 0; i < val.length; i++) {
                            intervalVal[i] = [val[i][0], valConf1[i][1], valConf2[i][1], val[i][1]];
                        }


                        valuesCombined.push(intervalVal);

                        plotSeries.push({
                            showMarker: false, showLabel: false, label: dataLabel + " (low-high estimate)",
                            renderer: jQuery.jqplot.OHLCRenderer, color: "rgb(125, 228, 247)"
                        });
                        //plotSeries.push({showMarker: true, label: 'abcdefg'});
                    }
                    valuesCombined.push(val);

                    if (!(!labelFormatString || jQuery.trim(labelFormatString) == "")) {
                        dataLabel = labelFormatString.replace(/%label/g, dataLabel).replace(/%unit/g, dataUnit);
                    }

                    plotSeries.push({showMarker: false, label: dataLabel});

                }


            }
            if (singleSerie['rangeErrorPolicy'] &&
                singleSerie.rangeErrorPolicy.policy != serieErrorPolicy.NO_DISPLAY_WITH_MSG &&
                singleSerie.rangeErrorPolicy.policy != serieErrorPolicy.NORMAL &&
                jQuery.trim(singleSerie.rangeErrorPolicy.message) != "") {
            }
        });


        if (!isNaN(min) && !isNaN(max) && min != null && max != null) {
            yaxis.min = min;
            yaxis.max = max;

            if (max - min > 2000) {
                yaxis.tickInterval = 1000;
                yaxis.tickOptions = {formatString: "%d"};
            }
            else if (max - min > 100) {
                yaxis.tickInterval = 100;
                yaxis.tickOptions = {formatString: "%d"};
            }
            else if (max - min > 5 && max - min < 20) {
                //yaxis.tickInterval = pickTickInterval(min, max, parseInt(max-min));//((label == "Atmospheric CO2 Concentration") ? 100 : 30);// pickTickInterval(min,max,5)
                yaxis.tickInterval = 1;
                yaxis.tickOptions = {formatString: "%d"};
            }
            else if (max - min > 20 && max - min < 30) {
                yaxis.tickInterval = 2;
                yaxis.tickOptions = {formatString: "%.1f"};
            }
            else if (max - min == 20) {
                yaxis.tickOptions = {formatString: "%d"};
            }
        }
        if (indexMin != null && indexMax != null) {
            xaxis.min = indexMin;
            xaxis.max = indexMax;
        }


        var plotOptions = {
            stackSeries: false,
            showMarker: false,
            seriesDefaults: {},
            series: plotSeries,
            axes: {
                xaxis: xaxis,
                yaxis: yaxis
            },
            grid: {
                drawGridLines: true,
                background: "#f3f2ec",
                shadow: false
            },
            legend: {
                renderer: $.jqplot.EnhancedLegendRenderer,
                show: true,
                location: 's',
                placement: 'outside',
                marginTop: '300px',
                rendererOptions: {numberColumns: 4}
            }
        };

        if (valuesCombined.length > 0) {
            chartContainer.jqplot(valuesCombined, plotOptions);
            chartContainer.find("table.jqplot-table-legend").appendTo(legendContainer).removeAttr('style');
            jQuery(legendContainer.find("table.jqplot-table-legend tr.emfModelsUnderChartMessage")).remove();

            try {
                if (!modelingWidget.inEditMode && !!modelingWidget.model) {
                    var legendElem = jQuery(legendContainer.find("table.jqplot-table-legend"));
                    var columnCount = legendElem.find("tr:first td").length;
                    var isModelRegional = modelingWidget.model.modelName.toLowerCase().indexOf("regional") != -1;
                    var isModelEMF = modelingWidget.model.modelName.toLowerCase().indexOf("emf") != -1;
                    var isModelEnRoads = modelingWidget.model.modelName.toLowerCase().indexOf("enroads") != -1;
                    var emfModelsUnderChartMessage = "<tr class='emfModelsUnderChartMessage'><td colspan='" + columnCount + "'>" +
                        "Results shown for the following models. ";
                    if (isModelRegional) {
                        emfModelsUnderChartMessage += "See <a href='/wiki/Climate+CoLab+Regional+Modeling+Tools' target='_blank'>Climate CoLab Regional model runs for more details.</a></td></tr>";
                    } else if (isModelEMF) {
                        emfModelsUnderChartMessage += "See <a href='/wiki/EMF27+model+runs' target='_blank'>EMF27 model runs for more details.</a></td></tr>";
                    } else if (isModelEnRoads) {
                        emfModelsUnderChartMessage += "See <a href='/wiki/EnROADS+by+Climate+Interactive' target='_blank'>Climate Interactive EnROADS model runs for more details.</a></td></tr>";
                    } else {
                        emfModelsUnderChartMessage = "";
                    }
                    legendElem.prepend(emfModelsUnderChartMessage);
                }
            } catch(exception){
                console.warn("Error while creating tab model links", exception);
            }
        }

        jQuery.each({
            indexedOutOfRangeError: 'rangeErrorPolicy',
            indexedInvalidError: 'invalidErrorPolicy'
        }, function (key, val) {
            if (output[key] && output[key]['message'] && seriesWithErrors[val].length > 0) {
                errorMessages.push(output[key].message.replace("%outputs", seriesWithErrors[val].join(", ")));
            }
        });

        for (var i = 0; i < errorMessages.length; i++) {
            errorMessagesContainer.append("<li>" + errorMessages[i] + "</li>");
        }
        if (errorMessages.length > 0) {
            errorMessagesContainer.show();
        }
    };


    XCoLab.modeling.outputItemRenderers.push(new DefaulChartRenderer());
})();
