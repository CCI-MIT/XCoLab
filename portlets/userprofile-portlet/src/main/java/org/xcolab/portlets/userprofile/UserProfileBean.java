package org.xcolab.portlets.userprofile;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.messaging.MessageConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.*;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.util.mail.MailEngineException;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserProfileBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserWrapper currentUser; 
    private User wrappedUser;
    private final static String USER_ID_PARAM = "userId";
    private final static Log _log = LogFactoryUtil.getLog(UserProfileBean.class);
    private static final String PAGE_PARAM = "page";
    private static final String PAGE_PARAM_SUBSCRIPTIONS = "subscriptions";
    private static final String PAGE_PARAM_MANAGE_SUBSCRIPTIONS = "subscriptionsManage";
    private static final String PAGE_PARAM_EDIT_PROFILE = "edit";
    private boolean viewingOwnProfile = false;
    private boolean editing = false;
    private String messageText;
    private String messageSubject;
    private List<MessageBean> messages;
    private ArrayList<UserActivityBean> subscribedActivities;
    private PageType pageType = PageType.PROFILE_NOT_INITIALIZED;
    private UserSubscriptionsBean subscriptionsBean;
    private String messagingPortletId;
    private boolean messageSent;
    private BadgeBean badges;

    private boolean displayEMailErrorMessage = false;
    
    public UserProfileBean() {
        Map<String, String> parameters = Helper.getUrlParametersMap();
        long companyId = CompanyThreadLocal.getCompanyId(); 
        if (companyId == 0) {
            CompanyThreadLocal.setCompanyId(Helper.getThemeDisplay().getCompanyId());
        }
        
        if (parameters.containsKey(USER_ID_PARAM)) {
            try {
                Long userId = Long.parseLong(parameters.get(USER_ID_PARAM));
                wrappedUser = UserLocalServiceUtil.getUser(userId);
                currentUser = new UserWrapper(wrappedUser);
                pageType = PageType.PROFILE_DETAILS;
                subscriptionsBean = new UserSubscriptionsBean(wrappedUser);
            }
            catch (NumberFormatException e) {
                _log.error("Can't parse user id: " + parameters.get(USER_ID_PARAM), e);
            }
            catch (SystemException e) {
                _log.error("Can't load user with id " + parameters.get(USER_ID_PARAM), e);
            }
            catch (PortalException e) {
                _log.error("Can't load user with id " + parameters.get(USER_ID_PARAM), e);
            }
        }
        
        if (parameters.containsKey(PAGE_PARAM)) {
            if (PAGE_PARAM_SUBSCRIPTIONS.equals(parameters.get(PAGE_PARAM)))
                pageType = PageType.SUBSCRIPTIONS_VIEW;
        }

        if (wrappedUser != null && 
                Helper.isUserLoggedIn() && 
                wrappedUser.getUserId() == Helper.getLiferayUser().getUserId()) {
            // user is logged in and is viewing his own profile
            viewingOwnProfile = true;

            if (parameters.containsKey(PAGE_PARAM)) {
                if (PAGE_PARAM_MANAGE_SUBSCRIPTIONS.equals(parameters.get(PAGE_PARAM)))
                    pageType = PageType.SUBSCRIPTIONS_MANAGE;
                else if (PAGE_PARAM_EDIT_PROFILE.equals(parameters.get(PAGE_PARAM))) 
                    pageType = PageType.PROFILE_EDIT;
            }
            
        }

        try {
            //display message if user is in the ignored recipients list
            MessagingIgnoredRecipients r = MessagingIgnoredRecipientsLocalServiceUtil.findByEmail(wrappedUser.getDisplayEmailAddress());
            if(r.getUserId() == 0) {
                displayEMailErrorMessage = true;
            }
        } catch (Exception e) {

        }
        if (currentUser != null) {
        	badges = new BadgeBean(currentUser.getUserId());
        }
    }

    public boolean isDisplayEMailErrorMessage() {
        return displayEMailErrorMessage;
    }
    
    public boolean isInitialized() {
        return currentUser != null;
    }
    
    
    public UserWrapper getCurrentUser() {
        return currentUser;
    }

    public boolean isViewingOwnProfile() {
        return viewingOwnProfile;
    }

    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEditing(ActionEvent e) throws PortalException, SystemException {
        editing = !editing;
        if (! editing) {
            currentUser.cancelChanges();
            pageType = PageType.PROFILE_DETAILS;
        }
        else {
            pageType = PageType.PROFILE_EDIT;
        }
    }
    
    public void updateUser(ActionEvent e) throws Exception {
        currentUser.persistChanges();
        editing = !editing;
        pageType = PageType.PROFILE_DETAILS;
    }
    
    public void setMessageText(String message) {
        this.messageText = message;
    }
    
    public String getMessageText() {
        return messageText;
    }
    
    public void setMessageSubject(String subject) {
        this.messageSubject = subject;   
    }
    
    public String getMessageSubject() {
        return messageSubject;
    }
    
    
    public void sendMessage(ActionEvent e) throws AddressException, SystemException, PortalException, MailEngineException {
        if (messageText != null && messageText.trim().length() > 0 && wrappedUser != null && Helper.isUserLoggedIn()) {
            //MessageUtil.sendMessage(subject, content, fromId, repliesTo, tousers, request)
            
            List<Long> recipients = new ArrayList<Long>();
            recipients.add(wrappedUser.getUserId());
            
            MessageUtil.sendMessage(messageSubject, messageText, Helper.getLiferayUser().getUserId(), 
                    Helper.getLiferayUser().getUserId(),recipients, null);
            messageSent = true;
        }
    }
    
    public boolean getMessageSent() {
        return messageSent;
    }
    
    public List<MessageBean> getMessages() throws SystemException, PortalException {
        if (messages == null) {
            messages = new ArrayList<MessageBean>();
            for (Message msg: MessageUtil.getMessages(currentUser.getUserId(), 0, 2, MessageConstants.INBOX)) {
                messages.add(new MessageBean(msg));
            }
        }
        return messages;
    }
    
    public List<UserActivityBean> getSubscribedActivities() throws SystemException, PortalException {
        if (subscribedActivities == null) {
            subscribedActivities = new ArrayList<UserActivityBean>();
            for (SocialActivity activity: ActivityUtil.groupActivities(ActivitySubscriptionLocalServiceUtil.getActivities(wrappedUser.getUserId(), 0, 1000))) {
                try {
                    subscribedActivities.add(new UserActivityBean(activity));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
            
        }
        return subscribedActivities;
    }
    
    public boolean getSendEmailOnMessage() throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(Helper.getLiferayUser().getUserId());
        return prefs.getEmailOnReceipt();
    }
    
    public void setSendEmailOnMessage(boolean send) throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(Helper.getLiferayUser().getUserId());
        prefs.setEmailOnReceipt(send);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
    }
    
    public boolean getSendEmailOnActivity() throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(Helper.getLiferayUser().getUserId());
        return prefs.getEmailOnActivity();
    }
    
    public void setSendEmailOnActivity(boolean send) throws SystemException {
        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(Helper.getLiferayUser().getUserId());
        prefs.setEmailOnActivity(send);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);
    }
    
    public void showPage(ActionEvent e) {
        try {
            String name = String.valueOf(e.getComponent().getAttributes().get("name"));
            pageType = PageType.valueOf(name);
        }
        catch (Exception ex) {
            // ignore
        }
    }


    public PageType getPageType() {
        return pageType;
    }

    public void setMessagingPortletId(String messagingPortletId) {
        this.messagingPortletId = messagingPortletId;
    }

    public String getMessagingPortletId() {
        return messagingPortletId;
    }

    public UserSubscriptionsBean getSubscriptionsBean() {
        return subscriptionsBean;
    }

    public BadgeBean getBadges(){
        return badges;
    }

    public void unlinkSSOAccount(ActionEvent event) throws SystemException, PortalException {
        String accountType = event.getComponent().getAttributes().get("accountType").toString();
        User u = UserLocalServiceUtil.getUser(wrappedUser.getUserId());
        if (accountType.equalsIgnoreCase("FACEBOOK")) u.setFacebookId(0);
        else if (accountType.equalsIgnoreCase("GOOGLE")) u.setOpenId("");
        UserLocalServiceUtil.updateUser(u);
        editing = !editing;
        pageType = PageType.PROFILE_DETAILS;
    }
    
    public long getTimestamp() {
    	return new Date().getTime();
    }

}
