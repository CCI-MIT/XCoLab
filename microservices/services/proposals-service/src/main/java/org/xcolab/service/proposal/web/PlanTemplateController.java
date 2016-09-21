package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.PlanSectionDefinition;
import org.xcolab.model.tables.pojos.PlanTemplate;
import org.xcolab.service.proposal.domain.plansectiondefinition.PlanSectionDefinitionDao;
import org.xcolab.service.proposal.domain.plantemplate.PlanTemplateDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

@RestController
public class PlanTemplateController {

    @Autowired
    private PlanTemplateDao planTemplateDao;

    @Autowired
    private PlanSectionDefinitionDao planSectionDefinitionDao;



    @RequestMapping(value = "/planTemplates/{planTemplateId}", method = RequestMethod.GET)
    public PlanTemplate getPlanTemplate(@PathVariable("planTemplateId") Long planTemplateId) throws NotFoundException {
        if (planTemplateId == null || planTemplateId == 0) {
            throw new NotFoundException("No planTemplateId given");
        } else {
            return planTemplateDao.get(planTemplateId);
        }
    }
    @RequestMapping(value = "/planSectionDefinitions", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PlanSectionDefinition> getPlanSectionDefinitions(
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) Boolean weight
    ) {
        return planSectionDefinitionDao.findByGiven(planTemplateId, weight);
    }

    @RequestMapping(value = "/planSectionDefinitions/{planSectionDefinitionId}", method = RequestMethod.GET)
    public PlanSectionDefinition getPlanSectionDefinition(@PathVariable("planSectionDefinitionId") Long planSectionDefinitionId) throws NotFoundException {
        if (planSectionDefinitionId == null || planSectionDefinitionId == 0) {
            throw new NotFoundException("No planSectionDefinitionId given");
        } else {
            return planSectionDefinitionDao.get(planSectionDefinitionId);
        }
    }
}
