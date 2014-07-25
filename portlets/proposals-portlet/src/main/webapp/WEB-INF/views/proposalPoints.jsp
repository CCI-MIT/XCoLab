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
            <portlet:param name="action_forwardToPage" value="proposalDetails"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId}"/>
            <portlet:param name="action" value="savePointAssignments"/>
        </portlet:actionURL>
        <form:form id="assignPointsForm" action="${savePointsAssignmentsURL}" method="post" commandName="assignPointsBean">
            <jsp:include page="./proposalDetails/proposalPointTypes.jspx"/>

            <div class="clearfix" style="width: 100%;">
                <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                    <a href="javascript:;" onclick="jQuery(this).parents('form').submit();">Save</a>
                </div>
            </div>
        </form:form>
	</div>
</jsp:root>