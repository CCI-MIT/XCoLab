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

        <label for="advanceRejectionSemifinalistSelectionText">Advance Rejection Text (Selection of semi-finalists):</label>
        <form:textarea path="advanceRejectionSemifinalistSelectionText" id="advanceRejectionSemifinalistSelectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceRejectionSemifinalistSelectionText" />

        <label for="advanceRejectionFinalistSelectionText">Advance Rejection Text (Selection of finalists):</label>
        <form:textarea path="advanceRejectionFinalistSelectionText" id="advanceRejectionFinalistSelectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceRejectionFinalistSelectionText" />

        <label for="advanceRejectionWinnerSelectionText">Advance Rejection Text (Selection of winners):</label>
        <form:textarea path="advanceRejectionWinnerSelectionText" id="advanceRejectionWinnerSelectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceRejectionWinnerSelectionText" />

        <label for="advanceAcceptanceSemifinalistSelectionText">Advance Acceptance Text (Selection of semi-finalists):</label>
        <form:textarea path="advanceAcceptanceSemifinalistSelectionText" id="advanceAcceptanceSemifinalistSelectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceAcceptanceSemifinalistSelectionText" />

        <label for="advanceAcceptanceFinalistSelectionText">Advance Acceptance Text (Selection of finalists):</label>
        <form:textarea path="advanceAcceptanceFinalistSelectionText" id="advanceAcceptanceFinalistSelectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceAcceptanceFinalistSelectionText" />

        <label for="advanceAcceptanceWinnerSelectionText">Advance Acceptance Text (Selection of winners):</label>
        <form:textarea path="advanceAcceptanceWinnerSelectionText" id="advanceAcceptanceWinnerSelectionText" cssStyle="width:100%; height:300px;" />
        <form:errors path="advanceAcceptanceWinnerSelectionText" />
		
		<input type="submit" value="Save" />
	</form:form>
</div>

</jsp:root>