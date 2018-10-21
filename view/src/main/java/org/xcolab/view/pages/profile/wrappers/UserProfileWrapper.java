package org.xcolab.view.pages.profile.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.client.members.pojo.Role;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.pojo.ContestTypeProposal;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.SupportedProposal;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.pages.profile.beans.BadgeBean;
import org.xcolab.view.pages.profile.beans.MessageBean;
import org.xcolab.view.pages.profile.beans.UserBean;
import org.xcolab.view.pages.profile.entity.Badge;
import org.xcolab.view.util.entity.ActivityUtil;
import org.xcolab.view.util.entity.EntityGroupingUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserProfileWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MAX_ACTIVITIES_COUNT = 50;
    private static final boolean FIRE_GOOGLE_EVENT = false;
    private static final boolean DISPLAY_EMAIL_ERROR_MESSAGE = false;

    private Member member;
    private UserBean userBean;
    private String realName;
    private MemberCategory highestRoleCategory;
    private int subscriptionsPageSize = 20;
    private int subscriptionsPaginationPageId;
    private String proposalsString;
    private String proposalString;

    private List<MessageBean> messages;
    private final List<SupportedProposal> supportedProposals = new ArrayList<>();
    private final Map<Long, ContestTypeProposal> contestTypeProposalWrappersByContestTypeId = new HashMap<>();
    private List<Proposal> linkingProposals;
    private final ArrayList<UserActivityWrapper> userActivities = new ArrayList<>();
    private List<UserActivityWrapper> subscribedActivities;
    private UserSubscriptionsWrapper userSubscriptions;
    private BadgeBean badges;

    private final ActivityEntryHelper activityEntryHelper;

    private boolean viewingOwnProfile;

    public UserProfileWrapper(long userId, Member loggedInMember,
            ActivityEntryHelper activityEntryHelper)
            throws MemberNotFoundException {
        this.activityEntryHelper = activityEntryHelper;

        member = MembersClient.getMember(userId);

        if (member.isActive()) {
            if (loggedInMember != null) {
                Member logUser = MembersClient.getMember(loggedInMember.getId());
                if (loggedInMember.getId() == member.getId()) {
                    viewingOwnProfile = true;
                }
            }
            init();
        }
    }

    private void init() {

        userBean = new UserBean(member);
        realName = member.getFullName();

        String firstPart = realName.substring(0, realName.length() / 2).trim();
        String secondPart = realName.substring(realName.length() / 2).trim();

        if (firstPart.equals(secondPart)) {
            realName = member.getFirstName();
        }

        badges = new BadgeBean(member.getId());

        highestRoleCategory = MembersClient.getHighestCategory(member.getRoles());

        userSubscriptions = new UserSubscriptionsWrapper(member);
        userActivities.clear();
        supportedProposals.addAll(ProposalMemberRatingClientUtil.getSupportedProposals(member.getId()));

        for (ActivityEntry activity : ActivityUtil.groupActivities(ActivitiesClientUtil
                .getActivityEntries(0, MAX_ACTIVITIES_COUNT, member.getId(), null))) {

            UserActivityWrapper a = new UserActivityWrapper(activity, activityEntryHelper);
            if (StringUtils.isNotBlank(a.getBody())) {
                userActivities.add(a);
            }
        }

        List<Proposal> proposals = ProposalClientUtil.getMemberProposals(member.getId());
        Map<ContestType, Set<Proposal>> proposalsByContestType = EntityGroupingUtil
                .groupByContestType(proposals);
        for (ContestType contestType : ContestTypeClient.getActiveContestTypes()) {
            contestTypeProposalWrappersByContestTypeId
                    .put(contestType.getId(), new ContestTypeProposal(contestType));
            final Set<Proposal> proposalsInContestType = proposalsByContestType
                    .get(contestType);
            if (proposalsInContestType != null) {
                for (Proposal p : proposalsInContestType) {
                    contestTypeProposalWrappersByContestTypeId.get(contestType.getId())
                            .getProposals().add(p);
                }
            }
        }
    }

    public boolean isStaffMemberProfile() {
        return PermissionsClient.canStaff(member.getId());
    }

    public boolean isInitialized() {
        return this.member != null;
    }

    public boolean isActive() {
        return member != null && member.isActive();
    }

    public boolean isViewingOwnProfile() {
        return viewingOwnProfile;
    }

    public Member getUser() {
        return member;
    }

    public void setUser(Member user) {
        this.member = user;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Date getJoinDate() {
        return member.getCreatedAt();
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
        return 1;
    }

    public int getSubscribedActivitiesCount() {
        if (subscribedActivities != null) {
            return subscribedActivities.size();
        }
        return 0;
    }

    public boolean getHasFacebookId() {
        return member.getFacebookId() != null && member.getFacebookId() != 0;
    }

    public boolean getHasGoogleId() {
        return member.getGoogleId() != null && !member.getGoogleId().isEmpty();
    }

    public boolean getHasOpenId() {
        return (member.getOpenId() != null && !member.getOpenId().isEmpty());
    }

    public boolean isFireGoogleEvent() {
        return FIRE_GOOGLE_EVENT;
    }

    public MemberCategory getHighestRoleCategory() {
        return highestRoleCategory;
    }

    public List<Role> getRoles() {
        return member.getRoles();
    }

    public boolean hasRole(long roleId) {
        return PermissionsClient.memberHasRole(member.getId(), roleId);
    }

    public boolean isDisplayEMailErrorMessage() {
        return DISPLAY_EMAIL_ERROR_MESSAGE;
    }


    public List<MessageBean> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
            for (Message msg : MessagingClient.getMessages(this.member.getId(), 0, 2, MessageType.INBOX)) {
                messages.add(new MessageBean(msg));
            }
        }
        return messages;
    }

    public List<UserActivityWrapper> getSubscribedActivities() {
        if (subscribedActivities == null) {
            subscribedActivities = new ArrayList<>();
            for (ActivityEntry activity : ActivityUtil.groupActivities(
                    ActivitiesClientUtil.getActivityEntries(0, 100, this.member.getId(), null))) {

                subscribedActivities.add(new UserActivityWrapper(activity, activityEntryHelper));
            }
        }
        return subscribedActivities;
    }

    public List<SupportedProposal> getSupportedProposals() {
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
            return ActivitiesClientUtil.countActivities(getUserId(),null);
    }

    public Long getUserId() {
        return member.getId();
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

            linkingProposals.addAll(proposals);
        }
        return linkingProposals;
    }

    private String getProposalWithPlurality(String plurality) {
        if (proposalsString == null) {
            proposalsString = ContestTypeClient.getProposalNames(
                            new ArrayList<>(contestTypeProposalWrappersByContestTypeId.keySet()),
                                            plurality,"or");
        }
        return proposalsString;
    }

    public String getProposalsString() {
        return this.getProposalWithPlurality(Plurality.PLURAL.name());
    }

    public String getProposalString() {
        return this.getProposalWithPlurality(Plurality.SINGULAR.name());
    }
}
