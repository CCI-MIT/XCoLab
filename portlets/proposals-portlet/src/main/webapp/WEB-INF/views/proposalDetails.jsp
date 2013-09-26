<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

<div id="content">
	<div class="proposal-head">
		<div class="inner">
			<div class="headline">
				<div class="proposal-image">
					<c:choose>
						<c:when test='${proposal.imageId > 0}'>
							<img src="/image/proposal?img_id=${proposal.imageId}" width="52" height="52" alt="${proposal.name }" />
						</c:when>
						<c:otherwise>
							<img src="/climatecolab-theme/images/proposal_default.png" width="52" height="52" alt="${proposal.name}" />
						</c:otherwise>
					</c:choose>
				</div>
				<div class="proposal-title">
					<div class="prop-description">
						Proposal for
						<proposalsPortlet:contestLink contestId="${contest.contestPK}" text="${contest.contestShortName} " />
						by
						<c:choose>
							<c:when test="${empty proposal.team}">
								<proposalsPortlet:userLinkSimple userId="${proposal.authorId}" text="${proposal.author.screenName}" />
							</c:when>
							<c:otherwise>
                        		${proposal.team}
                        	</c:otherwise>
                        </c:choose>
					</div>
					<h1>${proposal.name}</h1>
				</div>
			</div>
		</div>
		<!-- /inner -->
	</div>
	<!-- /proposal-head -->	
</div>

</jsp:root>