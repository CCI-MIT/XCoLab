<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

	<!--  >div>
		
		<c:forEach var="contest" items="${contests }">
			<h1>${contest.contestShortName }</h1>
			<p>${contest.name}</p>
			<p>${contest.logoPath}</p>
			<p>${contest.proposalsCount}</p>
			<p>${contest.commentsCount}</p>
		</c:forEach>
	</div-->

	<div>
        <h2>Featured contests <span><a href="/web/guest/plans">see all contests</a></span></h2>
        <c:forEach  var="contest" items="${contests }">
        	
            <div class="contestbox">
                <div class="img-wrap">
                    <a href="/web/guest/plans/-/plans/contestId/${contest.primaryKey}">
                        <c:if test="${not empty contest.logoPath}">
                            <img src="/image/${contestsBean.themeDisplay.pathImage}${contest.logoPath}" width="151" height="151" alt="${contest.contestShortName}" />
                        </c:if>
                        <c:if test="${empty contest.logoPath}">
                            <img src="/climatecolab-theme/images/blank.gif" width="151" height="151" alt="${contest.contestShortName}" style="border: 1px solid #bbb;"/>
                        </c:if>
                    </a>
                </div>                
                <div class="text-wrap">
                    <h3><a href="/web/guest/plans/-/plans/contestId/${contest.primaryKey}">${contest.contestShortName}</a></h3>
                    ${contest.contestName}
                    <div class="count">
                        <div class="icePnlGrp prop"><strong>${contest.proposalsCount}</strong> proposals</div>
                        <c:if test="${not contest.contestInVotingPhase}">
                        	<div class="icePnlGrp comm"><strong> ${contest.totalComments}</strong> comments</div>
                        </c:if>                        
                        <c:if test="${contest.contestInVotingPhase}">
                        	<div class="icePnlGrp comm"><strong>${contest.votesCount}</strong> votes</div>
                        </c:if>
                    </div>
                </div>
                
            </div> <!-- /contestbox -->
            <div class="clearfix"><!--  --></div>
        </c:forEach>
	</div>
</jsp:root>