package org.xcolab.portlets.userprofilenew.wrappers;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.messaging.MessageConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Message;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.userprofilenew.beans.BadgeBean;
import org.xcolab.portlets.userprofilenew.beans.MessageBean;
import org.xcolab.portlets.userprofilenew.beans.UserBean;
import org.xcolab.portlets.userprofilenew.entity.Badge;
import org.xcolab.utils.SendMessagePermissionChecker;


import javax.portlet.PortletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class
        UserProfileWrapper implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private User user = null;
    private UserBean userBean = null; // TODO remove.

    private String realName;  // TODO remove

    private Boolean attendsConference;
    private MemberRole role;
    private int maxActivitiesCount = 50;

    private List<MessageBean> messages;
    private SendMessagePermissionChecker _messagePermissionChecker;
    private List<SupportedPlanWrapper> supportedPlans = new ArrayList<SupportedPlanWrapper>();
    private List<UserActivityWrapper> userActivities = new ArrayList<UserActivityWrapper>();
    private List<ProposalWrapper> userProposals = new ArrayList<ProposalWrapper>();
    private ArrayList<UserActivityWrapper> subscribedActivities;
    private UserSubscriptionsWrapper userSubscriptions;
    private BadgeBean badges;

    private boolean profileWasComplete = false;
    private boolean fireGoogleEvent = false;
    private boolean displayEMailErrorMessage = false;
    private boolean viewingOwnProfile = false;

    private String messagingPortletId = "messagingportlet_WAR_messagingportlet";
    private ThemeDisplay _themeDisplay;
    private final static Log _log = LogFactoryUtil.getLog(UserProfileWrapper.class);


    public UserProfileWrapper(Long userId, PortletRequest request) throws PortalException, SystemException {
        _themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loggedInUser = com.liferay.portal.util.PortalUtil.getUser(request);

        User user;
        try {
            user = UserLocalServiceUtil.getUser(userId);
            if (user.isActive()) {

                if (loggedInUser != null) {
                    _messagePermissionChecker = new SendMessagePermissionChecker(loggedInUser);

                    if(loggedInUser.getUserId() == user.getUserId()) {
                        viewingOwnProfile = true;
                    }
                }

                init(user);
            }
        } catch (SystemException e) {
            System.out.println("Can't load user with id " + userId);
        } catch (PortalException e) {
            System.out.println("Can't load user with id " + userId);
        }

    }

    private void init(User user) throws PortalException, SystemException {
        this.user = user;

        userBean = new UserBean(user);

        // TODO check whether needed
        realName = getName(user.getFullName(), user.getScreenName());

        String firstPart = realName.substring(0, realName.length() / 2).trim();
        String secondPart = realName.substring(realName.length() / 2).trim();

        if (firstPart.equals(secondPart)) {
            realName = user.getFirstName();
        }

        profileWasComplete = profileIsComplete();

        attendsConference = ExpandoValueLocalServiceUtil.getData(User.class.getName(), CommunityConstants.EXPANDO, CommunityConstants.CONFERENCE2014, user.getUserId(), "").equals("1");
        badges = new BadgeBean(user.getUserId());

        List<Role> roles = user.getRoles();
        // Determine the highest role of the user (copied from {@link org.xcolab.portlets.members.MemberListItemBean})
        MemberRole currentRole = MemberRole.MEMBER;
        role = MemberRole.MEMBER;

        for (Role r: roles) {
            final String roleString = r.getName();

            currentRole = MemberRole.getMember(roleString);
            if (currentRole != null && role != null) {
                if (currentRole.ordinal() > role.ordinal()) {
                    role = currentRole;
                }
            }
        }

        if (role == MemberRole.MODERATOR) role = MemberRole.STAFF;

        userSubscriptions = new UserSubscriptionsWrapper(user);

        supportedPlans.clear();
        for(Object o : ProposalSupporterLocalServiceUtil.getProposals(user.getUserId())) {
            ProposalSupporter ps = (ProposalSupporter) o;
            try {
                supportedPlans.add(new SupportedPlanWrapper(ps));
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }

        userActivities.clear();
        for (SocialActivity activity : ActivityUtil.groupActivities(SocialActivityLocalServiceUtil
                .getUserActivities(user.getUserId(), 0, maxActivitiesCount))) {

            UserActivityWrapper a = new UserActivityWrapper(activity,_themeDisplay );
            if(a.getBody() !=null && !a.getBody().equals(""))
                userActivities.add(a);
        }

        userProposals.clear();
        List<Proposal> proposals = ProposalLocalServiceUtil.getUserProposals(user.getUserId());
        userProposals = new ArrayList<>();

        for (Proposal p : proposals) {
            userProposals.add(new ProposalWrapper(p));
        }

    }

    private boolean profileIsComplete() {
        //ignore mandatory fields, only care about optional ones
        String[] blankCheck = {userBean.getShortBio(), userBean.getCountry()};
        for (String s : blankCheck) if (s == null || s.equals(StringPool.BLANK)) return false;

        return true;
    }

    public boolean isInitialized() {
        return this.user != null;
    }

    public boolean isViewingOwnProfile() {
        return viewingOwnProfile;
    }

    public void setUser(User user){ this.user=user; }

    public User getUser(){ return user; }

    public Long getUserId() {
        return user.getUserId();
    }

    public UserBean getUserBean(){ return userBean; }

    public void setUserBean(UserBean userBean){ this.userBean=userBean; }

    private String getName(String name, String defaultName) {
        if (name == null || name.trim().equals("")) {
            return defaultName;
        }
        return name;
    }

    public Date getJoinDate() { return user.getCreateDate(); }

    public Boolean getAttendsConference() { return attendsConference; }

    public void setAttendsConference(Boolean attendsConference) { this.attendsConference = attendsConference; }

    public String getRealName() { return realName; }


    // TODO check this
    // public void setAbout(String about) throws UserInputException { this.about = UserInputFilterUtil.filterHtml(about); }
    //public String getFilteredAbout() {        return filteredAbout;    }

    public boolean getHasFacebookId() {
        return user.getFacebookId() != 0;
    }

    public boolean getHasOpenId() {
        return (user.getOpenId() != null && !user.getOpenId().isEmpty());
    }

    public void setMessagingPortletId(String messagingPortletId) {
        this.messagingPortletId = messagingPortletId;
    }

    public String getMessagingPortletId() {
        return messagingPortletId;
    }

    public User getWrapped() {
        return user;
    }

    public boolean isFireGoogleEvent() {
        return fireGoogleEvent;
    }

    public MemberRole getRole() { return role; }

    public boolean isDisplayEMailErrorMessage() {
        return displayEMailErrorMessage;
    }

    public ThemeDisplay getThemeDisplay() {
        return _themeDisplay;
    }

    public boolean getCanSendMessage() throws SystemException {
        if (_messagePermissionChecker != null) {
            return _messagePermissionChecker.canSendToUser(this.user);
        }
        return false;
    }

    public List<MessageBean> getMessages() throws SystemException, PortalException {
        if (messages == null) {
            messages = new ArrayList<MessageBean>();
            for (Message msg: MessageUtil.getMessages(this.user.getUserId(), 0, 2, MessageConstants.INBOX)) {
                messages.add(new MessageBean(msg));
            }
        }
        return messages;
    }

    public List<UserActivityWrapper> getSubscribedActivities() throws SystemException, PortalException {
        if (subscribedActivities == null) {
            subscribedActivities = new ArrayList<UserActivityWrapper>();
            for (SocialActivity activity: ActivityUtil.groupActivities(ActivitySubscriptionLocalServiceUtil.getActivities(this.user.getUserId(), 0, 1000))) {
                try {
                    subscribedActivities.add(new UserActivityWrapper(activity,_themeDisplay));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }

        }
        return subscribedActivities;
    }

    public List<SupportedPlanWrapper> getSupportedPlans() { return supportedPlans; }

    public List<UserActivityWrapper> getActivities() { return userActivities; }

    public List<ProposalWrapper> getProposals() { return userProposals; }

    public List<Badge> getBadges() { return badges.getBadges(); }

}
