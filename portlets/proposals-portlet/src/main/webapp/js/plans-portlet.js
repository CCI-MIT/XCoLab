function portletPlansModifyMessageBoardsInnerPortlet() {
    // update iframe height to wrap entire document, this prevents from displaying additional scrollbar
    var frame = document.getElementById("portlet_discussion");
    var frameContent = (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
    jQuery(frame).attr("scrolling", "no");
    var contentDocument = jQuery(frameContent);

    // remove browsing categories etc from discussion
    jQuery(frameContent._19_fm1).remove();
    contentDocument.find(".portlet-borderless-bar").remove();
    contentDocument.find(".tabs.ui-tabs").remove();

    // add search box like in the prototype
    searchBoxContainer = contentDocument.find("#_19_keywords2").parent();
    searchBoxContainer.html('<div class="searchInputBox"><input class="text" type="text"/>' +
            '<a class="searchBtn" href="javascript:;" onClick="document._19_fm2.submit()" ><span class="hidden">Search</span></a></div>' +
            '<a class="newThread" href="javascript:;" onClick="iframe_DeferUntilLogin(function () {_19_addMessage(); });"><span class="hidden">Post New Thread</span></a><div class="clear"/>');

    contentDocument.find("th.col-1").addClass("first");
    contentDocument.find("th.col-6").addClass("last");
    contentDocument.find("th.col-7").remove();
    contentDocument.find("th.col-6").attr("colspan", "2");

    contentDocument.find(".breadcrumbs .first").remove();

    var actionTags = contentDocument.find(".lfr-trigger");
    actionTags.find("strong").hide();
    actionTags.append('<img align="middle" alt="action" src="/collaboratorium-theme/images/aciont_icon.png"/><div>Action</div>');


    contentDocument.find(".page-selector").remove();
    contentDocument.find(".delta-selector").remove();
    contentDocument.find(".taglib-search-iterator-page-iterator-top").remove();


    frame.height = frameContent.body.scrollHeight + 50;
    jQuery(frame).css("overflow", "hidden");

    contentDocument.find("#banner").remove();
    jQuery("#portlet_discussion").load(portletPlansModifyMessageBoardsInnerPortlet);
}

var models = [];
var lastselection;
function initModelConfigurationWidget() {
    jQuery("select.availableModels").html("");
    var selected = jQuery("input.model-selected-item");
    jQuery(".model-config").each(function() {
        var idx = models.length;
        var model = {
            id:jQuery(this).find(".id").text(),
            name:jQuery(this).find(".name").text(),
            description:jQuery(this).find(".description").text(),
            link:jQuery(this).find(".link").text()
        };
        jQuery("select.availableModels").append("<option value='" + model.id + "'" + (selected == model.id ? " selected='selected'>" : ">") + model.name + "</option>");
        models.push(model);


    });
    updateSelectedModel(true);


    jQuery("select.availableModels").change(function() {
        updateSelectedModel();

    });

}

/**
 * Updates selected model
 *
 * @param fromfield True if from the hidden field, false if from the selected option
 */
function updateSelectedModel(fromfield) {
    var selected = jQuery("input.model-selected-item").val();
    if (!fromfield) {
        //clear the selection using the old value
        var oldopt = jQuery("select.availableModels option[value='" + selected + "']");
        if (oldopt != null) oldopt.removeAttr("selected");
        var idx = jQuery("select.availableModels").attr("selectedIndex");
        if (models[idx]) selected = models[idx].id;
    } 

    //clear everything just in case
    jQuery("select.availableModels option:selected").each(function(idx, elt) {
            jQuery(this).removeAttr("selected");
        });

    //and select just the one we want
    jQuery("select.availableModels option[value='" + selected + "']").attr("selected", "selected");
    idx = jQuery("select.availableModels").attr("selectedIndex");

    if (idx > -1) {
        jQuery("input.model-selected-item").val(models[idx].id);
        jQuery(".model-name").html(models[idx].name);
        jQuery(".model-description").html(models[idx].description);
        if (models[idx].link != "") {
            jQuery(".model-download-link").html("<a href='" + models[idx].link + "'>Download spreadsheet</a>");
        } else {
            jQuery(".model-download-link").html("");
        }
    } else {
        jQuery("input.model-selected-item").val(-1);
        jQuery(".model-name").html("");
        jQuery(".model-description").html("");
        jQuery(".model-download-link").html("");

    }
    jQuery(".updateModelsSubmit").click();

}

//for the curious... icefaces seems to rebuild the dom
//with cached information when information somewhere in the dom tree has
//changed.  In this case, when a successful submit occurs and we update the
//page, the #model-selection-popup div, which jquery moves to the top level in the
//dom, gets replaced by icefaces.  So, after the first dialog has been opened
//we can no longer use id lookup, and instead hold onto the specific dom element
//where the dialog should be created.

var modelSelectionPopup;

function showModelSelectionPopup() {
    jQuery("#model-selection-popup").dialog("destroy");
    updateSelectedModel(true);
    if (!modelSelectionPopup) {
        jQuery("#model-selection-popup-content").appendTo(jQuery("#model-selection-popup"));
        jQuery("#model-selection-popup").dialog({width: 500, modal: true, height: 250, draggable: false, resizable: false, dialogClass: 'selectModelPopup',title: "Select disaggregation model"});
        modelSelectionPopup = jQuery("#model-selection-popup");
    } else {
        jQuery("#model-selection-popup-content").appendTo(modelSelectionPopup)
        modelSelectionPopup.dialog({width: 500, modal: true, height: 250, draggable: false, resizable: false, dialogClass: 'selectModelPopup',title: "Select disaggregation model"});
    }
}

function hideModelSelectionDialog(submit) {

    jQuery("#model-selection-popup-content").appendTo(jQuery("#model-selection"));
    modelSelectionPopup.dialog("destroy");
    if (submit) {
        jQuery("input.submitModelsSubmit").click();
    } else {
        jQuery("input.cancelModelsSubmit").click();
    }
}


var modelUploadPopup;

function showModelUploadPopup() {
    jQuery("#model-upload-popup").dialog("destroy");
    if (!modelUploadPopup) {
        jQuery("#model-upload-popup-content").appendTo(jQuery("#model-upload-popup"));
        jQuery("#model-upload-popup").dialog({width: 500, modal: true, height: 250, draggable: false, resizable: false, dialogClass: 'selectModelPopup',title: "Upload model"});
        modelUploadPopup = jQuery("#model-upload-popup");
    } else {
        jQuery("#model-upload-popup-content").appendTo(jQuery("#model-upload-popup"));
        modelUploadPopup.dialog({width: 500, modal: true, height: 250, draggable: false, resizable: false, dialogClass: 'selectModelPopup',title: "Upload model"});
    }
}

function hideModelUploadDialog(submit) {

    jQuery("#model-upload-popup-content").appendTo(jQuery("#model-upload"));
    modelUploadPopup.dialog("destroy");
    if (submit) {
        jQuery("input.model-file-itemTxt").val(jQuery("#model-upload-popup-content input.model.popup.file-item").val());
        jQuery("input.model-upload-submit").click();
    } else {
        //jQuery("input.cancelModelsUploadSubmit").click();
    }
}

function navToWiki() {
    
}


var columns = [];
function drawColumnsConfigurationScreen() {
    if (columns.length > 0) {
        for (var i = 0; i < columns.length; i++) {
            columns[i].option.remove();
        }
    }
    columns = [];
    var columnsVisibleList = jQuery(".columnsConfigurationWidget .columnsVisible");
    var columnsNotVisibleList = jQuery(".columnsConfigurationWidget .columnsNotVisible");
    columnsVisibleList.html("");
    columnsNotVisibleList.html("");
    jQuery(".columnConfig").each(function() {
        var columnIdx = columns.length;

        var column = {
            name: jQuery(this).find(".name").text(),
            visible: jQuery(this).find(".trigger").attr("checked"),
            trigger: jQuery(this).find(".trigger"),
            option: jQuery("<option onclick='alert()' 'value='" + columnIdx + "'>" + jQuery(this).find(".name").text() + "</option>"),
            id: columnIdx

        };
        columns.push(column);

        if (column.visible) {
            column.option.appendTo(columnsVisibleList);
        }
        else {
            column.option.appendTo(columnsNotVisibleList);
        }
    });

    jQuery(".columnsConfigurationWidget .add").click(function() {
        columnsNotVisibleList.find(":selected").each(function() {
            var column = columns[jQuery(this).val()];
            column.visible = true;
            // find column after which this column should be appended
            var beforeColumn = null;
            for (var i = 0; i < column.id; i++) {
                if (columns[i].visible) {
                    beforeColumn = columns[i];
                }
            }
            if (beforeColumn) {
                beforeColumn.option.after(column.option);
            }
            else {
                column.option.prependTo(columnsVisibleList);
            }
            column.trigger.attr("checked", "true");
        });
    });

    jQuery(".columnsConfigurationWidget .remove").click(function() {
        columnsVisibleList.find(":selected").each(function() {
            var column = columns[jQuery(this).val()];
            column.visible = false;

            // find column after which this column should be appended
            var beforeColumn = null;
            for (var i = 0; i < column.id; i++) {
                if (! columns[i].visible) {
                    beforeColumn = columns[i];
                }
            }
            if (beforeColumn) {
                beforeColumn.option.after(column.option);
            }
            else {
                column.option.prependTo(columnsNotVisibleList);
            }

            column.trigger.removeAttr("checked");
        });
    });
}
function initFiltersWidget() {

    var inputs = [];

    function updateSliderInputs(input, values) {
        input.inputFrom.val(input.printValue(values[0]));
        input.inputTo.val(input.printValue(values[1]));
    }

    function printDate(timestamp, ignoreUnit) {
        var date = new Date();
        date.setTime(timestamp);
        return (date.getMonth() < 9 ? "0" : "") + (date.getMonth() + 1) + "-" + (date.getDate() < 10 ? "0" : "") + date.getDate() + "-" + date.getFullYear();
    }

    function parseDate(dateStr) {
        if (dateStr.match(/\d\d.\d\d.\d\d\d\d/)) {
            // date format mm-dd-yyyy
            var month = (dateStr.substring(0, 2));
            var day = (dateStr.substring(3, 5))
            var year = (dateStr.substring(6, 10));
            date = new Date(year, month - 1, day);
            return date.getTime();
        }
        // date format MMM dd, yyyy

        return parseInt(Date.parse(dateStr));
    }

    function printPercent(percent, ignoreUnit) {
        return percent + (ignoreUnit ? "" : "%");
    }

    function parsePercent(percentStr) {
        return parseInt(percent.replace("%", ""));
    }

    function printNumber(number, ignoreUnit) {
        return number.toFixed(0);
    }

    function parseNumber(number) {
        return parseInt(number);
    }

    function parseValue(unit) {
        if (unit == "Date") {
            return parseDate;
        }
        else if (unit == "Integer") {
            return parseNumber;
        }
        else if (unit == "Percent") {
            return parsePercent;
        }
    }

    function printValue(unit) {
        if (unit == "Date") {
            return printDate;
        }
        else if (unit == "Integer") {
            return printNumber;
        }
        else if (unit == "Percent") {
            return printPercent;
        }
    }

    jQuery(".inputWithSlider").each(function() {
        var def = jQuery(this);
        var inputId = inputs.length;
        var input = {
            id: inputId,
            inputFrom: def.find(".inputFrom"),
            inputTo: def.find(".inputTo"),
            from: def.find(".inputFrom").val(),
            to: def.find(".inputTo").val(),
            min: def.find(".min").val(),
            max: def.find(".max").val(),
            step:  def.find(".step").val(),
            type: def.find(".type").val()
        };
        input.parseValue = parseValue(input.type);
        input.printValue = printValue(input.type);

        if (jQuery.trim(input.from).length == 0) {
            input.from = input.min;
        }
        if (jQuery.trim(input.to).length == 0) {
            input.to = input.max;
        }

        input.min = input.parseValue(input.min);
        input.max = input.parseValue(input.max);
        input.from = input.parseValue(input.from);
        input.to = input.parseValue(input.to);

        updateSliderInputs(input, [input.from, input.to]);


        inputs.push(input);
        input.slider = def.find(".slider").slider("destroy");
        input.slider = def.find(".slider").slider({
            range: true,
            min: input.min,
            max: input.max,
            values: [input.from, input.to],
            step: 1,
            slide: function(event, ui) {
                updateSliderInputs(input, ui.values)
            }
        });

        function updateSliderOnBlur(input) {
            // get current values
            var values = input.slider.slider("option", "values");

            try {
                // try to parse user entered value
                from = input.parseValue(input.inputFrom.val());
                to = input.parseValue(input.inputTo.val());
            }
            catch (e) {
                // ignore

            }
            if (isNaN(from)) {
                from = values[0];
            }
            if (isNaN(to)) {
                to = values[1];
            }
            if (from > to) {
                from = to;
            }
            if (to < from) {
                to = from;
            }
            input.slider.slider("option", "values", [from, to]);
            updateSliderInputs(input, [from, to]);
        }

        input.inputFrom.blur(function() {
            updateSliderOnBlur(input)
        });
        input.inputTo.blur(function() {
            updateSliderOnBlur(input)
        });
    });
}

var configureColumnsDialog;
function showConfigureColumnsDialog() {
    drawColumnsConfigurationScreen();
    configureColumnsDialog = jQuery("#configure_columns_dialog").dialog({width: 770, modal: true, height: 400, draggable: false, resizable: false, dialogClass: 'plansIndexDialog configureColumnsDialog'});
    dialog.find(".ui-dialog-titlebar").hide();
}

function closeDialog(id) {
    jQuery(id).dialog("close");
}

function hideConfigureColumnsDialog() {
    configureColumnsDialog.dialog("destroy");
}

function updateColumns() {
    jQuery(".updateColumnsSubmit").click();
    configureColumnsDialog.dialog("destroy");
}

var filterPlansDialog;
function showFilterPlansDialog() {
    jQuery("#filterPlans").appendTo(jQuery("#filterPlansDialog"));
    jQuery("#filterPlansDialog").dialog("destroy");
    jQuery("#filterPlans").show();
    filterPlansDialog = jQuery("#filterPlansDialog").dialog({width: 630, modal: true, height: 530, draggable: false, resizable: false, dialogClass: 'plansIndexDialog filterPlansDialog'});
}

function hideFilterPlansDialog() {
    filterPlansDialog.dialog("close");
    filterPlansDialog.dialog("destroy");
    filterPlansDialog.appendTo(jQuery("#filterPlans_box"));
    jQuery("#filterPlans").hide();
    jQuery("#filterPlans").appendTo(jQuery("#filterPlansContainer"));

}

function updateFilters() {
    hideFilterPlansDialog();

    jQuery(".filtersEnabledCheckbox").attr("checked", "true");
    //filterPlansDialog.remove();
    jQuery(".updateFiltersButton").click();
}

function toogleFiltersEnabled() {
    jQuery(".updateFiltersButton").click();
}

var createPlanDialog = false;
function showCreatePlanDialog() {
    jQuery("#createPlan").appendTo(jQuery("#createPlanDialog"));
    createPlanDialog = jQuery("#createPlanDialog").dialog({width: 350, height: 275, modal: true, draggable: false, resizable: false, dialogClass: 'plansIndexDialog createPlanDialog'});
}

function hideCreatePlanDialog() {
    createPlanDialog.dialog("close");
    createPlanDialog.dialog("destroy");
    jQuery("#createPlanContainer").html("");
    jQuery("#createPlan").appendTo(jQuery("#createPlanContainer"));
    createPlanDialog.remove();

}


function createPlan() {
    jQuery("#createPlan").appendTo(jQuery("#createPlanContainer"));
    jQuery(".createPlanButton").click();
    jQuery("#createPlan").appendTo(jQuery("#createPlanDialog"));
}


function initializeColumnsInfo() {
    jQuery(".column-hover").each(function() {
        var button = jQuery(this);
        var cont = button.parent().parent().find(".popup-info-box");
        button.hover(function() {
            cont.css({'position': 'absolute', 'left': '-1000'});
            cont.show();

            var pos = button.parent().position();
            var width = button.parent().width();
            var height = cont.height();


            var xpos = pos.left + width / 2.0 - 125;
            var ypos = pos.top - height - 40;

            cont.css({'position': 'absolute', 'top':ypos,'left':xpos, "width":'250px'});
            cont.fadeIn("medium");

        }, function() {
            cont.fadeOut('medium');
        });
    });

    jQuery(".voteForPlan").parent().addClass("voteForPlan");
}

function initializeRibbons() {
    jQuery(".scrapbookProposal, .ribbon-hover, .planDetailsSection").each(function() {
        var button = jQuery(this);
        var cont = button.parent().find(".popup-info-box");
        button.hover(function() {
            cont.css({'position': 'absolute', 'left': '-1000'});
            cont.show();

            var pos = button.parent().position();
            var width = button.parent().width();
            var height = cont.height();


            var xpos = pos.left + width + 20;
            var ypos = pos.top - 20;

            cont.css({'position': 'absolute', 'top':ypos,'left':xpos, "width":'250px'});
            cont.fadeIn("medium");

        }, function() {
            cont.fadeOut('medium');
        });
    });

    jQuery(".voteForPlan").parent().addClass("voteForPlan");
}


function initializePopups() {
    jQuery(".planDetailsSection.pitch").each(function() {
        var button = jQuery(this);
        var cont = button.parent().find(".popup-info-box");
        button.hover(function() {
            cont.css({'position': 'absolute', 'left': '-1000'});
            cont.show();

            var pos = button.parent().position();
            var width = button.parent().width();
            var height = cont.height();


            var xpos = pos.left + 125;
            var ypos = pos.top;

            cont.css({'position': 'absolute', 'top':ypos,'left':xpos, "width":'250px'});
            cont.fadeIn("medium");

        }, function() {
            cont.fadeOut('medium');
        });
    });
}

function findUserVote(id) {
    //alert('looking for user vote' + jQuery(".userVote").length);
    jQuery(".userVote").parent().parent().addClass("active");
    jQuery.scrollTo(jQuery(".userVote"));
    jQuery(".userVote").focus();
    //alert('vote found\n' + findUserVote);
}

function deferUntilLogin() {
    if (Liferay.ThemeDisplay.isSignedIn()) {
        return true;
    } else {
        _user_info_showLoginPopup();
        return false;
    }
}

function navigateToPlan(planId) {
    window.location.hash = "#planId=" + planId;
}

function navigateToPlanIndex() {
    window.location.hash = "#";
}
function initVersionChoosingBox() {
    var container = jQuery(".versionsContainer");
    if (container.hasClass('processed')) {
        return;
    }
    var versions = container.find(".versions");
    var hidden = true;

	alert('initVersionChoosingBox, trigger.length: ' + trigger.length);
    trigger.click(function() {
    	alert('trigger');
        if (! container.is(':visible')) {
            trigger.html("Hide old versions")
            container.show("slide", {direction: "up"});
        }
        else {
            container.hide("slide", {direction: "up"}, 'medium', function() {
                trigger.html("Show old versions");
            });

        }
    });
    container.addClass('processed');
}

function triggerVersionsContainerVisibility() {
    var trigger = jQuery("#versionContainerTrigger");
    var container = jQuery(".versionsContainer");
    if (! container.is(':visible')) {
        trigger.html("Hide history")
        container.show("slide", {direction: "up"});
    }
    else {
        trigger.html("Show history");
        container.hide("slide", {direction: "up"}, 'medium', function() {
        });
    }
    
}


function updatePositionsSelection(positionsArray) {
    var selectedPositionsMap = {};
    for (var i = 0; i < positionsArray.length; i++) {
        selectedPositionsMap[positionsArray[i]] = true;
    }
    jQuery(".versionedRadio input").each(function() {
        var input = jQuery(this);
        if (selectedPositionsMap[input.val()]) {
            input.attr("checked", "true");
            input.addClass("positionChecked");
        }
        else {
            input.removeAttr("checked");
            input.removeClass("positionChecked");
        }

    });

    // add behaviour that allows deselecting an input when clicked
    jQuery(".versionedRadio input").unbind("click");
    jQuery(".versionedRadio input").click(function() {
        var input = jQuery(this);
        //alert('lll' + input.attr("checked"));
        if (input.attr("checked")) {
            //alert("disable");
            if (input.hasClass("positionChecked")) {
                input.removeAttr("checked");
                input.removeClass("positionChecked");
            }
            else {
                var parent = input.parent();
                while (! parent.hasClass("positionItem")) {
                    parent = parent.parent();
                }
                // remove checked class from previously selected position
                parent.find(".positionChecked").removeClass("positionChecked");
                // mark this position as selected
                input.addClass("positionChecked");
            }

        }
        else {
            input.attr("checked", "true");
            input.addClass("positionChecked");
        }
        input.addClass("valueChanged");
    });
}

function switchToScenario(scenarioId) {
    if (typeof(scenarioId) == 'undefined') {
        scenarioId = '';
    }
    var src = jQuery("#actionsAndImpacts").attr("src");

    var currentScenarioId = '';
    if (src.indexOf("scenarioId=") >= 0) {
        var currentScenarioId = src.replace(/^.*scenarioId=/g, "");
    }

    // if currentScenarioId is the same as requested then do nothing
    if (currentScenarioId == scenarioId) {
        return;
    }
    src = src.replace(/scenarioId.*/, "");
    if (scenarioId) {
        src += "scenarioId=" + scenarioId;
    }
    jQuery("#actionsAndImpacts").attr("src", src);
}

var requestMembershipDialog;

function showRequestMembershipDialog() {
    jQuery("#request_membership_dialog").dialog("destroy");
    jQuery("#request_membership_dialog_contents").appendTo(jQuery("#request_membership_dialog"));
    requestMembershipDialog = jQuery("#request_membership_dialog").dialog({width: 380, modal: true, height: 360, draggable: false, resizable: false, dialogClass: 'plansIndexDialog manageMembersDialog'});
}

function hideRequestMembershipDialog() {
    jQuery("#request_membership_dialog_contents").appendTo(jQuery("#request_membership"));
    jQuery("#request_membership_dialog").dialog("destroy");
}

function requestMembership() {
    jQuery("#request_membership_dialog_contents").appendTo(jQuery("#request_membership"));
    jQuery(".requestMembershipSubmitButton").click();
    hideRequestMembershipDialog();
}


function showManageMembershipRequestDialog(contentContainerId) {
    var container = jQuery("#" + contentContainerId);
    var dialog = jQuery("#manage_membership_request_dialog");
    var content = container.find(".content");


    dialog.dialog("destroy");
    content.appendTo(dialog);
    content.show();
    dialog.show();
    dialog = dialog.dialog({width: 380, modal: true, height: 360, draggable: false, resizable: false, dialogClass: 'plansIndexDialog manageMembersDialog'});
}

function hideManageMembershipRequestDialog(contentContainerId) {
    var container = jQuery("#" + contentContainerId);
    var dialog = jQuery("#manage_membership_request_dialog");
    var content = dialog.find(".content");

    content.hide();
    content.appendTo(container);
    dialog.dialog("destroy");
}

function respondToMembershipRequest(contentContainerId) {
    var container = jQuery("#" + contentContainerId);
    var dialog = jQuery("#manage_membership_request_dialog");
    var content = container.find(".content");
    hideManageMembershipRequestDialog(contentContainerId);
    container.find(".membershipRequestResponseSubmitButton").click();
}

var positionsDialog;
function showPositionsOnIndexPageDialog(positionsContainerId) {
    var positions = jQuery("#" + positionsContainerId);
    positionsDialog = positions.dialog({width: 500, modal: true, height: 500, draggable: false, resizable: false, dialogClass: 'plansIndexDialog positionsDialog'});
}

function hidePositionsOnIndexPageDialog(positionsContainerId) {
    var positions = jQuery("#" + positionsContainerId);
    positionsDialog.dialog("destroy");
    positions.dialog("destroy");
}


function makeNANotesVisible() {
    jQuery(".colab.prohome td .errors").each(function() {
        var text = jQuery.trim(jQuery(this).text());
        if (text.length != 0 && !jQuery(this).hasClass('errorsSetUp')) {
            jQuery(this).parent().append('<img class="note" src="/plans-portlet/img/warning_icon16x16.png" />');
            jQuery(this).addClass('errorsSetUp');
        }
    });
    jQuery(".colab.prohome td .note").unbind('hover');
    jQuery(".colab.prohome td .note").hover(function() {
        jQuery(this).parent().addClass("note-hover");
        var errors = jQuery(this).parent().find(".errors");
        if (errors.length > 0 && jQuery.trim(errors.text()).length != 0) {
            errors.fadeIn("medium");
        }
    }, function() {
        jQuery(this).parent().removeClass("note-hover");
        var errors = jQuery(this).parent().find(".errors");
        setTimeout(function() {
            if (! errors.parent().hasClass("note-hover") && !errors.parent().hasClass("popup-hover")) {
                errors.fadeOut("medium");
            }
        }, 200);
    }
            );
    jQuery(".colab.prohome td .popup-info-box").unbind('hover');
    jQuery(".colab.prohome td .popup-info-box").hover(function() {
        jQuery(this).parent().addClass("popup-hover");
        var errors = jQuery(this).parent().find(".errors");
        if (errors.length > 0 && jQuery.trim(errors.text()).length != 0) {
            errors.fadeIn("medium");
        }
    }, function() {
        jQuery(this).parent().removeClass("popup-hover");
        var errors = jQuery(this).parent().find(".errors");
        setTimeout(function() {
            if (! errors.parent().hasClass("note-hover") && !errors.parent().hasClass("popup-hover")) {
                errors.fadeOut("medium");
            }
        }, 200);
    }
            );
}


function setColabInEditMode(mode) {
	window.colabInEditMode = mode;
}



function showAlertIfInEdit(text) {
	if (window.colabInEditMode && !confirm(text)) {
		return false;
	}
	return true;
}


function monitorUploadFrame() {
	var imgContainer = jQuery(".proposalImageUpload .uploadbox");
		jQuery('.proposalImageUpload iframe').load(function() {
			
			var imgUrl = jQuery(this).contents().find("input.filepcTxt").attr("title");
			if (imgUrl) {
				imgUrl = jQuery.trim(imgUrl);
				if (imgUrl.length > 0) {
					var imgElement = "<img src='" + imgUrl + "' width='50px' height='50px' />";
					imgContainer.html(imgElement);
				}
			} 
		});
}
