function proposalCopy_loadContests(moveType) {
    jQuery.getJSON('/api/contestsOpenForProposals', function(data) {
    	var html = ["<table>"];
    	jQuery(data).each(function(idx, obj) {
    		html.push("<tr>");
    		html.push("<td><strong>");
    		html.push(obj.contestShortName);
    		html.push("</strong><br />");
    		html.push(obj.contestName);
    		html.push("</td>");
    		html.push("<td>");
    		html.push('<div class="c-Button__primary"><a href="/contests/'+ obj.contestYear +'/' + obj.contestUrlName);

			if (moveType == "FORK") {
				html.push('/createProposal/basedOn/');
				html.push(currentProposal.proposalId);
				html.push('/');
				html.push(currentProposal.version);
				html.push('/');
				html.push(currentProposal.contestPK);
			} else {
				html.push('/c/proposal/');
				html.push(currentProposal.proposalId);
				html.push('/moveFromContestPhaseId/' + currentProposal.contestPhaseId);
				html.push("/move/" + moveType);
			}
			html.push('">Select</a></div>');
    		html.push("</td></tr>");
    	});
    	html.push("</table>");
    	
    	$("#copyProposalContests").html(html.join(""));
    	
    });
}

var targetSectionId;
var proposalsLoaded = false;
var availableSections = {};
function loadProposalSections() {
	
	if (proposalsLoaded) return;
	
    jQuery.getJSON('/api/contests/' + baseContest.contestPK
		+ '/proposals/' + baseProposal.proposalId
        + '/versions/' + baseProposal.version + '/sections', {
      }, function(data) {
    	var html = ["<table>"];
    	jQuery(data).each(function(idx, obj) {
    		// section already present in new contest?
    		var alreadyPresent = jQuery("#sectionsContent" + obj.sectionId).length > 0;
    		availableSections[obj.sectionId] = obj;
    		html.push("<tr>");
    		html.push("<td class='baseProposalSection'><strong>");
    		html.push(obj.title);
    		html.push("</strong> <a href='javascript:;' class='expandContent toggleContent alignright'>show content</a> <a href='javascript:;' class='hideContent toggleContent alignright'>hide content</a> <br /><div class='sectionContent'>");
    		html.push(obj.content);
    		html.push("</div>");
    		if (alreadyPresent) {
    			html.push("<span class='sectionPresent'>This section is already used in your proposal</span>");
    		}
    		html.push("</td>");
    		html.push("<td>");
    		html.push('<div class="c-Button__primary"><a href="javascript:;" class="copySectionBtn" data-section-id="');
    	    html.push(obj.sectionId);
    	    html.push('">Copy</a></div>');
    		html.push("</td></tr>");
    	});
    	html.push("</table>");

		var $copyProposalContestsElement = jQuery("#copyProposalContests");
		$copyProposalContestsElement.html(html.join(""));
    	
    	$copyProposalContestsElement.find("a.copySectionBtn").click(function() {
    		var copyFromSectionId = jQuery(this).attr('data-section-id');
    		
    		CKEDITOR.instances['sectionsContent' + targetSectionId].insertHtml(availableSections[copyFromSectionId].content);
    		jQuery("#copyProposalContainer").hide();
    	});
    	
    	$copyProposalContestsElement.find("a.toggleContent").click(function() {
    		var container = jQuery(this).parent();
    		container.toggleClass("expanded").find(".sectionContent").toggle();
    		container.find(".toggleContent").toggle();
    	});
    	proposalsLoaded = true;
    	
    });
}

function updatePopupSize() {
	var container = jQuery("#copyProposalContainer");
	container.find("#copyProposalPopup").css({top: "20px"});
	var availableHeight = jQuery(window).height();
	container.find(".c-Popup").css({height: (availableHeight - 200), "overflow-x": "auto"});
}

jQuery(function() {
	jQuery(".copyFromBaseProposalTrigger").click(function() {
		targetSectionId = jQuery(this).attr('data-section-id');
		loadProposalSections();
		jQuery("#copyProposalContainer").show();
		updatePopupSize();
	});
});

function showCopyProposalPopup(moveType) {
	proposalCopy_loadContests(moveType);
	jQuery("#copyProposalContainer").show();
	updatePopupSize();
}
