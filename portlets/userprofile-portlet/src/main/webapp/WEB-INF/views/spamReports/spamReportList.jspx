<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          version="2.0">

    <jsp:directive.include file="../init.jspx" />

    <jsp:useBean id="spamReportsWrapper" type="org.xcolab.portlets.userprofile.wrappers.SpamReportsWrapper" scope="request"/>

    <div class="content">
        <table class="colab">
            <thead>
            <tr>
                <th>Category Group</th>
                <th>Admin reports</th>
                <th>Other reports</th>
                <th>Date</th>
            </tr>
            </thead>
            <c:forEach var="spamComment" items="${spamReportsWrapper.spamComments}">
                <tr class="colabRow">
                    <td><a href="${spamComment.discussionCategoryGroup.url}" target="_blank">${spamComment.discussionCategoryGroup}</a></td>
                    <td>${spamComment.adminReportCount}</td>
                    <td>${spamComment.otherReportCount}</td>
                    <td><fmt:formatDate value="${spamComment.date}" type="date" dateStyle="short" timeZone="America/New_York" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>


</jsp:root>