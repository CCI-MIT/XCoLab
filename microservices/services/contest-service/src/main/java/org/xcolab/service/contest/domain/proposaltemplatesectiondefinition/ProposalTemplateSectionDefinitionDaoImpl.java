package org.xcolab.service.contest.domain.proposaltemplatesectiondefinition;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.model.tables.records.ProposalTemplateSectionDefinitionRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_TEMPLATE_SECTION;
import static org.xcolab.model.Tables.PROPOSAL_TEMPLATE_SECTION_DEFINITION;

@Repository
public class ProposalTemplateSectionDefinitionDaoImpl implements ProposalTemplateSectionDefinitionDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalTemplateSectionDefinitionDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext is required");
        this.dslContext = dslContext;
    }

    @Override
    public List<ProposalTemplateSectionDefinitionWrapper> findByGiven(Long proposalTemplateId, Boolean weight) {
        final SelectQuery<Record> query = dslContext.select(PROPOSAL_TEMPLATE_SECTION_DEFINITION.fields())
                .from(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .join(PROPOSAL_TEMPLATE_SECTION).on(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID))
                .getQuery();

        if (proposalTemplateId != null) {
            query.addConditions(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID.eq(proposalTemplateId));
        }
        if (weight != null && weight) {
            query.addOrderBy(PROPOSAL_TEMPLATE_SECTION.WEIGHT);
        }
        return query.fetchInto(ProposalTemplateSectionDefinitionWrapper.class);
    }

    @Override
    public ProposalTemplateSectionDefinitionWrapper get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .where(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalTemplateSectionDefinitionWrapper with id " + id + " does not exist");
        }
        return record.into(ProposalTemplateSectionDefinitionWrapper.class);

    }

    @Override
    public ProposalTemplateSectionDefinitionWrapper create(
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {

        ProposalTemplateSectionDefinitionRecord ret = this.dslContext.insertInto(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TYPE, proposalTemplateSectionDefinition.getType())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADMIN_TITLE, proposalTemplateSectionDefinition.getAdminTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TITLE, proposalTemplateSectionDefinition.getTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.DEFAULT_TEXT, proposalTemplateSectionDefinition.getDefaultText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.HELP_TEXT, proposalTemplateSectionDefinition.getHelpText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CHARACTER_LIMIT, proposalTemplateSectionDefinition.getCharacterLimit())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.FOCUS_AREA_ID, proposalTemplateSectionDefinition.getFocusAreaId())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TIER, proposalTemplateSectionDefinition.getTier())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_CONTEST_TYPE_IDS, proposalTemplateSectionDefinition.getAllowedContestTypeIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_VALUES, proposalTemplateSectionDefinition.getAllowedValues())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADDITIONAL_IDS, proposalTemplateSectionDefinition.getAdditionalIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.LOCKED, proposalTemplateSectionDefinition.isLocked())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CONTEST_INTEGRATION_RELEVANCE, proposalTemplateSectionDefinition.isContestIntegrationRelevance())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.INCLUDE_IN_JUDGING_REPORT, proposalTemplateSectionDefinition.isIncludeInJudgingReport())
                .returning(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID)
                .fetchOne();
        if (ret != null) {
            proposalTemplateSectionDefinition.setId(ret.getValue(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID));
            return proposalTemplateSectionDefinition;
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
    public boolean update(ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition) {
        return dslContext.update(PROPOSAL_TEMPLATE_SECTION_DEFINITION)
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TYPE, proposalTemplateSectionDefinition.getType())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADMIN_TITLE, proposalTemplateSectionDefinition.getAdminTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TITLE, proposalTemplateSectionDefinition.getTitle())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.DEFAULT_TEXT, proposalTemplateSectionDefinition.getDefaultText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.HELP_TEXT, proposalTemplateSectionDefinition.getHelpText())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CHARACTER_LIMIT, proposalTemplateSectionDefinition.getCharacterLimit())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.FOCUS_AREA_ID, proposalTemplateSectionDefinition.getFocusAreaId())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.TIER, proposalTemplateSectionDefinition.getTier())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_CONTEST_TYPE_IDS, proposalTemplateSectionDefinition.getAllowedContestTypeIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ALLOWED_VALUES, proposalTemplateSectionDefinition.getAllowedValues())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ADDITIONAL_IDS, proposalTemplateSectionDefinition.getAdditionalIds())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.LOCKED, proposalTemplateSectionDefinition.isLocked())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.CONTEST_INTEGRATION_RELEVANCE, proposalTemplateSectionDefinition.isContestIntegrationRelevance())
                .set(PROPOSAL_TEMPLATE_SECTION_DEFINITION.INCLUDE_IN_JUDGING_REPORT, proposalTemplateSectionDefinition.isIncludeInJudgingReport())
                .where(PROPOSAL_TEMPLATE_SECTION_DEFINITION.ID.eq(proposalTemplateSectionDefinition.getId()))
                .execute() > 0;
    }
}
