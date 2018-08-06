package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
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
        if (!hasCorrectRole) {
            return false;
        }

        ContestPhase contestPhase = proposalContext.getContestPhase();

        final boolean isDirectJudging = contestPhase.getIsJudged()
                && !contestPhase.getFellowScreeningActive();
        if (isDirectJudging) {
            return true;
        }

        Proposal proposalWrapper = new Proposal(proposalContext.getProposal(), proposalContext.getContestPhase());
        ProposalJudgeWrapper wrapper = new ProposalJudgeWrapper(proposalWrapper, permissions.getMember());
        return wrapper.isPassedToJudges();
    }
}
