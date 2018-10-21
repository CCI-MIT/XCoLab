package org.xcolab.view.pages.proposals.permissions;


import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.tabs.access.AdaptationImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.tabs.access.ImpactAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;

import java.util.Date;

public class ProposalsPermissions {

    private final boolean planIsEditable;

    private final long userId;
    private final Member member;
    private final boolean isLoggedIn;
    private final boolean isGuest;

    private final Proposal proposal;
    private final Contest contest;
    private final ContestPhase contestPhase;
    private final ContestStatus contestStatus;

    private final ProposalClient proposalClient;
    private final ContestClient contestClient;


    public ProposalsPermissions(ClientHelper clientHelper, Member member, Proposal proposal,
            Contest contest, ContestPhase contestPhase) {
        this.member = member;

        this.proposalClient = clientHelper.getProposalClient();
        this.contestClient = clientHelper.getContestClient();

        if (contestPhase != null) {
            final long contestPhaseTypeId = contestPhase.getContestPhaseTypeId();

            final ContestPhaseType contestPhaseType = clientHelper.getContestClient()
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
        this.isGuest = PermissionsClient.isGuest(userId);
        this.proposal = proposal;
        this.contest = contest;
        this.contestPhase = contestPhase;
    }

    public Member getMember() {
        return member;
    }

    public boolean getIsPlanEditable() {
        return planIsEditable;
    }

    public boolean getCanReport() {
        return (ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get() && isLoggedIn && !isGuest)
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
        return isLoggedIn && !isGuest
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
        return isLoggedIn && !isGuest && getIsCreationAllowedByPhase()
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
        return isLoggedIn && !isGuest; // && !getCanJudgeActions() && !getIsTeamMember();
    }

    public boolean getCanManageUsers() {
        return getCanAdminProposal();
    }

    public boolean getCanPromoteToOwner() {
        return getCanManageUsers() && (getCanAdminAll()
                || ConfigurationAttributeKey.PROPOSALS_USER_CAN_PROMOTE_TO_OWNER.get());
    }

    public boolean getCanSupportProposal() {
        return isLoggedIn && !isGuest && !isVotingEnabled();
    }

    public boolean getCanSubscribeContest() {
        return isLoggedIn;
    }

    public boolean getCanSubscribeProposal() {
        return isLoggedIn;
    }

    public boolean isVotingEnabled() {
        return contestPhase != null && contestPhase.getPhaseActive()
                && contestStatus.isCanVote();
    }

    public boolean getCanVote() {
        return isLoggedIn && !isGuest && isVotingEnabled()
                && (proposal != null && proposal.getId() > 0);
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
        return PermissionsClient.canAdminAll(userId);
    }

    private boolean isProposalMember() {
        return proposal != null && proposal.getId() > 0 &&
                proposalClient.isUserInProposalTeam(proposal.getId(),userId);
    }

    public boolean getCanFellowActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }

        return PermissionsClient.canFellow(userId, contestPhase.getContestId()) || getCanAdminAll();
    }

    public boolean getCanJudgeActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }
        return PermissionsClient.canJudge(userId, contestPhase.getContestId())
                || getCanAdminAll();
    }

    public boolean getCanContestManagerActions() {
        if (!isLoggedIn) {
            return false;
        }
        return PermissionsClient.canContestManager(member) || PermissionsClient.canAdminAll(member);
    }

    public boolean getCanIAFActions() {
        if (!isLoggedIn) {
            return false;
        }
        return PermissionsClient.canIAF(member);
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

    public boolean getCanPromoteProposalToNextPhase(ContestPhase contestPhase) {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        boolean onlyPromoteIfCurrentContestPhaseIsNotJudged = contestPhase.getFellowScreeningActive();
        if (onlyPromoteIfCurrentContestPhaseIsNotJudged) {
            return false;
        }

        try {
            Contest latestProposalContest = proposalClient.getCurrentContestForProposal(proposal.getId());
            ContestPhase activePhaseForContest = contestClient.getActivePhase(latestProposalContest.getId());
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
