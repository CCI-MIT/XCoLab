<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<portlet:resourceURL var="downloadCsv">
		<portlet:param name="report" value="userActivitiesReport" />
	</portlet:resourceURL>
	
	<h1>Reports generator</h1>
	
	<a class='btn btn-primary' href="${downloadCsv }">Download user activities report</a>
</jsp:root>