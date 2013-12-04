<jsp:root
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions"
        xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
        xmlns:spring="http://www.springframework.org/tags"
        xmlns:form="http://www.springframework.org/tags/form"
        xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">

    <h1 style="padding-left: 20px;">Welcome to the Climate CoLab!</h1>
    <br /><br />
    <br />
    <portlet:actionURL var="provideSSOCredentials">
        <portlet:param name="action" value="provideSSOCredentials" />
        <portlet:param name="SSO" value="general" />
    </portlet:actionURL>

    <table>
        <tr>
            <td style="width:50%; padding: 0 20px;">
                <h2>Sign in as existing CoLab member</h2>
                <br />
                <span>You can sign in to your account to link your social media and Climate CoLab account and use the social media login when returning to the site.</span>
                <div style="height:15px;">&#160;</div>
                <c:if test="${credentialsError}">
                    <div class="error-message-registerOrLogin"><p>Authentication failed</p></div>
                </c:if>
                <div class="loginbox" style="margin: 15px auto 0 auto;">
                    <form id="signInFormPopup" method="post" action="${provideSSOCredentials}">
                        <input name="login" type="text" class="username" placeholder="username" />
                        <input name="password" type="password" class="password" placeholder="password" />
            <span><em><a href="javascript:;" class="forgot" onclick="showForgotPasswordPopup()">Forgot your
                password?</a></em></span> <input name="submit" type="submit" class="login-submit" value="LOGIN" id="loginPopupTopSubmit" /></form>
                </div>
            </td>
            <td style="width:50%; padding: 0 20px;">
                <h2>Register as new CoLab member</h2>
                <br />
                <span>You can register as new member of the Climate CoLab to be part of the community.</span>
                <br /><br /><br /><br />
                <h1 style="text-align: center;"><a href="/web/guest/loginregister">Register as new member</a></h1>
            </td>
        </tr>
    </table>


</jsp:root>