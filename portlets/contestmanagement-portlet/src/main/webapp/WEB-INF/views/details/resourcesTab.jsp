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

	<portlet:actionURL var="updateContestResourcesURL">
		<portlet:param name="action_forwardToPage" value="resourcesTab" />
		<portlet:param name="action_errorForwardToPage" value="resourcesTab" />
		<portlet:param name="tab" value="RESOURCES" />
		<portlet:param name="contestId" value="${contestWrapper.contestPK }" />
		<portlet:param name="action" value="updateContestResources" />
	</portlet:actionURL>

	<script type="text/javascript" src="/html/js/editor/ckeditor_old/ckeditor.js" ><!-- --></script>
	<div class="cmsDetailsBox">

		<p>The resources page is what visitors see when they click either "Read more" or the Resources button on the contest homepage. It provides a thorough overview of the sector or topic, key issues currently being faced, guidance on how members can focus proposals, and resources for further reading.</p>
		<p>Sample resources pages can be found here: <a href="http://climatecolab.org/resources/-/wiki/Main/Youth+action+on+climate+change">Sample 1. </a><a href="http://climatecolab.org/web/guest/resources/-/wiki/Main/Energy+Supply">Sample 2</a>.</p>

	<h2>Resource page content</h2>
		<div id="resourcesSections">
			<form:form action="${updateContestResourcesURL }" commandName="contestResourcesBean"
			 modelAttribute="contestResourcesBean" cssClass="addpropform" id="editForm" method="post">
				<form:hidden path="numberOfSections"/>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="*" />
				</div>

				<c:forEach var="section" items="${contestResourcesBean.sections}" varStatus="x" >
					<form:hidden path="sections[${x.index}].templateSection" data-form-name="templateSection"/>

					<c:if test="${fn:length(contestResourcesBean.sections)-2 eq x.index}">
						<div class="addSection" id="addSection" name="addSection">
							<div class="blue-button">
								<a id="addSectionButton" href="#addSection">ADD section</a>
							</div>
						</div>
					</c:if>

					<div class="addpropbox ${section.deletable ? 'blue deletable' : ''}"
						style="display: ${fn:length(contestResourcesBean.sections)-1 eq x.index ? 'none' : ''}"
						id="section${x.index}">
						<form:hidden path="sections[${x.index}].sectionNew" data-form-name="sectionNew"/>
						<form:hidden path="sections[${x.index}].weight" cssClass="weightInput" data-form-name="weight"/>

						<c:if test="${section.deletable}">
							<div class="deleteIcon"><!-- --></div>
						</c:if>

						<label>
							<c:choose>
								<c:when test="${section.deletable}">
									<strong>Title:</strong><form:input path="sections[${x.index}].title" data-form-name="title"/>
								</c:when>
								<c:otherwise>
									<form:hidden path="sections[${x.index}].title"/>
									<strong>${section.title}</strong>
								</c:otherwise>
							</c:choose>

							<c:if test="${not empty section.helpText}">
							<a class="helpTrigger" href="javascript:;">
								<img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" />
							</a>
							</c:if>
							<br />
							<c:if test="${section.characterLimit  > 0}">
								${section.characterLimit} characters
							</c:if>
						</label>

						<c:if test="${not empty section.helpText}">
							<div class="addprophelp">${section.helpText}</div>
						</c:if>

						<div class="clearfix"><!--  --></div>
						<c:if test="${section.deletable}">
							<strong>Content:</strong>
						</c:if>
						<div class="addpropInputContainer">
							<form:textarea path="sections[${x.index}].content" cssClass="${fn:length(contestResourcesBean.sections)-1 eq x.index ? '': 'ckeditor_placeholder'}" data-form-name="content" />
							<div class="reg_errors">
								<form:errors cssClass="alert alert-error" path="sections[${x.index}].content" />
							</div>
							<div class="clearfix"><!-- --></div>
							<c:if test="${section.characterLimit  > 0}">
								<div class="inputLimitContainer">
									<span class="limit_characterCount"><!--  --></span>
									/&#160;<span class="limit_charactersMax">
									${section.characterLimit} characters</span> characters
								</div>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</form:form>
		</div>
	</div>


	<script type="text/javascript">
		<![CDATA[

		jQuery('document').ready(function(){
			bindAddSectionClick();
			bindDeleteClickEvents(deleteSection);
		});

		function getNumberOfSections(){
			return parseInt(document.getElementById("numberOfSections").value);
		}

		function setNumberOfSections(numberOfSections){
			document.getElementById("numberOfSections").value = numberOfSections.toString();
		}

		function getInputData(sectionDummyElementId, sectionElementId, sectionElementNames){
			var formInputData = []; //Object.create(null);
			var sectionPrefix = "sections";
			for (var i = 0; i < sectionElementNames.length; i++) {
				var sectionDummyInputId = sectionPrefix + sectionDummyElementId + "." + sectionElementNames[i];
				var sectionInputId = sectionPrefix + sectionElementId + "." + sectionElementNames[i];
				var sectionInputName = sectionPrefix + "[" + sectionElementId + "]." + sectionElementNames[i];
				formInputData[sectionDummyInputId] = {id: sectionInputId, name: sectionInputName};
			}
			return formInputData;
		}

		function replaceInputDataByTagName(newSectionElement, newSectionInputData, tagName){
			var sectionFormInputs = newSectionElement.getElementsByTagName(tagName);
			for (var i = 0; i < sectionFormInputs.length; i++) {
				console.log("sectionFormInputs[i].id", sectionFormInputs[i].id);
				var sectionInputData = newSectionInputData[sectionFormInputs[i].id];
				sectionFormInputs[i].id = sectionInputData.id;
				sectionFormInputs[i].name = sectionInputData.name;
			}
		}

		function replaceDummySectionContent(newSectionElement, newSectionInputData, newSectionId){
			newSectionElement.style.display = "";
			newSectionElement.id = newSectionId;
			replaceInputDataByTagName(newSectionElement, newSectionInputData, 'input');
			replaceInputDataByTagName(newSectionElement, newSectionInputData, 'textarea');
		}

		function addNewSection(dummySectionId, newSectionId){
			var dummySectionElement = document.getElementById("section" + dummySectionId);
			var newSectionElement = dummySectionElement.cloneNode(true);

			var sectionElementNames = [];
			[].forEach.call(newSectionElement.getElementsByTagName('input'), function(element) {
				sectionElementNames.push(element.getAttribute("data-form-name"));
			});

			[].forEach.call(newSectionElement.getElementsByTagName('textarea'), function(element) {
				sectionElementNames.push(element.getAttribute("data-form-name"));
				element.classList.remove("rteInitialized");
				element.classList.add("ckeditor_placeholder");
			});

			[].forEach.call(newSectionElement.getElementsByTagName('select'), function(element) {
				sectionElementNames.push(element.getAttribute("data-form-name"));
			});

			var newSectionInputData = getInputData(dummySectionId, newSectionId, sectionElementNames);
			replaceDummySectionContent(newSectionElement, newSectionInputData, newSectionId );

			var addSectionElement = document.getElementById("addSection");
			document.getElementById("editForm").insertBefore(newSectionElement,addSectionElement);

			setNumberOfSections(newSectionId);

			initializeTextEditors();
		}

		function deleteSection(section){
			if(confirm("Do you want to remove this section ?")) {
				var newNumberOfSections = getNumberOfSections() - 1;
				setNumberOfSections(newNumberOfSections);
				getClosest(section, "tr").remove();
			}
		}

		function bindDeleteClickEvents(){
			[].forEach.call(document.getElementsByClassName('deleteIcon'), function(deletableSectionElements) {
				deletableSectionElements.addEventListener('click', deleteSection, false);
			});
		}

		function bindAddSectionClick(){
			var initialNumberOfSections = getNumberOfSections();
			var dummySectionId = initialNumberOfSections;
			var addSectionButtonElement = document.getElementById("addSectionButton");
			addSectionButtonElement.addEventListener("click", function(event) {
				var numberOfSections = getNumberOfSections();
				addNewSection(dummySectionId, numberOfSections + 1);
			});
		}

		]]>
	</script>


	<jsp:directive.include file="./footer.jspx"/>
</jsp:root>