<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./header.jsp"/>
	
	<h1>Input widgets</h1>
	<portlet:actionURL var="updateInputsUrl">
		<portlet:param name="action" value="updateOutputs"/>
		<portlet:param name="tab" value="inputWidgets" />
		<portlet:param name="modelId" value="${model.id }" />
	</portlet:actionURL>
	<form:form commandName="updateWidgetsBean" action="${updateInputsUrl }">
		
		<table class='table table-striped'>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Widget</th>
				<th>Chart type</th>
				<th>Order</th>
				<th>Name override</th>
				<th>chart oder pk</th>
			</tr>
			<c:forEach var="output" items="${allOutputs }">
				<tr >
					<td>${output.name }</td>
					<td>${output }</td>
					<td>${output.displayItemType }</td>
					<td>${output.chartType }</td>
					<td>${output.order }</td>
					<td>${output.nameOverride }</td>
					<td>${output.modelOutputChartOrderPK }</td>
					</tr>
			</c:forEach>
		</table>
	
    	<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-default">Submit</button>
    		</div>
  		</div>
	</form:form>
</jsp:root>
