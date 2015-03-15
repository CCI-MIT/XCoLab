<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:liferay-ui="http://liferay.com/tld/ui"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx" />

    <jsp:directive.include file="./proposalDetails/header.jspx" />

    <portlet:resourceURL var="getSectorsURL" id="proposalImpactGetSectors">
    </portlet:resourceURL>

    <portlet:resourceURL var="getRegionsForSectorURL" id="proposalImpactGetRegionsForSector">
        <portlet:param name="sectorTermId" value="@@REPLACE-SECTOR_TERM_ID@@" />
    </portlet:resourceURL>

    <portlet:resourceURL var="getDataSeriesURL" id="proposalImpactGetDataSeries">
        <portlet:param name="sectorTermId" value="@@REPLACE-SECTOR_TERM_ID@@" />
        <portlet:param name="regionTermId" value="@@REPLACE-REGION_TERM_ID@@" />
    </portlet:resourceURL>

    <portlet:resourceURL var="saveDataSeriesURL" id="proposalImpactSaveDataSeries">
        <portlet:param name="focusAreaId" value="@@REPLACE-FOCUS_AREA_ID@@" />
    </portlet:resourceURL>

    <script>
        // Placeholder strings
        var SECTOR_TERM_ID_PLACEHOLDER = "SECTOR_TERM_ID";
        var REGION_TERM_ID_PLACEHOLDER = "REGION_TERM_ID";
        var FOCUS_AREA_ID_PLACEHOLDER = "FOCUS_AREA_ID";

        var IMPACT_REDUCTION_PLACEHOLDER = "IMPACT_REDUCTION";
        var IMPACT_ADOPTION_RATE_PLACEHOLDER = "IMPACT_ADOPTION_RATE";

        // URL strings
        var getSectorsURL = '${getSectorsURL}';
        var getRegionsForSectorURL = '${getRegionsForSectorURL}';
        var getDataSeriesURL = '${getDataSeriesURL}';
        var saveDataSeriesURL = '${saveDataSeriesURL}';
    </script>

    <!-- HTML templates -->
    <script id="impactSeriesEditTableRowTemplate" type="text/x-handlebars-template">
        <tr id="impact-edit-row-{{series.name}}">
            <td>{{series.description}}</td>
            {{#each series.values}}
                {{#if ../series.editable}}
                    <td><input type="text" name="{{this.year}}" value="{{this.value}}" class="series-value"/><span>%</span></td>
                {{else}}
                    <td class="shaded-bg"><span class="series-value">{{this.value}}</span></td>
                {{/if}}
            {{/each}}
            <td> </td>
        </tr>
    </script>

    <script id="impactSeriesSaveButtonTemplate" type="text/x-handlebars-template">
        <div class="edit-prop-butts">
            <a id="impact-edit-save-button" href="javascript:;">Save</a>
        </div>
    </script>

    <!-- Content -->
    <div id="content">
        <div id="impact">
            <table>
                <tr>
                    <th class="blue-text">Sector</th><th class="blue-text">Region</th>
                    <c:forEach var="impactIteration" items="${impactIterations}"><th class="blue-bg" style="text-align: center;">${impactIteration.year}</th></c:forEach>
                </tr>
                <c:forEach var="impactSeries" items="${impactSerieses}" varStatus="index">
                    <tr class="impact-series-clickable" id="impact-row-${index.index}">
                        <td class="sector blue-bg"><span id="${impactSeries.whatTerm.id}">${impactSeries.whatTerm.name}</span></td>
                        <td class="region blue-bg"><span id="${impactSeries.whereTerm.id}">${impactSeries.whereTerm.name}</span></td>
                        <c:forEach var="impactIteration" items="${impactIterations}">
                            <td class="impact-value">${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                <!-- New impact series -->
                <tr id="impact-series-new">
                    <td class="sector blue-bg">
                        <select>
                            <option value="0" selected="selected">Select sector</option>
                            <c:forEach var="sectorTermEntry" items="${sectorTerms}">
                                <option value="${sectorTermEntry.id}">
                                        ${sectorTermEntry.name}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="region blue-bg">
                        <select>
                            <option value="0" selected="selected">Select sector first</option>
                        </select>
                    </td>
                    <td class="iteration-value"> </td>
                </tr>
            </table>

            <table id="impact-series-edit" class="clearfix" style="display: none;">
                <tr>
                    <td>dummy</td>
                </tr>
            </table>
        </div>
    </div>


    <script>
        jQuery(document).ready(function() {
            registerEventHandler();

            // HTML templates
            console.log($("#impactSeriesEditTableRowTemplate").html());
        });

        function registerEventHandler() {
            $('.impact-series-clickable').click(function() {
                console.log("clicked row " + $(this).attr('id'));
                toggleEditMode($(this));
            });

            // Change value in new series sector dropdown
            $('tr#impact-series-new .sector').children('select').on('change', function() {
                var sectorTermId = $(this).val();
                if (sectorTermId == 0) {
                    return;
                }

                if (sectorTermId != 0) {
                    var url = replaceImpactURLPlaceholders(getRegionsForSectorURL, [SECTOR_TERM_ID_PLACEHOLDER], [sectorTermId]);
                    $.getJSON(url, { get_param: 'value' }, function(data) {
                        var regionSelect = $('#impact-series-new > td.region > select');
                        regionSelect.empty();
                        regionSelect.append('<option value="' + 0 + '">Select region</option>');
                        console.log("data " + data);

                        $.each(data, function(index, attr) {
                            console.log("each " + attr);
                            regionSelect.append('<option value="' + attr.id + '">' + attr.name + '</option>');
                            //addToProposalPickerTable(attr,even);
                            //even = ! even;
                        });
                    });
                }
            });

            // Change value in new series region dropdown
            $('tr#impact-series-new .region').children('select').on('change', function() {
                var sectorTermId = $('tr#impact-series-new .sector').children('select').val();
                var regionTermId = $(this).val();

                if (sectorTermId == 0 || regionTermId == 0) {
                    return;
                }

                var url = replaceImpactURLPlaceholders(getDataSeriesURL,
                        [SECTOR_TERM_ID_PLACEHOLDER, REGION_TERM_ID_PLACEHOLDER],
                        [sectorTermId, regionTermId]);

                console.log("load data series with URL " + url);
                loadSeriesEditData(url);
            });
        }

        var currentEditingRowIndex = -1;
        function toggleEditMode(row) {
            var rowIndex = row.attr('id').substring("impact-row-".length);
            row.addClass("selected");

            if (currentEditingRowIndex != -1) {
                var oldEditingRow = $('tr#impact-row-' + currentEditingRowIndex);
                oldEditingRow.removeClass("selected");

                var sectorTermElement = oldEditingRow.find('td.sector select').children(':selected');
                var regionTermElement = oldEditingRow.find('td.region select').children(':selected');

                // Reset select elements
                console.log("selected indexes " + sectorTermElement.attr('value') + "; " + regionTermElement.attr('value'));
                var sectorSpan = $(document.createElement("span"));
                sectorSpan.attr('id', sectorTermElement.attr('value'));
                sectorSpan.text(sectorTermElement.text());
                oldEditingRow.find('td.sector').append(sectorSpan);

                var regionSpan = $(document.createElement("span"));
                regionSpan.attr('id', regionTermElement.attr('value'));
                regionSpan.text(regionTermElement.text());
                oldEditingRow.find('td.region').append(regionSpan);

                oldEditingRow.find('td.sector select').remove();
                oldEditingRow.find('td.region select').remove();
            }

            if (rowIndex == currentEditingRowIndex) {
                currentEditingRowIndex = -1;
                $('table#impact-series-edit').slideUp();
                row.removeClass("selected");
                return;
            }

            currentEditingRowIndex = rowIndex;

            var sectorTerm = row.find('td.sector > span');
            var regionTerm = row.find('td.region > span');
            row.find('td.sector').append($(document.createElement("select")));
            row.find('td.region').append($(document.createElement("select")));

            var sectorSelect = row.find('td.sector > select');
            var regionSelect = row.find('td.region > select');

            sectorSelect.append('<option selected="selected" value="' + sectorTerm.attr('id') + '">' + sectorTerm.text()+ '</option>');
            regionSelect.append('<option selected="selected" value="' + regionTerm.attr('id') + '">' + regionTerm.text() + '</option>');
            $.getJSON(getSectorsURL, { get_param: 'value' }, function(data) {
                console.log("get sector data " + data);
                if (data != null) {
                    $.each(data, function(index, attr) {
                        sectorSelect.append('<option value="' + attr.id + '">' + attr.name + '</option>');
                    });
                }
            });

            var url = replaceImpactURLPlaceholders(getRegionsForSectorURL, [SECTOR_TERM_ID_PLACEHOLDER], [sectorTerm.attr('id')]);
            $.getJSON(url, { get_param: 'value' }, function(data) {
                console.log("get regions data " + data);
                if (data != null) {
                    $.each(data, function (index, attr) {
                        regionSelect.append('<option value="' + attr.id + '">' + attr.name + '</option>');
                    });
                }
            });

            var url = replaceImpactURLPlaceholders(getDataSeriesURL,
                    [SECTOR_TERM_ID_PLACEHOLDER, REGION_TERM_ID_PLACEHOLDER],
                    [sectorTerm.attr('id'), regionTerm.attr('id')]);
            console.log("load data series with url: " + url);
            loadSeriesEditData(url);
            $('table#impact-series-edit').slideDown();

            sectorTerm.remove();
            regionTerm.remove();
        }

        function recalculateEditSeriesValues() {
            var bauValues = $('table#impact-series-edit #impact-edit-row-BAU td span.series-value');
            var reductionValues = $('table#impact-series-edit #impact-edit-row-'+ IMPACT_REDUCTION_PLACEHOLDER +' input.series-value');
            var adoptionValues = $('table#impact-series-edit #impact-edit-row-'+ IMPACT_ADOPTION_RATE_PLACEHOLDER + ' input.series-value');
            var resultValues = $('table#impact-series-edit #impact-edit-row-RESULT td span.series-value');

            for (var i = 0; i &lt; bauValues.size(); i++) {
                console.log("bau " + parseFloat($(bauValues[i]).text()) + "; reduction " + parseFloat($(reductionValues[i]).attr('value')) +
                            "; adoption " + parseFloat($(adoptionValues[i]).attr('value')));
                var resultValue = parseFloat($(bauValues[i]).text()) * (1.0 - parseFloat($(reductionValues[i]).attr('value')) * 0.01 *
                        parseFloat($(adoptionValues[i]).attr('value')) * 0.01).toFixed(2);;
                $(resultValues[i]).text('' + resultValue);
            }
        }
        /* Replace the URL placeholders with actual values */
        function replaceImpactURLPlaceholders(rawUrl, ids, values){
            var url = rawUrl;
            for (var i = 0; i &lt; ids.length; i++) {
                url = url.replace('%40%40REPLACE-' + ids[i] + '%40%40', values[i]);
            }

            return url;
        }

        function loadSeriesEditData(url) {
            $.getJSON(url, { get_param: 'value' }, function(data) {
                var editTable = $('table#impact-series-edit');
                editTable.empty();

                console.log("json: " + JSON.stringify(data));
                var dataSeries = data.serieses.BAU;
                console.log("json: " + JSON.stringify(dataSeries));
                var tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries}));
                editTable.append(tableRow);
                console.log("bau series"  + dataSeries);

                dataSeries = data.serieses.IMPACT_REDUCTION;
                tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries}));
                editTable.append(tableRow);
                console.log("reduction series"  + dataSeries);

                dataSeries = data.serieses.IMPACT_ADOPTION_RATE;
                tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries}));
                editTable.append(tableRow);
                console.log("adoption series"  + dataSeries);

                dataSeries = {"name": "RESULT", "description": "Proposal impact [tCO2] (with partial adoption)", "editable": false,
                "values": dataSeries.values};
                tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries}));
                editTable.append(tableRow);
                console.log("result json: " + JSON.stringify(dataSeries));

                // Save button
                $('table#impact-series-edit tr#impact-edit-row-RESULT').append(jQuery(impactSeriesSaveButtonTemplate({})));
                var url = replaceImpactURLPlaceholders(saveDataSeriesURL, [FOCUS_AREA_ID_PLACEHOLDER], [data.focusAreaId]);

                var reductionData = {};
                var adoptionData = {};
                $('a#impact-edit-save-button').click(function() {
                    $.each($('tr#impact-edit-row-' + IMPACT_REDUCTION_PLACEHOLDER+ ' input'), function(idx) {
                        reductionData[$(this).attr('name')] = $(this).attr('value');
                    });
                    $.each($('tr#impact-edit-row-' + IMPACT_ADOPTION_RATE_PLACEHOLDER+ ' input'), function(idx) {
                        adoptionData[$(this).attr('name')] = $(this).attr('value');
                    });

                    var postJson = {};
                    postJson[IMPACT_REDUCTION_PLACEHOLDER] = reductionData;
                    postJson[IMPACT_ADOPTION_RATE_PLACEHOLDER] = adoptionData;

                    $.post(url, {"json" : JSON.stringify(postJson)}, function(response) {
                        console.log(response.success);
                        if (!response.success) {
                            alert("Could not process request");
                        } else {
                            toggleEditMode(currentEditingRowIndex);

                            var resultValues = $('table#impact-series-edit #impact-edit-row-RESULT td span.series-value');
                            for (var i = 0; i &lt; resultValues.length; i++) {
                                var valueCell = ($('tr#impact-row-'+currentEditingRowIndex + ' td.impact-value')[i])
                                valueCell.text($(resultValues[i]).text());

                                console.log("Set value " + valueCell.text() + " for index " + i);
                            }
                        }
                    });
                });


                // Register input event handler
                $('table#impact-series-edit input').on('blur', function() {
                    recalculateEditSeriesValues();
                })

                recalculateEditSeriesValues();
            });
        }

        var impactSeriesEditTableRowTemplate = Handlebars.compile($("#impactSeriesEditTableRowTemplate").html());
        var impactSeriesSaveButtonTemplate = Handlebars.compile($("#impactSeriesSaveButtonTemplate").html());
    </script>
</jsp:root>