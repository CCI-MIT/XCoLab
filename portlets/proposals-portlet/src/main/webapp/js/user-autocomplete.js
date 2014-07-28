
var usersMap;
var userNamesMap;



function initUserAutocomplete(idPostfix) {
    jQuery('#userSelectorInput'+idPostfix).focus(function() {
        jQuery("#please_choose_from_list"+idPostfix).hide();
    });
        
    jQuery('#userSelectorInput'+idPostfix).blur(function() {
    	setTimeout(function() {
    		var recipient = null;
    		var unknownUser = null;
            var screenName = jQuery.trim(jQuery("#userSelectorInput"+idPostfix).val());
            if (screenName in usersMap) {
                recipient = usersMap[screenName];
            }
            else {
                unknownUser = screenName;
            }
    		if (recipient == null) {
    			jQuery("#please_choose_from_list"+idPostfix).show();
    		}
    		if (unknownUser != null) {
    			jQuery("#unknownUsersContainer"+idPostfix+" p").html(unknownUser);
    			jQuery("#unknownUsersContainer"+idPostfix).show();
    		}
    		else {
    			jQuery("#unknownUsersContainer"+idPostfix).hide();
    		}
    		
    	}, 200);
    });

    try {
       $("#userSelectorInput"+idPostfix)
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
             response($.ui.autocomplete.filter(Object.keys(usersMap), request.term));
           },
           focus: function() {
             // prevent value inserted on focus
             return false;
           },
           select: function( event, ui ) {
             var userName = ui.item.value;
               if (!jQuery('.listItem'+idPostfix+'-'+usersMap[userName]).length) {
                   jQuery("#userDistributionInputs" + idPostfix).append(
                           '<li class="listItem' + idPostfix + '-' + usersMap[userName] + '">'
                           + '<label class="percentageInput"><input type="text" name="assignments[' + idPostfix + ']['
                           + usersMap[userName] +
                           ']" class="popupreg_input" /> '+
                               '<a href="/web/guest/member/-/member/userId/'+usersMap[userName]+'">'
                               +'<span class="userId">'
                           + userNamesMap[usersMap[userName]] +
                           '</a></span>' +
                           '<span class="deleteListItem" data-item-id="' + idPostfix + '-' + usersMap[userName] + '">x</span>' +
                           '</label></li>'
                   );
               };
               this.value = "";
               initUserAssignmentInputs();
             return false;
           }
         });
    }
    catch (e) {
    	alert(e);
    }
    jQuery('.messageRecipients'+idPostfix).keypress(function(e) {
    	 var code = (e.keyCode ? e.keyCode : e.which);
    	 if (code == 13) {
    		 // enter pressed ignore
    		 return false;
    	 }
    	 
    });
}

function initUserAssignmentInputs() {
    //delete nodes
    jQuery(".deleteListItem").off('click');
    jQuery(".deleteListItem").on('click', function() {
        var id = jQuery(this).attr("data-item-id");
        jQuery(".listItem"+id).remove();
    });

    jQuery(".userDistributionInputs input").off('input');
    jQuery(".userDistributionInputs input").off('focus');
    jQuery(".userDistributionInputs input").on('focus', function() {
        if (this.value == 0) {
            this.value = "";
        }
    });
    jQuery(".userDistributionInputs input").on('input', function() {
        jQuery(".error", jQuery(this).closest(".userDistributionInputs")).html("");
        //filter non-numeric values
        this.value = this.value.replace(/[^0-9]/g, '');
    });

}
function areAllInputsValid() {
    var valid = true;
    jQuery(".userDistributionInputs").each(function() {
        var ul = jQuery(this);
        jQuery(".error", ul).html("");
        var sum = 0;
        var atLeastEntry = false;
        jQuery("input", ul).each(function() {
            sum += new Number(this.value);
            atLeastEntry = true;
        });
        if (atLeastEntry && sum != 100) {
            jQuery(".error", ul).html("Please make sure that the percentages sum up to exactly 100% (currently assigned: "+sum+"%).");
            valid = false;
        }
    });
    return valid;
}

jQuery(function() {
   jQuery("#submitPointsForm").click(function() {
       if (areAllInputsValid()) {
        jQuery(this).closest('form').submit();
       }
   })
});