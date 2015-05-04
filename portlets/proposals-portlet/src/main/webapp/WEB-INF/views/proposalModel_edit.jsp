<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0"
    xmlns:collab="http://climatecolab.org/tags/collab_1.0">
<jsp:directive.include file="./init.jspx" />

	<jsp:directive.include file="./proposalDetails/header.jspx" />
	<div id="content">
	
	<c:if test="${not empty availableModels }">
		<proposalsPortlet:modelPicker availableModels="${availableModels  }" contestPK="${contest.contestPK }" modelId="${proposal.modelId}" />
	</c:if>
		<modeling:simulationEdit scenarioId="${proposal.scenarioId }" modelId="${proposal.modelId }" />
	
	<portlet:actionURL var="updateProposalScenarioURL">
		<portlet:param name="action_forwardToPage" value="proposalDetails_ACTIONSIMPACTS" />
		<portlet:param name="contestId" value="${contest.contestPK }" />
		<portlet:param name="planId" value="${proposal.proposalId }" />
		<portlet:param name="action" value="updateProposalScenario" />
	</portlet:actionURL>
	
	<form action="${updateProposalScenarioURL }" id="updateProposalScenarioForm" method="post">
		<input type="text" id="proposalScenarioId" name="scenarioId" class="hidden" />
	</form>


    <div class="admin-overlay-wrap">
        <div class="admin-overlay">
            <div class="inner">
                <div class="admin-left">
                    <p>
                    	<c:choose>
                    		<c:when test="${proposal.currentVersion le 1}">
                            	You are currently editing a new proposal
                            </c:when>
                            <c:otherwise>
                            	You are editing a proposal
                            </c:otherwise>
                        </c:choose>
                    	<br />
                    	<c:if test="${not empty proposal.name }">"${proposal.name}"</c:if>
                    </p>
                    <div class="blue-button"><a href="javascript:;" id="saveChangesButton">SAVE and PUBLISH changes</a></div>
                    <div class="gray-button">
                    	<collab:proposalLink proposalId="${proposal.proposalId }" contestId="${contest.contestPK }" text="DISCARD changes" tab="${currentTab }" />
                    </div>
                </div>
                <div class="admin-right">
                    <p>&#160;</p>
                </div>
            </div>
        </div>
	</div>
	<script>
		jQuery("#saveChangesButton").click(function() {
			jQuery("#proposalScenarioId").val(jQuery("#modelingScenarioId").val());

    		disableDirtyCheck();
			jQuery("#updateProposalScenarioForm").submit();
		});
	
		jQuery(function() {
			enableDirtyCheck();
		});
		
	</script>
	
	</div>
</jsp:root>
