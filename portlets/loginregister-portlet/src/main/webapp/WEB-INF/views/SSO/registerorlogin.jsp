<jsp:root
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions"
        xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
        xmlns:spring="http://www.springframework.org/tags"
        xmlns:form="http://www.springframework.org/tags/form"
        xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

    <h1>Login or Register</h1>



    <h2>Login</h2>

    <portlet:actionURL var="provideSSOCredentials">
        <portlet:param name="action" value="provideSSOCredentials" />
    </portlet:actionURL>


        <div class="loginbox">
            <form id="signInFormPopup" method="post"
                  action="${provideSSOCredentials}">
                <input name="login" type="text" class="username" placeholder="username" />
                <input name="password" type="password" class="password" placeholder="password" />
                <span><em><a href="javascript:;" class="forgot" onclick="showForgotPasswordPopup()">Forgot your
                password?</a></em></span> <input name="submit" type="submit" class="login-submit" value="LOGIN" id="loginPopupTopSubmit" /></form>
        </div>


    <h2><a href="/web/guest/loginregister">Register</a></h2>






</jsp:root>