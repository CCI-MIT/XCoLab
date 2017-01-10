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

public final class ProposalsContextUtil {

    private static final ProposalsContext proposalsContext = new ProposalsContextImpl();

    private ProposalsContextUtil() {
    }

    public static Contest getContest(HttpServletRequest request) {
        return proposalsContext.getContest(request);
    }

    public static ContestPhase getContestPhase(
            HttpServletRequest request) {
        return proposalsContext.getContestPhase(request);
    }

    public static Proposal getProposal(HttpServletRequest request) {
        return proposalsContext.getProposal(request);
    }

    public static ProposalsPermissions getPermissions(
            HttpServletRequest request) {
        return proposalsContext.getPermissions(request);
    }

    public static ProposalsDisplayPermissions getDisplayPermissions(
            HttpServletRequest request) {
        return proposalsContext.getDisplayPermissions(request);
    }

    public static Proposal2Phase getProposal2Phase(
            HttpServletRequest request) {
        return proposalsContext.getProposal2Phase(request);
    }

    public static Long getViewContestPhaseId(HttpServletRequest request) {
        return proposalsContext.getViewContestPhaseId(request);
    }

    public static Proposal getProposalWrapped(
            HttpServletRequest request) {
        return proposalsContext.getProposalWrapped(request);
    }

    public static Contest getContestWrapped(
            HttpServletRequest request) {
        return proposalsContext.getContestWrapped(request);
    }

    public static ContestPhase getContestPhaseWrapped(
            HttpServletRequest request) {
        return proposalsContext.getContestPhaseWrapped(request);
    }

    public static ContestType getContestType(
            HttpServletRequest request) {
        return proposalsContext.getContestType(request);
    }

    public static Member getMember(HttpServletRequest request) {
        return proposalsContext.getMember(request);
    }

    public static long getMemberId(HttpServletRequest request) {
        return proposalsContext.getMemberId(request);
    }

    public static void invalidateContext(HttpServletRequest request) {
        proposalsContext.invalidateContext(request);
    }

    public static ProposalsPreferencesWrapper getProposalsPreferences(
            HttpServletRequest request) {
        return proposalsContext.getProposalsPreferences(request);
    }

    public static ClientHelper getClients(HttpServletRequest request) {
        return proposalsContext.getClients(request);
    }
}
