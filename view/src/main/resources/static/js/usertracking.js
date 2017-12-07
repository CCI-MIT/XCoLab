//find existing cookie containing the uuid
var uuid = Cookies.get("userTrackingUuid");

var url = document.location.href+"";
var postData = {
    uuid: uuid,
    //do not send the domain part
    url: "/"+url.replace(/^(?:\/\/|[^\/]+)*\//, ""),
    referer: document.referrer
};

jQuery.post("/trackVisitor", postData, function(data) {
    Cookies.set("userTrackingUuid", data.uuid);
});
