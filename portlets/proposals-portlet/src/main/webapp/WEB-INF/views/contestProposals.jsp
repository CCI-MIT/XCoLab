<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />
	<div id="content">
		<jsp:directive.include file="./contestProposals/header.jspx" />
		<jsp:directive.include file="./contestProposals/header_contest_details.jspx" />

		<div class="headline subhead">
			<h2>
				<span>${fn:length(proposals)}</span> proposals
			</h2>
			<c:if test="${contestPhase.status == 'OPEN_FOR_SUBMISSION' and contestPhase.active}">
				<div class="right">
					<div class="blue-button">
						<a href="#" onclick="if(!deferUntilLogin()) return false;">
							<span>CREATE</span> proposal
						</a>
					</div>
					
				</div>
			</c:if>
		</div>
	<div class="blueheaderbar tooltips">
				<div class="proposalname">
					<div style="display: inline-block">
						<a href="#">Proposal name</a>
						<div class="tooltip">
							click to sort by name
							<div class="tt-arrow"><!-- --></div>
						</div>
					</div>
					/&#160;

					<div style="display: inline-block">
						<a href="#">Author(s)</a>

						<div class="tooltip">
							click to sort by author
							<div class="tt-arrow"><!-- --></div>
						</div>
					</div>
				</div>
				<div class="thumbs">
					<a href="#"><!--  --></a>
					<div class="tooltip">
						click to sort by<br />number of supporters
						<div class="tt-arrow"><!-- --></div>
					</div>
				</div>
				<div class="propcomm">
					<a href="#"><!--  --></a>
					<div class="tooltip">
						click to sort by<br />number of comments
						<div class="tt-arrow"><!-- --></div>
					</div>
				</div>
				<div class="modified">
					<a href="#">Modified</a>
					<div class="tooltip">
						click to sort by date
						<div class="tt-arrow"><!-- --></div>
					</div>
				</div>
				<div class="contributor">
					<a href="#">Contributors</a>
					<div class="tooltip">
						click to sort by contributor
						<div class="tt-arrow"><!-- --></div>
					</div>
				</div>
			</div>
			<c:forEach var="proposal" items="${proposals }">
				<div class="propbox ${proposal.featured ? 'featured' : ''}">
					<c:if test="${proposal.ribbon != null and proposal.ribbon > 0}">
            <div class="ribbonPlaceholder">
                <span class="fieldWithTooltip">
                    <br />
                    <img src="/climatecolab-theme/images/icon-prize${proposal.ribbon}.png" />
                </span>
                <div class="tooltip">
                    ${proposal.ribbonText}
                </div>
            </div>
            </c:if>
            <div class="row1">
                <div class="title-author">
                    <h4>
                    	<c:if test="${not empty singleProposal.tags}">
                            <div style="display: inline; display: inline-block;">
                                <span class="fieldWithTooltip">${proposal.tags}:</span>
                                <div class="tooltip">
                                    ${proposal.tagsHover}
                                    <div class="tt-arrow"><!--  --></div>
                                </div>
                            </div>
                                
                        </c:if>
                        
                        <proposalsPortlet:proposalLink proposalId="${proposal.proposalId}" contestId="${contest.contestPK}" text="${proposal.name}" phaseId="${viewContestPhaseId  }" escape="true" />
                        
                        /&#160;
                        <c:choose>
                        	<c:when test="${empty proposal.team}">
                        		<proposalsPortlet:userLinkSimple userId="${proposal.author.userId}" text="${proposal.author.screenName}" />
                        	</c:when>
                        	<c:otherwise>
                            	${singleProposal.team}
                        	</c:otherwise>
                        </c:choose>
                    </h4>
                    <div class="clear" ><!--  --></div>
                </div>
                <c:choose>
                	<c:when test="${contestPhase.canVote}">
                		<div class="voteboxsmall">
    	            		<proposalsPortlet:proposalLink proposalId="${proposal.proposalId}" contestId="${contest.contestPK}" text="${proposal.votesCount}" />
    	        		</div>
    	        	</c:when>
    	        	<c:otherwise>
    	        		<div class="thumbbox">
                			<proposalsPortlet:proposalLink proposalId="${proposal.proposalId}" contestId="${contest.contestPK}" text="${proposal.supportersCount}" hashString="plans%3Dtab%3ATEAM"/>
                		</div>
                	</c:otherwise>
               	</c:choose>
                <div class="commbox">
                    <proposalsPortlet:proposalLink proposalId="${proposal.proposalId}" contestId="${contest.contestPK}" text="${proposal.commentsCount}" hashString="plans%3Dtab%3ACOMMENTS" />
                </div>
                <div class="modbox">
                    <fmt:formatDate value="${proposal.lastModifiedDate}" type="date" dateStyle="short" timeZone="America/New_York" />
                </div>
                <div class="conbox">
                	<c:choose>
                		<c:when test="${proposal.open}">
	                        anyone
	                    </c:when>
	                    <c:otherwise>
                        	team only
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row2">${proposal.pitch}</div>
        </div> <!-- /proposal -->
			</c:forEach>
	</div>
</jsp:root>