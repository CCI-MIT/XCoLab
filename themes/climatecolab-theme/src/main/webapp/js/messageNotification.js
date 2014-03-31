var url = "/api/jsonws/plansProposalsFacade-portlet.contest/get-number-of-unread-messages/";
var intervalMinutes = 10;
var interval = intervalMinutes * 60 * 1000;

$().ready(function() {
    if ($('#userPopupTrigger').length > 0) {
        console.log("ready");
        pollData();
    }
});

function pollData() {
    $.getJSON(url, function(data) {
        console.log("jsoncall");
        parseData(data);
        setTimeout(pollData,interval);
    });
}

function parseData(json) {
    var unreadMessages = json;
    console.log("data: " + unreadMessages);

    var element = $('.user-notification.user-notification-top');
    if (unreadMessages > 0) {
        element.show();
        element.html(unreadMessages);
    } else {
        element.hide();
    }
}
