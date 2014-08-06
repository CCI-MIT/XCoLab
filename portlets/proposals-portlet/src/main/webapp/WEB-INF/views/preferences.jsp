<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	
<div id="content">
	<h1>Proposals preferences</h1>
	<portlet:actionURL var="saveURL">
		<portlet:param name="action" value="save" />
	</portlet:actionURL>
	<form:form commandName="preferences" action="${saveURL }">
        <h2>General</h2>
		<label for="termsOfService">Terms of service:</label>
		<form:textarea path="termsOfService" id="termsOfService" cssStyle="width: 100%; height: 300px;"/>
		<form:errors path="termsOfService"/>


		<input type="submit" value="Save" />
	</form:form>
</div>

</jsp:root>