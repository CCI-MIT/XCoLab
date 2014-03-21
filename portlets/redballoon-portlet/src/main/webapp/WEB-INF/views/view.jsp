<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<div class="balloonContainer">
${balloonText.textBeforeForm }

<portlet:actionURL var="sendEmailURL">
	<portlet:param name="action" value="sendEmail" />
</portlet:actionURL>
<form:form modelAttribute="userEmailBean" action="${sendEmailURL }" id="sendUserEmailForm">

	<div>
		<form:checkbox path="acceptTos" />${balloonText.acceptTosText }<br />
		<form:errors path="acceptTos" class="alert alert-error"/>
	</div>
	
	<form:input path="email" id="email" cssClass="text profileInput balloonInput" required="true" placeholder="Please enter your e-Mail address" />
	<div class="blue-button">
		<a href="#" onclick="javascript:jQuery('#sendUserEmailForm').submit();">request personal URL</a>
	</div><br />
	<form:errors path="email" class="alert alert-error"/>
</form:form>

${balloonText.textAfterForm }

</div>
</jsp:root>