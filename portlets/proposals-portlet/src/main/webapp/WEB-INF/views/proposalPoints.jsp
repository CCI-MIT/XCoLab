<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx" />

    <jsp:directive.include file="./proposalDetails/header.jspx" />
    <div id="content">
        <h1>Points Distribution</h1>
        <portlet:actionURL var="savePointsAssignmentsURL">
            <portlet:param name="action_forwardToPage" value="proposalPoints"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId}"/>
            <portlet:param name="action" value="savePointAssignments"/>
        </portlet:actionURL>
        <form:form id="assignPointsForm" action="${savePointsAssignmentsURL}" method="post" commandName="assignPointsBean">
            <jsp:include page="./proposalDetails/proposalPointTypes.jspx"/>

            <div style="width: 229px; margin:0 auto;" class="clearfix">
                <div class="blue-button">
                    <a style="cursor:pointer;width:200px;text-align:center" id="submitPointsForm">Save</a>
                </div>
            </div>
        </form:form>
        <script type="text/javascript">
            var usersMap = {<c:forEach var="user" items="${assignPointsBean.usersNotInTeam}">'<c:out value="${user.screenName}"/>':'<c:out value="${user.userId}"/>',</c:forEach>};
            var userNamesMap = {<c:forEach var="user" items="${assignPointsBean.usersNotInTeam}">'<c:out value="${user.userId}"/>':'<c:out value="${user.fullName}"/>',</c:forEach>};
            jQuery.extend(userNamesMap, {<c:forEach var="user" items="${members}">'<c:out value="${user.userId}"/>':'<c:out value="${user.fullName}"/>',</c:forEach>});
            jQuery(function() {
                jQuery(".replaceUserId").each(function() {
                    jQuery(this).html(userNamesMap[jQuery.trim(jQuery(this).html())]);
                });
                initUserAssignmentInputs();
            });
        </script>
    </div>
</jsp:root>