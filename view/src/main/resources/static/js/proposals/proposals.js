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
        var x = jQuery(this).parents(".c-Box");
        invalidFieldsList.append("<li>" + jQuery(this).parents(".c-Box").find("strong").text() + "</li>");
        
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

// jQuery autocomplete
$(function() {
    var cache = {};
    var $recipientInput = $( "#invite-recipient" );
    $recipientInput.autocomplete({
            minLength: 3,
            source: "/api/members/getUserByScreenName"
    });

    $recipientInput.bind("autocompleteselect", "select", function(event) {
        event.stopPropagation();
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
