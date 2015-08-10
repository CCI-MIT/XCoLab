<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:collab="http://climatecolab.org/tags/collab_1.0"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="../init.jspx"/>

    <h1>Error!</h1>

    <h2>You request was not successful.</h2>

    <p>The following exception message can help our developers to investage into this issue: <br/>
        <c:if test="${not empty sessionScope.exceptionMessage }">
                ${sessionScope.exceptionMessage};
        </c:if>
    </p>

    <c:remove var="exceptionMessage" scope="session"/>
</jsp:root>