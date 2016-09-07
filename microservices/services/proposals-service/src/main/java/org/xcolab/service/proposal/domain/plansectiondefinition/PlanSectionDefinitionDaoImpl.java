package org.xcolab.service.proposal.domain.plansectiondefinition;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.PlanSectionDefinition;

import java.util.List;

import static org.xcolab.model.Tables.PLAN_SECTION_DEFINITION;
import static org.xcolab.model.Tables.PLAN_TEMPLATE_SECTION;

@Repository
public class PlanSectionDefinitionDaoImpl implements PlanSectionDefinitionDao{

    @Autowired
    private DSLContext dslContext;


    @Override
    public List<PlanSectionDefinition> findByGiven(Long planTemplateId, Boolean weight) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PLAN_SECTION_DEFINITION)
                .join(PLAN_TEMPLATE_SECTION).on(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID.eq(planTemplateId))
                .getQuery();

        if (planTemplateId != null) {
            query.addConditions(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID.eq(planTemplateId));
        }
        if (weight != null && weight) {
            query.addOrderBy(PLAN_TEMPLATE_SECTION.WEIGHT);
        }
        return query.fetchInto(PlanSectionDefinition.class);
    }
}
