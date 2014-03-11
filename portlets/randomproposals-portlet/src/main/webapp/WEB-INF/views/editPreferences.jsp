<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />


<portlet:actionURL var="updatePreferencesURL" />
<form:form action="${updatePreferencesURL }" commandName="preferences">
	<input type="hidden" name="action" value="save" />

	<table>
		<tr>
			<td style="width:30%">
				Enter title
			</td>	
			<td style="width:70%;padding-right:10px;">
				<form:input path="title" style="width:100%;"/>
			</td>
		</tr>
		
		<tr>
			<td>		
				Enter comma separated list of flag filters (empty if none)
			</td>
			<td>
				<form:input path="flagFiltersStr" />
			</td>
		</tr>
		
		<tr>
			<td>	
				Enter feed size
			</td>
			<td>			
				<form:input path="feedSize" />
			</td>
		</tr>	
	
		<tr>
			<td>
				Choose contest phases from which to pick proposals.
			</td>
			<td style="padding-right:10px;">		
				<form:select path="selectedPhases" items="${preferences.contestPhases}" style="width:100%; "/>
			</td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />

</form:form>	

</jsp:root>