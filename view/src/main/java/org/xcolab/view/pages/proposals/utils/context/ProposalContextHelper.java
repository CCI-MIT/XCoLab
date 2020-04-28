package org.xcolab.view.pages.proposals.utils.context;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.commons.servlet.RequestParamUtil;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.InvalidContestUrlException;
import org.xcolab.view.pages.proposals.exceptions.InvalidProposalUrlException;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;

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

    private final ContestWrapper contest;
    private final ClientHelper clientHelper;

    public ProposalContextHelper(HttpServletRequest request) {
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

        ContestWrapper localContest = fetchContest();
        log.trace("Fetched local contest: {}", localContest);
        clientHelper = new ClientHelper();
        if (localContest != null) {
            final Long contestId = localContest.getId();
            if (contestId != null) {
                contest = setupContestFromTheRightClient(contestId);
            } else {
                throw new IllegalStateException("Contest has contestId=null: " + localContest);
            }
        } else {
            log.trace("Local contest is null: contestUrlName={}, contestYear={}, contestId={}",
                    givenContestUrlName, givenContestYear, givenContestId);
            contest = null;
        }
    }

    private ContestWrapper setupContestFromTheRightClient(long contestId) {

        final IContestClient contestClient = clientHelper.getContestClient();
        log.debug("Setting up contest {} from client {}", contestId, contestClient);

        try {
            return new ContestWrapper(contestClient.getContest(contestId));
        } catch (ContestNotFoundException ignored) {
            log.warn("Mirroring problem: Contest {} not found in client {}",
                    contestId, contestClient);
            return null;
        }
    }

    private ContestWrapper fetchContest() {
        ContestWrapper localContest = null;
        try {
            if (StringUtils.isNotBlank(givenContestUrlName) && givenContestYear > 0) {
                localContest = StaticContestContext.getContestClient()
                        .getContest(givenContestUrlName, givenContestYear);
            } else if (givenContestId > 0) {
                localContest = StaticContestContext.getContestClient().getContest(givenContestId);
            }
        } catch (ContestNotFoundException e) {
            log.debug("Contest not found: {}", e.getMessage());
        }
        return localContest;
    }

    public UserWrapper getMember() {
        return MemberAuthUtil.getMemberOrNull();
    }

    public ContestWrapper getContest() throws InvalidContestUrlException {
        if (contest == null) {
            final boolean contestUserSupplied = StringUtils.isNotBlank(givenContestUrlName)
                    || givenContestId > 0;
            if (contestUserSupplied) {
                log.debug("Invalid contest supplied: givenContestUrlName = {}, givenContestId = {}",
                        givenContestUrlName, givenContestId);
                throw new InvalidContestUrlException(givenContestUrlName, givenContestId);
            }
        }
        return contest;
    }

    public ClientHelper getClientHelper() {
        return clientHelper;
    }

    public ContestPhaseWrapper getContestPhase(ContestWrapper contest, ProposalWrapper proposal) {
        final IContestClient contestClient = clientHelper.getContestClient();
        final IProposalClient proposalClient = clientHelper.getProposalClient();

        ContestPhaseWrapper contestPhase;
        if (givenPhaseId > 0) {
            contestPhase = contestClient.getContestPhase(givenPhaseId);
        } else if (proposal != null && proposal.isContestMatchesLatestContest()) {
            contestPhase = proposalClient.getLatestContestPhaseInProposal(proposal.getId());
        } else {
            contestPhase = contestClient.getActivePhase(contest.getId());
        }

        if (contestPhase == null) {
            throw ReferenceResolutionException
                    .toObject(ContestPhaseWrapper.class, "")
                    .fromObject(ContestWrapper.class, contest.getId());
        }
        return contestPhase;
    }

    public IProposal2Phase getProposal2Phase(ContestPhaseWrapper contestPhase) {
        final IProposalPhaseClient proposalPhaseClient = clientHelper.getProposalPhaseClient();
        try {
            return proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(givenProposalId,
                            contestPhase.getId());
        } catch (Proposal2PhaseNotFoundException e) {
            return null;
        }
    }

    public ProposalWrapper getProposal(ContestWrapper contest) throws InvalidProposalUrlException {
        final IProposalClient proposalClient = clientHelper.getProposalClient();
        ProposalWrapper proposal = null;
        if (givenProposalId > 0) {
            try {
                Integer version = givenVersion > 0 ? givenVersion : null;
                proposal = new ProposalWrapper(proposalClient.getProposal(givenProposalId), version, contest);
            } catch (ProposalNotFoundException e) {
                log.debug("Invalid proposal supplied: givenProposalId = {}", givenProposalId);
                throw new InvalidProposalUrlException(contest, null, givenProposalId);
            }
        }
        return proposal;
    }

    public ProposalWrapper getProposalWrapper(ProposalWrapper proposal, IProposal2Phase proposal2Phase,
            ContestPhaseWrapper contestPhase, ContestWrapper contest, UserWrapper member) {
        ProposalWrapper proposalWrapper;
        if (givenVersion > 0) {
            if (member != null && StaticUserContext.getPermissionClient()
                    .canJudge(member.getId(), contest.getId())) {
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

            if (member != null && StaticUserContext.getPermissionClient()
                    .canJudge(member.getId(), contest.getId())) {
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

}
