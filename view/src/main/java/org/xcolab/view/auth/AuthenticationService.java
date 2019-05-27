package org.xcolab.view.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {

    private final AuthenticationContext authenticationContext = new AuthenticationContext();
    private final RememberMeServices rememberMeServices;
    private final List<LogoutHandler> logoutHandlers = new ArrayList<>();
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public AuthenticationService(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
        if (rememberMeServices instanceof LogoutHandler) {
            logoutHandlers.add((LogoutHandler) rememberMeServices);
        }
        logoutHandlers.add(new SecurityContextLogoutHandler());
    }

    public boolean isLoggedIn() {
        return true;
        //return authenticationContext.isLoggedIn();
    }

    public boolean isImpersonating() {
        return authenticationContext.isImpersonating();
    }

    public UserWrapper getMemberOrNull(HttpServletRequest request) {
        return authenticationContext.getMemberOrNull();
    }

    public UserWrapper getRealMemberOrNull() {
        return authenticationContext.getRealMemberOrNull();
    }

    public Authentication authenticate(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member) {
        //initialize session if it doesn't exist
        request.getSession(true);

        final MemberDetails memberDetails = new MemberDetails(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        rememberMeServices.loginSuccess(request, response, authentication);
        if (authenticationSuccessHandler != null) {
            try {
                authenticationSuccessHandler.onAuthenticationSuccess(request, response,
                        authentication, false);
            } catch (IOException e) {
                // IOException can only be thrown if redirectOnSuccess == true
                // or if login is disabled
                throw new InternalException(e);
            }
        }
        return authentication;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        logoutHandlers.forEach(logoutHandler
                -> logoutHandler.logout(request, response, authentication));
    }

    public void setAuthenticationSuccessHandler(
            AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
}
