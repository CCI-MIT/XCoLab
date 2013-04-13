<%@ include file="/init.jsp"%>
<%
String listName = ParamUtil.getString(renderRequest, "listName");
pageContext.setAttribute("listName", listName);
//List<String> 
%>
<c:choose>
	<c:when test="${empty listName }">
		<%
        	Set<String> listNames = new TreeSet<String>();
            	for (EmailList email: EmailListLocalServiceUtil.getEmailLists(0, Integer.MAX_VALUE)) {
                	listNames.add(email.getName());
            	}
            	
            	pageContext.setAttribute("listNames", listNames);
        
		%>
		<h1>Choose list</h1>
		<ul>
			<c:forEach var="name" items="${listNames }">
				<portlet:renderURL var="listNameUrl">
    				<portlet:param name="jspPage" value="/view_emailLists.jsp" />
    				<portlet:param name="listName" value="${name }" />
				</portlet:renderURL>
			
				<li><a href="${listNameUrl }">${name }</a></li>
			</c:forEach>
		</ul>
		 
	</c:when>
	<c:otherwise>
		<portlet:renderURL var="backUrl">
			<portlet:param name="jspPage" value="/view_emailLists.jsp" />
			<portlet:param name="listName" value="${name }" />
		</portlet:renderURL>
		<a href="${backUrl }">Back to list names</a><br />
		<h1>List name: ${listName }</h1>
<textarea style="width: 100%; height: 500px;">
<%	for (EmailList email: EmailListLocalServiceUtil.findByListName(listName)) {	%><%= email.getEmail() %>
<% } %></textarea>
	</c:otherwise>
	
</c:choose>