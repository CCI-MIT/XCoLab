package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

public class ScreeningAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    @Override
    public boolean canAccess(ProposalContext proposalContext) {
        final ProposalsPermissions permissions = proposalContext.getPermissions();
        final ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
        final ContestWrapper contest = proposalContext.getContest();

        final boolean hasCorrectRole = permissions.getCanFellowActions()
                || permissions.getCanContestManagerActions();
        if (!hasCorrectRole) {
            return false;
        }

        return  contestPhase.getFellowScreeningActive();
    }
}
