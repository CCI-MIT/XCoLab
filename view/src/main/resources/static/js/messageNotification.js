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

    jQuery(function() {
        setTimeout(poll, POLLING_INITIAL_DELAY_SECONDS * 1000);
    });
}());
