/*
 * This file contains methods that should be used to check if given edit page is "dirty",
 * (user has changed anything). All methods in here should be used in conjunction with 
 */

function checkIfPlanPositionsDirty() {
	if (jQuery(".versionedRadio input").hasClass('valueChanged')) {
		return true;
	}
	return false;
}

function checkIfPlanNameDirty() {
	var planNameInput = jQuery('.planNameInput');
	if (planNameInput.length > 0 && planNameInput.hasClass('valueChanged')) {
		return true;
	} 
	return false;
}

function checkIfPlanDescriptionDirty() {
	// check every FCKEditor instance and if it is "dirty" then return true
	var isDirty = false;
	jQuery("#addpropform input[type='text'], #addpropform textarea").each(function() {
		var input = jQuery(this);
		if (input.hasClass('originalValue')) return;
		
		var oryginalValue = input.parent().find(".originalValue").val();
		if (jQuery.trim(input.val()) != jQuery.trim(oryginalValue)) {
			isDirty = true;
		}
	});
	return isDirty;
	
}

function checkIfActionsImpactsDirty() {
	return jQuery(".simulationInputsStatus").hasClass("valueChanged");
}
