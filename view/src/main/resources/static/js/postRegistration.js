/**
 * Created by Mente on 13/04/14.
 */
$(document).ready(function() {
    console.log("ready");
    $('#postRegister_form #redirect').val(document.URL);
    $('#postRegister_form #reg_username').bind('keyup click blur focus change paste', validateInput);
    $('#postRegister_form #reg_bio').bind('keyup click blur focus change paste', validateInput);

});

function validateInput() {
    var screenName = $('#postRegister_form #reg_username').val();
    var bio = $('#postRegister_form #reg_bio').val();
    var valid = screenName.match("^[a-zA-Z0-9]+$") != null && (bio == "" || bio.length < 2000);

    if (!valid) {
        $("#postRegister_form #error").show();
    } else {
        $("#postRegister_form #error").hide();
    }

    return valid;
}

function submitForm() {
    if (validateInput() == true) {
        var json = {};
        json.screenName = $('#postRegister_form #reg_username').val();
        json.bio = $('#postRegister_form #reg_bio').val();
        // Send the request
        $.post('/web/guest/loginregister/-/login/register/finalize', json, function(response) {
            var success = true;

            if (response.screenName.success == 0) {
                success = false;
                $('#screenName_error').show();
            } else {
                $('#screenName_error').hide();
            }
            if (response.bio.success == 0) {
                success = false;
                $('#bio_error').show();
            } else {
                $('#bio_error').hide();
            }

            if (success) {
                jQuery('#popup_postRegistration').hide();
                var url = document.URL;
                url = url.replace("postRegistration=true", "");
                window.location.href = url;
            }
        }, 'json');
    }
}