package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;

import java.util.List;

@RestController
public class ProposalContestPhaseAttributeController {

    @Autowired
    private ProposalContestPhaseAttributeDao proposalContestPhaseAttributeDao;

    @RequestMapping(value = "/proposalContestPhaseAttributes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) {
        return proposalContestPhaseAttributeDao.findByGiven(proposalId, contestPhaseId, name);
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes/getByContestPhaseProposalIdName", method = {RequestMethod.GET, RequestMethod.HEAD})
    public IProposalContestPhaseAttribute getProposalContestPhaseAttributesByContestPhase(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) throws NotFoundException {
        List<IProposalContestPhaseAttribute> prop = proposalContestPhaseAttributeDao.findByGiven(proposalId, contestPhaseId , name);
        if(prop!= null && prop.size()>=1) {
            return prop.get(0);
        }
        else{
            throw new NotFoundException();
        }
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes", method = RequestMethod.POST)
    public IProposalContestPhaseAttribute createProposalContestPhaseAttribute(@RequestBody IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        if(proposalContestPhaseAttribute.getAdditionalId() == null){
            proposalContestPhaseAttribute.setAdditionalId(0L);
        }
        if(proposalContestPhaseAttribute.getStringValue() == null){
            proposalContestPhaseAttribute.setStringValue("");
        }
        if(proposalContestPhaseAttribute.getRealValue() == null){
            proposalContestPhaseAttribute.setRealValue(0.0);
        }
        if(proposalContestPhaseAttribute.getNumericValue() == null){
            proposalContestPhaseAttribute.setNumericValue(0L);
        }
        return this.proposalContestPhaseAttributeDao.create(proposalContestPhaseAttribute);
    }

    @RequestMapping(value = "/proposalContestPhaseAttributes/{id}", method = RequestMethod.PUT)
    public boolean updateProposalContestPhaseAttribute(@RequestBody IProposalContestPhaseAttribute proposalContestPhaseAttribute,
                                                       @PathVariable("id") Long id) throws NotFoundException {
        if (id == null || id == 0 || proposalContestPhaseAttributeDao.get(id) == null) {
            throw new NotFoundException("No ProposalContestPhaseAttribute with id " + id);
        } else {
            return proposalContestPhaseAttributeDao.update(proposalContestPhaseAttribute);
        }
    }

    @RequestMapping(value = "/proposalContestPhaseAttributes/{id}", method = RequestMethod.DELETE)
    public String deleteProposalContestPhaseAttribute(@PathVariable("id") Long id)
            throws NotFoundException {
        if (id == null || id == 0) {
            throw new NotFoundException("No ProposalContestPhaseAttribute with id given");
        } else {
            IProposalContestPhaseAttribute proposalContestPhaseAttribute = this.proposalContestPhaseAttributeDao.get(id);
            if (proposalContestPhaseAttribute != null) {
                this.proposalContestPhaseAttributeDao.delete(proposalContestPhaseAttribute.getId());
                return "ProposalContestPhaseAttribute deleted successfully";
            } else {
                throw new NotFoundException("No ProposalContestPhaseAttribute with id given");
            }
        }
    }

}
