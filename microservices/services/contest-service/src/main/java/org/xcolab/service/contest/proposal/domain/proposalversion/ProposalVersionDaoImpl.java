package org.xcolab.service.contest.proposal.domain.proposalversion;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_VERSION;

@Repository
public class ProposalVersionDaoImpl implements ProposalVersionDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalVersionDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public ProposalVersionWrapper create(ProposalVersionWrapper proposalVersion) {

        this.dslContext.insertInto(PROPOSAL_VERSION)
                .set(PROPOSAL_VERSION.PROPOSAL_ID, proposalVersion.getProposalId())
                .set(PROPOSAL_VERSION.VERSION, proposalVersion.getVersion())
                .set(PROPOSAL_VERSION.AUTHOR_USER_ID, proposalVersion.getAuthorUserId())
                .set(PROPOSAL_VERSION.CREATED_AT, DSL.currentTimestamp())
                .set(PROPOSAL_VERSION.UPDATE_TYPE, proposalVersion.getUpdateType())
                .set(PROPOSAL_VERSION.UPDATE_ADDITIONAL_ID, proposalVersion.getUpdateAdditionalId())
                .execute();

        return proposalVersion;
    }

    @Override
    public List<ProposalVersionWrapper> findByGiven(Long proposalId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_VERSION).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL_VERSION.VERSION.eq(version));
        }
        return query.fetchInto(ProposalVersionWrapper.class);
    }

    @Override
    public int findMaxVersion(Long proposalId) {
        return dslContext.select(DSL.coalesce(PROPOSAL_VERSION.VERSION.max(), 0))
                .from(PROPOSAL_VERSION)
                .where(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId))
                .fetchOne().into(Integer.class);
    }

    @Override
    public ProposalVersionWrapper getByProposalIdVersion(Long proposalId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_VERSION).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL_VERSION.VERSION.eq(version));
        }
        return query.fetchOne().into(ProposalVersionWrapper.class);
    }

    @Override
    public int countByGiven(Long proposalId) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(PROPOSAL_VERSION)
                .where(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId))
                .getQuery();

        return query.fetchOne().into(Integer.class);
    }

    @Override
    public List<ProposalVersionWrapper> findByProposal2Phase(List<IProposal2Phase> proposal2Phases, Long proposalId) {

        final SelectQuery<Record> query = dslContext.selectDistinct()
                .from(PROPOSAL_VERSION).getQuery();
        Condition versionsRange = null;
        Condition versionsRangeOr = null ;
        for(IProposal2Phase p2p : proposal2Phases) {

            if(!p2p.getVersionTo().equals(-1)) {
                versionsRange = PROPOSAL_VERSION.VERSION.ge(p2p.getVersionFrom())
                        .and(PROPOSAL_VERSION.VERSION.le(p2p.getVersionTo()));
                if (versionsRangeOr == null) {
                    versionsRangeOr = versionsRange;
                } else {
                    versionsRangeOr = versionsRangeOr.or(versionsRange);
                }
            }

        }
        query.addConditions(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId));
        if(versionsRangeOr != null ) {
            query.addConditions(versionsRangeOr);
        }

        return query.fetchInto(ProposalVersionWrapper.class);
    }
}
