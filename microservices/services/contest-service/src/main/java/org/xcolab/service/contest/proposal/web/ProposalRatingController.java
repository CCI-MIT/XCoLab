package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IProposalRating;
import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposalrating.ProposalRatingDao;
import org.xcolab.service.contest.proposal.domain.proposalratingtype.ProposalRatingTypeDao;
import org.xcolab.service.contest.proposal.domain.proposalratingvalue.ProposalRatingValueDao;

import java.util.List;

@RestController
public class ProposalRatingController {

    @Autowired
    private ProposalRatingDao proposalRatingDao;

    @Autowired
    private ProposalRatingValueDao proposalRatingValueDao;

    @Autowired
    private ProposalRatingTypeDao proposalRatingTypeDao;

    @RequestMapping(value = "/proposalRatings", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IProposalRating> getProposalRatings(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long userId
    ) {
        return proposalRatingDao.findByGiven(proposalId, contestPhaseId, userId);
    }

    @RequestMapping(value = "/proposalRatings/findByProposalIdJudgeTypeJudgeIdContestPhaseId", method = {RequestMethod.GET})
    public List<IProposalRating> getProposalRatings(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer judgeType
    ) {
        return proposalRatingDao.findByProposalIdJudgeTypeJudgeIdContestPhaseId(proposalId,judgeType, contestPhaseId, userId );
    }


    @RequestMapping(value = "/proposalRatingValues/{proposalRatingValueId}", method = RequestMethod.GET)
    public IProposalRatingValue getProposalRatingValue(@PathVariable("proposalRatingValueId") Long proposalRatingValueId) throws NotFoundException {
        if (proposalRatingValueId == null || proposalRatingValueId == 0) {
            throw new NotFoundException("No proposalRatingValueId given");
        } else {
            return proposalRatingValueDao.get(proposalRatingValueId);
        }
    }

    @RequestMapping(value = "/proposalRatingValues", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IProposalRatingValue> getProposalRatingValues(
            @RequestParam(required = false) Long ratingTypeId
    ) {
        return proposalRatingValueDao.findByGiven(ratingTypeId);
    }

    @RequestMapping(value = "/proposalRatingTypes/{proposalRatingTypeId}", method = RequestMethod.GET)
    public IProposalRatingType getProposalRatingType(@PathVariable("proposalRatingTypeId") Long proposalRatingTypeId) throws NotFoundException {
        if (proposalRatingTypeId == null || proposalRatingTypeId == 0) {
            throw new NotFoundException("No proposalRatingTypeId given");
        } else {
            return proposalRatingTypeDao.get(proposalRatingTypeId);
        }
    }

    @RequestMapping(value = "/proposalRatingTypes")
    public List<IProposalRatingType> getProposalRatingTypes(@RequestParam Integer judgeType,
            @RequestParam(required = false) Boolean active)
            throws NotFoundException {
        return proposalRatingTypeDao.findByGiven(judgeType, active);
    }

    @RequestMapping(value = "/proposalRatings", method = RequestMethod.POST)
    public IProposalRating createProposalRating(@RequestBody IProposalRating proposalRating) {
        return this.proposalRatingDao.create(proposalRating);
    }

    @RequestMapping(value = "/proposalRatings/{id}", method = RequestMethod.PUT)
    public boolean updateProposalRating(@RequestBody IProposalRating proposalRating,
                                        @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || proposalRatingDao.get(id) == null) {
            throw new NotFoundException("No ProposalRating with id " + id);
        } else {
            return proposalRatingDao.update(proposalRating);
        }
    }
    // findByProposalIdJudgeTypeJudgeIdContestPhaseId
}
