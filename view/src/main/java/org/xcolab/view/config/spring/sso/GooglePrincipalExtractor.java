package org.xcolab.view.config.spring.sso;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

public class GooglePrincipalExtractor extends AbstractOpenIdPrincipalExtractor {

    public GooglePrincipalExtractor(LoginRegisterService loginRegisterService,
            MemberDetailsService memberDetailsService) {
        super(loginRegisterService, memberDetailsService);
    }

    @Override
    protected MemberDetails loadFromSsoId(String googleId) throws UsernameNotFoundException {
        return memberDetailsService.loadUserByGoogleId(googleId);
    }

    @Override
    protected void setSsoId(UserWrapper member, String ssoId) {
        member.setGoogleId(ssoId);
    }
}
