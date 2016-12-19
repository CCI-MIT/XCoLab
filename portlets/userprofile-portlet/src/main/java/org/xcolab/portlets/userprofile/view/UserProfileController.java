package org.xcolab.portlets.userprofile.view;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.entity.utils.ModelAttributeUtil;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.portlets.userprofile.beans.MessageBean;
import org.xcolab.portlets.userprofile.beans.NewsletterBean;
import org.xcolab.portlets.userprofile.beans.UserBean;
import org.xcolab.portlets.userprofile.utils.UserProfileAuthorizationException;
import org.xcolab.portlets.userprofile.utils.UserProfilePermissions;
import org.xcolab.portlets.userprofile.wrappers.UserProfileWrapper;
import org.xcolab.util.CountryUtil;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class UserProfileController {

    private final static Logger _log = LoggerFactory.getLogger(UserProfileController.class);

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
        _log.error("No mapping found - using default render handler for request mode {}, lifecycle {}, parameters {}",
                request.getPortletMode().toString(), lifecycle, prettyMapString.append("}"));
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
                    ConfigurationAttributeKey.IS_POINTS_ACTIVE.get());
            return "showUserProfile";
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for {}", userId);
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
                        ConfigurationAttributeKey.IS_MY_EMMA_ACTIVE.get());
                model.addAttribute("memberCategories", MembersClient.listMemberCategories());
                ModelAttributeUtil.populateModelWithPlatformConstants(model);
                return "editUserProfile";
            }
        } catch (MemberNotFoundException e) {
            _log.warn("Could not create user profile for {}", userId);
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
            _log.warn("Could not create user profile for {}", userId);
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
                    .DEFAULT_CONTEST_TYPE_ID.get();
            final ContestType contestType = ContestClientUtil
                    .getContestType(contestTypeId);
            model.addAttribute("contestType", contestType);

            if (currentUserProfile.isViewingOwnProfile()) {
                return "showUserSubscriptionsManage";
            }
        } catch ( MemberNotFoundException e) {
            _log.warn("Could not create user profile for {}", userId);
            return "showProfileNotInitialized";
        }
        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        return "showUserProfile";
    }

    @RequestMapping(params = "action=navigateSubscriptions")
    public void navigateSubscriptions(ActionRequest request, Model model, ActionResponse response,
                                      @RequestParam String paginationAction)
            throws IOException, MemberNotFoundException {

        Integer paginationPageId = 1;
        final long memberId = MemberAuthUtil.getMemberId(request);
        UserProfileWrapper currentUserProfile =
                new UserProfileWrapper(memberId, request);
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
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.get());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
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
            _log.warn("Could not create user profile for {}", userId);
            return "showProfileNotInitialized";
        }
        return "showUserProfile";
    }

    @RequestMapping(params = "action=update")
    public void updateUserProfile(ActionRequest request, Model model, ActionResponse response,
                                  @ModelAttribute UserBean updatedUserBean, BindingResult result)
            throws IOException, UserProfileAuthorizationException, MemberNotFoundException {
        UserProfilePermissions permissions = new UserProfilePermissions(request);
        model.addAttribute("permissions", permissions);

        if (!permissions.getCanEditMemberProfile(updatedUserBean.getUserId())) {
            throw new UserProfileAuthorizationException(String.format(
                    "User %d does not have the permissions to update the profile of user %d",
                    MemberAuthUtil.getMemberId(request), updatedUserBean.getUserId()));
        }
        UserProfileWrapper currentUserProfile = new UserProfileWrapper(updatedUserBean.getUserId(), request);
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("baseImagePath", currentUserProfile.getThemeDisplay().getPathImage());
        model.addAttribute("messageBean", new MessageBean());
        model.addAttribute("userBean", updatedUserBean);

        boolean changedUserPart = false;
        boolean validationError = false;
        final Long memberId = currentUserProfile.getUserId();
        if (StringUtils.isNotBlank(updatedUserBean.getPassword())) {
            final boolean isAdminEditingOtherProfile =
                    permissions.getCanAdmin() && !currentUserProfile.isViewingOwnProfile();
            final String currentPassword = updatedUserBean.getCurrentPassword();
            if (MembersClient.validatePassword(currentPassword.trim(), currentUserProfile.getUser().getUserId())
                    || isAdminEditingOtherProfile) {
                validator.validate(updatedUserBean, result, UserBean.PasswordChanged.class);

                if (!result.hasErrors()) {
                    final String newPassword = updatedUserBean.getPassword().trim();
                    MembersClient.updatePassword(memberId, currentPassword, newPassword);
                    //TODO: remove, currently needed to update password for liferay
                    try {
                        final User liferayUser = UserLocalServiceUtil.getUser(
                                memberId);
                        liferayUser.setPassword
                                (MembersClient.hashPassword(newPassword));
                        UserLocalServiceUtil.updateUser(liferayUser);
                    } catch (PortalException | SystemException e) {
                        //TODO: remove after liferay
                    }
                    changedUserPart = true;
                } else {
                    validationError = true;
                    response.setRenderParameter("passwordError", "true");
                    _log.warn("CompareStrings password failed for userId: {}", currentUserProfile.getUser().getId_());
                }
            } else {
                result.addError(
                        new ObjectError("currentPassword", "Password change failed: Current password is incorrect."));
                validationError = true;
                response.setRenderParameter("passwordError", "true");
                _log.warn("Current password wrong for userId: {}", currentUserProfile.getUser().getId_());
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
                _log.warn("Email change failed for userId: {}", currentUserProfile.getUser().getId_());
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

        if(updatedUserBean.getCountryName() != null) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors() && !updatedUserBean.getCountryName().isEmpty()) {
                 currentUserProfile.getUser().setCountry(HtmlUtil.cleanAll(updatedUserBean.getCountryName()));
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("Country name change failed for userId: {}", currentUserProfile.getUser().getId_());
            }
        }

        changedUserPart = changedUserPart | updateUserProfile(currentUserProfile, updatedUserBean);

        if (validationError) {
            response.setRenderParameter("userId", memberId.toString());
            response.setRenderParameter("updateError", "true");
            updatedUserBean.setImageId(currentUserProfile.getUserBean().getImageId());
        } else if (changedUserPart) {

            response.setRenderParameter("userId", memberId.toString());
            response.setRenderParameter("updateSuccess", "true");

            updatedUserBean.setImageId(currentUserProfile.getUser().getPortraitFileEntryId());

            MembersClient.updateMember(currentUserProfile.getUser());

            if (eMailChanged) {
                updatedUserBean.setEmailStored(updatedUserBean.getEmail());
                sendUpdatedEmail(currentUserProfile.getUser());
            }
        } else {
            response.sendRedirect("/web/guest/member/-/member/userId/" + memberId.toString());
            return;
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);
    }

    private boolean updateUserProfile(UserProfileWrapper currentUserProfile, UserBean updatedUserBean) {

        boolean changedMember = false;
        Member member = currentUserProfile.getUser();

        long companyId = CompanyThreadLocal.getCompanyId();
        if (companyId == 0) {
            CompanyThreadLocal.setCompanyId(currentUserProfile.getThemeDisplay().getCompanyId());
            changedMember = true;
        }

        String existingBio = member.getShortBio();
        if (existingBio == null) {
            existingBio = "";
        }

        if (!existingBio.equals(updatedUserBean.getShortBio())) {
            member.setShortBio(HtmlUtil.cleanSome(updatedUserBean.getShortBio(), ""));
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
            member.setCountry(CountryUtil.getCountryForCode(updatedUserBean.getCountryCode()));
            changedMember = true;
        }

        // TODO
        //	if(changedExpando || changedUserPart) {
        //	fireGoogleEvent = !profileWasComplete && profileIsComplete();
        //}

        if (updatedUserBean.getImageId() != currentUserProfile.getUserBean().getImageId()) {

            try {
                FileEntry fe = FilesClient.getFileEntry(updatedUserBean.getImageId());
                if (fe != null) {
                    currentUserProfile.getUser().setPortraitFileEntryId(fe.getFileEntryId());
                    changedMember = true;
                }
            } catch (FileEntryNotFoundException e) {
                throw new IllegalStateException("No file entry found for imageId " + updatedUserBean.getImageId()
                        + " for member " + updatedUserBean.getUserId());
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

        if (userId == permission.getCurrentMemberId()) {
            response.sendRedirect("/c/portal/logout");
        } else {
            response.sendRedirect("/web/guest/members");
        }
    }
}
