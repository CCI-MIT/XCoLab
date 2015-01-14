function clearSendMessageForm() {
    jQuery(
        ".sendMessagePopup .popuplogin_input, .sendMessagePopup textarea")
        .val('');
}

function lockSendMessageForm() {
    jQuery("#messageForm").submit();
    jQuery(".sendMessagePopup").block({
        message : "Sending message"
    });
}

function updateSuccess(){
    jQuery.growlUI('','User profile updated successfully');
}

function updateError(){
    jQuery.growlUI('','Erros occured while updating profile');
}

function messageSent() {
    jQuery.growlUI('','Message has been sent');
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