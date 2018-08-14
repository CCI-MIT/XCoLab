package org.xcolab.view.config.spring.sso;

import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractOpenIdPrincipalExtractor extends CustomPrincipalExtractor<String> {

    public AbstractOpenIdPrincipalExtractor(LoginRegisterService loginRegisterService,
            MemberDetailsService memberDetailsService) {
        super(loginRegisterService, memberDetailsService);
    }

    @Override
    protected String extractId(Map map) {
        return (String) map.get("sub");
    }

    @Override
    public boolean isExtractedEmailVerified(Map<String, Object> map) {
        return map.containsKey("email_verified") && (Boolean) map.get("email_verified");
    }

    @Override
    protected String extractFirstName(Map<String, Object> map) {
        return (String) map.get("given_name");
    }

    @Override
    protected String extractLastName(Map<String, Object> map) {
        return (String) map.get("family_name");
    }

    @Override
    protected Optional<String> extractProfileImageUrl(Map<String, Object> userInfoMap) {
        //TODO: deactivated until we can determine if it's the default picture
//        if (userInfoMap.containsKey("picture")) {
//            return Optional.of((String) userInfoMap.get("picture"));
//        }
        return Optional.empty();
    }
}
