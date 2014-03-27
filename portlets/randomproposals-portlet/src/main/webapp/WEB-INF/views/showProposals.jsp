<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	
	<div>
        <h2> ${proposalsModel.preferences.title} <span><a href="/web/guest/plans">see all proposals</a></span></h2>        
        <c:forEach items="${proposalsModel.proposals}" var="proposal">
        	<div class="contestbox">
                <div class="img-wrap">
                    <a href="/web/guest/plans/-/plans/contestId/${proposal.contestId}/planId/${proposal.proposalId}">
                        <c:if test="${proposal.image > 0}">
                            <img src="${proposalsModel.baseImagePath}${proposal.image}" width="151" height="151" alt="${proposal.name}" />
                       	</c:if>
                        <c:if test="${empty proposal.image or proposal.image le 0}">
                            <img src="/climatecolab-theme/images/blank.gif" width="151" height="151" alt="${proposal.name}" style="border: 1px solid #bbb;"/>
                        </c:if>
                    </a>
                </div>
                <div class="text-wrap">
                    <h3><a href="/web/guest/plans/-/plans/contestId/${proposal.contestId}/planId/${proposal.proposalId}">${proposal.name}</a></h3>
                    ${proposal.pitch}
                </div>
            </div> 
        </c:forEach>
 	</div>
 	
</jsp:root>