package org.xcolab.view.pages.proposals.permissions;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.view.pages.proposals.tabs.access.AdaptationImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.ImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;

import java.util.Date;

public class ProposalsPermissions {

    private final boolean planIsEditable;

    private final long userId;
    private final UserWrapper member;
    private final boolean isLoggedIn;
    private final boolean isGuest;

    private final ProposalWrapper proposal;
    private final ContestWrapper contest;
    private final ContestPhaseWrapper contestPhase;
    private final ContestStatus contestStatus;

    private final ContestPermissions contestPermissions;

    private final IProposalClient proposalClient;
    private final IContestClient contestClient;


    public ProposalsPermissions(ClientHelper clientHelper, UserWrapper member, ProposalWrapper proposal,
            ContestWrapper contest, ContestPhaseWrapper contestPhase) {
        this.member = member;

        this.proposalClient = clientHelper.getProposalClient();
        this.contestClient = clientHelper.getContestClient();

        if (contestPhase != null) {
            final long contestPhaseTypeId = contestPhase.getContestPhaseTypeId();

            final IContestPhaseType contestPhaseType = clientHelper.getContestClient()
                    .getContestPhaseType(contestPhaseTypeId);
            String statusStr = contestPhaseType.getStatus();
            contestStatus = ContestStatus.valueOf(statusStr);

        } else {
            contestStatus = null;
        }

        // set proper context group id
        if (proposal == null) {
            planIsEditable = false;
        } else {
            planIsEditable = contestStatus != null && contestStatus.isCanEdit()
                    && contestPhase.getPhaseActive();

        }
        this.userId = member != null ? member.getId() : 0;
        this.isLoggedIn = this.userId > 0;
        this.isGuest = StaticUserContext.getPermissionClient().isGuest(userId);
        this.proposal = proposal;
        this.contest = contest;
        this.contestPhase = contestPhase;
        this.contestPermissions = new ContestPermissions(member);
    }

    public UserWrapper getMember() {
        return member;
    }

    public boolean getIsPlanEditable() {
        return planIsEditable;
    }

    public boolean getCanView() {
        return contestPermissions.getCanAccessContest(contest);
    }

    public boolean getCanReport() {
        return (ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get() && isLoggedIn && !isGuest
                && getCanView())
                    || getCanAdminAll();
    }

    public boolean getCanReportProposal() {
        return getCanReport() && !isProposalMember();
    }

    /**
     * <p>Returns true if user is allowed to edit a proposal.</p>
     *
     * @return true if user is allowed to edit a proposal, false otherwise
     */
    public boolean getCanEdit() {
        return isLoggedIn && !isGuest && getCanView()
                && (getCanAdminAll() || planIsEditable
                && (isProposalOpen() || isProposalMember())
        );
    }

    public boolean getCanEditContest() {
        return isLoggedIn && !isGuest && getCanAdminAll();
    }

    public boolean getCanDownload() {
        return isLoggedIn && !isGuest && getCanAdminAll();
    }

    public boolean getCanDelete() {
        return isLoggedIn && !isGuest
                && (getCanAdminAll() || planIsEditable && isProposalMember());
    }

    public boolean getCanCreate() {
        return (isLoggedIn && !isGuest && getIsCreationAllowedByPhase()
                && getCanView())
                || getCanAdminAll();
    }

    public boolean getIsCreationAllowedByPhase() {
        return (contestPhase.getPhaseEndDate() == null
                || contestPhase.getPhaseEndDate().after(new Date())
        ) && contestPhase.getPhaseStartDate() != null
                && contestPhase.getPhaseStartDate().before(new Date())
                && contestStatus.isCanCreate();
    }

    public boolean getCanAssignRibbon() {
        return isLoggedIn && !isGuest && getCanAdminAll();
    }

    public boolean getCanPublicRating() {
        return isLoggedIn && !isGuest && getCanView(); // && !getCanJudgeActions() && !getIsTeamMember();
    }

    public boolean getCanManageUsers() {
        return getCanAdminProposal();
    }

    public boolean getCanPromoteToOwner() {
        return getCanManageUsers() && (getCanAdminAll()
                || ConfigurationAttributeKey.PROPOSALS_USER_CAN_PROMOTE_TO_OWNER.get());
    }

    public boolean getCanSupportProposal() {
        return isLoggedIn && !isGuest && getCanView()
                && !isVotingEnabled();
    }

    public boolean getCanSubscribeContest() {
        return isLoggedIn && getCanView();
    }

