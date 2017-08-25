/**
 * Sets the cookie for contests page (GRID/LIST) 
 * 
 * @param viewType type of the view (GRID/LIST)
 */
function setContestsViewTypeCookie(viewType) {
	Cookies.set("cc_contests_viewType", viewType, {expires: 365});
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
        jQuery('#invalidFieldsModal').modal('show');
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
}

function requestMembership() {
    $('#requestComment').slideDown('slow');
    $('.prop-butt-popover:first').css('background', 'url('+_themeImageFolder+'/search-bg.png)');
    var $requestButtons = $('#requestButtons');
    $requestButtons.empty();
    $requestButtons.append('<a href="javascript:;" class="c-Button__secondary" onclick="hideRequestForm(true);">Cancel</a>');
    $requestButtons.append('<a href="javascript:;" class="c-Button__primary b-layout__right" onclick="$(\'#requestMembershipForm\').submit();hideRequestForm(false);">Send</a>');
}
function hideRequestForm(animate) {
    var speed = animate ? 600 : 1;
    $('#requestComment').slideUp('slow', function () {
        $('.prop-butt-popover:first').css('background', 'none');
        var $requestButtons = $('#requestButtons');
        $requestButtons.empty();
        $requestButtons.append('<a href="javascript:;" class="c-Button__primary" onclick="if(deferUntilLogin()) requestMembership();">Request membership</a>');
    });
}
/* End of request membership form logic */

/* Membership invite logic */
function inviteMember() {
    $('#invite-comment').slideDown('slow');
    $('#invite-recipient').slideDown('slow');
    $('.prop-butt-popover:first').css('background', 'url('+_themeImageFolder+'/search-bg.png)');
    var $requestButtons = $('#requestButtons');
    $requestButtons.empty();
    $requestButtons.append('<a href="javascript:;" class="c-Button__secondary b-layout__left" onclick="hideInviteForm(true);">Cancel</a>');
    $requestButtons.append('<a href="javascript:;" class="c-Button__primary b-layout__right" onclick="$(\'#requestInviteForm\').submit();hideInviteForm(false);">Send</a>');
}

function hideInviteForm(animate) {
    var speed = animate ? 600 : 1;
    $('#invite-comment').slideUp('slow', function () {
        $('.prop-butt-popover:first').css('background', 'none');
        var $requestButtons = $('#requestButtons');
        $requestButtons.empty();
        $requestButtons.append(
            '<a href="javascript:;" class="c-Button__primary" rel="nofollow" onclick="if(deferUntilLogin()) inviteMember();">Invite team member</a>');
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
        modelSetRadios[i].checked = modelSetRadios[i].id == id;
    }
}

var selectModelForFetchedScenario = function(event){
    checkModelSetRadioWithId(event.scenario.modelId);
};
