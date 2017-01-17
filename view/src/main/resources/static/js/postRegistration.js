$(document).ready(function() {
    console.log("ready");
    var $postRegisterForm = $('#postRegister_form');
    $postRegisterForm.find('#redirect').val(document.URL);
    $postRegisterForm.find('#reg_username').bind('keyup click blur focus change paste', validateInput);
    $postRegisterForm.find('#reg_bio').bind('keyup click blur focus change paste', validateInput);
});

function validateInput() {
    var $postRegisterForm = $('#postRegister_form');
    var screenName = $postRegisterForm.find('#reg_username').val();
    var bio = $postRegisterForm.find('#reg_bio').val();
    var valid = screenName.match("^[a-zA-Z0-9]+$") != null && (bio == "" || bio.length < 2000);

    if (!valid) {
        $postRegisterForm.find("#error").show();
    } else {
        $postRegisterForm.find("#error").hide();
    }

    return valid;
}

function submitForm() {
    if (validateInput() == true) {
        var json = {};
        var $postRegisterForm = $('#postRegister_form');
        json.screenName = $postRegisterForm.find('#reg_username').val();
        json.bio = $postRegisterForm.find('#reg_bio').val();
        // Send the request
        $.post('/register/finalize', json, function(response) {
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