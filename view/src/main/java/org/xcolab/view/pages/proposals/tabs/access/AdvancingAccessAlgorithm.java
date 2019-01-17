package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.Proposal;
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

        if (!hasCorrectRole) {
            return false;
        }

        ContestPhase contestPhase = proposalContext.getContestPhase();

        final boolean isDirectJudging = contestPhase.getIsJudged()
                && !contestPhase.getFellowScreeningActive();
        if (isDirectJudging) {
            return true;
        }

        Proposal proposal = new Proposal(proposalContext.getProposal(), contestPhase);
        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposal,
                permissions.getMember());
        return proposalJudgeWrapper.isPassedToJudges();
    }
}
