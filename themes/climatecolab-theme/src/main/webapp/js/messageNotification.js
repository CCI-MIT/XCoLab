var url = "/api/jsonws/plansProposalsFacade-portlet.contest/get-number-of-unread-messages/";
var intervalMinutes = 10;
var interval = intervalMinutes * 60 * 1000;

$().ready(function() {
    if ($('#userPopupTrigger').length > 0) {
        setTimeout(pollData,interval);
    }
});

function pollData() {
    // Reset the session expiration timer
    extendSession();

    $.getJSON(url, function(data) {
        parseData(data);
        setTimeout(pollData,interval);
    });
}

function parseData(json) {
    var unreadMessages = json;
    // console.log("data: " + unreadMessages);

    var element = $('.user-notification.user-notification-top');
    if (unreadMessages > 0) {
        element.show();
        element.html(unreadMessages);
    } else {
        element.hide();
    }
}


function extendSession(){
    try { if ('noWarning' == mySessionRenewAtWarning()) { mySessionRenew(); } } catch(e) {}
}
//Closes the warning by invoking the click event of extend button...
function mySessionRenewAtWarning(){
    var jqryWarn = new jQuery;
    var warnElm = jqryWarn.find('.popup-alert-notice');
    if (warnElm.length > 0) {
        // Match content of warning with session expiration warning language
        if (Liferay.Language.get('warning-your-session-will-expire').indexOf(warnElm[0].firstChild.nodeValue) == 0) {
            // click the extend button
            warnElm.find('.popup-alert-close').click();
            return true;
        }
    }
    return "noWarning";
}

// Extends the session and resets the client timer
function mySessionRenew() {
    if (Liferay.Session._stateCheck) {
        window.clearTimeout(Liferay.Session._stateCheck);
        Liferay.Session._stateCheck = null;
    }
    Liferay.Session.autoExtent = true;
    Liferay.Session.extend();
}
