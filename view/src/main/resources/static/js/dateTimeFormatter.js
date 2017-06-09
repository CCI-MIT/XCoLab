// namespace = dateTimeFormatter
var dateTimeFormatter = {
    dateTime: function (unixEpoch) {
        return moment.unix(unixEpoch / 1000).format("MM/DD/YYYY hh:mm A") + ' '
                + dateTimeFormatter.getZoneAsCode();
    },

    date: function (unixEpoch) {
        return moment.unix(unixEpoch / 1000).format("MM/DD/YYYY");
    },

    dateText: function (unixEpoch) {
        return moment.unix(unixEpoch / 1000).format("MMM. DD, YYYY");
    },

    time: function (unixEpoch) {
        return moment.unix(unixEpoch / 1000).format("hh:mm A") + ' '
                + dateTimeFormatter.getZoneAsCode();
    },
    getZoneAsCode: function () {
        var paren = new Date().toString().match(/\(.+\)/);
        return paren ? paren[0].match(/([A-Z])/g).join("") : "";
    }
};
