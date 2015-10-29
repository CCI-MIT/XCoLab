<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:collab="http://climatecolab.org/tags/collab_1.0" version="2.0">

    <div>
        <h2> ${title} <span><a href="/web/guest/plans">see all contests</a></span></h2>
        <c:forEach var="contest" items="${contests }">
            <div class="contestbox">
                <div class="img-wrap">
                    <a href="${contest.contestUrl}">
                        <c:if test="${not empty contest.logoPath}">
                            <img src="/image/${contestsBean.themeDisplay.pathImage}${contest.logoPath}" width="151" height="151" alt="${contest.contestShortName}" />
                        </c:if>
                        <c:if test="${empty contest.logoPath}">
                            <img src="/climatecolab-theme/images/blank.gif" width="151" height="151" alt="${contest.contestShortName}" style="border: 1px solid #bbb;"/>
                        </c:if>
                    </a>
                </div>
                <div class="text-wrap">
                    <h3><collab:contestLink contest="${contest}"/></h3>
                    ${contest.contestName}
                    <div class="count">
                        <div class="icePnlGrp prop"><strong>${contest.proposalsCount}</strong> proposals</div>
                        <c:if test="${not contest.contestInVotingPhase}">
                        	<div class="icePnlGrp comm"><strong> ${contest.totalCommentsCount}</strong> comments</div>
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