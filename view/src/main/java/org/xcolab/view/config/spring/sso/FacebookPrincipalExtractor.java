package org.xcolab.view.config.spring.sso;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Map;

public class FacebookPrincipalExtractor extends CustomPrincipalExtractor<Long> {

    public FacebookPrincipalExtractor(LoginRegisterService loginRegisterService,
            MemberDetailsService memberDetailsService) {
        super(loginRegisterService, memberDetailsService);
    }

    @Override
    protected MemberDetails loadFromSsoId(Long facebookId) throws UsernameNotFoundException {
        return memberDetailsService.loadUserByFacebookId(facebookId);
    }

    @Override
    protected void setSsoId(Member member, Long ssoId) {
        member.setFacebookId(ssoId);
    }

    @Override
    protected Long extractId(Map map) {
        return Long.parseLong((String) map.get("id"));
    }

    @Override
    protected String extractFirstName(Map<String, Object> map) {
        return (String) map.get("first_name");
    }

    @Override
    protected String extractLastName(Map<String, Object> map) {
        return (String) map.get("last_name");
    }
}
