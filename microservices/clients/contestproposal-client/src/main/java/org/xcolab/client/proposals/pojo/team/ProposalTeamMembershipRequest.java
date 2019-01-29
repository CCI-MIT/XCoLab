package org.xcolab.client.proposals.pojo.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

    private UserWrapper requestUser;

    public ProposalTeamMembershipRequest() {
    }

    public ProposalTeamMembershipRequest(ProposalTeamMembershipRequest value) {
        super(value);
    }

    public ProposalTeamMembershipRequest(
            AbstractProposalTeamMembershipRequest abstractMembershipRequest) {
        super(abstractMembershipRequest);

    }

    public UserWrapper getRequestUser(){
        if(this.requestUser == null){
            if(this.getUserId() != null) {
                try {
                    this.requestUser = StaticUserContext.getUserClient().getMember(this.getUserId());
                } catch (MemberNotFoundException e) {
                    throw ReferenceResolutionException
                            .toObject(UserWrapper.class, this.getUserId())
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
