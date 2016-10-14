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

public interface ProposalsContext {

    Contest getContest(PortletRequest request);

    ContestPhase getContestPhase(PortletRequest request);

    Proposal getProposal(PortletRequest request);

    ProposalsPermissions getPermissions(PortletRequest request);

    ProposalsDisplayPermissions getDisplayPermissions(PortletRequest request);

    Proposal2Phase getProposal2Phase(PortletRequest request);

    Long getViewContestPhaseId(PortletRequest request);

    ProposalWrapper getProposalWrapped(PortletRequest request);

    ContestWrapper getContestWrapped(PortletRequest request);

    BaseContestPhaseWrapper getContestPhaseWrapped(PortletRequest request);

    ContestType getContestType(PortletRequest request);

    User getUser(PortletRequest request);

    Member getMember(PortletRequest request);

    long getMemberId(PortletRequest request);

    void invalidateContext(PortletRequest request);

    ProposalsPreferencesWrapper getProposalsPreferences(PortletRequest request);

}