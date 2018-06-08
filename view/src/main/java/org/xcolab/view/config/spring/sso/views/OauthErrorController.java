package org.xcolab.view.config.spring.sso.views;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.commons.servlet.flash.ErrorPage;
import org.xcolab.view.config.spring.sso.CustomPrincipalExtractor.EmailUsedByDeletedMemberException;
import org.xcolab.view.config.spring.sso.CustomPrincipalExtractor.NoEmailReceivedOauthException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OauthErrorController {

    @GetMapping("/oauth/error/authenticationError")
    public String error(HttpServletRequest request) {
        final AuthenticationException authenticationException = (AuthenticationException) request
                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (authenticationException instanceof NoEmailReceivedOauthException) {
            if (RequestUtil.getOriginalUri(request).startsWith("/sso/facebook")) {
                //Specific error message for Facebook
                return ErrorPage.error("An email address is required for registration and we did "
                        + "not receive one from Facebook. Facebook may not send your email address "
                        + "if it is not verified or if you chose not to allow our app to access it. "
                        + "If you continue having problems registering with Facebook, you can also "
                        + "<a href='/register'>register directly with a username and password</a>.")
                        .withTitle("Email address required for registration")
                        .flashAndReturnView(request);
            }
            //Generic error message otherwise
            return ErrorPage.error("An email address is required for registration and we did not "
                    + "receive one. Please check that you have given us permission to see your "
                    + "email address. You can also <a href='/register'>register directly with a "
                    + "username and password</a>.")
                    .withTitle("Email address required for registration")
                    .flashAndReturnView(request);
        } else if (authenticationException instanceof EmailUsedByDeletedMemberException) {
            return ErrorPage.error("An account using your email address was previously deleted. "
                    + "Please <a href='/feedback'>contact us</a> if you want to reopen it.")
                    .withTitle("Email used by deleted member")
                    .flashAndReturnView(request);
        } else {
            throw authenticationException;
        }
    }
}
