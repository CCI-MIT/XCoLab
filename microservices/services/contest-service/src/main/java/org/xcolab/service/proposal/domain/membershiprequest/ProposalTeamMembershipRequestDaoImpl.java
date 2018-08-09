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
    public ProposalTeamMembershipRequest create(ProposalTeamMembershipRequest membershipRequest) {

        ProposalTeamMembershipRequestRecord ret = this.dslContext.insertInto(PROPOSAL_TEAM_MEMBERSHIP_REQUEST)
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID, membershipRequest.getId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.USER_ID, membershipRequest.getUserId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.CREATED_AT, membershipRequest.getCreatedAt())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.PROPOSAL_ID, membershipRequest.getProposalId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.COMMENTS, membershipRequest.getComments())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_COMMENTS, membershipRequest.getReplyComments())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_DATE, membershipRequest.getReplyDate())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLIER_USER_ID, membershipRequest.getReplierUserId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.STATUS_ID, membershipRequest.getStatusId())
                .returning(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID)
                .fetchOne();
        if (ret != null) {
            membershipRequest.setId(ret.getValue(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID));
            return membershipRequest;
        } else {
            return null;
        }
    }

    @Override
    public boolean update(ProposalTeamMembershipRequest membershipRequest) {
        return dslContext.update(PROPOSAL_TEAM_MEMBERSHIP_REQUEST)
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_COMMENTS, membershipRequest.getReplyComments())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLY_DATE, membershipRequest.getReplyDate())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.REPLIER_USER_ID, membershipRequest.getReplierUserId())
                .set(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.STATUS_ID, membershipRequest.getStatusId())
                .where(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID.eq(membershipRequest.getId()))
                .execute() > 0;
    }

    @Override
    public ProposalTeamMembershipRequest get(Long membershipRequestId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(PROPOSAL_TEAM_MEMBERSHIP_REQUEST)
                .where(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.ID.eq(membershipRequestId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalTeamMembershipRequest with id " + membershipRequestId + " does not exist");
        }
        return record.into(ProposalTeamMembershipRequest.class);

    }

    @Override
    public List<ProposalTeamMembershipRequest> findByGiven(Long proposalId, Integer statusId, Long userId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_TEAM_MEMBERSHIP_REQUEST).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.PROPOSAL_ID.eq(proposalId));
        }
        if (statusId != null) {
            query.addConditions(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.STATUS_ID.eq(statusId));
        }
        if (userId != null) {
            query.addConditions(PROPOSAL_TEAM_MEMBERSHIP_REQUEST.USER_ID.eq(userId));
        }
        return query.fetchInto(ProposalTeamMembershipRequest.class);
    }
}
