package org.xcolab.service.proposal.domain.membershiprequest;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ProposalTeamMembershipRequest;
import org.xcolab.model.tables.records.ProposalTeamMembershipRequestRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_TEAM_MEMBERSHIP_REQUEST;

@Repository
public class ProposalTeamMembershipRequestDaoImpl implements org.xcolab.service.proposal.domain.membershiprequest.ProposalTeamMembershipRequestDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalTeamMembershipRequestDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public ProposalTeamMembershipRequest create(ProposalTeamMembershipRequest ProposalTeamMembershipRequest) {

        ProposalTeamMembershipRequestRecord ret = this.dslContext.insertInto(PROPOSAL_TEAM_MEMBERSHIP_REQUEST)
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID, ProposalTeamMembershipRequest.getId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.USER_ID, ProposalTeamMembershipRequest.getUserId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.CREATED_AT, ProposalTeamMembershipRequest.getCreatedAt())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.PROPOSAL_ID, ProposalTeamMembershipRequest.getProposalId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.COMMENTS, ProposalTeamMembershipRequest.getComments())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_COMMENTS, ProposalTeamMembershipRequest.getReplyComments())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_DATE, ProposalTeamMembershipRequest.getReplyDate())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLIER_USER_ID, ProposalTeamMembershipRequest.getReplierUserId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.STATUS_ID, ProposalTeamMembershipRequest.getStatusId())
                .returning(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID)
                .fetchOne();
        if (ret != null) {
            ProposalTeamMembershipRequest.setId(ret.getValue(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID));
            return ProposalTeamMembershipRequest;
        } else {
            return null;
        }
    }

    @Override
    public boolean update(ProposalTeamMembershipRequest ProposalTeamMembershipRequest) {
        return dslContext.update(PROPOSAL_TEAM_MEMBERSHIP_REQUEST)
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_COMMENTS, ProposalTeamMembershipRequest.getReplyComments())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_DATE, ProposalTeamMembershipRequest.getReplyDate())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLIER_USER_ID, ProposalTeamMembershipRequest.getReplierUserId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.STATUS_ID, ProposalTeamMembershipRequest.getStatusId())
                .where(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID.eq(ProposalTeamMembershipRequest.getId()))
                .execute() > 0;
    }

    @Override
    public ProposalTeamMembershipRequest get(Long ProposalTeamMembershipRequestId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(PROPOSAL_TEAM_MEMBERSHIP_REQUEST)
                .where(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID.eq(ProposalTeamMembershipRequestId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalTeamMembershipRequest with id " + ProposalTeamMembershipRequestId + " does not exist");
        }
        return record.into(ProposalTeamMembershipRequest.class);

    }

    @Override
    public List<ProposalTeamMembershipRequest> findByGiven(Integer statusId, Long userId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_TEAM_MEMBERSHIP_REQUEST).getQuery();

        if (statusId != null) {
            query.addConditions(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.STATUS_ID.eq(statusId));
        }
        if (userId != null) {
            query.addConditions(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.USER_ID.eq(userId));
        }
        return query.fetchInto(ProposalTeamMembershipRequest.class);
    }
}
