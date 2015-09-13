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

        <div class="fold">
            <c:if test="${contest.hideRibbons and contestCompleted}">
                <div class="fold-message">
                    <h3>We’re tallying the votes… winners will be announced soon!</h3>
                </div>
            </c:if>
        </div>
        <div class="headline subhead">
            <h2>
                <span>${fn:length(proposals.proposals)}</span> proposals
            </h2>
            <div class="right">
                <c:if test="${proposalsPermissions.isCreationAllowedByPhase }">
                    <div class="blue-button">
                        <portlet:renderURL var="createProposalURL">
                            <portlet:param name="contestId" value="${contest.contestPK }" />
                            <portlet:param name="pageToDisplay" value="createProposal" />
                        </portlet:renderURL>
                        <a href="${proposalsPermissions.canCreate ? createProposalURL : '#'}"
                           onclick="if(!deferUntilLoginTargeted('${createProposalURL}')) return false;">
                            <span>CREATE</span> proposal
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="blueheaderbar
                    tooltips
                    ${proposalsPermissions.canJudgeActions or proposalsPermissions.canFellowActions ? 'judgement-actions' : ''}
                    ${contest.isContestCompleted(contestPhase) ? 'completed' : ''}
                    ">
            <div class="proposalname">
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

                <c:if test="${contestPhase.canVote or (contest.isContestCompleted(contestPhase) and not contest.hideRibbons)}">
                    <div class="votes">
                        <portlet:renderURL var="sortURL">
                            <portlet:param name="contestId" value="${contest.contestPK }" />
                            <portlet:param name="pageToDisplay" value="contestProposals" />
                            <portlet:param name="phaseId" value="${contest.votingPhasePK }" />
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
                </c:if>
                <c:if test="${not contestPhase.canVote or contest.hideRibbons}">
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
				</c:if>

            <c:if test="${not contest.isContestCompleted(contestPhase) }">
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
                <c:if test="${not contest.isContestCompleted(contestPhase)}">
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
                </c:if>
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
            </c:if>
        <c:if test="${proposalsPermissions.canJudgeActions or proposalsPermissions.canFellowActions}">
            <div class="judgefellow">
                Advancing Status<br />

                <!-- Sorting by Fellows -->
                <c:if test="${proposalsPermissions.canFellowActions}">
                    <portlet:renderURL var="sortURL">
                        <portlet:param name="contestId" value="${contest.contestPK }" />
                        <portlet:param name="pageToDisplay" value="contestProposals" />
                        <portlet:param name="sortColumn" value="SCREENINGSTATUS" />
                        <portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
                        <portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'SCREENINGSTATUS' ? not sortFilterPage.sortAscending : true }" />
                    </portlet:renderURL>

                    <a href="${sortURL }" style="padding-left: 5px;">Fellows</a>
                    <collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="SCREENINGSTATUS" />
                </c:if>
                <div class="tooltip">
                    Status of the fellows' screening decision
                    <div class="tt-arrow"><!-- --></div>
                </div>

                <!-- Sorting by judges -->
                <portlet:renderURL var="sortURL">
                    <portlet:param name="contestId" value="${contest.contestPK }" />
                    <portlet:param name="pageToDisplay" value="contestProposals" />
                    <portlet:param name="sortColumn" value="JUDGESTATUS" />
                    <portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
                    <portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'JUDGESTATUS' ? not sortFilterPage.sortAscending : true }" />
                </portlet:renderURL>

                <a href="${sortURL }">
                    Judges
                </a>
                <div class="tooltip">
                    Status of the judges' ratings
                    <div class="tt-arrow"><!-- --></div>
                </div>
                <collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="JUDGESTATUS" />

                <!-- Sorting by Overall -->
                <portlet:renderURL var="sortURL">
                    <portlet:param name="contestId" value="${contest.contestPK }" />
                    <portlet:param name="pageToDisplay" value="contestProposals" />
                    <portlet:param name="sortColumn" value="OVERALLSTATUS" />
                    <portlet:param name="phaseId" value="${contestPhase.contestPhasePK }" />
                    <portlet:param name="sortAscending" value="${sortFilterPage.sortColumn == 'OVERALLSTATUS' ? not sortFilterPage.sortAscending : true }" />
                </portlet:renderURL>
                <a href="${sortURL }">
                    Overall
                </a>
                <collab:sortArrow sortAscending="${sortFilterPage.sortAscending }" sortColumn="${sortFilterPage.sortColumn }" currentColumn="OVERALLSTATUS" />
                <div class="tooltip">
                    State of the final advancing decision
                    <div class="tt-arrow"><!-- --></div>
                </div>
            </div>
        </c:if>

			</div>
			<proposalsPortlet:proposalsList proposals="${proposals.proposalsWithRibbons }" showShadebar="true"/>
			<proposalsPortlet:proposalsList proposals="${proposals.proposalsNormal }" showShadebar="${fn:length(proposals.proposalsWithRibbons) > 0 and not contest.hideRibbons}"/>
	</div>
</jsp:root>