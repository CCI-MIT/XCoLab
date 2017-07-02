package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.servlet.http.HttpServletRequest;

public interface ProposalsContext {

    Contest getContest(HttpServletRequest request);

    ContestPhase getContestPhase(HttpServletRequest request);

    Proposal getProposal(HttpServletRequest request);

    ProposalsPermissions getPermissions(HttpServletRequest request);

    ProposalsDisplayPermissions getDisplayPermissions(HttpServletRequest request);

    Proposal2Phase getProposal2Phase(HttpServletRequest request);

    Long getViewContestPhaseId(HttpServletRequest request);

    Proposal getProposalWrapped(HttpServletRequest request);

    Contest getContestWrapped(HttpServletRequest request);

    ContestPhase getContestPhaseWrapped(HttpServletRequest request);

    ContestType getContestType(HttpServletRequest request);

    Member getMember(HttpServletRequest request);

    long getMemberId(HttpServletRequest request);

    void invalidateContext(HttpServletRequest request);

    ProposalsPreferencesWrapper getProposalsPreferences(HttpServletRequest request);

    ClientHelper getClients(HttpServletRequest request);

}
