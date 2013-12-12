<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:proposalsPortlet="urn:jsptagdir:/WEB-INF/tags/proposalsPortlet"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0"
    xmlns:collab="http://climatecolab.org/tags/collab_1.0">
<jsp:directive.include file="./init.jspx" />

<script type="text/javascript" src="/html/js/editor/ckeditor_old/ckeditor.js" ><!-- --></script>
<jsp:directive.include file="./proposalDetails/header.jspx" />

<style>
    td em {
        background: #000; border-radius: 5px; font-style: normal;
        box-shadow: 0 0 2px #000;
    }
</style>


<div id="content">
	<div class="prop-left">
		
	<c:choose>
		<c:when test="${proposal.proposalId > 0 }">
			<portlet:actionURL var="updateProposalSectionsURL">
				<portlet:param name="action_forwardToPage" value="proposalDetails" />
				<portlet:param name="action_errorForwardToPage" value="proposalDetails" />
				<portlet:param name="contestId" value="${contest.contestPK }" />
				<portlet:param name="planId" value="${proposal.proposalId }" />
				<portlet:param name="action" value="updateProposalDetails" />
			</portlet:actionURL>
		</c:when>
		<c:otherwise>
			<portlet:actionURL var="updateProposalSectionsURL">
				<portlet:param name="action_forwardToPage" value="proposalDetails" />
				<portlet:param name="action_errorForwardToPage" value="createProposal" />
				<portlet:param name="contestId" value="${contest.contestPK }" />
				<portlet:param name="action" value="updateProposalDetails" />
			</portlet:actionURL>
		</c:otherwise>
	</c:choose>

    <div class="headline addprop">
        Please complete your proposal based on the template below. 
        If you have input on the template, please send it in a&#160;<a href="/web/guest/feedback" target="_blank">feedback message</a>.  
        To save your proposal, you must agree to the&#160;<a href="/web/guest/resources/-/wiki/Main/2012+contest+rules" target="_blank">Contest rules</a> 
        and&#160;<a href="/web/guest/resources/-/wiki/Main/Terms+of+use" target="_blank">Terms of use</a>.
    </div> <!-- /headline -->
    
    
	<form action="/fileUpload" method="post" enctype="multipart/form-data" target="_fileUploadFrame" id="fileUploadForm">
		<input type="file" name="file" id="fileUploadInput" />
	</form>
	
	
	<form:form action="${updateProposalSectionsURL }" commandName="updateProposalSectionsBean" modelAttribute="updateProposalSectionsBean"  cssClass="addpropform" id="editForm">
		
		<div class="addpropbox q1">
            <label>
                <strong>Title</strong> 
                <a class="helpTrigger" href="javascript:;"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
                80 characters
            </label>
            <div class="addprophelp">Give your proposal a title. It's the first thing most people will see, so you'll want to make it descriptive and engaging.</div>
            <div class="addpropInputContainer">
            	<form:input path="name" id="proposalName" />
         		<form:errors path="name" cssClass="alert alert-error" />
                <div class="clearfix"><!--  --></div>
         		
                <div class="inputLimitContainer"><span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">80</span> characters</div>
            </div>
        </div>
        
        <div class="addpropbox blue q2">
            <label>
                <span><strong>Team name</strong><br />
                <em>optional</em></span> 
                <a class="helpTrigger" href="javascript:;"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
                20 characters
            </label>
            <div class="addprophelp">If you'd like to have your proposal appear under a team name, insert it here. Otherwise, the proposal will appear under the user name of its owner.</div>
            <div class="addpropInputContainer">
            	<form:input path="team" id="proposalTeam" />
         		<form:errors path="team" />
            
                <div class="inputLimitContainer"><span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">20</span> characters</div>
            </div>
        </div>
        <div class="notation">Note: If you enter a team name, it will replace the proposal owner's name in the Author field.</div>
        
		<div class="addpropbox blue">
            <label>
                <span><strong>Proposal image</strong><br />
                <em>optional</em></span>
            </label>
            
            <div class="upload proposalImageUpload">
				<div class="uploadbox" id="proposalImage">
					<c:choose>
						<c:when test='${proposal.imageId > 0}'>
							<c:set var="imageUrl" value="/image/proposal?img_id=${proposal.imageId}" />
						</c:when>
						<c:otherwise>
							<c:set var="imageUrl" value="/climatecolab-theme/images/proposal_default.png" />
						</c:otherwise>
					</c:choose>
					<img src="${imageUrl }" width="52" height="52" alt="${proposal.name}" id="proposalImageImg"/>
					<form:input path="imageId" cssStyle="display: none;" id="proposalImageId" />
                </div>
                            
                <div id="uploadWidget"><!--  --></div>
                <div class="clear"><!--  --></div>    
            </div>
        </div>
        
        <div class="addpropbox q3">
            <label>
                <strong>Pitch</strong> 
                <a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
                140 characters
            </label>
            <div class="addprophelp">Insert a tweet-length (140 character) message that conveys the key idea behind your proposal.</div>
            <div class="addpropInputContainer">
            	<form:textarea path="pitch" id="proposalPitchInput" cssClass="proposalPitchInput" />
         		<form:errors path="pitch" />
                <div class="inputLimitContainer">
                    <span class="limit_characterCount"><!--  --></span>/&#160;<span class="limit_charactersMax">140</span> characters
                </div>
            </div>
            
            <script>
                jQuery("textarea#proposalPitchInput").keydown(function(e) {
                    var TABKEY = 9;
                    if(e.keyCode == TABKEY) {
                        this.value += "    ";
                        if(e.preventDefault) {
                            e.preventDefault();
                        }
                        return false;
                    }
                });
            </script>
        </div>

        <!-- legacy for old proposals-->
        <c:if test="${fn:length(proposal.description) gt 0}">
            <div class="addpropbox q3">
                <label>
                    <strong>Description</strong>
                    <a href="javascript:;" class="helpTrigger"><img src="/climatecolab-theme/images/icon-addprop-question.png" width="15" height="15" /></a><br />
                </label>

                <div class="addpropInputContainer">
                    <form:textarea cssClass="rte"  cols="54" rows="7" path="description" />
                </div>
            </div>
        </c:if>
		
		<c:forEach var="section" items="${proposal.sections }">
			<c:if test="${not section.locked }">
				<div class="addpropbox q3">
					<proposalsPortlet:proposalSectionEdit section="${section }" />
				</div>
			</c:if>
		</c:forEach>	
	</form:form>
		
		
	</div>
		<jsp:directive.include file="./proposalDetails/proposalSummary.jspx" />
		
		    
	<div id="acceptTosPopup" style="position: fixed; width: 100%; height: 100%; top: 0; left: 0; z-index: 100; display: none;">
		<div class="popup-wrap p1" id="acceptTosPopup">
 			<div class="popup">
  				<h4>By saving your proposal you are entering the Climate CoLab contest and agree to the&#160;
  				<a href="/web/guest/resources/-/wiki/Main/Terms+of+use" target="_blank">Terms of Use</a>
  				&#160;and Contest Rules.</h4>
  				
  				<h5>CONTEST RULES:</h5>
  				<div class="terms">
					${preferences.termsOfService}
  				</div>
  				<div class="btns">
   					<div class="blue-button"><a href="javascript:;" id="tosAccepted" class="cp1-1">ACCEPT</a></div> 
   					<div class="gray-button"><a href="javascript:;" class="cp1-2" id="closeAcceptTos">DO NOT Accept</a></div>
   				</div>
 			</div>
		</div>
	</div>
	
	<div id="invalidFieldsPopupContainer" style="position: fixed; width: 100%; height: 100%; top: 0; left: 0; z-index: 100; display: none; ">
		<div class="popup-wrap p1" id="invalidFieldsPopup">
 			<div class="popup">
  				<h4>The following sections have too many characters:</h4>
  				<ul id="invalidFieldsList"><!--  --></ul>
  				<div class="btns">
   					<div class="blue-button"><a href="javascript:;" onclick="jQuery('#invalidFieldsPopupContainer').fadeOut('fast')" class="cp1-1">CLOSE</a></div>
  				</div>
 			</div>
		</div>
	</div>

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
                    <div class="blue-button"><a href="#" id="saveChangesButton">SAVE changes</a></div>
                    <div class="gray-button">
                    <c:choose>
                    	<c:when test="${proposal.currentVersion > 0 }">
                    		<collab:proposalLink proposalId="${proposal.proposalId }" contestId="${contest.contestPK }" text="DISCARD changes" />
                    	</c:when>
                    	<c:otherwise>
                    		<!--  proposal creation, return to contest proposals page on discard -->
                    		<proposalsPortlet:contestLink contestId="${contest.contestPK }" text="DISCARD changes" />
                    	</c:otherwise>
                    </c:choose>
                    </div>
                </div>
                <div class="admin-right">
                    <p>&#160;</p>
                </div>
            </div>
        </div>
