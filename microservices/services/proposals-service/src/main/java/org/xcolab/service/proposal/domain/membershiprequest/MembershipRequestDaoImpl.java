package org.xcolab.service.proposal.domain.membershiprequest;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.MembershipRequest;
import org.xcolab.model.tables.records.MembershipRequestRecord;

import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.MEMBERSHIP_REQUEST;

@Repository
public class MembershipRequestDaoImpl implements MembershipRequestDao {

    @Autowired
    private DSLContext dslContext;


    public MembershipRequest create(MembershipRequest membershipRequest) {

        MembershipRequestRecord ret = this.dslContext.insertInto(MEMBERSHIP_REQUEST)
                .set(MEMBERSHIP_REQUEST.MEMBERSHIP_REQUEST_ID, membershipRequest.getMembershipRequestId())
                .set(MEMBERSHIP_REQUEST.COMPANY_ID, membershipRequest.getCompanyId())
                .set(MEMBERSHIP_REQUEST.USER_ID, membershipRequest.getUserId())
                .set(MEMBERSHIP_REQUEST.CREATE_DATE, membershipRequest.getCreateDate())
                .set(MEMBERSHIP_REQUEST.GROUP_ID, membershipRequest.getGroupId())
                .set(MEMBERSHIP_REQUEST.COMMENTS, membershipRequest.getComments())
                .set(MEMBERSHIP_REQUEST.REPLY_COMMENTS, membershipRequest.getReplyComments())
                .set(MEMBERSHIP_REQUEST.REPLY_DATE, membershipRequest.getReplyDate())
                .set(MEMBERSHIP_REQUEST.REPLIER_USER_ID, membershipRequest.getReplierUserId())
                .set(MEMBERSHIP_REQUEST.STATUS_ID, membershipRequest.getStatusId())
                .returning(MEMBERSHIP_REQUEST.MEMBERSHIP_REQUEST_ID)
                .fetchOne();
        if (ret != null) {
            membershipRequest.setMembershipRequestId(ret.getValue(MEMBERSHIP_REQUEST.MEMBERSHIP_REQUEST_ID));
            return membershipRequest;
        } else {
            return null;
        }

    }

    public MembershipRequest get(Long membershipRequestId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(MEMBERSHIP_REQUEST)
                .where(MEMBERSHIP_REQUEST.MEMBERSHIP_REQUEST_ID.eq(membershipRequestId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("MembershipRequest with id " + membershipRequestId + " does not exist");
        }
        return record.into(MembershipRequest.class);

    }

    @Override
    public List<MembershipRequest> findByGiven(Long groupId, Integer statusId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MEMBERSHIP_REQUEST).getQuery();

        if (groupId != null) {
            query.addConditions(MEMBERSHIP_REQUEST.GROUP_ID.eq(groupId));
        }
        if (statusId != null) {
            query.addConditions(MEMBERSHIP_REQUEST.STATUS_ID.eq(statusId));
        }
        return query.fetchInto(MembershipRequest.class);
    }
}
