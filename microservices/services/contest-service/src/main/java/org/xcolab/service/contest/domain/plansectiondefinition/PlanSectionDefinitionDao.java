package org.xcolab.service.contest.domain.plansectiondefinition;

import org.xcolab.model.tables.pojos.PlanSectionDefinition;
import org.xcolab.service.contest.exceptions.NotFoundException;


import java.util.List;

public interface PlanSectionDefinitionDao {
    List<PlanSectionDefinition> findByGiven(Long planTemplateId, Boolean weight);
    PlanSectionDefinition get(Long id_) throws NotFoundException;
    PlanSectionDefinition create(PlanSectionDefinition planSectionDefinition);
    boolean update(PlanSectionDefinition planSectionDefinition);
    int delete(Long id_);
}
