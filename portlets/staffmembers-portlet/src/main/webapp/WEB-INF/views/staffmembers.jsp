<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />

    <c:if test="${not empty portletTitle}">
        <h2>${portletTitle}</h2>
    </c:if>

    <c:choose>
        <c:when test="${displayPhoto}">
            <ul class="staffmembers-photos">
                <jsp:directive.include file="./staffmembers-items.jspx" />
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="staffmembers-list">
                <jsp:directive.include file="./staffmembers-items.jspx" />
            </ul>
        </c:otherwise>
    </c:choose>
    <div class="clear"></div>

</jsp:root>