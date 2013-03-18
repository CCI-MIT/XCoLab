
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

function initShowMessage() {
    jQuery(".showContent").unbind("click");
    jQuery(".showContent").click(function() {
    	showMessageByElem(this);
    });
}



function initSendMessageForm(users, usersMapParam, preFill) {
    usersMap = usersMapParam;
    
    jQuery('#userSelectorInput').focus(function() {
        jQuery("#please_choose_from_list").hide();
    });
        
    jQuery('#userSelectorInput').blur(function() {
    	var thiz = this;
    	setTimeout(function() {
    		//alert('robimy walidacje!');
    		var receipients = [];
    		jQuery(".as-selections li").each(function() {
    			receipients.push(usersMap[jQuery(this).text().substring(1)]);
    		});
    		if (receipients.length > 1) {
    			// valid
    		}
    		else {
    			jQuery("#please_choose_from_list").show();
    		}
    		
    		jQuery(thiz).val(""); 
    		
    		jQuery(".as-list .as-result-item").remove();
    		//jQuery("#userSelectorInput").show();
    	}, 200);
    });

    try {
    	if (preFill != null) {
    		var input = jQuery("#userSelectorInput").autoSuggest(users, {selectedItemProp: "username", searchObjProps: "username", startText: 'Begin typing for a list', preFill: preFill});
    	}
    	else {
    		var input = jQuery("#userSelectorInput").autoSuggest(users, {selectedItemProp: "username", searchObjProps: "username", startText: 'Begin typing for a list'});
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
    jQuery(".as-selections li").each(function() {
        receipients.push(usersMap[jQuery(this).text().substring(1)]);
    });
    receipients.sort();
    jQuery(".messageReceipientsInput").val(receipients);
}

function sendMessage() {
    if (jQuery(".composeMessageForm").valid() && receipientsValid()) {
    	jQuery("#ComposeMessage").block();
        jQuery(".sendMessageLink").click();
    }
}

function receipientsValid() {
    updateReceipients();
    jQuery(".messageReceipientsError").remove();
    //alert(jQuery(".messageReceipientsInput").val());
    if (jQuery.trim(jQuery(".messageReceipientsInput").val()) == '') {
        jQuery(".messageReceipientsInput").after("<label class='error messageReceipientsError'>This field is required.</label>");
        return false;
    }
    return true;
}

jQuery(document).ready(function() {
    initShowMessage();
});
        