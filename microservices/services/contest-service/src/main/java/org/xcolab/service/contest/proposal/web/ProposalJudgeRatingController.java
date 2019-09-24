package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposalrating.ProposalRatingDao;
import org.xcolab.service.contest.proposal.domain.proposalratingtype.ProposalRatingTypeDao;
import org.xcolab.service.contest.proposal.domain.proposalratingvalue.ProposalRatingValueDao;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ProposalJudgeRatingController implements IProposalJudgeRatingClient {

    private final ProposalRatingDao proposalRatingDao;
    private final ProposalRatingValueDao proposalRatingValueDao;
    private final ProposalRatingTypeDao proposalRatingTypeDao;

    @Autowired
    public ProposalJudgeRatingController(ProposalRatingDao proposalRatingDao,
            ProposalRatingValueDao proposalRatingValueDao,
            ProposalRatingTypeDao proposalRatingTypeDao) {
        this.proposalRatingDao = proposalRatingDao;
        this.proposalRatingValueDao = proposalRatingValueDao;
        this.proposalRatingTypeDao = proposalRatingTypeDao;
    }

    @Override
    @GetMapping("/proposalRatings")
    public List<ProposalRatingWrapper> getProposalRatingsByProposalUserContestPhase(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long userId) {
        return proposalRatingDao.findByGiven(proposalId, contestPhaseId, userId);
    }

    @Override
    @GetMapping("/proposalRatings/findByProposalIdJudgeTypeJudgeIdContestPhaseId")
    public List<ProposalRatingWrapper> getRatingsForProposalAndUser(@RequestParam Long proposalId,
            @RequestParam(required = false) Integer judgeType,
            @RequestParam(required = false) Long userId, @RequestParam Long contestPhaseId) {
        return proposalRatingDao
                .findByProposalIdJudgeTypeJudgeIdContestPhaseId(proposalId, judgeType,
                        contestPhaseId, userId);
    }

    @Override
    @GetMapping("/proposalRatingValues/{proposalRatingValueId}")
    public IProposalRatingValue getProposalRatingValue(@PathVariable Long proposalRatingValueId) {
        if (proposalRatingValueId == null || proposalRatingValueId == 0) {
            throw new RuntimeEntityNotFoundException("proposalRatingValueId not given");
        }
        try {
            return proposalRatingValueDao.get(proposalRatingValueId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalRatingValue not found with id " + proposalRatingValueId);
        }
    }

    @Override
    @GetMapping("/proposalRatingValues")
    public List<IProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(
            @RequestParam(required = false) Long ratingTypeId) {
        return proposalRatingValueDao.findByGiven(ratingTypeId);
    }

    @Override
    @GetMapping("/proposalRatingTypes/{proposalRatingTypeId}")
    public IProposalRatingType getProposalRatingType(@PathVariable Long proposalRatingTypeId) {
        if (proposalRatingTypeId == null || proposalRatingTypeId == 0) {
            throw new RuntimeEntityNotFoundException("proposalRatingTypeId not given");
        }
        try {
            return proposalRatingTypeDao.get(proposalRatingTypeId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalRatingType not found with id " + proposalRatingTypeId);
        }
    }

    @Override
    @GetMapping("/proposalRatingTypes")
    public List<IProposalRatingType> getRatingTypesForJudgeType(@RequestParam Integer judgeType) {
        return proposalRatingTypeDao.findByGiven(judgeType, true);
    }

    @Override
    @PostMapping("/proposalRatings")
    public ProposalRatingWrapper createProposalRating(
            @RequestBody ProposalRatingWrapper proposalRating) {
        return this.proposalRatingDao.create(proposalRating);
    }

    @Override
    @PutMapping("/proposalRatings")
    public boolean updateProposalRating(@RequestBody ProposalRatingWrapper proposalRating) {
        Long id = proposalRating.getId();
        try {
            if (!(id == null || id == 0 || proposalRatingDao.get(id) == null)) {
                return proposalRatingDao.update(proposalRating);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("ProposalRating not found with id " + id);
    }
}
