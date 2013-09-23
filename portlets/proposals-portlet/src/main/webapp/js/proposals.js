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
	$("#filterContestsForm").submit();
}

$(function() {
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
});