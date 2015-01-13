package org.xcolab.portlets.userprofilenew.view;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.*;
import javax.validation.Valid;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.portal.PwdEncryptorException;
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
import org.xcolab.commons.utils.PwdEncryptor;
import org.xcolab.portlets.userprofilenew.beans.NewsletterBean;
import org.xcolab.portlets.userprofilenew.utils.Helper;
import org.xcolab.portlets.userprofilenew.beans.MessageBean;
import org.xcolab.portlets.userprofilenew.beans.UserBean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.userprofilenew.wrappers.UserProfileWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class UserProfileController {

    private User _loggedInUser = null;
    private UserProfileWrapper _currentUserProfile = null;

    private final static Log _log = LogFactoryUtil.getLog(UserProfileController.class);

    @Autowired
    //@Qualifier("UserBean")
    private SmartValidator validator;

    //@Autowired
    //@Qualifier("MessageBean")
    //private SmartValidator validator;

    @InitBinder("messageBean")
    public void initMessageBeanBinder(WebDataBinder binder) { binder.setValidator(validator); }

    @InitBinder("userBean")
    public void initUserWrapperBeanBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    public UserProfileController() {

    }

    @RequestMapping(params = "page=view")
    public String showUserProfileView(PortletRequest request, PortletResponse response, Model model,
                                     @RequestParam(required = true) String userId
    ) throws SystemException, PortalException {

        try{
            initUserWrapper(request, model, userId);

            if(_currentUserProfile.isInitialized()) {
                model.addAttribute("userBean", _currentUserProfile.getUserBean());
                return "showUserProfile";
            }
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
        }

        return "showProfileNotInitialized";

    }

    @RequestMapping(params = "page=edit")
    public String showUserProfileEdit(PortletRequest request, PortletResponse response, Model model,
                                     @RequestParam(required = true) String userId
    ) throws SystemException, PortalException {

        try{
            initUserWrapper(request, model, userId);

            if(_currentUserProfile.isInitialized()) {
                model.addAttribute("userBean", _currentUserProfile.getUserBean());
                model.addAttribute("newsletterBean", new NewsletterBean(_currentUserProfile.getUserBean().getEmailStored()));
                if (_currentUserProfile.isViewingOwnProfile()) {
                    return "editUserProfile";
                }
            }
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
        }

        return "showProfileNotInitialized";

    }

    @RequestMapping(params = "page=subscriptions")
    public String showUserProfileSubscriptions(PortletRequest request, PortletResponse response, Model model,
                                      @RequestParam(required = true) String userId,
                                      @RequestParam(required = false, defaultValue = "1") int paginationId
    ) throws SystemException, PortalException {

        try{
            initUserWrapper(request, model, userId);

            if(_currentUserProfile.isInitialized()) {
                _currentUserProfile.setSubscriptionsPaginationPageId(paginationId);
                model.addAttribute("userBean", _currentUserProfile.getUserBean());
                    return "showUserSubscriptions";
            }
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
        }

        return "showProfileNotInitialized";

    }

    @RequestMapping(params = "page=subscriptionsManage")
    public String showUserSubscriptionsManage(PortletRequest request, PortletResponse response, Model model,
                                               @RequestParam(required = true) String userId,
                                               @RequestParam(required = false) String typeFilter
    ) throws SystemException, PortalException {

        try{
            initUserWrapper(request, model, userId);
            if(_currentUserProfile.isInitialized()) {
                if(typeFilter != null){
                    _currentUserProfile.getUserSubscriptions().setFilterType(typeFilter);
                }
                model.addAttribute("userBean", _currentUserProfile.getUserBean());
                model.addAttribute("userSubscriptions", _currentUserProfile.getUserSubscriptions());

                if (_currentUserProfile.isViewingOwnProfile()) {
                    return "showUserSubscriptionsManage";
                }
            }
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
        }

        return "showProfileNotInitialized";

    }

    @RequestMapping(params = "action=navigateSubscriptions")
    public void navigateSubscriptions(ActionRequest request, Model model, ActionResponse response,
                                      @RequestParam(required = true) String paginationAction
    ) throws IOException {

        Integer paginationPageId = 1;
        switch(paginationAction){
            case "First": paginationPageId = 1; break;
            case "<Previous": paginationPageId = _currentUserProfile.getSubscriptionsPaginationPageId() - 1; break;
            case "Next>": paginationPageId = _currentUserProfile.getSubscriptionsPaginationPageId() + 1; break;
            case "Last": paginationPageId = _currentUserProfile.getSubscriptionsPaginationPageMax(); break;
        }
        //response.setRenderParameter("paginationId", paginationPageId.toString());
        response.sendRedirect("/web/guest/member/-/member/userId/" + _currentUserProfile.getUserId().toString()+
                "/page/subscriptions/"+paginationPageId.toString());
    }

    @RequestMapping(params = "messageError=true")
    public String sendMessageError(PortletRequest request, Model model,
                               @RequestParam(required = false) String redirect,
                               @RequestParam(required = true) String userId) {

        model.addAttribute("limitExceeded", true);

        try{
            initUserWrapper(request, model, userId);
            if(_currentUserProfile.isInitialized()) {
                model.addAttribute("userBean", _currentUserProfile.getUserBean());
                model.addAttribute("userSubscriptions", _currentUserProfile.getUserSubscriptions());
                return "showUserProfile";
            }
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
        }
        return "showProfileNotInitialized";

    }

    @RequestMapping(params = "messageSuccess=true")
    public String sendMessageSuccess(PortletRequest request, Model model,
                                     @RequestParam(required = false) String redirect,
                                     @RequestParam(required = true) String userId) {

        model.addAttribute("messageSent", true);

        try{
            initUserWrapper(request, model, userId);
            if(_currentUserProfile.isInitialized()) {
                model.addAttribute("userBean", _currentUserProfile.getUserBean());
                model.addAttribute("userSubscriptions", _currentUserProfile.getUserSubscriptions());
                return "showUserProfile";
            }
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
        }
        return "showProfileNotInitialized";

    }


    @RequestMapping(params = "action=send")
    public void sendMessage(ActionRequest request, Model model, ActionResponse response,
                            @Valid MessageBean messageBean, BindingResult result
    ) throws  AddressException, SystemException, PortalException, MailEngineException {


        if (!result.hasErrors()) {

            if (messageBean.getMessageHoneypot() != null && messageBean.getMessageHoneypot().length() > 0) {
                _log.warn("Message was not sent because honeypot was filled - text: " + messageBean.getMessageText() + " honeypot: " + messageBean.getMessageHoneypot());
                //trick bot into thinking message was sent
                // currentUser.setMessageSent(true); TODO
                return;
            }

            if (messageBean.getMessageText() != null && messageBean.getMessageText().trim().length() > 0 && _currentUserProfile != null && _loggedInUser!=null) { // TODO check recipient user for not null

                List<Long> recipients = new ArrayList<Long>();
                recipients.add(_currentUserProfile.getUserId());

                boolean success = MessageUtil.checkLimitAndSendMessage(messageBean.getMessageSubject(), messageBean.getMessageText(), _loggedInUser, recipients);
                if (success) {
                    response.setRenderParameter("messageSuccess", "true");
                } else {
                    response.setRenderParameter("messageError", "true");
                }
            }
        } else {
            response.setRenderParameter("messageError", "true");
        }

        response.setRenderParameter("userId", _currentUserProfile.getUserId().toString());
        SessionErrors.clear(request);
        SessionMessages.clear(request);
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
            initUserWrapper(request, model, userId);

            if (_currentUserProfile != null) {
                if (_currentUserProfile.isViewingOwnProfile()) {
                    return "editUserProfile";
                } else {
                    return "showUserProfile";
                }
            }

        } catch (Exception e) {
            _log.warn("Could not create user profile for " + userId);
        }

        return "showProfileNotInitialized";
    }

    @RequestMapping(params = "updateSuccess=true")
    public String updateProfileSuccess(PortletRequest request, Model model,
                                     @RequestParam(required = true) String userId) {

        model.addAttribute("updateSuccess", true);

        try{
            initUserWrapper(request, model, userId);
        } catch(Exception e){
            _log.warn("Could not create user profile for " + userId);
            return "showProfileNotInitialized";
        }

        //response.sendRedirect("/web/guest/member/-/member/userId/" + _currentUserProfile.getUserId().toString());

        return "showUserProfile";
    }

    @RequestMapping(params = "action=update")
    public void updateUserProfile(ActionRequest request, Model model,
                             ActionResponse response,
                             @ModelAttribute UserBean updatedUserBean,
                             BindingResult result
    ) throws IOException, PwdEncryptorException{

        boolean changedUserPart = false;
        boolean validationError = false;
        boolean eMailChanged = false;

        if (updatedUserBean.getPassword().trim().length() > 0) {
            if(_currentUserProfile.getUser().getPassword()
                    .equals(PwdEncryptor.encrypt(updatedUserBean.getCurrentPassword().trim()))){

                validator.validate(updatedUserBean, result, UserBean.PasswordChanged.class);

                if (!result.hasErrors()) {
                    _currentUserProfile.getUser().setPassword(PwdEncryptor.encrypt(updatedUserBean.getPassword().trim()));
                    changedUserPart = true;
                }
                else {
                    validationError = true;
                    response.setRenderParameter("passwordError", "true");
                    _log.warn("CompareStrings password failed for userId: " + _currentUserProfile.getUser().getUserId());
                }
            } else {
                result.addError(new ObjectError("currentPassword","Password change failed: Current password is incorrect."));
                validationError = true;
                response.setRenderParameter("passwordError", "true");
                _log.warn("Current password wrong for userId: " + _currentUserProfile.getUser().getUserId());
            }
        }

        if (!updatedUserBean.getScreenName().equals(_currentUserProfile.getUserBean().getScreenName())) {
            validator.validate(updatedUserBean, result, UserBean.ScreenNameChanged.class);

            if (!result.hasErrors()) {
                _currentUserProfile.getUser().setScreenName(updatedUserBean.getScreenName());
                changedUserPart = true;
            }else{
                validationError = true;
                _log.warn("ScreenName change failed for userId: " + _currentUserProfile.getUser().getUserId());
            }
        }

        if (updatedUserBean.getEmail()!=null && updatedUserBean.getEmail().trim().length() > 0 &&
                !updatedUserBean.getEmail().equals(_currentUserProfile.getUserBean().getEmailStored())) {
            validator.validate(updatedUserBean, result, UserBean.EmailChanged.class);

            if (!result.hasErrors()) {
                _currentUserProfile.getUser().setEmailAddress(updatedUserBean.getEmail());
                changedUserPart = true;
                eMailChanged = true;
            } else {
                validationError = true;
                response.setRenderParameter("emailError", "true");
                _log.warn("Email change failed for userId: " + _currentUserProfile.getUser().getUserId());
            }

        }

        if (!updatedUserBean.getFirstName().equals(_currentUserProfile.getUserBean().getFirstName())) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors()) {
                _currentUserProfile.getUser().setFirstName(updatedUserBean.getFirstName());
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("First name change failed for userId: " + _currentUserProfile.getUser().getUserId());
            }
        }
        if (!updatedUserBean.getLastName().equals(_currentUserProfile.getUserBean().getLastName())) {
            validator.validate(updatedUserBean, result);
            if (!result.hasErrors()) {
                _currentUserProfile.getUser().setLastName(updatedUserBean.getLastName());
                changedUserPart = true;
            } else {
                validationError = true;
                _log.warn("First name change failed for userId: " + _currentUserProfile.getUser().getUserId());
            }
        }

        try {
            changedUserPart = changedUserPart | updateUserProfile(updatedUserBean);
        } catch(Exception e){
            _log.warn("Updating Expando settings or portrait image failed for userId: " + _currentUserProfile.getUser().getUserId());
            _log.warn(e);
            validationError = true;
        }


        if(validationError) {
            response.setRenderParameter("userId", _currentUserProfile.getUserId().toString());
            response.setRenderParameter("updateError", "true");
            updatedUserBean.setPortrait(_currentUserProfile.getUserBean().getPortrait());
        }
        else if(changedUserPart){

            response.setRenderParameter("userId", _currentUserProfile.getUserId().toString());
            response.setRenderParameter("updateSuccess", "true");

            updatedUserBean.setPortrait(_currentUserProfile.getUserBean().getPortrait());

            try {
                UserLocalServiceUtil.updateUser(_currentUserProfile.getUser());
            } catch(SystemException e) {
                response.removePublicRenderParameter("updateSuccess");
                response.setRenderParameter("updateError", "true");
                _log.warn("Updating user failed for userId: " + _currentUserProfile.getUser().getUserId());
                _log.warn(e);
            }

            if (eMailChanged) {
                updatedUserBean.setEmailStored(updatedUserBean.getEmail());
                try {
                    sendUpatedEmail(_currentUserProfile.getUser());
                } catch(AddressException | MailEngineException e){
                    _log.warn("Sending eMail confirmation after email change failed for userId: " + _currentUserProfile.getUser().getUserId());
                    _log.warn(e);
                }
            }
        }
        else {
            response.sendRedirect("/web/guest/member/-/member/userId/" + _currentUserProfile.getUserId().toString());
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

    }

    private void initUserWrapper(PortletRequest request, Model model, String userId
    ) throws PortalException, SystemException {

        _loggedInUser = com.liferay.portal.util.PortalUtil.getUser(request);
        _currentUserProfile = null;

        if (userId != null) {

            try {
                _currentUserProfile = new UserProfileWrapper(Long.parseLong(userId), request);
                model.addAttribute("currentUser", _currentUserProfile);
                model.addAttribute("baseImagePath", _currentUserProfile.getThemeDisplay().getPathImage());
                model.addAttribute("messageBean", new MessageBean());

            } catch (NumberFormatException e) {
                _log.warn("Can't parse user id: " + userId);
            }
        }
    }

    private boolean  updateUserProfile(UserBean updatedUserBean) throws Exception {

        boolean changedDetails = false;

        long companyId = CompanyThreadLocal.getCompanyId();
        if (companyId == 0) {
            CompanyThreadLocal.setCompanyId(_currentUserProfile.getThemeDisplay().getCompanyId());
            changedDetails = true;
        }

        String existingBio = ExpandoValueLocalServiceUtil.getData(
                User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.BIO, _currentUserProfile.getUser().getUserId(), StringPool.BLANK);
        if (!existingBio.equals(updatedUserBean.getShortBio())) {
            ExpandoValueLocalServiceUtil.addValue(User.class.getName(),
                    CommunityConstants.EXPANDO, CommunityConstants.BIO,
                    _currentUserProfile.getUser().getUserId(), updatedUserBean.getShortBio());
            changedDetails = true;
        }

        String existingCountry = Helper.getCodeForCounty(ExpandoValueLocalServiceUtil.getData(
                User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.COUNTRY, _currentUserProfile.getUser().getUserId(), StringPool.BLANK));

        if (!existingCountry.equals(updatedUserBean.getCountry())) {
            ExpandoValueLocalServiceUtil.addValue(User.class.getName(),
                    CommunityConstants.EXPANDO, CommunityConstants.COUNTRY,
                    _currentUserProfile.getUser().getUserId(), Helper.getCountryForCode(updatedUserBean.getCountry()));
            changedDetails = true;
        }


        // TODO
        //	if(changedExpando || changedUserPart) {
        //	fireGoogleEvent = !profileWasComplete && profileIsComplete();
        //}


        if (updatedUserBean.getImageId() != null && updatedUserBean.getImageId() != _currentUserProfile.getUserBean().getImageId()) {
            Image img = ImageLocalServiceUtil.getImage(updatedUserBean.getImageId());
            // we need to set permission checker for liferay
            PermissionChecker permissionChecker = PermissionCheckerFactoryUtil
                    .create(_currentUserProfile.getUser(), true);
            PermissionThreadLocal
                    .setPermissionChecker(permissionChecker);
            if (img != null) {
                byte[] bytes = img.getTextObj();
                UserServiceUtil.updatePortrait(_currentUserProfile.getUser().getUserId(), bytes);
                _currentUserProfile.getUser().setPortraitId(0L);
                UserLocalServiceUtil.updateUser(_currentUserProfile.getUser());
                UserServiceUtil.updatePortrait(_currentUserProfile.getUser().getUserId(), bytes);
                _currentUserProfile.setUser(UserLocalServiceUtil.getUser(_currentUserProfile.getUser().getUserId()));
                changedDetails = true;
            }
        }

        if (updatedUserBean.getSendEmailOnMessage() != MessageUtil.getMessagingPreferences(_currentUserProfile.getUser().getUserId()).getEmailOnReceipt()){
            MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(_currentUserProfile.getUser().getUserId());
            prefs.setEmailOnReceipt(updatedUserBean.getSendEmailOnMessage());
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            changedDetails = true;
        }

        if (updatedUserBean.getSendEmailOnActivity() != MessageUtil.getMessagingPreferences(_currentUserProfile.getUser().getUserId()).getEmailOnActivity()){
            MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(_currentUserProfile.getUser().getUserId());
            prefs.setEmailOnActivity(updatedUserBean.getSendEmailOnActivity());
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            changedDetails = true;
        }

        if (updatedUserBean.getSendDailyEmailOnActivity() != MessageUtil.getMessagingPreferences(_currentUserProfile.getUser().getUserId()).getEmailActivityDailyDigest()){
            MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(_currentUserProfile.getUser().getUserId());
            prefs.setEmailActivityDailyDigest(updatedUserBean.getSendDailyEmailOnActivity());
            MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
            changedDetails = true;
        }

        return changedDetails;

    }

    private void sendUpatedEmail(User user) throws AddressException, MailEngineException {
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

        InternetAddress replyTo[] = {addressFrom};

        MailEngine.send(addressFrom, addressTo, null, null, null,
                messageSubject, messageBody, false, replyTo, null, null);
    }
}
