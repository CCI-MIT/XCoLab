<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
          xmlns:discussions="http://climatecolab.org/tags/xcollab_discussions_1.0"
          xmlns:discussionsTagFiles="urn:jsptagdir:/WEB-INF/tags/discussions"
          xmlns:addthis="http://www.addthis.com/help/api-spec"
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0"
          xmlns:liferay-ui="http://liferay.com/tld/ui">
    <jsp:directive.include file="./init.jspx"/>

    <jsp:directive.include file="./proposalDetails/header.jspx"/>

    <style>h3 {
        margin: 20px 0 10px 0 !important;
    }</style>

    <portlet:actionURL var="saveAdvanceDetailsURL">
        <portlet:param name="action_forwardToPage" value="proposalDetails_ADVANCING"/>
        <portlet:param name="contestId" value="${contest.contestPK }"/>
        <portlet:param name="planId" value="${proposal.proposalId }"/>
        <portlet:param name="action" value="saveAdvanceDetails"/>
    </portlet:actionURL>
    <portlet:actionURL var="sendEmailURL">
        <portlet:param name="action_forwardToPage" value="proposalDetails_ADVANCING"/>
        <portlet:param name="contestId" value="${contest.contestPK }"/>
        <portlet:param name="planId" value="${proposal.proposalId }"/>
        <portlet:param name="action" value="sendComment"/>
    </portlet:actionURL>
    <portlet:resourceURL id="getJudgingCsv" var="getJudgingCsvURL">
    </portlet:resourceURL>

    <div id="content" class="${isJudgeReadOnly ? 'judgeReadOnly' : ''}">
        This page is shared by contest Fellows only.  Advisors and Judges will not be able to view this page.
        <br/>
        <div style="display: inline-block;	float:right; margin-top: 20px;">
            <liferay-ui:icon image="download" url="${resourceUrl}" /><a href="#" onClick="location.href = '${getJudgingCsvURL}'"> Judges rating as CSV</a>
        </div>
        <h1 style="margin-top:15px;">Rating</h1>


        <c:if test="${not isJudgeReadOnly}">
            <div class="judging_left">
                <c:choose>
                    <c:when test="${not proposal.allJudgesReviewFinished}">
                        Not all judges have completed the review yet or this proposal was not forwarded to any judges.
                    </c:when>
                    <c:otherwise>
                        <form:form id="fellowRatingForm" action="${saveAdvanceDetailsURL}" method="post"
                                   commandName="proposalAdvancingBean">
                            <form:hidden path="contestPhaseId" />
                            <div class="addpropbox">
                                <h3>Advance Proposal to Next Round?</h3>
                                <form:select id="advanceDecision" path="advanceDecision" items="${advanceOptions}" itemValue="attributeValue" itemLabel="description"/>

                                <div id="comment-container">
                                    <h3>Comment to send to author:</h3>
                                    <i>Your comment will be visible to the public.</i>

                                    <div class="form-errors"><!--  -->
                                        <form:errors cssClass="alert alert-error" path="advanceComment" />
                                    </div>
                                    <a name="advanceCommentAnchor" id="advanceCommentAnchor"></a>
                                    <form:textarea id="advanceComment" cssClass="commentbox" path="advanceComment" style="width:100%;"/>
                                    <div id="comment-footers">
                                        <c:forEach var="template" items="${emailTemplates}">
                                            <div class="${template.key}">
                                                    ${template.value.getFooter()}
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <c:if test="proposalsPermissions.canAdminAll">
                                        <div class="blue-button" style="display:block; float:left;">
                                            <a href="${sendEmailURL}">Send e-Mails</a>
                                        </div>
                                    </c:if>
                                </div>
                                <a href="${contest.emailTemplateUrl}" target="_blank">Go to Email Template</a>
                                <div id="advance-save-buttons">
                                    <c:choose>
                                        <c:when test="${hasNoWritePermission}">
                                            <p class="submitStatus error">
                                                <strong>You have no permission to change the advancement this proposal.</strong>
                                            </p>
                                        </c:when>
                                        <c:when test="${isFrozen}">
                                            <p class="submitStatus">
                                                <strong>The advancement is finalized and may not be changed anymore.</strong>
                                            </p>
                                            <c:if test="${isAdmin}">
                                                <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                                    <a href="javascript:;" onclick="jQuery(this).parents('form').submit();">
                                                        Save
                                                    </a>
                                                </div>
                                                <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                                    <input type="submit" id="submit-unfreeze" name="isUnfreeze" style="display:none" value="true" />
                                                    <a href="javascript:;" onclick="$('#submit-unfreeze').click();">
                                                        Unfreeze
                                                    </a>
                                                </div>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                                <a href="javascript:;" onclick="jQuery(this).parents('form').submit();">
                                                    Save
                                                </a>
                                            </div>
                                            <div id="advancement-freeze-button">
                                                <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                                    <input type="submit" id="submit-freeze" name="isFreeze" style="display:none" value="true" />
                                                    <a href="javascript:;" onclick="$('#submit-freeze').click();">
                                                        Freeze
                                                    </a>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <div id="advancement-force-button">
                                        <c:if test="${isAdmin and not hasAlreadyBeenPromoted}">
                                            <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                                <input type="submit" id="submit-forcePromotion" name="isForcePromotion" style="display:none" value="true" />
                                                <a href="javascript:;" onclick="$('#submit-forcePromotion').click();">
                                                    Execute judging decision
                                                </a>
                                            </div>
                                        </c:if>
                                    </div>
                                    <c:if test="${hasAlreadyBeenPromoted}">
                                        <p class="submitStatus error">
                                            <strong>This proposal has already been promoted to the next phase. Thus, changes have no effect.</strong>
                                        </p>
                                    </c:if>

                                </div>

                            </div>
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        <div class="judging_right">
            <div class="addpropbox">
                <h2>Evaluation criteria</h2>

                <proposalsPortlet:ratingCriteriaText ratingTypes="${proposalAdvancingBean.ratingTypes}" />
                </div>
        </div>
        <div class="judging_comments">
            <h2>Comments by Judges</h2>
            <c:choose>
                <c:when test="${judgeRatings.size() > 0}">
                    <div class="addpropbox">
                        <proposalsPortlet:proposalRatingComments showRating="true" proposalRatingsWrappers="${judgeRatings}" proposalId="${proposal.proposalId}" />
                    </div>
                </c:when>
                <c:otherwise>
                    <p>No comments by judges yet.</p>
                </c:otherwise>
            </c:choose>
            <h2>Comments by Fellows</h2>
            <c:choose>
                <c:when test="${fellowRatings.size() > 0}">
                <div class="addpropbox">
                    <proposalsPortlet:proposalRatingComments showRating="${isAdmin ? 'true' : 'false'}" proposalRatingsWrappers="${fellowRatings}" proposalId="${proposal.proposalId}" />
                </div>
                </c:when>
                <c:otherwise>
                    <p>No comments by fellows yet.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script>
        jQuery( document ).ready(function() {
            jQuery('#advanceDecision').change(function() {
                refreshCommentHeader();
                refreshEmailTemplates();
            });

            refreshCommentHeader();
            refreshEmailTemplates();
        });

        function refreshCommentHeader() {
            var advanceDecisionIdx = document.getElementById("advanceDecision").selectedIndex;
            if (advanceDecisionIdx > 0) {
                $("#advancement-force-button").show();
                $("#advancement-freeze-button").show();
                $('#comment-container').slideDown();
            } else {
                $("#advancement-force-button").hide();
                $("#advancement-freeze-button").hide();
                $('#comment-container').slideUp();
            }
        }

        function refreshEmailTemplates() {
            jQuery("#comment-footers > div").hide();
            jQuery("#comment-headers > div").hide();

            var action = $("#advanceDecision").val();
            var classToBeShown = "";
            if (action == "1") {
                classToBeShown = "ADVANCING_DO_NOT_ADVANCE";
            } else if (action == "2") {
                classToBeShown = "ADVANCING_ADVANCE_TO_SEMIFINALIST";
            }
            if (classToBeShown != "") {
                jQuery("#comment-headers ."+classToBeShown).add("#comment-footers ."+classToBeShown).show();
            }
        }

        function copyCommentsToTextArea(element) {
            var text = "";
            $(".comment-wrapper", $(element).closest(".comments")).each(function() {text+="\n\n"+$(this).text()});
            $("#advanceComment").val($("#advanceComment").val()+text);
        }
    </script>
    <c:if test="${hasNoWritePermission or (isFrozen and not isAdmin)}">
        <script>
            $("#fellowRatingForm select").add($("#fellowRatingForm input")).add($("#fellowRatingForm textarea")).attr("disabled", "disabled");
        </script>
    </c:if>
    <c:if test="${not proposal.allJudgesReviewFinished}">
        <script>
            $(".judging_comments .blue-button").hide();
        </script>
    </c:if>

</jsp:root>