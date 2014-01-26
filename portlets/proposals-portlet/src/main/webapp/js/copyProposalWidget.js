function loadContests() {
    jQuery.getJSON('/api/jsonws/plansProposalsFacade-portlet.contest/get-contests-open-for-proposals', function(data) {
    	var html = ["<table>"];
    	jQuery(data).each(function(idx, obj) {
    		html.push("<tr>");
    		html.push("<td><strong>");
    		html.push(obj.contestShortName);
    		html.push("</strong><br />");
    		html.push(obj.contestName);
    		html.push("</td>");
    		html.push("<td>");
    		html.push('<div class="blue-button"><a href="/web/guest/plans/-/plans/contestId/');
    		html.push(obj.contestPK);
    		html.push('/createProposal/basedOn/');
    		html.push(currentProposal.proposalId);
    		html.push('/');
    		html.push(currentProposal.version);
    		html.push('/');
    		html.push(obj.contestPK);
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
	
    jQuery.getJSON('/api/jsonws/plansProposalsFacade-portlet.proposal/get-proposal-contest-sections', {
        proposalId: 1702,
        version: 67,
        contestId: 1300103
      }, function(data) {
    	var html = ["<table>"];
    	jQuery(data).each(function(idx, obj) {
    		availableSections[obj.sectionId] = obj;
    		html.push("<tr>");
    		html.push("<td><strong>");
    		html.push(obj.title);
    		html.push("</strong> <a href='javascript:;'>show content</a><br /><div class='sectionContent'>");
    		html.push(obj.content);
    		html.push("</div></td>");
    		html.push("<td>");
    		html.push('<div class="blue-button"><a href="javascript:;" data-section-id="');
    	    html.push(obj.sectionId)
    	    html.push('">Copy</a></div>');
    		html.push("</td></tr>");
    	});
    	html.push("</table>");
    	
    	
    	jQuery("#copyProposalContests").html(html.join(""));
    	
    	jQuery("#copyProposalContests a").click(function() {
    		var copyFromSectionId = jQuery(this).attr('data-section-id');
    		CKEDITOR.instances['sectionsContent' + targetSectionId].insertHtml(availableSections[copyFromSectionId].content);
    		jQuery("#copyProposalContainer").hide();
    	});
    	proposalsLoaded = true;
    	
    });
}

jQuery(function() {
	jQuery(".copyFromBaseProposalTrigger").click(function() {
		targetSectionId = jQuery(this).attr('data-section-id');
		loadProposalSections();
		jQuery("#copyProposalContainer").show();
		
		
	});
});

function showCopyProposalPopup() {
	loadContests();
	jQuery("#copyProposalContainer").show();
}


   