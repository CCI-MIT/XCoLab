<jsp:root
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions"
        xmlns:collab="http://climatecolab.org/tags/collab_1.0"
        xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
        version="2.0">

<div id="content">
    Internal error has ocurred, please contact the administrator.
    <div id="stackTrace" style="display:none;">
    <c:if test="${not empty exception}">
        <p><strong>Exception:</strong><br/><c:out value="${exception}"/></p>
        <p><strong>Stacktrace:</strong><br/>
            <pre>
                <c:forEach var="stackTraceElem" items="${exception.stackTrace}">
                    <c:out value="${stackTraceElem}"/><br/>
                </c:forEach>
            </pre>
        </p>
    </c:if>
    </div>
</div>
</jsp:root>
