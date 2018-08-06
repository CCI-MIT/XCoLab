package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public class ScreeningAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        final ProposalsPermissions permissions = proposalContext.getPermissions();
        final ContestPhase contestPhase = proposalContext.getContestPhase();
        final Contest contest = proposalContext.getContest();

        final boolean hasCorrectRole = permissions.getCanFellowActions()
                || permissions.getCanContestManagerActions();
        if (!hasCorrectRole) {
            return false;
        }

        return  contestPhase.getFellowScreeningActive();
    }
}
