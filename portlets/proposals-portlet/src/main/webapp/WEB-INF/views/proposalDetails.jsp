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
	
	<h1>Sections etc... ${updateProposalSectionsBean }</h1>

	
	<div id="content">
		<div class="prop-left">
		
	<portlet:actionURL var="updateProposalSectionsURL">
		<portlet:param name="pageToDisplay" value="proposalDetails" />
		<portlet:param name="contestId" value="${contest.contestPK }" />
		<portlet:param name="planId" value="${proposal.proposalId }" />
		<portlet:param name="action" value="updateProposalDetails" />
	</portlet:actionURL>
	
	
			<form:form action="${updateProposalSectionsURL }" commandName="updateProposalSectionsBean">
			<input type="submit" value="submit" />
		<c:forEach var="section" items="${proposal.sections }">
			<c:if test="${not section.locked }">
				<div class="addpropbox q3">
					<label>
                        <strong>${section.title}</strong> 
                        <a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
                        <c:if test="${section.characterLimit gt 0}">
                            ${section.characterLimit} characters
                        </c:if>
                    </label>
                    <div class="addprophelp">${section.helpText}</div>
                    <div class="addpropInputContainer">
                            <form:textarea cssClass="rte"  cols="54" rows="7" path="sectionsContent[${section.sectionDefinitionId}]" />
                        <c:if test="${section.characterLimit gt 0}">
                            <div class="inputLimitContainer">
                                <span class="limit_characterCount"></span>/&#160;<span class="limit_charactersMax">${section.characterLimit}</span> characters
                            </div>
                        </c:if>
                    </div>
				</div>
			</c:if>
		</c:forEach>	
	</form:form>
		
		
			<c:if test="${not empty proposal.pitch }">
				<h2>Pitch</h2>
				<p class="intro">${proposal.pitch}</p>
				<div class="div1"><!--  --></div>
			</c:if>
		
			<h2>Description</h2>
			<c:choose>
				<c:when test="${empty proposal.sections }">
					${proposal.description }
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