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
			html.push('<a class="btn btn-primary" href="/contests/'+ obj.contestYear +'/' + obj.contestUrlName);

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
			html.push('">Select</a>');
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
			html.push('<button type="button" class="btn btn-primary js-CopySectionButton" data-section-id="');
			html.push(obj.sectionId);
			html.push('">Copy</button></div>');
			html.push("</td></tr>");
		});
		html.push("</table>");

		var $copyProposalContestsElement = jQuery("#copyProposalContests");
		$copyProposalContestsElement.html(html.join(""));

		$copyProposalContestsElement.find(".js-CopySectionButton").click(function() {
			var copyFromSectionId = jQuery(this).attr('data-section-id');

			CKEDITOR.instances['sectionsContent' + targetSectionId].insertHtml(availableSections[copyFromSectionId].content);
			jQuery('#copyProposalModal').modal('show');
		});

		$copyProposalContestsElement.find("a.toggleContent").click(function() {
			var container = jQuery(this).parent();
			container.toggleClass("expanded").find(".sectionContent").toggle();
			container.find(".toggleContent").toggle();
		});
		proposalsLoaded = true;

	});
}


jQuery(function() {
	jQuery(".copyFromBaseProposalTrigger").click(function() {
		targetSectionId = jQuery(this).attr('data-section-id');
		loadProposalSections();
        jQuery('#copyProposalModal').modal('show');
	});
});

function showCopyProposalPopup(moveType) {
	proposalCopy_loadContests(moveType);
    jQuery('#copyProposalModal').modal('show');
}
