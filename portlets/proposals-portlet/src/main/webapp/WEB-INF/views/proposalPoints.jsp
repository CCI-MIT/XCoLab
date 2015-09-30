<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

    <jsp:directive.include file="./init_proposal_tab.jspx" />

    <jsp:directive.include file="./proposalDetails/header.jspx" />

    <!--ProposalPointsTabController-->
    <jsp:useBean id="assignPointsBean" scope="request" type="org.xcolab.portlets.proposals.requests.AssignPointsBean"/>

    <div id="content">
        <h1 class="pointsHeadline">
            Points Distribution
            <a id="expand-help-text">?</a>
        </h1>
        <div id="help-text">
            <p>
                In 2014, the Climate CoLab launched its first contest seeking <a href="/web/guest/resources/-/wiki/Main/Integrated+proposals">integrated proposals</a> which allow members to combine proposals from other contests into a larger whole. Other Climate CoLab proposals used in these integrated proposals are called sub-proposals.
            </p>
            <p style="margin-bottom:0">
                Good integrated proposals include ideas from the people who contributed to the sub-proposals, not just those who created the integrated proposal itself. To recognize all these contributions, a winning integrated proposal receives CoLab Points that are distributed among all these people. Each proposal will show how points are divided among the project team and other contributors, and each community member’s profile will show points he or she has received For more information, see <a href="/resources/-/wiki/Main/Climate+CoLab+Points">Climate CoLab Points</a>.
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
                var $helpTextElement = $("#help-text");
                if ($helpTextElement.is(":visible")) {
                    $helpTextElement.fadeOut('fast');
                } else {
                    $helpTextElement.fadeIn('fast');
                }
            });
        </script>
    </div>
</jsp:root>