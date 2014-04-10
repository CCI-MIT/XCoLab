<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
<jsp:directive.include file="./init.jspx" />

	<jsp:directive.include file="./proposalDetails/header.jspx" />
	<div id="content">
		<modeling:simulationView scenarioId="${proposal.scenarioId }" modelId="${proposal.modelId }" />
		<div class="clear"><!--  --></div>
		<br />
		<br />
	
		<c:if test="${proposal.modelId ge 10 and proposal.modelId le 13 }">
			Click on the link to learn more about the <a href='/web/guest/resources/-/wiki/Main/MIT+Composite+ModelMIT Composite Model'>MIT Composite Model</a>
		</c:if>
	</div>

</jsp:root>