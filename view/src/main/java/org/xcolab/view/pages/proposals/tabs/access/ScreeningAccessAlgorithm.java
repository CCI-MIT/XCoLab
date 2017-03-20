package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

public class ScreeningAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalsContextWrapper contextWrapper) {
        final ProposalsPermissions permissions = contextWrapper.getPermissions();
        final ContestPhase contestPhase = contextWrapper.getContestPhase();
        final Contest contest = contextWrapper.getContest();

        final boolean isForeignContest = contest.getIsSharedContestInForeignColab();
        final boolean hasCorrectRole = permissions.getCanFellowActions()
                || permissions.getCanContestManagerActions();
        if (!hasCorrectRole || isForeignContest) {
            return false;
        }

        ContestPhasePromoteType phasePromoteType = ContestPhasePromoteType
                .getPromoteType(contestPhase.getContestPhaseAutopromote());
        final boolean isJudged = phasePromoteType == ContestPhasePromoteType.PROMOTE_JUDGED;
        return isJudged && contestPhase.getFellowScreeningActive();
    }
}
