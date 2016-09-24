package org.xcolab.service.contest.domain.plantemplatesection;

import org.xcolab.model.tables.pojos.PlanTemplateSection;

import java.util.List;

public interface PlanTemplateSectionDao {
    List<PlanTemplateSection> findByGiven(Long planTemplateId, Long planSectionId);
    int delete(Long planTemplateId, Long planSectionDefinitionId);
    boolean update(PlanTemplateSection planTemplateSection);
}
