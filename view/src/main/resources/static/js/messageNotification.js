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

                var jsonResult = JSON.parse(result);

                if (jsonResult.notificationId != cookieId) {
                    noty({text: jsonResult.notificationText, type: 'success'});
                    Cookies.set('notificationId', jsonResult.notificationId, {expires: 1});
                }
            },
            error: function (result) {
                console.error('Retrieving notifications: ' + result)
            }
        });
        setTimeout(poll, POLLING_INTERVAL_MINUTES * 60 * 1000);
    }

    jQuery(function() {
        setTimeout(poll, POLLING_INITIAL_DELAY_SECONDS * 1000);
    });
}());
