package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalJudgeType;

import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IProposalJudgeRatingClient {

    @GetMapping("/proposalRatings")
    List<ProposalRatingWrapper> getProposalRatingsByProposalUserContestPhase(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "userId", required = false) Long userId);

    default List<ProposalRatingWrapper> getFellowRatingsForProposal(long proposalId,
            long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.FELLOW.getId());
    }

    default List<IProposalRatingType> getRatingTypesForJudges() {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.JUDGE.getId());
    }

    default List<IProposalRatingType> getRatingTypesForFellows() {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.FELLOW.getId());
    }

    @GetMapping("/proposalRatingTypes")
    List<IProposalRatingType> getRatingTypesForJudgeType(
            @RequestParam("judgeType") Integer judgeType);

    default List<ProposalRatingWrapper> getRatingsForProposal(Long proposalId, Long contestPhaseId,
            Integer judgeType) {
        return getRatingsForProposalAndUser(proposalId, judgeType, null, contestPhaseId);
    }

    default List<ProposalRatingWrapper> getJudgeRatingsForProposal(long proposalId,
            long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.JUDGE.getId());
    }

    default List<ProposalRatingWrapper> getJudgeRatingsForProposalAndUser(long userId,
            long proposalId, long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId,
                contestPhaseId);
    }

    @GetMapping("/proposalRatings/findByProposalIdJudgeTypeJudgeIdContestPhaseId")
    List<ProposalRatingWrapper> getRatingsForProposalAndUser(
            @RequestParam("proposalId") Long proposalId,
            @RequestParam(value = "judgeType", required = false) Integer judgeType,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam("contestPhaseId") Long contestPhaseId);

    default List<ProposalRatingWrapper> getFellowRatingForProposalAndUser(long userId,
            long proposalId, long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId,
                contestPhaseId);
    }

    @PostMapping("/proposalRatings")
    ProposalRatingWrapper createProposalRating(@RequestBody ProposalRatingWrapper proposalRating);

    @PutMapping("/proposalRatings")
    boolean updateProposalRating(@RequestBody ProposalRatingWrapper proposalRating);

    @GetMapping("/proposalRatingValues/{proposalRatingValueId}")
    IProposalRatingValue getProposalRatingValue(
            @PathVariable("proposalRatingValueId") Long proposalRatingValueId);

    @GetMapping("/proposalRatingValues")
    List<IProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(
            @RequestParam(value = "ratingTypeId", required = false) Long ratingTypeId);

    @GetMapping("/proposalRatingTypes/{proposalRatingTypeId}")
    IProposalRatingType getProposalRatingType(
            @PathVariable("proposalRatingTypeId") Long proposalRatingTypeId);
}
