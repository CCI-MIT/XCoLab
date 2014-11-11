

jQuery( document ).ready(function() {

    var a = ["Portlet unavailable", "Internal error has ocurred, please contact the administrator."]; // Add more error messages here
    a.forEach(function(errorMessage) {
        if (document.body.innerText.match( errorMessage )){
            jQuery('#popup_error_reporting').show();
        }
    });
});