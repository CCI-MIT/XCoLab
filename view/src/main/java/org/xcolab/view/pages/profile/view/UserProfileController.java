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
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.commons.CountryUtil;
import org.xcolab.commons.html.HtmlUtil;
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
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.ErrorPage;

import java.io.IOException;
import java.util.Optional;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile")
public class UserProfileController {

    private static final Logger _log = LoggerFactory.getLogger(UserProfileController.class);
    private static final String SHOW_PROFILE_VIEW = "profile/showUserProfile";
    private static final String EDIT_PROFILE_VIEW = "profile/editUserProfile";

    private final ActivityEntryHelper activityEntryHelper;
    private final AuthenticationService authenticationService;
    private final BalloonService balloonService;

    private final SmartValidator validator;

    @Autowired
    public UserProfileController(ActivityEntryHelper activityEntryHelper,
            AuthenticationService authenticationService, BalloonService balloonService,
            SmartValidator validator) {
        this.activityEntryHelper = activityEntryHelper;
        this.authenticationService = authenticationService;
        this.balloonService = balloonService;
        this.validator = validator;
    }

    @InitBinder("userBean")
    public void initUserWrapperBeanBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping
    public String showProfile(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member) throws IOException {
        if (member != null) {
            return "redirect:/members/profile/" + member.getId_();
        }
        return new AccessDeniedPage(null).toViewName(response);
    }

    @GetMapping("{memberId}")
    public String showUserProfileView(HttpServletRequest request, HttpServletResponse response,
            Model model, Member loggedInMember, @PathVariable long memberId,
            @RequestParam(defaultValue = "false") boolean generateReferralLink) {
        try {
            UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
            model.addAttribute("permissions", permissions);
            model.addAttribute("_activePageLink", "community");

            final UserProfileWrapper currentUserProfile =
                    new UserProfileWrapper(memberId, loggedInMember, activityEntryHelper);
            populateUserWrapper(currentUserProfile, model);

            final Boolean isSnpActive = ConfigurationAttributeKey.SNP_IS_ACTIVE.get();
            model.addAttribute("isSnpActive", isSnpActive);
            if (isSnpActive && currentUserProfile.isViewingOwnProfile()) {
                String consentFormText = ConfigurationAttributeKey.SNP_CONSENT_FORM_TEXT.get();
                model.addAttribute("consentFormText", consentFormText);
                final Optional<BalloonUserTracking> butOpt = balloonService
                        .getBalloonUserTracking(request, response);
                if (butOpt.isPresent()) {
                    model.addAttribute("balloonLink", butOpt.get().getBalloonLink());
                    model.addAttribute("balloonText", butOpt.get().getBalloonText());
                }
            }
            model.addAttribute("pointsActive", ConfigurationAttributeKey.POINTS_IS_ACTIVE.get());
            return SHOW_PROFILE_VIEW;
        } catch (MemberNotFoundException e) {
            return ErrorPage.error("User profile not found").flashAndReturnView(request);
        }
    }

    private void populateUserWrapper(UserProfileWrapper currentUserProfile, Model model) {
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("userBean", currentUserProfile.getUserBean());
        model.addAttribute("messageBean", new MessageBean());
    }

    @GetMapping("{memberId}/edit")
    public String showUserProfileEdit(HttpServletRequest request, HttpServletResponse response,
            Model model, Member loggedInMember, @PathVariable long memberId) {

        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        if (!permissions.getCanEditMemberProfile(memberId)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }
        model.addAttribute("permissions", permissions);
        model.addAttribute("_activePageLink", "community");

        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(memberId,
                    loggedInMember, activityEntryHelper);
            populateUserWrapper(currentUserProfile, model);

            model.addAttribute("newsletterBean",
                    new NewsletterBean(currentUserProfile.getUserId()));
            model.addAttribute("newsletterActive",
                    ConfigurationAttributeKey.IS_MY_EMMA_ACTIVE.get());
            model.addAttribute("memberCategories", MembersClient.listMemberCategories());
            model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());

            model.addAttribute("isI18NActive",ConfigurationAttributeKey.IS_I18N_ACTIVE.get());

