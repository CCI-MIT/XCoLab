package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.proposal.service.proposalattribute.ProposalAttributeHelperData;
import org.xcolab.service.proposal.service.proposalattribute.ProposalAttributeService;
import org.xcolab.service.proposal.service.proposalattribute.ProposalUnversionedAttributeHelperData;
import org.xcolab.commons.spring.web.annotation.ListMapping;

import java.util.List;

@RestController
public class ProposalAttributeController {

    private final ProposalAttributeDao proposalAttributeDao;

    private final ProposalAttributeService proposalAttributeService;

    @Autowired
    public ProposalAttributeController(ProposalAttributeDao proposalAttributeDao,
            ProposalAttributeService proposalAttributeService) {
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposalAttributeService = proposalAttributeService;
    }

    @PostMapping("/proposalAttributes/setProposalAttribute")
    public ProposalAttribute createProposalAttribute(
            @RequestBody ProposalAttribute proposalAttribute, @RequestParam Long authorUserid) {
        if (proposalAttribute.getAdditionalId() == null) {
            proposalAttribute.setAdditionalId(0L);
        }
        if (proposalAttribute.getStringValue() == null) {
            proposalAttribute.setStringValue("");
        }
        if (proposalAttribute.getRealValue() == null) {
            proposalAttribute.setRealValue(0.0);
        }
        if (proposalAttribute.getNumericValue() == null) {
            proposalAttribute.setNumericValue(0L);
        }
        return this.proposalAttributeService.setAttribute(proposalAttribute, authorUserid);
    }

    @GetMapping("/proposalAttributes/{proposalAttributeId}")
    public ProposalAttribute getProposalAttribute(
            @PathVariable("proposalAttributeId") Long proposalAttributeId)
            throws NotFoundException {
        if (proposalAttributeId == null || proposalAttributeId == 0) {
            throw new NotFoundException("No proposalAttributeId given");
        } else {
            return proposalAttributeDao.get(proposalAttributeId);
        }
    }

    @PutMapping("/proposalAttributes/{id}")
    public boolean updateProposalAttribute(@RequestBody ProposalAttribute proposalAttribute,
            @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || proposalAttributeDao.get(id) == null) {
            throw new NotFoundException("No ProposalAttribute with id " + id);
        } else {
            return proposalAttributeDao.update(proposalAttribute);
        }
    }

    @DeleteMapping("/proposalAttributes/{id}")
    public String deleteProposalAttribute(@PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No ProposalAttribute with id given");
        } else {
            ProposalAttribute proposalAttribute = this.proposalAttributeDao.get(id);
            if (proposalAttribute != null) {
                this.proposalAttributeDao.delete(proposalAttribute.getId());
                return "ProposalAttribute deleted successfully";
            } else {
                throw new NotFoundException("No ProposalAttribute with id given");
            }
        }
    }

    @ListMapping("/proposalAttributes")
    public List<ProposalAttribute> getProposalAttributes(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long additionalId,
            @RequestParam(required = false) Integer version) {
        return proposalAttributeDao.findByGiven(proposalId, name, additionalId, version);
    }

    @GetMapping("/proposals/{proposalId}/versions/{version}/attributeHelper")
    public ProposalAttributeHelperData getProposalAttributeHelper(@PathVariable long proposalId,
            @PathVariable int version) {
        return proposalAttributeService.getProposalAttributeHelperData(proposalId, version);
    }

    @GetMapping("/proposals/{proposalId}/attributeHelper")
    public ProposalUnversionedAttributeHelperData getProposalUnversionedAttributeHelper(
            @PathVariable long proposalId) {
        return proposalAttributeService.getProposalUnversionedAttributeHelperData(proposalId);
    }
}
