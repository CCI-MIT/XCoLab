/**
 * Sets the cookie for contests page (GRID/LIST) 
 * 
 * @param viewType type of the view (GRID/LIST)
 */
function setContestsViewTypeCookie(viewType) {
	jQuery.cookie("cc_contests_viewType", null, {expires: -1});
	jQuery.cookie("cc_contests_viewType", viewType, {expires: 365, path: '/'});
}

/**
 * 
 * 
 */
function filterContests() {
	jQuery("#filterContestsForm").submit();
}

jQuery(function() {
	jQuery("#contestFilterInput").change(filterContests).keypress(function (e) {
		if (e.keyCode == 13) {
			filterContests();
			return false;
		}
	});
	jQuery("#contestsFilterClear").click(function() {
		jQuery("#contestFilterInput").val("");
		jQuery("#filterContestsForm").submit();
	});
});

/**
 * Counts characters 
 * @param input
 * @param editor
 * @returns
 */
function countCharacters(input, editor) {
    if (editor) {
    	if (editor == null || editor.document == null) return 0;
        if (editor.document['$'].body.textContent) {
            return jQuery.trim(editor.document['$'].body.textContent).length;
            
        }
        if (editor.document['$'].body.innerText) {
            return jQuery.trim(editor.document['$'].body.innerText).length;
        }
    }
    return jQuery.trim(input.val().replace(/&lt;[^&gt;]*&gt;/g, "").replace(/\s+/g, " ").length);
    
}

/**
 * Returns true if input is allowed to contain more characters
 * @param input 
 * @returns
 */
function shouldAllowMoreCharacters(input) {
    if (typeof(input.attr('validateLength')) == 'undefined' || 
    		typeof(input.attr('maxCharacters')) == 'undefined' || 
    		input.attr('maxCharacters') + 0 <= 0) {
        return true;
    }
    return input.attr('maxCharacters') > input.val().length;
}

/**
 * Updates character counter for given input
 * @param input
 * @param editor
 * @returns
 */
function updateCharacterCounter(input, editor) {
	var parent = input.parents('.addpropbox');
    var elem = input.get(0);
    var max = input.attr('maxCharacters');
    if (elem && elem.limitCharacterCounter) {
        var count = countCharacters(input, editor);
        if (count > 1 * max) {
            elem.limitCharacterCounter.parent().addClass('overflow');
            input.addClass('invalid');
        }
        else {
            elem.limitCharacterCounter.parent().removeClass('overflow');
            input.removeClass('invalid');
        }
        elem.limitCharacterCounter.text(count);
        if (count > 0) {
        	parent.removeClass('empty').addClass('notempty');
        }
        else {
        	parent.removeClass('notempty').addClass('empty');
        }
    }
}

function markEditorDirty(editor) {
	editor.addClass('editorDirty');
}

function initializeTextEditors() {
	jQuery("input[type='text'], textarea").each(function() {
		if (jQuery(this).hasClass('rteInitialized')) {
			return;
		}
		
		var tmp = this;
		var thiz = jQuery(this);
    
		var limitCharactersMax = thiz.parent().find(".limit_charactersMax");
		var limitCharacterCount = thiz.parent().find(".limit_characterCount");
    
    
		if (limitCharactersMax.length > 0) {
			thiz.attr({maxCharacters: limitCharactersMax.text(), validateLength: true});
			this.limitCharacterCounter = limitCharacterCount;
			updateCharacterCounter(thiz);
		}
		else {
			thiz.attr({validateLength: false});
		}
		
		if (thiz.hasClass("rte")) {
        
			var editor = CKEDITOR.replace(thiz.attr("id"));
        
			thiz.get(0)["ckeditor"] = editor;
			editor.updatedCharCount = false;
       
			function updateEditorCharCount() {
				try{
					if (editor == null) return;
            	
					if (editor &&  editor.document && editor.document['$'] && (editor.checkDirty() || !editor.updatedCharCount)) {
						markEditorDirty(thiz);
						updateCharacterCounter(thiz, editor);
						editor.updatedCharCount = true;
						editor.resetDirty();
					}
            
					setTimeout(updateEditorCharCount, 1000);
				} catch (e) {
					if (typeof(console) != 'undefined' && typeof(console.log) != 'undefined') {
						console.log(e);
					}
				}
			}
			if (! jQuery.browser.ie || jQuery.browser.version.number >= 9) {
				updateEditorCharCount();
			}
        
			editor.on("blur", function() {
				updateCharacterCounter(thiz, editor);
			});
        
			// initiate char counters
			setTimeout(function() { updateCharacterCounter(thiz, editor); }, 2000);
        }
		else {
			eventsToBind = {
					keypress: function(event) {
	                    if (! shouldAllowMoreCharacters(thiz)) {
	                        //event.preventDefault();
	                    }
	                    if (thiz.attr('validateLength') && tmp.limitCharacterCounter) {
	                        updateCharacterCounter(thiz);
	                    }
	                },
	                keyup: function(event) {
	                    if (thiz.attr('validateLength') && tmp.limitCharacterCounter) {
	                        updateCharacterCounter(thiz);
	                    }
	                },
	                change: function(event) {
	                	markEditorDirty(thiz);
	                }
	        };
			thiz.bind(eventsToBind);
		}
		jQuery(this).addClass('rteInitialized');
	});
    
    
    
};

