package org.xcolab.portlets.userprofile.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.service.PointsLocalServiceUtil;
import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.legacy.utils.SendMessagePermissionChecker;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.pojo.ContestTypeProposal;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.entity.utils.EntityGroupingUtil;
import org.xcolab.enums.Plurality;
import org.xcolab.portlets.userprofile.beans.BadgeBean;
import org.xcolab.portlets.userprofile.beans.MessageBean;
import org.xcolab.portlets.userprofile.beans.UserBean;
import org.xcolab.portlets.userprofile.entity.Badge;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

public class UserProfileWrapper implements Serializable {

    private static final Logger _log = LoggerFactory.getLogger(UserProfileWrapper.class);

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
    private final Map<Long, ContestTypeProposal> contestTypeProposalWrappersByContestTypeId = new HashMap<>();
    private List<Proposal> linkingProposals;
    private final ArrayList<UserActivityWrapper> userActivities = new ArrayList<>();
    private List<UserActivityWrapper> subscribedActivities;
    private UserSubscriptionsWrapper userSubscriptions;
    private BadgeBean badges;

    private boolean viewingOwnProfile;

    private String messagingPortletId = "messagingportlet_WAR_messagingportlet";
    private final ThemeDisplay themeDisplay;

    public UserProfileWrapper(long userId, PortletRequest request)
            throws MemberNotFoundException {
        themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        user = MembersClient.getMember(userId);
        if (user.isActive()) {
            User loggedInUser = null;
            try {
                loggedInUser = com.liferay.portal.util.PortalUtil.getUser(request);
            } catch (PortalException | SystemException e) {
                throw new InternalException(e);
            }
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

    private void init(Member user) {
        try {
            this.user = user;

            userBean = new UserBean(user);
            realName = getName(user.getFullName(), user.getScreenName());

            String firstPart = realName.substring(0, realName.length() / 2).trim();
            String secondPart = realName.substring(realName.length() / 2).trim();

            if (firstPart.equals(secondPart)) {
                realName = user.getFirstName();
            }

            attendsConference = false; //TODO: store this outside expando if we want to reactive this
            badges = new BadgeBean(user.getId_());

            try {
                role = MemberRole.getHighestRole(user.getRoles());
            } catch (MemberRole.NoSuchMemberRoleException ignored) {
            }

            userSubscriptions = new UserSubscriptionsWrapper(user);
            supportedProposals.clear();
            userActivities.clear();
            for (ProposalSupporter ps : ProposalMemberRatingClientUtil.getProposalSupportersByUserId(user.getId_())) {
                supportedProposals.add(new SupportedProposalWrapper(ps));
            }

            for (ActivityEntry activity : ActivityUtil.groupActivities(ActivitiesClientUtil
                    .getActivityEntries(0, MAX_ACTIVITIES_COUNT, user.getId_(), null))) {

                UserActivityWrapper a = new UserActivityWrapper(activity, themeDisplay);
                if (a.getBody() != null && !a.getBody().equals("")) {
                    userActivities.add(a);
                }
            }

            List<Proposal> proposals = ProposalClientUtil.getMemberProposals(user.getId_());
            Map<ContestType, List<Proposal>> proposalsByContestType = EntityGroupingUtil
                    .groupByContestType(proposals);
            for (ContestType contestType : ContestClientUtil.getActiveContestTypes()) {
                contestTypeProposalWrappersByContestTypeId
                        .put(contestType.getId_(), new ContestTypeProposal(contestType));
                final List<Proposal> proposalsInContestType = proposalsByContestType
                        .get(contestType);
                for (Proposal p : proposalsInContestType) {
                    contestTypeProposalWrappersByContestTypeId.get(contestType.getId_())
                            .getProposals()
                            .add(p);
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new InternalException(e);
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

    public boolean getCanSeeSendMessageButton() throws MemberRole.NoSuchMemberRoleException {
        return messagePermissionChecker == null || messagePermissionChecker
                .canSendToUser(this.user);
    }

    public List<MessageBean> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
            for (Message msg : MessagingClient.getMessages(this.user.getId_(), 0, 2, MessageType.INBOX)) {
                messages.add(new MessageBean(msg));
            }
        }
        return messages;
    }

    public List<UserActivityWrapper> getSubscribedActivities() {
        if (subscribedActivities == null) {
            subscribedActivities = new ArrayList<>();
            for (ActivityEntry activity : ActivityUtil.groupActivities(
                    ActivitiesClientUtil.getActivityEntries(0, 100, this.user.getId_(), null))) {

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

    public Collection<ContestTypeProposal> getContestTypeProposalWrappersByContestTypeId() {
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
        return MembersClient.getMemberMaterializedPoints(getUserId());
    }

    public String getPotentialPointsFormatted() {
        return String.format("%,d", getPotentialPoints());
    }

    public long getPotentialPoints() {
        return MembersClient.getMemberHypotheticalPoints(getUserId());
    }

    public List<Proposal> getLinkingProposals() {
        if (linkingProposals == null) {
                linkingProposals = new ArrayList<>();
                List<Proposal> proposals = ProposalClientUtil.getLinkingProposalsForUser(getUserId());
                for (Proposal p : proposals) {
                    linkingProposals.add((p));
                }

        }
        return linkingProposals;
    }

    public String getProposalsString() {
        if (proposalsString == null) {
            proposalsString =
                    ContestClientUtil.getProposalNames(
                    new ArrayList<>(contestTypeProposalWrappersByContestTypeId.keySet()), Plurality.PLURAL.name(),
                    "or");
        }
        return proposalsString;
    }

    public String getProposalString() {
        if (proposalString == null) {
            proposalString = ContestClientUtil.getProposalNames(
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
