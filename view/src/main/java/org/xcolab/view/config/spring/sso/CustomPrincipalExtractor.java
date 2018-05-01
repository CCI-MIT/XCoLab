package org.xcolab.view.config.spring.sso;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Locale;
import java.util.Map;

public abstract class CustomPrincipalExtractor<IdT> implements PrincipalExtractor {

    private static final Logger log = LoggerFactory.getLogger(CustomPrincipalExtractor.class);

    private final LoginRegisterService loginRegisterService;
    protected final MemberDetailsService memberDetailsService;

    public CustomPrincipalExtractor(LoginRegisterService loginRegisterService,
            MemberDetailsService memberDetailsService) {
        this.loginRegisterService = loginRegisterService;
        this.memberDetailsService = memberDetailsService;
    }

    /**
     * This method attempts to load a MemberDetails from the id.
     * @param ssoId The user's ID in this SSO service.
     * @return MemberDetails, if found, or null otherwise.
     */
    protected abstract MemberDetails loadFromSsoId(IdT ssoId) throws UsernameNotFoundException;

    /**
     * This method sets the member's ID in this SSO service.
     * @param member The member object, which will have the SSO service's ID field modified.
     * @param ssoId The sso ID to be set.
     */
    protected abstract void setSsoId(Member member, IdT ssoId);

    /**
     * This method extracts this SSO services ID from the user info map.
     * @param map The user info map.
     */
    protected abstract IdT extractId(Map<String, Object> map);

    /**
     * This method extracts the first name the user info map.
     * @param map The user info map.
     */
    protected abstract String extractFirstName(Map<String, Object> map);

    /**
     * This method extracts the last name from the user info map.
     * @param map The user info map.
     */
    protected abstract String extractLastName(Map<String, Object> map);

    /**
     * This method extracts the email address from the user info map.
     * @param map The user info map.
     */
    protected String extractEmailAddress(Map<String, Object> map) {
        return (String) map.get("email");
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        final IdT ssoId = extractId(map);
        if (ssoId == null) {
            throw new InternalException("Could not extract ssoId from User Info map.");
        }
        final String emailAddress = extractEmailAddress(map);
        if (emailAddress == null) {
            throw new InternalException("Could not extract email address from User Info map.");
        }
        MemberDetails memberDetails;
        try {
            memberDetails = loadFromSsoId(ssoId);
        } catch (UsernameNotFoundException e) {
            try {
                memberDetails = memberDetailsService.loadByEmail(emailAddress);
                final Member member = memberDetails.getMember();
                setSsoId(member, ssoId);
                MembersClient.updateMember(member);
            } catch (UsernameNotFoundException e2) {
                log.debug("No user found for sssId={} or email={}. Generating profile...",
                        ssoId, emailAddress);

                String firstName = extractFirstName(map);
                String lastName = extractLastName(map);
                final Locale locale = LocaleUtils.toLocale((String) map.get("locale"));
                String country;
                String language;
                if (locale != null) {
                    country = StringUtils.isEmpty(locale.getCountry()) ? null : locale.getCountry();
                    language = I18nUtils.getSupportedLanguage(locale);
                } else {
                    country = null;
                    language = I18nUtils.DEFAULT_LANGUAGE;
                }

                Member member = loginRegisterService
                        .register(null, null, emailAddress, firstName, lastName,
                                null, country, null, false, language);

                setSsoId(member, ssoId);
                MembersClient.updateMember(member);

                //TODO: how do we get the cookies/request here?
                //                    loginRegisterService.updateBalloonTracking(member,
                // request);
                loginRegisterService.recordRegistrationEvent(member);

                memberDetails = new MemberDetails(member);
            }
        }
        return memberDetails;
    }
}
