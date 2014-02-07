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
          xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
    <jsp:directive.include file="./init.jspx"/>

    <jsp:directive.include file="./proposalDetails/header.jspx"/>

    <style>h3 {
        margin: 20px 0 10px 0 !important;
    }</style>

    <div id="content">

        This page is shared by contest Fellows only. Advisors and Judges will not be able to view this page
        <portlet:actionURL var="saveFellowRatingURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_FELLOW"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId }"/>
            <portlet:param name="action" value="saveFellowRating"/>
        </portlet:actionURL>

        <portlet:actionURL var="sendEmailURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_FELLOW"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId }"/>
            <portlet:param name="action" value="sendComment"/>
        </portlet:actionURL>

        <div class="judging_left">
            <div class="addpropbox">
                <form:form id="fellowRatingForm" action="${saveFellowRatingURL }" method="post"
                           commandName="judgeProposalBean">

                    <h3 style="margin-top: 0;">Rating</h3>
                    Rate the proposal based on the four criteria to the right.
                    <table class="judgingForm">
                        <tbody>
                        <tr>
                            <td>Poor</td>
                            <td>Fair</td>
                            <td>Good</td>
                            <td>Very good</td>
                            <td>Outstanding</td>
                        </tr>
                        <tr>
                            <td><form:radiobutton path="fellowRating" value="1"/></td>
                            <td><form:radiobutton path="fellowRating" value="2"/></td>
                            <td><form:radiobutton path="fellowRating" value="3"/></td>
                            <td><form:radiobutton path="fellowRating" value="4"/></td>
                            <td><form:radiobutton path="fellowRating" value="5"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <h3>Advance Proposal</h3>


                    <form:select path="fellowAction" items="${judgingOptions}" itemLabel="description"/>


                    <h3>Select Judge(s)</h3>
                    Select which Judge(s) will review this proposal.
                    <table class="judgingForm">
                        <tbody>
                        <tr>
                            <c:forEach var="judge" items="${contest.contestJudges }">
                                <td>
                                    <proposalsPortlet:userPortrait screenName="${judge.screenName }"
                                                                   portraitId="${judge.portraitId}" width="30"
                                                                   height="30"/><br/>
                                    <proposalsPortlet:userLinkSimple userId="${judge.userId}"
                                                                     text="${judge.fullName} (${judge.comments})"/><br/>
                                    <form:checkbox path="selectedJudges" value="${judge.userId}"/>
                                </td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>

                    <c:if test="${judgeProposalBean.fellowAction.attributeValue ne 3}">
                    <h3>Comment to send to author</h3>
                    <form:textarea id="fellowComment" cssClass="commentbox" path="fellowComment" style="width:100%;"/>
                    </c:if>

                    <c:choose>
                        <c:when test="${!judgeProposalBean.judgingStatus}">
                            <div class="blue-button" style="display:block; float:right;">
                                <a href="javascript:;" class="requestMembershipSubmitButton"
                                   onclick="jQuery(this).parents('form').submit();">Save</a>
                            </div>
                        </c:when>
                        <c:otherwise>(No more changes possible)</c:otherwise>
                    </c:choose>

                </form:form>
            </div>
            <c:if test="${judgeProposalBean.fellowAction.attributeValue ne 0 and judgeProposalBean.fellowAction.attributeValue ne 3 and !judgeProposalBean.judgingStatus}">
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

</jsp:root>