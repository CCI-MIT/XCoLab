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


    <portlet:resourceURL var="impactGetDefaultSeries" id="proposalPicker">
        <portlet:param name="type" value="@@REPLACE-TYPE@@" />
        <portlet:param name="filterKey" value="@@REPLACE-FILTERKEY@@" />
        <portlet:param name="filterText" value="@@REPLACE-FILTERTEXT@@" />
        <portlet:param name="start" value="@@REPLACE-START@@" />
        <portlet:param name="end" value="@@REPLACE-END@@" />
        <portlet:param name="sortColumn" value="@@REPLACE-SORTCOLOMN@@" />
        <portlet:param name="sortOrder" value="@@REPLACE-SORTORDER@@" />
        <portlet:param name="sectionId" value="@@REPLACE-SECTIONID@@" />
        <portlet:param name="contestPK" value="@@REPLACE-CONTESTPK@@" />
    </portlet:resourceURL>

    <script>
        var proposalPickerURL = '${proposalPickerURL}';
    </script>

    <div id="content">

        <table>
            <tr>
                <th>Sector</th><th>Region</th>
                <c:forEach var="impactIteration" items="${impactIterations}"><th>${impactIteration.year}</th></c:forEach>
            </tr>
            <c:forEach var="impactSeries" items="${impactSerieses}" varStatus="index">
                <tr class="impact-series-clickable">
                    <td style="visibility: hidden;">${index.index}</td>
                    <td>${impactSeries.whatTerm.name}</td>
                    <td>${impactSeries.whereTerm.name}</td>
                    <c:forEach var="impactIteration" items="${impactIterations}">
                        <td>${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>

        <table id="impact-series-edit" style="display: none;">

        </table>

    </div>

    <script>
        jQuery(document).ready(function() {
            $('.impact-series-clickable').click(function() {

            });
        });

        function impactSeriesClicked() {
            var rowIndex = $(this).children().first().text();

        }
    </script>
</jsp:root>