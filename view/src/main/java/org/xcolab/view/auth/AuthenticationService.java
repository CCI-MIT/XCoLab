package org.xcolab.view.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService {

    private final AuthenticationContext authenticationContext = new AuthenticationContext();
    private final RememberMeServices rememberMeServices;

    @Autowired
    public AuthenticationService(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    public boolean isLoggedIn() {
        return authenticationContext.isLoggedIn();
    }

    public boolean isImpersonating(HttpServletRequest request) {
        return authenticationContext.isImpersonating(request);
    }

    public Member getMemberOrNull(HttpServletRequest request) {
        return authenticationContext.getMemberOrNull(request);
    }

    public Member getRealMemberOrNull() {
        return authenticationContext.getRealMemberOrNull();
    }

    public Member getMemberOrThrow(HttpServletRequest request) {
        return authenticationContext.getMemberOrThrow(request);
    }

    public void authenticate(HttpServletRequest request, HttpServletResponse response,
            Member member) {
        //initialize session if it doesn't exist
        request.getSession();

        final MemberDetails memberDetails = new MemberDetails(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        rememberMeServices.loginSuccess(request, response, authentication);
    }
}
