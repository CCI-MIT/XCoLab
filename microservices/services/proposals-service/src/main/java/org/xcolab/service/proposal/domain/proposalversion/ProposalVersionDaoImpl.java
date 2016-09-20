package org.xcolab.service.proposal.domain.proposalversion;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalVersion;

import java.util.List;


import static org.xcolab.model.Tables.PROPOSAL_VERSION;


@Repository
public class ProposalVersionDaoImpl implements ProposalVersionDao {

    @Autowired
    private DSLContext dslContext;

    public ProposalVersion create(ProposalVersion proposalVersion) {

        this.dslContext.insertInto(PROPOSAL_VERSION)
                .set(PROPOSAL_VERSION.PROPOSAL_ID, proposalVersion.getProposalId())
                .set(PROPOSAL_VERSION.VERSION, proposalVersion.getVersion())
                .set(PROPOSAL_VERSION.AUTHOR_ID, proposalVersion.getAuthorId())
                .set(PROPOSAL_VERSION.CREATE_DATE, proposalVersion.getCreateDate())
                .set(PROPOSAL_VERSION.UPDATE_TYPE, proposalVersion.getUpdateType())
                .set(PROPOSAL_VERSION.UPDATE_ADDITIONAL_ID, proposalVersion.getUpdateAdditionalId())
                .execute();

        return proposalVersion;
    }

    @Override
    public List<ProposalVersion> findByGiven(Long proposalId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_VERSION).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL_VERSION.VERSION.eq(version));
        }
        return query.fetchInto(ProposalVersion.class);
    }

    public ProposalVersion getByProposalIdVersion(Long proposalId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_VERSION).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL_VERSION.VERSION.eq(version));
        }
        return query.fetchOne().into(ProposalVersion.class);
    }

    public int countByGiven(Long proposalId) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(PROPOSAL_VERSION)
                .where(PROPOSAL_VERSION.PROPOSAL_ID.eq(proposalId))
                .getQuery();

        return query.fetchOne().into(Integer.class);
    }

}
