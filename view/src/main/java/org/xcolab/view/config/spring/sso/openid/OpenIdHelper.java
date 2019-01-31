package org.xcolab.view.config.spring.sso.openid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.login.spring.MemberDetails;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class OpenIdHelper {

    public static final String OAUTH_USERINFO_ENDPOINT = "/oauth/userinfo";
    public static final String OAUTH_AUTHORIZE_ENDPOINT = "/oauth/authorize";
    public static final String OAUTH_TOKEN_ENDPOINT = "/oauth/token";

    //Claims
    public static final String ISSUER = "iss";
    public static final String AUDIENCE = "aud";
    public static final String ISSUE_DATE = "iat";
    public static final String EXPIRATION_DATE = "exp";
    public static final String SUBJECT = "sub";
    public static final String EMAIL = "email";
    public static final String EMAIL_VERIFIED = "email_verified";
    public static final String NAME = "name";
    public static final String GIVEN_NAME = "given_name";
    public static final String FAMILY_NAME = "family_name";
    public static final String PICTURE = "picture";
    public static final String PREFERRED_USERNAME = "preferred_username";

    //Scopes
    public static final String SCOPE_OPENID = "openid";
    public static final String SCOPE_EMAIL = "email";
    public static final String SCOPE_PROFILE = "profile";


    private static final String URL = PlatformAttributeKey.COLAB_URL.get();

    private final OAuth2Authentication authentication;

    public OpenIdHelper(OAuth2Authentication authentication) {
        this.authentication = authentication;
    }

    public static Map<String, Object> getOpenIdConfiguration() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("issuer", URL);
        map.put("authorization_endpoint", URL + OAUTH_AUTHORIZE_ENDPOINT);
        map.put("token_endpoint", URL + OAUTH_TOKEN_ENDPOINT);
        map.put("userinfo_endpoint", URL + OAUTH_USERINFO_ENDPOINT);
        map.put("token_endpoint_auth_methods_supported",
                new String[]{"client_secret_basic", "client_secret_post"});

        map.put("scopes_supported", new String[]{SCOPE_OPENID, SCOPE_EMAIL, SCOPE_PROFILE});

        map.put("claims_supported", new String[]{ISSUER, AUDIENCE, ISSUE_DATE, EXPIRATION_DATE,
                SUBJECT, EMAIL, EMAIL_VERIFIED, NAME, GIVEN_NAME, FAMILY_NAME, PREFERRED_USERNAME, PICTURE});
        return map;
    }

    public UserWrapper getMember() {
        final Object principal = authentication.getPrincipal();
        if (!(principal instanceof MemberDetails)) {
            throw new IllegalStateException("Invalid principal: " + principal);
        }
        MemberDetails memberDetails = (MemberDetails) principal;
        return memberDetails.getMember();
    }

    public Map<String, Object> getIdTokenMap() {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put(ISSUER, URL);
        map.put(AUDIENCE, authentication.getOAuth2Request().getClientId());
        final Instant now = Instant.now();
        map.put(ISSUE_DATE, now.toEpochMilli());
        map.put(EXPIRATION_DATE, now.plus(1, ChronoUnit.DAYS).toEpochMilli());
        final UserWrapper member = getMember();
        addSubjectField(map, member);
        if (hasEmailScope()) {
            addEmailScopedFields(map, member);
        }
        if (hasProfileScope()) {
            addProfileScopedFields(map, member);
        }
        return map;
    }

    public boolean hasOpenIdScope() {
        return authentication.getOAuth2Request().getScope().contains(SCOPE_OPENID);
    }

    public boolean hasEmailScope() {
        return authentication.getOAuth2Request().getScope().contains(SCOPE_EMAIL);
    }

    public boolean hasProfileScope() {
        return authentication.getOAuth2Request().getScope().contains(SCOPE_PROFILE);
    }

    public void addSubjectField(Map<String, Object> map, UserWrapper member) {
        map.put(SUBJECT, member.getId());
    }

    public void addEmailScopedFields(Map<String, Object> map, UserWrapper member) {
        map.put(EMAIL, member.getEmailAddress());
        map.put(EMAIL_VERIFIED, member.isIsEmailConfirmed());
    }

    public void addProfileScopedFields(Map<String, Object> map, UserWrapper member) {
        map.put(NAME, member.getFullName());
        map.put(GIVEN_NAME, member.getFirstName());
        map.put(FAMILY_NAME, member.getLastName());
        map.put(PREFERRED_USERNAME, member.getScreenName());
        if (StringUtils.isNotEmpty(member.getAbsoluteImageUrl())) {
            map.put(PICTURE, member.getAbsoluteImageUrl());
        }
    }
}
