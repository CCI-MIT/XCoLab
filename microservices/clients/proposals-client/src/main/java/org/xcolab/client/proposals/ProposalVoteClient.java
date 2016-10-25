package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVoteDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalVoteClient {

    private static final Map<RestService, ProposalVoteClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource<ProposalVoteDto, Long> proposalVoteResource;

    private ProposalVoteClient(RestService proposalService) {
        proposalVoteResource = new RestResource1<>(proposalService,
                "proposalVotes", ProposalVoteDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalVoteClient fromService(RestService proposalService) {
        ProposalVoteClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalVoteClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public Integer countProposalVotesInContestPhase(Long contestPhaseId) {
        try {
            return proposalVoteResource.<Proposal, Integer>service("count", Integer.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestPhaseId", contestPhaseId)
                            .asCount(), CacheRetention.REQUEST)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public Integer countProposalVotesInContestPhaseProposalId(Long proposalId,
            Long contestPhaseId) {
        return proposalVoteResource.service("count", Integer.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public Boolean hasUserVoted(Long proposalId, Long contestPhaseId, Long memberId) {
        return proposalVoteResource.service("hasUserVoted", Boolean.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("memberId", memberId)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public List<ProposalVote> getProposalVotes(Long contestPhaseId, Long proposalId, Long userId) {
        return DtoUtil.toPojos(proposalVoteResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .execute(), proposalService);
    }

    public boolean updateProposalVote(ProposalVote proposalVote) {
        return proposalVoteResource.service("updateVote", Boolean.class)
                .post(proposalVote);
    }

    public ProposalVote getProposalVoteByProposalIdUserId(Long proposalId, Long userId) {
        return proposalVoteResource.service("getProposalVoteByProposalIdUserId", ProposalVote.class)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .get();
    }
}
