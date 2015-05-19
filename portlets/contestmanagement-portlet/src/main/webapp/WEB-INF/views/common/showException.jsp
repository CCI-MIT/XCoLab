<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="../init.jspx" />

	<h1>Exception occured!</h1>
	<c:select>
		<c:when test="${not empty sessionScope.exceptionMessage }">
			<p>${sessionScope.exceptionMessage}</p>
			<c:remove var="exceptionMessage" scope="session" />
		</c:when>
		<c:when test="${exceptionMessage}">
			<p>${exceptionMessage}</p>
		</c:when>
	</c:select>

</jsp:root>