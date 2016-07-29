package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.client.proposals.pojo.ProposalVote;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalsClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource<Proposal> proposalResource = new RestResource<>(
            proposalService, "proposals", Proposal.TYPES);

    private static final RestResource<Proposal2Phase> proposal2PhaseResource = new RestResource<>(
            proposalService, "proposal2Phases", Proposal2Phase.TYPES);

    private static final RestResource<ProposalVote> proposalVoteResource = new RestResource<>(proposalService,
            "proposalVotes", ProposalVote.TYPES);


    public static Proposal createProposal(Proposal proposal) {
        return proposalResource.create(proposal).execute();
    }

    public static List<Proposal> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public static List<Proposal> listProposals(int start, int limit, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        return proposalResource.list()
                .addRange(start, limit)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("visible", visible)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon)
                .execute();
    }

    public static Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    public static Proposal getProposal(long proposalId, boolean includeDeleted)
            throws ProposalNotFoundException {
        try {
            return proposalResource.get(proposalId)
                    .queryParam("includeDeleted", includeDeleted)
                    .cacheIdentifier("proposalId_" + proposalId
                            + "_includeDeleted_" + includeDeleted)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException(proposalId);
        }
    }

    public static boolean updateProposal(Proposal proposal) {
        return proposalResource.update(proposal, proposal.getProposalId()).execute();
    }

    public static boolean deleteProposal(long proposalId) {
        return proposalResource.delete(proposalId).execute();
    }

    public static Integer getProposalCountForActiveContestPhase(Long contestPhasePK ) {
        try {
            return proposal2PhaseResource.service(contestPhasePK, "getProposalCount", Integer.class).get();
        } catch (EntityNotFoundException ignored){
            return 0;
        }
    }

    public static Integer countProposalsInContestPhaseVotes(Long contestPhaseId){
        try {
            return proposalVoteResource.service("count", Integer.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .cacheIdentifier("proposalsInContestPhaseVotes" + contestPhaseId)
                    .get();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static List<ProposalVote> getProposalVotes(Long contestPhaseId,Long proposalId,Long userId) {
        return proposalVoteResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .execute();
    }
}
