package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ProposalSupporter;
import org.xcolab.service.proposal.domain.proposalsupporter.ProposalSupporterDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ProposalSupporterController {

    @Autowired
    private ProposalSupporterDao proposalSupporterDao;


    @RequestMapping(value = "/proposalSupporters", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalSupporter> getProposalSupporters(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long userId
    ) {
        return proposalSupporterDao.findByGiven(proposalId, userId);
    }


    @RequestMapping(value = "/proposalSupporters/count", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Integer getProposalSupportersCount(
            @RequestParam Long proposalId
    ) {

        return proposalSupporterDao.countByProposalId(proposalId);
    }

    @RequestMapping(value = "/proposalSupporter", method = RequestMethod.POST)
    public ProposalSupporter createProposalSupporter(@RequestBody ProposalSupporter proposalSupporter) {
        return this.proposalSupporterDao.create(proposalSupporter);
    }

    @RequestMapping(value = "/proposalSupporters/isMemberProposalSupporter", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Boolean getProposalSupportersCount(
            @RequestParam Long proposalId,
            @RequestParam Long memberId
    ) {
        List<ProposalSupporter> ret = proposalSupporterDao.findByGiven(proposalId, memberId);

        if (ret != null && ret.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/proposalSupporter/deleteProposalSupporter", method = RequestMethod.DELETE)
    public Boolean deleteProposalSupporter(@RequestParam("proposalId") Long proposalId,
                                           @RequestParam("memberId") Long memberId)
            throws NotFoundException {
        this.proposalSupporterDao.delete(proposalId, memberId);
        return true;

    }

}
