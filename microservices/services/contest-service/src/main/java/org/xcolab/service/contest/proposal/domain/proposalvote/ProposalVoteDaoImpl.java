package org.xcolab.service.contest.proposal.domain.proposalvote;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalVote;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_VOTE;

@Repository
public class ProposalVoteDaoImpl implements ProposalVoteDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalVoteDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IProposalVote create(IProposalVote proposalVote) {
        this.dslContext.insertInto(PROPOSAL_VOTE)
                .set(PROPOSAL_VOTE.PROPOSAL_ID, proposalVote.getProposalId())
                .set(PROPOSAL_VOTE.CONTEST_PHASE_ID, proposalVote.getContestPhaseId())
                .set(PROPOSAL_VOTE.USER_ID, proposalVote.getUserId())
                .set(PROPOSAL_VOTE.VALUE, proposalVote.getValue())
                .set(PROPOSAL_VOTE.CREATED_AT, DSL.currentTimestamp())
                .set(PROPOSAL_VOTE.VOTER_IP, proposalVote.getVoterIp())
                .set(PROPOSAL_VOTE.VOTER_USER_AGENT, proposalVote.getVoterUserAgent())
                .set(PROPOSAL_VOTE.IS_VALID, proposalVote.isIsValid())
                .set(PROPOSAL_VOTE.CONFIRMATION_EMAIL_SEND_DATE, proposalVote.getConfirmationEmailSendDate())
                .set(PROPOSAL_VOTE.CONFIRMATION_TOKEN, proposalVote.getConfirmationToken())
                .set(PROPOSAL_VOTE.INITIAL_VALIDATION_RESULT, proposalVote.getInitialValidationResult())
                .set(PROPOSAL_VOTE.LAST_VALIDATION_RESULT, proposalVote.getLastValidationResult())
                .set(PROPOSAL_VOTE.IS_VALID_OVERRIDE, proposalVote.isIsValidOverride())
                .set(PROPOSAL_VOTE.MANUAL_VALIDATION_RESULT, proposalVote.getManualValidationResult())
                .execute();

        return proposalVote;
    }

    public IProposalVote get(Long proposalId, Long contestPhaseId) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(PROPOSAL_VOTE)
                .where(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalVote with id " + proposalId + " and " +contestPhaseId+ " does not exist");
        }
        return record.into(ProposalVote.class);

    }

    @Override
    public boolean update(IProposalVote proposalVote) {
        return dslContext.update(PROPOSAL_VOTE)
                .set(PROPOSAL_VOTE.VALUE, proposalVote.getValue())
                .set(PROPOSAL_VOTE.VOTER_IP, proposalVote.getVoterIp())
                .set(PROPOSAL_VOTE.VOTER_USER_AGENT, proposalVote.getVoterUserAgent())
                .set(PROPOSAL_VOTE.IS_VALID, proposalVote.isIsValid())
                .set(PROPOSAL_VOTE.CONFIRMATION_EMAIL_SEND_DATE, proposalVote.getConfirmationEmailSendDate())
                .set(PROPOSAL_VOTE.CONFIRMATION_TOKEN, proposalVote.getConfirmationToken())
                .set(PROPOSAL_VOTE.INITIAL_VALIDATION_RESULT, proposalVote.getInitialValidationResult())
                .set(PROPOSAL_VOTE.LAST_VALIDATION_RESULT, proposalVote.getLastValidationResult())
                .set(PROPOSAL_VOTE.IS_VALID_OVERRIDE, proposalVote.isIsValidOverride())
                .set(PROPOSAL_VOTE.MANUAL_VALIDATION_RESULT, proposalVote.getManualValidationResult())
                .where(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalVote.getProposalId()))
                .and(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(proposalVote.getContestPhaseId()))
                .and(PROPOSAL_VOTE.USER_ID.eq(proposalVote.getUserId()))
                .execute() > 0;
    }

    @Override
    public List<IProposalVote> findByGiven(Long proposalId, Long contestPhaseId, Long userId) {
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
    public Integer countByGiven(Long proposalId, Long contestPhaseId, Long userId,
            Boolean isValidOverride) {
        final SelectQuery<Record1<Serializable>> query = dslContext
                .select(DSL.coalesce(DSL.sum(PROPOSAL_VOTE.VALUE), 0))
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
        if (isValidOverride != null && isValidOverride) {
            query.addConditions(PROPOSAL_VOTE.IS_VALID_OVERRIDE.isNull()
                    .or(PROPOSAL_VOTE.IS_VALID_OVERRIDE.eq(true)));
        }
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public int delete(long proposalId, long userId, long contestPhaseId) {
        return dslContext.deleteFrom(PROPOSAL_VOTE)
                .where(PROPOSAL_VOTE.PROPOSAL_ID.eq(proposalId)
                        .and(PROPOSAL_VOTE.USER_ID.eq(userId))
                        .and(PROPOSAL_VOTE.CONTEST_PHASE_ID.eq(contestPhaseId)))
                .execute();
    }
}
