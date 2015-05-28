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

	<portlet:actionURL var="updateContestOverviewURL">
		<portlet:param name="action_forwardToPage" value="overviewTab" />
		<portlet:param name="action_errorForwardToPage" value="overviewTab" />
		<portlet:param name="tab" value="OVERVIEW" />
		<portlet:param name="manager" value="true" />
		<portlet:param name="action" value="updateContestOverview" />
	</portlet:actionURL>

	<portlet:resourceURL var="getExport" id="getExport">
		<portlet:param name="action_forwardToPage" value="overviewTab" />
		<portlet:param name="action_errorForwardToPage" value="overviewTab" />
		<portlet:param name="tab" value="OVERVIEW" />
		<portlet:param name="manager" value="true" />
	</portlet:resourceURL>



	<div class="cmsDetailsBox">
		<div style="margin-bottom: 40px;">
			<div class="floatRight outerVerticalCenter">
				<div class="blue-button innerVerticalCenter" >
					<a href="/web/guest/cms/-/contestmanagement/createContest" target="_blank">Create new Contest</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="cmsDetailsBox">
		<div class="floatRight outerVerticalCenter">
			<div class="innerVerticalCenter floatLeft">
				<h3>Filter: </h3>
			</div>
			<div class="innerVerticalCenter">
				<select id="contestsFilterSelect">
					<option value="all" label="All contests"/>
					<option value="active" label="Active contests"/>
					<option value="prior" label="Prior contests"/>
				</select>
			</div>
		</div>

		<form:form action="${updateContestOverviewURL }" commandName="contestOverviewWrapper" id="editForm" method="post">
			<div class="outerVerticalCenter">
				<div class="innerVerticalCenter">
					<form:select path="selectedMassAction" id="selectedMassAction">
						<form:options items="${massActionsItems}" itemValue="value" itemLabel="lable"/>
					</form:select>
				</div>
				<div class="blue-button innerVerticalCenter" >
					<a href="#" id="submitButton">SUBMIT</a>
				</div>
			</div>
			<div id="massMessage" style="display: none;">
				<h3 class="floatLeft">Subject</h3>
				<form:input path="massMessageBean.subject" />
				<div class="clearLeft"><!-- --></div>
				<h3 class="floatLeft">Body</h3>
				<form:textarea path="massMessageBean.body"/>
			</div>
			<table class="contestOverview">
					<col span="2" class="extraSmallColumn"/>
					<col span="1" class="wideColumn"/>
					<col span="1" class="mediumColumn"/>
					<col span="9" class="extraSmallColumn"/>
					<col span="1" class="smallColumn"/>
				<thead>
					<tr>
						<th><input type="checkbox" id="selectAllCheckbox"/>
						</th>
						<th>#</th>
						<th>Title</th>
						<th>Phase</th>
						<th class="rotate">Active</th>
						<th class="rotate">Private</th>
						<th class="rotate">Featured</th>
						<th class="rotate">Subscribed</th>
						<th class="rotate">Judges</th>
						<th class="rotate">Advisors</th>
						<th class="rotate">Fellows</th>
						<th class="rotate">Proposals</th>
						<th class="rotate">Comments</th>
						<th class="rotate"></th>
					</tr>
				</thead>
				<tbody id="contestOverviewBody">
					<c:forEach var="contestWrapper" items="${contestOverviewWrapper.contestWrappers}" varStatus="x" >
							<tr draggable="true"
								id = "${contestWrapper.contestPK}"
								data-filter-attribute="${contestWrapper.contestActive ? 'active' : 'prior'}"
								class = "${x.index %2==0 ? 'blue' : ''} draggable"
								data-filter-visible="true"
									>
								<form:hidden path="contestWrappers[${x.index}].contestPK"
											 data-form-name="contestPK" />
								<td>
									<form:checkbox path="selectedContest[${x.index}]" cssClass="checkbox"/>
								</td>
								<td>
									<form:hidden path="contestWrappers[${x.index}].weight"
												cssClass="weightInput" data-form-name="weight" />
												 <span>${contestWrapper.weight}</span>
								</td>
								<td >
									<collab:contestLink contestId="${contestWrapper.contestPK}" text="${contestWrapper.contestShortName}"
														/>
								</td>
								<td >${contestWrapper.activePhase.name}</td>
								<td><form:checkbox path="contestWrappers[${x.index}].contestActive" disabled="true" /></td>
								<td><form:checkbox path="contestWrappers[${x.index}].contestPrivate" disabled="true" /></td>
								<td><form:checkbox path="contestWrappers[${x.index}].featured" disabled="true" /></td>
								<td><form:checkbox path="subscribedToContest[${x.index}]" disabled="true" /></td>
								<td>${fn:length(contestWrapper.contestJudges)} </td>
								<td>${fn:length(contestWrapper.contestAdvisors)} </td>
								<td>${fn:length(contestWrapper.contestFellows)} </td>
								<td>${contestWrapper.proposalsCount} </td>
								<td>${contestWrapper.commentsCount} </td>
								<td>
									<div class="blue-button innerVerticalCenter" >
										<a href="/web/guest/cms/-/contestmanagement/contestId/${contestWrapper.contestPK}" target="_blank">EDIT</a>
									</div>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>

		</form:form>
	</div>

	<script type="text/javascript">
		<![CDATA[

		var MASS_MESSAGE_SELECT_ID = 1;
		var REPORT_SELECT_ID = 3;
		var actionURL = "${updateContestOverviewURL }";
		var resourceURL = "${getExport }";

		jQuery('document').ready(function(){
			bindMassActionChange();
			bindSelectAllClick();
			bindFormSubmits();
			bindFilterSelectChange();
			bindDragDropEvents();
		});

		function bindMassActionChange(){

			var dropDownElement = document.getElementById("selectedMassAction");
			dropDownElement.addEventListener("change", function(ev){
				var massMessageDiv = document.getElementById("massMessage");
				var editFormElement = document.getElementById("editForm");
				var selectedDropDownId = ev.target.value;

				if(selectedDropDownId == REPORT_SELECT_ID){
					editFormElement.action = resourceURL;
				} else {
					editFormElement.action = actionURL;
				}

				if(selectedDropDownId == MASS_MESSAGE_SELECT_ID){
					massMessageDiv.style.display = '';
				} else{
					massMessageDiv.style.display = 'none';
				}
			})
		}

		function bindSelectAllClick(){
			var selectAllCheckboxElement = document.getElementById("selectAllCheckbox");
			selectAllCheckboxElement.addEventListener("change", function (ev) {
				var selectAllChecked = selectAllCheckboxElement.checked;
				var contestsTableBody = document.getElementsByTagName('tbody')[0];
				[].forEach.call(contestsTableBody.getElementsByClassName("checkbox"), function (element) {
					var parentRow = getClosest(element, "tr");
					if( parentRow.getAttribute("data-filter-visible")  === 'true'){
						if (selectAllChecked) {
							element.checked = true;
						} else {
							element.checked = false;
						}
					}
				});
			});
		}

		function bindFormSubmits(){
			var editFormElement = document.getElementById('editForm');
			var submitButtonElement = document.getElementById("submitButton");
			submitButtonElement.addEventListener("click", function(){
				editFormElement.submit();
			})
		}

		function bindFilterSelectChange(){
			var contestsFilterSelectElement = document.getElementById("contestsFilterSelect");
			contestsFilterSelectElement.addEventListener("change", function(){
				var selectedFilterAttribute = contestsFilterSelectElement.value;
				showContestsWithDataAttributeFilter(selectedFilterAttribute);
			})
		}

		function bindDragDropEvents(){

			[].forEach.call(document.getElementsByTagName("tr"), function (rowElement) {
				console.log("rowElement", rowElement);
				rowElement.addEventListener("dragend", dragEnd);
				rowElement.addEventListener("dragstart", dragStart);
				rowElement.addEventListener("drop", drop);
				rowElement.addEventListener("dragenter", dragEnter);
				rowElement.addEventListener("dragover", dragOver);
				rowElement.addEventListener("dragleave", dragLeave);
			} )

		}

		function sendGetReportRequestToServer(){
			var editFormElement = document.getElementById('editForm');
			jQuery.ajax({
				type: 'POST',
				url: resourceURL,
				data: jQuery(editFormElement).serialize(),
				success: function(response){
				},
				error: function(xhr, status, error){
				}
			});
		}

		function showContestsWithDataAttributeFilter(dataFilterAttribute){
			var contestsTableBody = document.getElementsByTagName('tbody')[0];
			[].forEach.call(contestsTableBody.getElementsByTagName('tr'), function (element) {
				var elementDataFilterAttribute =  element.getAttribute("data-filter-attribute");
				if(dataFilterAttribute === "all" || elementDataFilterAttribute === dataFilterAttribute ){
					element.style.display = "";
					element.setAttribute("data-filter-visible", "true");
				} else {
					element.style.display = "none";
					element.setAttribute("data-filter-visible", "false");
				}
			});
		}

		function dragEnd(event) {
			event.target.classList.remove("drag");
			[].forEach.call(document.getElementsByTagName('tr'), function (element) {
				element.classList.remove("allowDrop");
			});
		}

		function dragLeave(event){
			var targetRow = getClosest(event.target, "tr")
			targetRow.classList.remove("allowDrop");
		}

		function dragOver(event) {
			event.preventDefault();
			return false;
		}

		function dragEnter(event) {
			var targetRow = getClosest(event.target, "tr")
			targetRow.classList.add("allowDrop");
		}

		function dragStart(event) {
			console.log("start drag", event);
			event.target.style.visibility = "none";
			event.dataTransfer.setData("srcElementId", event.target.id);
			var srcElementId = event.dataTransfer.getData("srcElementId");
			event.target.classList.add("drag");
		}

		function drop(event) {

			var srcElementId = event.dataTransfer.getData("srcElementId");
			var srcElement = document.getElementById(srcElementId);
			var targetRow = getClosest(event.target, "tr")
			var targetParentElement = targetRow.parentNode;
			targetParentElement.insertBefore(srcElement, targetRow)

			reCalculateWeights();
			event.stopPropagation(); // Stops some browsers from redirecting.
			event.preventDefault();
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

		//}());
		]]>
	</script>

</jsp:root>