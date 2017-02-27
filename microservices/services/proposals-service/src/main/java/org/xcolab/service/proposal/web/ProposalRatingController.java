package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ProposalRating;
import org.xcolab.model.tables.pojos.ProposalRatingType;
import org.xcolab.model.tables.pojos.ProposalRatingValue;
import org.xcolab.service.proposal.domain.proposalrating.ProposalRatingDao;
import org.xcolab.service.proposal.domain.proposalratingtype.ProposalRatingTypeDao;
import org.xcolab.service.proposal.domain.proposalratingvalue.ProposalRatingValueDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

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
    public List<ProposalRating> getProposalRatings(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long userId
    ) {
        return proposalRatingDao.findByGiven(proposalId, contestPhaseId, userId);
    }

    @RequestMapping(value = "/proposalRatings/findByProposalIdJudgeTypeJudgeIdContestPhaseId", method = {RequestMethod.GET})
    public List<ProposalRating> getProposalRatings(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer judgeType
    ) {
        return proposalRatingDao.findByProposalIdJudgeTypeJudgeIdContestPhaseId(proposalId,judgeType, contestPhaseId, userId );
    }


    @RequestMapping(value = "/proposalRatingValues/{proposalRatingValueId}", method = RequestMethod.GET)
    public ProposalRatingValue getProposalRatingValue(@PathVariable("proposalRatingValueId") Long proposalRatingValueId) throws NotFoundException {
        if (proposalRatingValueId == null || proposalRatingValueId == 0) {
            throw new NotFoundException("No proposalRatingValueId given");
        } else {
            return proposalRatingValueDao.get(proposalRatingValueId);
        }
    }

    @RequestMapping(value = "/proposalRatingValues", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalRatingValue> getProposalRatingValues(
            @RequestParam(required = false) Long ratingTypeId
    ) {
        return proposalRatingValueDao.findByGiven(ratingTypeId);
    }

    @RequestMapping(value = "/proposalRatingTypes/{proposalRatingTypeId}", method = RequestMethod.GET)
    public ProposalRatingType getProposalRatingType(@PathVariable("proposalRatingTypeId") Long proposalRatingTypeId) throws NotFoundException {
        if (proposalRatingTypeId == null || proposalRatingTypeId == 0) {
            throw new NotFoundException("No proposalRatingTypeId given");
        } else {
            return proposalRatingTypeDao.get(proposalRatingTypeId);
        }
    }

    @RequestMapping(value = "/proposalRatingTypes")
    public List<ProposalRatingType> getProposalRatingTypes(@RequestParam Integer judgeType,
            @RequestParam(required = false) Boolean active)
            throws NotFoundException {
        return proposalRatingTypeDao.findByGiven(judgeType, active);
    }


    @RequestMapping(value = "/proposalRatings", method = RequestMethod.POST)
    public ProposalRating createProposalRating(@RequestBody ProposalRating proposalRating) {
        return this.proposalRatingDao.create(proposalRating);
    }

    @RequestMapping(value = "/proposalRatings/{id_}", method = RequestMethod.PUT)
    public boolean updateProposalRating(@RequestBody ProposalRating proposalRating,
                                        @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || proposalRatingDao.get(id_) == null) {
            throw new NotFoundException("No ProposalRating with id " + id_);
        } else {
            return proposalRatingDao.update(proposalRating);
        }
    }

    // findByProposalIdJudgeTypeJudgeIdContestPhaseId
}
