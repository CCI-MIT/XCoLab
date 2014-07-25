
var usersMap;



function initUserAutocomplete(usersMapParam, preFill) {
    usersMap = usersMapParam;
    
    jQuery('#userSelectorInput').focus(function() {
        jQuery("#please_choose_from_list").hide();
    });
        
    jQuery('#userSelectorInput').blur(function() {
    	setTimeout(function() {
    		var recipient = null;
    		var unknownUser = null;
            var screenName = jQuery.trim(jQuery("#userSelectorInput").val());
            if (screenName in usersMap) {
                recipient = usersMap[screenName];
            }
            else {
                unknownUser = screenName;
            }
    		if (recipient == null) {
    			jQuery("#please_choose_from_list").show();
    		}
    		if (unknownUser != null) {
    			jQuery("#unknownUsersContainer p").html(unknownUser);
    			jQuery("#unknownUsersContainer").show();
    		}
    		else {
    			jQuery("#unknownUsersContainer").hide();
    		}
    		
    	}, 200);
    });

    try {
       $( "#userSelectorInput" )
         // don't navigate away from the field on tab when selecting an item
         .bind( "keydown", function( event ) {
           if ( event.keyCode === $.ui.keyCode.TAB &&
               $( this ).data( "ui-autocomplete" ).menu.active ) {
             event.preventDefault();
           }
         })
         .autocomplete({
           minLength: 0,
           source: function( request, response ) {
             // delegate back to autocomplete
             response($.ui.autocomplete.filter(Object.keys(usersMapParam), request.term));
           },
           focus: function() {
             // prevent value inserted on focus
             return false;
           },
           select: function( event, ui ) {
             this.value = ui.item.value;
             return false;
           }
         });

       if (preFill) {
           jQuery("#userSelectorInput").val(preFill);
       }
    }
    catch (e) {
    	alert(e);
    }
    jQuery('.messageRecipients').keypress(function(e) {
    	 var code = (e.keyCode ? e.keyCode : e.which);
    	 if (code == 13) {
    		 // enter pressed ignore
    		 return false;
    	 }
    	 
    });
    
}

function updateRecipient() {
    var userName = jQuery.trim(jQuery("#userSelectorInput").val());
    if (userName.length > 0 && userName in usersMap) {
        recipient = usersMap[userName];
    }
    jQuery(".messageRecipientsInput").val(recipient);
}


function recipientValid() {
    updateRecipient();
    jQuery(".messageRecipientsError").remove();
    if (jQuery.trim(jQuery(".messageRecipientsInput").val()) == '') {
        jQuery(".messageRecipientsInput").after("<label class='error messageRecipientsError'>This field is required.</label>");
        return false;
    }
    return true;
}