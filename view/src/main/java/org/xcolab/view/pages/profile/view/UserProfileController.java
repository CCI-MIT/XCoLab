package org.xcolab.view.pages.profile.view;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.client.email.IEmailClient;

import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.IUserLoginRegisterClient;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.permissions.SystemRole;
import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.CountryUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.commons.servlet.flash.ErrorPage;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.util.http.exceptions.ExceptionUtils;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.profile.beans.MessageBean;
import org.xcolab.view.pages.profile.beans.NewsletterBean;
import org.xcolab.view.pages.profile.beans.UserBean;
import org.xcolab.view.pages.profile.utils.UserProfilePermissions;
import org.xcolab.view.pages.profile.wrappers.UserProfileWrapper;
import org.xcolab.view.pages.redballoon.utils.BalloonService;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile")
public class UserProfileController {

    private static final Logger _log = LoggerFactory.getLogger(UserProfileController.class);
    private static final String SHOW_PROFILE_VIEW = "profile/showUserProfile";
    private static final String EDIT_PROFILE_VIEW = "profile/editUserProfile";

    private static final Set<SystemRole> SEO_INDEXABLE_MEMBER_ROLES = EnumSet.of(
            SystemRole.STAFF, SystemRole.JUDGE, SystemRole.ADVISOR, SystemRole.FELLOW,
            SystemRole.IMPACT_ASSESSMENT_FELLOW, SystemRole.EXPERT);

    private final ActivityEntryHelper activityEntryHelper;
    private final AuthenticationService authenticationService;
    private final BalloonService balloonService;
    private final SmartValidator validator;
    private final IFileClient fileClient;
    private final IBalloonClient balloonClient;
    private final IEmailClient emailClient;
    private final IUserClient userClient;
    private final IPermissionClient permissionClient;
    private final IUserLoginRegisterClient userLoginRegisterClient;

    @Autowired
    public UserProfileController(ActivityEntryHelper activityEntryHelper,
            AuthenticationService authenticationService, BalloonService balloonService,
            SmartValidator validator, IFileClient fileClient, IBalloonClient balloonClient,
            IEmailClient emailClient, IUserClient userClient, IPermissionClient permissionClient,
            IUserLoginRegisterClient userLoginRegisterClient) {
        this.activityEntryHelper = activityEntryHelper;
        this.authenticationService = authenticationService;
        this.balloonService = balloonService;
        this.validator = validator;
        this.fileClient = fileClient;
        this.balloonClient = balloonClient;
        this.emailClient = emailClient;
        this.userClient = userClient;
        this.permissionClient = permissionClient;
        this.userLoginRegisterClient = userLoginRegisterClient;
    }

    @InitBinder("userBean")
    public void initUserWrapperBeanBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping
    public String showProfile(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member) throws IOException {
        if (member != null) {
            return "redirect:/members/profile/" + member.getId();
        }
        return new AccessDeniedPage(null).toViewName(response);
    }

    @GetMapping("{userId}")
    public String showUserProfileView(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper loggedInMember, @PathVariable long userId,
            @RequestParam(defaultValue = "false") boolean generateReferralLink) {
        try {
            UserWrapper member = userClient.getMember(userId);
            model.addAttribute("isUserProfileActive", member.isActive());
            if (member.isActive()) {
                UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
                model.addAttribute("permissions", permissions);
                model.addAttribute("_activePageLink", "community");

                final UserProfileWrapper currentUserProfile =
                        new UserProfileWrapper(userId, loggedInMember, activityEntryHelper);
                populateUserWrapper(currentUserProfile, model);
                model.addAttribute("allowSearchEngineIndexing",
                        shouldAllowIndexingForUser(currentUserProfile));

                final Boolean isSnpActive = ConfigurationAttributeKey.SNP_IS_ACTIVE.get();
                model.addAttribute("isSnpActive", isSnpActive);
                if (isSnpActive && currentUserProfile.isViewingOwnProfile()) {
                    String consentFormText = ConfigurationAttributeKey.SNP_CONSENT_FORM_TEXT.get();
                    model.addAttribute("consentFormText", consentFormText);
                    final Optional<IBalloonUserTracking> butOpt = balloonService
                            .getBalloonUserTracking(request, response);
                    if (butOpt.isPresent()) {
                        model.addAttribute("balloonLink", ExceptionUtils.getOrNull(
                                () -> balloonClient.getBalloonLink(butOpt.get().getUuid())));
                        model.addAttribute("balloonText", ExceptionUtils.getOrNull(
                                () -> balloonClient
                                        .getBalloonText(butOpt.get().getBalloonTextId())));
                    }
                }
                model.addAttribute("pointsActive",
                        ConfigurationAttributeKey.POINTS_IS_ACTIVE.get());
            }
        } catch (org.xcolab.client.user.exceptions.MemberNotFoundException e) {
            return ErrorPage.error("User profile not found").flashAndReturnView(request);
        }
        return SHOW_PROFILE_VIEW;
    }