</div>
		
</div>

<iframe name="_fileUploadFrame" id="fileUploadFrame" class="hidden" style="display: none;"><!-- comment --></iframe>

<!--  Proposals Picker  -->
<portlet:resourceURL var="proposalPickerURL" id="proposalPicker">
    <portlet:param name="type" value="@@REPLACE-TYPE@@" />
    <portlet:param name="filterKey" value="@@REPLACE-FILTERKEY@@" />
    <portlet:param name="filterText" value="@@REPLACE-FILTERTEXT@@" />
    <portlet:param name="start" value="@@REPLACE-START@@" />
    <portlet:param name="end" value="@@REPLACE-END@@" />
    <portlet:param name="sortColumn" value="@@REPLACE-SORTCOLOMN@@" />
    <portlet:param name="sortOrder" value="@@REPLACE-SORTORDER@@" />
</portlet:resourceURL>

<portlet:resourceURL var="proposalPickerCounterURL" id="proposalPickerCounter">
    <portlet:param name="type" value="@@REPLACE-TYPE@@" />
    <portlet:param name="filterKey" value="@@REPLACE-FILTERKEY@@" />
    <portlet:param name="filterText" value="@@REPLACE-FILTERTEXT@@" />
    <portlet:param name="start" value="@@REPLACE-START@@" />
    <portlet:param name="end" value="@@REPLACE-END@@" />
    <portlet:param name="sortColumn" value="@@REPLACE-SORTCOLOMN@@" />
    <portlet:param name="sortOrder" value="@@REPLACE-SORTORDER@@" />
