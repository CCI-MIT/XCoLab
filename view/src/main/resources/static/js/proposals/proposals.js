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
    //Try to initialize client-side code first time
    initializeJavaScript();
    //Try to initiliaze after timeout since portlet doesn't sometimes load
    setTimeout(function(){
        initializeJavaScript();
    }, 10000);
});

function initializeJavaScript(){
    initTooltips();
    evaluateTime();
    jQuery(".addpropform .helpTrigger").click(function() {
        var trigger = jQuery(this);
        trigger.parent().parent().find(".addprophelp").slideToggle("fast");
    });

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
}

function evaluateTime() {
    $(".dateInfo").each(function() {
        var adjustedDate = new Date(Date.parse($(this).html()));
        var timeZoneIdentifier = String(String(new Date).split("(")[1]).split(")")[0];  //get the timezone abbreviation
        //add +1 to month since the enumeration starts with 0
        $(this).html((adjustedDate.getMonth()+1)+"/"+adjustedDate.getDate()+"/"+adjustedDate.getFullYear()+" "+timeZoneIdentifier);
        $(this).removeClass("dateInfo");
    });
}

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
}

function enableDirtyCheck() {
	window.oldOnBeforeUnload = window.onbeforeunload;

	window.onbeforeunload = function() {
        var dirtyEditors = $(".editorDirty").filter(function(index){
            return !($(this).attr("name") === "login");
        });
        console.log(dirtyEditors);
		if (dirtyEditors.length > 0) {
			return 'You have modified this page but have not saved your changes.';
		}
		return null;
	};
}

/* Request membership form logic */
function clearContents(element) {
    element.value = '';
};

function requestMembership() {
    $('#requestComment').slideDown('slow');
    $('.prop-butt-popover:first').css('background', 'url('+_themeImageFolder+'/search-bg.png)');
    $('#requestButtons').empty();
    $('#requestButtons').append('<div class="c-Button__secondary"><a href="javascript:;" class="requestMembershipSubmitFormButton b-layout__left" onclick="hideRequestForm(true);">Cancel</a></div>');
    $('#requestButtons').append('<div class="c-Button__primary"><a href="javascript:;" class="requestMembershipSubmitFormButton b-layout__right" onclick="$(\'#requestMembershipForm\').submit();hideRequestForm(false);">Send</a></div>');
}
function hideRequestForm(animate) {
    var speed = animate ? 600 : 1;
    $('#requestComment').slideUp('slow', function () {
        $('.prop-butt-popover:first').css('background', 'none');
        $('#requestButtons').empty();
        $('#requestButtons').append('<div class="c-Button__primary" style="display:block;"><a href="javascript:;" class="requestMembershipSubmitButton" onclick="if(deferUntilLogin()) requestMembership();">Request membership</a></div>');
    });
}
/* End of request membership form logic */

/* Membership invite logic */
function inviteMember() {
    $('#invite-comment').slideDown('slow');
    $('#invite-recipient').slideDown('slow');
    $('.prop-butt-popover:first').css('background', 'url('+_themeImageFolder+'/search-bg.png)');
    $('#requestButtons').empty();
    $('#requestButtons').append('<div class="c-Button__secondary"><a href="javascript:;" class="requestMembershipSubmitFormButton b-layout__left" onclick="hideInviteForm(true);">Cancel</a></div>');
    $('#requestButtons').append('<div class="c-Button__primary"><a href="javascript:;" class="requestMembershipSubmitFormButton b-layout__right" onclick="$(\'#requestInviteForm\').submit();hideInviteForm(false);">Send</a></div>');
}

function hideInviteForm(animate) {
    var speed = animate ? 600 : 1;
    $('#invite-comment').slideUp('slow', function () {
        $('.prop-butt-popover:first').css('background', 'none');
        $('#requestButtons').empty();
        $('#requestButtons').append(
            '<div class="c-Button__primary" style="display:block;"><a href="javascript:;" class="requestMembershipSubmitButton" onclick="if(deferUntilLogin()) inviteMember();">Invite team member</a></div>');
    });
    $('#invite-recipient').slideUp('slow');
}

// jQuery autocomplete
$(function() {
    var cache = {};
    var $recipientInput = $( "#invite-recipient" );
    $recipientInput.autocomplete({
            minLength: 3,
            source: "/api/members/getUserByScreenName"
    });

    $recipientInput.bind("autocompleteselect", "select", function(event) {

    });
});



function setModelSetRadiosState(enabled) {
    var modelSetRadios = document.getElementsByName("modelSet");
    var len = modelSetRadios.length;
    for (var i = 0; i < len; i++) {
        modelSetRadios[i].disabled = enabled;
    }
}

function checkModelSetRadioWithId(id) {
    var modelSetRadios = document.getElementsByName("modelSet");
    var len = modelSetRadios.length;
    for (var i = 0; i < len; i++) {
        if (modelSetRadios[i].id == id) {
            modelSetRadios[i].checked = true;
        }
        else {
            modelSetRadios[i].checked = false;
        }
    }
}

var selectModelForFetchedScenario = function(event){
    checkModelSetRadioWithId(event.scenario.modelId);
};
