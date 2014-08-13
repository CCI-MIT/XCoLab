//find existing cookie containing the uuid
var uuid = null;
var isTrackedVisitor = null;
if (jQuery.cookie("userTrackingUuid")) {
    uuid = jQuery.cookie("userTrackingUuid");
    if (jQuery.cookie("userTrackingIsTrackedVisitor")) {
        isTrackedVisitor = jQuery.cookie("userTrackingIsTrackedVisitor");
    }
} else {
    jQuery.removeCookie("userTrackingIsTrackedVisitor");
}

var postData = {
    uuid: uuid,
    userId: usertracking_userId,
    hash: usertracking_hash,
    //do not send the domain part
    url: "/"+document.location.href.replace(/^(?:\/\/|[^\/]+)*\//, ""),
    isTrackedVisitor: isTrackedVisitor ? "true" : null,
    referer: document.referrer
};


jQuery.post("/usertracking-portlet/trackVisitor", postData, function(data) {
    jQuery.cookie("userTrackingUuid", data.uuid, {path: '/'});
    if (data.isTrackedVisitor) {
        jQuery.cookie("userTrackingIsTrackedVisitor", data.isTrackedVisitor, {path: '/'});
    }
});