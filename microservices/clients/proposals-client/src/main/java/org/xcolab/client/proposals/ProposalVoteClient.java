package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVote;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ProposalVoteClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource<ProposalVote, Long> proposalVoteResource = new RestResource1<>(proposalService,
            "proposalVotes", ProposalVote.TYPES);

    public static Integer countProposalVotesInContestPhase(Long contestPhaseId) {
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

    public static Integer countProposalVotesInContestPhaseProposalId(Long proposalId, Long contestPhaseId) {
        return proposalVoteResource.service("count", Integer.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public static Boolean hasUserVoted(Long contestPhaseId, Long memberId) {
        return proposalVoteResource.service("hasUserVoted", Boolean.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("memberId", memberId)
                .get();
    }

    public static Boolean hasUserVoted(Long proposalId, Long contestPhaseId, Long memberId) {
        return proposalVoteResource.service("hasUserVoted", Boolean.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("memberId", memberId)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public static List<ProposalVote> getProposalVotes(Long contestPhaseId, Long proposalId, Long userId) {
            return proposalVoteResource.list()
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("userId", userId)
                    .execute();


    }

    public static void removeProposalVote(Long contestPhaseId, Long userid) {
         proposalVoteResource.service("deleteVote", Boolean.class)
                .queryParam("memberId",userid)
                .queryParam("contestPhaseId", contestPhaseId)
                .delete();
    }

    public static boolean updateProposalVote(ProposalVote proposalVote) {
        return proposalVoteResource.service("updateVote", Boolean.class)
                .post(proposalVote);
    }

    public static ProposalVote getProposalVoteByProposalIdUserId(Long proposalId, Long userId) {
        try {
            return proposalVoteResource
                    .service("getProposalVoteByProposalIdUserId", ProposalVote.class)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("userId", userId)
                    .getChecked();
        }catch (EntityNotFoundException ignored){
            return null;
        }
    }
    public static void addVote(Long proposalId,Long contestPhaseId,Long userId){
        ProposalVote pv = new ProposalVote();
        pv.setProposalId(proposalId);
        pv.setContestPhaseId(contestPhaseId);
        pv.setUserId(userId);
        pv.setIsValid(true);
        pv.setCreateDate(new Timestamp(new Date().getTime()));
        proposalVoteResource.create(pv).execute();
    }
}
