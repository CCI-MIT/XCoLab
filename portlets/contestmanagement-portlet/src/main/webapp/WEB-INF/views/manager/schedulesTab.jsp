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

	<portlet:actionURL var="updateContestScheduleURL">
		<portlet:param name="action_forwardToPage" value="schedulesTab" />
		<portlet:param name="action_errorForwardToPage" value="schedulesTab" />
		<portlet:param name="tab" value="SCHEDULES" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="action" value="updateContestSchedule" />
	</portlet:actionURL>

	<portlet:actionURL var="changeElementURL">
		<portlet:param name="tab" value="${param.tab}" />
		<portlet:param name="manager" value="${param.manager}" />
		<portlet:param name="elementId" value="${contestProposalTemplateWrapper.planTemplate.id}" />
	</portlet:actionURL>

	<portlet:actionURL var="deleteContestScheduleURL">
		<portlet:param name="action_forwardToPage" value="schedulesTab" />
		<portlet:param name="action_errorForwardToPage" value="schedulesTab" />
		<portlet:param name="tab" value="SCHEDULES" />
		<portlet:param name="scheduleId" value="${contestScheduleWrapper.scheduleId}" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="action" value="deleteContestSchedule" />
	</portlet:actionURL>

	<portlet:actionURL var="createContestScheduleURL">
		<portlet:param name="action_forwardToPage" value="schedulesTab" />
		<portlet:param name="action_errorForwardToPage" value="schedulesTab" />
		<portlet:param name="tab" value="SCHEDULES" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="action" value="createContestSchedule" />
	</portlet:actionURL>

	<portlet:actionURL var="deleteContestScheduleURL">
		<portlet:param name="action_forwardToPage" value="schedulesTab" />
		<portlet:param name="action_errorForwardToPage" value="schedulesTab" />
		<portlet:param name="tab" value="SCHEDULES" />
		<portlet:param name="scheduleId" value="${contestScheduleWrapper.scheduleId}" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="action" value="deleteContestSchedule" />
	</portlet:actionURL>

	<portlet:actionURL var="createContestScheduleURL">
		<portlet:param name="action_forwardToPage" value="schedulesTab" />
		<portlet:param name="action_errorForwardToPage" value="schedulesTab" />
		<portlet:param name="tab" value="SCHEDULES" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="action" value="createContestSchedule" />
	</portlet:actionURL>

	<form action="${deleteContestScheduleURL}" id="deleteScheduleForm" method="post" style="visibility: hidden;" >
		<!-- -->
	</form>

	<form action="${createContestScheduleURL}" id="createScheduleForm" method="post" style="visibility: hidden;" >

		<!-- -->
	</form>

	<div class="cmsDetailsBox">
		<div style="margin-bottom: 40px;">
			<div class="floatRight outerVerticalCenter">
				<div class="blue-button innerVerticalCenter" >
					<a href="#" onclick="saveExistingSchedule()">SAVE schedule</a>
				</div>
				<div class="blue-button innerVerticalCenter" >
					<a href="#" onclick="saveAsNewSchedule()">SAVE as new schedule</a>
				</div>
				<div class="blue-button innerVerticalCenter" >

					<a href="#" onclick="submitCreateScheduleForm()">CREATE new schedule</a>
				</div>
				<div class="blue-button innerVerticalCenter" >
					<a href="#" onclick="submitDeleteScheduleForm()">DELETE schedule</a>

				</div>
			</div>
		</div>
	</div>

	<div class="cmsDetailsBox">
		<div class="reg_errors"><!--  -->
			<form:errors cssClass="alert alert-error" path="*" />
		</div>

		<form:form action="${changeContestScheduleURL }" commandName="elementSelectIdWrapper" id="selectForm" method="post">

			<div class="addpropbox">
				<strong class="inputTitleLeft">Schedule template:</strong>
				<div class="addpropInputContainer">
					<form:select path="elementId" id="changeContestScheduleSelect" cssClass="wideLargeInput">
						<form:options items="${elementSelectIdWrapper.selectionItems}" itemValue="value" itemLabel="lable"/>
					</form:select>
					<div class="reg_errors">
						<form:errors cssClass="alert alert-error" path="elementId" />
					</div>
				</div>
			</div>
		</form:form>

		<form:form action="${updateContestScheduleURL }" commandName="contestScheduleWrapper" id="editForm" method="post">
			<form:hidden path="scheduleId"/>

			<form:hidden path="createNew" id="createNewFlag"/>

			<div class="addpropbox">
				<strong class="inputTitleLeft">Schedule name:</strong>
				<form:input path="scheduleName" cssClass="wideLargeInput"/>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="scheduleName" />
				</div>
			</div>

			<div class="addpropbox">
				<strong class="inputTitleLeft">Contest phases for this schedule:</strong>
				<div class="outerVerticalCenter floatRight">
					<div class="blue-button innerVerticalCenter" >
						<a href="#" onclick="addContestPhase(event)">Add contest phase</a>
					</div>
				</div>
			<table class="contestOverview">
					<col span="1" class="extraSmallColumn"/>
					<col span="1" class="wideColumn"/>
					<!-- <col span="1" class="extraSmallColumn"/> -->
					<col span="2" class="mediumColumn"/>
					<col span="1" class="extraSmallColumn"/>
				<thead>
					<tr>
						<th>#</th>
						<th>Phase Type</th>
						<!-- <th>Buffer</th> -->
						<th>Start Date</th>
						<th>End Date</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="contestOverviewBody" class="contestPhases">
					<c:set var="dateTimePickerIndex" value="0"/>
					<c:forEach var="schedulePhase" items="${contestScheduleWrapper.schedulePhases}" varStatus="x" >
							<tr data-filter-attribute="${fn:length(contestScheduleWrapper.schedulePhases) - 1 eq x.index ? 'dummyPhase' : ''}"
								data-form-index = "${x.index}"
								style="${fn:length(contestScheduleWrapper.schedulePhases) - 1 eq x.index ? 'display:none' : ''}"
									>

								<form:hidden path="schedulePhases[${x.index}].contestScheduleId" data-form-name="contestScheduleId" />
								<form:hidden path="schedulePhases[${x.index}].contestPhasePK" data-form-name="contestPhasePK" />
								<form:hidden path="schedulePhases[${x.index}].contestPK" data-form-name="contestPK" />
								<form:hidden path="schedulePhases[${x.index}].contestPhaseDeleted" data-form-name="contestPhaseDeleted" />

								<div class="reg_errors"><!--  -->
									<form:errors cssClass="alert alert-error" path="schedulePhases[${x.index}].phaseStartDate" />
								</div>

								<td data-form-attribute="indexLable">${x.index + 1}</td>
								<td >
									<form:select path="schedulePhases[${x.index}].contestPhaseType" class="autoWidth" data-form-name="contestPhaseType">
										<form:options items="${contestPhaseTypesSelectionItems}" itemValue="value" itemLabel="lable"/>
									</form:select>
								</td>
								<!-- <td>
									<form:checkbox path="schedulePhases[${x.index}].hasBuffer"
												   data-select-attribute="hasBufferCheckbox"/></td> -->
								<td>
									<fmt:formatDate value="${schedulePhase.phaseStartDate}" pattern="MM/dd/yyyy HH:mm"
													type="date" dateStyle="short" timeZone="America/New_York" var="formatDate"/>

									<form:input path="schedulePhases[${x.index}].phaseStartDate"
												cssClass="datetimepicker" value="${formatDate}"
												data-type-attribute="start"
												data-index-attribute="${dateTimePickerIndex}"
												data-select-attribute="datetimepicker"
												data-form-name="phaseStartDate"
											/>
								</td>

								<td>
									<form:input path="schedulePhases[${x.index}].phaseEndDateFormatted"
												cssClass="datetimepicker"
												data-type-attribute="end"
												data-index-attribute="${dateTimePickerIndex + 1}"
												data-select-attribute="datetimepicker"
												data-form-name="phaseEndDateFormatted"
											/>
								</td>
								<td>
									<div class="deleteIcon"><!-- --></div>
								</td>
							</tr>
						<c:set var="dateTimePickerIndex" value="${dateTimePickerIndex + 2}"/>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</form:form>
	</div>


	<div class="cmsDetailsBox">

		<div class="addpropbox" style="margin-top: 20px;">
			<strong class="inputTitleLeft">Contests using this schedule:</strong>
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
					<c:forEach items="${contestScheduleWrapper.contestsUsingSelectedSchedule}" var="contestWrapper" varStatus="x">
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

	<script type="text/javascript">
		<![CDATA[

		jQuery('document').ready(function(){
			initDateTimePicker();
			bindDeleteClickEvents(deleteContestPhase);
			bindContestScheduleSelectChange();
			bindCheckBoxClick();
		});

		function initDateTimePicker(){
			jQuery('.datetimepicker').datetimepicker({
				lazyInit: true,
				weeks: true,
				format: 'm/d/Y H:i',
				onChangeDateTime: function(dateTimePickerDateString, inst) {
					var dateTimePickerIndex = inst[0].getAttribute("data-index-attribute");
					var nexDateTimePickerIndex = parseInt(dateTimePickerIndex) + 1;
					var nextDateTimePicker = document.querySelectorAll('[data-index-attribute="' + nexDateTimePickerIndex + '"]');
					nextDateTimePicker = nextDateTimePicker[0];
					if(nextDateTimePicker.value != undefined) {
						try {
							var nextDateTimePickerDate = new Date(nextDateTimePicker.value);
							var dateTimePickerDate = new Date(dateTimePickerDateString);
							if(dateTimePickerDate > nextDateTimePickerDate){
								nextDateTimePicker.value = dateTimePickerDate.dateFormat('m/d/Y H:i');
							}
						} catch (e){
							console.log("Couldn't parse nextDateTimePickerDate", e);
						}
					}
				}});
		}

		function bindCheckBoxClick(){
			var hasBufferCheckboxElements = document.querySelectorAll('[data-select-attribute="hasBufferCheckbox"]');
			[].forEach.call(hasBufferCheckboxElements, function(element){
				element.addEventListener("click", function(ev) {
					var checked = ev.target.checked;
					console.log("click -> ev", ev);
					console.log("click -> ev.target", ev.target);
					// TODO implement buffer
				});
			});
		}

		function bindContestScheduleSelectChange(){

			var dropDownElement = document.getElementById("changeContestScheduleSelect");

			dropDownElement.addEventListener("change", function(ev){
				var selectedScheduleId = ev.target.value;
				window.location="/web/guest/cms/-/contestmanagement/manager/tab/SCHEDULES/elementId/" + selectedScheduleId;
			})
		}

		function bindDeleteClickEvents(callback){
			[].forEach.call(document.getElementsByClassName('deleteIcon'), function(deletableSectionElements) {
				deletableSectionElements.addEventListener('click', callback, false);
			});
		}

		function deleteContestPhase(event){
			if(confirm("Do you want to remove this contestPhase ?")) {
				var contestPhaseRow = getClosest(event.target, "tr");
				var isNewContestPhase = contestPhaseRow.querySelectorAll("[data-form-name=contestPhasePK]")[0].value == -1;
				if(isNewContestPhase){
					contestPhaseRow.remove();
				} else{
					contestPhaseRow.style.display = "none";
					var contestPhaseDeletedElement = contestPhaseRow.querySelectorAll("[data-form-name=contestPhaseDeleted]")[0];
					contestPhaseDeletedElement.value = true;
				}
				reCalculateIndex();
			}
		}

		function addContestPhase(event){
			event.preventDefault();

			var dummyContestPhaseRow = getDummyContestPhase();
			var dummyContestPhaseIndex = dummyContestPhaseRow.getAttribute("data-form-index");

			var newContestPhaseRow = dummyContestPhaseRow.cloneNode(true);
			var newContestPhaseIndex = getHighestUsedContestPhaseIndex() + 1;

			var sectionElementNames = extractInputElementsInNode(newContestPhaseRow);
			var newSectionInputData = createFormInputsIdReplacements(dummyContestPhaseIndex, newContestPhaseIndex, sectionElementNames , "schedulePhases");
			replaceSectionFormIds(newContestPhaseRow, newSectionInputData);

			newContestPhaseRow.setAttribute("data-filter-attribute","");
			newContestPhaseRow.setAttribute("data-form-index", newContestPhaseIndex.toString());
			newContestPhaseRow.getElementsByClassName('deleteIcon')[0].addEventListener('click', deleteContestPhase, false);

			var contestPhasesListNode = document.getElementById("contestOverviewBody");
			contestPhasesListNode.appendChild(newContestPhaseRow);

			initDateTimePicker();
			reCalculateIndex();
		}

		function getHighestUsedContestPhaseIndex(){
			var maxIndex = 0;
			[].forEach.call(document.querySelectorAll("[data-form-index]"), function (contestPhaseIndexElement) {
				var contestPhaseIndexString = contestPhaseIndexElement.getAttribute("data-form-index");
				var contestPhaseIndex = parseInt(contestPhaseIndexString);
				var isNotElementDummyPhase = contestPhaseIndexElement.getAttribute("data-filter-attribute") !== "dummyPhase";
				if(contestPhaseIndex > maxIndex && isNotElementDummyPhase){
					maxIndex = contestPhaseIndex;
				}
			});
			return maxIndex;
		}

		function reCalculateIndex(){
			var firstElementInList = document.getElementById("contestOverviewBody");
			var nextElementInList = firstElementInList.firstChild;
			var index = 0;
			do {
				if(!isElementDummyContestPhase(nextElementInList)) {
					index++;
					var dataFormIndexLable = nextElementInList.querySelector("[data-form-attribute=indexLable]");
					dataFormIndexLable.innerHTML = index.toString();
				}
			} while (nextElementInList = nextElementInList.nextSibling)
		}

		function isElementDummyContestPhase(element){
			var dataFormAttribute = element.getAttribute("data-filter-attribute");
			return dataFormAttribute == "dummyPhase";
		}

		function removeDummyContestPhases(){
			var dummyContestPhaseRow = getDummyContestPhase();
			dummyContestPhaseRow.remove();
		}

		function getDummyContestPhase(){
			return document.querySelectorAll("[data-filter-attribute=dummyPhase]")[0];
		}

		function saveExistingSchedule(){

			document.getElementById('createNewFlag').value = false;

			removeDummyContestPhases();
			document.getElementById('editForm').submit();
		}

		function saveAsNewSchedule(){

			document.getElementById('createNewFlag').value = true;

			removeDummyContestPhases();
			document.getElementById('editForm').submit();
		}

		function submitCreateScheduleForm(){

			document.getElementById('createScheduleForm').submit();
		}

		function submitDeleteScheduleForm(){
			document.getElementById('deleteScheduleForm').submit();

		}

		]]>
	</script>

</jsp:root>