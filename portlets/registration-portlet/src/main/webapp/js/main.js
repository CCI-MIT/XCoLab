function monitorUploadFrame() {
	setTimeout(function() {
		jQuery('.profile_upload iframe').load(function() {
			jQuery(".signalPictureUploaded").click();
		})}, 5);
}

function selectAllSubscriptions() {
	jQuery(".subscriptionSelect").attr('checked', 'true');
}