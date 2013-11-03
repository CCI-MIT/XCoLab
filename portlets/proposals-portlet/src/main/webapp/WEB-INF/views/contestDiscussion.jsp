<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:discussionsTagFiles="urn:jsptagdir:/WEB-INF/tags/discussions"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />
	<div class="proposal-head">
		<div class="inner">
			<div class='headline'>
				<div class="contest-image">
					<img src="/image/contest?img_id=${contest.contestLogoId}" width="52" height="52" alt="Contest Image" />
				</div>
				<div class='proposal-title'>
					<h1>Discussion <proposalsPortlet:contestLink contestId="${contest.contestPK}" text="${contest.contestShortName} " /></h1>
				</div>
			</div>
		</div>
		<!-- /inner -->
	</div>
	<!-- /proposal-head -->	
	<div id="content">
	
		<discussionsTagFiles:discussionComments discussionId="${discussionId }" />
	</div>
</jsp:root>