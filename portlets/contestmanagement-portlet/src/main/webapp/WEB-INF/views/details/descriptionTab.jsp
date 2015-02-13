<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="../init.jspx" />
	<jsp:directive.include file="./header.jspx"/>

	<portlet:actionURL var="updateContestDescriptionURL">
		<portlet:param name="action_forwardToPage" value="descriptionTab" />
		<portlet:param name="action_errorForwardToPage" value="descriptionTab" />
		<portlet:param name="tab" value="DESCRIPTION" />
		<portlet:param name="contestId" value="${contestWrapper.contestPK }" />
		<portlet:param name="action" value="updateContestDetails" />
	</portlet:actionURL>

	<script type="text/javascript" src="/html/js/editor/ckeditor_old/ckeditor.js" ><!-- --></script>
	<div class="cmsDetailsBox">
	<collab:imageUpload uploadImageDivId="contestImage"/>

	<h1>Introduction</h1>
	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
	dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.
	Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy dolor sit amet.

	<collab:imageUpload uploadImageDivId="logoImage"/>
	<h1>Contest description sections</h1>
	<form:form action="${updateContestDescriptionURL }" commandName="contestDescriptionBean" cssClass="addpropform" id="editForm" method="post">
		<div class="reg_errors"><!--  -->
			<form:errors cssClass="alert alert-error" path="*" />
		</div>
		<div class="addpropbox">
			<label>
				<strong>Title</strong>
				<a class="helpTrigger" href="javascript:;"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
				50 characters
			</label>
			<div class="addprophelp">Two to five words very broadly describing the contest topic, such as "Transportation Efficiency," or "Scaling Renewables in Emerging Economies". This is how the contest will be identified.</div>
			<div class="clearfix"><!--  --></div>
			<div class="addpropInputContainer">
				<form:input path="contestShortName" id="contestShortName" />
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="contestShortName" />
				</div>
				<div class="clearfix"><!-- --></div>
				<div class="inputLimitContainer"><span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">80</span> characters</div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Question</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
				140 characters
			</label>
			<div class="addprophelp">The key question to be answered through the contest. The most successful contest questions are concise and worded with language accessible to the public. </div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:textarea path="contestName" id="contestName" cssClass="proposalPitchInput" />
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="contestName" />
				</div>
				<div class="clearfix"><!-- --></div>
				<div class="inputLimitContainer">
					<span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">140</span> characters
				</div>
			</div>
		</div>

		<div class="addpropbox blue">
			<label>
                <span><strong>Contest image</strong><br />
                <em>optional</em></span>
			</label>
			<div class="addprophelp">The image symbolizes the contest challenge. Teams may wish to consider the message conveyed by the image; will it be a representation of the problem (an oil rig, a house devastated by a hurricane) or a vision of the solution (an abundant forest, a community working together).</div>

			<div class="upload contestImageUpload">

				<div class="uploadbox" id="contestImage">
					<c:choose>
						<c:when test='${contestWrapper.contestLogoId > 0}'>
							<c:set var="contestImageUrl" value="/image/contest?img_id=${contestWrapper.contestLogoId}" />
						</c:when>
						<c:otherwise>
							<c:set var="contestImageUrl" value="/climatecolab-theme/images/proposal_default.png" />
						</c:otherwise>
					</c:choose>
					<img src="${contestImageUrl }" width="52" height="52" alt="${contestWrapper.contestName} image" id="contestImageImg"/>
					<form:input path="contestLogoId" cssStyle="display: none;" id="contestImageId" />
					<div class="clearfix"><!-- --></div>
				</div>

				<div id="contestImageUploadWidget" class="uploadWidget"><!--  --></div>
				<div class="clear"><!--  --></div>
			</div>
		</div>

		<div class="addpropbox blue">
			<label>
                <span><strong>Organization/company logo</strong><br />
                <em>optional</em></span>
			</label>
			<div class="addprophelp">horizontal version preferred, if available.</div>

			<div class="upload logoImageUpload">
				<div class="uploadbox" id="logoImage">
					<c:choose>
						<c:when test='${contestWrapper.sponsorLogoId > 0}'>
							<c:set var="logoImageUrl" value="/image/proposal?img_id=${contestWrapper.sponsorLogoId}" />
						</c:when>
						<c:otherwise>
							<c:set var="logoImageUrl" value="/climatecolab-theme/images/proposal_default.png" />
						</c:otherwise>
					</c:choose>
					<img src="${logoImageUrl }" width="52" height="52" alt="${contestWrapper.contestName} image" id="logoImageImg"/>
					<form:input path="sponsorLogoId" cssStyle="display: none;" id="logoImageId" />
				</div>

				<div id="logoImageUploadWidget" class="uploadWidget"><!--  --></div>
				<div class="clear"><!--  --></div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Description</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
				300 characters
			</label>
			<div class="addprophelp">A short paragraph that states the specific problem the contest seeks to address and why it is important. The description is essentially a summary of the details page (see below) and typically starts by briefly outlining the opportunity and/or challenge the contest seeks to tackle and concludes by stating the contest's focus.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:textarea path="contestDescription" id="contestDescription" cssClass="contestDescription" />
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="contestDescription" />
				</div>
				<div class="clearfix"><!-- --></div>
				<div class="inputLimitContainer">
					<span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">300</span> characters
				</div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Proposal template</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
				Helptext dummy.
			</label>
			<div class="addprophelp">Helptext dummy.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:select path="planTemplateId">
					<form:option value="NONE" label="--- Select ---" />
					<form:options items="${proposalTemplateSelectionItems}" itemValue="value" itemLabel="lable"/>
				</form:select>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="planTemplateId" />
				</div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Schedule template</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
				Helptext dummy.
			</label>
			<div class="addprophelp">Helptext dummy.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:select path="scheduleTemplateId">
					<form:option value="NONE" label="--- Select ---" />
					<form:options items="${scheduleTemplateSelectionItems}" itemValue="value" itemLabel="lable"/>
				</form:select>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="scheduleTemplateId" />
				</div>
			</div>
		</div>
	</form:form>
	</div>


	<jsp:directive.include file="./footer.jspx"/>
</jsp:root>