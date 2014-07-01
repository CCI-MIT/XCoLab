<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./header.jsp" />

	<h1>Input widgets</h1>

	<portlet:actionURL var="toggleCustomInputsUrl">
		<portlet:param name="action" value="toggleCustomInputs" />
		<portlet:param name="tab" value="inputWidgets" />
		<portlet:param name="modelId" value="${model.id }" />
	</portlet:actionURL>
	<form:form commandName="updateWidgetsBean"
		action="${toggleCustomInputsUrl }">

		<c:choose>
			<c:when test="${modelPreferences.usesCustomInputs }">
				<button type="submit" class="btn btn-default">Disable
					custom inputs</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-default">Enable custom
					inputs</button>
			</c:otherwise>

		</c:choose>
	</form:form>

	<portlet:actionURL var="updateInputsUrl">
		<portlet:param name="action" value="updateInputs" />
		<portlet:param name="tab" value="inputWidgets" />
		<portlet:param name="modelId" value="${model.id }" />
	</portlet:actionURL>
	<c:choose>
		<c:when test="${not modelPreferences.usesCustomInputs }">
			<form:form commandName="updateWidgetsBean"
				action="${updateInputsUrl }">

				<table class='table table-striped'>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Widget</th>
					</tr>
					<c:forEach var="input" items="${modelDisplay.allIndividualInputs }">
						<c:if test="${input.displayItemType == 'INDIVIDUAL' }">
							<tr>
								<td>${input.name }</td>
								<td>${input.description }</td>
								<td><form:select path="widgets[${input.metaData.id }]"
										items="${availableInputWidgets}" /></td>
								<td><form:input path="orders[${input.metaData.id }]" /></td>
								<td><form:select path="groups[${input.metaData.id }]"
										items="${groupInputsById}" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</form:form>
		</c:when>
		<c:otherwise>
			<form:form commandName="updateWidgetsBean"
				action="${updateInputsUrl }">
				<form:textarea path="customInputWidgets"
					style="width: 100%; height: 50%;" rows="20" />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</form:form>
		</c:otherwise>
	</c:choose>
</jsp:root>
