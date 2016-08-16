package org.xcolab.service.proposal.domain.proposalsupporter;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalSupporter;
import org.xcolab.model.tables.records.ProposalSupporterRecord;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_SUPPORTER;


@Repository
public class ProposalSupporterDaoImpl implements  ProposalSupporterDao {

    @Autowired
    private DSLContext dslContext;

    /*

    ProposalSupporterLocalService
	ProposalSupporterLocalServiceUtil.getProposals(userId)
	ProposalSupporterLocalServiceUtil.getProposalSupporters(0, Integer.MAX_VALUE)

    * */
    public ProposalSupporter create(ProposalSupporter proposalSupporter) {

        this.dslContext.insertInto(PROPOSAL_SUPPORTER)
                .set(PROPOSAL_SUPPORTER.PROPOSAL_ID, proposalSupporter.getProposalId())
                .set(PROPOSAL_SUPPORTER.USER_ID, proposalSupporter.getUserId())
                .set(PROPOSAL_SUPPORTER.CREATE_DATE, proposalSupporter.getCreateDate())

                .execute();

        return proposalSupporter;

    }

    public List<ProposalSupporter> findByGiven(Long proposalId, Long userId) {
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
}
