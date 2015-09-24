<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="../init.jspx"/>

    <c:choose>
        <c:when test="${not empty sessionScope.exceptionMessage or not empty sessionScope}">
            <jsp:directive.include file="reportException.jspx"/>
        </c:when>
        <c:otherwise>
            <h1>Error!</h1>

            <h3>The requested page was not found!</h3>
        </c:otherwise>
    </c:choose>
    </c:if>
</jsp:root>