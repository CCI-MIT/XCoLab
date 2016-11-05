package org.xcolab.client.proposals;

import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporterDto;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVoteDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalMemberRatingClient {

    private static final Map<RestService, ProposalMemberRatingClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource1<ProposalSupporterDto, Long> proposalSupporterResource;
    private final RestResource<ProposalVoteDto, Long> proposalVoteResource;

    private ProposalMemberRatingClient(RestService proposalService) {
        proposalSupporterResource = new RestResource1<>(proposalService,
                "proposalSupporters", ProposalSupporterDto.TYPES);
        proposalVoteResource = new RestResource1<>(proposalService,
                "proposalVotes", ProposalVoteDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalMemberRatingClient fromService(RestService proposalService) {
        ProposalMemberRatingClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalMemberRatingClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public List<ProposalSupporter> getProposalSupporters(Long proposalId) {
        return DtoUtil.toPojos(proposalSupporterResource.list()
                .withCache(CacheKeys.withClass(ProposalSupporterDto.class)
                                .withParameter("proposalId", proposalId)
                                .asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public List<ProposalSupporter> getProposalSupportersByUserId(Long userId) {
        return DtoUtil.toPojos(proposalSupporterResource.list()
                .optionalQueryParam("userId", userId)
                .execute(), proposalService);
    }

    public Integer getProposalSupportersCount(Long proposalId) {

        return proposalSupporterResource.service("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public Boolean isMemberProposalSupporter(Long proposalId, Long memberId) {

        return proposalSupporterResource.service("isMemberProposalSupporter", Boolean.class)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("memberId", memberId)
                .get();
    }

    public void addProposalSupporter(long proposalId, long userId) {
        addProposalSupporter(proposalId, userId, true);
    }

    public void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
        ProposalSupporter supporter = new ProposalSupporter();
        supporter.setProposalId(proposalId);
        supporter.setUserId(userId);
        supporter.setCreateDate(new Timestamp(new Date().getTime()));
        createProposalSupporter(supporter);

        if (publishActivity) {
            ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                    ActivityProvidersType.ProposalSupporterAddedActivityEntry.getType());
        }
    }

    public ProposalSupporter createProposalSupporter(ProposalSupporter proposalSupporter) {
        return proposalSupporterResource
                .create(new ProposalSupporterDto(proposalSupporter))
                .execute().toPojo(proposalService);
    }

    public void removeProposalSupporter(long proposalId, long userId) {
        deleteProposalSupporter(proposalId, userId);
        ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                ActivityProvidersType.ProposalSupporterRemovedActivityEntry.getType());
    }

    public Boolean deleteProposalSupporter(Long proposalId, Long memberId) {
        return proposalSupporterResource.service("deleteProposalSupporter", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("memberId", memberId)
                .delete();
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
    public  Boolean hasUserVoted(Long contestPhaseId, Long memberId) {
        return proposalVoteResource.service("hasUserVoted", Boolean.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("memberId", memberId)
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
    public boolean deleteProposalVote(Long contestPhaseId , Long memberId) {
        return proposalVoteResource.service("deleteVote", Boolean.class)
                .queryParam("memberId", memberId)
                .queryParam("contestPhaseId", contestPhaseId)
                .delete();
    }
    public ProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long memberId) {
        ProposalVote pv = new ProposalVote();
        pv.setContestPhaseId(proposalId);
        pv.setContestPhaseId(contestPhaseId);
        pv.setUserId(memberId);
        pv.setCreateDate(new Timestamp(new Date().getTime()));
        pv.setIsValid(true);// should this default to true?
        return createProposalVote(pv);
    }
    public ProposalVote createProposalVote(ProposalVote proposalVote) {
        return proposalVoteResource.create(new ProposalVoteDto(proposalVote)).execute()
                .toPojo(proposalService);
    }

    public ProposalVote getProposalVoteByProposalIdUserId(Long proposalId, Long userId) {
        return proposalVoteResource.service("getProposalVoteByProposalIdUserId", ProposalVote.class)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .get();
    }
}
