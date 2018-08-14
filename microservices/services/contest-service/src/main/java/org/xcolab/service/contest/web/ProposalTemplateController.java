package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.commons.spring.web.annotation.ListMapping;
import org.xcolab.model.tables.pojos.ProposalTemplate;
import org.xcolab.model.tables.pojos.ProposalTemplateSection;
import org.xcolab.model.tables.pojos.ProposalTemplateSectionDefinition;
import org.xcolab.service.contest.domain.proposaltemplatesectiondefinition.ProposalTemplateSectionDefinitionDao;
import org.xcolab.service.contest.domain.proposaltemplate.ProposalTemplateDao;
import org.xcolab.service.contest.domain.proposaltemplatesection.ProposalTemplateSectionDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ProposalTemplateController {

    @Autowired
    private ProposalTemplateSectionDefinitionDao proposalTemplateSectionDefinitionDao;

    @Autowired
    private ProposalTemplateDao proposalTemplateDao;

    @Autowired
    private ProposalTemplateSectionDao proposalTemplateSectionDao;


    @RequestMapping(value = "/proposalTemplates", method = RequestMethod.POST)
    public ProposalTemplate createProposalTemplate(@RequestBody ProposalTemplate proposalTemplate) {

        return this.proposalTemplateDao.create(proposalTemplate);
    }

    @RequestMapping(value = "/proposalTemplates/{id}", method = RequestMethod.PUT)
    public boolean updateProposalTemplate(@RequestBody ProposalTemplate proposalTemplate,
                                      @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || proposalTemplateDao.get(id) == null) {
            throw new NotFoundException("No ProposalTemplate with id " + id);
        } else {
            return proposalTemplateDao.update(proposalTemplate);
        }
    }


    @RequestMapping(value = "/proposalTemplates/{proposalTemplateId}", method = RequestMethod.GET)
    public ProposalTemplate getProposalTemplate(@PathVariable("proposalTemplateId") Long proposalTemplateId) throws NotFoundException {
        if (proposalTemplateId == null || proposalTemplateId == 0) {
            throw new NotFoundException("No proposalTemplateId given");
        } else {
            return proposalTemplateDao.get(proposalTemplateId);
        }
    }

    @RequestMapping(value = "/proposalTemplates", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalTemplate> getProposalTemplates() {
        return proposalTemplateDao.findByGiven();
    }

    @RequestMapping(value = "/proposalTemplates/{id}", method = RequestMethod.DELETE)
    public String deleteProposalTemplate(@PathVariable("id") Long id)
            throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No ProposalTemplate with id given");
        } else {
            ProposalTemplate proposalTemplate = this.proposalTemplateDao.get(id);
            if (proposalTemplate != null) {
                this.proposalTemplateDao.delete(proposalTemplate.getId());
                return "ProposalTemplate deleted successfully";
            } else {
                throw new NotFoundException("No ProposalTemplate with id given");
            }
        }
    }

    @RequestMapping(value = "/proposalTemplateSections", method = RequestMethod.POST)
    public ProposalTemplateSection createProposalTemplateSection(@RequestBody ProposalTemplateSection proposalTemplateSection) {
        return this.proposalTemplateSectionDao.create(proposalTemplateSection);
    }

    @RequestMapping(value = "/proposalTemplateSectionDefinitions/{proposalTemplateSectionDefinitionId}", method = RequestMethod.GET)
    public ProposalTemplateSectionDefinition getProposalTemplateSectionDefinition(@PathVariable("proposalTemplateSectionDefinitionId") Long proposalTemplateSectionDefinitionId) throws NotFoundException {
        if (proposalTemplateSectionDefinitionId == null || proposalTemplateSectionDefinitionId == 0) {
            throw new NotFoundException("No proposalTemplateSectionDefinitionId given");
        } else {
            return proposalTemplateSectionDefinitionDao.get(proposalTemplateSectionDefinitionId);
        }
    }

    @RequestMapping(value = "/proposalTemplateSectionDefinitions/{id}", method = RequestMethod.DELETE)
    public String deleteProposalTemplateSectionDefinition(@PathVariable("id") Long id)
            throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No ProposalTemplateSectionDefinition with id given");
        } else {
            ProposalTemplateSectionDefinition proposalTemplateSectionDefinition = this.proposalTemplateSectionDefinitionDao.get(id);
            if (proposalTemplateSectionDefinition != null) {
                this.proposalTemplateSectionDefinitionDao.delete(proposalTemplateSectionDefinition.getId());
                return "ProposalTemplateSectionDefinition deleted successfully";
            } else {
                throw new NotFoundException("No ProposalTemplateSectionDefinition with id given");
            }
        }
    }

    @RequestMapping(value = "/proposalTemplateSectionDefinitions/{id}", method = RequestMethod.PUT)
    public boolean updateProposalTemplateSectionDefinition(@RequestBody ProposalTemplateSectionDefinition proposalTemplateSectionDefinition,
                                               @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || proposalTemplateSectionDefinitionDao.get(id) == null) {
            throw new NotFoundException("No ProposalTemplateSectionDefinition with id " + id);
        } else {
            return proposalTemplateSectionDefinitionDao.update(proposalTemplateSectionDefinition);
        }
    }

    @RequestMapping(value = "/proposalTemplateSectionDefinitions", method = RequestMethod.POST)
    public ProposalTemplateSectionDefinition createProposalTemplateSectionDefinition(@RequestBody ProposalTemplateSectionDefinition proposalTemplateSectionDefinition) {
        if(proposalTemplateSectionDefinition.getLocked() == null){
            proposalTemplateSectionDefinition.setLocked(false);
        }
        return this.proposalTemplateSectionDefinitionDao.create(proposalTemplateSectionDefinition);
    }

    @ListMapping("/proposalTemplateSectionDefinitions")
    public List<ProposalTemplateSectionDefinition> getProposalTemplateSectionDefinitions(
            @RequestParam(required = false) Long proposalTemplateId,
            @RequestParam(required = false) Boolean weight
    ) {
        return proposalTemplateSectionDefinitionDao.findByGiven(proposalTemplateId, weight);
    }

    @ListMapping("/proposalTemplateSections")
    public List<ProposalTemplateSection> getProposalTemplateSections(
            @RequestParam(required = false) Long proposalTemplateId,
            @RequestParam(required = false) Long planSectionId
    ) {
        return proposalTemplateSectionDao.findByGiven(proposalTemplateId, planSectionId);
    }
    @PostMapping("/proposalTemplateSections/updateTemplateSection")
    public boolean updateProposalTemplateSection(@RequestBody ProposalTemplateSection proposalTemplateSection) throws NotFoundException {
            return proposalTemplateSectionDao.update(proposalTemplateSection);
    }

    @DeleteMapping("/proposalTemplateSections/deleteProposalTemplateSection")
    public Boolean deleteProposalTemplateSection(@RequestParam Long proposalTemplateId, @RequestParam Long proposalTemplateSectionDefinitionId) {
        return this.proposalTemplateSectionDao.delete(proposalTemplateId,proposalTemplateSectionDefinitionId)> 0;
    }
}
