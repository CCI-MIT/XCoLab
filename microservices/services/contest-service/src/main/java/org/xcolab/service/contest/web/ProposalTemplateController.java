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
import org.xcolab.service.contest.domain.plansectiondefinition.ProposalTemplateSectionDefinitionDao;
import org.xcolab.service.contest.domain.plantemplate.ProposalTemplateDao;
import org.xcolab.service.contest.domain.plantemplatesection.ProposalTemplateSectionDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ProposalTemplateController {

    @Autowired
    private ProposalTemplateSectionDefinitionDao planSectionDefinitionDao;

    @Autowired
    private ProposalTemplateDao planTemplateDao;

    @Autowired
    private ProposalTemplateSectionDao planTemplateSectionDao;


    @RequestMapping(value = "/planTemplates", method = RequestMethod.POST)
    public ProposalTemplate createProposalTemplate(@RequestBody ProposalTemplate planTemplate) {

        return this.planTemplateDao.create(planTemplate);
    }

    @RequestMapping(value = "/planTemplates/{id}", method = RequestMethod.PUT)
    public boolean updateProposalTemplate(@RequestBody ProposalTemplate planTemplate,
                                      @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || planTemplateDao.get(id) == null) {
            throw new NotFoundException("No ProposalTemplate with id " + id);
        } else {
            return planTemplateDao.update(planTemplate);
        }
    }


    @RequestMapping(value = "/planTemplates/{planTemplateId}", method = RequestMethod.GET)
    public ProposalTemplate getProposalTemplate(@PathVariable("planTemplateId") Long planTemplateId) throws NotFoundException {
        if (planTemplateId == null || planTemplateId == 0) {
            throw new NotFoundException("No planTemplateId given");
        } else {
            return planTemplateDao.get(planTemplateId);
        }
    }

    @RequestMapping(value = "/planTemplates", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalTemplate> getProposalTemplates() {
        return planTemplateDao.findByGiven();
    }

    @RequestMapping(value = "/planTemplates/{id}", method = RequestMethod.DELETE)
    public String deleteProposalTemplate(@PathVariable("id") Long id)
            throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No ProposalTemplate with id given");
        } else {
            ProposalTemplate planTemplate = this.planTemplateDao.get(id);
            if (planTemplate != null) {
                this.planTemplateDao.delete(planTemplate.getId());
                return "ProposalTemplate deleted successfully";
            } else {
                throw new NotFoundException("No ProposalTemplate with id given");
            }
        }
    }

    @RequestMapping(value = "/planTemplateSections", method = RequestMethod.POST)
    public ProposalTemplateSection createProposalTemplateSection(@RequestBody ProposalTemplateSection planTemplateSection) {
        return this.planTemplateSectionDao.create(planTemplateSection);
    }

    @RequestMapping(value = "/planSectionDefinitions/{planSectionDefinitionId}", method = RequestMethod.GET)
    public ProposalTemplateSectionDefinition getProposalTemplateSectionDefinition(@PathVariable("planSectionDefinitionId") Long planSectionDefinitionId) throws NotFoundException {
        if (planSectionDefinitionId == null || planSectionDefinitionId == 0) {
            throw new NotFoundException("No planSectionDefinitionId given");
        } else {
            return planSectionDefinitionDao.get(planSectionDefinitionId);
        }
    }

    @RequestMapping(value = "/planSectionDefinitions/{id}", method = RequestMethod.DELETE)
    public String deleteProposalTemplateSectionDefinition(@PathVariable("id") Long id)
            throws NotFoundException {

        if (id == null || id == 0) {
            throw new NotFoundException("No ProposalTemplateSectionDefinition with id given");
        } else {
            ProposalTemplateSectionDefinition planSectionDefinition = this.planSectionDefinitionDao.get(id);
            if (planSectionDefinition != null) {
                this.planSectionDefinitionDao.delete(planSectionDefinition.getId());
                return "ProposalTemplateSectionDefinition deleted successfully";
            } else {
                throw new NotFoundException("No ProposalTemplateSectionDefinition with id given");
            }
        }
    }

    @RequestMapping(value = "/planSectionDefinitions/{id}", method = RequestMethod.PUT)
    public boolean updateProposalTemplateSectionDefinition(@RequestBody ProposalTemplateSectionDefinition planSectionDefinition,
                                               @PathVariable("id") Long id) throws NotFoundException {

        if (id == null || id == 0 || planSectionDefinitionDao.get(id) == null) {
            throw new NotFoundException("No ProposalTemplateSectionDefinition with id " + id);
        } else {
            return planSectionDefinitionDao.update(planSectionDefinition);
        }
    }

    @RequestMapping(value = "/planSectionDefinitions", method = RequestMethod.POST)
    public ProposalTemplateSectionDefinition createProposalTemplateSectionDefinition(@RequestBody ProposalTemplateSectionDefinition planSectionDefinition) {
        if(planSectionDefinition.getLocked() == null){
            planSectionDefinition.setLocked(false);
        }
        return this.planSectionDefinitionDao.create(planSectionDefinition);
    }

    @ListMapping("/planSectionDefinitions")
    public List<ProposalTemplateSectionDefinition> getProposalTemplateSectionDefinitions(
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) Boolean weight
    ) {
        return planSectionDefinitionDao.findByGiven(planTemplateId, weight);
    }

    @ListMapping("/planTemplateSections")
    public List<ProposalTemplateSection> getProposalTemplateSections(
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) Long planSectionId
    ) {
        return planTemplateSectionDao.findByGiven(planTemplateId, planSectionId);
    }
    @PostMapping("/planTemplateSections/updateTemplateSection")
    public boolean updateProposalTemplateSection(@RequestBody ProposalTemplateSection planTemplateSection) throws NotFoundException {
            return planTemplateSectionDao.update(planTemplateSection);
    }

    @DeleteMapping("/planTemplateSections/deletePlanTemplateSection")
    public Boolean deleteProposalTemplateSection(@RequestParam Long planTemplateId, @RequestParam Long planSectionDefinitionId) {
        return this.planTemplateSectionDao.delete(planTemplateId,planSectionDefinitionId)> 0;
    }
}
