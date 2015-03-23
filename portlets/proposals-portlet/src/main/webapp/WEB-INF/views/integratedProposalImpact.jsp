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
            <table>
                <tr>
                    <td>&#160;</td>
                    <c:forEach var="impactIteration" items="${impactIterations}"><th class="blue-bg" style="text-align: center;">${impactIteration.year}</th></c:forEach>
                </tr>
                <c:forEach var="seriesEntry" items="${impactSeries.seriesTypeToAggregatedSeriesMap}" varStatus="index">
                    <c:set var="seriesValues" value="${impactSeries.seriesTypeToAggregatedSeriesMap[seriesEntry.key]}" />
                    <tr>
                        <td class="sector blue-bg">${seriesEntry.key}</td>
                        <c:forEach var="impactIteration" items="${impactIterations}">
                            <fmt:formatNumber var="value"
                                              value="${seriesValues.yearToValueMap[impactIteration.year]}"
                                              maxFractionDigits="2" />
                            <td class="impact-value">${value}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                <tr>
                    <td class="sector blue-bg">Selected proposal portfolio sum</td>
                    <c:forEach var="impactIteration" items="${impactIterations}">
                        <fmt:formatNumber var="value"
                                          value="${impactSeries.resultSeriesValues.yearToValueMap[impactIteration.year]}"
                                          maxFractionDigits="2" />
                        <td class="impact-value">${value}</td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
</jsp:root>