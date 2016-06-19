package org.xcolab.service.proposal.domain.proposal;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.records.ProposalRecord;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;


@Repository
public class ProposalDaoImpl implements ProposalDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Proposal> findByGiven(PaginationHelper paginationHelper, Long contestId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL)
                .getQuery();

        if (contestId != null) {
            query.addJoin(PROPOSAL_2_PHASE, PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_2_PHASE.PROPOSAL_ID));
            query.addJoin(CONTEST_PHASE,
                    CONTEST_PHASE.CONTEST_PHASE_PK.eq(PROPOSAL_2_PHASE.CONTEST_PHASE_ID));
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(contestId));
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());
        return query.fetchInto(Proposal.class);
    }

    @Override
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

    @Override
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


    @Override
    public Proposal get(Long proposalId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL)
                .where(PROPOSAL.PROPOSAL_ID.eq(proposalId)).fetchOne();

        if (record == null) {
            throw new NotFoundException("Proposal with id " + proposalId + " does not exist");
        }
        return record.into(Proposal.class);

    }
}
