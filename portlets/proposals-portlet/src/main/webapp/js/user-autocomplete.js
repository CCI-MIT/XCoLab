
var usersMap;
var userNamesMap;



function initUserAutocomplete(idPostfix, canEdit) {
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
                   //show container
                   jQuery("#userDistributionTable" + idPostfix).css("display", "block");
                   //add input
                   var htmlTemplate =
                       '<li class="listItem' + idPostfix + '-' + usersMap[userName] + '">'
                       + '<label class="percentageInput">';
                   if (canEdit) {
                   htmlTemplate +=
                       '<input type="text" name="assignments[' + idPostfix + ']['
                       + usersMap[userName] +
                       ']" class="popupreg_input" value="0.0" /> ';
                   } else {
                       htmlTemplate += '<span class="input">0.0</span>';
                   }
                   htmlTemplate +=
                       '<a href="/web/guest/member/-/member/userId/'+usersMap[userName]+'">'
                       +'<span class="userId">'
                       + userNamesMap[usersMap[userName]] +
                       '</a></span>';
                   if (canEdit) {
                       htmlTemplate +=
                           '<span class="deleteListItem" data-point-id="' + idPostfix + '" data-item-id="' + idPostfix + '-' + usersMap[userName] + '">x</span>' +
                           '</label></li>'
                       ;
                   }
                   jQuery("#userDistributionInputs" + idPostfix).append(htmlTemplate);
                   distributeEvenly(jQuery("#userDistributionInputs" + idPostfix));
                   recalculateTotalPercentages();
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

function formatInputValue(doNotRound) {
    //filter non-numeric values
    this.value = this.value.replace(/[^0-9\.]/g, '');
    //only allow one decimal separator
    this.value = this.value.replace(/\.(.*\.)/g, '$1');
    if (doNotRound !== true) {
        //round to exactly 2 decimal places
        this.value = (+this.value).toFixed(2);
    }
}

function initUserAssignmentInputs() {
    //delete nodes
    jQuery(".deleteListItem").off('click');
    jQuery(".deleteListItem").on('click', function() {
        var id = jQuery(this).attr("data-item-id");
        jQuery(".listItem"+id).remove();

        //hide container if empty
        var pointId = jQuery(this).attr("data-point-id");

        setTimeout(function() {
            if (jQuery("#userDistributionInputs"+pointId+" .deleteListItem").length == 0) {
                jQuery("#userDistributionTable"+pointId).css("display", "none");
            }
            distributeEvenly(jQuery("#userDistributionInputs" + pointId));
            recalculateTotalPercentages();
        }, 50);
    });

    jQuery(".userDistributionInputs input").off('input');
    jQuery(".userDistributionInputs input").off('focus');
    jQuery(".userDistributionInputs input").off('blur');

    jQuery(".userDistributionInputs input").each(formatInputValue);
    jQuery(".userDistributionInputs input").on('blur', formatInputValue);
    jQuery(".userDistributionInputs input").on('input', function() {
        var start = this.selectionStart,
            end = this.selectionEnd;
        var originalLen = this.value.length;

        formatInputValue.bind(this, true)();

        jQuery(this).attr("data-changed-by-user", "true");

        this.setSelectionRange(start+(this.value.length-originalLen), end+(this.value.length-originalLen));

        var listContainer = jQuery(this).closest(".userDistributionInputs");
        //remove error on change, if any
        jQuery(".error", listContainer).html("");
        recalculateTotalPercentages();
    });

}

function distributeEvenly(listContainer) {
    var pointType = listContainer.attr("data-pointType");
    var containsCustomValue = false;
    var inputs = jQuery("input", listContainer);
    var inputsReadOnly = jQuery("span.input", listContainer);
    inputs.each(function() {
        if (jQuery(this).attr("data-changed-by-user") == "true") {
            containsCustomValue = true;
        }
    });
    if (!containsCustomValue) {
        var avg = ((listContainer.attr("data-percentage")/100)/Math.max(inputs.length, inputsReadOnly.length))*100;
            inputs.each(function() {
                this.value = avg.toFixed(2);
            });
            inputsReadOnly.each(function() {
                $(this).attr("data-value", avg.toFixed(2));
                $(this).html(avg.toFixed(2));
            });
    }
}

function recalculateTotalPercentages() {
    jQuery(".userDistributionInputs").each(function() {
        var listContainer = jQuery(this);
        var pointType = listContainer.attr("data-pointType");
        var totalNode = jQuery("#userDistributionTotal"+pointType);
        var sum = 0;
        jQuery("input", listContainer).each(function() {
            sum += Number(this.value);
        });
        jQuery("span.input", listContainer).each(function() {
            sum += Number($(this).attr('data-value'));
        });
        totalNode.html(sum.toFixed(2)+"%");
    });
}


function areAllInputsValid() {
    var valid = true;
    jQuery(".userDistributionInputs").each(function() {
        var percentage = Number($(this).attr("data-percentage"));
        var ul = jQuery(this);
        jQuery(".error", ul).html("");
        var sum = 0;
        var atLeastEntry = false;
        jQuery("input", ul).each(function() {
            formatInputValue.bind(this)();
            sum += Number(this.value);
            atLeastEntry = true;
        });
        //round
        sum = (+sum.toFixed(2));
        if (atLeastEntry && sum != percentage) {
            jQuery(".error", ul).html("Please make sure that the percentages sum up to exactly "+percentage.toFixed(2)+"% (currently assigned: "+sum.toFixed(2)+"%).");
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
    });
    recalculateTotalPercentages();
});