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

    <div id="content">
        This page is shared by contest Fellows only.  Advisors and Judges will not be able to view this page.
        <br/>
        <h1 style="margin-top:15px;">Rating</h1>

        <div class="judging_left">
            <c:choose>
                <c:when test="${not proposal.allJudgesReviewFinished}">
                    Not all judges have completed the review yet or this proposal was not forwarded to any judges.
                </c:when>
                <c:otherwise>
                    <form:form id="fellowRatingForm" action="${saveAdvanceDetailsURL}" method="post"
                               commandName="proposalAdvancingBean">
                        <div class="addpropbox">
                            <h3>Advance Proposal to Semi-Finalist Round?</h3>
                            <form:select id="advanceDecision" path="advanceDecision" items="${advanceOptions}" itemValue="attributeValue" itemLabel="description"/>

                            <div id="comment-container">
                                <h3>Comment to send to author</h3>
                                <i style="font-size:10pt;">The following message will be used as a template as the response message to the author. Your comment
                                    will be replacing the marked section in the text below.<br />
                                    When writing this message, please consider the comments and ratings of judges in the Fellows &amp; Judges Comments tab.
                                </i>
                                <br/>
                                <br/>
                                <div id="comment-header">
                                    <!-- -->
                                </div>
                                <div class="form-errors"><!--  -->
                                    <form:errors cssClass="alert alert-error" path="advanceComment" />
                                </div>
                                <form:textarea id="advanceComment" cssClass="commentbox" path="advanceComment" style="width:100%;"/>
                                <div id="comment-footer">
                                    <!-- -->
                                </div>
                                <c:if test="proposalsPermissions.canAdminAll">
                                    <div class="blue-button" style="display:block; float:left;">
                                        <a href="${sendEmailURL}">Send e-Mails</a>
                                    </div>
                                </c:if>
                                <c:choose>
                                    <c:when test="${hasNoWritePermission}">
                                        <p class="submitStatus error">
                                            <strong>You have no permission to advance this proposal.</strong>
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
                                        <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                            <input type="submit" id="submit-freeze" name="isFreeze" style="display:none" value="true" />
                                            <a href="javascript:;" onclick="$('#submit-freeze').click();">
                                                Freeze
                                            </a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${isAdmin and not hasAlreadyBeenPromoted}">
                                    <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                                        <input type="submit" id="submit-forcePromotion" name="isForcePromotion" style="display:none" value="true" />
                                        <a href="javascript:;" onclick="$('#submit-forcePromotion').click();">
                                            Execute judging decision
                                        </a>
                                    </div>
                                </c:if>
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
        <div class="judging_right">
            <div class="addpropbox">
                <h2>Evaluation criteria</h2>

                <h3>Feasibility of the actions proposed</h3>
                <span>Technical, economic, social, and political feasibility of the proposals.</span>

                <h3>Novelty of the proposal's ideas</h3>
                <span>Innovative thinking and originality will be valued more than encyclopedic knowledge. In addition, instead of selecting a roster of finalists that are very similar, Judges will try to select a group of proposals that represent a diverse range of approaches.</span>

                <h3>Likely impact on climate change and desirability of other impacts</h3>
                <span>For impact of mitigation actions, the amount of greenhouse gas emission reductions; for adaptation actions, the extent to which the actions counteract the effects of climate change. Desirability can be assessed across multiple dimensions, e.g. economic, social, lifestyle.</span>

                <h3>Presentation quality</h3>
                <span>Proposals that are well-presented will be favored over those that aren't. Presentation quality includes how well written a proposal is, how well it uses graphics or other visual elements, and how compelling are its artistic representations of possible future worlds (if any).</span>
            </div>
        </div>
        <div class="judging_comments">
            <h2>Comments by Judges</h2>
            <c:if test="${judgeRatings.size() > 0}">
                <div class="addpropbox">
                    <proposalsPortlet:proposalRatingComments showRating="true" proposalRatingsWrappers="${judgeRatings}" proposalId="${proposal.proposalId}" />
                </div>
            </c:if>
            <h2>Comments by Fellows</h2>
            <c:if test="${fellowRatings.size() > 0}">
                <div class="addpropbox">
                    <proposalsPortlet:proposalRatingComments showRating="${isAdmin ? 'true' : 'false'}" proposalRatingsWrappers="${fellowRatings}" proposalId="${proposal.proposalId}" />
                </div>
            </c:if>
        </div>
    </div>

    <script>
        var advanceCommentHeaders = new Array();
        var advanceCommentFooters = new Array();

        <c:forEach var="commentHeader" items="${proposalAdvancingBean.advanceCommentHeaders}">
                advanceCommentHeaders.push("${commentHeader}");
        </c:forEach>
        <c:forEach var="commentFooter" items="${proposalAdvancingBean.advanceCommentFooters}">
                advanceCommentFooters.push("${commentFooter}");
        </c:forEach>

        jQuery( document ).ready(function() {
            jQuery('#advanceDecision').change(function() {
                refreshCommentHeader();
            });

            refreshCommentHeader();
        });

        function refreshCommentHeader() {
            var advanceDecisionIdx = document.getElementById("advanceDecision").selectedIndex;
            if (advanceDecisionIdx > 0) {
                $('#comment-container').slideDown();
            } else {
                $('#comment-container').slideUp();
            }

            $('#comment-header').html(advanceCommentHeaders[advanceDecisionIdx]);
            $('#comment-footer').html(advanceCommentFooters[advanceDecisionIdx]);
        }
    </script>
    <c:if test="${hasNoWritePermission or (isFrozen and not isAdmin)}">
        <script>
            $("#fellowRatingForm select").add($("#fellowRatingForm input")).add($("#fellowRatingForm textarea")).attr("disabled", "disabled");
        </script>
    </c:if>

</jsp:root>