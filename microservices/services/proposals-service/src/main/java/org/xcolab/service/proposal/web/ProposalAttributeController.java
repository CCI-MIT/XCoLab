package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.proposal.service.proposalattribute.ProposalAttributeService;

import java.util.List;

@RestController
public class ProposalAttributeController {

    @Autowired
    ProposalAttributeDao proposalAttributeDao;

    @Autowired
    ProposalAttributeService proposalAttributeService;


    /*
    ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(), ProposalAttributeKeys.BASE_PROPOSAL_ID,
                    baseProposalId);
    ProposalAttributeLocalServiceUtil.getImpactProposalAttributes
    ProposalAttributeLocalServiceUtil.removeAttribute(proposalsContext.getUser(request).getUserId(), proposalAttribute);
    ProposalAttributeLocalServiceUtil.getAttributes(visibleProposal.getProposalId(), targetVersion);
    ProposalAttributeLocalServiceUtil.getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME, 0L)

    * */

    @RequestMapping(value = "/proposalAttributes", method = RequestMethod.POST)
    public ProposalAttribute createProposalAttribute(@RequestBody ProposalAttribute proposalAttribute, @RequestParam Long authorId) {
        return this.proposalAttributeService.setAttribute(proposalAttribute, authorId);
    }

    @RequestMapping(value = "/proposalAttributes/{proposalAttributeId}", method = RequestMethod.GET)
    public ProposalAttribute getProposalAttribute(@PathVariable("proposalAttributeId") Long proposalAttributeId) throws NotFoundException {
        if (proposalAttributeId == null || proposalAttributeId == 0) {
            throw new NotFoundException("No proposalAttributeId given");
        } else {
            return proposalAttributeDao.get(proposalAttributeId);
        }
    }

    @RequestMapping(value = "/proposalAttributes/{id_}", method = RequestMethod.PUT)
    public boolean updateProposalAttribute(@RequestBody ProposalAttribute proposalAttribute,
                                           @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || proposalAttributeDao.get(id_) == null) {
            throw new NotFoundException("No ProposalAttribute with id " + id_);
        } else {
            return proposalAttributeDao.update(proposalAttribute);
        }
    }

    @RequestMapping(value = "/proposalAttributes/{id_}", method = RequestMethod.DELETE)
    public String deleteProposalAttribute(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No ProposalAttribute with id given");
        } else {
            ProposalAttribute proposalAttribute = this.proposalAttributeDao.get(id_);
            if (proposalAttribute != null) {
                this.proposalAttributeDao.delete(proposalAttribute.getId_());
                return "ProposalAttribute deleted successfully";
            } else {
                throw new NotFoundException("No ProposalAttribute with id given");
            }
        }
    }


    @RequestMapping(value = "/proposalAttributes/getImpactProposalAttributes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalAttribute> getImpactProposalAttributes(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Integer version) {
        return this.proposalAttributeDao.findByProposalIdVersionAndImpact(proposalId,version);
    }

    @RequestMapping(value = "/proposalAttributes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalAttribute> getProposalAttributes(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long additionalId,
            @RequestParam(required = false) Integer version
    ) {
        return proposalAttributeDao.findByGiven(proposalId, name, additionalId, version);
    }



}
