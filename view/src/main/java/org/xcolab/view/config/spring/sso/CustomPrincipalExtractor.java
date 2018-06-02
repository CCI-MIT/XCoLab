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
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.login.spring.MemberDetails;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

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
     * This method allows subclasses to update additional fields of the member object.
     *
     * Changes to the Member object will be persisted automatically, this method does not need to
     * call update on the Member itself.
     *
     * @param member The member object to be modified.
     */
    protected void updateAdditionalInformation(Member member, Map<String, Object> userInfoMap) {
        //Do nothing by default
    };

    /**
     * This method extracts this SSO services ID from the user info map.
     */
    protected abstract IdT extractId(Map<String, Object> userInfoMap);

    /**
     * This method extracts the email address from the user info map.
     */
    protected String extractEmailAddress(Map<String, Object> userInfoMap) {
        return (String) userInfoMap.get("email");
    }

    /**
     * This method extracts the first name the user info map.
     */
    protected abstract String extractFirstName(Map<String, Object> userInfoMap);

    /**
     * This method extracts the last name from the user info map.
     */
    protected abstract String extractLastName(Map<String, Object> userInfoMap);

    /**
     * This method extracts the last name from the user info map.
     */
    protected abstract Optional<String> extractProfileImageUrl(Map<String, Object> userInfoMap);

    @Override
    public Object extractPrincipal(Map<String, Object> userInfoMap) {
        log.debug("Extracting principal from user info map: {}", userInfoMap);
        final IdT ssoId = extractId(userInfoMap);
        if (ssoId == null) {
            throw new InternalException("Could not extract ssoId from User Info map.");
        }
        final String emailAddress = extractEmailAddress(userInfoMap);
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

                if (MembersClient.isEmailUsed(emailAddress)) {
                    // Email is already used (e.g. by a deleted member)

                    // Invalidate session, otherwise the exception messes up the OAuthClientContext
                    RequestUtil.getRequest().getSession().invalidate();

                    AlertMessage.danger("An account using your email address was previously deleted.")
                            .flash(RequestUtil.getRequest());
                    throw new EmailUsedByDeletedMemberException(emailAddress);
                }

                String firstName = extractFirstName(userInfoMap);
                if (firstName == null) {
                    throw new InternalException("Could not extract firstName from User Info map.");
                }
                String lastName = extractLastName(userInfoMap);
                if (lastName == null) {
                    throw new InternalException("Could not extract lastName from User Info map.");
                }
                final Locale locale = LocaleUtils.toLocale((String) userInfoMap.get("locale"));
                String country;
                String language;
                if (locale != null) {
                    country = StringUtils.isEmpty(locale.getCountry()) ? null : locale.getCountry();
                    language = I18nUtils.getSupportedLanguage(locale);
                } else {
                    log.debug("No country found. Setting defaults...");
                    country = null;
                    language = I18nUtils.DEFAULT_LANGUAGE;
                }

                final Optional<String> pictureUrlOpt = extractProfileImageUrl(userInfoMap);
                Long imageId = null;
                if (pictureUrlOpt.isPresent()) {
                    imageId = ImageUploadUtils.linkProfilePicture(pictureUrlOpt.get());
                }

                Member member = loginRegisterService
                        .register(null, null, emailAddress, firstName, lastName,
                                null, country, imageId, false, language);

                setSsoId(member, ssoId);
                updateAdditionalInformation(member, userInfoMap);
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

    public static class EmailUsedByDeletedMemberException extends RuntimeException {
        public EmailUsedByDeletedMemberException(String email) {
            super("Email " + email + " is already in use by a deleted member.");
        }
    }
}
