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
<jsp:directive.include file="./init.jspx" />

	<jsp:directive.include file="./proposalDetails/header.jspx" />

    <style>h3 { margin: 20px 0 10px 0 !important;}</style>

    <div id="content">


        <h1>Rating</h1>
        <portlet:actionURL var="saveFellowRatingURL">
            <portlet:param name="action_forwardToPage" value="proposalDetails_FELLOW" />
            <portlet:param name="contestId" value="${contest.contestPK }" />
            <portlet:param name="planId" value="${proposal.proposalId }" />
            <portlet:param name="action" value="saveFellowRating" />
        </portlet:actionURL>



        <div class="judging_left">
            <div class="addpropbox">
                <form:form id="fellowRatingForm" action="${saveFellowRatingURL }" method="post" commandName="judgeProposalBean">

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
                            <td><form:radiobutton path="fellowRating" value="1" /></td>
                            <td><form:radiobutton path="fellowRating" value="2" /></td>
                            <td><form:radiobutton path="fellowRating" value="3" /></td>
                            <td><form:radiobutton path="fellowRating" value="4" /></td>
                            <td><form:radiobutton path="fellowRating" value="5" /></td>
                        </tr>
                        </tbody>
                    </table>
                    <h3>Promotion</h3>


                    <form:select path="fellowAction" items="${judgingOptions}" itemLabel="description"/>


                    <h3>Select judges</h3>
                    <table class="judgingForm">
                        <tbody>
                            <tr>
                                <c:forEach var="judge" items="${contest.contestJudges }">
                                    <td>
                                        <proposalsPortlet:userPortrait screenName="${judge.screenName }"	portraitId="${judge.portraitId}" width="30"	height="30" /><br />
                                        <proposalsPortlet:userLinkSimple userId="${judge.userId}" text="${judge.fullName}" /><br />
                                        <form:checkbox path="selectedJudges" value="${judge.userId}"/>
                                    </td>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>

                    <h3>Comment</h3>
                    <form:textarea id="fellowComment" cssClass="commentbox" path="fellowComment" style="width:100%;"/>

                    <div class="blue-button" style="display:block; float:right;">
                        <a href="javascript:;" class="requestMembershipSubmitButton" onclick="jQuery(this).parents('form').submit();">Save</a>
                    </div>

                </form:form>
            </div>
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

        <div style="padding-top:40px; clear:both;"></div>
        <discussionsTagFiles:discussionComments discussionId="${discussionId }" />


    </div>
	
</jsp:root>