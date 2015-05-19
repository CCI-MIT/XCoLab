
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

        if (thiz.hasClass("ckeditor_placeholder")) {

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
                        event.preventDefault();
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

function shouldAllowMoreCharacters(input) {
    if (typeof(input.attr('validateLength')) == 'undefined' ||
        typeof(input.attr('maxCharacters')) == 'undefined' ||
        input.attr('maxCharacters') + 0 <= 0) {
        return true;
    }
    return input.attr('maxCharacters') > input.val().length;
}

function markEditorDirty(editor) {
    editor.addClass('editorDirty');
}

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

function isJSONavailable(){
    return typeof JSON === 'object' && typeof JSON.parse === 'function';
}
function updatePictureId (uploadImageDivId, imageId){
    jQuery(uploadImageDivId + "Img").attr("src","/image/contest?img_id=" + imageId);
    jQuery(uploadImageDivId + "Id").val(imageId);
}

function updateUploadBtnOffset(uploadWidget,fileUploadInputId) {
    console.log("updateUploadBtnOffset call");
    var container = jQuery(uploadWidget);
    var containerOffset = container.offset();
    jQuery(fileUploadInputId).offset(containerOffset);
}


function autoresize(textarea) {
    textarea.style.height = '0px';     //Reset height, so that it not only grows but also shrinks
    textarea.style.height = (textarea.scrollHeight-10) + 'px';    //Set new height
}

function resizeAllTextareas(){
    jQuery("textarea").each(function () {
        this.style.height = (this.scrollHeight-10)+'px';
    });
}

function extractInputElementsInNode(node){
    var sectionElementNames =[];

    [].forEach.call(node.getElementsByTagName('input'), function(element) {
        sectionElementNames.push(element.getAttribute("data-form-name"));
    });

    [].forEach.call(node.getElementsByTagName('textarea'), function(element) {
        sectionElementNames.push(element.getAttribute("data-form-name"));
    });

    [].forEach.call(node.getElementsByTagName('select'), function(element) {
        sectionElementNames.push(element.getAttribute("data-form-name"));
    });

    return sectionElementNames;
}

function createFormInputsIdReplacements(oldSectionElementId, newSectionElementId, sectionElementNames, sectionPrefix){
    var formInputData = [];
    for (var i = 0; i < sectionElementNames.length; i++) {
        var sectionDummyInputId = sectionPrefix + oldSectionElementId + "." + sectionElementNames[i];
        var sectionInputId = sectionPrefix + newSectionElementId + "." + sectionElementNames[i];
        var sectionInputName = sectionPrefix + "[" + newSectionElementId + "]." + sectionElementNames[i];
        formInputData[sectionDummyInputId] = {id: sectionInputId, name: sectionInputName};
    }
    return formInputData;
}

function replaceInputDataByTagName(newSectionElement, newSectionInputData, tagName){
    var sectionFormInputs = newSectionElement.getElementsByTagName(tagName);
    for (var i = 0; i < sectionFormInputs.length; i++) {
        var sectionInputData = newSectionInputData[sectionFormInputs[i].id];
        console.log(" sectionFormInputs[i].id", sectionFormInputs[i]);
        sectionFormInputs[i].id = sectionInputData.id;
        sectionFormInputs[i].name = sectionInputData.name;
    }
}

function replaceSectionFormIds(newSectionElement, newSectionInputData, newSectionId){
    newSectionElement.style.display = "";

    if(newSectionId != undefined) {
        console.log("newSectionId", newSectionId);
        newSectionElement.id = newSectionId;
        newSectionElement.setAttribute("data-section-id", newSectionId);
    }
    replaceInputDataByTagName(newSectionElement, newSectionInputData, 'input');
    replaceInputDataByTagName(newSectionElement, newSectionInputData, 'select');
    replaceInputDataByTagName(newSectionElement, newSectionInputData, 'textarea');
}


function getClosest(el, tag) {
    // this is necessary since nodeName is always in upper case
    tag = tag.toUpperCase();
    do {
        if (el.nodeName === tag) {
            // tag name is found! let's return it. :)
            return el;
        }
    } while (el = el.parentNode);

    // not found :(
    return null;
}

function filter(className, element){
    return element.getElementsByClassName(className)[0] != undefined;
}

jQuery(function() {

    jQuery(".addpropform .helpTrigger").click(function() {
        var trigger = jQuery(this);
        trigger.parent().parent().find(".addprophelp").slideToggle("fast");
    });

    initializeTextEditors();
    resizeAllTextareas();
});