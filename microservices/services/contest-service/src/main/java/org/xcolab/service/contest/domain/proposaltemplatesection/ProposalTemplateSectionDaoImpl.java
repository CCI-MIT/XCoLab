package org.xcolab.service.contest.domain.proposaltemplatesection;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplateSection;

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
    public List<IProposalTemplateSection> findByGiven(Long proposalTemplateId, Long sectionDefinition) {
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
    public IProposalTemplateSection create(IProposalTemplateSection proposalTemplateSection) {

        this.dslContext.insertInto(PROPOSAL_TEMPLATE_SECTION)
                .set(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID, proposalTemplateSection.getProposalTemplateId())
                .set(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID, proposalTemplateSection.getSectionDefinitionId())
                .set(PROPOSAL_TEMPLATE_SECTION.WEIGHT, proposalTemplateSection.getWeight())
                .execute();
        return proposalTemplateSection;
    }

    @Override
    public int delete(Long proposalTemplateId, Long sectionDefinitionId) {
        return dslContext.deleteFrom(PROPOSAL_TEMPLATE_SECTION)
                .where(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID.eq(proposalTemplateId))
                .and(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(sectionDefinitionId))
                .execute();
    }

    @Override
    public boolean update(IProposalTemplateSection proposalTemplateSection) {
        return dslContext.update(PROPOSAL_TEMPLATE_SECTION)
                .set(PROPOSAL_TEMPLATE_SECTION.WEIGHT, proposalTemplateSection.getWeight())
                .where(PROPOSAL_TEMPLATE_SECTION.PROPOSAL_TEMPLATE_ID.eq(proposalTemplateSection.getProposalTemplateId()))
                .and(PROPOSAL_TEMPLATE_SECTION.SECTION_DEFINITION_ID.eq(proposalTemplateSection.getSectionDefinitionId()))
                .execute() > 0;
    }
}
