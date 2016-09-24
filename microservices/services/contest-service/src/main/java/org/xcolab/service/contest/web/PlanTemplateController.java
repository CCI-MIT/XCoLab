package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.PlanSectionDefinition;
import org.xcolab.model.tables.pojos.PlanTemplate;
import org.xcolab.model.tables.pojos.PlanTemplateSection;
import org.xcolab.service.contest.domain.plansectiondefinition.PlanSectionDefinitionDao;
import org.xcolab.service.contest.domain.plantemplate.PlanTemplateDao;
import org.xcolab.service.contest.domain.plantemplatesection.PlanTemplateSectionDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class PlanTemplateController {

    @Autowired
    private PlanSectionDefinitionDao planSectionDefinitionDao;

    @Autowired
    private PlanTemplateDao planTemplateDao;

    @Autowired
    private PlanTemplateSectionDao planTemplateSectionDao;




    @RequestMapping(value = "/planTemplates", method = RequestMethod.POST)
    public PlanTemplate createPlanTemplate(@RequestBody PlanTemplate planTemplate) {
        return this.planTemplateDao.create(planTemplate);
    }

    @RequestMapping(value = "/planTemplates/{id_}", method = RequestMethod.PUT)
    public boolean updatePlanTemplate(@RequestBody PlanTemplate planTemplate,
                                      @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || planTemplateDao.get(id_) == null) {
            throw new NotFoundException("No PlanTemplate with id " + id_);
        } else {
            return planTemplateDao.update(planTemplate);
        }
    }


    @RequestMapping(value = "/planTemplates/{planTemplateId}", method = RequestMethod.GET)
    public PlanTemplate getPlanTemplate(@PathVariable("planTemplateId") Long planTemplateId) throws NotFoundException {
        if (planTemplateId == null || planTemplateId == 0) {
            throw new NotFoundException("No planTemplateId given");
        } else {
            return planTemplateDao.get(planTemplateId);
        }
    }

    @RequestMapping(value = "/planTemplates", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PlanTemplate> getPlanTemplates() {
        return planTemplateDao.findByGiven();
    }




    @RequestMapping(value = "/planSectionDefinition/{planSectionDefinitionId}", method = RequestMethod.GET)
    public PlanSectionDefinition getPlanSectionDefinition(@PathVariable("planSectionDefinitionId") Long planSectionDefinitionId) throws NotFoundException {
        if (planSectionDefinitionId == null || planSectionDefinitionId == 0) {
            throw new NotFoundException("No planSectionDefinitionId given");
        } else {
            return planSectionDefinitionDao.get(planSectionDefinitionId);
        }
    }

    @RequestMapping(value = "/planSectionDefinitions/{id_}", method = RequestMethod.DELETE)
    public String deletePlanSectionDefinition(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No PlanSectionDefinition with id given");
        } else {
            PlanSectionDefinition planSectionDefinition = this.planSectionDefinitionDao.get(id_);
            if (planSectionDefinition != null) {
                this.planSectionDefinitionDao.delete(planSectionDefinition.getId_());
                return "PlanSectionDefinition deleted successfully";
            } else {
                throw new NotFoundException("No PlanSectionDefinition with id given");
            }
        }
    }

    @RequestMapping(value = "/planSectionDefinitions/{id_}", method = RequestMethod.PUT)
    public boolean updatePlanSectionDefinition(@RequestBody PlanSectionDefinition planSectionDefinition,
                                               @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || planSectionDefinitionDao.get(id_) == null) {
            throw new NotFoundException("No PlanSectionDefinition with id " + id_);
        } else {
            return planSectionDefinitionDao.update(planSectionDefinition);
        }
    }
    @RequestMapping(value = "/planSectionDefinitions", method = RequestMethod.POST)
    public PlanSectionDefinition createPlanSectionDefinition(@RequestBody PlanSectionDefinition planSectionDefinition) {
        return this.planSectionDefinitionDao.create(planSectionDefinition);
    }




    @RequestMapping(value = "/planSectionDefinitions", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PlanSectionDefinition> getPlanSectionDefinitions(
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) Boolean weight
    ) {
        return planSectionDefinitionDao.findByGiven(planTemplateId, weight);
    }

    @RequestMapping(value = "/planTemplateSections", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PlanTemplateSection> getPlanTemplateSections(
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) Long planSectionId
    ) {
        return planTemplateSectionDao.findByGiven(planTemplateId,planSectionId);
    }
    @RequestMapping(value = "/planTemplateSections/updateTemplateSection", method = RequestMethod.PUT)
    public boolean updatePlanTemplateSection(@RequestBody PlanTemplateSection planTemplateSection) throws NotFoundException {
            return planTemplateSectionDao.update(planTemplateSection);
    }



    @RequestMapping(value = "/planTemplateSections/deletePlanTemplateSection", method = RequestMethod.DELETE)
    public Boolean deletePlanTemplateSection(@RequestParam Long planTemplateId, @RequestParam Long planSectionDefinitionId)
            throws NotFoundException {
                return this.planTemplateSectionDao.delete(planTemplateId,planSectionDefinitionId)> 0;
    }



}
