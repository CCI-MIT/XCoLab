package org.xcolab.view.config.spring.sso;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

import javax.servlet.http.HttpServletRequest;

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
    protected abstract void setSsoId(UserWrapper member, IdT ssoId);

    /**
     * This method allows subclasses to update additional fields of the member object.
     *
     * Changes to the Member object will be persisted automatically, this method does not need to
     * call update on the Member itself.
     *
     * @param member The member object to be modified.
     */
    protected void updateAdditionalInformation(UserWrapper member, Map<String, Object> userInfoMap) {
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
     * This method determines whether ownership of the account implies ownership of the email.
     *
     * This is important to avoid someone taking over your xCoLab account by creating a fake
     * social media account with your email.
     */
    protected abstract boolean isExtractedEmailVerified(Map<String, Object> userInfoMap);

    /**
     * This method extracts the locale from the user info map.
     */
    protected String extractLocaleString(Map<String, Object> userInfoMap) {
        return (String) userInfoMap.get("locale");
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
        final HttpServletRequest request = RequestUtil.getRequest();
        if (emailAddress == null) {
            // Invalidate session, otherwise the exception messes up the OAuthClientContext
            request.getSession().invalidate();
            throw new NoEmailReceivedOauthException();
        }
        MemberDetails memberDetails;
        try {
            memberDetails = loadFromSsoId(ssoId);
        } catch (UsernameNotFoundException e) {
            try {
                memberDetails = memberDetailsService.loadByEmail(emailAddress);
                if (isExtractedEmailVerified(userInfoMap)) {
                    final UserWrapper member = memberDetails.getMember();
                    setSsoId(member, ssoId);
                    try{
                        StaticUserContext.getUserClient().updateUser(member);
                    }catch (MemberNotFoundException ignored){

                    }
                } else {
                    AlertMessage.danger("An account with the email address " + emailAddress
                            + " already exists, but cannot be linked because it is not verified.")
                            .flash(request);
                    // Invalidate session, otherwise the exception messes up the OAuthClientContext
                    request.getSession().invalidate();
                    throw new UnverifiedEmailAddressException();
                }
            } catch (UsernameNotFoundException e2) {
                log.debug("No user found for sssId={} or email={}. Generating profile...",
                        ssoId, emailAddress);

                if (StaticUserContext.getUserLoginRegister().isEmailUsed(emailAddress)) {
                    // Email is already used (e.g. by a deleted member)

                    // Invalidate session, otherwise the exception messes up the OAuthClientContext
                    request.getSession().invalidate();
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

                final String localeString = extractLocaleString(userInfoMap);
                Locale locale = getLocale(localeString);
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

                UserWrapper member = loginRegisterService
                        .register(null, null, emailAddress, firstName, lastName,
                                null, country, imageId, false, language);

                setSsoId(member, ssoId);
                updateAdditionalInformation(member, userInfoMap);
                try{
                    StaticUserContext.getUserClient().updateUser(member);
                }catch (MemberNotFoundException ignored){

                }

                //TODO: how do we get the cookies/request here?
                //                    loginRegisterService.updateBalloonTracking(member,
                // request);
                loginRegisterService.recordRegistrationEvent(member);

                memberDetails = new MemberDetails(member);
            }
        }
        return memberDetails;
    }

    private Locale getLocale(String localeString) {
        Locale locale;
        try {
            locale = LocaleUtils.toLocale(localeString);
        } catch (IllegalArgumentException e3) {
            log.warn("Error while parsing locale: {}", e3.getMessage());
            locale = null;
        }
        return locale;
    }

    public static class EmailUsedByDeletedMemberException extends AuthenticationException {
        public EmailUsedByDeletedMemberException(String email) {
            super("Email " + email + " is already in use by a deleted member.");
        }
    }

    public static class NoEmailReceivedOauthException extends AuthenticationException {
        public NoEmailReceivedOauthException() {
            super("No email was received in user info map.");
        }
    }

    public static class UnverifiedEmailAddressException extends AuthenticationException {
        public UnverifiedEmailAddressException() {
            super("An account with this email address already exists, "
                    + "but cannot be linked because it is not verified.");
        }
    }
}
