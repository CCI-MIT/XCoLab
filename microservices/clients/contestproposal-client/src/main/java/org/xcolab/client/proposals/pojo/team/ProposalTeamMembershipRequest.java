package org.xcolab.client.proposals.pojo.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalTeamMembershipRequest extends AbstractProposalTeamMembershipRequest
        implements Serializable {

    public static final TypeProvider<ProposalTeamMembershipRequest> TYPES =
            new TypeProvider<>(ProposalTeamMembershipRequest.class,
                    new ParameterizedTypeReference<List<ProposalTeamMembershipRequest>>() {});

    private Member requestUser;

    public ProposalTeamMembershipRequest() {
    }

    public ProposalTeamMembershipRequest(ProposalTeamMembershipRequest value) {
        super(value);
    }

    public ProposalTeamMembershipRequest(
            AbstractProposalTeamMembershipRequest abstractMembershipRequest) {
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
                            .fromObject(ProposalTeamMembershipRequest.class,
                                    this.getId());
                }
            }else{
                requestUser = null;
            }
        }

        return requestUser;
    }

    public ProposalTeamMembershipRequest getMembershipRequest(){
        return this;
    }
}
