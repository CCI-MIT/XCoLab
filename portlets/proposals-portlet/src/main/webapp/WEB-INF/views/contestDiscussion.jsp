<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:discussionsTagFiles="urn:jsptagdir:/WEB-INF/tags/evaluations"
	version="2.0">

	<jsp:directive.include file="./init_contest.jspx" />

	<div class="proposal-head">
		<div class="inner">
			<div class='headline'>
				<div class="contest-image">
					<img src="/image/contest?img_id=${contest.contestLogoId}" width="52" height="52" alt="Contest Image" />
				</div>
				<div class='proposal-title'>
					<h1>Discussion <proposalsPortlet:contestLink contest="${contest}" text="${contest.contestShortName} " /></h1>
				</div>
			</div>
		</div>
		<!-- /inner -->
	</div>
	<!-- /proposal-head -->	
	<div id="content">
		<discussionsTagFiles:evaluationComments evaluationDiscussionId="${discussionId }" />
	</div>
</jsp:root>