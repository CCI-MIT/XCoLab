package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMembershipRequest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.ReferenceResolutionException;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalTeamMembershipRequestWrapper extends ProposalTeamMembershipRequest
        implements Serializable {

    private Member requestUser;

    public ProposalTeamMembershipRequestWrapper() {
    }

    public ProposalTeamMembershipRequestWrapper(ProposalTeamMembershipRequestWrapper value) {
        super(value);
    }

    public ProposalTeamMembershipRequestWrapper(ProposalTeamMembershipRequest abstractMembershipRequest) {
        super(abstractMembershipRequest);
    }

    @JsonIgnore
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

    @JsonIgnore
    public ProposalTeamMembershipRequestWrapper getMembershipRequest(){
        return this;
    }
}
