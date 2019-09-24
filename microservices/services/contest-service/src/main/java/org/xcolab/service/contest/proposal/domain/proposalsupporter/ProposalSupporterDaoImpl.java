package org.xcolab.service.contest.proposal.domain.proposalsupporter;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalSupporter;

import java.util.List;

import static org.jooq.impl.DSL.countDistinct;
import static org.xcolab.model.Tables.PROPOSAL_SUPPORTER;

@Repository
public class ProposalSupporterDaoImpl implements ProposalSupporterDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalSupporterDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IProposalSupporter create(IProposalSupporter proposalSupporter) {

        this.dslContext.insertInto(PROPOSAL_SUPPORTER)
                .set(PROPOSAL_SUPPORTER.PROPOSAL_ID, proposalSupporter.getProposalId())
                .set(PROPOSAL_SUPPORTER.USER_ID, proposalSupporter.getUserId())
                .set(PROPOSAL_SUPPORTER.CREATED_AT, proposalSupporter.getCreatedAt())

                .execute();

        return proposalSupporter;
    }

    @Override
    public int delete(Long proposalId, Long userId) {
        return dslContext.deleteFrom(PROPOSAL_SUPPORTER)
                .where(PROPOSAL_SUPPORTER.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_SUPPORTER.USER_ID.eq(userId))
                .execute();
    }

    @Override
    public List<IProposalSupporter> findByGiven(Long proposalId, Long userId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_SUPPORTER).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_SUPPORTER.PROPOSAL_ID.eq(proposalId));
        }
        if (userId != null) {
            query.addConditions(PROPOSAL_SUPPORTER.USER_ID.eq(userId));
        }
        return query.fetchInto(ProposalSupporter.class);
    }

    @Override
    public Integer countByProposalId(Long proposalId) {
        final SelectQuery<Record1<Integer>> query = dslContext.select(countDistinct(PROPOSAL_SUPPORTER.USER_ID))
                .from(PROPOSAL_SUPPORTER)
                .where(PROPOSAL_SUPPORTER.PROPOSAL_ID.eq(proposalId)).getQuery();

        return query.fetchOne().into(Integer.class);
    }
}
