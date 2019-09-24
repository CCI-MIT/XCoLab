package org.xcolab.view.config.spring.sso;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Map;
import java.util.Optional;

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
    protected void setSsoId(UserWrapper member, Long ssoId) {
        member.setFacebookId(ssoId);
    }

    @Override
    protected boolean isExtractedEmailVerified(Map<String, Object> userInfoMap) {
        // Facebook does not return emails that were not verified
        return true;
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

    @Override
    protected Optional<String> extractProfileImageUrl(Map<String, Object> userInfoMap) {
        //TODO: deactivated until it can be fixed
        if (false) {
            //noinspection unchecked
            final Map<String, Map<String, String>> picture = (Map<String, Map<String, String>>) userInfoMap.get("picture");
            //TODO: check if it's the default picture
            return Optional.of(picture.get("data").get("url"));
        }
        return Optional.empty();
    }
}
