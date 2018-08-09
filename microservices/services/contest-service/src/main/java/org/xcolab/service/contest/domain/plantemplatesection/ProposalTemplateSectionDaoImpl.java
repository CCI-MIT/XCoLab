package org.xcolab.service.contest.domain.plantemplatesection;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalTemplateSection;

import java.util.List;


import static org.xcolab.model.Tables.PROPOSAL_TEMPLATE_SECTION;

@Repository
public class ProposalTemplateSectionDaoImpl implements ProposalTemplateSectionDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalTemplateSectionDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<ProposalTemplateSection> findByGiven(Long proposalTemplateId, Long sectionDefinition) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_TEMPLATE_SECTION).getQuery();

        if (proposalTemplateId != null) {
            query.addConditions(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID.eq(proposalTemplateId));
        }

        if (sectionDefinition != null) {
            query.addConditions(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(sectionDefinition));
        }

        return query.fetchInto(ProposalTemplateSection.class);
    }

    @Override
    public ProposalTemplateSection create(ProposalTemplateSection planTemplateSection) {

        this.dslContext.insertInto(PROPOSAL_TEMPLATE_SECTION)
                .set(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID, planTemplateSection.getProposalTemplateId())
                .set(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID, planTemplateSection.getSectionDefinitionId())
                .set(PROPOSAL_TEMPLATE_SECTION.WEIGHT, planTemplateSection.getWeight())
                .execute();
        return planTemplateSection;

    }

    @Override
    public int delete(Long proposalTemplateId, Long sectionDefinitionId) {
        return dslContext.deleteFrom(PROPOSAL_TEMPLATE_SECTION)
                .where(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID.eq(proposalTemplateId))
                .and(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(sectionDefinitionId))
                .execute();
    }

    @Override
    public boolean update(ProposalTemplateSection proposalTemplateSection) {
        return dslContext.update(PROPOSAL_TEMPLATE_SECTION)
                .set(PROPOSAL_TEMPLATE_SECTION.WEIGHT, proposalTemplateSection.getWeight())
                .where(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID.eq(proposalTemplateSection.getProposalTemplateId()))
                .and(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(proposalTemplateSection.getSectionDefinitionId()))
                .execute() > 0;
    }



}
