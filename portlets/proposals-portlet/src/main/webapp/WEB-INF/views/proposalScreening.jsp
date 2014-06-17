<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:judging="http://climatecolab.org/tags/xcollab_judging_1.0"
          xmlns:discussionsTagFiles="urn:jsptagdir:/WEB-INF/tags/discussions"
          xmlns:addthis="http://www.addthis.com/help/api-spec"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx"/>

    <jsp:directive.include file="./proposalDetails/header.jspx"/>

    <style>h3 {
        margin: 20px 0 10px 0 !important;
    }</style>

    <div id="content">
        <form:errors cssClass="alert alert-error" />

        This page is shared by contest Fellows only. Advisors and Judges will not be able to view this page
        <portlet:actionURL var="saveScreeningURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_SCREENING"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId }"/>
            <portlet:param name="action" value="saveScreening"/>
        </portlet:actionURL>

        <portlet:actionURL var="sendEmailURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_SCREENING"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId }"/>
            <portlet:param name="action" value="sendComment"/>
        </portlet:actionURL>

        <div class="judging_left">
            <div class="addpropbox">
                <form:form id="fellowRatingForm" action="${saveScreeningURL }" method="post"
                           commandName="fellowProposalScreeningBean">
                    <form:errors path="*" cssClass="alert alert-error" />

                    <h3 style="margin-top: 0;">Rating</h3>
                    Rate the proposal based on the four criteria to the right.
                    <proposalsPortlet:proposalRating path="fellowScreeningRating"/>

                    <h4>Rating comment:</h4>
                    <form:textarea id="fellowRatingComment" cssClass="commentbox" path="fellowScreeningRatingComment" style="width:100%;"/>

                    <h3>Advance Proposal</h3>


                    <form:select id="fellowScreeningAction" path="fellowScreeningAction" items="${judgingOptions}" itemValue="attributeValue" itemLabel="description"/>

                    <div id="fellowSelectJudgesContainer" style="display: none;">
                        <h3>Select Judge(s)</h3>
                        Select which Judge(s) will review this proposal.
                        <table class="judgingForm">
                            <tbody>
                            <tr>
                                <c:forEach var="judge" items="${contest.contestJudges }">
                                    <td>
                                        <div class="review-status-container">
                                            <proposalsPortlet:userPortrait screenName="${judge.screenName }"
                                                                           portraitId="${judge.portraitId}" width="30"
                                                                           height="30"/>
                                            <judging:judgeReviewStatus userId="${judge.userId}" contestPhaseId="${contestPhase.contestPhasePK}"
                                                                       proposalId="${proposal.proposalId}">
                                                <c:choose>
                                                    <c:when test="${judgeReviewStatus.statusValue eq 1}">
                                                        <img src="/climatecolab-theme/images/icon_question-x.png" class="review-status-icon"/>
                                                    </c:when>
                                                    <c:when test="${judgeReviewStatus.statusValue eq 2}">
                                                        <img src="/climatecolab-theme/images/icon_question-chk.png" class="review-status-icon"/>
                                                    </c:when>
                                                </c:choose>
                                            </judging:judgeReviewStatus>
                                        </div>
                                        <br/>
                                        <judging:judgeAssignedProposalCount userId="${judge.userId}" contestPhaseId="${contestPhase.contestPhasePK}">
                                            <proposalsPortlet:userLinkSimple userId="${judge.userId}"
                                                                             text="${judge.fullName} (${proposalCount})"/>

                                        </judging:judgeAssignedProposalCount>
                                        <br/>
                                        <form:checkbox path="selectedJudges" value="${judge.userId}"/>
                                    </td>
                                </c:forEach>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="fellowActionCommentContainer" style="display: none">
                        <h3>Comment to send to author</h3>
                        <i style="font-size:10pt;">The message below will be used as a template as the response message to the author.</i>
                        <br/>
                        <br/>
                        <div id="comment-header">
                            <!-- -->
                        </div>
                        <div class="form-errors"><!--  -->
                            <form:errors cssClass="alert alert-error" path="fellowScreeningActionCommentBody" />
                        </div>
                        <form:textarea id="fellowCommentBody" cssClass="commentbox" path="fellowScreeningActionCommentBody" style="width:100%;"/>
                        <div id="comment-footer">
                            <!-- -->
                        </div>
                    </div>
                            <div class="blue-button" style="display:block; float:right;">
                                <a href="javascript:;" class="requestMembershipSubmitButton"
                                   onclick="jQuery(this).parents('form').submit();">Save</a>
                            </div>

                </form:form>
            </div>
            <c:if test="proposalsPermissions.canAdminAll">
                <div class="addpropbox">
                    <div class="blue-button" style="display:block; float:right;">
                        <a class="requestMembershipSubmitButton" href="${sendEmailURL}">Send e-Mails</a>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="judging_right">
            <div class="addpropbox">

                <h2>Evaluation Criteria</h2>

                <h3>Feasibility of the actions proposed</h3>
                <span>Technical, economic, social, and political feasibility of the proposals.</span>

                <h3>Novelty of the proposal's ideas</h3>
                <span>Innovative thinking and originality is to be valued more than strict encyclopedic knowledge.  Judges may consider selecting a roster of finalists that represent a diverse range of approaches rather than proposals that are very similar.</span>

                <h3>Likely impact on climate change and desirability of other impacts</h3>
                <span>The extent to which the actions will fulfill on the stated mission.  For example, for mitigation actions, the impact should be based on the amount of greenhouse gas emission reductions; for adaptation actions, the extent to which the actions counteract the effects of climate change; etc.  Some proposals mention other impacts outside of the initial intention, and the potential of these should be taken into consideration.</span>

                <h3>Presentation quality</h3>
                <span>Proposals that are well-presented will be favored over those that aren't. Presentation quality includes how well written a proposal is, how well it uses graphics or other visual elements, and how compelling are its artistic representations of possible future worlds (if any).</span>
            </div>
        </div>

        <div style="padding-top:40px; clear:both;"></div>
        <discussionsTagFiles:discussionComments discussionId="${discussionId }"/>


    </div>

    <script type="text/javascript">
        var fellowScreeningActions = {};
        var screeningCommentHeaders = new Array();
        var screeningCommentFooters = new Array();

        <c:forEach var="fellowScreeningActions" items="${judgingOptions}">
                fellowScreeningActions[${fellowScreeningActions.attributeValue}] = {attributeValue: ${fellowScreeningActions.attributeValue},
            description: "${fellowScreeningActions.description}", selectJudgesEnabled: ${fellowScreeningActions.selectJudgesEnabled},
            commentEnabled: ${fellowScreeningActions.commentEnabled}};
        </c:forEach>
        <c:forEach var="commentHeader" items="${fellowProposalScreeningBean.fellowCommentHeaders}">
                screeningCommentHeaders.push("${commentHeader}");
        </c:forEach>
        <c:forEach var="commentFooter" items="${fellowProposalScreeningBean.fellowCommentFooters}">
                screeningCommentFooters.push("${commentFooter}");
        </c:forEach>

        jQuery( document ).ready(function() {
            jQuery('#fellowScreeningAction').change(function() {
                refreshCommentFieldVisibility();
            });

            refreshCommentFieldVisibility();
        });

        function refreshCommentFieldVisibility() {
            var fellowActionSelectIdx = document.getElementById("fellowScreeningAction").selectedIndex;
            if (fellowScreeningActions[fellowActionSelectIdx].commentEnabled) {
                jQuery('#fellowActionCommentContainer').slideDown();
            } else {
                jQuery('#fellowActionCommentContainer').slideUp();
            }

            if (fellowScreeningActions[fellowActionSelectIdx].selectJudgesEnabled) {
                jQuery('#fellowSelectJudgesContainer').slideDown();
            } else {
                jQuery('#fellowSelectJudgesContainer').slideUp();
            }

            $('#comment-header').html(screeningCommentHeaders[fellowActionSelectIdx]);
            $('#comment-footer').html(screeningCommentFooters[fellowActionSelectIdx]);
        }

    </script>

</jsp:root>