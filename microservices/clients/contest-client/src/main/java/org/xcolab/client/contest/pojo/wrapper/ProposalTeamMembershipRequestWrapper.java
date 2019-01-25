package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMembershipRequest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.ReferenceResolutionException;

public class ProposalTeamMembershipRequestWrapper extends ProposalTeamMembershipRequest {

    private Member requestUser;

    public ProposalTeamMembershipRequestWrapper() {
    }

    public ProposalTeamMembershipRequestWrapper(ProposalTeamMembershipRequestWrapper value) {
        super(value);
    }

    public ProposalTeamMembershipRequestWrapper(ProposalTeamMembershipRequest abstractMembershipRequest) {
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
                            .fromObject(ProposalTeamMembershipRequestWrapper.class,
                                    this.getId());
                }
            }else{
                requestUser = null;
            }
        }

        return requestUser;
    }

    public ProposalTeamMembershipRequestWrapper getMembershipRequest(){
        return this;
    }
}
