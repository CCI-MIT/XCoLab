<%@ include file="init.jsp"%>
<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
  List<MessagingIgnoredRecipients> recipients = 
      MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientses(0, 
          MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientsesCount());

  List<User> users = Collections.emptyList();

  users = UserLocalServiceUtil.getUsers(0, Short.MAX_VALUE);
%>


<script type="text/javascript">
  function addDeletedRecipientId(recipientId) {
	  $("#<portlet:namespace />fm").append("<input type='hidden' style='display: none;' name='<portlet:namespace />recipientId' value='" + recipientId + "' />");
	  var form = $("#<portlet:namespace />fm");
	  var input = $("<input type='hidden' style='display: none;' name='<portlet:namespace />recipientId' value='" + recipientId + "' />");
	  input.appendTo(form);
  }
</script>





<script type="text/javascript">

    var <portlet:namespace/>users = [
        <c:forEach items="<%=users%>" var="item" varStatus="status">
          "${item.screenName}", "${item.emailAddress}"
          <c:if test="${not status.last}">, </c:if>
          
        </c:forEach>
        
    ];

    <portlet:namespace/>users.sort();


    $("document").ready(function() {
        var input = $("#<portlet:namespace/>userSelectorInput").autocomplete(<portlet:namespace/>users);//, {selectedItem: "username", searchObj: "username", startText: 'Begin typing for a list' });
        

    });
</script>


<portlet:actionURL windowState="MAXIMIZED" var="submitActionURL" name="manageIgnoredRecipients">
    <portlet:param name="jspPage" value="/manageIgnoredRecipients.jsp" />
</portlet:actionURL>

<h1>"do not call list" management</h1>
<form method="post" action="${submitActionURL}" id="<portlet:namespace />fm" name="<portlet:namespace />fm">
  <input name="redirect" value="<%= currentURL %>" type="hidden" />
  <table>
    <tr>
      <td>
        <label for="name">Screen name or email that should be ignored when sending messages with mass messaging tool</label>
        <input name="name" type="text" size="60" id="<portlet:namespace/>userSelectorInput" />
      </td>
      <td>
        <input type="submit" name="op" value="Add" />
      </td>
    </tr>
  </table>

  <h2>Ignored recipients</h2>
  
  <liferay-ui:search-container emptyResultsMessage="There are no messages" delta="1000">
    <liferay-ui:search-container-results 
            results="<%= MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientses(searchContainer.getStart(), searchContainer.getEnd()) %>"
            total="<%= MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientsesCount() %>"
            
            
    />
    
    <liferay-ui:search-container-row
        className="com.ext.portlet.model.MessagingIgnoredRecipients"
        keyProperty="ignoredRecipientId"
        modelVar="recipient"
    >
       <liferay-ui:search-container-column-text
            name="Name"
            property="name"
        />
        
        <liferay-ui:search-container-column-text
            name="Email"
            property="email"
        />
        
        
        <liferay-ui:search-container-column-text
            name="Action"
            value='<input type="submit" name="op" value="Delete" onclick="addDeletedRecipientId(${recipient.ignoredRecipientId})" />' 
        />
            
    </liferay-ui:search-container-row>
    
    <liferay-ui:search-iterator />

</liferay-ui:search-container>
  
  
  
  
  
  
  
  
  
  
  
</form>