            model.addAttribute("languageSelectItems", I18nUtils.getSelectList());
            return EDIT_PROFILE_VIEW;
        } catch (MemberNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
    }

    @RequestMapping(params = "updateError=true")
    public String updateProfileError(HttpServletRequest request,
            Model model, Member loggedInMember,
            @RequestParam(required = false) boolean emailError,
            @RequestParam(required = false) boolean passwordError,
            @RequestParam Long memberId) {
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
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(memberId,
                    loggedInMember, activityEntryHelper);
            if (permissions.getCanEditMemberProfile(memberId)) {
                model.addAttribute("newsletterBean",
                        new NewsletterBean(currentUserProfile.getUserBean().getUserId()));
                model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());
                model.addAttribute("isI18NActive",ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
                model.addAttribute("languageSelectItems", I18nUtils.getSelectList());
                return EDIT_PROFILE_VIEW;
            }
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for {}", memberId);
            return "showProfileNotInitialized";
        }

        return SHOW_PROFILE_VIEW;
    }

    @PostMapping("{memberId}/edit")
    public String updateUserProfile(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long memberId, @ModelAttribute UserBean updatedUserBean,
            BindingResult result, Member loggedInMember)
            throws IOException, MemberNotFoundException {
        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        model.addAttribute("permissions", permissions);
        model.addAttribute("_activePageLink", "community");
        model.addAttribute("countrySelectItems", CountryUtil.getSelectOptions());
        model.addAttribute("isI18NActive",ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
        model.addAttribute("languageSelectItems", I18nUtils.getSelectList());

        if (!permissions.getCanEditMemberProfile(updatedUserBean.getUserId())
                || memberId != updatedUserBean.getUserId()) {
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
            final boolean isAdminEditingOtherProfile =
                    permissions.getCanAdmin() && !currentUserProfile.isViewingOwnProfile();
            final String currentPassword = updatedUserBean.getCurrentPassword();
            if (MembersClient.validatePassword(currentPassword.trim(), currentUserProfile.getUser().getUserId())
                    || isAdminEditingOtherProfile) {
                validator.validate(updatedUserBean, result, UserBean.PasswordChanged.class);

                if (!result.hasErrors()) {
                    final String newPassword = updatedUserBean.getPassword().trim();
                    MembersClient.updatePassword(memberId, newPassword);
                    changedUserPart = true;
                } else {
                    validationError = true;
                    return EDIT_PROFILE_VIEW;
                }
            } else {
                result.addError(
                        new ObjectError("currentPassword", "Password change failed: Current password is incorrect."));
                validationError = true;
                return EDIT_PROFILE_VIEW;
            }
        }

        boolean eMailChanged = false;
        if (updatedUserBean.getEmail() != null && !updatedUserBean.getEmail().trim().isEmpty() &&
                !updatedUserBean.getEmail().equals(currentUserProfile.getUserBean().getEmailStored())) {
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
                && !updatedUserBean.getFirstName().equals(currentUserProfile.getUserBean().getFirstName())) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors()) {
                currentUserProfile.getUser().setFirstName(HtmlUtil.cleanAll(updatedUserBean.getFirstName()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("First name change failed for userId: {}", currentUserProfile.getUser().getId_());
            }
        }
        if (updatedUserBean.getLastName() != null
                && !updatedUserBean.getLastName().equals(currentUserProfile.getUserBean().getLastName())) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors()) {
                currentUserProfile.getUser().setLastName(HtmlUtil.cleanAll(updatedUserBean.getLastName()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Last name change failed for userId: {}", currentUserProfile.getUser().getId_());
            }
        }

        if (updatedUserBean.getCountryName() != null) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors() && !updatedUserBean.getCountryName().isEmpty()) {
                currentUserProfile.getUser().setCountry(HtmlUtil.cleanAll(updatedUserBean.getCountryCode()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Country name change failed for userId: {}", currentUserProfile.getUser().getId_());
            }
        }

        if (updatedUserBean.getDefaultLocale() != null) {

            if (!result.hasErrors() && !updatedUserBean.getDefaultLocale().isEmpty()) {
                currentUserProfile.getUser().setDefaultLocale(HtmlUtil.cleanAll(updatedUserBean.getDefaultLocale()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Default language locale change failed for userId: {}", currentUserProfile.getUser().getId_());
            }
        }

        changedUserPart = changedUserPart | updateUserProfile(currentUserProfile, updatedUserBean);

        if (validationError) {
            updatedUserBean.setImageId(currentUserProfile.getUserBean().getImageId());
            return EDIT_PROFILE_VIEW;
        } else if (changedUserPart) {
            updatedUserBean.setImageId(currentUserProfile.getUser().getPortraitFileEntryId());

            MembersClient.updateMember(currentUserProfile.getUser());

            if (eMailChanged) {
                updatedUserBean.setEmailStored(updatedUserBean.getEmail());
                sendUpdatedEmail(currentUserProfile.getUser());
            }
            AlertMessage.CHANGES_SAVED.flash(request);
            return SHOW_PROFILE_VIEW;
        } else {
            return "redirect:/members/profile/" + memberId;
        }
    }

    private boolean updateUserProfile(UserProfileWrapper currentUserProfile, UserBean updatedUserBean) {

        Member member = currentUserProfile.getUser();

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
        if (updatedUserBean.getCountryCode() != null && !updatedUserBean.getCountryCode().equals(existingCountry)
                && !StringUtils.isEmpty(updatedUserBean.getCountryCode())) {
            member.setCountry(updatedUserBean.getCountryCode());
            changedMember = true;
        }

        final long newImageId = updatedUserBean.getImageId();
        if (newImageId != currentUserProfile.getUserBean().getImageId()) {

            if (newImageId > 0) {
                FileEntry fe = FilesClient.getFileEntry(newImageId).orElseThrow(
                        () -> new IllegalStateException(
                                "No file entry found for imageId " + newImageId + " for member " +
                                        updatedUserBean.getUserId()));
                currentUserProfile.getUser().setPortraitFileEntryId(fe.getFileEntryId());
                changedMember = true;
            } else {
                currentUserProfile.getUser().setPortraitFileEntryId(0L);
                changedMember = true;
            }
        }

        final MessagingUserPreferences messagingPreferences = MessagingClient
                .getMessagingPreferencesForMember(currentUserProfile.getUser().getId_());
        boolean changedMessagingPreferences = false;
        if (updatedUserBean.getSendEmailOnMessage() != messagingPreferences.getEmailOnReceipt()) {
            messagingPreferences.setEmailOnReceipt(updatedUserBean.getSendEmailOnMessage());
            changedMessagingPreferences = true;
        }

        if (updatedUserBean.getSendEmailOnActivity() != messagingPreferences.getEmailOnActivity()) {
            messagingPreferences.setEmailOnActivity(updatedUserBean.getSendEmailOnActivity());
            changedMessagingPreferences = true;
        }

        if (updatedUserBean.getSendDailyEmailOnActivity() != messagingPreferences.getEmailActivityDailyDigest()) {
            messagingPreferences.setEmailActivityDailyDigest(updatedUserBean.getSendDailyEmailOnActivity());
            changedMessagingPreferences = true;
        }

        if (changedMessagingPreferences) {
            MessagingClient.updateMessagingPreferences(messagingPreferences);
        }
        if (changedMember) {
            MembersClient.updateMember(currentUserProfile.getUser());
        }

        return changedMember || changedMessagingPreferences;
    }

    private void sendUpdatedEmail(Member user) {
        String messageSubject = TemplateReplacementUtil
                .replacePlatformConstants("Your email address on the <colab-name/> has been updated");
        String messageBody = TemplateReplacementUtil.replacePlatformConstants("Dear " + user.getFirstName() + ",\n" +
                "\n" +
                "This is an automated message to confirm that you recently updated your email address on the <colab-name/> website.\n"
                +
                "\n" +
                "Your username:  " + user.getScreenName() + "\n" +
                "Your updated email address: " + user.getEmailAddress() + "\n" +
                "\n" +
                "You can login with your username at <colab-url/>.  If you have any questions or need additional help, simply reply to this message.\n"
                +
                "\n" +
                "Thank you for engaging on the <colab-name/>!\n");

        InternetAddress addressFrom = TemplateReplacementUtil.getAdminFromEmailAddress();

        EmailClient.sendEmail(addressFrom.getAddress(),ConfigurationAttributeKey.COLAB_NAME.get(), user.getEmailAddress(), messageSubject,
                messageBody, false, addressFrom.getAddress(),ConfigurationAttributeKey.COLAB_NAME.get(),user.getId_());
    }

    @PostMapping("{memberId}/delete")
    public void deleteUserProfile(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long memberId, Member loggedInMember)
            throws IOException {
        UserProfilePermissions permission = new UserProfilePermissions(loggedInMember);

        if (permission.getCanEditMemberProfile(memberId)) {
            MembersClient.deleteMember(memberId);
        }

        if (memberId == loggedInMember.getId_()) {
            authenticationService.logout(request, response);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/members");
        }
    }
}
