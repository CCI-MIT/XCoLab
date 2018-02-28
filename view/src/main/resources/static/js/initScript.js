// Globally required scripts

function deferUntilLogin(source) {
    if (_isLoggedIn) {
        return true;
    } else {
        if (source != null) {
            var $source = $(source);
            jQuery("#signInForm_form").find("input[name=redirect]").val($source.attr('href'));
        }
        $('#loginModal').modal();
    }
}

function showForgotPasswordModal() {
    jQuery('#loginModal').modal('hide');
    jQuery('#forgotPasswordModal').modal();
}

function enableDirtyCheck() {
    window.oldOnBeforeUnload = window.onbeforeunload;
    window.onbeforeunload = function () {
        if (jQuery(".editorDirty").length > 0) {
            return 'You have modified this page but have not saved your changes.';
        }
        return null;
    };
}

function disableDirtyCheck() {
    if ('oldOnBeforeUnload' in window) {
        window.onbeforeunload = window.oldOnBeforeUnload;
    }
    delete window.onbeforeunload;
}

(function () {
    var POLLING_INITIAL_DELAY_SECONDS = 2;
    var POLLING_INTERVAL_MINUTES = 3;

    function poll() {
        $.ajax({
            type: "GET",
            url: "/notificationMessage",
            data: null,
            success: function (result) {

                var cookieId = Cookies.get("notificationId");

                if (result.notificationId > 0 && result.notificationId != cookieId) {
                    noty({text: result.notificationText, type: 'success'});
                    Cookies.set('notificationId', result.notificationId, {expires: 1});
                }
            },
            error: function (result) {
                console.error('Error retrieving notifications: ' + result)
            }
        });
        setTimeout(poll, POLLING_INTERVAL_MINUTES * 60 * 1000);
    }

    jQuery(function () {
        setTimeout(poll, POLLING_INITIAL_DELAY_SECONDS * 1000);
    });
}());
