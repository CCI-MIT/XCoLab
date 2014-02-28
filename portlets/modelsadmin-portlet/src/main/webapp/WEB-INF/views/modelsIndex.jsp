<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<h1>Models administration</h1>
	
	<table class='table'>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Type</th>
		</tr>
		<c:forEach var="model" items="${models }">
			<portlet:renderURL var="modelUrl">
				<portlet:param name="modelId" value="${model.id }" />
			</portlet:renderURL>
			<tr>
				<td>${model.id }</td>
				<td><a href="${modelUrl}">${model.name }</a></td>
				<td>${model.type }</td>
			</tr>
		</c:forEach>
	</table>
</jsp:root>