</portlet:resourceURL>

<script>
    var proposalPickerURL = '${proposalPickerURL}';
    var proposalPickerCounterURL = '${proposalPickerCounterURL}';
    var filterKey = 'ACCEPTALL';
    var proposalType;
</script>

<div id="popup_proposalPicker" class="popup-wrap proposal-picker" style="display:none;">
    <div class="popup">
        <div class="closepopuplogin">
            <a href="javascript:;" onclick="jQuery('#popup_proposalPicker').hide()">
                <img src="http://localhost:8080/climatecolab-theme/images/help_close.png" width="20" height="20"
                     alt="X"/>
            </a>
        </div>
        <h4>Choose your proposal</h4>



        <div class="prop-tabs">
            <ul>
                <li class="c">
                    <a href="javascript:;" onclick="proposalPickerTabSelected($(this),'subscriptions');"> My subscriptions <div id="numberOfSubscriptions">&#160;</div></a>
                </li>
                <li class="">
                    <a href="javascript:;" onclick="proposalPickerTabSelected($(this),'supporting');"> Supporting <div id="numberOfSupporting">&#160;</div></a></li>
                <li class="">
                    <a href="javascript:;" onclick="proposalPickerTabSelected($(this),'all');"> All proposals <div id="numberOfProposals">&#160;</div></a>
                </li>
                <li style="float: right; margin:0;">
                    <input id="prop-search" name="searchField" type="text" placeholder="Filter" maxcharacters="80" validatelength="true" class="rteInitialized" style="display: block; height: 22px; width: 175px; padding: 0 5px;" />
                </li>

            </ul>
        </div>

        <div class="popup-tabcontainer" style="min-height: 200px;" id="proposalPickerTableContainer">
            <table id ="proposalPickerTable">
                <thead>
                    <tr class="blueheaderbar tooltips" style="border-top: none;">
                        <td style="width: 35%;">
                            <a href="javascript:;" onclick="sortByColumn($(this), 'contest');">Contest</a>
                            <div class="tooltip" style="display: none; top: -41px; left: -41px;"> click to sort by contest name <div class="tt-arrow"></div></div>
                        </td>
                        <td style="width: 35%;">
                            <a href="javascript:;" onclick="sortByColumn($(this), 'proposal');">Proposal</a>
                            <div class="tooltip" style="display: none; top: -41px; left: -41px;"> click to sort by proposal name <div class="tt-arrow"></div></div>
                        </td>
                        <td style="width: 15%;">
                            <a href="javascript:;" onclick="sortByColumn($(this), 'date');">Subscribed</a>
                            <div class="tooltip" style="display: none; top: -41px; left: -41px;"> click to sort by date subscribed <div class="tt-arrow"></div></div>
                        </td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div id="warning"></div>
    </div>
</div>
<!--  /Proposals Picker  -->

<script type="text/javascript">
	jQuery("#fileUploadInput").change(function() {
		jQuery("#fileUploadForm").submit();
		jQuery("#proposalImage").block({message: "", css: {"font-size":"12px", margin: "0px", padding: 0}});
	});
	jQuery("#fileUploadFrame").load(
		function() {
			try {
				var response = eval("("	+ jQuery(this).contents().text() + ")");
				
				jQuery("#proposalImageImg").attr("src", "/image/proposal?img_id=" + response.imageId);
				jQuery("#proposalImage").unblock();
				jQuery("#proposalImageId").val(response.imageId);
			}
			catch (e) {
			}
		}
	);

	function updateUploadBtnOffset() {
		var container = jQuery("#uploadWidget");
		var containerOffset = container.offset();
				
		jQuery("#fileUploadInput").offset(containerOffset);
				
	}
	
     function showAcceptTosPopup() {
        jQuery("#acceptTosPopup").fadeIn("fast");
    }
    
    function closeAcceptTosPopup() {
        jQuery("#acceptTosPopup").fadeOut("fast");
        
    }
    
    function tosAcceptedSave() {
    	jQuery("#acceptTosPopup").fadeOut("fast");
    	saveIfValid();
    }
    
    function saveIfValid() {
    	if (validatePlanEditForm()) {
    		disableDirtyCheck();
			jQuery("#editForm").submit();
    	}
    }
	
	jQuery(function() {
		updateUploadBtnOffset();
		$(window).resize(updateUploadBtnOffset);
		
		
		
		jQuery("#saveChangesButton").click(function() {
			if (${proposal.proposalId > 0}) {
				saveIfValid();
			}
			else {
				showAcceptTosPopup();
			}
		});
		
		jQuery("#closeAcceptTos").click(closeAcceptTosPopup);
		jQuery("#tosAccepted").click(tosAcceptedSave);
		
		enableDirtyCheck();
	});





</script>

</jsp:root>