    private boolean shouldAllowIndexingForUser(UserProfileWrapper userProfileWrapper) {
        return permissionClient.memberHasAnySystemRole(userProfileWrapper.getUserId(),
                SEO_INDEXABLE_MEMBER_ROLES) || !userProfileWrapper.getBadges().isEmpty();
    }

    private void populateUserWrapper(UserProfileWrapper currentUserProfile, Model model) {
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("userBean", currentUserProfile.getUserBean());
        model.addAttribute("messageBean", new MessageBean());
    }

    @GetMapping("{userId}/edit")
    public String showUserProfileEdit(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper loggedInMember, @PathVariable long userId) {

        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        if (!permissions.getCanEditMemberProfile(userId)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }
        model.addAttribute("permissions", permissions);
        model.addAttribute("_activePageLink", "community");

        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId,
                    loggedInMember, activityEntryHelper);
            populateUserWrapper(currentUserProfile, model);

            model.addAttribute("newsletterBean",
                    new NewsletterBean(currentUserProfile.getUserId()));
            model.addAttribute("newsletterActive",
                    ConfigurationAttributeKey.IS_MY_EMMA_ACTIVE.get());
            model.addAttribute("memberCategories", StaticUserContext.getUserCategoryClient().listMemberCategories());
            model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());

            model.addAttribute("isI18NActive", ConfigurationAttributeKey.IS_I18N_ACTIVE.get());

            model.addAttribute("languageSelectItems", I18nUtils.getSelectList());
            return EDIT_PROFILE_VIEW;
        } catch (org.xcolab.client.user.exceptions.MemberNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
    }

    @RequestMapping(params = "updateError=true")
    public String updateProfileError(HttpServletRequest request,
            Model model, UserWrapper loggedInMember,
            @RequestParam(required = false) boolean emailError,
            @RequestParam(required = false) boolean passwordError,
            @RequestParam Long userId) {
        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        model.addAttribute("permissions", permissions);
        model.addAttribute("updateError", true);
        if (emailError) {
            model.addAttribute("emailError", true);
        }
        if (passwordError) {
            model.addAttribute("passwordError", true);
        }
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId,
                    loggedInMember, activityEntryHelper);
            if (permissions.getCanEditMemberProfile(userId)) {
                model.addAttribute("newsletterBean",
                        new NewsletterBean(currentUserProfile.getUserBean().getUserId()));
                model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());
                model.addAttribute("isI18NActive", ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
                model.addAttribute("languageSelectItems", I18nUtils.getSelectList());
                return EDIT_PROFILE_VIEW;
            }
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for {}", userId);
            return "showProfileNotInitialized";
        }

        return SHOW_PROFILE_VIEW;
    }

    @PostMapping("{userId}/edit")
    public String updateUserProfile(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long userId, @ModelAttribute UserBean updatedUserBean,
            BindingResult result, UserWrapper loggedInMember)
            throws IOException, MemberNotFoundException {
        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        model.addAttribute("permissions", permissions);
        model.addAttribute("_activePageLink", "community");
        model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());
        model.addAttribute("isI18NActive", ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
        model.addAttribute("languageSelectItems", I18nUtils.getSelectList());

        if (!permissions.getCanEditMemberProfile(updatedUserBean.getUserId())
                || userId != updatedUserBean.getUserId()) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
        UserProfileWrapper currentUserProfile = new UserProfileWrapper(updatedUserBean.getUserId(),
                loggedInMember, activityEntryHelper);
        model.addAttribute("currentUserProfile", currentUserProfile);

        model.addAttribute("messageBean", new MessageBean());
        model.addAttribute("userBean", updatedUserBean);

        boolean changedUserPart = false;
        boolean validationError = false;
        if (StringUtils.isNotBlank(updatedUserBean.getPassword())) {
            final String currentPassword = updatedUserBean.getCurrentPassword();
            if (userLoginRegisterClient
                    .validatePassword(currentPassword.trim(), currentUserProfile.getUser().getId())
                    || (permissions.getCanAdmin() && StaticUserContext.getUserLoginRegister().validatePassword(
                    currentPassword.trim(), permissions.getLoggedInMember().getId()))) {
                validator.validate(updatedUserBean, result, UserBean.PasswordChanged.class);

                if (!result.hasErrors()) {
                    final String newPassword = updatedUserBean.getPassword().trim();
                    StaticUserContext.getUserLoginRegister().updatePassword(userId, newPassword);
                    changedUserPart = true;
                } else {
                    validationError = true;
                    return EDIT_PROFILE_VIEW;
                }
            } else {
                result.addError(
                        new ObjectError("currentPassword",
                                "Password change failed: Current password is incorrect."));
                validationError = true;
                return EDIT_PROFILE_VIEW;
            }
        }

        boolean eMailChanged = false;
        if (updatedUserBean.getEmail() != null && !updatedUserBean.getEmail().trim().isEmpty() &&
                !updatedUserBean.getEmail()
                        .equals(currentUserProfile.getUserBean().getEmailStored())) {
            validator.validate(updatedUserBean, result, UserBean.EmailChanged.class);

            if (!result.hasErrors()) {
                currentUserProfile.getUser().setEmailAddress(updatedUserBean.getEmail());
                changedUserPart = true;
                eMailChanged = true;
            } else {
                validationError = true;
                return EDIT_PROFILE_VIEW;
            }
        }

        if (updatedUserBean.getFirstName() != null
                && !updatedUserBean.getFirstName()
                .equals(currentUserProfile.getUserBean().getFirstName())) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors()) {
                currentUserProfile.getUser()
                        .setFirstName(HtmlUtil.cleanAll(updatedUserBean.getFirstName()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("First name change failed for userId: {}",
                        currentUserProfile.getUser().getId());
            }
        }
        if (updatedUserBean.getLastName() != null
                && !updatedUserBean.getLastName()
                .equals(currentUserProfile.getUserBean().getLastName())) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors()) {
                currentUserProfile.getUser()
                        .setLastName(HtmlUtil.cleanAll(updatedUserBean.getLastName()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Last name change failed for userId: {}",
                        currentUserProfile.getUser().getId());
            }
        }

        if (updatedUserBean.getCountryName() != null) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors() && !updatedUserBean.getCountryName().isEmpty()) {
                currentUserProfile.getUser()
                        .setCountry(HtmlUtil.cleanAll(updatedUserBean.getCountryCode()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Country name change failed for userId: {}",
                        currentUserProfile.getUser().getId());
            }
        }

        if (updatedUserBean.getDefaultLocale() != null) {

            if (!result.hasErrors() && !updatedUserBean.getDefaultLocale().isEmpty()) {
                currentUserProfile.getUser()
                        .setDefaultLocale(HtmlUtil.cleanAll(updatedUserBean.getDefaultLocale()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Default language locale change failed for userId: {}",
                        currentUserProfile.getUser().getId());
            }
        }

        changedUserPart = changedUserPart | updateUserProfile(currentUserProfile, updatedUserBean);

        if (validationError) {
            updatedUserBean.setImageId(currentUserProfile.getUserBean().getImageId());
            return EDIT_PROFILE_VIEW;
        } else if (changedUserPart) {
            updatedUserBean.setImageId(currentUserProfile.getUser().getPortraitFileEntryId());

            userClient.updateUser(currentUserProfile.getUser());

            if (eMailChanged) {
                updatedUserBean.setEmailStored(updatedUserBean.getEmail());
                sendUpdatedEmail(currentUserProfile.getUser());
            }
            AlertMessage.CHANGES_SAVED.flash(request);
        }
        return "redirect:" + currentUserProfile.getUser().getProfileLinkUrl();
    }

    private boolean updateUserProfile(UserProfileWrapper currentUserProfile,
            UserBean updatedUserBean) {

        UserWrapper member = currentUserProfile.getUser();

        String existingBio = member.getShortBio();
        if (existingBio == null) {
            existingBio = "";
        }

        boolean changedMember = false;
        if (!existingBio.equals(updatedUserBean.getShortBio())) {
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            String updatedShortBio = updatedUserBean.getShortBio();
            updatedShortBio = HtmlUtil.linkifyUrlsInHtml(updatedShortBio);
            updatedShortBio = HtmlUtil.cleanSome(updatedShortBio, baseUri);
            member.setShortBio(updatedShortBio);
            updatedUserBean.setShortBio(updatedShortBio);
            changedMember = true;
        }

        String existingCountry = member.getCountry();
        if (!StringUtils.isEmpty(existingCountry)) {
            if (CountryUtil.getCodeForCounty(existingCountry) != null) {
                existingCountry = CountryUtil.getCodeForCounty(existingCountry);
            }
        }
        if (updatedUserBean.getCountryCode() != null && !updatedUserBean.getCountryCode()
                .equals(existingCountry)
                && !StringUtils.isEmpty(updatedUserBean.getCountryCode())) {
            member.setCountry(updatedUserBean.getCountryCode());
            changedMember = true;
        }

        final long newImageId = updatedUserBean.getImageId();
        if (newImageId != currentUserProfile.getUserBean().getImageId()) {

            if (newImageId > 0) {
                IFileEntry fe = fileClient.getFileEntry(newImageId).orElseThrow(
                        () -> new IllegalStateException(
                                "No file entry found for imageId " + newImageId
                                        + " for UserWrapper " +
                                        updatedUserBean.getUserId()));
                currentUserProfile.getUser().setPortraitFileEntryId(fe.getId());
                changedMember = true;
            } else {
                currentUserProfile.getUser().setPortraitFileEntryId(0L);
                changedMember = true;
            }
        }

        final MessagingUserPreferenceWrapper messagingPreferences = StaticUserContext.getMessagingClient()
                .getMessagingPreferences(currentUserProfile.getUser().getId());
        boolean changedMessagingPreferences = false;
        if (updatedUserBean.getSendEmailOnMessage() != messagingPreferences.getEmailOnReceipt()) {
            messagingPreferences.setEmailOnReceipt(updatedUserBean.getSendEmailOnMessage());
            changedMessagingPreferences = true;
        }

        if (updatedUserBean.getSendEmailOnActivity() != messagingPreferences.getEmailOnActivity()) {
            messagingPreferences.setEmailOnActivity(updatedUserBean.getSendEmailOnActivity());
            changedMessagingPreferences = true;
        }

        if (updatedUserBean.getSendDailyEmailOnActivity() != messagingPreferences
                .getEmailActivityDailyDigest()) {
            messagingPreferences
                    .setEmailActivityDailyDigest(updatedUserBean.getSendDailyEmailOnActivity());
            changedMessagingPreferences = true;
        }

        if (changedMessagingPreferences) {
            StaticUserContext.getMessagingClient().updateMessagingPreferences(
                    currentUserProfile.getUser().getId(),messagingPreferences.getId(),
                    messagingPreferences);
        }
        if (changedMember) {
            try {
                userClient.updateUser(currentUserProfile.getUser());
            } catch (MemberNotFoundException ignore) {

            }
        }

        return changedMember || changedMessagingPreferences;
    }

    private void sendUpdatedEmail(UserWrapper user) {
        String messageSubject = TemplateReplacementUtil
                .replacePlatformConstants(
                        "Your email address on the <colab-name/> has been updated");
        String messageBody = TemplateReplacementUtil
                .replacePlatformConstants("Dear " + user.getFirstName() + ",\n" +
                        "\n" +
                        "This is an automated message to confirm that you recently updated your "
                        + "email address on the <colab-name/> website.\n"
                        +
                        "\n" +
                        "Your username:  " + user.getScreenName() + "\n" +
                        "Your updated email address: " + user.getEmailAddress() + "\n" +
                        "\n" +
                        "You can login with your username at <colab-url/>.  If you have any "
                        + "questions or need additional help, simply reply to this message.\n"
                        +
                        "\n" +
                        "Thank you for engaging on the <colab-name/>!\n");

        InternetAddress addressFrom = TemplateReplacementUtil.getAdminFromEmailAddress();

        emailClient.sendEmail(addressFrom.getAddress(), ConfigurationAttributeKey.COLAB_NAME.get(),
                user.getEmailAddress(), messageSubject,
                messageBody, false, addressFrom.getAddress(),
                ConfigurationAttributeKey.COLAB_NAME.get(), user.getId());
    }

    @PostMapping("{userId}/delete")
    public void deleteUserProfile(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long userId, UserWrapper loggedInMember,
            @RequestParam(required = false, defaultValue = "false") boolean anonymize)
            throws IOException, MemberNotFoundException {
        UserProfilePermissions permission = new UserProfilePermissions(loggedInMember);

        if (anonymize && permission.getCanAdmin()) {
            UserWrapper memberToAnonymize = new UserWrapper(userClient.getMember(userId));
            memberToAnonymize.anonymize();
            userClient.updateUser(memberToAnonymize);
            userClient.unsubscribeToNewsletter(userId);
        }

        if (permission.getCanEditMemberProfile(userId)) {
            userClient.deleteUser(userId);
        }

        if (userId == loggedInMember.getId()) {
            authenticationService.logout(request, response);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/members");
        }
    }
}
