function clearSendMessageForm() {
    jQuery(".sendMessagePopup, .popuplogin_input, .sendMessagePopup textarea").val('');
}

function lockSendMessageForm() {
    jQuery(".sendMessagePopup").block({
        message : "Sending message"
    });
}

function unblockSendMessageForm() {
    jQuery(".sendMessagePopup").unblock();
}

function updateSuccess(){
    jQuery.growlUI('','User profile updated successfully.');
}

function updateError(){
    jQuery.growlUI('','Errors occurred while updating profile.');
}

function messageSent() {
    jQuery.growlUI('', 'Message has been sent.');
}

function messageNotSent() {
    jQuery.growlUI('','Message has NOT been sent.');
}

function limitExceeded() {
    jQuery.growlUI('Please try again tomorrow.','Your daily message limit has been reached.');
}

function hideSendMessagForm() {
    jQuery(".sendMessagePopup").hide();
}

function showSendMessageForm() {
    clearSendMessageForm();
    jQuery(".sendMessagePopup").show();

}

function sendMessageFormValid() {
    var valid = true;
    clearSendMessageForm();
    var subj = jQuery(".sendMessage_subject");
    var msg = jQuery(".sendMessage_message");

    if (jQuery.trim(subj.val()) == '') {
        subj.parent().find(".error").show();
        valid = false;
    }

    if (jQuery.trim(msg.val()) == '') {
        msg.parent().find(".error").show();
        valid = false;
    }

    return valid;
}

function clearSendMessageForm() {
    jQuery(".sendMessagePopup .error").hide();
    jQuery(".sendMessage_subject .sendMessage_message").val('');
}

function selectAllSubscriptions() {
    jQuery(".subscriptionSelect").prop('checked', true);
}

function unSelectAllSubscriptions() {
    jQuery(".subscriptionSelect").prop('checked', false);
}


function sendAjaxToServer(serverUrl, formData){

    var deferred = jQuery.Deferred();

    jQuery.ajax({
        type: 'POST',
        url: serverUrl,
        dataType: 'text',
        data: formData,
        success: function(response){
            var responseStatus  = JSON.parse(response);
            if(responseStatus.hasOwnProperty("success")){
                if(responseStatus.hasOwnProperty("error")){
                    deferred.resolve(responseStatus.error);
                } else{
                    deferred.resolve(responseStatus.success);
                }
            }
        },
        error: function(xhr, status, error){
            deferred.resolve(false);
        }
    });

    return deferred;

}