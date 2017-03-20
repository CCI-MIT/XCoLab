package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

public class AdvancingAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalsContextWrapper contextWrapper) {
        ProposalsPermissions permissions = contextWrapper.getPermissions();
        boolean hasCorrectRole = permissions.getCanFellowActions()
                || permissions.getCanContestManagerActions();

        final Contest contest = contextWrapper.getContest();
        final boolean isForeignContest = contest.getIsSharedContestInForeignColab();
        if (!hasCorrectRole || isForeignContest) {
            return false;
        }

        ContestPhase contestPhase = contextWrapper.getContestPhase();
        ContestPhasePromoteType phasePromoteType = ContestPhasePromoteType
                .getPromoteType(contestPhase.getContestPhaseAutopromote());

        final boolean isJudged = phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED;
        final boolean isDirectJudging = isJudged && !contestPhase.getFellowScreeningActive();
        if (isDirectJudging) {
            return true;
        }

        Proposal proposalWrapper = new Proposal(contextWrapper.getProposal(), contextWrapper.getContestPhase());
        ProposalJudgeWrapper wrapper = new ProposalJudgeWrapper(proposalWrapper, contextWrapper.getMember());
        return wrapper.isPassedToJudges();
    }
}
