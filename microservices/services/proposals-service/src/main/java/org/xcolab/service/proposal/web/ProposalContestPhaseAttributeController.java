package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
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
        return proposalContestPhaseAttributeDao.findByGiven(proposalId, contestPhaseId, name);
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes/getByContestPhaseProposalIdName", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ProposalContestPhaseAttribute getProposalContestPhaseAttributesByContestPhase(
            @RequestParam(required = false) Long contestPhaseId,
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) throws NotFoundException {
        List<ProposalContestPhaseAttribute> prop = proposalContestPhaseAttributeDao.findByGiven(proposalId, contestPhaseId , name);
        if(prop!= null && prop.size()>=1) {
            return prop.get(0);
        }
        else{
            throw new NotFoundException();
        }
    }
    @RequestMapping(value = "/proposalContestPhaseAttributes", method = RequestMethod.POST)
    public ProposalContestPhaseAttribute createProposalContestPhaseAttribute(@RequestBody ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        if(proposalContestPhaseAttribute.getAdditionalId() == null){
            proposalContestPhaseAttribute.setAdditionalId(0l);
        }
        if(proposalContestPhaseAttribute.getStringValue() == null){
            proposalContestPhaseAttribute.setStringValue("");
        }
        if(proposalContestPhaseAttribute.getRealValue() == null){
            proposalContestPhaseAttribute.setRealValue(0.0);
        }
        if(proposalContestPhaseAttribute.getNumericValue() == null){
            proposalContestPhaseAttribute.setNumericValue(0l);
        }
        return this.proposalContestPhaseAttributeDao.create(proposalContestPhaseAttribute);
    }

    @RequestMapping(value = "/proposalContestPhaseAttributes/{id_}", method = RequestMethod.PUT)
    public boolean updateProposalContestPhaseAttribute(@RequestBody ProposalContestPhaseAttribute proposalContestPhaseAttribute,
                                                       @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || proposalContestPhaseAttributeDao.get(id_) == null) {
            throw new NotFoundException("No ProposalContestPhaseAttribute with id " + id_);
        } else {
            return proposalContestPhaseAttributeDao.update(proposalContestPhaseAttribute);
        }
    }

    @RequestMapping(value = "/proposalContestPhaseAttributes/{id_}", method = RequestMethod.DELETE)
    public String deleteProposalContestPhaseAttribute(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No ProposalContestPhaseAttribute with id given");
        } else {
            ProposalContestPhaseAttribute proposalContestPhaseAttribute = this.proposalContestPhaseAttributeDao.get(id_);
            if (proposalContestPhaseAttribute != null) {
                this.proposalContestPhaseAttributeDao.delete(proposalContestPhaseAttribute.getId_());
                return "ProposalContestPhaseAttribute deleted successfully";
            } else {
                throw new NotFoundException("No ProposalContestPhaseAttribute with id given");
            }
        }
    }

}
