package org.xcolab.view.config.spring.sso;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Map;

public class ColabPrincipalExtractor extends AbstractOpenIdPrincipalExtractor {

    public ColabPrincipalExtractor(LoginRegisterService loginRegisterService,
            MemberDetailsService memberDetailsService) {
        super(loginRegisterService, memberDetailsService);
    }

    @Override
    protected MemberDetails loadFromSsoId(String colabSsoId) throws UsernameNotFoundException {
        return memberDetailsService.loadUserByColabSsoId(colabSsoId);
    }

    @Override
    protected void setSsoId(UserWrapper member, String colabSsoId) {
        member.setColabSsoId(colabSsoId);
    }


    @Override
    protected String extractId(Map map) {
        return (String) map.get("sub");
    }
}
