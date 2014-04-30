<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />

<h1>Edit</h1>
<portlet:actionURL var="updatePreferencesURL" />
<form:form action="${updatePreferencesURL }" commandName="feedsPreferences">
	<input type="hidden" name="action" value="savePreferences" />
	<table>
		<tr>
			<td>feed type:</td>
			<td>
				<form:select path="feedType" items="${feedTypes }" />
				<select>
					<option value="">--- choose</option>
					<option value="ACTIVITIES">ACTIVITIES</option>
					<option value="RECENTLY_ACTIVE">RECENTLY_ACTIVE</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>feed size:</td>
			<td>
				<form:input path="feedSize" />
			</td>
		</tr>
		<tr>
			<td>feed title:</td>
			<td>
				<form:input path="feedTitle" />
			</td>
		</tr>
		<tr>
			<td>feed display style:</td>
			<td>
				<form:select path="feedStyle" items="${feedDisplayStyles }" />
			</td>
		</tr>
		<tr>
			<td>remove admin:</td>
			<td>
				<form:checkbox path="removeAdmin"  />
			</td>
		</tr>
	</table>
	<input type="submit" value="Save" />
</form:form>
</jsp:root>