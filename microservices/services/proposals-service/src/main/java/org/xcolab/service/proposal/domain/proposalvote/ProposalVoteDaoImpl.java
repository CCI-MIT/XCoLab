package org.xcolab.service.proposal.domain.proposalvote;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalVote;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;


import static org.xcolab.model.Tables.PROPOSAL_VOTE;

@Repository
public class ProposalVoteDaoImpl implements ProposalVoteDao {
    @Autowired
    private DSLContext dslContext;




    public ProposalVote create(ProposalVote proposalVote) {

        this.dslContext.insertInto(PROPOSAL_VOTE)
                .set(PROPOSAL_VOTE.PROPOSAL_ID, proposalVote.getProposalId())
                .set(PROPOSAL_VOTE.CONTEST_PHASE_ID, proposalVote.getContestPhaseId())
                .set(PROPOSAL_VOTE.USER_ID, proposalVote.getUserId())
                .set(PROPOSAL_VOTE.CREATE_DATE, proposalVote.getCreateDate())
                .set(PROPOSAL_VOTE.IS_VALID, proposalVote.getIsValid())
                .set(PROPOSAL_VOTE.CONFIRMATION_EMAIL_SEND_DATE, proposalVote.getConfirmationEmailSendDate())
                .set(PROPOSAL_VOTE.CONFIRMATION_TOKEN, proposalVote.getConfirmationToken())
                .execute();


        return proposalVote;
    }

    public ProposalVote get(Long proposalId, Long contestPhaseId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_VOTE)
                .where(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalVote with id " + proposalId + " and " +contestPhaseId+ " does not exist");
        }
        return record.into(ProposalVote.class);

    }

    public boolean update(ProposalVote proposalVote) {
        return dslContext.update(PROPOSAL_VOTE)
                .set(PROPOSAL_VOTE.CREATE_DATE, proposalVote.getCreateDate())
                .set(PROPOSAL_VOTE.IS_VALID, proposalVote.getIsValid())
                .set(PROPOSAL_VOTE.CONFIRMATION_EMAIL_SEND_DATE, proposalVote.getConfirmationEmailSendDate())
                .set(PROPOSAL_VOTE.CONFIRMATION_TOKEN, proposalVote.getConfirmationToken())
                .where(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalVote.getProposalId()))
                .and(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(proposalVote.getContestPhaseId()))
                .and(PROPOSAL_VOTE.USER_ID.eq(proposalVote.getUserId()))

                .execute() > 0;
    }

    @Override
    public List<ProposalVote> findByGiven(Long proposalId, Long contestPhaseId, Long userId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_VOTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (userId != null) {
            query.addConditions(PROPOSAL_VOTE.USER_ID.eq(userId));
        }
        return query.fetchInto(ProposalVote.class);
    }

    @Override
    public Integer countByGiven(Long proposalId, Long contestPhaseId, Long userId) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(PROPOSAL_VOTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (userId != null) {
            query.addConditions(PROPOSAL_VOTE.USER_ID.eq(userId));
        }
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public int delete(Long proposalId, Long contestPhaseId) {
        return dslContext.deleteFrom(PROPOSAL_VOTE)
                .where(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .execute();
    }
}
