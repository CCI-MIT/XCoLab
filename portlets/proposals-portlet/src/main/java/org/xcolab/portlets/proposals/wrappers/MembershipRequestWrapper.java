package org.xcolab.portlets.proposals.wrappers;


import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.MembershipRequest;
import org.xcolab.util.exceptions.ReferenceResolutionException;

public class MembershipRequestWrapper {
    private final Member requestUser;
    private final MembershipRequest membershipRequest;

    public MembershipRequestWrapper(MembershipRequest membershipRequest){
        this.membershipRequest = membershipRequest;

        try {
            this.requestUser = MembersClient.getMember(membershipRequest.getUserId());
        } catch (MemberNotFoundException e) {
            throw ReferenceResolutionException.toObject(Member.class, membershipRequest.getUserId())
                    .fromObject(MembershipRequest.class, membershipRequest.getMembershipRequestId());
        }
    }

    public Member getRequestUser(){
        return requestUser;
    }

    public MembershipRequest getMembershipRequest(){
        return membershipRequest;
    }
}
