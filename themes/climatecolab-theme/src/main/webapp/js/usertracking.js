//find existing cookie containing the uuid
var uuid = null;
var isTrackedVisitor = null;
if (jQuery.cookie("userTrackingUuid")) {
    uuid = jQuery.cookie("userTrackingUuid");
    if (jQuery.cookie("userTrackingIsTrackedVisitor")) {
        isTrackedVisitor = jQuery.cookie("userTrackingIsTrackedVisitor");
    }
} else {
    jQuery.removeCookie("userTrackingIsTrackedVisitor", { path: '/' });
}

//10115 is the guest user's id - delete it
if (usertracking_userId == 10115) {
    usertracking_userId = null;
    usertracking_hash = null;
    isTrackedVisitor = false;
    jQuery.removeCookie("userTrackingIsTrackedVisitor", { path: '/' });
}

var url = document.location.href+"";
var postData = {
    uuid: uuid,
    //do not send the domain part
    url: "/"+url.replace(/^(?:\/\/|[^\/]+)*\//, ""),
    referer: document.referrer
};

if (usertracking_userId) {
    postData.userId = usertracking_userId;
    postData.hash = usertracking_hash;
}
if (isTrackedVisitor) {
    postData.isTrackedVisitor = true;
}


jQuery.post("/usertracking-portlet/trackVisitor", postData, function(data) {
    jQuery.cookie("userTrackingUuid", data.uuid, {path: '/'});
    if (data.isTrackedVisitor) {
        jQuery.cookie("userTrackingIsTrackedVisitor", data.isTrackedVisitor, {path: '/'});
    }
});