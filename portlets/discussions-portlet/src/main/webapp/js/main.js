function initializeTextEditors() {
    console.log("initializeTextEditors");

    jQuery("input[type='text'], textarea").each(function() {
        if (jQuery(this).hasClass('rteInitialized') || jQuery(this).parent().parent().attr('class') == "login_popup_box") {
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
        } else {
            thiz.attr({validateLength: false});
        }

        if (thiz.hasClass("ckeditor_placeholder")) {

            var editor = CKEDITOR.replace(thiz.attr("id"));

            thiz.get(0)["ckeditor"] = editor;
            editor.updatedCharCount = false;

            function updateEditorCharCount() {
                try{
                    if (editor == null) return;

                    if (editor &&  editor.document && editor.document['$'] && (editor.checkDirty() || editor.updatedCharCount)) {
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
                    console.log("asdfasdfasdfasdfasdfasfd");
                    markEditorDirty(thiz);
                }
            };
            thiz.bind(eventsToBind);
        }
        jQuery(this).addClass('rteInitialized');
    });
}

function initializeDropDowns() {
    jQuery("select").each(function() {
        eventsToBind = {
            change: function(event) {
                markEditorDirty(jQuery(jQuery("input[type='text'], textarea")[0]));
            }
        };
        jQuery(this).bind(eventsToBind);
    });
}

function markEditorDirty(editor) {
    editor.addClass('editorDirty');
}

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