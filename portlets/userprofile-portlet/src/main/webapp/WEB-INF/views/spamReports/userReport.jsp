<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:portlet="http://java.sun.com/portlet_2_0"
          version="2.0">

    <jsp:useBean id="spamReportsWrapper" type="org.xcolab.portlets.userprofile.wrappers.SpamReportsWrapper" scope="request"/>

    <h2 style="margin-top: 20px;">Spam Reports</h2>

    <a style="margin-left: 10px;" href="${spamReportsWrapper.deleteUserUrl }">Delete user</a> <strong><a style="margin-left: 10px;" href="${spamReportsWrapper.deleteUserUrl }/deleteComments">Delete user and all comments</a></strong>

    <table class="colab">
        <thead>
        <tr>
            <th>Category Group</th>
            <th>Admin reports</th>
            <th>Other reports</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <c:forEach var="spamComment" items="${spamReportsWrapper.spamComments}">
            <tr class="colabRow">
                <td><a href="${spamComment.discussionCategoryGroup.url}" target="_blank">${spamComment.discussionCategoryGroup.description}</a></td>
                <td>${spamComment.adminReportCount}</td>
                <td>${spamComment.otherReportCount}</td>
                <td><fmt:formatDate value="${spamComment.messageDate}" type="date" dateStyle="short" timeZone="America/New_York" /></td>
                <td><a style="margin-left: 10px;" href="${spamComment.deleteCommentUrl }" target="_blank">Delete comment</a></td>

            </tr>
        </c:forEach>
    </table>

</jsp:root>