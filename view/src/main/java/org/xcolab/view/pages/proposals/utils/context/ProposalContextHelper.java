package org.xcolab.view.pages.proposals.utils.context;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.util.entity.portlet.RequestParamUtil;

import javax.servlet.http.HttpServletRequest;

public class ProposalContextHelper {

    private static final Logger log = LoggerFactory.getLogger(ProposalContextHelper.class);

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
    private final HttpServletRequest request;
    private final Contest contest;
    private final ClientHelper clientHelper;

    public ProposalContextHelper(HttpServletRequest request) {
        this.request = request;
        final long proposalIdParam =
                RequestParamUtil.getLong(request, PROPOSAL_ID_PARAM);
        givenProposalId = (proposalIdParam == 0)
                ? RequestParamUtil.getLong(request, PLAN_ID_PARAM)
                : proposalIdParam;
        givenContestUrlName =
                RequestParamUtil.getString(request, CONTEST_URL_NAME_PARAM);
        givenContestYear = RequestParamUtil.getLong(request, CONTEST_YEAR_PARAM);
        givenContestId = RequestParamUtil.getLong(request, CONTEST_ID_PARAM);
        givenPhaseId = RequestParamUtil.getLong(request, CONTEST_PHASE_ID_PARAM);
        givenVersion = RequestParamUtil.getInteger(request, VERSION_PARAM);

        Contest localContest = fetchContest();
        log.trace("Fetched local contest: {}", localContest);
        clientHelper = new ClientHelper(localContest);
        if (localContest != null) {
            contest = setupContestFromTheRightClient(localContest.getContestPK());
        } else {
            log.trace("Local contest is null: contestUrlName={}, contestYear={}, contestId={}",
                    givenContestUrlName, givenContestYear, givenContestId);
            contest = null;
        }
    }

    private Contest setupContestFromTheRightClient(long contestId) {

        final ContestClient contestClient = clientHelper.getContestClient();
        log.debug("Setting up contest {} from client {}", contestId, contestClient);

        try {
            return contestClient.getContest(contestId);
        } catch (ContestNotFoundException ignored) {
            log.warn("Mirroring problem: Contest {} not found in client {}",
                    contestId, contestClient);
            return null;
        }
    }

    private Contest fetchContest() {
        Contest localContest = null;
        try {
            if (StringUtils.isNotBlank(givenContestUrlName) && givenContestYear > 0) {
                localContest = ContestClientUtil
                        .getContest(givenContestUrlName, givenContestYear);
            } else if (givenContestId > 0) {
                localContest = ContestClientUtil.getContest(givenContestId);
            }
        } catch (ContestNotFoundException e) {
            log.debug("Contest not found: {}", e.getMessage());
        }
        return localContest;
    }

    public Member getMember() {
        return MemberAuthUtil.getMemberOrNull(request);
    }

    public Contest getContest() throws InvalidAccessException {
        if (contest == null) {
            final boolean contestUserSupplied = StringUtils.isNotBlank(givenContestUrlName)
                    || givenContestId > 0;
            if (contestUserSupplied) {
                log.debug("Invalid contest supplied: givenContestUrlName = {}, givenContestId = {}",
                        givenContestUrlName, givenContestId);
                throw new InvalidAccessException();
            }
        }
        return contest;
    }

    public ClientHelper getClientHelper() {
        return clientHelper;
    }

    public ContestPhase getContestPhase(Contest contest, Proposal proposal) {
        final ContestClient contestClient = clientHelper.getContestClient();
        final ProposalClient proposalClient = clientHelper.getProposalClient();

        ContestPhase contestPhase;
        if (givenPhaseId > 0) {
            contestPhase = contestClient.getContestPhase(givenPhaseId);
        } else if (proposal != null) {
            contestPhase = proposalClient.getLatestContestPhaseInProposal(proposal.getProposalId());
        } else {
            contestPhase = contestClient.getActivePhase(contest.getContestPK());
        }

        if (contestPhase == null) {
            throw ReferenceResolutionException
                    .toObject(ContestPhase.class, "")
                    .fromObject(Contest.class, contest.getContestPK());
        }
        return contestPhase;
    }

    public Proposal2Phase getProposal2Phase(ContestPhase contestPhase) {
        final ProposalPhaseClient proposalPhaseClient = clientHelper.getProposalPhaseClient();
        try {
            return proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(givenProposalId,
                            contestPhase.getContestPhasePK());
        } catch (Proposal2PhaseNotFoundException e) {
            return null;
        }
    }

    public Proposal getProposal() throws InvalidAccessException {
        final ProposalClient proposalClient = clientHelper.getProposalClient();
        Proposal proposal = null;
        if (givenProposalId > 0) {
            try {
                proposal = proposalClient.getProposal(givenProposalId);
            } catch (ProposalNotFoundException e) {
                log.debug("Invalid proposal supplied: givenProposalId = {}", givenProposalId);
                throw new InvalidAccessException();
            }
        }
        return proposal;
    }

    public Proposal getProposalWrapper(Proposal proposal, Proposal2Phase proposal2Phase,
            ContestPhase contestPhase, Contest contest, Member member) {
        Proposal proposalWrapper;
        if (givenVersion > 0) {
            if (member != null && PermissionsClient
                    .canJudge(member.getUserId(), contest.getContestPK())) {
                proposalWrapper = new ProposalJudgeWrapper(proposal, givenVersion,
                        contest, contestPhase, proposal2Phase, member);
            } else {
                proposalWrapper = new Proposal(proposal, givenVersion,
                        contest,
                        contestPhase, proposal2Phase);
            }
        } else {
            final boolean hasVersionTo =
                    proposal2Phase != null && proposal2Phase.getVersionTo() > 0;
            final int localVersion =
                    hasVersionTo ? proposal2Phase.getVersionTo()
                            : proposal.getCurrentVersion();

            if (member != null && PermissionsClient
                    .canJudge(member.getUserId(), contest.getContestPK())) {
                proposalWrapper = new ProposalJudgeWrapper(proposal,
                        localVersion,
                        contest, contestPhase, proposal2Phase, member);
            } else {
                proposalWrapper = new Proposal(proposal, localVersion,
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
