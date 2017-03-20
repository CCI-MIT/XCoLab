package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.servlet.http.HttpServletRequest;

public class ProposalsContextWrapper {

    private final HttpServletRequest request;
    private final ProposalsContext proposalsContext = new ProposalsContextImpl();

    public ProposalsContextWrapper(HttpServletRequest request) {
        this.request = request;
    }

    public Contest getContest() {
        return proposalsContext.getContest(request);
    }

    public ContestPhase getContestPhase() {
        return proposalsContext.getContestPhase(request);
    }

    public Proposal getProposal() {
        return proposalsContext.getProposal(request);
    }

    public ProposalsPermissions getPermissions() {
        return proposalsContext.getPermissions(request);
    }

    public ProposalsDisplayPermissions getDisplayPermissions() {
        return proposalsContext.getDisplayPermissions(request);
    }

    public Proposal2Phase getProposal2Phase() {
        return proposalsContext.getProposal2Phase(request);
    }

    public Long getViewContestPhaseId() {
        return proposalsContext.getViewContestPhaseId(request);
    }

    public Proposal getProposalWrapped() {
        return proposalsContext.getProposalWrapped(request);
    }

    public Contest getContestWrapped() {
        return proposalsContext.getContestWrapped(request);
    }

    public ContestPhase getContestPhaseWrapped() {
        return proposalsContext.getContestPhaseWrapped(request);
    }

    public ContestType getContestType() {
        return proposalsContext.getContestType(request);
    }

    public Member getMember() {
        return proposalsContext.getMember(request);
    }

    public long getMemberId() {
        return proposalsContext.getMemberId(request);
    }

    public void invalidateContext() {
        proposalsContext.invalidateContext(request);
    }

    public ProposalsPreferencesWrapper getProposalsPreferences() {
        return proposalsContext.getProposalsPreferences(request);
    }

    public ClientHelper getClients() {
        return proposalsContext.getClients(request);
    }
}
