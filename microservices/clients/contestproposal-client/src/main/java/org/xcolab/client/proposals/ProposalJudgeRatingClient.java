package org.xcolab.client.proposals;

import org.xcolab.client.proposals.enums.ProposalJudgeType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingDto;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingTypeDto;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValueDto;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalJudgeRatingClient {

    private static final Map<RestService, ProposalJudgeRatingClient> instances = new HashMap<>();

    private final RestService proposalService;

    private final RestResource1<ProposalRatingDto, Long> proposalRatingResource;
    private final RestResource1<ProposalRatingValueDto, Long> proposalRatingValueResource;
    private final RestResource1<ProposalRatingTypeDto, Long> proposalRatingTypeResource;

    private ProposalJudgeRatingClient(RestService proposalService) {
        proposalRatingResource = new RestResource1<>(proposalService,
                "proposalRatings", ProposalRatingDto.TYPES);
        proposalRatingValueResource = new RestResource1<>(proposalService,
                "proposalRatingValues", ProposalRatingValueDto.TYPES);
        proposalRatingTypeResource = new RestResource1<>(proposalService,
                "proposalRatingTypes", ProposalRatingTypeDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalJudgeRatingClient fromService(RestService proposalService) {
        ProposalJudgeRatingClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalJudgeRatingClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public List<ProposalRating> getProposalRatingsByProposalUserContestPhase(Long proposalId,
            Long contestPhaseId, Long userId) {
        return DtoUtil.toPojos(proposalRatingResource.list()
                //.withCache(CacheKeys.withClass(ProposalRatingDto.class)
                //                .withParameter("proposalId", proposalId)
                //                .withParameter("contestPhaseId", contestPhaseId)
                //                .withParameter("userId", userId).asList(),
                //        CacheRetention.MISC_MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .execute(), proposalService);
    }

    public List<ProposalRating> getFellowRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.FELLOW.getId());
    }


    public  List<ProposalRatingType> getRatingTypesForJudges() {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.JUDGE.getId());
    }

    public List<ProposalRatingType> getRatingTypesForFellows() {
            return this.getRatingTypesForJudgeType(ProposalJudgeType.FELLOW.getId());
    }

    private List<ProposalRatingType> getRatingTypesForJudgeType(int judgeType) {
        return DtoUtil.toPojos(proposalRatingTypeResource.list().queryParam("judgeType",judgeType).execute(),proposalService);
    }

    protected List<ProposalRating> getRatingsForProposal(long proposalId, long contestPhaseId,
            int judgeType) {

        return DtoUtil.toPojos(proposalRatingResource
                .service("findByProposalIdJudgeTypeJudgeIdContestPhaseId",
                        ProposalRatingDto.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("contestPhaseId", contestPhaseId)
                .getList(), proposalService);
    }

    public List<ProposalRating> getJudgeRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.JUDGE.getId());
    }

    public List<ProposalRating> getJudgeRatingsForProposalAndUser(long userId, long proposalId,
            long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId,
                contestPhaseId);
    }

    protected List<ProposalRating> getRatingsForProposalAndUser(long proposalId, int judgeType,
            long userId, long contestPhaseId) {
        return DtoUtil.toPojos(proposalRatingResource
                .service("findByProposalIdJudgeTypeJudgeIdContestPhaseId",
                        ProposalRatingDto.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .getList(), proposalService);

    }

    public List<ProposalRating> getFellowRatingForProposalAndUser(long userId, long proposalId,
            long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId,
                contestPhaseId);
    }

    public ProposalRating createProposalRating(ProposalRating proposalRating) {
        return proposalRatingResource
                .create(new ProposalRatingDto(proposalRating))
                .execute().toPojo(proposalService);
    }

    public boolean updateProposalRating(ProposalRating proposalRating) {
        return proposalRatingResource
                .update(new ProposalRatingDto(proposalRating), proposalRating.getId_())
                .execute();
    }

    public ProposalRatingValue getProposalRatingValue(long id) {
        return proposalRatingValueResource.get(id)
                .execute().toPojo(proposalService);
    }

    public List<ProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(Long ratingTypeId) {
        return DtoUtil.toPojos(proposalRatingValueResource.list()
                .optionalQueryParam("ratingTypeId", ratingTypeId)
                .execute(),proposalService);
    }

    public ProposalRatingType getProposalRatingType(long id) {
        return proposalRatingTypeResource.get(id)
                .execute().toPojo(proposalService);
    }
}
