
var usersMap;

function showMessageByElem(elem, highlight) {
    var link = jQuery(elem);
    var contentRow;
    if (! link.hasClass('processed')) {
        var content = link.parent().find(".msgContent");
        // append content after current row
        contentRow = jQuery("<tr class='contentRow hidden'><td colspan='4'>" + content.html() + "</td></tr>");
        link.parent().parent().after(contentRow); 
        contentRow.show();
        
        link.addClass("processed");
    }
    else {
        contentRow = link.parent().parent().next();
        if (contentRow.is(":visible")) {
            contentRow.hide();
        }
        else {
            contentRow.show();
        }
    
    }	
    if (highlight) {
        contentRow.effect("highlight", {}, 3000);
        link.parent().parent().effect("highlight", {}, 3000);
    }
}

function showMessageById(id) {
	showMessageByElem(document.getElementById(id), true);
}


function initSendMessageForm(users, usersMapParam, preFill) {
    usersMap = usersMapParam;
    
    jQuery('#userSelectorInput').focus(function() {
        jQuery("#please_choose_from_list").hide();
    });
        
    jQuery('#userSelectorInput').blur(function() {
    	var thiz = this;
    	setTimeout(function() {
    		var receipients = [];
    		var unknownUsers = [];
    		jQuery(jQuery("#userSelectorInput").val().split(",")).each(function(idx, str) {
    			var screenName = jQuery.trim(str);
    			if (screenName in usersMap) {
    				receipients.push(usersMap[jQuery.trim(str)]);
    			}
    			else {
    				unknownUsers.push(screenName);
    			}
    		});
    		if (receipients.length > 1) {
    			// valid
    		}
    		else {
    			jQuery("#please_choose_from_list").show();
    		}
    		if (unknownUsers.length >= 1) {
    			var html = [];
    			jQuery(unknownUsers).each(function(idx, userName) {
    				html.push("<li>");
    				html.push(userName);
    				html.push("</li>");
    			});
    			jQuery("#unknownUsersContainer ul").html(html.join(""));
    			jQuery("#unknownUsersContainer").show();
    		}
    		else {
    			jQuery("#unknownUsersContainer").hide();
    		}
    		
    	}, 200);
    });

    try {
    	/*
    	if (preFill != null) {
    		var input = jQuery("#userSelectorInput").autoSuggest(users, {selectedItemProp: "username", searchObjProps: "username", startText: 'Begin typing for a list', preFill: preFill});
    	}
    	else {
    		var input = jQuery("#userSelectorInput").autoSuggest(users, {selectedItemProp: "username", searchObjProps: "username", startText: 'Begin typing for a list'});
    	}*/

    	                   function split( val ) {
    	                     return val.split( /,\s*/ );
    	                   }
    	                   function extractLast( term ) {
    	                     return split( term ).pop();
    	                   }
    	                
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
    	                         // delegate back to autocomplete, but extract the last term
    	                         response( $.ui.autocomplete.filter(
    	                           userNames, extractLast( request.term ) ) );
    	                       },
    	                       focus: function() {
    	                         // prevent value inserted on focus
    	                         return false;
    	                       },
    	                       select: function( event, ui ) {
    	                         var terms = split( this.value );
    	                         // remove the current input
    	                         terms.pop();
    	                         // add the selected item
    	                         terms.push( ui.item.value );
    	                         // add placeholder to get the comma-and-space at the end
    	                         terms.push( "" );
    	                         this.value = terms.join( ", " );
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
    jQuery(".composeMessageForm").validate();
    
    jQuery('.messageReceipients').keypress(function(e) {
    	 var code = (e.keyCode ? e.keyCode : e.which);
    	 if (code == 13) {
    		 // enter pressed ignore
    		 return false;
    	 }
    	 
    });
    
}

function updateReceipients() {
    var receipients = [];
	jQuery(jQuery("#userSelectorInput").val().split(",")).each(function(idx, str) {
		var userName = jQuery.trim(str);
		if (userName.length > 0 && userName in usersMap) {
			receipients.push(usersMap[userName]);
		}
	});
    
    receipients.sort();
    jQuery(".messageReceipientsInput").val(receipients);
}

function sendMessage() {
    if (jQuery(".composeMessageForm").valid() && receipientsValid()) {
    	jQuery("#ComposeMessage").block();
    	jQuery(".iceMsgsError").remove();
        jQuery(".sendMessageLink").click();
    }
}

function receipientsValid() {
    updateReceipients();
    jQuery(".messageReceipientsError").remove();
    if (jQuery.trim(jQuery(".messageReceipientsInput").val()) == '') {
        jQuery(".messageReceipientsInput").after("<label class='error messageReceipientsError'>This field is required.</label>");
        return false;
    }
    return true;
}
function unblockForm() {
	jQuery("#ComposeMessage").unblock();
}

        