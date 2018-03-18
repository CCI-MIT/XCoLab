package org.xcolab.client.proposals.pojo.team;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class MembershipRequest extends AbstractMembershipRequest {

    private Member requestUser;

    public MembershipRequest() {
    }

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
            ServiceNamespace serviceNamespace) {
        super(abstractMembershipRequest);

    }

    public Member getRequestUser(){
        if(this.requestUser == null){
            if(this.getUserId() != null) {
                try {
                    this.requestUser = MembersClient.getMember(this.getUserId());
                } catch (MemberNotFoundException e) {
                    throw ReferenceResolutionException
                            .toObject(Member.class, this.getUserId())
                            .fromObject(MembershipRequest.class,
                                    this.getMembershipRequestId());
                }
            }else{
                requestUser = null;
            }
        }

        return requestUser;
    }

    public MembershipRequest getMembershipRequest(){
        return this;
    }
}
