package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

public class AdvancingAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        ProposalsPermissions permissions = proposalContext.getPermissions();
        boolean hasCorrectRole = permissions.getCanFellowActions()
                || permissions.getCanContestManagerActions();

        final Contest contest = proposalContext.getContest();
        final boolean isForeignContest = contest.getIsSharedContestInForeignColab();
        if (!hasCorrectRole || isForeignContest) {
            return false;
        }

        ContestPhase contestPhase = proposalContext.getContestPhase();
        ContestPhasePromoteType phasePromoteType = contestPhase.getContestPhaseAutopromoteEnum();

        final boolean isJudged = phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED;
        final boolean isDirectJudging = isJudged && !contestPhase.getFellowScreeningActive();
        if (isDirectJudging) {
            return true;
        }

        Proposal proposalWrapper = new Proposal(proposalContext.getProposal(), proposalContext.getContestPhase());
        ProposalJudgeWrapper wrapper = new ProposalJudgeWrapper(proposalWrapper, permissions.getMember());
        return wrapper.isPassedToJudges();
    }
}
