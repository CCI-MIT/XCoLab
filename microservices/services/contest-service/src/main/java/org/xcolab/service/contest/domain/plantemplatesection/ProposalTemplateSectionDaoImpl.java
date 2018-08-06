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

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ProposalTemplateSection> findByGiven(Long planTemplateId, Long planSectionId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_TEMPLATE_SECTION).getQuery();

        if (planTemplateId != null) {
            query.addConditions(PROPOSAL_TEMPLATE_SECTION.ID.eq(planTemplateId));
        }

        if (planSectionId != null) {
            query.addConditions(PROPOSAL_TEMPLATE_SECTION.ID.eq(planSectionId));
        }

        return query.fetchInto(ProposalTemplateSection.class);
    }

    @Override
    public ProposalTemplateSection create(ProposalTemplateSection planTemplateSection) {

        this.dslContext.insertInto(PROPOSAL_TEMPLATE_SECTION)
                .set(PROPOSAL_TEMPLATE_SECTION.ID, planTemplateSection.getId())
                .set(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID, planTemplateSection.getSectionDefinitionId())
                .set(PROPOSAL_TEMPLATE_SECTION.WEIGHT, planTemplateSection.getWeight())
                .execute();
        return planTemplateSection;

    }

    @Override
    public int delete(Long planTemplateId, Long planSectionDefinitionId) {
        return dslContext.deleteFrom(PROPOSAL_TEMPLATE_SECTION)
                .where(PROPOSAL_TEMPLATE_SECTION.ID.eq(planTemplateId))
                .and(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(planSectionDefinitionId))
                .execute();
    }

    @Override
    public boolean update(ProposalTemplateSection planTemplateSection) {
        return dslContext.update(PROPOSAL_TEMPLATE_SECTION)
                .set(PROPOSAL_TEMPLATE_SECTION.WEIGHT, planTemplateSection.getWeight())
                .where(PROPOSAL_TEMPLATE_SECTION.ID.eq(planTemplateSection.getId()))
                .and(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(planTemplateSection.getSectionDefinitionId()))
                .execute() > 0;
    }



}
