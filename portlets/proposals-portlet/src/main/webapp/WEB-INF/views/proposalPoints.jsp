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
        <h1 class="pointsHeadline">
            Points Distribution
            <a id="expand-help-text">?</a>
        </h1>
        <div id="help-text">
            <p>
                A winning integrated proposal includes ideas from all the people who contributed to the sub-proposals, not just those who created the integrated proposal itself.
            </p>
            <p>
                To recognize all these contributions, a winning integrated proposal receives CoLab Points that are distributed among all these people. Each community member’s profile will show the points he or she has received
            </p>
            <p style="margin-bottom: 0;">
                For more information, see <a href="/resources/-/wiki/Main/Climate+CoLab+Points">Climate CoLab Points</a>.
            </p>
        </div>
        <portlet:actionURL var="savePointsAssignmentsURL">
            <portlet:param name="action_forwardToPage" value="proposalPoints"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId}"/>
            <portlet:param name="action" value="savePointAssignments"/>
        </portlet:actionURL>
        <form:form id="assignPointsForm" action="${savePointsAssignmentsURL}" method="post" commandName="assignPointsBean">
            <jsp:include page="./proposalDetails/proposalPointTypes.jspx"/>

            <c:if test="${currentTabWrapped.canEdit}">
                <div style="width: 229px; margin:0 auto;" class="clearfix">
                    <div class="blue-button">
                        <a style="cursor:pointer;width:200px;text-align:center" id="submitPointsForm">Save</a>
                    </div>
                </div>
            </c:if>
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
            $("#expand-help-text").click(function() {
                if ($("#help-text").is(":visible")) {
                    $("#help-text").fadeOut('fast');
                } else {
                    $("#help-text").fadeIn('fast');
                }
            });
        </script>
    </div>
</jsp:root>