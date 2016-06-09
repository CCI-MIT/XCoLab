package org.xcolab.service.proposal.domain.proposal;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.records.ProposalRecord;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;

import static org.xcolab.model.Tables.PROPOSAL;


@Repository
public class ProposalDaoImpl implements ProposalDao {

    @Autowired
    private DSLContext dslContext;

    public Proposal create(Proposal proposal) {

        ProposalRecord ret = this.dslContext.insertInto(PROPOSAL)
                .set(PROPOSAL.CREATE_DATE, proposal.getCreateDate())
                .set(PROPOSAL.UPDATED_DATE, proposal.getUpdatedDate())
                .set(PROPOSAL.CURRENT_VERSION, proposal.getCurrentVersion())
                .set(PROPOSAL.AUTHOR_ID, proposal.getAuthorId())
                .set(PROPOSAL.VISIBLE, proposal.getVisible())
                .set(PROPOSAL.DISCUSSION_ID, proposal.getDiscussionId())
                .set(PROPOSAL.RESULTS_DISCUSSION_ID, proposal.getResultsDiscussionId())
                .set(PROPOSAL.JUDGE_DISCUSSION_ID, proposal.getJudgeDiscussionId())
                .set(PROPOSAL.FELLOW_DISCUSSION_ID, proposal.getFellowDiscussionId())
                .set(PROPOSAL.ADVISOR_DISCUSSION_ID, proposal.getAdvisorDiscussionId())
                .set(PROPOSAL.GROUP_ID, proposal.getGroupId())
                .returning(PROPOSAL.PROPOSAL_ID)
                .fetchOne();
        if (ret != null) {
            proposal.setProposalId(ret.getValue(PROPOSAL.PROPOSAL_ID));
            return proposal;
        } else {
            return null;
        }

    }

    public boolean update(Proposal proposal) {

        return dslContext.update(PROPOSAL)
                .set(PROPOSAL.CREATE_DATE, proposal.getCreateDate())
                .set(PROPOSAL.UPDATED_DATE, proposal.getUpdatedDate())
                .set(PROPOSAL.CURRENT_VERSION, proposal.getCurrentVersion())
                .set(PROPOSAL.AUTHOR_ID, proposal.getAuthorId())
                .set(PROPOSAL.VISIBLE, proposal.getVisible())
                .set(PROPOSAL.DISCUSSION_ID, proposal.getDiscussionId())
                .set(PROPOSAL.RESULTS_DISCUSSION_ID, proposal.getResultsDiscussionId())
                .set(PROPOSAL.JUDGE_DISCUSSION_ID, proposal.getJudgeDiscussionId())
                .set(PROPOSAL.FELLOW_DISCUSSION_ID, proposal.getFellowDiscussionId())
                .set(PROPOSAL.ADVISOR_DISCUSSION_ID, proposal.getAdvisorDiscussionId())
                .set(PROPOSAL.GROUP_ID, proposal.getGroupId())
                .where(PROPOSAL.PROPOSAL_ID.eq(proposal.getProposalId()))
                .execute() > 0;
    }


    public Proposal get(Long proposalId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL)
                .where(PROPOSAL.PROPOSAL_ID.eq(proposalId)).fetchOne();

        if (record == null) {
            throw new NotFoundException("Proposal with id " + proposalId + " does not exist");
        }
        return record.into(Proposal.class);

    }
}
