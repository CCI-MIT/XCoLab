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

    <!-- Content -->
    <div id="content">
        <div id="impact">
            <c:choose>
                <!-- Don't show table if no data is available and if user cannot edit -->
                <c:when test="${impactSeries.emptySeries}">
                    <h3>No data available yet.</h3>
                </c:when>
                <c:otherwise>
                    <div id="impact-chart">&#160;</div>
                    <table>
                        <tr>
                            <td>&#160;</td>
                            <c:forEach var="impactIteration" items="${impactIterations}"><th class="blue-bg" style="text-align: center;">${impactIteration.year}</th></c:forEach>
                        </tr>
                        <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToAggregatedSeriesMap}" varStatus="index">
                            <c:set var="seriesValues" value="${impactSeries.seriesTypeToAggregatedSeriesMap[seriesEntry.key]}" />
                            <tr>
                                <td class="sector">${impactSeries.seriesTypeToDescriptionMap[seriesEntry.key]}</td>
                                <c:forEach var="impactIteration" items="${impactIterations}">
                                    <fmt:formatNumber var="value"
                                                      value="${seriesValues.yearToValueMap[impactIteration.year]}"
                                                      maxFractionDigits="2" />
                                    <td class="impact-value">${value}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        <tr>
                            <c:choose>
                                <c:when test="${empty proposal.team}"><td class="sector">${proposal.author.screenName}'s proposal</td></c:when>
                                <c:otherwise><td class="sector">${proposal.team}'s proposal</td></c:otherwise>
                            </c:choose>

                            <c:forEach var="impactIteration" items="${impactIterations}">
                                <fmt:formatNumber var="value"
                                                  value="${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}"
                                                  maxFractionDigits="2" />
                                <td class="impact-value">${value}</td>
                            </c:forEach>
                        </tr>
                    </table>
                    <div style="text-align: center;">
                        <h3 style="margin-top: 50px;">Coming soon:</h3><h4>a list with the emission reduction estimates of all proposals included in this plan.</h4>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script type="text/javascript">
        var tableColors = ['#8C7AAE','#65B868'];

        $().ready(function() {
            // Color table columns
            var hitFirstRow = false;
            $('div#impact > table tr').each(function(idx) {
                if (!hitFirstRow) {
                    hitFirstRow = true;
                } else {
                    $(this).children('td.sector').css('background-color', tableColors[idx - 1]);
                }
            });
        });

        google.load('visualization', '1', {packages: ['corechart', 'line']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.setOnLoadCallback(drawChart);

        function drawChart() {
            var minValue = Number.MAX_VALUE;
            var maxValue = 0;

            // Chart labels
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Year');
            <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToAggregatedSeriesMap}" varStatus="index">
                data.addColumn('number', '${seriesEntry.key}');
            </c:forEach>
            data.addColumn('number', 'Selected proposal portfolio sum');

            // Build chart data
            <c:forEach var="impactIteration" items="${impactIterations}">
                var dataRow = ["${impactIteration.year}"];

                <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToAggregatedSeriesMap}" varStatus="index">
                    <c:set var="seriesValues" value="${impactSeries.seriesTypeToAggregatedSeriesMap[seriesEntry.key]}" />
                    <c:set var="value" value="${seriesValues.yearToValueMap[impactIteration.year]}" />

                    dataRow.push(${value});
                    minValue = Math.min(minValue, ${value});
                    maxValue = Math.max(maxValue, ${value});
                </c:forEach>

                <c:set var="value" value="${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}" />

                dataRow.push(${value});
                data.addRow(dataRow);
                minValue = Math.min(minValue, ${value});
                maxValue = Math.max(maxValue, ${value});
            </c:forEach>

            var dataValueRange = maxValue - minValue;

            var options = {
                title: "Total emissions of ${contest.contestShortName} (Mt CO2)",
                titleTextStyle: {
                    fontSize: "16"
                },
                hAxis: {
                    title: 'Year',
                    gridlines: {
                        color: '#333',
                        count: 5
                    }
                },
                vAxis: {
                    title: 'MtCO2',
                    viewWindow: {
                        max: (maxValue + Math.max((Math.round(dataValueRange / 100.0) + 1) * 5, 5)),
                        min: (minValue - Math.max((Math.round(dataValueRange / 100.0) + 1) * 5, 5))
                    }
                },
                legend: { position: 'top' },
                pointShape: 'circle',
                pointSize: 6,
                colors:tableColors,
                width:500,
                height:Math.min(150 * Math.max(Math.round(dataValueRange / 50.0), 2), 600.0)
            };

            var chart = new google.visualization.LineChart(document.getElementById('impact-chart'));
            chart.draw(data, options);
        }
    </script>
</jsp:root>