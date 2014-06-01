CKEDITOR.plugins.add( 'proposalLink', {
    icons: 'proposalLink',
    init: function( editor ) {
    	
    	console.log('editor', editor);

    	var sectionContainer = null;//jQuery(editor.container.$).parents('.addpropbox');
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
    		proposalLinkHtml.push("/web/guest/plans/-/plans/contestId/");
    		proposalLinkHtml.push(event.contestId);
    		proposalLinkHtml.push("/planId/");
    		proposalLinkHtml.push(event.proposalId);
    		proposalLinkHtml.push("\" class=\"proposalLink\">");
    		proposalLinkHtml.push(event.proposalName);
    		proposalLinkHtml.push("</a>");
    		
    		editor.insertHtml(proposalLinkHtml.join(""));
    	});
    	var editorSectionType = jQuery(editor.element).attr('data-section-type');
    	var supportedSectionTypes = {'PROPOSAL_REFERENCE': true, 'PROPOSAL_LIST_REFERENCE': true, 'PROPOSAL_LIST_TEXT_REFERENCE': true};
    	var addProposalPickerButton = editorSectionType && editorSectionType in supportedSectionTypes;
    	
    	if (addProposalPickerButton) {
    		editor.ui.addButton( 'ProposalLink', {
    			label: 'Proposal link',
    			command: 'proposalLink',
    			toolbar: 'insert'
    		});
    	
    		editor.addCommand( 'proposalLink', new CKEDITOR.command( editor, {
    			exec: function( editor ) {
    				sectionContainer = jQuery(editor.container.$).parents('.addpropbox');
    				sectionId = sectionContainer.attr("data-section-id");
    	    	
    				pickProposal(sectionId);
    			}}));
    	}
    	
    },
    
    
});