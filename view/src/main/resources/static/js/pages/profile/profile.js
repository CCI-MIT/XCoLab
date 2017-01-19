jQuery(function() {
    jQuery(".helpTrigger").click(function() {
        var trigger = jQuery(this);
        trigger.parent().parent().find(".profilehelp").slideToggle("fast");
    });

    // tooltips
    initTooltips();
    selectCountry();

});

function selectCountry() {
    var userCountry = $("#userCountry").html()
    $("#countryCode > option").each(function () {
        if(userCountry == $(this).html()){
            $(this).prop('selected', true);
        }
    });
}
function clearSendMessageForm() {
    jQuery(".c-Message__popup, .c-Message__input, .c-Message__popup textarea").val('');
}

function lockSendMessageForm() {
    jQuery(".c-Message__popup").block({
        message : "Sending message"
    });
}

function unblockSendMessageForm() {
    jQuery(".c-Message__popup").unblock();
}

function updateSuccess(){
    noty({text: 'User profile updated successfully.', type: 'success'})
}

function updateError(){
    noty({text: 'Errors occurred while updating profile.', type: 'error'});
}

function imageSizeError(){
    noty({text: 'Your profile picture is too big, please upload a smaller one.', type: 'error'});
}

function messageSent() {
    noty({text: 'Message has been sent.', type: 'success'})
}

function messageNotSent() {
    noty({text: 'Message has NOT been sent.', type: 'error'})
}

function limitExceeded() {
    noty({text: 'Your daily message limit has been reached.', type: 'error'});
}

function hideSendMessagForm() {
    jQuery(".c-Message__popup").hide();
}

function showSendMessageForm() {
    clearSendMessageForm();
    jQuery(".c-Message__popup").show();

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
    jQuery(".c-Message__popup .error").hide();
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