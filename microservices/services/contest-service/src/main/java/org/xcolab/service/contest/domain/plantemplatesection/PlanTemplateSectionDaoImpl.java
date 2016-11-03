package org.xcolab.service.contest.domain.plantemplatesection;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.PlanTemplateSection;

import java.util.List;


import static org.xcolab.model.Tables.PLAN_TEMPLATE_SECTION;

@Repository
public class PlanTemplateSectionDaoImpl implements PlanTemplateSectionDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<PlanTemplateSection> findByGiven(Long planTemplateId, Long planSectionId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PLAN_TEMPLATE_SECTION).getQuery();

        if (planTemplateId != null) {
            query.addConditions(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID.eq(planTemplateId));
        }

        if (planSectionId != null) {
            query.addConditions(PLAN_TEMPLATE_SECTION.PLAN_SECTION_ID.eq(planSectionId));
        }

        return query.fetchInto(PlanTemplateSection.class);
    }

    @Override
    public int delete(Long planTemplateId, Long planSectionDefinitionId) {
        return dslContext.deleteFrom(PLAN_TEMPLATE_SECTION)
                .where(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID.eq(planTemplateId))
                .and(PLAN_TEMPLATE_SECTION.PLAN_SECTION_ID.eq(planSectionDefinitionId))
                .execute();
    }

    public boolean update(PlanTemplateSection planTemplateSection) {
        return dslContext.update(PLAN_TEMPLATE_SECTION)
                .set(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID, planTemplateSection.getPlanTemplateId())
                .set(PLAN_TEMPLATE_SECTION.PLAN_SECTION_ID, planTemplateSection.getPlanSectionId())
                .set(PLAN_TEMPLATE_SECTION.WEIGHT, planTemplateSection.getWeight())
                .where(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID.eq(planTemplateSection.getPlanTemplateId()))
                .execute() > 0;
    }



}