    public boolean getCanSubscribeProposal() {
        return isLoggedIn && getCanView();
    }

    public boolean isVotingEnabled() {
        return contestPhase != null && contestPhase.getPhaseActive()
                && contestStatus.isCanVote();
    }

    public boolean getCanVote() {
        return isLoggedIn && !isGuest && getCanView()
                && isVotingEnabled() && (proposal != null && proposal.getId() > 0);
    }

    public boolean getCanToggleOpen() {
        return getCanAdminProposal() && ConfigurationAttributeKey.CONTESTS_ALLOW_OPEN_PROPOSALS.get();
    }

    public boolean getCanAdminProposal() {
        return getCanAdminAll() || isOwner();
    }


    public boolean getIsTeamMember() {
        return proposal != null && proposal.getId() > 0
                && proposalClient.isUserInProposalTeam(proposal.getId(),userId)
                && isLoggedIn;
    }

    private boolean isOwner() {
        return isLoggedIn && (proposal == null || userId == proposal.getAuthorUserId());
    }

    private boolean isProposalOpen() {
        return proposal != null && proposal.getId() > 0
                && proposal.isOpen();
    }

    /**
     * Returns true if user is admin (not only proposal contributor)
     */
    public boolean getCanAdminAll() {
        return StaticUserContext.getPermissionClient().canAdminAll(userId);
    }

    private boolean isProposalMember() {
        return proposal != null && proposal.getId() > 0 &&
                proposalClient.isUserInProposalTeam(proposal.getId(),userId);
    }

    public boolean getCanFellowActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }

        return StaticUserContext.getPermissionClient().canFellow(userId, contestPhase.getContestId()) || getCanAdminAll();
    }

    public boolean getCanJudgeActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }
        return StaticUserContext.getPermissionClient().canJudge(userId, contestPhase.getContestId())
                || getCanAdminAll();
    }

    public boolean getCanContestManagerActions() {
        if (!isLoggedIn) {
            return false;
        }
        return StaticUserContext.getPermissionClient().canContestManager(member) ||
                StaticUserContext.getPermissionClient().canAdminAll(member);
    }

    public boolean getCanIAFActions() {
        if (!isLoggedIn) {
            return false;
        }
        return StaticUserContext.getPermissionClient().canIAF(member);
    }

    public boolean getCanViewMitigationImpactTab() {
        return ImpactAccessAlgorithm.view().canAccess(this, contest);
    }

    public boolean getCanViewAdaptationImpactTab() {
        return AdaptationImpactAccessAlgorithm.view().canAccess(contest);
    }

    public boolean getCanEditBasicImpact() {
        return getCanEdit() || getCanIAFActions();
    }

    public boolean getCanPromoteProposalToNextPhase() {
        return contestPhase != null && getCanPromoteProposalToNextPhase(contestPhase);
    }

    public boolean getCanPromoteProposalToNextPhase(ContestPhaseWrapper contestPhase) {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        boolean onlyPromoteIfCurrentContestPhaseIsNotJudged = contestPhase.getFellowScreeningActive();
        if (onlyPromoteIfCurrentContestPhaseIsNotJudged) {
            return false;
        }

        try {
            ContestWrapper latestProposalContest = proposalClient.getCurrentContestForProposal(proposal.getId());
            ContestPhaseWrapper activePhaseForContest = contestClient.getActivePhase(latestProposalContest.getId());
            boolean onlyPromoteIfThisIsNotTheLatestContestPhaseInContest = contestPhase.equals(activePhaseForContest);
            return !onlyPromoteIfThisIsNotTheLatestContestPhaseInContest && getCanAdminAll();
        }catch (ContestNotFoundException ignored){

        }
        return false;
    }

    public boolean getCanMoveProposal() {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        if (getIsCreationAllowedByPhase()) {
            return getCanAdminProposal();
        }

        return getCanAdminAll();
    }

    public boolean getCanCopyProposal() {
        return !wasProposalMovedElsewhere()
                && !getIsCreationAllowedByPhase() && getCanAdminProposal();
    }

    public boolean getCanForkProposal() {
        return !wasProposalMovedElsewhere()
                && getCanAdminProposal();
    }

    private boolean wasProposalMovedElsewhere() {

        try {
            final long currentContestId = proposalClient
                    .getCurrentContestForProposal(proposal.getId()).getId();
            return currentContestId != contestPhase.getContestId();
        }catch(ContestNotFoundException ignored){
            return false;
        }

    }

}
