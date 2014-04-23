// check if extra data has been stored already (cookie has special flag for that)
// tracking is enabled only when there is a climatecolabBalloonCookie present and it doens't contain a flag saying that data has been already gathered
/*
var trackingEnabled = true;
var extraDataStored = false;
jQuery.each(jQuery.cookie(), function(key, val) { 
	if (key == 'climatecolabBalloonCookie') {
		trackingEnabled = true;
		var parts = val.split("|");
		if (parts.length < 2) return;
		if (parts[1] == 'true') extraDataStored = true;
	}
});

var sendDataUrl = "/redballoon-portlet/balloonTrack";
function sendData(data) {
	console.log("sending data....", data);
	jQuery.ajax({type: "POST", url: sendDataUrl, data: data});
}

if (trackingEnabled && ! extraDataStored) {
	// check for user geolocation
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function (pos) {
			var crd = pos.coords;
			sendData(crd);
		});
	}
	
}*/