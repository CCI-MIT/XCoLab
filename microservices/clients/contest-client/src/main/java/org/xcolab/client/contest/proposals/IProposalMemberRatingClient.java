package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalSupporter;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalVote;
import org.xcolab.client.contest.pojo.wrapper.SupportedProposal;
import org.xcolab.util.activities.enums.ProposalActivityType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IProposalMemberRatingClient {

    @GetMapping("/proposalSupporters")
    List<IProposalSupporter> getProposalSupporters(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "userId", required = false) Long userId);

    default List<IProposalSupporter> getProposalSupporters(Long proposalId) {
        return getProposalSupporters(proposalId, null);
    }

    default List<IProposalSupporter> getProposalSupportersByUserId(Long userId) {
        return getProposalSupporters(null, userId);
    }

    @GetMapping("/supportedProposals")
    List<IProposalSupporter> getSupportedProposals(@RequestParam("userId") Long userId);

    @RequestMapping("/proposalSupporters/count")
    int getProposalSupportersCount(@RequestParam("proposalId") Long proposalId);

    @GetMapping("/proposalSupporters/isMemberProposalSupporter")
    boolean isMemberProposalSupporter(@RequestParam("proposalId") Long proposalId,
            @RequestParam("userId") Long userId);

    default void addProposalSupporter(long proposalId, long userId) {
        addProposalSupporter(proposalId, userId, true);
    }

    default void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
        IProposalSupporter supporter = new ProposalSupporter();
        supporter.setProposalId(proposalId);
        supporter.setUserId(userId);
        supporter.setCreatedAt(new Timestamp(new Date().getTime()));
        createProposalSupporter(supporter);

        IActivityClient activityClient = StaticActivityContext.getActivityClient();

        if (publishActivity) {
            activityClient.createActivityEntry(ProposalActivityType.SUPPORT_ADDED, userId,
                    proposalId);
        }
    }

    @PostMapping("/proposalSupporters")
    IProposalSupporter createProposalSupporter(@RequestBody IProposalSupporter proposalSupporter);

    @DeleteMapping("/proposalSupporters/deleteProposalSupporter")
    boolean deleteProposalSupporter(@RequestParam("proposalId") Long proposalId,
            @RequestParam("userId") Long userId);

    @GetMapping("/proposalVotes/count")
    int countProposalVotes(
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "isValidOverride", required = false) Boolean isValidOverride);

    default int countProposalVotesInContestPhase(Long contestPhaseId) {
        return countProposalVotes(contestPhaseId, null, null, true);
    }

    default int countVotesByUserInProposalAndPhase(long userId, long proposalId, long phaseId){
        return countProposalVotes(phaseId, proposalId, userId, null);
    }
    default int countVotesByUserInPhase(long userId, long phaseId) {
        return countProposalVotes(phaseId, null, userId, null);
    }

    @GetMapping("/proposalVotes/hasUserVoted")
    boolean hasUserVoted(
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "userId", required = false) Long userId);

    default boolean hasUserVoted(Long contestPhaseId, Long userId) {
        return hasUserVoted(null, contestPhaseId, userId);
    }

    default List<IProposalVote> getProposalVotesInPhase(long contestPhaseId) {
        return getProposalVotes(contestPhaseId, null, null);
    }

    default List<IProposalVote> getVotesByMember(Long userId) {
        return getProposalVotes(null, null, userId);
    }

    default void invalidateVotesForMember(long userId, String reason) {
        getVotesByMember(userId)
                .stream()
                .filter(IProposalVote::isIsValid)
                .forEach(vote -> {
                    vote.setIsValid(false);
                    vote.setLastValidationResult(reason);
                    updateProposalVote(vote);
                });
    }

    @GetMapping("/proposalVotes")
    List<IProposalVote> getProposalVotes(
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "userId", required = false) Long userId);

    default List<IProposalVote> getProposalVotesByUserInPhase(long userId, long contestPhaseId) {
        return getProposalVotes(contestPhaseId, null, userId);
    }

    @PutMapping("/proposalVotes/updateVote")
    boolean updateProposalVote(@RequestBody IProposalVote proposalVote);

    @DeleteMapping("/proposalVotes/deleteVote")
    boolean deleteProposalVote(@RequestParam("proposalId") Long proposalId,
            @RequestParam("contestPhaseId") Long contestPhaseId,
            @RequestParam("userId") Long userId);

    default IProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long userId,
            int value) {
        IProposalVote pv = new ProposalVote();
        pv.setProposalId(proposalId);
        pv.setContestPhaseId(contestPhaseId);
        pv.setUserId(userId);
        pv.setValue(value);
        pv.setIsValid(true);// should this default to true?
        return createProposalVote(pv);
    }

    @PostMapping("/proposalVotes")
    IProposalVote createProposalVote(@RequestBody IProposalVote proposalVote);

    @GetMapping("/proposalVotes/proposalVoteByProposalIdUserId")
    IProposalVote getProposalVoteByProposalIdUserId(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "userId", required = false) Long userId);
}
