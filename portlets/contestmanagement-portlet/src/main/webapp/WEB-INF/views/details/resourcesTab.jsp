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

	<h1>Introduction</h1>
	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
	dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.
	Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy dolor sit amet.

	<h1>Resources sections</h1>
		<div id="resourcesSections">
			<form:form action="${updateContestResourcesURL }" commandName="contestResourcesBean"
			 modelAttribute="contestResourcesBean" cssClass="addpropform" id="editForm" method="post">
				<form:hidden path="numberOfSections"/>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="*" />
				</div>

				<c:forEach var="section" items="${contestResourcesBean.sections}" varStatus="x" >
					<form:hidden path="sections[${x.index}].templateSection"/>

					<c:if test="${fn:length(contestResourcesBean.sections)-2 eq x.index}">
						<div class="addSection" id="addSection">
							<div class="blue-button">
								<a id="addSectionButton" href="#">ADD section</a>
							</div>
						</div>
					</c:if>

					<div class="addpropbox ${section.deletable ? 'blue deletable' : ''}"
						style="display: ${fn:length(contestResourcesBean.sections)-1 eq x.index ? 'none' : ''}"
						id="section${x.index}">
						<form:hidden path="sections[${x.index}].sectionNew"/>
						<c:if test="${section.deletable}">
							<div class="deleteIcon"><!-- --></div>
						</c:if>

						<label>
							<c:choose>
								<c:when test="${section.deletable}">
									<strong>Title:</strong><form:input path="sections[${x.index}].title"/>
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
							<form:textarea path="sections[${x.index}].content" cssClass="ckeditor_placeholder" />
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
			var sectionElementNames = ["sectionNew", "title", "content"];
			var newSectionInputData = getInputData(dummySectionId, newSectionId, sectionElementNames);

			replaceDummySectionContent(newSectionElement, newSectionInputData, newSectionId );

			var addSectionElement = document.getElementById("addSection");
			document.getElementById("editForm").insertBefore(newSectionElement,addSectionElement);

			setNumberOfSections(newSectionId);
		}

		function deleteSection(section){
			var newNumberOfSections = getNumberOfSections() - 1;
			setNumberOfSections(newNumberOfSections);
			jQuery(section).closest(".addpropbox").remove();
		}

		jQuery(function() {
			var initialNumberOfSections = getNumberOfSections();
			var dummySectionId = initialNumberOfSections;

			$('#addSectionButton').click(function(){
				var numberOfSections = getNumberOfSections();
				console.log("numberOfSections", numberOfSections);
				console.log("initialNumberOfSections", initialNumberOfSections);
				console.log("dummySectionId", dummySectionId);
				addNewSection(dummySectionId, numberOfSections + 1);
			})

			jQuery('.deletable').delegate(".deleteIcon", "click", function() {
				if(confirm("Do want to remove this section ?")) {
					deleteSection(this);
				}
			});

		});
		]]>
	</script>


	<jsp:directive.include file="./footer.jspx"/>
</jsp:root>