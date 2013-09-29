<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

<jsp:directive.include file="./proposalDetails/header.jspx" />


<div id="content">
	<liferay-ui:success key="membershipRequestSent" message="Membership request sent" />
	<div class="headline prophead">
		<h2>
			<span>${fn:length(proposal.members)}</span> ${fn:length(proposal.members) == 1 ? 'member' : 'members'}
		</h2>
	</div>
	<table class="contributors">
		<c:forEach var="member" items="${proposal.members }" varStatus="status">
			<tr class="${(status.index / 2) mod 2 > 0 ? 'even' : 'odd'}">
				<td><proposalsPortlet:proposalTeamMember member="${member }" /></td>
				<td>${member.memberType }</td>
				<!-- TODO add options to remove user for admin -->
			</tr>
		</c:forEach>
	</table>
	
	
	<div class="headline propsubhead">
		<h2>
			<span>${fn:length(proposal.supporters)}</span> ${fn:length(proposal.supporters) == 1 ? 'supporter' : 'supporters' }
		</h2>
	</div>
	<c:if test="${not empty proposal.supporters }">
		<table class="contributors">
			<c:forEach var="supporter" items="${proposal.supporters }" varStatus="status" step="2">
				<tr class="${(status.index / 2) mod 2 > 0 ? 'even' : 'odd'}">
					<td><proposalsPortlet:userLinkSimple userId="${supporter.userId}" text="${supporter.screenName}" /></td>
					<td>
						<c:if test="${fn:length(proposal.supporters) > status.index}">
							<proposalsPortlet:userLinkSimple userId="${proposal.supporters[status.index+1].userId}" text="${proposal.supporters[status.index+1].screenName}" />
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	

	<portlet:actionURL var="requestMembershipURL">
		<portlet:param name="pageToDisplay" value="proposalDetails_TEAM" />
		<portlet:param name="contestId" value="${contest.contestPK }" />
		<portlet:param name="planId" value="${proposal.proposalId }" />
		<portlet:param name="action" value="requestMembership" />
	</portlet:actionURL>
	
	<form:form action="${requestMembershipURL }" method="post" commandName="requestMembershipBean">
		<div class="requestMembershipDIV">
			<div class="manageMembersTop">
				<h3>Request Membership</h3>
			</div>
			<div class="manageMembersContent">
				Optional comment:
				<p>
					<form:textarea cssClass="requestComment" path="requestComment" />
					<form:errors cssClass="alert alert-error" path="requestComment" />
				</p>
			</div>
			<div class="blue-button">
				<a href="#" class="requestMembershipSubmitButton" onclick="jQuery(this).parents('form').submit();">REQUEST membership</a>
			</div>
			<div class="blue-button">
				<a href="#">CANCEL</a>
			</div>
		</div>
	</form:form>

</div>	
</jsp:root>