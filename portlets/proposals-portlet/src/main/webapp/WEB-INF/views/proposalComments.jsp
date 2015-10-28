<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:discussionsTagFiles="urn:jsptagdir:/WEB-INF/tags/evaluations"
	version="2.0">

	<jsp:directive.include file="./init_proposal_tab.jspx" />

	<jsp:directive.include file="./proposalDetails/header.jspx" />

	<div id="content">
		<discussionsTagFiles:evaluationComments evaluationDiscussionId="${discussionId }" />
	</div>
	

</jsp:root>