//TODO: duplicate in proposals.js (proposals-portlet)
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
}

jQuery(document).ready(function() {
   initializeTextEditors();
});