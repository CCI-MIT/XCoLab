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

		<p>Welcome! This is the contest creation tool, where you can set up and preview the contest before it goes live on the Climate CoLab site.</p>
		<p>This first tab is where your team enters and edits the content that will be displayed on the contest homepage.</p>
		<p>Click the tabs above to edit the other elements of the contest. Be sure to click <strong>SAVE changes</strong> before exiting a page, or submitting a comment, or your content may be lost. Once all the contest content is complete and finalized with your team, click <strong>SUBMIT</strong> to send it to the Climate CoLab staff for review before launch.</p>
		<p>You can use the <strong>Comment</strong> feature below to communicate with your other contest team members or ask questions of the Climate CoLab staff.</p>

	<collab:imageUpload uploadImageDivId="logoImage"/>
	<h2>Homepage content</h2>
	<form:form action="${updateContestDescriptionURL }" commandName="contestDescriptionBean" cssClass="addpropform" id="editForm" method="post">
		<div class="reg_errors"><!--  -->
			<form:errors cssClass="alert alert-error" path="*" />
		</div>
		<div class="addpropbox">
			<label>
				<strong>Title</strong>
				<a class="helpTrigger" href="javascript:;"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
			</label>
			<div class="addprophelp">Two to five words very broadly describing the contest topic, such as "Transportation Efficiency," or "Scaling Renewables in Emerging Economies". This is how the contest will be identified.</div>
			<div class="clearfix"><!--  --></div>
			<div class="addpropInputContainer">
				<form:input path="contestShortName" id="contestShortName"/>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="contestShortName" />
				</div>
				<div class="clearfix"><!-- --></div>
				<div class="inputLimitContainer"><span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">50</span> characters</div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Question</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
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
               <strong>Contest image</strong>
			</label>
			<div class="addprophelp">The image symbolizes the contest challenge. Requirements: 300x300 pixels or larger (if the image is not square, it will appear warped); format in .PNG, JPEG or .JPG; no closed copyrights attached to the image. <a href="http://climatecolab.org/resources/-/wiki/Main/Launching+the+Contest" target="_blank">For guidance, please click here.</a></div>

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
                <strong>Organization/company logo</strong>
			</label>
			<div class="addprophelp">If available, it is preferred to use a version of the logo that is horizontal and has a transparent background.</div>

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
			</label>
			<div class="addprophelp">A short paragraph that states the specific problem the contest seeks to address and why it is important. The description is essentially a summary of the resources page (see next tab) and typically starts by briefly outlining the opportunity and/or challenge the contest seeks to tackle and concludes by stating the contest's focus.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:textarea path="contestDescription" id="contestDescription" cssClass="ckeditor_placeholder contestDescription" />
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="contestDescription" />
				</div>
				<div class="clearfix"><!-- --></div>
				<div class="inputLimitContainer">
					<span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">1300</span> characters
				</div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Proposal template</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
			</label>
			<div class="addprophelp">Select the proposal template associated with your contest. Templates hold the set of questions members will be asked to answer in completing a proposal. If you would like any changes to your template or need help selecting the most appropriate template, please submit a comment below for the Climate CoLab team.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:select path="planTemplateId">
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
			</label>
			<div class="addprophelp">Select the schedule associated with your contest. If you would like any changes to your schedule, please submit a comment below for the Climate CoLab team.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
			<form:select path="scheduleTemplateId">
				<form:options items="${scheduleTemplateSelectionItems}" itemValue="value" itemLabel="lable"/>
			</form:select>
			<div class="reg_errors">
				<form:errors cssClass="alert alert-error" path="scheduleTemplateId" />
			</div>
			</div>
		</div>

		<div class="addpropbox">
			<label>
				<strong>Contest level</strong>
				<a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
			</label>
			<div class="addprophelp">Select the level associated with your contest. If you have questions on which level to select, please submit a comment below for the Climate CoLab team.</div>
			<div class="clearfix"><!-- --></div>
			<div class="addpropInputContainer">
				<form:select path="contestTier">
					<form:options items="${contestLevelSelectionItems}" itemValue="value" itemLabel="lable"/>
				</form:select>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="contestTier" />
				</div>
			</div>
		</div>

	</form:form>
	</div>

	<jsp:directive.include file="./footer.jspx"/>
</jsp:root>