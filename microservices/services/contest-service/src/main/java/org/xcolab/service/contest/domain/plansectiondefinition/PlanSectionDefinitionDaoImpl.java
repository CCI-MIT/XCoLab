package org.xcolab.service.contest.domain.plansectiondefinition;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.PlanSectionDefinition;
import org.xcolab.model.tables.records.PlanSectionDefinitionRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PLAN_SECTION_DEFINITION;
import static org.xcolab.model.Tables.PLAN_TEMPLATE_SECTION;

@Repository
public class PlanSectionDefinitionDaoImpl implements PlanSectionDefinitionDao {

    private final DSLContext dslContext;

    @Autowired
    public PlanSectionDefinitionDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext is required");
        this.dslContext = dslContext;
    }

    @Override
    public List<PlanSectionDefinition> findByGiven(Long planTemplateId, Boolean weight) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PLAN_SECTION_DEFINITION)
                .join(PLAN_TEMPLATE_SECTION).on(PLAN_TEMPLATE_SECTION.PLAN_SECTION_ID.eq(PLAN_SECTION_DEFINITION.ID))
                .getQuery();

        if (planTemplateId != null) {
            query.addConditions(PLAN_TEMPLATE_SECTION.PLAN_TEMPLATE_ID.eq(planTemplateId));
        }
        if (weight != null && weight) {
            query.addOrderBy(PLAN_TEMPLATE_SECTION.WEIGHT);
        }
        return query.fetchInto(PlanSectionDefinition.class);
    }

    @Override
    public PlanSectionDefinition get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PLAN_SECTION_DEFINITION)
                .where(PLAN_SECTION_DEFINITION.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("PlanSectionDefinition with id " + id + " does not exist");
        }
        return record.into(PlanSectionDefinition.class);

    }

    @Override
    public PlanSectionDefinition create(PlanSectionDefinition planSectionDefinition) {

        PlanSectionDefinitionRecord ret = this.dslContext.insertInto(PLAN_SECTION_DEFINITION)
                .set(PLAN_SECTION_DEFINITION.TYPE_, planSectionDefinition.getType_())
                .set(PLAN_SECTION_DEFINITION.ADMIN_TITLE, planSectionDefinition.getAdminTitle())
                .set(PLAN_SECTION_DEFINITION.TITLE, planSectionDefinition.getTitle())
                .set(PLAN_SECTION_DEFINITION.DEFAULT_TEXT, planSectionDefinition.getDefaultText())
                .set(PLAN_SECTION_DEFINITION.HELP_TEXT, planSectionDefinition.getHelpText())
                .set(PLAN_SECTION_DEFINITION.CHARACTER_LIMIT, planSectionDefinition.getCharacterLimit())
                .set(PLAN_SECTION_DEFINITION.FOCUS_AREA_ID, planSectionDefinition.getFocusAreaId())
                .set(PLAN_SECTION_DEFINITION.TIER, planSectionDefinition.getTier())
                .set(PLAN_SECTION_DEFINITION.ALLOWED_CONTEST_TYPE_IDS, planSectionDefinition.getAllowedContestTypeIds())
                .set(PLAN_SECTION_DEFINITION.ALLOWED_VALUES, planSectionDefinition.getAllowedValues())
                .set(PLAN_SECTION_DEFINITION.ADDITIONAL_IDS, planSectionDefinition.getAdditionalIds())
                .set(PLAN_SECTION_DEFINITION.LOCKED, planSectionDefinition.getLocked())
                .set(PLAN_SECTION_DEFINITION.CONTEST_INTEGRATION_RELEVANCE, planSectionDefinition.getContestIntegrationRelevance())
                .set(PLAN_SECTION_DEFINITION.INCLUDE_IN_JUDGING_REPORT, planSectionDefinition.getIncludeInJudgingReport())
                .returning(PLAN_SECTION_DEFINITION.ID)
                .fetchOne();
        if (ret != null) {
            planSectionDefinition.setId(ret.getValue(PLAN_SECTION_DEFINITION.ID));
            return planSectionDefinition;
        } else {
            return null;
        }

    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(PLAN_SECTION_DEFINITION)
                .where(PLAN_SECTION_DEFINITION.ID.eq(id))
                .execute();
    }

    @Override
    public boolean update(PlanSectionDefinition planSectionDefinition) {
        return dslContext.update(PLAN_SECTION_DEFINITION)
                .set(PLAN_SECTION_DEFINITION.TYPE_, planSectionDefinition.getType_())
                .set(PLAN_SECTION_DEFINITION.ADMIN_TITLE, planSectionDefinition.getAdminTitle())
                .set(PLAN_SECTION_DEFINITION.TITLE, planSectionDefinition.getTitle())
                .set(PLAN_SECTION_DEFINITION.DEFAULT_TEXT, planSectionDefinition.getDefaultText())
                .set(PLAN_SECTION_DEFINITION.HELP_TEXT, planSectionDefinition.getHelpText())
                .set(PLAN_SECTION_DEFINITION.CHARACTER_LIMIT, planSectionDefinition.getCharacterLimit())
                .set(PLAN_SECTION_DEFINITION.FOCUS_AREA_ID, planSectionDefinition.getFocusAreaId())
                .set(PLAN_SECTION_DEFINITION.TIER, planSectionDefinition.getTier())
                .set(PLAN_SECTION_DEFINITION.ALLOWED_CONTEST_TYPE_IDS, planSectionDefinition.getAllowedContestTypeIds())
                .set(PLAN_SECTION_DEFINITION.ALLOWED_VALUES, planSectionDefinition.getAllowedValues())
                .set(PLAN_SECTION_DEFINITION.ADDITIONAL_IDS, planSectionDefinition.getAdditionalIds())
                .set(PLAN_SECTION_DEFINITION.LOCKED, planSectionDefinition.getLocked())
                .set(PLAN_SECTION_DEFINITION.CONTEST_INTEGRATION_RELEVANCE, planSectionDefinition.getContestIntegrationRelevance())
                .set(PLAN_SECTION_DEFINITION.INCLUDE_IN_JUDGING_REPORT, planSectionDefinition.getIncludeInJudgingReport())
                .where(PLAN_SECTION_DEFINITION.ID.eq(planSectionDefinition.getId()))
                .execute() > 0;
    }
}
