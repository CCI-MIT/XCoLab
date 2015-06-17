<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

    <style type="text/css">
        .admin_left {
            width: 400px;
            float: left;
        }
        .admin_right {
            float: left;
        }
    </style>

	<jsp:directive.include file="./proposalDetails/header.jspx" />
    <div id="content">
        <div class="admin_left">
        <c:set var="addBlueClass" value='false' />
        
		<c:if test="${proposalsPermissions.canAdmin }">
        	<div class="addpropbox ${addBlueClass ? 'blue' : '' }">
            	<strong>This proposal can be edited by:</strong>
            	<div>            		
            		<portlet:actionURL var="deleteProposalURL">
                    	<portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
	                    <portlet:param name="contestId" value="${contest.contestPK }" />
    	                <portlet:param name="planId" value="${proposal.proposalId }" />
        	            <portlet:param name="action" value="toggleProposalOpen" />
            		</portlet:actionURL >

            		<form action="${deleteProposalURL }" method="post">
            			<![CDATA[
            				<input type='radio' value='false' name='planOpen' ${not proposal.open ? "checked='checked'" : ''} />Team members only<br />
            				<input type='radio' value='true' name='planOpen' ${proposal.open ? "checked='checked'" : ''} />Anyone<br />
            			]]>
            		
            			<div class="blue-button">
                			<a href="javascript:;" onclick="jQuery(this).parents('form').submit();">
                    		    Save
                    		</a>
                    	</div>
            		</form>
            	</div>
        	</div>
        	<c:set var="addBlueClass" value='${not addBlueClass }' />
        </c:if>
        
        <c:if test="${proposalsPermissions.canAssignRibbon }">
        	<div class="addpropbox ${addBlueClass ? 'blue' : '' }">
            	<strong>Proposal ribbon in contest phase</strong>
            	<div><!--  -->
            		<portlet:actionURL var="assignRibbonURL">
                    	<portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
	                    <portlet:param name="contestId" value="${contest.contestPK }" />
    	                <portlet:param name="planId" value="${proposal.proposalId }" />
        	            <portlet:param name="action" value="assignRibbon" />
            		</portlet:actionURL >
            		<form action="${assignRibbonURL }" method="post">
            			<select name="ribbon">
            				<option value="-1">no ribbon</option>
            				<c:forEach var="ribbon" items="${availableRibbons }">
            					<c:choose>
            						<c:when test="${proposal.ribbonId == ribbon.id }">
            							<option value="${ribbon.id }" selected="selected">${ribbon.ribbon} - ${fn:substring(ribbon.hoverText, 0, 50)}</option>
            						</c:when>
            						<c:otherwise>
            							<option value="${ribbon.id }">${ribbon.ribbon} - ${fn:substring(ribbon.hoverText, 0, 50)}</option>
            						</c:otherwise>
            					</c:choose>	
            				</c:forEach>
            			</select>
                        <div class="blue-button" style="display:block;">
                            <a href="javascript:;" onclick="$(this).parents('form:first').submit();">
                                Save
                            </a>
                        </div>
            		</form>
            	</div>
        	</div>
        	<c:set var="addBlueClass" value='${not addBlueClass }' />
        </c:if>
        
        <c:if test="${proposalsPermissions.canDelete and contestPhase.getProposalVisibility(proposal.proposalId) }">
        	<div class="addpropbox ${addBlueClass ? 'blue' : '' }">
            	<strong>Owner actions</strong>
            	<div>            		
            		<portlet:actionURL var="deleteProposalURL">
                    	<portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
	                    <portlet:param name="contestId" value="${contest.contestPK }" />
    	                <portlet:param name="planId" value="${proposal.proposalId }" />
        	            <portlet:param name="action" value="deleteProposal" />
        	            <portlet:param name="delete" value="true" />
            		</portlet:actionURL >
            		<form action="${deleteProposalURL }" method="post" id="deleteProposalForm">
            			<div class="blue-button">
                			<a href="javascript:;" onclick="if(!confirm('Are you sure you want to proceed with removal?')){ return false; } jQuery('#deleteProposalForm').submit();">
                    		    DELETE proposal
                    		</a>
                    	</div>
            		</form>
            	</div>
        	</div>
        	<c:set var="addBlueClass" value='${not addBlueClass }' />
        	
        </c:if>


		<c:if test="${ proposalsPermissions.getCanPromoteProposalToNextPhase() }">

			<portlet:actionURL var="promoteProposalURL">
				<portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
				<portlet:param name="contestId" value="${contest.contestPK }" />
				<portlet:param name="planId" value="${proposal.proposalId }" />
				<portlet:param name="contestPhaseId" value="${proposal.contestPhase.contestPhasePK}" />
				<portlet:param name="action" value="promoteProposal" />
			</portlet:actionURL >

			<form action="${promoteProposalURL }" method="post">
				<div class="addpropbox ${addBlueClass ? 'blue' : '' }">
					<strong>Promote proposal to latest contest phase</strong>
					<div>
						<div class="blue-button">
							<a href="javascript:;" onclick="jQuery(this).parents('form').submit();">
								<span>Promote</span> proposal
							</a>
						</div>
					</div>
				</div>
			</form>
		</c:if>

        <c:if test="${ proposalsPermissions.getCanMoveProposalAndHideInCurrentContest() }">
        	<div class="addpropbox ${addBlueClass ? 'blue' : '' }">
            	<strong>Move proposal to different contest</strong>
            	<div>            		
					<div class="blue-button">
						<a href="javascript:;"
							onclick="if(!deferUntilLogin()) return false; showCopyProposalPopup(true,true)">
							<span>Move</span> this proposal
						</a>
					</div>
				</div>
			</div>
		</c:if>

            <c:if test="${ proposalsPermissions.getCanMoveProposalAndKeepInCurrentContest() }">
                <div class="addpropbox ${addBlueClass ? 'blue' : '' }">
                    <strong>Copy proposal to different contest</strong>
                    <div>
                        <div class="blue-button">
                            <a href="javascript:;"
                               onclick="if(!deferUntilLogin()) return false; showCopyProposalPopup(true,false)">
                                <span>Copy</span> this proposal
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>
        
        
        </div>
        <div class="admin_right">
            <div class="addpropbox">
                <strong>Membership requests</strong>

                <c:if test="${fn:length(proposal.membershipRequests) lt 1}">
                    <div><i>No new requests</i></div>
                </c:if>
                <portlet:actionURL var="replyToMembershipRequest">
                    <portlet:param name="action_forwardToPage" value="proposalDetails_ADMIN" />
                    <portlet:param name="contestId" value="${contest.contestPK }" />
                    <portlet:param name="planId" value="${proposal.proposalId }" />
                    <portlet:param name="action" value="replyToMembershipRequest" />
                </portlet:actionURL>
                <c:set var="count" value="1" scope="page" />
                    <c:forEach items="${proposal.membershipRequests}" var="request">
                        <form action="${replyToMembershipRequest }" method="post" style="padding: 10px 0; ${count lt fn:length(proposal.membershipRequests) ? 'border-bottom: 1px solid gray;' :''}">
                            <proposalsPortlet:userLinkSimple userId="${request.requestUser.userId}" text="${request.requestUser.firstName} ${request.requestUser.lastName} (${request.requestUser.screenName})" />
                            <div>${request.membershipRequest.comments }</div>
                            <input type="hidden" name="requestId" value="${request.membershipRequest.primaryKey}" />
                            <div style="margin-top: 10px;">
                                <textarea name="comment" class="membership-request-comment-admin" onfocus="clearContents(this);">Optional response</textarea>
                                <div class="allow-deny-membership-box">
                                    <div>
                                        <input type="radio" name="approve" value="approve" id="radio-approve"/>
                                        <label for="radio-approve" style="margin-left: 10px;">Approve</label>
                                    </div>
                                    <div>
                                        <input type="radio" name="approve" value="deny" id="radio-deny" />
                                        <label for="radio-deny" style="margin-left: 10px;">Deny</label>
                                    </div>
                                </div>
                                <a class="sendButton" href="javascript:;" onclick="jQuery(this).parents('form').submit();">Send</a>
                            </div>
                        </form>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>

                <div id="manage_membership_request_dialog"><!--  --></div>
            </div>
            
        	<div class="addpropbox blue">
		        <strong>History</strong>
            	<div id="versions" class="versionsContainer hidden">
                	<div class="versions">
                    	<div class="historyTable">
                        	<table>
                            	<tbody class="ui-datatable-data ui-widget-content">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <script>
            		jQuery(function() {
            			triggerHistoryVisibility();
            		});
            	</script>
            </div>
        </div>
    </div>

   	<div id="copyProposalContainer" style="display: none;">
    	<div class="popup-wrap p1" id="copyProposalPopup">
			<div class="popup">
				<div class="closepopuplogin">
            		<a href="javascript:;" onclick="jQuery('#copyProposalContainer').hide()">
                		<img src="/climatecolab-theme/images/help_close.png" width="20" height="20" alt="X"/>
            		</a>
        		</div>
			
				<h4>Please choose contest to which you'd like to copy this proposal</h4>
				<div class="lrContentPlaceholder lfr-column " id="copyProposalPopupContent">
					<div id="copyProposalContests"><!--  --></div>
					<center>
						<div class="blue-button"><a href="javascript:;" onclick="$('#copyProposalContainer').hide();">Cancel</a></div>
					</center>
				</div>
			</div>
		</div>
	</div>
    <script>
		currentProposal = {
				proposalId: ${proposal.proposalId},
				version: ${proposal.version},
                contestPhaseId :  ${proposal.contestPhase.contestPhasePK}
		}
	</script>


</jsp:root>