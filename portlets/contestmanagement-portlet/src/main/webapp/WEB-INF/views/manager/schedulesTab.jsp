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
			<div class="addpropbox">
				<strong class="inputTitleLeft">Schedule name:</strong>
				<form:input path="scheduleName" cssClass="wideLargeInput"/>
				<div class="reg_errors"><!--  -->
					<form:errors cssClass="alert alert-error" path="scheduleName" />
				</div>

				<div class="outerVerticalCenter floatRight">
					<div class="blue-button innerVerticalCenter" >
						<a href="#" onclick="document.getElementById('editForm').submit()">SAVE changes to name/phases</a>
					</div>
				</div>
			</div>

			<div class="addpropbox">
				<strong class="inputTitleLeft">Contest phase for this schedule:</strong>
			<table class="contestOverview">
					<col span="1" class="extraSmallColumn"/>
					<col span="1" class="wideColumn"/>
					<!-- <col span="1" class="extraSmallColumn"/> -->
					<col span="2" class="mediumColumn"/>
				<thead>
					<tr style="cursor: default;">
						<!-- <th><input type="checkbox" id="selectAllCheckbox"/>
						</th> -->
						<th>#</th>
						<th>Phase Type</th>
						<!-- <th>Buffer</th> -->
						<th>Start Date</th>
						<th>End Date</th>
					</tr>
				</thead>
				<tbody id="contestOverviewBody">
					<c:set var="dateTimePickerIndex" value="0"/>
					<c:forEach var="schedulePhase" items="${contestScheduleWrapper.schedulePhases}" varStatus="x" >
							<tr draggable="true"
								ondragend="dragEnd(event)"
								ondragstart="dragStart(event)"
								ondrop="drop(event)"
								ondragenter="dragEnter(event)"
								ondragover="dragOver(event)"
								ondragleave="dragLeave(event)"
								style="cursor: default;"
								id = "${schedulePhases.contestSchedulePK}">

								<form:hidden path="schedulePhases[${x.index}].contestScheduleId"
											 data-form-name="contestScheduleId" />

								<form:hidden path="schedulePhases[${x.index}].contestPhaseType"
											 data-form-name="contestPhaseType" />

								<!-- <td ><input type="checkbox"/></td> -->
								<td >${x.index + 1}</td>
								<td >${schedulePhase.contestPhaseTypeTitle}</td>

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
							<td >${x.index + 1}</td>
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

			jQuery('.datetimepicker').datetimepicker({
				lazyInit: true,
				weeks: true,
				format: 'm/d/Y H:i',
				onChangeDateTime: function(dateTimePickerDateString, inst) {
					console.log
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

			var selectAllCheckboxElement = document.getElementById("selectAllCheckbox");
			selectAllCheckboxElement.addEventListener("change", function () {
			var selectAllChecked = selectAllCheckboxElement.checked;
				var contestsTableBody = document.getElementById('contestOverviewBody');
				[].forEach.call(contestsTableBody.getElementsByClassName("checkbox"), function (element) {
					if(selectAllChecked) {
						element.checked = true;
					} else {
						element.checked = false;
					}
				});
			});

		});

		function showContestsWithDataAttributeFilter(dataFilterAttribute){
			var contestsTableBody = document.getElementById('contestOverviewBody');
			[].forEach.call(contestsTableBody.getElementsByTagName('tr'), function (element) {
				var elementDataFilterAttribute =  element.getAttribute("data-filter-attribute");
				if(dataFilterAttribute === "all" || elementDataFilterAttribute === dataFilterAttribute ){
					element.style.display = "";
				} else {
					element.style.display = "none";
				}
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
		}

		function drop(ev) {

			var srcElementId = ev.dataTransfer.getData("srcElementId");
			var srcElement = document.getElementById(srcElementId);

			var targetRow = getClosest(ev.target, "tr")
			var targetParentElement = targetRow.parentNode;
			targetParentElement.insertBefore(srcElement, targetRow)

			reCalculateWeights();

			ev.stopPropagation(); // Stops some browsers from redirecting.
			ev.preventDefault();
			return false;
		}

		function reCalculateWeights(){
		var firstElementInList = document.getElementById("contestOverviewBody");
		var nextElementInList = firstElementInList.firstChild;
		var index = 0;
		do {
			if (filter("weightInput", nextElementInList)){
				index++;
				var weightInput = nextElementInList.getElementsByClassName("weightInput")[0];
				var weightInputLable = weightInput.nextSibling;
				weightInput.value = index;
				weightInputLable.innerHTML = index;
			}
		} while (nextElementInList = nextElementInList.nextSibling)

		}

		function filter(className, element){
			return element.getElementsByClassName(className)[0] != undefined;
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

		//}());
		]]>
	</script>

</jsp:root>