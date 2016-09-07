package org.xcolab.service.proposal.domain.plansectiondefinition;

import org.xcolab.model.tables.pojos.PlanSectionDefinition;

import java.util.List;

public interface PlanSectionDefinitionDao {
    List<PlanSectionDefinition> findByGiven(Long planTemplateId, Boolean weight);
}
