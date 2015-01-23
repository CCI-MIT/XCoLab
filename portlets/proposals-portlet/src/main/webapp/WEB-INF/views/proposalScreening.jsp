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
            <form:form id="fellowRatingForm" action="${saveScreeningURL}" method="post"
                       commandName="fellowProposalScreeningBean">
                <div class="addpropbox">
                    <h3>Advance Proposal to Judges for Review?</h3>

                    <form:hidden path="contestPhaseId" />
                    <form:select id="fellowScreeningAction" path="fellowScreeningAction" items="${judgingOptions}" itemValue="attributeValue" itemLabel="description"/>

                    <div id="fellowSelectJudgesContainer" style="display: none;">
                        <h3>Select Judge(s)</h3>
                        Select which Judge(s) will review this proposal.
                        <table class="judgingForm">
                            <tbody>
                            <tr>
                                <c:forEach var="judge" items="${contest.contestJudges}">
                                    <td>
                                        <div class="review-status-container">
                                            <proposalsPortlet:userPortrait screenName="${judge.screenName}"
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
                        <div id="comment-headers">
                            <c:forEach var="template" items="${emailTemplates}">
                                <div class="${template.key}">
                                    ${template.value.getHeader()}
                                </div>
                            </c:forEach>
                        </div>
                        <i>Your comment will be visible to the public.</i>
                        <div class="form-errors"><!--  -->
                            <form:errors cssClass="alert alert-error" path="fellowScreeningActionCommentBody" />
                        </div>
                        <form:textarea id="fellowCommentBody" cssClass="commentbox" path="fellowScreeningActionCommentBody" style="width:100%;"/>
                        <div id="comment-footers">
                            <c:forEach var="template" items="${emailTemplates}">
                                <div class="${template.key}">
                                        ${template.value.getFooter()}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${hasNoWritePermission}">
                            <p class="submitStatus error">
                                <strong>You have no permission to advance this proposal.</strong>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <div class="blue-button" style="display:block; float:right;">
                                <a href="javascript:;" class="requestMembershipSubmitButton"
                                   onclick="jQuery(this).parents('form').submit();">Save proposal decision</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${hasAlreadyBeenPromoted}">
                        <p class="submitStatus error">
                            <strong>This proposal has already been promoted to the next phase. Thus, changes have no effect.</strong>
                        </p>
                    </c:if>

                </div>
                <div class="addpropbox">
                    <form:errors path="*" cssClass="alert alert-error" />

                    <h3 style="margin-top: 0;">My Rating</h3>
                    <i>Your rating will be visible to the Judging team only.</i>
                    <c:choose>
                        <c:when test="${hasNoWritePermission}">
                            <p class="submitStatus error">
                                <strong>You have no permission to rate this proposal.</strong>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p>
                                This is individualized for each Fellow and will be used for research purposes. Your comment (but not your rating) will be seen by other Fellows and Judges.
                            </p>

                            <div style="float: left; padding-left: 30px;"><h5>Least (1)</h5></div> <div style="text-align: right; padding-right: 30px;"><h5>Most (4)</h5></div>
                            <br/>
                            <proposalsPortlet:proposalRating ratingTypes="${fellowProposalScreeningBean.ratingTypes}" ratingValuesPath="ratingValues" />

                            <h4>Rating comment:</h4>
                            <i>Your comment will be visible to other Fellows and Judges only.</i>
                            <form:textarea id="fellowRatingComment" cssClass="commentbox" path="comment" style="width:100%;"/>
                            <div class="blue-button" style="display:block; float:right;">
                                <a href="javascript:;" class="requestMembershipSubmitButton"
                                   onclick="jQuery(this).parents('form').submit();">Save</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </form:form>
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
                <proposalsPortlet:ratingCriteriaText ratingTypes="${fellowProposalScreeningBean.ratingTypes}"/>
            </div>
        </div>

        <div style="padding-top:40px; clear:both;"></div>
        <discussionsTagFiles:discussionComments discussionId="${discussionId }"/>


    </div>

    <script type="text/javascript">
        var fellowScreeningActions = {};
    </script>

    <c:forEach var="fellowScreeningActions" items="${judgingOptions}">
        <script type="text/javascript">
        fellowScreeningActions[${fellowScreeningActions.attributeValue}] = {attributeValue: ${fellowScreeningActions.attributeValue},
        description: "${fellowScreeningActions.description}", selectJudgesEnabled: ${fellowScreeningActions.selectJudgesEnabled},
        commentEnabled: ${fellowScreeningActions.commentEnabled}};
        </script>
    </c:forEach>

    <script type="text/javascript">

        jQuery( document ).ready(function() {
            jQuery('#fellowScreeningAction').change(function() {
                refreshCommentFieldVisibility();
                refreshEmailTemplates();
            });

            refreshCommentFieldVisibility();
            refreshEmailTemplates();
        });

        function refreshCommentFieldVisibility() {
            var fellowAction = $("#fellowScreeningAction").val();

            if (fellowScreeningActions[fellowAction].commentEnabled) {
                jQuery('#fellowActionCommentContainer').slideDown();
            } else {
                jQuery('#fellowActionCommentContainer').slideUp();
            }

            if (fellowScreeningActions[fellowAction].selectJudgesEnabled) {
                jQuery('#fellowSelectJudgesContainer').slideDown();
            } else {
                jQuery('#fellowSelectJudgesContainer').slideUp();
            }
        }


        function refreshEmailTemplates() {
            jQuery("#comment-footers > div").hide();
            jQuery("#comment-headers > div").hide();


            var fellowAction = $("#fellowScreeningAction").val();
            var classToBeShown = "";
            if (fellowAction == "1") {
                classToBeShown = "SCREENING_DO_NOT_ADVANCE_INCOMPLETE";
            } else if (fellowAction == "2") {
                classToBeShown = "SCREENING_DO_NOT_ADVANCE_OFF_TOPIC";
            } else if (fellowAction == "4") {
                classToBeShown = "SCREENING_DO_NOT_ADVANCE_OTHER";
            }
            if (classToBeShown != "") {
                jQuery("#comment-headers ."+classToBeShown).add("#comment-footers ."+classToBeShown).show();
            }
        }
    </script>

    <c:if test="${hasNoWritePermission}">
        <script>
            $("#fellowRatingForm select").add($("#fellowRatingForm input")).add($("#fellowRatingForm textarea")).attr("disabled", "disabled");
        </script>
    </c:if>

</jsp:root>