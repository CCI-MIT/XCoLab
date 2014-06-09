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
<form:form action="${updatePreferencesURL }" commandName="staffMembersPreferences">
	<input type="hidden" name="action" value="savePreferences" />
	<table>
        <tr>
            <td>Portlet title:</td>
            <td>
                <form:input path="portletTitle" />
            </td>
        </tr>
		<tr>
			<td>Category:</td>
			<td>
				<form:select path="categoryId">
                    <form:option value="1">External members</form:option>
                    <form:option value="2">Climate CoLab staff</form:option>
                </form:select>
			</td>
		</tr>
		<tr>
			<td>Display photos:</td>
			<td>
				<form:checkbox path="displayPhoto" />
			</td>
		</tr>
        <tr>
            <td>Display URLs:</td>
            <td>
                <form:checkbox path="displayUrl" />
            </td>
        </tr>
        <tr>
            <td>Amount of columns (only applies if photos are displayed):</td>
            <td>
                <form:input path="columnAmount" />
            </td>
        </tr>
	</table>
	<input type="submit" value="Save" />
</form:form>
</jsp:root>