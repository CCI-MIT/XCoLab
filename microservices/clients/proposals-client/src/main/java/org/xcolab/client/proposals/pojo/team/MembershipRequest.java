package org.xcolab.client.proposals.pojo.team;

import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;

public class MembershipRequest extends AbstractMembershipRequest {

    public MembershipRequest() {}

    public MembershipRequest(MembershipRequest value) {
        super(value);
    }

    public MembershipRequest(
            Long membershiprequestid,
            Long companyid,
            Long userid,
            Timestamp createdate,
            Long groupid,
            String comments,
            String replycomments,
            Timestamp replydate,
            Long replieruserid,
            Integer statusid
    ) {
        super(membershiprequestid, companyid, userid, createdate, groupid, comments, replycomments,
                replydate, replieruserid, statusid);
    }

    public MembershipRequest(AbstractMembershipRequest abstractMembershipRequest,
            RestService restService) {
        super(abstractMembershipRequest);
    }
}
