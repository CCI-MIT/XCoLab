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
            return editor.document['$'].body.textContent.length;
            
        }
        if (editor.document['$'].body.innerText) {
            return editor.document['$'].body.innerText.length;
        }
    }
    return input.val().replace(/&lt;[^&gt;]*&gt;/g, "").replace(/\s+/g, " ").length;
    
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
    }
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
                }
        };
		if (thiz.hasClass("rte")) {
        
			var editor = CKEDITOR.replace(thiz.attr("id"));
        
			thiz.get(0)["ckeditor"] = editor;
			editor.updatedCharCount = false;
       
			function updateEditorCharCount() {
				try{
					if (editor == null) return;
            	
					if (editor &&  editor.document && editor.document['$'] && (editor.checkDirty() || !editor.updatedCharCount)) {
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
			thiz.bind(eventsToBind);
		}
		jQuery(this).addClass('rteInitialized');
	});
    
    
    
};

jQuery(function() {
	jQuery(".addpropform .helpTrigger").click(function() {
		var trigger = jQuery(this);
		trigger.parent().parent().find(".addprophelp").slideToggle("fast");
	});
	
	initializeTextEditors();
});