function validatePlanEditForm() {
    for (var ckInstanceId in CKEDITOR.instances) {
        if (document.getElementById(ckInstanceId)) {
        	var ckInstance = CKEDITOR.instances[ckInstanceId];
            ckInstance.element['$'].value = ckInstance.getData();
        }
    }
    var invalidFieldsList = jQuery("#invalidFieldsList");
    invalidFieldsList.html("");
    
    var isValid = true;
    jQuery("input[type='text'].invalid, textarea.invalid").each(function() {
        var x = jQuery(this).parents(".addpropbox");
        invalidFieldsList.append("<li>" + jQuery(this).parents(".addpropbox").find("strong").text() + "</li>");
        
        isValid = false;   
    });
    if (!isValid) {
        jQuery("#invalidFieldsPopupContainer").show();
    }
    for (var ckInstanceId in CKEDITOR.instances) {
        if (document.getElementById(ckInstanceId)) {
        	// remove all instances
        	//ckInstance.destroy();
        }
    }
    //delete CKEDITOR;
    
    return isValid;
};

function enableDirtyCheck() {
	window.oldOnBeforeUnload = window.onbeforeunload;

	window.onbeforeunload = function() {
		if (jQuery(".editorDirty").length > 0) {
			return 'You have modified this page but have not saved your changes.';
		}
		return null;
	};
}

function disableDirtyCheck() {
	if ('oldOnBeforeUnload' in window) {
		window.onbeforeunload = window.oldOnBeforeUnload;
	}
	delete window.onbeforeunload;
}


jQuery(function() {
	jQuery(".addpropform .helpTrigger").click(function() {
		var trigger = jQuery(this);
		trigger.parent().parent().find(".addprophelp").slideToggle("fast");
	});
	
	// tooltips
	initTooltips();
	
	initializeTextEditors();
});



/* Request membership form logic */
function clearContents(element) {
    element.value = '';
};

function requestMembership() {
    $('#requestComment').slideDown('slow');
    $('.prop-butt-popover:first').css('background', 'url(/climatecolab-theme/images/search-bg.png)');
    $('#requestButtons').empty();
    $('#requestButtons').append('<div class="blue-button"><a href="javascript:;" class="requestMembershipSubmitFormButton" onclick="hideRequestForm(true);">Cancel</a></div>');
    $('#requestButtons').append('<div class="blue-button"><a href="javascript:;" class="requestMembershipSubmitFormButton" onclick="$(\'#requestMembershipForm\').submit();hideRequestForm(false);">Send</a></div>');
}
function hideRequestForm(animate) {
    var speed = animate ? 600 : 1;
    $('#requestComment').slideUp('slow', function () {
        $('.prop-butt-popover:first').css('background', 'none');
        $('#requestButtons').empty();
        $('#requestButtons').append('<div class="blue-button" style="display:block;"><a href="javascript:;" class="requestMembershipSubmitButton" onclick="if(deferUntilLogin()) requestMembership();">Request membership</a></div>');
    });
}
/* End of request membership form logic */

/* Membership invite logic */
function inviteMember() {
    $('#invite-comment').slideDown('slow');
    $('#invite-recipient').slideDown('slow');
    $('.prop-butt-popover:first').css('background', 'url(/climatecolab-theme/images/search-bg.png)');
    $('#requestButtons').empty();
    $('#requestButtons').append('<div class="blue-button"><a href="javascript:;" class="requestMembershipSubmitFormButton" onclick="hideInviteForm(true);">Cancel</a></div>');
    $('#requestButtons').append('<div class="blue-button"><a href="javascript:;" class="requestMembershipSubmitFormButton" onclick="$(\'#requestInviteForm\').submit();hideInviteForm(false);">Send</a></div>');
}

function hideInviteForm(animate) {
    var speed = animate ? 600 : 1;
    $('#invite-comment').slideUp('slow', function () {
        $('.prop-butt-popover:first').css('background', 'none');
        $('#requestButtons').empty();
        $('#requestButtons').append('<div class="blue-button" style="display:block;"><a href="javascript:;" class="requestMembershipSubmitButton" onclick="if(deferUntilLogin()) inviteMember();">Invite team member</a></div>');
    });
    $('#invite-recipient').slideUp('slow');

function validateRecipients() {
    var input = $("#recipient-input").val();
    var list = split(input);
    console.log("list: " + list);

    $.each(list, function(k, v) {
        console.log("item " + v);
    });
    var json = new Object();
    json.params = list;
    console.log("json: " + JSON.stringify(json));

    $.ajax
    ({
        type: "POST",
        //the url where you want to sent the userName and password to
        url: validationURL,
        dataType: 'json',
        async: false,
        //json object to sent to the authentication url
        data: {screenNames : list},
        success: function (data) {
            if (data.success) {
                $('#send-button').removeAttr('disabled');

            } else {
                var error = "";
                var list = data.message;
                if (list.length > 0) {
                    error += "The following recipients could not be resolved:&lt;ul&gt;";

                    $.each(list, function(k, v) {
                        error += "&lt;li&gt;" + v + "&lt;/li&gt;";
                    });

                    error += "&lt;ul&gt;";
                }

                $('.recipient-error').html(error);
                console.log(error);
                $('#send-button').attr('disabled', 'disabled');
            }
        }
    });
}}

// jQuery autocomplete
$(function() {
    var cache = {};
    $( "#invite-recipient" ).autocomplete({
        minLength: 3,
        source: function( request, response ) {
            var term = request.term;
            if ( term in cache ) {
                response( cache[ term ] );
                return;
            }

            $.getJSON( $('#invite-member-validation-url').text(), request, function( data, status, xhr ) {
                cache[ term ] = data;
                response( data );
            });
        }
    });
    $( "#invite-recipient" ).bind("autocompleteselect", "select", function(event) {

    });

    $("#invite-recipient").focusout(function(event) {
        validateRecipients();
    });
});


