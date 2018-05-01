package org.xcolab.view.config.spring.sso;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Map;
import java.util.Optional;

public class ColabPrincipalExtractor extends CustomPrincipalExtractor<String> {

    public ColabPrincipalExtractor(LoginRegisterService loginRegisterService,
            MemberDetailsService memberDetailsService) {
        super(loginRegisterService, memberDetailsService);
    }

    @Override
    protected MemberDetails loadFromSsoId(String colabSsoId) throws UsernameNotFoundException {
        return memberDetailsService.loadUserByColabSsoId(colabSsoId);
    }

    @Override
    protected void setSsoId(Member member, String colabSsoId) {
        member.setColabSsoId(colabSsoId);
    }

    @Override
    protected String extractFirstName(Map<String, Object> map) {
        return (String) map.get("firstName");
    }

    @Override
    protected String extractLastName(Map<String, Object> map) {
        return (String) map.get("lastName");
    }

    @Override
    protected String extractId(Map map) {
        return (String) map.get("id");
    }

    @Override
    protected Optional<String> extractProfileImageUrl(Map<String, Object> userInfoMap) {
        if (userInfoMap.containsKey("picture")) {
            return Optional.of((String) userInfoMap.get("picture"));
        }
        return Optional.empty();
    }
}
