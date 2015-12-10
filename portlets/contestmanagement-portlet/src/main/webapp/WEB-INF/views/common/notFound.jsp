<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          version="2.0">
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
</jsp:root>