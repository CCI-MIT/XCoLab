package org.xcolab.service.contest.domain.proposaltemplate;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplate;
import org.xcolab.model.tables.records.ProposalTemplateRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_TEMPLATE;

@Repository
public class ProposalTemplateDaoImpl implements ProposalTemplateDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public IProposalTemplate get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_TEMPLATE)
                .where(PROPOSAL_TEMPLATE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalTemplate with id " + id + " does not exist");
        }
        return record.into(ProposalTemplate.class);
    }

    @Override
    public IProposalTemplate create(IProposalTemplate proposalTemplate) {

        ProposalTemplateRecord ret = this.dslContext.insertInto(PROPOSAL_TEMPLATE)
                .set(PROPOSAL_TEMPLATE.NAME, proposalTemplate.getName())
                .set(PROPOSAL_TEMPLATE.BASE_TEMPLATE_ID, proposalTemplate.getBaseTemplateId())
                .set(PROPOSAL_TEMPLATE.IMPACT_SERIES_TEMPLATE_ID, proposalTemplate.getImpactSeriesTemplateId())
                .set(PROPOSAL_TEMPLATE.FOCUS_AREA_LIST_TEMPLATE_ID, proposalTemplate.getFocusAreaListTemplateId())
                .returning(PROPOSAL_TEMPLATE.ID)
                .fetchOne();
        if (ret != null) {
            proposalTemplate.setId(ret.getValue(PROPOSAL_TEMPLATE.ID));
            return proposalTemplate;
        } else {
            return null;
        }
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(PROPOSAL_TEMPLATE)
                .where(PROPOSAL_TEMPLATE.ID.eq(id))
                .execute();
    }

    @Override
    public boolean update(IProposalTemplate proposalTemplate) {
        return dslContext.update(PROPOSAL_TEMPLATE)
                .set(PROPOSAL_TEMPLATE.NAME, proposalTemplate.getName())
                .set(PROPOSAL_TEMPLATE.BASE_TEMPLATE_ID, proposalTemplate.getBaseTemplateId())
                .set(PROPOSAL_TEMPLATE.IMPACT_SERIES_TEMPLATE_ID, proposalTemplate.getImpactSeriesTemplateId())
                .set(PROPOSAL_TEMPLATE.FOCUS_AREA_LIST_TEMPLATE_ID, proposalTemplate.getFocusAreaListTemplateId())
                .where(PROPOSAL_TEMPLATE.ID.eq(proposalTemplate.getId()))
                .execute() > 0;
    }

    @Override
    public List<IProposalTemplate> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_TEMPLATE).getQuery();

        return query.fetchInto(ProposalTemplate.class);
    }
}
