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


        <h1>Rating</h1>
        <portlet:actionURL var="saveJudgeRatingURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_JUDGE"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId }"/>
            <portlet:param name="action" value="saveJudgeRating"/>
        </portlet:actionURL>
        <portlet:actionURL var="sendEmailsURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_JUDGE"/>
            <portlet:param name="contestId" value="${contest.contestPK }"/>
            <portlet:param name="planId" value="${proposal.proposalId }"/>
            <portlet:param name="action" value="sendEmails"/>
        </portlet:actionURL>

        <div class="judging_left">
            <form:form id="fellowRatingForm" action="${saveJudgeRatingURL }" method="post"
                       commandName="judgeProposalBean">
                <div class="addpropbox">

                    <h3>Comment</h3>
                    <form:textarea id="judgeComment" cssClass="commentbox" path="judgeComment" style="width:100%;"/>

                    <c:if test="${!judgeProposalBean.judgingStatus}">
                        <div class="blue-button" style="display:block; float:left;">
                            <a class="requestMembershipSubmitButton" href="${sendEmailsURL}">Send e-Mails</a>
                        </div>
                    </c:if>

                    <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                        <a href="javascript:;" class="requestMembershipSubmitButton"
                           onclick="jQuery(this).parents('form').submit();">Save</a>
                    </div>
                </div>

                <div class="addpropbox">

                    <h3 style="margin-top: 0;">Rating</h3>
                    <table class="judgingForm">
                        <tbody>
                        <tr>
                            <td>poor</td>
                            <td>fair</td>
                            <td>good</td>
                            <td>very good</td>
                            <td>outstanding</td>
                        </tr>
                        <tr>
                            <td><form:radiobutton path="judgeRating" value="1"/></td>
                            <td><form:radiobutton path="judgeRating" value="2"/></td>
                            <td><form:radiobutton path="judgeRating" value="3"/></td>
                            <td><form:radiobutton path="judgeRating" value="4"/></td>
                            <td><form:radiobutton path="judgeRating" value="5"/></td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                        <a href="javascript:;" class="requestMembershipSubmitButton"
                           onclick="jQuery(this).parents('form').submit();">Save</a>
                    </div>
                </div>

                <div class="addpropbox">
                    <h3>Promotion</h3>
                    <form:select path="judgeAction" items="${judgingOptions}" itemLabel="description"/>

                    <div class="blue-button" style="display:block; float:right; margin-top: 10px;">
                        <a href="javascript:;" class="requestMembershipSubmitButton"
                           onclick="jQuery(this).parents('form').submit();">Save</a>
                    </div>
                </div>
            </form:form>
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
    </div>


</jsp:root>