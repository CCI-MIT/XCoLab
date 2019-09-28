// CKEditor incompatibility with jquery and bootstrap
$.fn.modal.Constructor.prototype.enforceFocus = function() {
    modal_this = this;
    $(document).on('focusin.modal', function (e) {
        console.log("Modal window opened.")
        if (modal_this.$element[0] !== e.target && !modal_this.$element.has(e.target).length
                && !$(e.target.parentNode).hasClass('cke_dialog_ui_input_select')
                && $(e.target.parentNode).hasClass('cke_contents cke_reset')
                && !$(e.target.parentNode).hasClass('cke_dialog_ui_input_text')) {
            modal_this.$element.focus()
        }
    })
};

function markEditorDirty(editor) {
    editor.addClass('editorDirty');
}

function countCharacters(input, editor) {
    if (editor) {
        if (editor == null || editor.document == null) return 0;
        var text = (editor.document['$'].body.textContent || editor.document['$'].body.innerText);
        // remove zero width spaces
        text = text.replace(/\u200b/g, '');
        text = jQuery.trim(text);
        return text.length;
    }
    var numberOfLines = input.val().split(/\r\n|\r|\n/).length - 1 ;//java will count \n as \r\n
    input.val().replace(/&lt;[^&gt;]*&gt;/g, "").replace(/\s+/g, " ").length;
    return jQuery.trim(input.val().length + numberOfLines);
}

function updateCharacterCounter(input, editor) {

    var max = input.attr('data-characterLimit');
    if (input && input.limitCharacterCounter) {
        var count = countCharacters(input, editor);
        if (count > 1 * max) {
            input.limitCharacterCounter.parent().addClass('overflow');
            input.addClass('invalid');
        }
        else {
            input.limitCharacterCounter.parent().removeClass('overflow');
            input.removeClass('invalid');
        }
        input.limitCharacterCounter.text(count);

        // needed for copy from base proposal - hides button when there is content
        var parent = input.parents('.c-Box');
        if (count > 0) {
            parent.removeClass('empty').addClass('notempty');
        }
        else {
            parent.removeClass('notempty').addClass('empty');
        }
    }
}

function initializeTextEditors() {
    jQuery("input[type='text'], textarea").each(function() {
        var element = this;
        var $element = jQuery(this);

        if ($element.hasClass('rte-editorInitialized')) {
            return;
        }

        if($element.attr("data-section-placeholder")){
            if($element.val()== "") {
                $element.val($element.attr("data-section-placeholder"));
            }
        }

        var limitCharactersMax = $element.parent().find(".limit_charactersMax");
        var limitCharacterCount = $element.parent().find(".limit_characterCount");

        var countCharacters = limitCharactersMax.length > 0;
        if (countCharacters) {
            $element.attr({'data-characterLimit': limitCharactersMax.text(), 'data-validateLength': true});
            $element.limitCharacterCounter = limitCharacterCount;
            updateCharacterCounter($element);
        } else {
            $element.attr({'data-validateLength': false});
        }

        if ($element.hasClass("rte-editorPlaceholder")) {

            var editor = CKEDITOR.replace($element.attr("id"), { customConfig: '/js/lib/newckeditorplugins/configProposal.js'});

            $element.get(0)["ckeditor"] = editor;
            editor.updatedCharCount = false;

            function updateEditorCharCount() {
                try {
                    if (editor == null) return;

                    if (editor && editor.document && editor.document['$'] && (editor.checkDirty() || editor.updatedCharCount)) {
                        markEditorDirty($element);
                        updateCharacterCounter($element, editor);
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
            // if (! jQuery.browser.ie || jQuery.browser.version.number >= 9) {
            updateEditorCharCount();
            // }

            editor.on("blur", function() {
                updateCharacterCounter($element, editor);
            });

            // initiate char counters
            setTimeout(function() { updateCharacterCounter($element, editor); }, 2000);
        } else {
            eventsToBind = {
                keypress: function(event) {
                    if ($element.attr('data-validateLength') && $element.limitCharacterCounter) {
                        updateCharacterCounter($element);
                    }
                },
                keyup: function(event) {
                    if ($element.attr('data-validateLength') && $element.limitCharacterCounter) {
                        updateCharacterCounter($element);
                    }
                },
                change: function(event) {
                    markEditorDirty($element);
                }
            };
            $element.bind(eventsToBind);
        }
        jQuery(this).addClass('rte-editorInitialized');
    });
}

jQuery(function() {
    CKEDITOR.plugins.addExternal( 'proposalLink',
            '/js/lib/newckeditorplugins/proposalLink/plugin.js' );
    initializeTextEditors();
});
