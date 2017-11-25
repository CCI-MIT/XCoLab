//find existing cookie containing the uuid
var isTrackedVisitor = null;
var uuid = Cookies.get("userTrackingUuid");

var url = document.location.href+"";
var postData = {
    uuid: uuid,
    //do not send the domain part
    url: "/"+url.replace(/^(?:\/\/|[^\/]+)*\//, ""),
    Referer: document.referrer
};

if (typeof usertracking_userId != 'undefined') {
    postData.userId = usertracking_userId;
    postData.hash = usertracking_hash;
}

jQuery.post("/trackVisitor", postData, function(data) {
    Cookies.set("userTrackingUuid", data.uuid);
});
