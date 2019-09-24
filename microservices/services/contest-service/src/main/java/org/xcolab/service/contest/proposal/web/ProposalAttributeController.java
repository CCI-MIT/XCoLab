package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttributeHelperDataDto;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttributeHelperDataDto;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.contest.proposal.domain.proposalunversionedattribute.ProposalUnversionedAttributeDao;
import org.xcolab.service.contest.proposal.service.proposalattribute.ProposalAttributeService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ProposalAttributeController implements IProposalAttributeClient {

    private final ProposalAttributeDao proposalAttributeDao;
    private final ProposalUnversionedAttributeDao proposalUnversionedAttributeDao;

    private final ProposalAttributeService proposalAttributeService;

    @Autowired
    public ProposalAttributeController(ProposalAttributeDao proposalAttributeDao,
            ProposalAttributeService proposalAttributeService,
            ProposalUnversionedAttributeDao proposalUnversionedAttributeDao) {
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposalAttributeService = proposalAttributeService;
        this.proposalUnversionedAttributeDao = proposalUnversionedAttributeDao;
    }

    @Override
    @PostMapping("/proposalAttributes/setProposalAttribute")
    public ProposalAttribute setProposalAttribute(@RequestBody ProposalAttribute proposalAttribute,
            @RequestParam Long authorUserId) {
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
        return this.proposalAttributeService.setAttribute(proposalAttribute, authorUserId);
    }

    @Override
    @GetMapping("/proposalAttributes/{proposalAttributeId}")
    public ProposalAttribute getProposalAttribute(@PathVariable Long proposalAttributeId)
            throws ProposalAttributeNotFoundException {
        try {
            if (!(proposalAttributeId == null || proposalAttributeId == 0)) {
                return proposalAttributeDao.get(proposalAttributeId);
            }
        } catch (NotFoundException e) {}
        throw new ProposalAttributeNotFoundException(proposalAttributeId);
    }

    @Override
    @PutMapping("/proposalAttributes")
    public boolean updateProposalAttribute(@RequestBody ProposalAttribute proposalAttribute) {
        Long id = proposalAttribute.getId();
        try {
            if (!(id == null || id == 0 || proposalAttributeDao.get(id) == null)) {
                return proposalAttributeDao.update(proposalAttribute);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("ProposalAttribute not found with id " + id);
    }

    @Override
    @DeleteMapping("/proposalAttributes/{id}")
    public boolean deleteProposalAttribute(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new RuntimeEntityNotFoundException("ProposalAttribute not found with id " + id);
        }
        try {
            ProposalAttribute proposalAttribute = this.proposalAttributeDao.get(id);
            if (proposalAttribute != null) {
                this.proposalAttributeDao.delete(proposalAttribute.getId());
                return true;
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("ProposalAttribute not found with id " + id);
    }

    @Override
    @GetMapping("/proposalAttributes")
    public List<ProposalAttribute> getProposalAttributes(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long additionalId,
            @RequestParam(required = false) Integer version) {
        return proposalAttributeDao.findByGiven(proposalId, name, additionalId, version);
    }

    @Override
    @GetMapping("/proposals/{proposalId}/versions/{version}/attributeHelper")
    public ProposalAttributeHelperDataDto getProposalAttributeHelperData(
            @PathVariable Long proposalId, @PathVariable Integer version) {
        return proposalAttributeService.getProposalAttributeHelperData(proposalId, version);
    }

    @GetMapping("/proposals/{proposalId}/attributeHelper")
    public ProposalUnversionedAttributeHelperDataDto getProposalUnversionedAttributeHelperData(
            @PathVariable Long proposalId) {
        return proposalAttributeService.getProposalUnversionedAttributeHelperData(proposalId);
    }

    @Override
    @PostMapping("/proposalUnversionedAttributes")
    public ProposalUnversionedAttribute createProposalUnversionedAttribute(
            @RequestBody ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return this.proposalUnversionedAttributeDao.create(proposalUnversionedAttribute);
    }

    @Override
    @PutMapping("/proposalUnversionedAttributes")
    public boolean updateProposalUnversionedAttribute(
            @RequestBody ProposalUnversionedAttribute proposalUnversionedAttribute) {
        Long id = proposalUnversionedAttribute.getId();
        try {
            if (!(id == null || id == 0 || proposalUnversionedAttributeDao.get(id) == null)) {
                return proposalUnversionedAttributeDao.update(proposalUnversionedAttribute);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException(
                "ProposalUnversionedAttribute not found with id " + id);
    }

    @Override
    @GetMapping("/proposalUnversionedAttributes/getByProposalIdName")
    public ProposalUnversionedAttribute getProposalUnversionedAttribute(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) String name) throws EntityNotFoundException {
        ProposalUnversionedAttribute rt =
                proposalUnversionedAttributeDao.getByProposalIdName(proposalId, name);
        if (rt != null) {
            return rt;
        }
        throw new EntityNotFoundException(
                "ProposalUnversionedAttribute not found with id " + proposalId + " and name "
                        + name);
    }

    @GetMapping("/proposalUnversionedAttributes")
    public List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            @RequestParam(required = false) Long proposalId) {
        return proposalUnversionedAttributeDao.findByGiven(proposalId);
    }

    @Override
    @DeleteMapping("/proposalUnversionedAttributes/{id}")
    public boolean deleteProposalUnversionedAttribute(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalUnversionedAttribute not found with id " + id);
        }
        try {
            ProposalUnversionedAttribute proposalUnversionedAttribute =
                    this.proposalUnversionedAttributeDao.get(id);
            if (proposalUnversionedAttribute != null) {
                this.proposalUnversionedAttributeDao.delete(proposalUnversionedAttribute.getId());
                return true;
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException(
                "ProposalUnversionedAttribute not found with id " + id);
    }
}
