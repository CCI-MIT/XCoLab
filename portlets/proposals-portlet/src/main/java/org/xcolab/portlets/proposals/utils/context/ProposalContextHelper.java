package org.xcolab.portlets.proposals.utils.context;

import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.kernel.util.ParamUtil;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import javax.portlet.PortletRequest;

public class ProposalContextHelper {

    private static final String PROPOSAL_ID_PARAM = "proposalId";
    private static final String PLAN_ID_PARAM = "planId";
    private static final String CONTEST_ID_PARAM = "contestId";
    private static final String CONTEST_URL_NAME_PARAM = "contestUrlName";
    private static final String CONTEST_YEAR_PARAM = "contestYear";
    private static final String CONTEST_PHASE_ID_PARAM = "phaseId";
    private static final String VERSION_PARAM = "version";

    private final String givenContestUrlName;
    private final long givenContestYear;
    private final long givenContestId;
    private final long givenPhaseId;
    private final int givenVersion;
    private final long givenProposalId;
    private final PortletRequest request;

    public ProposalContextHelper(PortletRequest request) {
        this.request = request;
        final long proposalIdParam = ParamUtil.getLong(request, PROPOSAL_ID_PARAM);
        givenProposalId = (proposalIdParam == 0)
                ? ParamUtil.getLong(request, PLAN_ID_PARAM) : proposalIdParam;
        givenContestUrlName = ParamUtil.getString(request, CONTEST_URL_NAME_PARAM);
        givenContestYear = ParamUtil.getLong(request, CONTEST_YEAR_PARAM);
        givenContestId = ParamUtil.getLong(request, CONTEST_ID_PARAM);
        givenPhaseId = ParamUtil.getLong(request, CONTEST_PHASE_ID_PARAM);
        givenVersion = ParamUtil.getInteger(request, VERSION_PARAM);
    }

    public Member getMember() {
        Member member = null;
        String memberIdString = request.getRemoteUser();
        if (StringUtils.isNotBlank(memberIdString)) {
            member = MembersClient.getMemberUnchecked(Long.parseLong(memberIdString));
        }
        return member;
    }

    public Contest getContest() throws InvalidAccessException {
        Contest contest = null;
        if (StringUtils.isNotBlank(givenContestUrlName) && givenContestYear > 0) {
            contest = ContestClientUtil
                    .getContestByContestUrlNameContestYear(givenContestUrlName, givenContestYear);
            if (contest == null) {
                throw new InvalidAccessException();
            }
        } else if (givenContestId > 0) {
            try {
                contest = ContestClientUtil.getContest(givenContestId);
            } catch (ContestNotFoundException e) {
                throw new InvalidAccessException();
            }
        }
        return contest;
    }

    public ContestPhase getContestPhase(Contest contest) {
        ContestPhase contestPhase;
        if (givenPhaseId > 0) {
            contestPhase = ContestClientUtil.getContestPhase(givenPhaseId);
            if (contestPhase == null) {
                contestPhase = ContestClientUtil.getActivePhase(contest.getContestPK());
            }

        } else {
            contestPhase = ContestClientUtil.getActivePhase(contest.getContestPK());
        }
        if (contestPhase == null) {
            throw ReferenceResolutionException
                    .toObject(ContestPhase.class, "")
                    .fromObject(Contest.class, contest.getContestPK());
        }
        return contestPhase;
    }

    public Proposal2Phase getProposal2Phase(ContestPhase contestPhase) {
        try {
            return ProposalPhaseClientUtil
                    .getProposal2PhaseByProposalIdContestPhaseId(givenProposalId,
                            contestPhase.getContestPhasePK());
        } catch (Proposal2PhaseNotFoundException e) {
            return null;
        }
    }

    public Proposal getProposal() throws InvalidAccessException {
        Proposal proposal = null;
        if (givenProposalId > 0) {
            try {
                proposal = ProposalClientUtil.getProposal(givenProposalId);
            } catch (ProposalNotFoundException e) {
                throw new InvalidAccessException();
            }
        }
        return proposal;
    }

    public ProposalWrapper getProposalWrapper(Proposal proposal, Proposal2Phase proposal2Phase,
            ContestPhase contestPhase, Contest contest, Member member) {
        ProposalWrapper proposalWrapper;
        if (givenVersion > 0) {
            if (member != null && PermissionsClient.canJudge(member.getUserId(), contest.getContestPK())) {
                proposalWrapper = new ProposalJudgeWrapper(proposal, givenVersion,
                        contest, contestPhase, proposal2Phase, member);
            } else {
                proposalWrapper = new ProposalWrapper(proposal, givenVersion,
                        contest,
                        contestPhase, proposal2Phase);
            }
        } else {
            final boolean hasVersionTo =
                    proposal2Phase != null && proposal2Phase.getVersionTo() > 0;
            final int localVersion =
                    hasVersionTo ? proposal2Phase.getVersionTo()
                            : proposal.getCurrentVersion();

            if (member != null && PermissionsClient.canJudge(member.getUserId(), contest.getContestPK())) {
                proposalWrapper = new ProposalJudgeWrapper(proposal,
                        localVersion,
                        contest, contestPhase, proposal2Phase, member);
            } else {
                proposalWrapper = new ProposalWrapper(proposal, localVersion,
                        contest, contestPhase, proposal2Phase);
            }
        }
        return proposalWrapper;
    }

    public long getGivenPhaseId() {
        return givenPhaseId;
    }

    public String getGivenContestUrlName() {
        return givenContestUrlName;
    }

    public long getGivenContestYear() {
        return givenContestYear;
    }

    public long getGivenContestId() {
        return givenContestId;
    }

    public int getGivenVersion() {
        return givenVersion;
    }

    public long getGivenProposalId() {
        return givenProposalId;
    }

    public static class InvalidAccessException extends Exception {

    }
}
