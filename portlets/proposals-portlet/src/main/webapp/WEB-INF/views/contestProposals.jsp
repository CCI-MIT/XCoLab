<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx"/>
    <div id="content">
        <jsp:directive.include file="./contestProposals/header.jspx"/>
        <jsp:directive.include file="./contestProposals/header_contest_details.jspx"/>

        <div class="headline subhead">
            <h2>
                <span>${fn:length(proposals.proposals)}</span> proposals
            </h2>
            <!--  TODO should check if user can see createProposalButton -->
            <div class="right">
                <c:if test="${proposalsPermissions.canCreate }">
                    <div class="blue-button">
                        <a href="/web/guest/plans/-/plans/contestId/${contest.contestPK }/createProposal"
                           onclick="if(!deferUntilLogin()) return false;">
                            <span>CREATE</span> proposal
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="blueheaderbar tooltips">
            <div class="proposalname" style="${proposalsPermissions.canJudgeActions ? 'width:554px;' : ''}">
                <div style="display: inline-block">
                    <portlet:renderURL var="sortURL">
                        <portlet:param name="contestId" value="${contest.contestPK }" />
        					<portlet:param name="pageToDisplay" value="contestProposals" />
        					<portlet:param name="sortColumn" value="NAME" />
        					<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        					<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'NAME' ? not sortFilterPage.sortAscending : true }" />
        				</portlet:renderURL>
						<a href="${sortURL }">Proposal name</a>
						<div class="tooltip">
							click to sort by name
							<div class="tt-arrow"><!-- --></div>
						</div>
						<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="NAME" />
					</div>
					/&#160;

					<div style="display: inline-block">
						<portlet:renderURL var="sortURL">
        					<portlet:param name="contestId" value="${contest.contestPK }" />
        					<portlet:param name="pageToDisplay" value="contestProposals" />
        					<portlet:param name="sortColumn" value="AUTHOR" />
        					<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        					<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'AUTHOR' ? not sortFilterPage.sortAscending : true }" />
        				</portlet:renderURL>
						<a href="${sortURL }">Author(s)</a>

						<div class="tooltip">
							click to sort by author
							<div class="tt-arrow"><!-- --></div>
						</div>
						<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="AUTHOR" />
					</div>
				</div>
				
        		<c:choose>
        			<c:when test="${contestPhase.canVote }">
						<div class="votes">
							<portlet:renderURL var="sortURL">
        						<portlet:param name="contestId" value="${contest.contestPK }" />
        						<portlet:param name="pageToDisplay" value="contestProposals" />
        						<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        						<portlet:param name="sortColumn" value="VOTES" />
        						<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'VOTES' ? not sortFilterPage.sortAscending : true }" />
        					</portlet:renderURL>
							<a href="${sortURL }"><!--  --></a>
							<div class="tooltip">
								click to sort by<br />number of votes
								<div class="tt-arrow"><!-- --></div>
							</div>
							<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="VOTES" />
						</div>
        			</c:when>
        			<c:otherwise>
						<div class="thumbs">
							<portlet:renderURL var="sortURL">
        						<portlet:param name="contestId" value="${contest.contestPK }" />
        						<portlet:param name="pageToDisplay" value="contestProposals" />
        						<portlet:param name="sortColumn" value="SUPPORTERS" />
        						<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        						<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'SUPPORTERS' ? not sortFilterPage.sortAscending : true }" />
        					</portlet:renderURL>
							<a href="${sortURL }"><!--  --></a>
							<div class="tooltip">
								click to sort by<br />number of supporters
								<div class="tt-arrow"><!-- --></div>
							</div>
							<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="SUPPORTERS" />
						</div>
					</c:otherwise>
				</c:choose>
				
				<div class="propcomm">
					<portlet:renderURL var="sortURL">
        				<portlet:param name="contestId" value="${contest.contestPK }" />
        				<portlet:param name="pageToDisplay" value="contestProposals" />
        				<portlet:param name="sortColumn" value="COMMENTS" />
        				<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        				<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'COMMENTS' ? not sortFilterPage.sortAscending : true }" />
        			</portlet:renderURL>
					<a href="${sortURL }"><!--  --></a>
					<div class="tooltip">
						click to sort by<br />number of comments
						<div class="tt-arrow"><!-- --></div>
					</div>
					<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="COMMENTS" />
				</div>
				<div class="modified">
					<portlet:renderURL var="sortURL">
        				<portlet:param name="contestId" value="${contest.contestPK }" />
        				<portlet:param name="pageToDisplay" value="contestProposals" />
        				<portlet:param name="sortColumn" value="MODIFIED" />
        				<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        				<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'MODIFIED' ? not sortFilterPage.sortAscending : true }" />
        			</portlet:renderURL>
					<a href="${sortURL }">Modified</a>
					<div class="tooltip">
						click to sort by date
						<div class="tt-arrow"><!-- --></div>
					</div>
					<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="MODIFIED" />
				</div>
				<div class="contributor">
					<portlet:renderURL var="sortURL">
        				<portlet:param name="contestId" value="${contest.contestPK }" />
        				<portlet:param name="pageToDisplay" value="contestProposals" />
        				<portlet:param name="sortColumn" value="CONTRIBUTORS" />
        				<portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
        				<portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'CONTRIBUTORS' ? not sortFilterPage.sortAscending : true }" />
        			</portlet:renderURL>
					<a href="${sortURL }">Contributors</a>
					<div class="tooltip">
						click to sort by contributor
						<div class="tt-arrow"><!-- --></div>
					</div>
					<collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="CONTRIBUTORS" />
				</div>

        <c:if test="${proposalsPermissions.canJudgeActions}">
            <div class="judgefellow">
                <a href="#">Judge</a><c:if test="${proposalsPermissions.canFellowActions}"><a href="#" style="padding-left: 5px;">Fellow</a></c:if>
                <div class="tooltip">
                    Judge or fellow actions
                    <div class="tt-arrow"><!-- --></div>
                </div>
            </div>
        </c:if>

			</div>
			<proposalsPortlet:proposalsList proposals="${proposals.proposalsWithRibbons }" showShadebar="true"/>
			<proposalsPortlet:proposalsList proposals="${proposals.proposalsNormal }" showShadebar="${fn:length(proposals.proposalsWithRibbons) > 0 }"/>
	</div>
</jsp:root>