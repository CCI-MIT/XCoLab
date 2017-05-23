package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ProposalUnversionedAttribute;
import org.xcolab.service.proposal.domain.proposalunversionedattribute.ProposalUnversionedAttributeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ProposalUnversionedAttributeController {

    @Autowired
    private ProposalUnversionedAttributeDao proposalUnversionedAttributeDao;

    @RequestMapping(value = "/proposalUnversionedAttributes", method = RequestMethod.POST)
    public ProposalUnversionedAttribute createProposalUnversionedAttribute(@RequestBody ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return this.proposalUnversionedAttributeDao.create(proposalUnversionedAttribute);
    }

    @RequestMapping(value = "/proposalUnversionedAttributes/{id_}", method = RequestMethod.PUT)
    public boolean updateProposalUnversionedAttribute(@RequestBody ProposalUnversionedAttribute proposalUnversionedAttribute,
                                                      @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || proposalUnversionedAttributeDao.get(id_) == null) {
            throw new NotFoundException("No ProposalUnversionedAttribute with id " + id_);
        } else {
            return proposalUnversionedAttributeDao.update(proposalUnversionedAttribute);
        }
    }

    @RequestMapping(value = "/proposalUnversionedAttributes/getByProposalIdName", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ProposalUnversionedAttribute getProposalUnversionedAttributes (
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name
    ) throws NotFoundException {
        ProposalUnversionedAttribute rt = proposalUnversionedAttributeDao.getByProposalIdName(proposalId, name);
        if(rt!= null) {
            return rt;
        }
        throw new NotFoundException("Proposal Unversioned Attribute with id: "+ proposalId + " name: "+ name);

    }

    @RequestMapping(value = "/proposalUnversionedAttributes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalUnversionedAttribute> getProposalUnversionedAttributes(
            @RequestParam(required = false) Long proposalId
    ) {
        return proposalUnversionedAttributeDao.findByGiven(proposalId);
    }

    @RequestMapping(value = "/proposalUnversionedAttributes/{id_}", method = RequestMethod.DELETE)
    public String deleteProposalUnversionedAttribute(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No ProposalUnversionedAttribute with id given");
        } else {
            ProposalUnversionedAttribute proposalUnversionedAttribute = this.proposalUnversionedAttributeDao.get(id_);
            if (proposalUnversionedAttribute != null) {
                this.proposalUnversionedAttributeDao.delete(proposalUnversionedAttribute.getId_());
                return "ProposalUnversionedAttribute deleted successfully";
            } else {
                throw new NotFoundException("No ProposalUnversionedAttribute with id given");
            }
        }
    }



}
