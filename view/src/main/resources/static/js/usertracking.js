//find existing cookie containing the uuid
var isTrackedVisitor = null;
var uuid = Cookies.get("userTrackingUuid");
if (uuid) {
    isTrackedVisitor = Cookies.get("userTrackingIsTrackedVisitor");
} else {
    Cookies.remove("userTrackingIsTrackedVisitor");
}

if (typeof usertracking_userId == 'undefined') {
    isTrackedVisitor = false;
    Cookies.remove("userTrackingIsTrackedVisitor");
}

var url = document.location.href+"";
var postData = {
    uuid: uuid,
    //do not send the domain part
    url: "/"+url.replace(/^(?:\/\/|[^\/]+)*\//, ""),
    referer: document.referrer
};

if (typeof usertracking_userId != 'undefined') {
    postData.userId = usertracking_userId;
    postData.hash = usertracking_hash;
}
if (isTrackedVisitor) {
    postData.isTrackedVisitor = true;
}

jQuery.post("/trackVisitor", postData, function(data) {
    Cookies.set("userTrackingUuid", data.uuid);
    if (data.isTrackedVisitor) {
        Cookies.set("userTrackingIsTrackedVisitor", data.isTrackedVisitor);
    }
});
