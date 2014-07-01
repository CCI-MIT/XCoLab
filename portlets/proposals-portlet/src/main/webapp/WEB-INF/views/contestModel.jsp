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
	<c:if test="${not empty availableModels }">
	<div class='modelSelector'>
            <div>
                <p>Select your model:</p>
                <div class="">
                	<select class='selectbox1 modelsSelectBox'>
                	<c:forEach var="model" items="${availableModels }">
                		<option value="${model.key }">${model.value }</option>
                	</c:forEach>
                	</select>
                </div>
            </div>
	</div>
	<script>
		var preferredModelsCookie = "cc_contests_preferredModels";
		var preferredModelsStr = $.cookie(preferredModelsCookie);
		var preferredModelsMap = {};
		// try to parse preferred models
		try {
			preferredModelsMap = JSON.parse(preferredModelsStr);
			if ("${contest.contestPK}" in preferredModelsMap) {
				// there is a preferred model, select it in models select box
				$(".modelsSelectBox").val(preferredModelsMap["${contest.contestPK}"]);
			}
			
			
		} catch (e) {
			// ignore
		}
		jQuery(".selectbox1").change(function() {
			modeling.loadModel($(this).val());
			jQuery(".act-edit_left").html("");
			jQuery(".act-edit_right").html("");
			preferredModelsMap["${contest.contestPK}"] = $(this).val();
			
			$.cookie("cc_contests_preferredModels", JSON.stringify(preferredModelsMap), { expires : 365 });
		});
	</script>
	</c:if>
	
			<modeling:simulationEdit  modelId="${modelId }" />
	</div>
	
</jsp:root>