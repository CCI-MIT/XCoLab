<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

	<jsp:directive.include file="./proposalDetails/header.jspx" />
	
	<div id="content">
		<div class="prop-left">
			<c:if test="${not empty proposal.pitch }">
				<h2>Pitch</h2>
				<p class="intro">${proposal.pitch}</p>
				<div class="div1"><!--  --></div>
			</c:if>
		
			<h2>Description</h2>
			<c:choose>
				<c:when test="${empty proposal.sections }">
					${proposa.description }
				</c:when>
				<c:otherwise>
					<c:forEach var="section" items="${proposal.sections }" varStatus="status">
						<h3>${section.title }</h3>
							<p>${section.content }</p>
					
						<c:if test="${not status.last }">
							<div class="div2"><!--  --></div>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</jsp:root>