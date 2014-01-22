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
        In the following templates, please include <strong>${prefs.getJudgingTemplatePlaceholder()}</strong>, which will be replaced by the individual judging-comment.

        <label for="judgingIncompleteText">Judging Incomplete Text:</label>
        <form:textarea path="judgingIncompleteText" id="judgingIncompleteText" cssStyle="width:100%; height:300px;" />
        <form:errors path="judgingIncompleteText" />

        <label for="judgingRejectionText">Judging Rejection Text:</label>
        <form:textarea path="judgingRejectionText" id="judgingRejectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="judgingRejectionText" />

        <label for="judgingAcceptanceText">Judging Acceptance Text:</label>
        <form:textarea path="judgingAcceptanceText" id="judgingAcceptanceText" cssStyle="width:100%; height:300px;" />
        <form:errors path="judgingAcceptanceText" />

        <label for="judgingOfftopicText">Judging Offtopic Text:</label>
        <form:textarea path="judgingOfftopicText" id="judgingOfftopicText" cssStyle="width:100%; height:300px;" />
        <form:errors path="judgingOfftopicText" />
		
		<input type="submit" value="Save" />
	</form:form>
</div>

</jsp:root>