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

	<portlet:actionURL var="changeContestScheduleURL">
		<portlet:param name="action_forwardToPage" value="schedulesTab" />
		<portlet:param name="action_errorForwardToPage" value="schedulesTab" />
		<portlet:param name="tab" value="SCHEDULES" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="elementId" value="${contestScheduleWrapper.scheduleId}" />
		<portlet:param name="action" value="updateContestSchedule" />
	</portlet:actionURL>

	<div class="cmsDetailsBox">

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
				<div class="outerVerticalCenter floatRight">
					<div class="blue-button innerVerticalCenter" >
						<a href="#" onclick="saveAsNewSchedule()">SAVE as new schedule</a>
					</div>
				</div>
				<div class="outerVerticalCenter floatRight">
					<div class="blue-button innerVerticalCenter" >
						<a href="#" onclick="saveExistingSchedule()">SAVE changes</a>
					</div>
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
					<tr style="cursor: default;">
						<!-- <th><input type="checkbox" id="selectAllCheckbox"/>
						</th> -->
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
							<tr draggable="true"
								ondragend="dragEnd(event)"
								ondragstart="dragStart(event)"
								ondrop="drop(event)"
								ondragenter="dragEnter(event)"
								ondragover="dragOver(event)"
								ondragleave="dragLeave(event)"
								id = "${schedulePhase.contestSchedulePK}"
								data-form-index = "${x.index + 1}"
								style="cursor: default; display: ${fn:length(contestScheduleWrapper.schedulePhases)-1 eq x.index ? 'none' : ''}"
								data-filter-attribute="${fn:length(contestScheduleWrapper.schedulePhases)-1 eq x.index ? 'dummyPhase' : ''}"
									>

								<form:hidden path="schedulePhases[${x.index}].contestScheduleId" data-form-name="contestScheduleId" />
								<form:hidden path="schedulePhases[${x.index}].contestPhasePK" data-form-name="contestPhasePK" />
								<form:hidden path="schedulePhases[${x.index}].contestPK" data-form-name="contestPK" />
								<form:hidden path="schedulePhases[${x.index}].contestPhaseDeleted" data-form-name="contestPhaseDeleted" />

								<!-- <td ><input type="checkbox"/></td> -->
								<td data-form-attribute="indexLable">${x.index + 1}</td>
								<td >
									<form:select path="schedulePhases[${x.index}].contestPhaseType" class="autoWidth">
										<form:options items="${contestPhaseTypesSelectionItems}" itemValue="value" itemLabel="lable"/>
									</form:select>
								</td>

								<!-- <td>
									<form:checkbox path="schedulePhases[${x.index}].hasBuffer"
												   data-select-attribute="hasBufferCheckbox"/></td> -->
								<td>
									<fmt:formatDate value="${schedulePhase.phaseStartDate}"
													pattern="MM/dd/yyyy HH:mm"
									type="date" dateStyle="short" timeZone="America/New_York" var="formatDate"/>
									<form:input path="schedulePhases[${x.index}].phaseStartDate"
												cssClass="datetimepicker" value="${formatDate}"
												data-type-attribute="start"
												data-index-attribute="${dateTimePickerIndex}"
												data-select-attribute="datetimepicker"
											/>
								</td>

								<td>
									<form:input path="schedulePhases[${x.index}].phaseEndDateFormatted"
												cssClass="datetimepicker"
												data-type-attribute="end"
												data-index-attribute="${dateTimePickerIndex + 1}"
												data-select-attribute="datetimepicker"
											/>
								</td>
								<td>
									<div class="deleteIcon"><!-- --></div>
								</td>
								<c:if test="${schedulePhase.hasBuffer}">
										<tr>show buffer row</tr>
								</c:if>
							</tr>
						<c:set var="dateTimePickerIndex" value="${dateTimePickerIndex + 2}"/>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</form:form>


		<div class="addpropbox" style="margin-top: 20px;">
			<strong class="inputTitleLeft">Contests using this schedule:</strong>
			<table class="contestOverview">
				<col span="1" class="extraSmallColumn"/>
				<col span="1" class="wideColumn" style="text-align: left;"/>
				<col span="1" class="smallColumn"/>
				<col span="1" class="smallColumn"/>
				<thead>
				<tr style="cursor: default;">
					<th>#</th>
					<th>Title</th>
					<th>Tier</th>
					<th></th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${contestScheduleWrapper.contestsUsingSelectedSchedule}" var="contestWrapper" varStatus="x">
						<tr style="cursor: default;">
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

		//(function(){

		jQuery('document').ready(function(){

			initDateTimePicker();

			bindDeleteClickEvents();

			var hasBufferCheckboxElements = document.querySelectorAll('[data-select-attribute="hasBufferCheckbox"]');
			[].forEach.call(hasBufferCheckboxElements, function(element){
				element.addEventListener("click", function(ev) {
					var checked = ev.target.checked;
					console.log("click -> ev", ev);
					console.log("click -> ev.target", ev.target);
				});
			});

			var dropDownElement = document.getElementById("changeContestScheduleSelect");
			dropDownElement.addEventListener("change", function(ev){
				var selectedScheduleId = ev.target.value;
				window.location="/web/guest/cms/-/contestmanagement/manager/tab/SCHEDULES/elementId/" + selectedScheduleId;
			})

		});

		function bindDeleteClickEvents(){
			[].forEach.call(document.getElementsByClassName('deleteIcon'), function(deletableSectionElements) {
				deletableSectionElements.addEventListener('click', deleteContestPhase, false);
			});
		}

		function dragEnd(ev) {
			ev.target.classList.remove("drag");

			[].forEach.call(document.getElementsByTagName('tr'), function (element) {
				element.classList.remove("allowDrop");
			});
		}

		function dragLeave(ev){

			var targetRow = getClosest(ev.target, "tr")
			targetRow.classList.remove("allowDrop");

			//var srcElementId = ev.dataTransfer.getData("srcElementId");
			//var srcElementPreviewId = srcElementPreview.id + "Preview";
			//var srcElementPreview = document.getElementById(srcElementPreviewId)
			//srcElementPreview.parentNode.removeChild(srcElementPreview);
		}

		function dragOver(ev) {
			ev.preventDefault();
			return false;
		}

		function dragEnter(ev) {
			var targetRow = getClosest(ev.target, "tr")
			targetRow.classList.add("allowDrop");
		}

		function dragStart(ev) {
			ev.target.style.visibility = "none";
			ev.dataTransfer.setData("srcElementId", ev.target.id);
			var srcElementId = ev.dataTransfer.getData("srcElementId");
			ev.target.classList.add("drag");
			console.log("drop -> srcElementId", ev.target.id);
			console.log("drop -> ev.target", ev.target);
		}

		function drop(ev) {

			var srcElementId = ev.dataTransfer.getData("srcElementId");
			var srcElement = document.getElementById(srcElementId);


			var targetRow = getClosest(ev.target, "tr")
			var targetParentElement = targetRow.parentNode;

			console.log("drop -> srcElementId", srcElementId);
			console.log("drop -> srcElement", srcElement);
			console.log("drop -> targetRow", targetRow);
			console.log("drop -> targetParentElement", targetParentElement);

			targetParentElement.insertBefore(srcElement, targetRow)

			reCalculateIndex();

			ev.stopPropagation(); // Stops some browsers from redirecting.
			ev.preventDefault();
			return false;
		}

		function reCalculateIndex(){
		var firstElementInList = document.getElementById("contestOverviewBody");
		var nextElementInList = firstElementInList.firstChild;
		var index = 0;
			var dataFormNameAttribute="data-form-index";
		do {
			if (filter(dataFormNameAttribute, nextElementInList)){
				index++;
				var dataFormIndex = nextElementInList.getAttribute(dataFormNameAttribute);
				var dataFormIndexLable = nextElementInList.querySelector("");

				dataFormIndex.value = index;
				indexInput.innerHTML = index;
			}
		} while (nextElementInList = nextElementInList.nextSibling)

		}

		function filter(attribute, element){
			return element.getAttribute(attribute)!= undefined;
		}

		function getClosest(el, tag) {
			// this is necessary since nodeName is always in upper case
			tag = tag.toUpperCase();
			do {
				if (el.nodeName === tag) {
					// tag name is found! let's return it. :)
					return el;
				}
			} while (el = el.parentNode);

			// not found :(
			return null;
		}

		function deleteContestPhase(event){
			if(confirm("Do you want to remove this contestPhase ?")) {
				var contestPhaseRow = getClosest(event.target, "tr");
				contestPhaseRow.style.display = "none";
				var contestPhaseDeletedElement = contestPhaseRow.querySelectorAll("[data-form-name=contestPhaseDeleted]")[0];
				contestPhaseDeletedElement.value = true;
				reCalculateIndex();
			}
		}

		function addContestPhase(event){
			event.preventDefault();

			var contestPhasesListNode = document.getElementById("contestOverviewBody");
			var dummyContestPhaseRow = document.querySelectorAll("[data-filter-attribute=dummyPhase]")[0];
			var newContestPhaseRow = dummyContestPhaseRow.cloneNode(true);
			newContestPhaseRow.style.display = "";
			newContestPhaseRow.setAttribute("id", "-1");
			newContestPhaseRow.setAttribute("data-filter-attribute","");
			newContestPhaseRow.getElementsByClassName('deleteIcon')[0].addEventListener('click', deleteContestPhase, false);

			var contestPhasePKElement = newContestPhaseRow.querySelectorAll("[data-form-name=contestPhasePK]")[0];
			contestPhasePKElement.value = -1;

			contestPhasesListNode.appendChild(newContestPhaseRow);
			initDateTimePicker();
		}

		function saveExistingSchedule(){
			document.getElementById('createNewFlag').value = false;
			removeLastContestPhases();
			document.getElementById('editForm').submit();
		}

		function saveAsNewSchedule(){
			document.getElementById('createNewFlag').value = true;
			removeLastContestPhases();
			document.getElementById('editForm').submit();
		}

		function removeLastContestPhases(){
			var dummyContestPhaseRow = document.querySelectorAll("[data-filter-attribute=dummyPhase]")[0];
			dummyContestPhaseRow.remove();
		}

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
		//}());
		]]>
	</script>

</jsp:root>