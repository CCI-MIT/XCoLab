<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:modeling="urn:jsptagdir:/WEB-INF/tags/modeling"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="./init.jspx" />
	<div class="proposal-head">
		<div class="inner">
			<div class='headline'>
				<div class="contest-image">
					<img src="/image/contest?img_id=${contest.contestLogoId}" width="52" height="52" alt="Contest Image" />
				</div>
				<div class='proposal-title'>
					<h1>Model <proposalsPortlet:contestLink contestId="${contest.contestPK}" text="${contest.contestShortName} " /></h1>
				</div>
			</div>
		</div>
		<!-- /inner -->
	</div>
	<!-- /proposal-head -->	
	<div id="content">
			<modeling:simulationEdit  modelId="${modelId }" />
	</div>
	<c:if test="${not empty availableModels }">
	<div>
			<div class="clear"></div>
			<br />
        
            <div class="act-edit-box_left" style="height: 80px;">
            
                <p>Select varying levels of geographic disaggregation for the actions</p>
                <div class="">
                	<select class='selectbox1'>
                	<c:forEach var="model" items="${availableModels }">
                		<option value="${model.key }">${model.value }</option>
                	</c:forEach>
                	</select>
                </div>
            </div>
	        <div class="act-edit-box_right" style="height: 80px;">
    	        <p>You can also upload your own disaggregation mode</p>
        	    <div class="butt_wrap">
            	    <div class="button"><a href="/web/guest/resources/-/wiki/Main/Upload+model+help"><span>CONTRIBUTE</span> model</a></div>
            	</div>
        	</div>
        <div class="clear"></div>
	</div>
	<script>
		jQuery(".selectbox1").change(function() {
			modeling.loadModel($(this).val());
			jQuery(".act-edit_left").html("");
			jQuery(".act-edit_right").html("");
		});
	</script>
	</c:if>
	
</jsp:root>