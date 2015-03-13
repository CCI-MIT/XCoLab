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

    <portlet:resourceURL var="getDataSeriesURL" id="getDataSeries">
        <portlet:param name="sectorTermId" value="@@REPLACE-SECTOR_TERM_ID@@" />
        <portlet:param name="regionTermId" value="@@REPLACE-REGION_TERM_ID@@" />
    </portlet:resourceURL>

    <script>
        // Placeholder strings
        var SECTOR_TERM_ID_PLACEHOLDER = "SECTOR_TERM_ID";
        var REGION_TERM_ID_PLACEHOLDER = "REGION_TERM_ID";

        // URL strings
        var getSectorsURL = '${getSectorsURL}';
        var getRegionsForSectorURL = '${getRegionsForSectorURL}';
        var getDataSeriesURL = '${getDataSeriesURL}';

        // HTML templates
        var impactSeriesEditTableRowTemplate = Handlebars.compile($("#impactSeriesEditTableRowTemplate").html());
        var impactSeriesMainTableEditDropdownTemplate = Handlebars.compile($("#impactSeriesEditDropdownTemplate").html());
    </script>

    <div id="content">

        <table>
            <tr>
                <th>Sector</th><th>Region</th>
                <c:forEach var="impactIteration" items="${impactIterations}"><th>${impactIteration.year}</th></c:forEach>
            </tr>
            <c:forEach var="impactSeries" items="${impactSerieses}" varStatus="index">
                <tr class="impact-series-clickable" id="impact-row-${index.index}">
                    <td class="sector"><span id="${impactSeries.whatTerm.id}">${impactSeries.whatTerm.name}</span></td>
                    <td class="region"><span id="${impactSeries.whereTerm.id}">${impactSeries.whereTerm.name}</span></td>
                    <c:forEach var="impactIteration" items="${impactIterations}">
                        <td>${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <!-- New impact series -->
            <tr id="impact-series-new">
                <td class="sector">
                    <select>
                        <option value="0" selected="selected">Select sector</option>
                        <c:forEach var="sectorTermEntry" items="${sectorTerms}">
                            <option value="${sectorTermEntry.id}">
                                    ${sectorTermEntry.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td class="region">
                    <select>
                        <option value="0" selected="selected">Select sector first</option>
                    </select>
                </td>
            </tr>
        </table>

        <table id="impact-series-edit" style="display: none;">

        </table>

    </div>


    <script>
        jQuery(document).ready(function() {
            registerEventHandler();
            $('.impact-series-clickable').click(function() {
                toggleEditMode($(this));
            });
        });

        function registerEventHandler() {
            // Change value in new series sector dropdown
            $('tr#impact-series-new .sector').children('select').on('change', function() {
                var sectorTermId = $(this).val();

                if (sectorTermId != 0) {
                    var url = replaceImpactURLPlaceholders(getRegionsForSectorURL, [SECTOR_TERM_ID_PLACEHOLDER], [sectorTermId]);
                    $.getJSON(url, { get_param: 'value' }, function(data) {
                        var regionSelect = $('#impact-series-new > td.region > select');
                        regionSelect.empty();
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

                var url = replaceImpactURLPlaceholders(getDataSeriesURL,
                        [SECTOR_TERM_ID_PLACEHOLDER, REGION_TERM_ID_PLACEHOLDER],
                        [sectorTermId, regionTermId]);

                loadSeriesEditData(url);
            });
        }

        var editingRow = null;
        function toggleEditMode(row) {
            if (row == editingRow) {
                return;
            }

            if (editingRow != null) {
                var sectorTermElement = editingRow.find('td.sector select').filter(':selected');
                var regionTermElement = editingRow.find('td.region select').filter(':selected');

                editingRow.find('td.sector').attr('id', sectorTermElement.attr('value'));
                editingRow.find('td.sector').text(sectorTermElement.text());
                editingRow.find('td.region').attr('id', regionTermElement.attr('value'));
                editingRow.find('td.region').text(regionTermElement.text());
                editingRow.find('td.sector select').remove();
                editingRow.find('td.region select').remove();
            }

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
            //loadSeriesEditData(url);
            $('table#impact-series-edit').slideDown();

            sectorTerm.remove();
            regionTerm.remove();
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

                $.each(data.serieses, function(index, attr) {
                    var mergedData = {};
                    mergedData.iterations = data.iterations;
                    mergedData.series = attr;
                    var tableRow = jQuery(impactSeriesEditTableRowTemplate({series: mergedData}));
                    editTable.append(tableRow);
                });

                var dataSeries = data.serieses["BAU"];
                var tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries, iterations: data.iterations}));
                editTable.append(tableRow);

                dataSeries = data.serieses["IMPACT_REDUCTION"];
                tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries, iterations: data.iterations}));
                editTable.append(tableRow);

                dataSeries = data.serieses["IMPACT_ADOPTION_RATE"];
                tableRow = jQuery(impactSeriesEditTableRowTemplate({series: dataSeries, iterations: data.iterations}));
                editTable.append(tableRow);
            });
        }
    </script>


    <script id="impactSeriesEditDropdownTemplate"
            type="text/x-handlebars-template">


    </script>
    <script id="impactSeriesEditTableRowTemplate"
            type="text/x-handlebars-template">

        <tr>
            <td>{{series.description}}</td>
            {{ #each iterations }}
                {{ #if series.editable }}
                <td><input type="text" name="this" value="{{series.values[this.year]}}" /></td>
                {{ else }}
                    <td>{{series.values[this]}}</td>
                {{/if}}
            {{ /each }}
        </tr>
    </script>
</jsp:root>