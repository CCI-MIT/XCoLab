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
    var userCountry = $("#userCountry").html();
    $("#countryCode").find("> option").each(function () {
        if(userCountry == $(this).html()){
            $(this).prop('selected', true);
        }
    });
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