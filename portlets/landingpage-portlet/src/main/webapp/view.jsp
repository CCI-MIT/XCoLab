<%@ page import="com.ext.portlet.model.LandingPage" %>
<%@ page import="java.util.List" %> 

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript" src="/html/portlet/ext/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/jquery-ui-1.7.2.custom.min.js"></script>

<portlet:defineObjects />

<h1>landing page</h1>
<% 
  List<LandingPage> landingPages = (List<LandingPage>) request.getAttribute("landingPages"); 
  String localName = request.getServerName();
  int port = request.getServerPort();
  
  String serverUrl = "http://" + localName + (port != 80 ? ":" + port : "") + "/";
  pageContext.setAttribute("serverUrl", serverUrl);
  
%>

<script type="text/javascript">
function editLandingPage(id) {
	jQuery("#landingPageDisplay_" + id + ", #landingPageEdit_" + id).toggle();
	return false;
}

function removeLandingPage(id) {
	if (confirm("Are you sure you want to delete landing page with id " + id + "?")) {
		jQuery("#<portlet:namespace />messageForm" + id + "Remove").submit();
	}
}


</script>

<portlet:actionURL windowState="MAXIMIZED" var="submitActionURL">
</portlet:actionURL>

    <table style="width: 100%">
        <tr>
            <th>Id</th>
            <th>Base url</th>
            <th>Target url</th>
            <th>Link to target page</th>
            <th>Link to landing page (use this to post to twitter etc.)</th>
            <th style="width: 150px;">Actions</th>
        <c:forEach var="landingPage" items="${landingPages }">
            <tr id="landingPageDisplay_${landingPage.id }">
                <td>${landingPage.id }</td>
                <td>${landingPage.baseUrl }</td>
                <td>${landingPage.targetUrl }</td>
                <td><a href="${serverUrl }${landingPage.targetUrl }">link to target page</a></td>
                <td><a href="${serverUrl }landingpage/${landingPage.id }/${landingPage.baseUrl }">link to use on twitter etc.</a></td>
                <td><button onclick="editLandingPage(${landingPage.id})">Edit</button><button onclick="removeLandingPage(${landingPage.id})">Remove</button></td>
            </tr>
            <form action="${submitActionURL}" id='<portlet:namespace />messageForm${landingPage.id}Edit' method="POST" name="<portlet:namespace />messageForm">
                <input type="hidden" id="updateLandingPageCommand" name="<portlet:namespace />cmd" value="update" />
                <input type="hidden" id="updateLandingPageCommand" name="<portlet:namespace />landingPageId" value="${landingPage.id }" />
                <tr id="landingPageEdit_${landingPage.id }" style="display: none;">
                    <td>${landingPage.id }</td>
                    <td><input type="text" value="${landingPage.baseUrl }" name="<portlet:namespace />baseUrl" /></td>
                    <td><input type="text" value="${landingPage.targetUrl }" name="<portlet:namespace />targetUrl" /></td>
                    <td><a href="${serverUrl }${landingPage.targetUrl }">link to target page</a></td>
                    <td><a href="${serverUrl }landingpage/${landingPage.id }/${landingPage.baseUrl }">link to use on twitter etc.</a></td>
                    <td><button onclick="editLandingPage(${landingPage.id}); return false;">Cancel</button><button>Submit</button></td>
                </tr>
            </form>
            <form action="${submitActionURL}" id='<portlet:namespace />messageForm${landingPage.id}Remove' method="POST" name="<portlet:namespace />messageForm">
                <input type="hidden" id="updateLandingPageCommand" name="<portlet:namespace />cmd" value="delete" />
                <input type="hidden" id="updateLandingPageCommand" name="<portlet:namespace />landingPageId" value="${landingPage.id }" />
            </form>
        </c:forEach>

    </table>
</form>


<br />

<h2>Add landing page</h2>
<form action="${submitActionURL}" id='<portlet:namespace />messageForm' method="POST" name="<portlet:namespace />messageForm">
    <input type="hidden" name="<portlet:namespace />cmd" value="add" />
    <table>
        <tr>
            <th>Base url</th>
            <th>Target url</th>
            <th></th>
        </tr>
        <tr>
            <td><input type="text" name="<portlet:namespace />baseUrl" /></td>
            <td><input type="text" name="<portlet:namespace />targetUrl" /></td>
            <td><input type="submit" name="<portlet:namespace />submit" value="Submit" /></td>
        </tr>
    </table>
</form>