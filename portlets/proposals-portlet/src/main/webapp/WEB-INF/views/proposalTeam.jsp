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
	<div class="headline prophead">
		<h2>
			<span>${fn:length(proposal.members)}</span> members
		</h2>
		<table class="contributors">
			<c:forEach var="member" items="${proposal.members }" varStatus="status">
				<tr class="${(status.index / 2) mod 2 > 0 ? 'even' : 'odd'}">
					<td><proposalsPortlet:proposalTeamMember member="${member }" /></td>
					<td>${member.memberType }</td>
					<!-- TODO add options to remove user for admin -->
				</tr>
			</c:forEach>
			<c:forEach var="member" items="${proposal.members }" step="2" varStatus="status">
				<tr class="${(status.index / 2) mod 2 > 0 ? 'even' : 'odd'}">
					<td>
						<proposalsPortlet:proposalTeamMember member="${proposal.members[status.index] }" /></td>
					<td>
						<c:if test="${not status.last }">
							<proposalsPortlet:proposalTeamMember member="${proposal.members[status.index+1] }"/>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</div>	
	
	<h1>team tab</h1>

</jsp:root>