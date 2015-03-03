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
	<div id="content">

		<h2>Proposal rating function for every colab user</h2>
		tbd

		<c:choose>
			<!-- TODO clarify when to show what -->
			<c:when test="${showDiscussion}">
				<h2>Judging results</h2>
				<div class="addpropbox evaluation">
					<proposalsPortlet:proposalRatingComments showRating="true" showPhase="true"
															 proposalRatingsWrappers="${judgeAverageRating}"
															 proposalId="${proposalId}" />
				</div>

				<h2>Discussion area</h2>
				<discussionsTagFiles:discussionComments discussionId="${discussionId }" />
			</c:when>
			<c:otherwise>
				The judging decisions will be visible once a decision was made.
			</c:otherwise>
		</c:choose>

	</div>
	

</jsp:root>