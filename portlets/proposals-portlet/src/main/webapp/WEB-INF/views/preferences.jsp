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

        <h2>Judging System</h2>
        In the following templates, please be sure that the custom comment will be placed within
        <strong>${prefs.getCommentBeginTag()}${prefs.getCommentEndTag()}</strong>.

        <label for="screeningIncompleteText">Screening Incomplete Text:</label>
        <form:textarea path="screeningIncompleteText" id="screeningIncompleteText" cssStyle="width:100%; height:300px;" />
        <form:errors path="screeningIncompleteText" />

        <label for="screeningOfftopicText">Screening Offtopic Text:</label>
        <form:textarea path="screeningOfftopicText" id="screeningOfftopicText" cssStyle="width:100%; height:300px;" />
        <form:errors path="screeningOfftopicText" />

        <label for="advanceRejectionText">Advance Rejection Text:</label>
        <form:textarea path="advanceRejectionText" id="advanceRejectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceRejectionText" />

        <label for="advanceAcceptanceText">Advance Acceptance Text:</label>
        <form:textarea path="advanceAcceptanceText" id="advanceAcceptanceText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceAcceptanceText" />
		
		<input type="submit" value="Save" />
	</form:form>
</div>

</jsp:root>