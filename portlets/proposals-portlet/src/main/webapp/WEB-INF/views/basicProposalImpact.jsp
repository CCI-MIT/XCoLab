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

    <portlet:resourceURL var="getRegionsUrl" id="proposalImpactGetRegions">
    </portlet:resourceURL>

    <portlet:resourceURL var="getSectorsForRegionURL" id="proposalImpactGetSectorsForRegion">
        <portlet:param name="regionTermId" value="@@REPLACE-REGION_TERM_ID@@" />
    </portlet:resourceURL>

    <portlet:resourceURL var="getDataSeriesURL" id="proposalImpactGetDataSeries">
        <portlet:param name="sectorTermId" value="@@REPLACE-SECTOR_TERM_ID@@" />
        <portlet:param name="regionTermId" value="@@REPLACE-REGION_TERM_ID@@" />
    </portlet:resourceURL>

    <portlet:resourceURL var="saveDataSeriesURL" id="proposalImpactSaveDataSeries">
        <portlet:param name="focusAreaId" value="@@REPLACE-FOCUS_AREA_ID@@" />
    </portlet:resourceURL>

    <portlet:resourceURL var="deleteDataSeriesURL" id="proposalImpactDeleteDataSeries">
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
        var getRegionsURL = '${getRegionsUrl}';
        var getSectorsForRegionURL = '${getSectorsForRegionURL}';
        var getDataSeriesURL = '${getDataSeriesURL}';
        var saveDataSeriesURL = '${saveDataSeriesURL}';
        var deleteDataSeriesURL = '${deleteDataSeriesURL}';
    </script>

    <!-- HTML templates -->
    <script id="impactSeriesEditTableRowTemplate" type="text/x-handlebars-template">
        <tr id="impact-edit-row-{{series.name}}" class="impact-edit-row">
            <td>{{series.description}}
                <div class="addprophelp"><!-- Todo replace dummy text {{series.helpText}}--> dummy text </div>
            </td>
            {{#each series.values}}
                {{#if ../series.editable}}
                    <td><input type="text" name="{{this.year}}" value="{{this.value}}" class="series-value"/></td>
                {{else}}
                    <td class="shaded-bg"><span class="series-value">{{this.value}}</span></td>
                {{/if}}
            {{/each}}
            <td>&#160;</td>
        </tr>
    </script>

    <script id="impactSeriesSaveButtonTemplate" type="text/x-handlebars-template">
        <div class="edit-prop-butts">
            <a id="impact-edit-save-button" href="javascript:;">Save</a>
        </div>
        <span class="spinner-area">&#160;</span>
    </script>

    <script id="impactSeriesNewTableRowTemplate" type="text/x-handlebars-template">
        <tr class="impact-series-clickable" id="impact-row-{{series.rowIndex}}">
            <td class="region blue-bg"><span id="{{series.regionTerm.id}}">{{series.regionTerm.name}}</span></td>
            <td class="sector blue-bg"><span id="{{series.sectorTerm.id}}">{{series.sectorTerm.name}}</span></td>
            {{#each series.resultValues}}
                <td class="impact-value">{{this}}</td>
            {{/each}}
            <td><span class="spinner-area">&#160;</span></td>
        </tr>
    </script>

    <!-- Content -->
    <div id="content">
        <div id="impact">
            <c:choose>
                <!-- Don't show table if no data is available and if user cannot edit -->
                <c:when test="${not proposalsPermissions.canEdit and empty impactSerieses}">
                    <h3>No data available yet.</h3>
                </c:when>
                <c:otherwise>
                    <c:if test="${proposalsPermissions.canEdit}">
                        <div class="blue-button">
                            <a id="add-button" href="javascript:;">Add</a>
                        </div>
                        <div class="blue-button disabled">
                            <a id="remove-button" href="javascript:;">Remove</a>
                        </div>
                    </c:if>

                    <table id="impact-summary">
                        <thead>
                            <tr>
                                <th colspan="2"><!-- --></th>
                                <th class="blue-bg" colspan="${fn:length(impactIterations)}"><span class="centered">Emission reductions (relative to Business As Usual)</span></th>
                            </tr>
                            <tr>
                                <th class="blue-text">Sector</th><th class="blue-text">Region</th>
                                <c:forEach var="impactIteration" items="${impactIterations}">
                                    <th class="blue-bg" style="text-align: center;">
                                        ${impactIteration.year} [GtCO2e]
                                        <div class="addprophelp"><!-- Todo replace dummy text --> help text</div>
                                        <div class="clearfix"><!-- --></div>
                                    </th>
                                </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="impactSeries" items="${impactSerieses}" varStatus="index">
                                <tr class="impact-series-clickable" id="impact-row-${index.index}">
                                    <td class="region blue-bg"><span id="${impactSeries.whereTerm.id}">${impactSeries.whereTerm.name}</span></td>
                                    <td class="sector blue-bg"><span id="${impactSeries.whatTerm.id}">${impactSeries.whatTerm.name}</span></td>
                                    <c:forEach var="impactIteration" items="${impactIterations}">
                                        <td class="impact-value">${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="impact-series-detail">
                        <!-- New impact series -->
                        <div id="new-series" style="display: none">
                            <select id="region">
                                <option value="0" selected="selected">Select region</option>
                                <c:forEach var="regionTermEntry" items="${regionTerms}">
                                    <option value="${regionTermEntry.id}">
                                            ${regionTermEntry.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <select id="sector">
                                <option value="0" selected="selected">Select region first</option>
                            </select>
                            <div class="blue-button disabled">
                                <a id="continue-button" href="javascript:;">Continue</a>
                            </div>
                            <span class="spinner-area"> </span>
                        </div>
                        <div id="header" style="display: none">
                            <h3 id="region-title"><!-- --></h3>
                            <h3 id="sector-title"><!-- --></h3>
                            <div style="float:right; margin-right: 20px;" class="blue-button">
                                <a id="save-button" href="javascript:;">Save</a>
                            </div>
                        </div>
                        <table id="edit-table">
                            <!--  -->
                        </table>
                    </div>

                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <c:if test="${not proposalsPermissions.canEdit}">
        <script>
            $().ready(function() {
                $('tr.impact-series-clickable').removeClass('impact-series-clickable');
                $('table#impact-summary').scrolltable({
                    height: 300
                });
            });
        </script>
    </c:if>

    <script>
        var ROW_INDEX_NONE_SELECTED = -1;
        var editedFocusArea = 0;
        var userInputOccurred = false;
        var currentEditingRowIndex = ROW_INDEX_NONE_SELECTED;

        var newRegionTermId = 0;
        var newSectorTermId = 0;

        jQuery(document).ready(function() {
            registerEventHandler();
        });

        function registerEventHandler() {
            $('tr.impact-series-clickable').click(function() {
                console.log("clicked row " + $(this).attr('id'));
                toggleEditMode($(this));
            });

            $('a#remove-button').click(deleteSeriesRow);
            $('a#add-button').click(addButtonClicked);

            $('a#continue-button').click(continueButtonClicked);
            $('a#save-button').click(saveDataSeries);

            // Change value in new series sector dropdown
            registerDropdownEventListener($('div#new-series select#region'), $('div#new-series select#sector'));
        }

        function disableEditMode() {
            if (currentEditingRowIndex != ROW_INDEX_NONE_SELECTED) {
                toggleEditMode(getSelectedOverviewTableRow());
            } else {
                $('div#impact-series-detail table#edit-table').fadeOut('normal', function() {
                    $(this).empty();
                });
                //$('div#impact-series-detail').fadeOut('normal');
                userInputOccurred = false;
            }
        }
        function toggleEditMode(row) {
            var rowIndex = getIndexFromTableRow(row);
            row.addClass("selected");

            // Another row has already been edited
            if (currentEditingRowIndex != ROW_INDEX_NONE_SELECTED) {
                var oldEditingRow = getSelectedOverviewTableRow();
                oldEditingRow.removeClass("selected");
            }

            if (rowIndex == currentEditingRowIndex) {
                currentEditingRowIndex = ROW_INDEX_NONE_SELECTED;
                $('div#impact-series-detail table#edit-table').fadeOut('normal', function() {
                    $(this).empty();
                });
                row.removeClass("selected");
                userInputOccurred = false;
                editedFocusArea = 0;
                currentEditingRowIndex = ROW_INDEX_NONE_SELECTED;

                $('a#remove-button').parent().addClass("disabled");

                return;
            }

            $('a#remove-button').parent().removeClass("disabled");
            currentEditingRowIndex = rowIndex;

            // Set detail table header
            var sectorTerm = row.find('td.sector > span');
            var regionTerm = row.find('td.region > span');
            $('div#impact-series-detail #header h3#region-title').text(regionTerm);
            $('div#impact-series-detail #header h3#sector-title').text(sectorTerm);

            var url = replaceImpactURLPlaceholders(getDataSeriesURL,
                    [SECTOR_TERM_ID_PLACEHOLDER, REGION_TERM_ID_PLACEHOLDER],
                    [sectorTerm.attr('id'), regionTerm.attr('id')]);
            console.log("load data series with url: " + url);
            loadSeriesEditData(url, row);
            $('table#impact-series-edit').fadeIn();
        }

        function recalculateEditSeriesValues() {
            var bauValues = $('#impact table tr#impact-edit-row-BAU td span.series-value');
            var reductionValues = $('#impact table tr#impact-edit-row-'+ IMPACT_REDUCTION_PLACEHOLDER +' input.series-value');
            var adoptionValues = $('#impact table tr#impact-edit-row-'+ IMPACT_ADOPTION_RATE_PLACEHOLDER + ' input.series-value');
            var resultValues = $('#impact table tr#impact-edit-row-RESULT td span.series-value');

            for (var i = 0; i &lt; bauValues.size(); i++) {
                console.log("bau " + parseFloat($(bauValues[i]).text()) + "; reduction " + parseFloat($(reductionValues[i]).attr('value')) +
                            "; adoption " + parseFloat($(adoptionValues[i]).attr('value')));
                var resultValue = (parseFloat($(bauValues[i]).text()) * (1.0 - parseFloat($(reductionValues[i]).attr('value')) * 0.01 *
                        parseFloat($(adoptionValues[i]).attr('value')) * 0.01)).toFixed(2);
                console.log("resultValue: " + resultValue);
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
                editedFocusArea = data.focusAreaId;

                // Delete old edit rows
                var editTable = $('div#impact-series-detail table#edit-table');
                editTable.empty();

                // Add new rows
                console.log("json: " + JSON.stringify(data));
                var dataSeries = data.serieses.BAU;
                var tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries}));
                editTable.append(tableRow);
                console.log("bau series"  + dataSeries);


                dataSeries = data.serieses.IMPACT_REDUCTION;
                tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries}));
                console.log("Reduction: " + JSON.stringify(dataSeries));
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

                editedFocusArea = data.focusAreaId;

                // Register input event handler
                $('#impact table tr.impact-edit-row input').on('blur', function() {
                    // check valid input
                    var floatValue = parseFloat($(this).attr('value'));
                    if (floatValue &lt; 0) {
                        $(this).attr('value', "0");
                    } else if (floatValue &gt; 100) {
                        $(this).attr('value', "100");
                    }

                    userInputOccurred = true;
                    recalculateEditSeriesValues();
                });

                recalculateEditSeriesValues();
            });
        }

        function registerDropdownEventListener(regionDropdownElement, sectorDropdownElement) {
            sectorDropdownElement.click(function(event) {event.stopPropagation();});
            regionDropdownElement.click(function(event) {event.stopPropagation();});

            regionDropdownElement.on('change', function() {
                $('#impact a#continue-button').addClass('disabled');

                var regionTermId = $(this).val();
                console.log("region term selected: " + regionTermId);
                if (regionTermId == 0) {
                    return;
                }

                // Get sector terms
                var url = replaceImpactURLPlaceholders(getSectorsForRegionURL, [REGION_TERM_ID_PLACEHOLDER], [regionTermId]);
                $.getJSON(url, { get_param: 'value' }, function(data) {
                    sectorDropdownElement.empty();
                    sectorDropdownElement.append('<option value="' + 0 + '">Select sector</option>');
                    console.log("data " + data);

                    $.each(data, function(index, attr) {
                        console.log("each " + attr);
                        sectorDropdownElement.append('<option value="' + attr.id + '">' + attr.name + '</option>');
                    });
                });
            });

            sectorDropdownElement.on('change', function() {
                $('#impact a#continue-button').addClass('disabled');
                var sectorTermId = sectorDropdownElement.val();
                var regionTermId = regionDropdownElement.val();

                console.log("hit");
                if (sectorTermId &gt; 0 &amp;&amp; regionTermId &gt; 0) {
                    $('#impact a#continue-button').parent().removeClass('disabled');
                }
            });
        }

        function saveDataSeries(sender) {
            if (!userInputOccurred) {
                disableEditMode();
                return;
            }

            var url = replaceImpactURLPlaceholders(saveDataSeriesURL, [FOCUS_AREA_ID_PLACEHOLDER], [editedFocusArea]);

            var reductionData = {};
            var adoptionData = {};

            $.each($('tr#impact-edit-row-' + IMPACT_REDUCTION_PLACEHOLDER+ ' input'), function(idx) {
                reductionData[$(this).attr('name')] = $(this).attr('value');
            });
            $.each($('tr#impact-edit-row-' + IMPACT_ADOPTION_RATE_PLACEHOLDER+ ' input'), function(idx) {
                adoptionData[$(this).attr('name')] = $(this).attr('value');
            });

            var postJson = {};
            postJson[IMPACT_REDUCTION_PLACEHOLDER] = reductionData;
            postJson[IMPACT_ADOPTION_RATE_PLACEHOLDER] = adoptionData;

            $(this).parents('td').children('span.spinner-area').spin('small');
            $.post(url, {"json" : JSON.stringify(postJson)}, function(response) {
                responseData = JSON.parse(response);
                console.log("responseData " + responseData);
                if (!responseData.success) {
                    $('tr.impact-edit-row span.spinner-area').spin(false);
                    alert("Could not process request");
                } else {
                    var resultValues = $('div#impact-series-detail table#edit-table #impact-edit-row-RESULT td span.series-value');

                    // Editing a new series
                    if (currentEditingRowIndex == ROW_INDEX_NONE_SELECTED) {
                        insertNewSeries(resultValues);
                    }

                    // Editing an existing series
                    else {
                        console.log("else");
                        updateSeriesValues(resultValues);
                        disableEditMode();
                    }

                    editedFocusArea = 0;
                }
                $(this).parents('td').children('span.spinner-area').spin(false);
            });
        }

        function addButtonClicked(event) {
            // Disable edit mode if present
            disableEditMode();

            // Show new series header with dropdowns
            $('div#impact-series-detail div#new-series').fadeIn("normal");
            $('div#impact-series-detail').fadeIn("normal");
        }

        function continueButtonClicked(event) {
            newRegionTermId = $('div#new-series select#region').val();
            newSectorTermId = $('div#new-series select#sector').val();
            console.log("url " + getDataSeriesURL);
            var url = replaceImpactURLPlaceholders(getDataSeriesURL,
                    [SECTOR_TERM_ID_PLACEHOLDER, REGION_TERM_ID_PLACEHOLDER],
                    [newSectorTermId, newRegionTermId]);

            console.log("load data series with URL " + url);
            loadSeriesEditData(url);
            $('div#impact-series-detail table#edit-table').fadeIn();

            // Replace detail header
            $('div#impact-series-detail #header h3#region-title').text($('div#new-series select#region option:selected').text());
            $('div#impact-series-detail #header h3#sector-title').text($('div#new-series select#sector option:selected').text());
            $('#impact-series-detail #new-series').fadeOut();
            $('#impact-series-detail #header').fadeIn();
            // userInputOccurred = true;
        }

        function insertNewSeries(resultValues) {

            var regionTermName = $('div#impact-series-detail h3#region-title').text();
            var sectorTermName = $('div#impact-series-detail h3#sector-title').text();
            var seriesData = {"sectorTerm" : {"id": newSectorTermId, "name" : sectorTermName},
                "regionTerm" : {"id": newRegionTermId, "name" : regionTermName},
                "rowIndex": $('tr.impact-series-clickable').size(),
                "resultValues" : [],
                "focusAreaId" : editedFocusArea};

            //console.log("series data " + JSON.stringify(seriesData));
            $.each(resultValues, function(idx) {
                seriesData.resultValues.push($(this).text());
            });
            // Insert new row
            var newRow = jQuery(impactSeriesNewTableRowTemplate({series: seriesData}));
            var overviewTableRows = $('table#impact-summary tr.impact-series-clickable');
            if (overviewTableRows.length == 0) {
                $('table#impact-summary').append(newRow);
            } else {
                newRow.insertAfter(overviewTableRows[overviewTableRows.length - 1]);
            }

            newRow.click(function() {
                toggleEditMode($(this));
            });

            disableEditMode();
        }

        function updateSeriesValues(resultValues) {
            for (var i = 0; i &lt; resultValues.length; i++) {
                var valueCell = $($('tr#impact-row-'+currentEditingRowIndex + ' td.impact-value')[i]);
                console.log("get valueCell with index " + i);
                valueCell.text($(resultValues[i]).text());

                console.log("Set value " + valueCell.text() + " for index " + i);
            }
        }
        function deleteSeriesRow(event) {
            if (currentEditingRowIndex != ROW_INDEX_NONE_SELECTED) {
                $('#impact table tr.impact-edit-row').fadeOut('normal', function() {
                    $(this).remove();
                });

                event.stopPropagation();
                console.log('focus area id ' + editedFocusArea);
                var url = replaceImpactURLPlaceholders(deleteDataSeriesURL, [FOCUS_AREA_ID_PLACEHOLDER], [editedFocusArea]);
                console.log("delete url " + url);
                var row = getSelectedOverviewTableRow();
                console.log("row: " + row.html());

                // $(this).parents('td').children('span.spinner-area').spin('small');
                $.post(url, {}, function(response) {
                    var responseData = JSON.parse(response);
                    if (!responseData.success) {
                        alert("Could not delete row");
                    } else {
                        row.fadeOut("normal", function(){ row.remove(); });

                        // Reload available sector terms
                        var regionsSelectElement = $('div#impact-series-detail div#new-series select#region');
                        regionsSelectElement.empty();
                        regionsSelectElement.append('<option value="0" selected="selected">Select region</option>');
                        $.getJSON(getRegionsURL, { get_param: 'value' }, function(data) {
                            console.log("get regions data " + data);
                            if (data != null) {
                                $.each(data, function(index, attr) {
                                    regionsSelectElement.append('<option value="' + attr.id + '">' + attr.name + '</option>');
                                });
                            }
                        });
                    }
                });

                currentEditingRowIndex = ROW_INDEX_NONE_SELECTED;
                setRemoveButtonState(false);
            }
        }

        function getOverviewTableRowWithIndex(index) {
            console.log('tr#impact-row-' + index);
            return $('tr#impact-row-' + index);
        }

        function getIndexFromTableRow(row) {
            return row.attr('id').substring("impact-row-".length);
        }

        function getSelectedOverviewTableRow() {
            return getOverviewTableRowWithIndex(currentEditingRowIndex);
        }

        function setRemoveButtonState(enabled) {
            if (enabled) {
                $('a#remove-button').parent().removeClass('disabled');
            } else {
                $('a#remove-button').parent().addClass('disabled');
            }
        }

        var impactSeriesEditTableRowTemplate = Handlebars.compile($("#impactSeriesEditTableRowTemplate").html());
        var impactSeriesSaveButtonTemplate = Handlebars.compile($("#impactSeriesSaveButtonTemplate").html());
        var impactSeriesNewTableRowTemplate = Handlebars.compile($('#impactSeriesNewTableRowTemplate').html());
    </script>
</jsp:root>