CKEDITOR.plugins.add( 'proposalLink', {
    icons: 'proposalLink',
    init: function( editor ) {
    	
    	console.log('editor', editor);

    	var sectionId = null;
    	
    	jQuery(document).on("proposalPicker_proposalSelected", function(event) {
    		if (event.sectionId != sectionId) {
    			return;
    		}
    		var host = window.location.toString();
    		host = host.substring(0, host.indexOf("/web/guest"));
    		
    		var proposalLinkHtml = [];
    		proposalLinkHtml.push("<a href=\"");
    		proposalLinkHtml.push(host);
    		proposalLinkHtml.push(event.linkUrl);
    		proposalLinkHtml.push("\" class=\"proposalLink\">");
    		proposalLinkHtml.push(event.proposalName);
    		proposalLinkHtml.push("</a>");
    		
    		editor.insertHtml(proposalLinkHtml.join(""));
    	});
    	var editorSectionType = jQuery(editor.element).attr('data-section-type');
    	var supportedSectionTypes = {'PROPOSAL_REFERENCE': true, 'PROPOSAL_LIST_REFERENCE': true, 'PROPOSAL_LIST_TEXT_REFERENCE': true};
    	var addProposalPickerButton = editorSectionType && editorSectionType in supportedSectionTypes;
    	if (addProposalPickerButton) {
            sectionId = jQuery(editor.element).attr('data-section-id');
            var contestTypeNames = sectionContestTypeNames[sectionId];

    		editor.ui.addButton( 'ProposalLink', {
    			label: contestTypeNames[0] + ' link',
    			command: 'proposalLink',
    			toolbar: 'insert'
    		});
    	
    		editor.addCommand( 'proposalLink', new CKEDITOR.command( editor, {
    			exec: function() {
    			    //TODO: this doesn't honor ConfigurationAttributeKey.PROPOSALS_PICKER_DEFAULT_TAB_CONTESTS
    				pickProposal(sectionId, 'ALL_CONTESTS', contestTypeNames[0], contestTypeNames[1], contestTypeNames[2], contestTypeNames[3]);
    			}}));
    	}
    	
    }
});
