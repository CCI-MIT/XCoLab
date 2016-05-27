package org.xcolab.portlets.userprofile.wrappers;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.PointsLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.legacy.utils.SendMessagePermissionChecker;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.enums.Plurality;
import org.xcolab.portlets.userprofile.beans.BadgeBean;
import org.xcolab.portlets.userprofile.beans.MessageBean;
import org.xcolab.portlets.userprofile.beans.UserBean;
import org.xcolab.portlets.userprofile.entity.Badge;
import org.xcolab.utils.EntityGroupingUtil;
import org.xcolab.wrappers.BaseProposalWrapper;
import org.xcolab.wrappers.ContestTypeProposalWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

public class UserProfileWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final long DEFAULT_COMPANY_ID = 10112L;

    private static final int MAX_ACTIVITIES_COUNT = 50;
    private static final boolean FIRE_GOOGLE_EVENT = false;
    private static final boolean DISPLAY_EMAIL_ERROR_MESSAGE = false;

    private Member user;
    private UserBean userBean;
    private String realName;
    private Boolean attendsConference;
    private MemberRole role;
    private int subscriptionsPageSize = 20;
    private int subscriptionsPaginationPageId;
    private String proposalsString;
    private String proposalString;

    private SendMessagePermissionChecker messagePermissionChecker;
    private List<MessageBean> messages;
    private final List<SupportedProposalWrapper> supportedProposals = new ArrayList<>();
    private final Map<Long, ContestTypeProposalWrapper> contestTypeProposalWrappersByContestTypeId = new HashMap<>();
    private List<BaseProposalWrapper> linkingProposals;
    private final ArrayList<UserActivityWrapper> userActivities = new ArrayList<>();
    private List<UserActivityWrapper> subscribedActivities;
    private UserSubscriptionsWrapper userSubscriptions;
    private BadgeBean badges;

    private boolean viewingOwnProfile;

    private String messagingPortletId = "messagingportlet_WAR_messagingportlet";
    private final ThemeDisplay themeDisplay;

    public UserProfileWrapper(long userId, PortletRequest request)
            throws PortalException, SystemException, MemberNotFoundException {
        themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        user = MembersClient.getMember(userId);
        if (user.isActive()) {
            User loggedInUser = com.liferay.portal.util.PortalUtil.getUser(request);
            if (loggedInUser != null) {
                Member logUser = MembersClient.getMember(loggedInUser.getUserId());
                messagePermissionChecker = new SendMessagePermissionChecker(logUser);
                if (loggedInUser.getUserId() == user.getId_()) {
                    viewingOwnProfile = true;
                }
            }
            init(user);
        }
    }

    private void init(Member user) throws PortalException, SystemException {
        this.user = user;

        userBean = new UserBean(user);
        realName = getName(user.getFullName(), user.getScreenName());

        String firstPart = realName.substring(0, realName.length() / 2).trim();
        String secondPart = realName.substring(realName.length() / 2).trim();

        if (firstPart.equals(secondPart)) {
            realName = user.getFirstName();
        }

        attendsConference = ExpandoValueLocalServiceUtil
                .getData(DEFAULT_COMPANY_ID, User.class.getName(), CommunityConstants.EXPANDO,
                        CommunityConstants.CONFERENCE2014, user.getId_(), "").equals("1");
        badges = new BadgeBean(user.getId_());

        try {
            role = MemberRole.getHighestRole(user.getRoles());
        } catch (MemberRole.NoSuchMemberRoleException ignored) {
        }

        userSubscriptions = new UserSubscriptionsWrapper(user);
        supportedProposals.clear();
        userActivities.clear();
        for (ProposalSupporter ps : ProposalSupporterLocalServiceUtil.getProposals(user.getId_())) {
            supportedProposals.add(new SupportedProposalWrapper(ps));
        }

        for (SocialActivity activity : ActivityUtil.groupActivities(SocialActivityLocalServiceUtil
                .getUserActivities(user.getId_(), 0, MAX_ACTIVITIES_COUNT))) {

            UserActivityWrapper a = new UserActivityWrapper(activity, themeDisplay);
            if (a.getBody() != null && !a.getBody().equals("")) {
                userActivities.add(a);
            }
        }

        List<Proposal> proposals = ProposalLocalServiceUtil.getUserProposals(user.getId_());
        Map<ContestType, List<Proposal>> proposalsByContestType = EntityGroupingUtil.groupByContestType(proposals);
        for (ContestType contestType : ContestTypeLocalServiceUtil.getActiveContestTypes()) {
            contestTypeProposalWrappersByContestTypeId
                    .put(contestType.getId(), new ContestTypeProposalWrapper(contestType));
            final List<Proposal> proposalsInContestType = proposalsByContestType.get(contestType);
            for (Proposal p : proposalsInContestType) {
                try {
                    contestTypeProposalWrappersByContestTypeId.get(contestType.getId()).getProposals()
                            .add(new BaseProposalWrapper(p));
                } catch (NoSuchContestException ignored) {
                }
            }
        }
    }

    private String getName(String name, String defaultName) {
        if (name == null || name.trim().equals("")) {
            return defaultName;
        }
        return name;
    }

    public boolean isStaffMemberProfile() {
        return this.role == MemberRole.STAFF;
    }

    public boolean isInitialized() {
        return this.user != null;
    }

    public boolean isActive() {
        return user != null && user.isActive();
    }

    public boolean isViewingOwnProfile() {
        return viewingOwnProfile;
    }

    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Date getJoinDate() {
        return user.getCreateDate();
    }

    public Boolean getAttendsConference() {
        return attendsConference;
    }

    public void setAttendsConference(Boolean attendsConference) {
        this.attendsConference = attendsConference;
    }

    public String getRealName() {
        return realName;
    }

    public UserSubscriptionsWrapper getUserSubscriptions() {
        return userSubscriptions;
    }

    public int getSubscriptionsPageSize() {
        return subscriptionsPageSize;
    }

    public void setSubscriptionsPageSize(int subscriptionsPageSize) {
        this.subscriptionsPageSize = subscriptionsPageSize;
    }

    public int getSubscriptionsPaginationPageId() {
        return subscriptionsPaginationPageId;
    }

    public void setSubscriptionsPaginationPageId(int subscriptionsPaginationPageId) {
        this.subscriptionsPaginationPageId = subscriptionsPaginationPageId;
    }

    public int getSubscriptionsPaginationPageMax() {
        if (subscribedActivities != null && subscriptionsPageSize != 0) {
            double d1 = subscribedActivities.size();
            double d2 = subscriptionsPageSize;
            return (int) Math.ceil(d1 / d2);
        }
        return 0;
    }

    public int getSubscribedActivitiesCount() {
        if (subscribedActivities != null) {
            return subscribedActivities.size();
        }
        return 0;
    }

    public boolean getHasFacebookId() {
        return user.getFacebookId() != null && user.getFacebookId() != 0;
    }

    public boolean getHasOpenId() {
        return (user.getOpenId() != null && !user.getOpenId().isEmpty());
    }

    public String getMessagingPortletId() {
        return messagingPortletId;
    }

    public void setMessagingPortletId(String messagingPortletId) {
        this.messagingPortletId = messagingPortletId;
    }

    public Member getWrapped() {
        return user;
    }

    public boolean isFireGoogleEvent() {
        return FIRE_GOOGLE_EVENT;
    }

    public MemberRole getRole() {
        return role;
    }

    public boolean isDisplayEMailErrorMessage() {
        return DISPLAY_EMAIL_ERROR_MESSAGE;
    }

    public ThemeDisplay getThemeDisplay() {
        return themeDisplay;
    }

    public boolean getCanSeeSendMessageButton() throws SystemException, MemberRole.NoSuchMemberRoleException {
        if (messagePermissionChecker != null) {
            return messagePermissionChecker.canSendToUser(this.user);
        }
        return true;
    }

    public List<MessageBean> getMessages() throws SystemException, PortalException {
        if (messages == null) {
            messages = new ArrayList<>();
            for (Message msg : MessageUtil.getMessages(this.user.getId_(), 0, 2, MessageType.INBOX)) {
                messages.add(new MessageBean(msg));
            }
        }
        return messages;
    }

    public List<UserActivityWrapper> getSubscribedActivities() throws SystemException, PortalException {
        if (subscribedActivities == null) {
            subscribedActivities = new ArrayList<>();
            for (SocialActivity activity : ActivityUtil.groupActivities(
                    ActivitySubscriptionLocalServiceUtil.getActivities(this.user.getId_(), 0, 1000))) {
                subscribedActivities.add(new UserActivityWrapper(activity, themeDisplay));
            }
        }
        return subscribedActivities;
    }

    public List<SupportedProposalWrapper> getSupportedProposals() {
        return supportedProposals;
    }

    public List<UserActivityWrapper> getActivities() {
        return userActivities;
    }

    public Collection<ContestTypeProposalWrapper> getContestTypeProposalWrappersByContestTypeId() {
        return contestTypeProposalWrappersByContestTypeId.values();
    }

    public List<Badge> getBadges() {
        return badges.getBadges();
    }

    public String getUserActivityCountFormatted() {
        return String.format("%,d", getUserActivityCount());
    }

    public long getUserActivityCount() {
        try {
            return Xcolab_UserLocalServiceUtil.getUserActivityCount(getUserId());
        } catch (SystemException e) {
            return 0;
        }
    }

    public Long getUserId() {
        return user.getId_();
    }

    public String getActualPointsFormatted() {
        return String.format("%,d", getActualPoints());
    }

    public long getActualPoints() {
        try {
            return PointsLocalServiceUtil.getUserMaterializedPoints(getUserId());
        } catch (SystemException e) {
            return 0;
        }
    }

    public String getPotentialPointsFormatted() {
        return String.format("%,d", getPotentialPoints());
    }

    public long getPotentialPoints() {
        try {
            return PointsLocalServiceUtil.getUserHypotheticalPoints(getUserId());
        } catch (SystemException e) {
            return 0;
        }
    }

    public List<BaseProposalWrapper> getLinkingProposals() {
        if (linkingProposals == null) {
            try {
                linkingProposals = new ArrayList<>();
                List<Proposal> proposals = PointsLocalServiceUtil.getLinkingProposalsForUser(getUserId());
                for (Proposal p : proposals) {
                    linkingProposals.add(new BaseProposalWrapper(p));
                }
            } catch (PortalException | SystemException ignored) {
            }
        }
        return linkingProposals;
    }

    public String getProposalsString() {
        if (proposalsString == null) {
            proposalsString = ContestTypeLocalServiceUtil.getProposalNames(
                    new ArrayList<>(contestTypeProposalWrappersByContestTypeId.keySet()), Plurality.PLURAL.name(),
                    "or");
        }
        return proposalsString;
    }

    public String getProposalString() {
        if (proposalString == null) {
            proposalString = ContestTypeLocalServiceUtil.getProposalNames(
                    new ArrayList<>(contestTypeProposalWrappersByContestTypeId.keySet()), Plurality.SINGULAR.name(),
                    "or");
        }
        return proposalString;
    }

    public String getDoAsUserString() {
        try {
            final Company company = CompanyLocalServiceUtil.getCompany(DEFAULT_COMPANY_ID);
            return Encryptor.encrypt(company.getKeyObj(), String.valueOf(getUserId()));
        } catch (PortalException | SystemException | EncryptorException e) {
            return "";
        }
    }
}
