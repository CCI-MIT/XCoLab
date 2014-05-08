<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />

<portlet:resourceURL var="activitiesDumpURL" >
	<portlet:param name="action" value="generateDump" />
</portlet:resourceURL>

<div class="alignright" style="display:none;">
	<a href="${activitiesDumpURL }"><liferay-ui:icon image="download" />activities in CSV format</a>
</div>
<div class="clearfix"><!--  --></div>
<h2>${portletTitle}</h2>
<c:choose>
	<c:when test="${empty activities  }">
		Nothing found.
	</c:when>
	<c:when test="${feedStyle == 'FULL' }">
		<c:forEach items="${activities }" var="activity">
			<div id="activitiesTable">
				<div class="activitybox ${activity.odd ? '' : 'blu' }" style="width: 100%">
					<div class="status ${activity.type.displayName}" ><!--  --></div>
					<div class="text" style="width: 750px; margin-top: 20px;">
						<c:out value="${activity.body}" escapeXml="false" />						
					</div>
					<h3 style="float: right; margin-top: 20px; margin-right:10px;">${activity.activityDateAgo}</h3>
				</div>
            	<div class="clear clearfix"></div>
			</div>
		</c:forEach>
		
		<div class="paginatorControls">
			<c:choose>
		
				<c:when test="${empty filterUserId }"> 
					<portlet:renderURL var="prevURL">
						<portlet:param name="page" value="${sortFilterPage.page - 1 }" />
					</portlet:renderURL> 
				</c:when>
				<c:otherwise>
					<portlet:renderURL var="prevURL">
						<portlet:param name="page" value="${sortFilterPage.page - 1 }" />
						<portlet:param name="userId" value="${filterUserId}" />
					</portlet:renderURL> 
				
				</c:otherwise> 
			</c:choose>
			<div class="alignleft">
				<c:if test="${sortFilterPage.page > 0 }">
					<a href="${prevURL }">&amp;laquo; newer</a>
				</c:if>
			</div>

			<div class="alignright">
				<c:choose>
					<c:when test="${empty filterUserId }"> 
						<portlet:renderURL var="nextURL">
							<portlet:param name="page" value="${sortFilterPage.page + 1 }" />
						</portlet:renderURL>
					</c:when>
					<c:otherwise>
						<portlet:renderURL var="nextURL">
							<portlet:param name="page" value="${sortFilterPage.page + 1 }" />
							<portlet:param name="userId" value="${filterUserId}" />
						</portlet:renderURL>
					
					</c:otherwise> 
				</c:choose>
				<c:if test="${ not isLastPage }">
					<a href="${nextURL }">older &amp;raquo;</a>
				</c:if>
			</div>
		</div>
		<div class="clearfix"><!--  --></div>
	</c:when>
	<c:otherwise>
		<c:forEach items="${activities }" var="activity">
			<div class="activitybox ${activity.odd ? '' : 'blu'}">
				<div class="status ${activity.type.displayName}"><!--  --></div>
				<div class="text">
        			<h3>${activity.activityDateAgo}</h3>
					<c:out value="${activity.body}" escapeXml="false" />
        		</div>
    		</div>
		</c:forEach>
	</c:otherwise>
</c:choose>

<c:if test="${seeMoreLinkShown}">
    <div class="alignright" style="margin-top:10px;">
        <a href="/web/guest/activities">View all activities</a>
    </div>
</c:if>
<c:if test="${not empty filterUserId}">
	<script type="text/javascript">
		var filterSettings = {
			filterUserId: ${filterUserId},
			filterUserName: '${filterUser.screenName}'
		};
	</script>  
</c:if>


</jsp:root>