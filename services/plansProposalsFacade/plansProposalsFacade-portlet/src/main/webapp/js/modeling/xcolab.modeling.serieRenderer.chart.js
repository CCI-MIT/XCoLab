if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var serieErrorPolicy = {
	NO_DISPLAY_WITH_MSG: 'NO_DISPLAY_WITH_MSG',
	NORMAL: 'NORMAL',
	DISPLAY_AVAILBLE_NO_MSG: 'DISPLAY_AVAILBLE_NO_MSG'
};

var chartSerieRenderer = {

	canRender: function(serie) {
		return serie.chartType == 'TIME_SERIES' && serie.series.length > 0;
	},
	render: function(target, serie) {
		var targetContainer = $(target);

		var errorMessages = [];
		var errorMessagesContainer = jQuery("<div></div>");
		targetContainer.append(errorMessagesContainer);
		var chartContainer = jQuery("<div></div>");
		targetContainer.append(chartContainer);
		
		var valuesCombined = [];
		

		var indexMin = parseInt(serie.index.metaData.min[0]);
		var indexMax = parseInt(serie.index.metaData.max[0]);
		
		var min = null;
		var max = null;
		
		var xaxisTicks = null;
		if (isNaN(indexMin) || isNaN(indexMax)) {
			indexMin = null;
			indexMax = null;
		}
		else {
			xaxisTicks = [];
			for (var i=indexMin; i<=indexMax; i+=10) {
				xaxisTicks.push(i);
			}
		}
		
		var chartType = 'NORMAL';
		
		var yaxis = {labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer};
		var xaxis = {autoscale: false, tickOptions:{formatString:'%d'}, ticks: xaxisTicks, labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer, label: 'Year'};
		
		

		var plotSeries = [];
		var seriesWithErrors = {rangeErrorPolicy: [], invalidErrorPolicy: []};
		var confidenceIntervalsByDescribedId = {};
		
		serie.series = serie.series.sort(function (s1, s2) {
			if (('associatedMetaDataId' in s1) && !('associatedMetaDataId' in s2)) return -1;
			if (('associatedMetaDataId' in s2) && !('associatedMetaDataId' in s1)) return 1;
			return s1.order - s2.order;
		});
		
		jQuery.each(serie.series, function(idx, singleSerie) {
			
			var shouldShow = true;
			
			jQuery.each(['rangeErrorPolicy', 'invalidErrorPolicy'], function(i, errorPolicyName) {
				if (! singleSerie[errorPolicyName]) return;
				seriesWithErrors[errorPolicyName].push(singleSerie.name);
				
				var errorPolicy = singleSerie[errorPolicyName];
				if (errorPolicy.policy != serieErrorPolicy.NO_DISPLAY_WITH_MSG && 
						errorPolicy.policy != serieErrorPolicy.NORMAL && 
						jQuery.trim(errorPolicy.message).length > 0) {
				    errorMessages.push(errorPolicy.message);
				} 
				if (errorPolicy.policy == serieErrorPolicy.NO_DISPLAY_WITH_MSG) shouldShow = false;
				
			});
			
			if (shouldShow) {
				
				var parseFunc = null;
				if (singleSerie.variable.metaData.profiles[1] == 'java.lang.Integer') parseFunc = parseInt;
				else parseFunc = parseFloat;
				
				var localMin = parseFunc(singleSerie.variable.metaData.min[1]);
				var localMax = parseFunc(singleSerie.variable.metaData.max[1]);
			
				if (max == null || max < localMax) max = localMax;
				if (min == null || min > localMin) min = localMin;
			
				var val = [];
				for (var i = 0; i < this.variable.values.length; i++) {
					val.push([parseInt(this.variable.values[i][0]), parseFloat(this.variable.values[i][1])]);
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
					if (singleSerie.variable.metaData.id in confidenceIntervalsByDescribedId && confidenceIntervalsByDescribedId[singleSerie.variable.metaData.id].length == 2) {
						// we have a serie that has confidence interval defined
						var valConf1 = confidenceIntervalsByDescribedId[singleSerie.variable.metaData.id][0];
						var valConf2 = confidenceIntervalsByDescribedId[singleSerie.variable.metaData.id][1];
						
						var intervalVal = [];

						for (var i = 0; i < val.length; i++) {
							intervalVal[i] = [val[i][0], valConf1[i][1], valConf2[i][1], val[i][1]];
						}
						

						valuesCombined.push(intervalVal);

						plotSeries.push({showMarker: false, showLabel: false, 
							renderer: jQuery.jqplot.OHLCRenderer, color: "rgb(125, 228, 247)"});
					}
						valuesCombined.push(val);
						var dataLabel = singleSerie.name;
						var dataUnit = singleSerie.variable.metaData.units[1];
						var labelFormatString = singleSerie.labelFormatString;

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
		
		
		if (min != null && max != null) {
			yaxis.min = min;
			yaxis.max = max;

			if (max - min > 2000) {
				yaxis.tickInterval = 1000;
				yaxis.tickOptions = {formatString:"%d"};	
			}
			else if (max - min > 100) {
				yaxis.tickInterval = 100;
				yaxis.tickOptions = {formatString:"%d"};
			}
			else if (max - min > 5 && max - min < 20) {
				//yaxis.tickInterval = pickTickInterval(min, max, parseInt(max-min));//((label == "Atmospheric CO2 Concentration") ? 100 : 30);// pickTickInterval(min,max,5)
				yaxis.tickInterval = 1;
				yaxis.tickOptions = {formatString:"%d"};
			}
			else if (max - min > 20 && max-min < 30) {
				yaxis.tickInterval = 2;
				yaxis.tickOptions = {formatString:"%.1f"};
			}
			else if (max - min == 20) {
				yaxis.tickOptions = {formatString:"%d"};
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
		    	axes:{
		    		xaxis: xaxis,
		    		yaxis: yaxis
		    	},
		    	grid: {
		    		drawGridLines: true,
		    		background: "#f3f2ec",
		    		shadow: false
		    	},
		    	legend : {
		    		show :true,
		    		location :'nw',
		    		placement: 'insideGrid',
		    		marginTop: "320px",
		    		yoffset :320,
		    		xoffset:0
		    	}
		};
		
		if (valuesCombined.length > 0) {
			chartContainer.jqplot(valuesCombined, plotOptions);
		}
		
		
		jQuery.each({indexedOutOfRangeError: 'rangeErrorPolicy', indexedInvalidError: 'invalidErrorPolicy'}, function(key, val) {
			if (serie[key] && serie[key]['message'] && seriesWithErrors[val].length > 0) {
				errorMessages.push(serie[key].message.replace("%outputs", seriesWithErrors[val].join(", ")));
			}
		}); 
		
		for (var i=0; i < errorMessages.length; i++) {
			errorMessagesContainer.append("<li>" + errorMessages[i] + "</li>");
		}
		if (errorMessages.length > 0) {
			errorMessagesContainer.show();
		}
	}
};

XCoLab.modeling.serieRenderers.push(chartSerieRenderer);

