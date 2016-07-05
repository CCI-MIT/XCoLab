package org.xcolab.portlets.userprofile.view;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.portal.UserPortraitSizeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngineException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.portlets.userprofile.beans.MessageBean;
import org.xcolab.portlets.userprofile.beans.NewsletterBean;
import org.xcolab.portlets.userprofile.beans.UserBean;
import org.xcolab.portlets.userprofile.utils.UserProfileAuthorizationException;
import org.xcolab.portlets.userprofile.utils.UserProfilePermissions;
import org.xcolab.portlets.userprofile.wrappers.UserProfileWrapper;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.utils.CountryUtil;
import org.xcolab.utils.ModelAttributeUtil;
import org.xcolab.utils.TemplateReplacementUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class UserProfileController {

    private final static Log _log = LogFactoryUtil.getLog(UserProfileController.class);

    @Autowired
    private SmartValidator validator;

    public UserProfileController() {
    }

    @InitBinder("userBean")
    public void initUserWrapperBeanBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RenderMapping
    public String defaultShowUserProfileNotInitializedView(PortletRequest request) {
        final String lifecycle =
                (String) PortalUtil.getHttpServletRequest(request).getAttribute(PortletRequest.LIFECYCLE_PHASE);
        final StringBuilder prettyMapString = new StringBuilder().append("{");
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            prettyMapString.append(
                    String.format("%s = %s, ", entry.getKey(), Arrays.asList(entry.getValue()).toString()));
        }
        _log.error(String.format(
                "No mapping found - using default render handler for request mode %s, lifecycle %s, parameters %s",
                request.getPortletMode().toString(), lifecycle, prettyMapString.append("}")));
        return "showProfileNotInitialized";
    }

    @RequestMapping(params = "page=view")
    public String showUserProfileView(PortletRequest request, PortletResponse response, Model model,
                                      @RequestParam Long userId) {
        return showUserProfileOrNotInitialized(request, model, userId);
    }

    private String showUserProfileOrNotInitialized(PortletRequest request, Model model, Long userId) {
        try {
            UserProfilePermissions permissions = new UserProfilePermissions(request);
            model.addAttribute("permissions", permissions);
            populateUserWrapper(new UserProfileWrapper(userId, request), model);
            ModelAttributeUtil.populateModelWithPlatformConstants(model);
            model.addAttribute("pointsActive",
                    ConfigurationAttributeKey.IS_POINTS_ACTIVE.getBooleanValue());
            return "showUserProfile";
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for " + userId);
        }
        return "showProfileNotInitialized";
    }

    public void populateUserWrapper(UserProfileWrapper currentUserProfile, Model model) {
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("baseImagePath", currentUserProfile.getThemeDisplay().getPathImage());
        model.addAttribute("userBean", currentUserProfile.getUserBean());
        model.addAttribute("messageBean", new MessageBean());
    }

    @RequestMapping(params = "page=edit")
    public String showUserProfileEdit(PortletRequest request, PortletResponse response, Model model,
                                      @RequestParam long userId) {

        UserProfilePermissions permissions = new UserProfilePermissions(request);
        model.addAttribute("permissions", permissions);

        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId, request);
            populateUserWrapper(currentUserProfile, model);
            if (permissions.getCanEditMemberProfile(currentUserProfile.getUserId())) {
                model.addAttribute("newsletterBean",
                        new NewsletterBean(currentUserProfile.getUserId()));
                model.addAttribute("newsletterActive",
                        ConfigurationAttributeKey.IS_MY_EMMA_ACTIVE.getBooleanValue());
                ModelAttributeUtil.populateModelWithPlatformConstants(model);
                return "editUserProfile";
            }
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        return "showUserProfile";
    }

    @RequestMapping(params = "page=subscriptions")
    public String showUserProfileSubscriptions(PortletRequest request, PortletResponse response, Model model,
                                               @RequestParam Long userId,
                                               @RequestParam(required = false, defaultValue = "1") int paginationId) {
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId, request);
            populateUserWrapper(currentUserProfile, model);
            currentUserProfile.setSubscriptionsPaginationPageId(paginationId);
            return "showUserSubscriptions";
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
    }

    @RequestMapping(params = "page=subscriptionsManage")
    public String showUserSubscriptionsManage(PortletRequest request, PortletResponse response, Model model,
                                              @RequestParam long userId,
                                              @RequestParam(required = false) String typeFilter) {
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId, request);
            populateUserWrapper(currentUserProfile, model);
            if (typeFilter != null) {
                currentUserProfile.getUserSubscriptions().setFilterType(typeFilter);
            }
            model.addAttribute("userSubscriptions", currentUserProfile.getUserSubscriptions());

            final long contestTypeId = ConfigurationAttributeKey
                    .DEFAULT_CONTEST_TYPE_ID.getLongValue();
            final ContestType contestType = ContestTypeLocalServiceUtil
                    .getContestType(contestTypeId);
            model.addAttribute("contestType", contestType);

            if (currentUserProfile.isViewingOwnProfile()) {
                return "showUserSubscriptionsManage";
            }
        } catch (PortalException | MemberNotFoundException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        return "showUserProfile";
    }

    @RequestMapping(params = "action=navigateSubscriptions")
    public void navigateSubscriptions(ActionRequest request, Model model, ActionResponse response,
                                      @RequestParam String paginationAction)
            throws IOException, MemberNotFoundException {

        Integer paginationPageId = 1;
        UserProfileWrapper currentUserProfile =
                new UserProfileWrapper(Long.parseLong(request.getRemoteUser()), request);
        switch (paginationAction) {
            case "First":
                paginationPageId = 1;
                break;
            case "<Previous":
                paginationPageId = currentUserProfile.getSubscriptionsPaginationPageId() - 1;
                break;
            case "Next>":
                paginationPageId = currentUserProfile.getSubscriptionsPaginationPageId() + 1;
                break;
            case "Last":
                paginationPageId = currentUserProfile.getSubscriptionsPaginationPageMax();
                break;
        }
        response.sendRedirect("/web/guest/member/-/member/userId/" + currentUserProfile.getUserId().toString() +
                "/page/subscriptions/" + paginationPageId.toString());
    }

    @RequestMapping(params = "updateError=true")
    public String updateProfileError(PortletRequest request, Model model,
                                     @RequestParam(required = false) boolean emailError,
                                     @RequestParam(required = false) boolean passwordError,
                                     @RequestParam Long userId) {
        UserProfilePermissions permissions = new UserProfilePermissions(request);
        model.addAttribute("permissions", permissions);
        model.addAttribute("updateError", true);
        if (emailError) {
            model.addAttribute("emailError", true);
        }
        if (passwordError) {
            model.addAttribute("passwordError", true);
        }
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.getStringValue());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.getStringValue());
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId, request);
            if (permissions.getCanEditMemberProfile(userId)) {
                model.addAttribute("newsletterBean",
                        new NewsletterBean(currentUserProfile.getUserBean().getUserId()));
                ModelAttributeUtil.populateModelWithPlatformConstants(model);
                return "editUserProfile";
            }
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }

        return "showUserProfile";
    }

    @RequestMapping(params = "updateSuccess=true")
    public String updateProfileSuccess(PortletRequest request, Model model,
                                       @RequestParam Long userId) {

        model.addAttribute("updateSuccess", true);
        UserProfilePermissions permissions = new UserProfilePermissions(request);
        model.addAttribute("permissions", permissions);
        try {
            populateUserWrapper(new UserProfileWrapper(userId, request), model);
            ModelAttributeUtil.populateModelWithPlatformConstants(model);
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
        return "showUserProfile";
    }

    @RequestMapping(params = "action=update")
    public void updateUserProfile(ActionRequest request, Model model, ActionResponse response,
                                  @ModelAttribute UserBean updatedUserBean, BindingResult result)
            throws IOException, UserProfileAuthorizationException, SystemException, PortalException, MemberNotFoundException {
        UserProfilePermissions permissions = new UserProfilePermissions(request);
        model.addAttribute("permissions", permissions);

        if (!permissions.getCanEditMemberProfile(updatedUserBean.getUserId())) {
            throw new UserProfileAuthorizationException(String.format(
                    "User %s does not have the permissions to update the profile of user %d",
                    request.getRemoteUser(), updatedUserBean.getUserId()));
        }
        UserProfileWrapper currentUserProfile = new UserProfileWrapper(updatedUserBean.getUserId(), request);
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("baseImagePath", currentUserProfile.getThemeDisplay().getPathImage());
        model.addAttribute("messageBean", new MessageBean());
        model.addAttribute("userBean", updatedUserBean);

        boolean changedUserPart = false;
        boolean validationError = false;
        if (updatedUserBean.getPassword() != null && !updatedUserBean.getPassword().trim().isEmpty()) {
            if (isPasswordMatchingExistingPassword(currentUserProfile, updatedUserBean.getCurrentPassword().trim())
                    || (permissions.getCanAdmin() && !currentUserProfile.isViewingOwnProfile())) {
                validator.validate(updatedUserBean, result, UserBean.PasswordChanged.class);

                if (!result.hasErrors()) {
                    currentUserProfile.getUser().setHashedPassword(
                            MembersClient.hashPassword(updatedUserBean.getPassword().trim()));
                    //TODO: remove, currently needed to update password for liferay
                    final User liferayUser = UserLocalServiceUtil.getUser(currentUserProfile.getUserId());
                    liferayUser.setPassword
                            (MembersClient.hashPassword(updatedUserBean.getPassword().trim(), true));
                    UserLocalServiceUtil.updateUser(liferayUser);
                    changedUserPart = true;
                } else {
                    validationError = true;
                    response.setRenderParameter("passwordError", "true");
                    _log.warn("CompareStrings password failed for userId: " + currentUserProfile.getUser().getId_());
                }
            } else {
                result.addError(
                        new ObjectError("currentPassword", "Password change failed: Current password is incorrect."));
                validationError = true;
                response.setRenderParameter("passwordError", "true");
                _log.warn("Current password wrong for userId: " + currentUserProfile.getUser().getId_());
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
                response.setRenderParameter("emailError", "true");
                _log.warn("Email change failed for userId: " + currentUserProfile.getUser().getId_());
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
                _log.warn("First name change failed for userId: " + currentUserProfile.getUser().getId_());
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
                _log.warn("First name change failed for userId: " + currentUserProfile.getUser().getId_());
            }
        }

        try {
            changedUserPart = changedUserPart | updateUserProfile(currentUserProfile, updatedUserBean);
        } catch (Exception e) {
            _log.warn("Updating Expando settings or portrait image failed for userId: " + currentUserProfile.getUser()
                    .getId_());
            _log.warn(e);
            if (e instanceof UserPortraitSizeException) {
                model.addAttribute("imageSizeError", true);
            }
            validationError = true;
        }

        if (validationError) {
            response.setRenderParameter("userId", currentUserProfile.getUserId().toString());
            response.setRenderParameter("updateError", "true");
            updatedUserBean.setImageId(currentUserProfile.getUserBean().getImageId());
        } else if (changedUserPart) {

            response.setRenderParameter("userId", currentUserProfile.getUserId().toString());
            response.setRenderParameter("updateSuccess", "true");

            updatedUserBean.setImageId(currentUserProfile.getUser().getPortraitFileEntryId());

            MembersClient.updateMember(currentUserProfile.getUser());

            if (eMailChanged) {
                updatedUserBean.setEmailStored(updatedUserBean.getEmail());
                try {
                    sendUpdatedEmail(currentUserProfile.getUser());
                } catch (MailEngineException | AddressException e) {
                    _log.warn("Sending eMail confirmation after email change failed for userId: " + currentUserProfile
                            .getUser().getId_());
                    _log.warn(e);
                }
            }
        } else {
            response.sendRedirect("/web/guest/member/-/member/userId/" + currentUserProfile.getUserId().toString());
            return;
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);
    }

    private boolean isPasswordMatchingExistingPassword(UserProfileWrapper currentUserProfile, String password) {
        return MembersClient.validatePassword(password, currentUserProfile.getUser().getUserId());
    }

    //TODO: remove reliance on all exceptions being thrown
    private boolean updateUserProfile(UserProfileWrapper currentUserProfile, UserBean updatedUserBean)
            throws Exception {

        boolean changedDetails = false;
        Member member = currentUserProfile.getUser();

        long companyId = CompanyThreadLocal.getCompanyId();
        if (companyId == 0) {
            CompanyThreadLocal.setCompanyId(currentUserProfile.getThemeDisplay().getCompanyId());
            changedDetails = true;
        }

        String existingBio = member.getShortBio();
        if (existingBio == null) {
            existingBio = "";
        }

        if (!existingBio.equals(updatedUserBean.getShortBio())) {
            member.setShortBio(HtmlUtil.cleanSome(updatedUserBean.getShortBio(), ""));
            changedDetails = true;
        }

        String existingCountry = member.getCountry();
        if (!StringUtils.isEmpty(existingCountry)) {
            if (CountryUtil.getCodeForCounty(existingCountry) != null) {
                existingCountry = CountryUtil.getCodeForCounty(existingCountry);
            }
        }

        if (updatedUserBean.getCountryCode() != null && !updatedUserBean.getCountryCode().equals(existingCountry)) {
            member.setCountry(CountryUtil.getCountryForCode(updatedUserBean.getCountryCode()));
            changedDetails = true;
        }

        // TODO
        //	if(changedExpando || changedUserPart) {
        //	fireGoogleEvent = !profileWasComplete && profileIsComplete();
        //}

        if (updatedUserBean.getImageId() != currentUserProfile.getUserBean().getImageId()) {

            FileEntry fe = FilesClient.getFileEntry(updatedUserBean.getImageId());
            if( fe!= null){
                currentUserProfile.getUser().setPortraitFileEntryId(fe.getFileEntryId());
                changedDetails = true;
            }
        }

        if (updatedUserBean.getSendEmailOnMessage() != MessageUtil
                .getMessagingPreferences(currentUserProfile.getUser().getId_()).getEmailOnReceipt()) {
            MessagingUserPreferences prefs =
                    MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getId_());
            prefs.setEmailOnReceipt(updatedUserBean.getSendEmailOnMessage());
            try {
                MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            }
            changedDetails = true;
        }

        if (updatedUserBean.getSendEmailOnActivity() != MessageUtil
                .getMessagingPreferences(currentUserProfile.getUser().getId_()).getEmailOnActivity()) {
            MessagingUserPreferences prefs =
                    MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getId_());
            prefs.setEmailOnActivity(updatedUserBean.getSendEmailOnActivity());
            try {
                MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            }
            changedDetails = true;
        }

        if (updatedUserBean.getSendDailyEmailOnActivity() != MessageUtil
                .getMessagingPreferences(currentUserProfile.getUser().getId_()).getEmailActivityDailyDigest()) {
            MessagingUserPreferences prefs =
                    MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getId_());
            prefs.setEmailActivityDailyDigest(updatedUserBean.getSendDailyEmailOnActivity());
            try {
                MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            }
            changedDetails = true;
        }

        if (changedDetails) {
            MembersClient.updateMember(currentUserProfile.getUser());
        }

        return changedDetails;
    }

    private void sendUpdatedEmail(Member user)
            throws MailEngineException, AddressException, UnsupportedEncodingException {
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

        EmailClient.sendEmail(addressFrom.getAddress(), user.getEmailAddress(), messageSubject,
                messageBody, false, addressFrom.getAddress());
    }

    @RequestMapping(params = "action=deleteProfile")
    public void deleteUserProfile(ActionRequest request, ActionResponse response, Model model,
                long userId)
            throws IOException, SystemException, PortalException {
        UserProfilePermissions permission = new UserProfilePermissions(request);

        if (permission.getCanEditMemberProfile(userId)) {
            UserLocalServiceUtil.updateStatus(userId, WorkflowConstants.STATUS_INACTIVE,
                    ServiceContextFactory.getInstance(request));
            MembersClient.deleteMember(userId);
        }

        if (userId == permission.getCurrentUser().getUserId()) {
            response.sendRedirect("/c/portal/logout");
        } else {
            response.sendRedirect("/web/guest/members");
        }
    }
}
