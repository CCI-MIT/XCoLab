package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.proposals.exceptions.ProposalTemplateNotFoundException;
import org.xcolab.service.contest.domain.proposaltemplate.ProposalTemplateDao;
import org.xcolab.service.contest.domain.proposaltemplatesection.ProposalTemplateSectionDao;
import org.xcolab.service.contest.domain.proposaltemplatesectiondefinition.ProposalTemplateSectionDefinitionDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.util.List;

@RestController
public class ProposalTemplateController implements IProposalTemplateClient {

    private final ProposalTemplateSectionDefinitionDao proposalTemplateSectionDefinitionDao;
    private final ProposalTemplateDao proposalTemplateDao;
    private final ProposalTemplateSectionDao proposalTemplateSectionDao;

    @Autowired
    public ProposalTemplateController(
            ProposalTemplateSectionDefinitionDao proposalTemplateSectionDefinitionDao,
            ProposalTemplateDao proposalTemplateDao,
            ProposalTemplateSectionDao proposalTemplateSectionDao) {
        this.proposalTemplateSectionDefinitionDao = proposalTemplateSectionDefinitionDao;
        this.proposalTemplateDao = proposalTemplateDao;
        this.proposalTemplateSectionDao = proposalTemplateSectionDao;
    }

    @Override
    @PostMapping("/proposalTemplates")
    public IProposalTemplate createProposalTemplate(
            @RequestBody IProposalTemplate proposalTemplate) {
        return this.proposalTemplateDao.create(proposalTemplate);
    }

    @Override
    @PutMapping("/proposalTemplates")
    public boolean updateProposalTemplate(@RequestBody IProposalTemplate proposalTemplate) {
        Long id = proposalTemplate.getId();
        try {
            if (!(id == null || id == 0 || proposalTemplateDao.get(id) == null)) {
                return proposalTemplateDao.update(proposalTemplate);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("No ProposalTemplate with id " + id);
    }

    @Override
    @GetMapping("/proposalTemplates/{proposalTemplateId}")
    public IProposalTemplate getProposalTemplate(@PathVariable Long proposalTemplateId) {
        if (proposalTemplateId == null || proposalTemplateId == 0) {
            throw new ProposalTemplateNotFoundException(proposalTemplateId);
        } else {
            try {
                return proposalTemplateDao.get(proposalTemplateId);
            } catch (NotFoundException e) {
                throw new ProposalTemplateNotFoundException(proposalTemplateId);
            }
        }
    }

    @Override
    @GetMapping("/proposalTemplates")
    public List<IProposalTemplate> getProposalTemplates() {
        return proposalTemplateDao.findByGiven();
    }

    @Override
    @DeleteMapping("/proposalTemplates/{id}")
    public boolean deleteProposalTemplate(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new RuntimeEntityNotFoundException("ProposalTemplate not found with id " + id);
        }
        try {
            IProposalTemplate proposalTemplate = this.proposalTemplateDao.get(id);
            if (proposalTemplate != null) {
                this.proposalTemplateDao.delete(proposalTemplate.getId());
                return true;
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("ProposalTemplate not found with id " + id);
    }

    @Override
    @PostMapping("/proposalTemplateSections")
    public IProposalTemplateSection createProposalTemplateSection(
            @RequestBody IProposalTemplateSection proposalTemplateSection) {
        return this.proposalTemplateSectionDao.create(proposalTemplateSection);
    }

    @Override
    @GetMapping("/proposalTemplateSectionDefinitions/{proposalTemplateSectionDefinitionId}")
    public ProposalTemplateSectionDefinitionWrapper getProposalTemplateSectionDefinition(
            @PathVariable Long proposalTemplateSectionDefinitionId) {
        if (proposalTemplateSectionDefinitionId == null
                || proposalTemplateSectionDefinitionId == 0) {
            throw new RuntimeEntityNotFoundException(
                    "No proposalTemplateSectionDefinitionId given");
        }
        try {
            return proposalTemplateSectionDefinitionDao.get(proposalTemplateSectionDefinitionId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalTemplateSectionDefinition not found with id "
                            + proposalTemplateSectionDefinitionId);
        }
    }

    @Override
    @DeleteMapping("/proposalTemplateSectionDefinitions/{id}")
    public boolean deleteProposalTemplateSectionDefinition(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new RuntimeEntityNotFoundException(
                    "ProposalTemplateSectionDefinition not found with id " + id);
        }
        try {
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition =
                    this.proposalTemplateSectionDefinitionDao.get(id);
            if (proposalTemplateSectionDefinition != null) {
                this.proposalTemplateSectionDefinitionDao
                        .delete(proposalTemplateSectionDefinition.getId());
                return true;
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException(
                "ProposalTemplateSectionDefinition not found with id " + id);
    }

    @Override
    @PutMapping("/proposalTemplateSectionDefinitions")
    public boolean updateProposalTemplateSectionDefinition(@RequestBody
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        Long id = proposalTemplateSectionDefinition.getId();
        try {
            if (!(id == null || id == 0 || proposalTemplateSectionDefinitionDao.get(id) == null)) {
                return proposalTemplateSectionDefinitionDao
                        .update(proposalTemplateSectionDefinition);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException(
                "No ProposalTemplateSectionDefinition with id " + id);
    }

    @Override
    @PostMapping("/proposalTemplateSectionDefinitions")
    public ProposalTemplateSectionDefinitionWrapper createProposalTemplateSectionDefinition(
            @RequestBody
                    ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        if (proposalTemplateSectionDefinition.isLocked() == null) {
            proposalTemplateSectionDefinition.setLocked(false);
        }
        return this.proposalTemplateSectionDefinitionDao.create(proposalTemplateSectionDefinition);
    }

    @Override
    @GetMapping("/proposalTemplateSectionDefinitions")
    public List<ProposalTemplateSectionDefinitionWrapper> getProposalTemplateSectionDefinitionByProposalTemplateId(
            @RequestParam(required = false) Long proposalTemplateId,
            @RequestParam(required = false) Boolean weight) {
        return proposalTemplateSectionDefinitionDao.findByGiven(proposalTemplateId, weight);
    }

    @Override
    @GetMapping("/proposalTemplateSections")
    public List<IProposalTemplateSection> getProposalTemplateSections(
            @RequestParam(required = false) Long proposalTemplateId,
            @RequestParam(required = false) Long planSectionId) {
        return proposalTemplateSectionDao.findByGiven(proposalTemplateId, planSectionId);
    }

    @Override
    @PostMapping("/proposalTemplateSections/updateTemplateSection")
    public boolean updateProposalTemplateSection(
            @RequestBody IProposalTemplateSection proposalTemplateSection) {
        return proposalTemplateSectionDao.update(proposalTemplateSection);
    }

    @Override
    @DeleteMapping("/proposalTemplateSections/deleteProposalTemplateSection")
    public boolean deleteProposalTemplateSection(@RequestParam Long proposalTemplateId,
            @RequestParam Long proposalTemplateSectionDefinitionId) {
        return this.proposalTemplateSectionDao
                .delete(proposalTemplateId, proposalTemplateSectionDefinitionId) > 0;
    }
}
