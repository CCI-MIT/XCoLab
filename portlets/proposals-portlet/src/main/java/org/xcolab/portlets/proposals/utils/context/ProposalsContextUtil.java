package org.xcolab.portlets.proposals.utils.context;

import com.liferay.portal.model.User;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.portlets.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.wrappers.BaseContestPhaseWrapper;

import javax.portlet.PortletRequest;

public final class ProposalsContextUtil {

    private static final ProposalsContext proposalsContext = new ProposalsContextImpl();

    private ProposalsContextUtil() {
    }

    public static Contest getContest(PortletRequest request) {
        return proposalsContext.getContest(request);
    }

    public static ContestPhase getContestPhase(
            PortletRequest request) {
        return proposalsContext.getContestPhase(request);
    }

    public static Proposal getProposal(PortletRequest request) {
        return proposalsContext.getProposal(request);
    }

    public static ProposalsPermissions getPermissions(
            PortletRequest request) {
        return proposalsContext.getPermissions(request);
    }

    public static ProposalsDisplayPermissions getDisplayPermissions(
            PortletRequest request) {
        return proposalsContext.getDisplayPermissions(request);
    }

    public static Proposal2Phase getProposal2Phase(
            PortletRequest request) {
        return proposalsContext.getProposal2Phase(request);
    }

    public static Long getViewContestPhaseId(PortletRequest request) {
        return proposalsContext.getViewContestPhaseId(request);
    }

    public static ProposalWrapper getProposalWrapped(
            PortletRequest request) {
        return proposalsContext.getProposalWrapped(request);
    }

    public static ContestWrapper getContestWrapped(
            PortletRequest request) {
        return proposalsContext.getContestWrapped(request);
    }

    public static BaseContestPhaseWrapper getContestPhaseWrapped(
            PortletRequest request) {
        return proposalsContext.getContestPhaseWrapped(request);
    }

    public static ContestType getContestType(
            PortletRequest request) {
        return proposalsContext.getContestType(request);
    }

    public static User getUser(PortletRequest request) {
        return proposalsContext.getUser(request);
    }

    public static Member getMember(PortletRequest request) {
        return proposalsContext.getMember(request);
    }

    public static long getMemberId(PortletRequest request) {
        return proposalsContext.getMemberId(request);
    }

    public static void invalidateContext(PortletRequest request) {
        proposalsContext.invalidateContext(request);
    }

    public static ProposalsPreferencesWrapper getProposalsPreferences(
            PortletRequest request) {
        return proposalsContext.getProposalsPreferences(request);
    }

    public static ClientHelper getClients(PortletRequest request) {
        return proposalsContext.getClients(request);
    }
}
