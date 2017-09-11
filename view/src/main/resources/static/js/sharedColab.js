function showSharedColabLogin(fn) {

    if (_isLoggedIn) {
        return true;
    } else {
        jQuery('#loginModal').modal('hide');
        jQuery('#ssoLoginModal').modal();
    }
}

var sharedContestAutoRegContestId = null;
var sharedContestAutoRegCallbackFunction = null;

function showSharedContestAutoRegPopUp(fn, contestId) {

    if (_isLoggedIn) {
        //if has cookie for contestId return true;
        var sharedContestConfirm = Cookies.get("sharedColab_" + contestId);
        if (sharedContestConfirm === undefined) {
            sharedContestConfirm = false;
        }
        if (sharedContestConfirm) {
            return true;
        }

        sharedContestAutoRegCallbackFunction = fn;
        sharedContestAutoRegContestId = contestId;

        jQuery('#loinModal').modal('hide');
        jQuery('#autoRegistrationModal').modal();
        return false;
    } else {
        return deferUntilLogin();
    }
}

function handleOkForSharedColabAutoReg() {
    Cookies.set("sharedColab_" + sharedContestAutoRegContestId, "true");
    if (sharedContestAutoRegCallbackFunction != null) {
        sharedContestAutoRegCallbackFunction.call(null);
    }
}

function handleNoForSharedColabAutoReg() {
    jQuery('#autoRegistrationModal').modal('hide');
}
