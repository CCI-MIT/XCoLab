package org.xcolab.portlets.userprofile.view;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.UserPortraitSizeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
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
import org.xcolab.commons.utils.PwdEncryptor;
import org.xcolab.portlets.userprofile.beans.MessageBean;
import org.xcolab.portlets.userprofile.beans.NewsletterBean;
import org.xcolab.portlets.userprofile.beans.UserBean;
import org.xcolab.portlets.userprofile.utils.Helper;
import org.xcolab.portlets.userprofile.wrappers.UserProfileWrapper;
import org.xcolab.utils.HtmlUtil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class UserProfileController {

    private static final long DEFAULT_COMPANY_ID = 10112L;
    private final static Log _log = LogFactoryUtil.getLog(UserProfileController.class);

    @Autowired
    private SmartValidator validator;

    @InitBinder("userBean")
    public void initUserWrapperBeanBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    public UserProfileController() {
    }

    @RenderMapping
    public String defaultShowUserProfileNotInitializedView(PortletRequest request) {
        return "showProfileNotInitialized";
    }

    @RequestMapping(params = "page=view")
    public String showUserProfileView(PortletRequest request, PortletResponse response, Model model,
                                     @RequestParam(required = true) String userId)
            throws SystemException, PortalException {
        return showUserProfileOrNotInitialized(request, model, userId);
    }

    @RequestMapping(params = "page=edit")
    public String showUserProfileEdit(PortletRequest request, PortletResponse response, Model model,
                                     @RequestParam(required = true) String userId) {

        try{
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(request.getRemoteUser(), request);
            populateUserWrapper(currentUserProfile, model);
            if (currentUserProfile.isViewingOwnProfile()) {
                model.addAttribute("newsletterBean",
                        new NewsletterBean(currentUserProfile.getUserBean().getEmailStored(), request));
                return "editUserProfile";
            }
        } catch (SystemException | PortalException e){
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
        return "showUserProfile";
    }

    @RequestMapping(params = "page=subscriptions")
    public String showUserProfileSubscriptions(PortletRequest request, PortletResponse response, Model model,
                                      @RequestParam(required = true) String userId,
                                      @RequestParam(required = false, defaultValue = "1") int paginationId) {
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(request.getRemoteUser(), request);
            populateUserWrapper(currentUserProfile, model);
            currentUserProfile.setSubscriptionsPaginationPageId(paginationId);
            return "showUserSubscriptions";
        } catch (SystemException | PortalException e){
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
    }

    @RequestMapping(params = "page=subscriptionsManage")
    public String showUserSubscriptionsManage(PortletRequest request, PortletResponse response, Model model,
                                               @RequestParam(required = true) String userId,
                                               @RequestParam(required = false) String typeFilter) {
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(request.getRemoteUser(), request);
            populateUserWrapper(currentUserProfile, model);
            if (typeFilter != null) {
                currentUserProfile.getUserSubscriptions().setFilterType(typeFilter);
            }
            model.addAttribute("userSubscriptions", currentUserProfile.getUserSubscriptions());
            if (currentUserProfile.isViewingOwnProfile()) {
                return "showUserSubscriptionsManage";
            }
        } catch (SystemException | PortalException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
        return "showUserProfile";
    }

    @RequestMapping(params = "action=navigateSubscriptions")
    public void navigateSubscriptions(ActionRequest request, Model model, ActionResponse response,
                                      @RequestParam(required = true) String paginationAction)
            throws SystemException, PortalException, IOException {

        Integer paginationPageId = 1;
        UserProfileWrapper currentUserProfile = new UserProfileWrapper(request.getRemoteUser(), request);
        switch (paginationAction) {
            case "First": paginationPageId = 1; break;
            case "<Previous": paginationPageId = currentUserProfile.getSubscriptionsPaginationPageId() - 1; break;
            case "Next>": paginationPageId = currentUserProfile.getSubscriptionsPaginationPageId() + 1; break;
            case "Last": paginationPageId = currentUserProfile.getSubscriptionsPaginationPageMax(); break;
        }
        response.sendRedirect("/web/guest/member/-/member/userId/" + currentUserProfile.getUserId().toString() +
                "/page/subscriptions/"+paginationPageId.toString());
    }

    @RequestMapping(params = "updateError=true")
    public String updateProfileError(PortletRequest request, Model model,
                                      @RequestParam(required = false) boolean emailError,
                                      @RequestParam(required = false) boolean passwordError,
                                      @RequestParam(required = true) String userId) {
        model.addAttribute("updateError", true);
        if(emailError) {
            model.addAttribute("emailError", true);
        }
        if(passwordError){
            model.addAttribute("passwordError", true);
        }
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(request.getRemoteUser(), request);
            if (currentUserProfile.isViewingOwnProfile()) {
                model.addAttribute("newsletterBean",
                        new NewsletterBean(currentUserProfile.getUserBean().getEmailStored(), request));
                return "editUserProfile";
            }
        } catch (SystemException | PortalException e) {
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }

        return "showUserProfile";
    }

    @RequestMapping(params = "updateSuccess=true")
    public String updateProfileSuccess(PortletRequest request, Model model,
                                     @RequestParam(required = true) String userId) {

        model.addAttribute("updateSuccess", true);
        try{
            populateUserWrapper(new UserProfileWrapper(request.getRemoteUser(), request), model);
        } catch(SystemException | PortalException e){
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }
        return "showUserProfile";
    }

    @RequestMapping(params = "action=update")
    public void updateUserProfile(ActionRequest request, Model model, ActionResponse response,
                             @ModelAttribute UserBean updatedUserBean, BindingResult result) throws IOException {
        Long loggedInUserId = Long.parseLong(request.getRemoteUser());
        if(!loggedInUserId.equals(updatedUserBean.getUserId())){
            response.sendRedirect("/web/guest/member/-/member/userId/" + updatedUserBean.getUserId());
        }
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(request.getRemoteUser(), request);
            model.addAttribute("currentUserProfile", currentUserProfile);
            model.addAttribute("baseImagePath", currentUserProfile.getThemeDisplay().getPathImage());
            model.addAttribute("messageBean", new MessageBean());
            model.addAttribute("userBean", updatedUserBean);

            boolean validationError = false;
            boolean changedUserPart = false;
            if (updatedUserBean.getPassword() != null  && !updatedUserBean.getPassword().trim().isEmpty()) {
            if(isPasswordMatchingExistingPassword(currentUserProfile, updatedUserBean.getCurrentPassword().trim())){
                validator.validate(updatedUserBean, result, UserBean.PasswordChanged.class);

                if (!result.hasErrors()) {
                    currentUserProfile.getUser().setPassword(PwdEncryptor.encrypt(updatedUserBean.getPassword().trim()));
                    changedUserPart = true;
                }
                else {
                    validationError = true;
                    response.setRenderParameter("passwordError", "true");
                    _log.warn("CompareStrings password failed for userId: " + currentUserProfile.getUser().getUserId());
                }
            } else {
                result.addError(new ObjectError("currentPassword","Password change failed: Current password is incorrect."));
                validationError = true;
                response.setRenderParameter("passwordError", "true");
                _log.warn("Current password wrong for userId: " + currentUserProfile.getUser().getUserId());
            }
        }

        if (updatedUserBean.getScreenName() != null
                && !updatedUserBean.getScreenName().equals(currentUserProfile.getUserBean().getScreenName())) {

            validator.validate(updatedUserBean, result, UserBean.ScreenNameChanged.class);

            if (!result.hasErrors()) {
                currentUserProfile.getUser().setScreenName(updatedUserBean.getScreenName());
                changedUserPart = true;
            }else{
                validationError = true;
                _log.warn("ScreenName change failed for userId: " + currentUserProfile.getUser().getUserId());
            }
        }

            boolean eMailChanged = false;
            if (updatedUserBean.getEmail()!= null && !updatedUserBean.getEmail().trim().isEmpty() &&
                !updatedUserBean.getEmail().equals(currentUserProfile.getUserBean().getEmailStored())) {
            validator.validate(updatedUserBean, result, UserBean.EmailChanged.class);

            if (!result.hasErrors()) {
                currentUserProfile.getUser().setEmailAddress(updatedUserBean.getEmail());
                changedUserPart = true;
                eMailChanged = true;
            } else {
                validationError = true;
                response.setRenderParameter("emailError", "true");
                _log.warn("Email change failed for userId: " + currentUserProfile.getUser().getUserId());
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
                _log.warn("First name change failed for userId: " + currentUserProfile.getUser().getUserId());
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
                _log.warn("First name change failed for userId: " + currentUserProfile.getUser().getUserId());
            }
        }

        try {
            changedUserPart = changedUserPart | updateUserProfile(currentUserProfile, updatedUserBean);
        } catch(Exception e){
            _log.warn("Updating Expando settings or portrait image failed for userId: " + currentUserProfile.getUser().getUserId());
            _log.warn(e);
            if (e instanceof UserPortraitSizeException){
                model.addAttribute("imageSizeError", true);

            }
            validationError = true;
        }


        if(validationError) {
            response.setRenderParameter("userId", currentUserProfile.getUserId().toString());
            response.setRenderParameter("updateError", "true");
            updatedUserBean.setImageId(currentUserProfile.getUserBean().getImageId());
        }
        else if(changedUserPart){

            response.setRenderParameter("userId", currentUserProfile.getUserId().toString());
            response.setRenderParameter("updateSuccess", "true");

            updatedUserBean.setImageId(currentUserProfile.getUserBean().getImageId());

            try {
                UserLocalServiceUtil.updateUser(currentUserProfile.getUser());
            } catch(SystemException e) {
                response.removePublicRenderParameter("updateSuccess");
                response.setRenderParameter("updateError", "true");
                _log.warn("Updating user failed for userId: " + currentUserProfile.getUser().getUserId());
                _log.warn(e);
            }

            if (eMailChanged) {
                updatedUserBean.setEmailStored(updatedUserBean.getEmail());
                try {
                    sendUpdatedEmail(currentUserProfile.getUser());
                } catch(MailEngineException | AddressException e){
                    _log.warn("Sending eMail confirmation after email change failed for userId: " + currentUserProfile.getUser().getUserId());
                    _log.warn(e);
                }
            }
        } else {
            response.sendRedirect("/web/guest/member/-/member/userId/" + currentUserProfile.getUserId().toString());
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);
        } catch(SystemException | PortalException e){
            _log.warn("Could not update user profile for " + loggedInUserId, e);
            response.sendRedirect("/web/guest/member/-/member/userId/" + loggedInUserId);
        }
    }

    private void populateUserWrapper(UserProfileWrapper currentUserProfile, Model model){
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("baseImagePath", currentUserProfile.getThemeDisplay().getPathImage());
        model.addAttribute("userBean", currentUserProfile.getUserBean());
        model.addAttribute("messageBean", new MessageBean());
    }

    private String showUserProfileOrNotInitialized(PortletRequest request, Model model, String userId) {
        try {
            populateUserWrapper(new UserProfileWrapper(userId,request) ,model);
            return "showUserProfile";
        } catch (SystemException | PortalException e) {
            _log.warn("Could not create user profile for " + userId);
        }
        return "showProfileNotInitialized";
    }

    private boolean  updateUserProfile(UserProfileWrapper currentUserProfile, UserBean updatedUserBean) throws Exception {

        boolean changedDetails = false;

        long companyId = CompanyThreadLocal.getCompanyId();
        if (companyId == 0) {
            CompanyThreadLocal.setCompanyId(currentUserProfile.getThemeDisplay().getCompanyId());
            changedDetails = true;
        }

        String existingBio = ExpandoValueLocalServiceUtil.getData(DEFAULT_COMPANY_ID,
                User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.BIO, currentUserProfile.getUser().getUserId(), StringPool.BLANK);

        if (!existingBio.equals(updatedUserBean.getShortBio())) {
            ExpandoValueLocalServiceUtil.addValue(DEFAULT_COMPANY_ID,  User.class.getName(),
                    CommunityConstants.EXPANDO, CommunityConstants.BIO,
                    currentUserProfile.getUser().getUserId(), HtmlUtil.cleanSome(updatedUserBean.getShortBio()));
            changedDetails = true;
        }

        String existingCountry = ExpandoValueLocalServiceUtil.getData(DEFAULT_COMPANY_ID,
                User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.COUNTRY, currentUserProfile.getUser().getUserId(), StringPool.BLANK);
        if(!existingCountry.isEmpty()){
            if (Helper.getCodeForCounty(existingCountry)!= null) {
                existingCountry = Helper.getCodeForCounty(existingCountry);
            }
        }

        if (updatedUserBean.getCountry() != null && !updatedUserBean.getCountry().equals(existingCountry)) {
            ExpandoValueLocalServiceUtil.addValue(DEFAULT_COMPANY_ID, User.class.getName(),
                    CommunityConstants.EXPANDO, CommunityConstants.COUNTRY,
                    currentUserProfile.getUser().getUserId(), Helper.getCountryForCode(updatedUserBean.getCountry()));
            changedDetails = true;
        }

        // TODO
        //	if(changedExpando || changedUserPart) {
        //	fireGoogleEvent = !profileWasComplete && profileIsComplete();
        //}


        if (updatedUserBean.getImageId() != currentUserProfile.getUserBean().getImageId()) {
            Image img = ImageLocalServiceUtil.getImage(updatedUserBean.getImageId());
            // we need to set permission checker for liferay
            PermissionChecker permissionChecker = PermissionCheckerFactoryUtil
                    .create(currentUserProfile.getUser(), true);
            PermissionThreadLocal
                    .setPermissionChecker(permissionChecker);
            if (img != null) {
                byte[] bytes = img.getTextObj();
                UserServiceUtil.updatePortrait(currentUserProfile.getUser().getUserId(), bytes);
                currentUserProfile.getUser().setPortraitId(0L);
                UserLocalServiceUtil.updateUser(currentUserProfile.getUser());
                UserServiceUtil.updatePortrait(currentUserProfile.getUser().getUserId(), bytes);
                currentUserProfile.setUser(UserLocalServiceUtil.getUser(currentUserProfile.getUser().getUserId()));
                changedDetails = true;
            }
        }

        if (updatedUserBean.getSendEmailOnMessage() != MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getUserId()).getEmailOnReceipt()){
            MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getUserId());
            prefs.setEmailOnReceipt(updatedUserBean.getSendEmailOnMessage());
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            changedDetails = true;
        }

        if (updatedUserBean.getSendEmailOnActivity() != MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getUserId()).getEmailOnActivity()){
            MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getUserId());
            prefs.setEmailOnActivity(updatedUserBean.getSendEmailOnActivity());
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            changedDetails = true;
        }

        if (updatedUserBean.getSendDailyEmailOnActivity() != MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getUserId()).getEmailActivityDailyDigest()){
            MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(currentUserProfile.getUser().getUserId());
            prefs.setEmailActivityDailyDigest(updatedUserBean.getSendDailyEmailOnActivity());
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            changedDetails = true;
        }

        return changedDetails;

    }

    private boolean isPasswordMatchingExistingPassword(UserProfileWrapper currentUserProfile, String password){
        boolean existing = false;
        try {
            final String existingPassword = currentUserProfile.getUser().getPassword();
            final String existingPasswordWithoutSHA1 = currentUserProfile.getUser().getPassword().substring(7);
            existing = PwdEncryptor.encrypt(password).equals(existingPassword) ||
                   PwdEncryptor.encrypt(password).equals(existingPasswordWithoutSHA1);
        } catch (PwdEncryptorException ignored) { }
        return existing;
    }

    private void sendUpdatedEmail(User user) throws MailEngineException, AddressException {
        String messageSubject = "Your email address on the Climate CoLab has been updated";
        String messageBody = "Dear " + user.getFirstName() + ",\n" +
                "\n" +
                "This is an automated message to confirm that you recently updated your email address on the Climate CoLab website.\n" +
                "\n" +
                "Your username:  " + user.getScreenName() + "\n" +
                "Your updated email address: " + user.getEmailAddress() + "\n" +
                "\n" +
                "You can login with your username at www.climatecolab.org.  If you have any questions or need additional help, simply reply to this message.\n" +
                "\n" +
                "Thank you for engaging on the Climate CoLab!\n";

        InternetAddress addressFrom = new InternetAddress("admin@climatecolab.org");
        InternetAddress[] addressTo = {new InternetAddress(user.getEmailAddress())};

        InternetAddress[] replyTo = {addressFrom};
            MailEngine.send(addressFrom, addressTo, null, null, null,
                    messageSubject, messageBody, false, replyTo, null, null);
    }


}
