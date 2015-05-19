<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="../init.jspx" />

	<c:choose>
		<c:when test="${param.manager}">
			<jsp:directive.include file="../manager/header.jspx"/>
		</c:when>
		<c:otherwise>
			<jsp:directive.include file="../details/header.jspx"/>
		</c:otherwise>
	</c:choose>

	<portlet:resourceURL var="getSectionDefinition" id="getSectionDefinition" />

	<portlet:actionURL var="changeElementURL">
		<portlet:param name="tab" value="${param.tab}" />
		<portlet:param name="manager" value="${param.manager}" />
		<portlet:param name="elementId" value="${contestProposalTemplateWrapper.planTemplateId}" />
	</portlet:actionURL>

	<portlet:actionURL var="updateContestProposalTemplateURL">
		<portlet:param name="action_forwardToPage" value="proposalTemplateTab" />
		<portlet:param name="action_errorForwardToPage" value="proposalTemplateTab" />
		<portlet:param name="tab" value="PROPOSALTEMPLATE" />
		<portlet:param name="contestId" value="${contestWrapper.contestPK }" />
		<portlet:param name="action" value="updateContestProposalTemplate" />
	</portlet:actionURL>

	<script type="text/javascript" src="/html/js/editor/ckeditor_old/ckeditor.js" ><!-- --></script>

	<c:if test="${param.manager}">
		<jsp:directive.include file="../manager/action.jspx"/>
	</c:if>

	<div class="cmsDetailsBox">

		<p>Templates hold the set of questions members will be asked to answer in completing a proposal.<br/>
			If you would like any changes to your template, please submit a comment below for the Climate CoLab team.
		</p>

		<h3>Proposal template details</h3>

		<form:form action="${changeElementURL }" commandName="elementSelectIdWrapper" id="selectForm" method="post">
			<div class="addpropbox">
				<strong class="inputTitleLeft">Selected template:</strong>
				<div class="addpropInputContainer">
					<form:select path="elementId" id="changeElementSelect" cssClass="wideLargeInput">
						<form:options items="${elementSelectIdWrapper.selectionItems}" itemValue="value" itemLabel="lable"/>
					</form:select>
					<div class="reg_errors">
						<form:errors cssClass="alert alert-error" path="elementId" />
					</div>
				</div>
			</div>
		</form:form>

		<div id="resourcesSections">
			<form:form action="${updateElementFormURL }" commandName="contestProposalTemplateWrapper"
			 cssClass="addpropform" id="editForm" method="post">

				<form:hidden path="createNew" id="createNew"/>
				<form:hidden path="numberOfSections"/>
				<form:hidden path="planTemplateId"/>

				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="*" />
				</div>

				<div class="addpropbox">
					<strong class="inputTitleLeft">Template name:</strong>
					<form:input path="templateName"/>
					<div class="reg_errors"><!--  -->
						<form:errors cssClass="alert alert-error" path="templateName" />
					</div>
				</div>

				<h3>Proposal template sections</h3>
				<c:forEach var="section" items="${contestProposalTemplateWrapper.sections}" varStatus="x" >

					<div class="dropzone" style="display: ${fn:length(contestProposalTemplateWrapper.sections)-1 eq x.index ? 'none' : ''}"
						 ondrop="drop(event)" ondragenter="dragEnter(event)" ondragover="dragOver(event)" ondragleave="dragLeave(event)"
							data-bind-id="${x.index}"
							id="dropzone${x.index}"><span class="counter" style="float: left;">Position # ${x.index +1}</span><span style="float:right">To change order drag a section and drop it on any of these areas.</span></div>

					<div class="addpropbox ${section.deletable ? 'blue deletable' : ''} ${section.type}"
						style="display: ${fn:length(contestProposalTemplateWrapper.sections)-1 eq x.index ? 'none' : ''}"
						id="section${x.index}"
						data-section-id="${x.index}"
						draggable="true"
						ondragend="dragEnd(event)"
						ondragstart="dragStart(event)">

						<form:hidden path="sections[${x.index}].templateSection" data-form-name="templateSection"/>
						<form:hidden path="sections[${x.index}].sectionNew" data-form-name="sectionNew"/>
						<form:hidden path="sections[${x.index}].weight" cssClass="weightInput" data-form-name="weight" />
						<form:hidden path="sections[${x.index}].sectionDefinitionId" data-form-name="sectionDefinitionId" />

						<c:if test="${section.deletable}">
							<div class="deleteIcon"><!-- --></div>
						</c:if>

						<label>
							<strong>Section type:</strong>
							<form:select path="sections[${x.index}].type" data-form-name="type">
								<form:option value="NONE" label="--- Select ---" />
								<form:options items="${sectionTypeSelectionItems}" itemValue="value" itemLabel="lable"/>
							</form:select>

							<div class="levelVisible" style="${fn:containsIgnoreCase(section.type, 'PROPOSAL') ? '' : 'display: none;'}">
								<strong>Level:</strong>
								<form:select path="sections[${x.index}].level" data-form-name="level">
									<form:options items="${levelSelectionItems}" itemValue="value" itemLabel="lable"/>
								</form:select>
							</div>
							<strong>Title:</strong><form:input path="sections[${x.index}].title" data-form-name="title"/>
							<br />
							<span class="floatLeft"><strong>Character limit:</strong></span>
							<form:input path="sections[${x.index}].characterLimit" data-form-name="characterLimit"/>
						</label>

						<div class="clearfix"><!--  --></div>
						<strong>Help text:</strong>
						<div class="addpropInputContainer">
							<form:textarea path="sections[${x.index}].helpText"  data-form-name="helpText"  />   <!-- cssClass="ckeditor_placeholder" -->
							<div class="reg_errors">
								<form:errors cssClass="alert alert-error" path="sections[${x.index}].helpText" />
							</div>
						</div>

						<strong>Default text:</strong>
						<div class="addpropInputContainer">
							<form:textarea path="sections[${x.index}].defaultText"  data-form-name="defaultText"  />   <!-- cssClass="ckeditor_placeholder" -->
							<div class="reg_errors">
								<form:errors cssClass="alert alert-error" path="sections[${x.index}].defaultText" />
							</div>
						</div>

					</div>
				</c:forEach>

					<div class="addSection" id="addSection" name="addSection">
						<div class="blue-button">
							<a id="addSectionButton" href="#addSection">ADD section</a>
					</div>
				</div>

			</form:form>
		</div>
	</div>

	<c:if test="${param.manager}">
		<div class="cmsDetailsBox">
			<h3>Contests using this template:</h3>
			<div class="addpropbox" style="margin-top: 20px;">
			<table class="contestOverview">
				<col span="1" class="extraSmallColumn"/>
				<col span="1" class="wideColumn" style="text-align: left;"/>
				<col span="1" class="smallColumn"/>
				<col span="1" class="smallColumn"/>
				<thead>
				<tr>
					<th>#</th>
					<th>Title</th>
					<th>Tier</th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${contestProposalTemplateWrapper.contestsUsingSelectedTemplate}" var="contestWrapper" varStatus="x">
					<tr>
						<td data-form-name="index">${x.index + 1}</td>
						<td ><collab:contestLink contestId="${contestWrapper.contestPK}" text="${contestWrapper.contestShortName}"/></td>
						<td>Tier ${contestWrapper.contestTier}</td>
						<td>
							<div class="blue-button innerVerticalCenter" >
								<a href="/web/guest/cms/-/contestmanagement/contestId/${contestWrapper.contestPK}" target="_blank">EDIT</a>
							</div>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</c:if>

	<script type="text/javascript">
		<![CDATA[

		jQuery('document').ready(function(){
			bindDeleteClicks();
			bindAddSectionClick();
			bindMassActionSelectChange();
			bindContestScheduleSelectChange();
		});


		function bindDeleteClicks(){
			[].forEach.call(document.getElementsByClassName('deletable'), function(deletableSectionElements) {
				deletableSectionElements.getElementsByClassName('deleteIcon')[0].addEventListener('click', deleteSection, false);
			});
		}

		function bindAddSectionClick(){
			var initialNumberOfSections = getNumberOfSections();
			var addSectionButtonElement = document.getElementById("addSectionButton");

			addSectionButtonElement.addEventListener("click", function(event) {
				var numberOfSections = getNumberOfSections();
				addNewSection(initialNumberOfSections, numberOfSections + 1);
			});
		}

		function bindMassActionSelectChange(){
			[].forEach.call(document.getElementsByTagName('select'), function(selectElement) {
				var dataFormName = selectElement.getAttribute("data-form-name");
				if (dataFormName == "type") {
					selectElement.addEventListener('change', selectTypeChangeCallback, false);
				} else {
					selectElement.addEventListener('change', function(event){
						event.preventDefault();
					}, false);
				}
			});
		}


		function getSectionDefinitionFromServer(sectionDefinitionId){
			var deferred = jQuery.Deferred();

			jQuery.ajax({
				type: 'POST',
				dataType: 'text',
				url : "${getSectionDefinition}",
				data: "sectionDefinitionId="+sectionDefinitionId,
				success: function(jsondata){
					deferred.resolve(jsondata);
				}
			});

			return deferred;
		}

		function dragEnd(ev) {
			ev.target.classList.remove("dragState");
			[].forEach.call(document.getElementsByClassName('dropzone'), function (element) {
				element.classList.remove("showAllowDropState");
				element.classList.remove("allowDropState");
			});
		}

		function dragLeave(ev){
			ev.target.classList.remove("allowDropState");
		}

		function dragOver(ev) {
			ev.preventDefault();
			return false;
		}

		function dragEnter(ev) {
			ev.target.classList.add("allowDropState");
		}

		function dragStart(ev) {
			ev.dataTransfer.setData("srcElementId", ev.target.id);
			ev.target.classList.add("dragState");
			[].forEach.call(document.getElementsByClassName('dropzone'), function(element) {
				if(!ev.target.previousSibling.isEqualNode(element)) {
					element.classList.add("showAllowDropState");
				}
			});
		}

		function drop(ev) {

			var targetDropzoneElement = ev.target;
			var targetParentElement = targetDropzoneElement.parentNode;

			var srcElementId = ev.dataTransfer.getData("srcElementId");
			var srcSectionElement = document.getElementById(srcElementId);
			var srcSectionDropzoneElement = srcSectionElement.previousSibling;

			if(!srcSectionElement.previousSibling.isEqualNode(targetDropzoneElement)) {

				var posTargetDropzone = parseInt(targetDropzoneElement.getAttribute("data-bind-id"))+2;
				var posSectionElement = parseInt(srcSectionElement.getElementsByClassName("weightInput")[0].value);

				if(posTargetDropzone < posSectionElement) {
					targetParentElement.insertBefore(srcSectionElement, targetDropzoneElement);
					targetParentElement.insertBefore(srcSectionDropzoneElement, srcSectionElement);
				} else {

					var targetDropzoneElementBelow = ev.target.nextSibling.nextSibling;
					targetParentElement.insertBefore(srcSectionElement, targetDropzoneElementBelow);
					targetParentElement.insertBefore(srcSectionDropzoneElement, srcSectionElement);
				}
				reCalculateWeights();
			}

			[].forEach.call(document.getElementsByClassName('dropzone'), function (element) {
				element.classList.remove("showAllowDropState");
				element.classList.remove("allowDropState");
			});

			[].forEach.call(document.getElementsByClassName('addpropbox'), function (element) {
				element.classList.remove("dragState");
			});

			ev.preventDefault();
			return false;
		}

		function filter(className, element){
			return element.getElementsByClassName(className)[0] != undefined;
		}

		function reCalculateWeights(){
			var firstSectionInList = document.getElementById("editForm");
			var nextSectionInList = firstSectionInList.firstChild;
			var index = 0;
			do {

				if (filter("counter", nextSectionInList)){
					if(getNumberOfSections() !== index) {
						index++;
					}
					var dropZoneDivCounter = nextSectionInList.getElementsByClassName("counter")[0];
					dropZoneDivCounter.innerHTML = "Position #" + index ;
				}

				if (filter("weightInput", nextSectionInList)){
					var weightInput = nextSectionInList.getElementsByClassName("weightInput")[0];
					weightInput.value = index;
				}

			} while (nextSectionInList = nextSectionInList.nextSibling)

		}

		function getNumberOfSections(){
			return parseInt(document.getElementById("numberOfSections").value);
		}

		function setNumberOfSections(numberOfSections){
			document.getElementById("numberOfSections").value = numberOfSections.toString();
		}

		function addNewSection(templateSectionId, newSectionId){
			var templateSectionElement = document.getElementById("section" + templateSectionId);

			var newDropzoneElement = templateSectionElement.previousSibling.cloneNode(true);
			var newSectionElement = templateSectionElement.cloneNode(true);

			var sectionElementNames = extractInputElementsInNode(newSectionElement);
			var newSectionInputData = createFormInputsIdReplacements(templateSectionId, newSectionId, sectionElementNames, "sections");
			replaceSectionFormIds(newSectionElement, newSectionInputData, newSectionId );

			var addSectionElement = document.getElementById("addSection");
			document.getElementById("editForm").insertBefore(newSectionElement,addSectionElement);

			newDropzoneElement.style.display = "";
			newDropzoneElement.id = "dropzone" + newSectionId;
			newDropzoneElement.setAttribute("data-bind-id", newSectionId);

			document.getElementById("editForm").insertBefore(newDropzoneElement,newSectionElement);

			setNumberOfSections(newSectionId);

			newSectionElement.addEventListener('change', selectTypeChangeCallback, false);
			newSectionElement.getElementsByClassName('deleteIcon')[0].addEventListener('click', deleteSection, false);

			reCalculateWeights();
		}

		function deleteSection(event){
			if(confirm("Do you want to remove this section ?")) {
				var section = event.target;
				var newNumberOfSections = getNumberOfSections() - 1;
				setNumberOfSections(newNumberOfSections);
				var sectionElement = section.parentNode;
				var previousDropzone = section.parentNode.previousSibling;
				document.getElementById("editForm").removeChild(previousDropzone);
				document.getElementById("editForm").removeChild(sectionElement);
				reCalculateWeights();
			}
		}

		function selectTypeChangeCallback(event){
				event.preventDefault();
				var selectedSectionDefinitionId = event.target.value;

				if(selectedSectionDefinitionId.indexOf("PROPOSAL") >= 0 ){
					event.target.nextSibling.style.display = "";
				} else {
					event.target.nextSibling.style.display = "none";
				}
				/*
				getSectionDefinitionFromServer(selectedSectionDefinitionId).done(function(jsondata){
					var sectionDefinitionContent = JSON.parse(jsondata);
					var reloadSectionElement = event.target.parentNode.parentNode;
					try{
						[].forEach.call(reloadSectionElement.getElementsByTagName('input'), function(element) {
							var dataFormName = element.getAttribute("data-form-name");
							element.value = sectionDefinitionContent[dataFormName];
						});

						[].forEach.call(reloadSectionElement.getElementsByTagName('textarea'), function(element) {
							var dataFormName = element.getAttribute("data-form-name");
							element.value = sectionDefinitionContent[dataFormName];
						});
					}catch (e){
						console.log(e);
					}
				});*/
		}


		function bindContestScheduleSelectChange(){
			var dropDownElement = document.getElementById("changeElementSelect");
			dropDownElement.addEventListener("change", function(ev){
				var selectedScheduleId = ev.target.value;
				window.location="/web/guest/cms/-/contestmanagement/manager/tab/PROPOSALTEMPLATES/elementId/" + selectedScheduleId;
			})
		}

		]]>
	</script>

	<c:if test="${not param.manager}">
		<jsp:directive.include file="../details/footer.jspx"/>
	</c:if>

</jsp:root>