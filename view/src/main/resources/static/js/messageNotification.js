var rotator = function(){

    $.ajax({
        type: "GET",
        url: "/notificationMessage",
        data: null,
        success: function (result) {

            var cookieID = getCookie("notificationID");

            var obj = JSON.parse(result);
            //alert(obj.notificationId);
            //alert(obj.notificationText);
            if(obj.notificationId != cookieID)
            {
                noty({text: obj.notificationId, type: 'success'})
                setCookie("notificationID", obj.notificationId, 1)
            }

        },
        error: function (result) {
            alert("Error");
        }
    });


    setTimeout(rotator,5000);

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }

};

rotator();