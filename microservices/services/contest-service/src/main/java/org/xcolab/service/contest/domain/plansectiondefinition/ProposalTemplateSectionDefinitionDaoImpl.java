package org.xcolab.service.contest.domain.plansectiondefinition;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ProposalTemplateSectionDefinition;
import org.xcolab.model.tables.records.ProposalTemplateSectionDefinitionRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_TEMPLATE_SECTION_DEFINITION;
import static org.xcolab.model.Tables.PROPOSAL_TEMPLATE_SECTION;

@Repository
public class ProposalTemplateSectionDefinitionDaoImpl implements ProposalTemplateSectionDefinitionDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalTemplateSectionDefinitionDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext is required");
        this.dslContext = dslContext;
    }

    @Override
    public List<ProposalTemplateSectionDefinition> findByGiven(Long planTemplateId, Boolean weight) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .join(PROPOSAL_TEMPLATE_SECTION).on(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID))
                .getQuery();

        if (planTemplateId != null) {
            query.addConditions(PROPOSAL_TEMPLATE_SECTION.ID.eq(planTemplateId));
        }
        if (weight != null && weight) {
            query.addOrderBy(PROPOSAL_TEMPLATE_SECTION.WEIGHT);
        }
        return query.fetchInto(ProposalTemplateSectionDefinition.class);
    }

    @Override
    public ProposalTemplateSectionDefinition get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .where(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalTemplateSectionDefinition with id " + id + " does not exist");
        }
        return record.into(ProposalTemplateSectionDefinition.class);

    }

    @Override
    public ProposalTemplateSectionDefinition create(ProposalTemplateSectionDefinition planSectionDefinition) {

        ProposalTemplateSectionDefinitionRecord ret = this.dslContext.insertInto(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TYPE, planSectionDefinition.getType())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADMIN_TITLE, planSectionDefinition.getAdminTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TITLE, planSectionDefinition.getTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.DEFAULT_TEXT, planSectionDefinition.getDefaultText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.HELP_TEXT, planSectionDefinition.getHelpText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CHARACTER_LIMIT, planSectionDefinition.getCharacterLimit())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.FOCUS_AREA_ID, planSectionDefinition.getFocusAreaId())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TIER, planSectionDefinition.getTier())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_CONTEST_TYPE_IDS, planSectionDefinition.getAllowedContestTypeIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_VALUES, planSectionDefinition.getAllowedValues())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADDITIONAL_IDS, planSectionDefinition.getAdditionalIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.LOCKED, planSectionDefinition.getLocked())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CONTEST_INTEGRATION_RELEVANCE, planSectionDefinition.getContestIntegrationRelevance())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.INCLUDE_IN_JUDGING_REPORT, planSectionDefinition.getIncludeInJudgingReport())
                .returning(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID)
                .fetchOne();
        if (ret != null) {
            planSectionDefinition.setId(ret.getValue(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID));
            return planSectionDefinition;
        } else {
            return null;
        }

    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .where(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID.eq(id))
                .execute();
    }

    @Override
    public boolean update(ProposalTemplateSectionDefinition planSectionDefinition) {
        return dslContext.update(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TYPE, planSectionDefinition.getType())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADMIN_TITLE, planSectionDefinition.getAdminTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TITLE, planSectionDefinition.getTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.DEFAULT_TEXT, planSectionDefinition.getDefaultText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.HELP_TEXT, planSectionDefinition.getHelpText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CHARACTER_LIMIT, planSectionDefinition.getCharacterLimit())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.FOCUS_AREA_ID, planSectionDefinition.getFocusAreaId())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TIER, planSectionDefinition.getTier())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_CONTEST_TYPE_IDS, planSectionDefinition.getAllowedContestTypeIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_VALUES, planSectionDefinition.getAllowedValues())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADDITIONAL_IDS, planSectionDefinition.getAdditionalIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.LOCKED, planSectionDefinition.getLocked())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CONTEST_INTEGRATION_RELEVANCE, planSectionDefinition.getContestIntegrationRelevance())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.INCLUDE_IN_JUDGING_REPORT, planSectionDefinition.getIncludeInJudgingReport())
                .where(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID.eq(planSectionDefinition.getId()))
                .execute() > 0;
    }
}
