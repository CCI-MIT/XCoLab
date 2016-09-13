package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

@RestController
public class ProposalContestPhaseAttributeController {

    @Autowired
    private ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

    @RequestMapping(value = "/proposalContestPhaseAttributes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) {
        return proposalContestPhaseAttributeDao.findByGiven(contestPhaseId, proposalId, name);
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes/getByContestPhaseProposalIdName", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ProposalContestPhaseAttribute getProposalContestPhaseAttributesS(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) throws EntityNotFoundException {
        List<ProposalContestPhaseAttribute> prop = proposalContestPhaseAttributeDao.findByGiven(contestPhaseId, proposalId, name);
        if(prop!= null && prop.size()>=1) {
            return prop.get(0);
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes", method = RequestMethod.POST)
    public ProposalContestPhaseAttribute createProposalContestPhaseAttribute(@RequestBody ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return this.proposalContestPhaseAttributeDao.create(proposalContestPhaseAttribute);
    }
}
