package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ProposalSupporter;
import org.xcolab.service.proposal.domain.proposalsupporter.ProposalSupporterDao;

import java.util.List;

@RestController
public class ProposalSupporterController {

    @Autowired
    private ProposalSupporterDao proposalSupporterDao;



    @RequestMapping(value = "/proposalSupporters", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalSupporter> getProposalSupporters(
            @RequestParam(required = false) Long proposalId
    ) {
        return proposalSupporterDao.findByGiven(proposalId, null);
    }


    @RequestMapping(value = "/proposalSupporters/count", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Integer getProposalSupportersCount(
            @RequestParam Long proposalId
    ) {

        return proposalSupporterDao.countByProposalId(proposalId);
    }


    @RequestMapping(value = "/proposalSupporters/isMemberProposalSupporter", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Boolean getProposalSupportersCount(
            @RequestParam Long proposalId,
            @RequestParam Long memberId
    ) {
        List<ProposalSupporter> ret = proposalSupporterDao.findByGiven(proposalId, memberId);

        if(ret != null && ret.size() ==1 ){
            return true;
        }else{
            return false;
        }
    }

}
