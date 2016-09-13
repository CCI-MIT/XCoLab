package org.xcolab.service.proposal.domain.plansectiondefinition;

import org.xcolab.model.tables.pojos.PlanSectionDefinition;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface PlanSectionDefinitionDao {
    List<PlanSectionDefinition> findByGiven(Long planTemplateId, Boolean weight);
    PlanSectionDefinition get(Long id_) throws NotFoundException;
}
