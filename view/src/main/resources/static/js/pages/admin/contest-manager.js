function initializeDropDowns() {
    jQuery("select").each(function() {
        console.log("initializeDropDowns 2");
        if (jQuery(this).attr("id") == "changeElementSelect") {
            return;
        }
        eventsToBind = {
            change: function(event) {
                markEditorDirty(jQuery(jQuery("input[type='text'], textarea")[0]));
            }
        };
        jQuery(this).bind(eventsToBind);
    });
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
    console.debug("replaceInputDataByTagName -> tagName", tagName);
    var sectionFormInputs = newSectionElement.getElementsByTagName(tagName);
    for (var i = 0; i < sectionFormInputs.length; i++) {
        console.log(" sectionFormInputs[i].id", sectionFormInputs[i].id);
        console.log(" newSectionInputData", newSectionInputData);

        var sectionInputData = newSectionInputData[sectionFormInputs[i].id];
        console.log(" sectionFormInputs[i]", sectionFormInputs[i]);
        console.log(" sectionFormInputs[i].id", sectionFormInputs[i].id);
        console.log(" sectionInputData", typeof(sectionInputData));
        if(typeof(sectionInputData) != 'undefined') {
            sectionFormInputs[i].id = sectionInputData.id;
            sectionFormInputs[i].name = sectionInputData.name;
        }
    }
}

function replaceSectionFormIds(newSectionElement, newSectionInputData, newSectionId){
    newSectionElement.style.display = "";

    if(newSectionId != undefined) {
        console.log("newSectionId", newSectionId);
        console.log("newSectionElement", newSectionElement);
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

    initializeDropDowns();
    resizeAllTextareas();
});