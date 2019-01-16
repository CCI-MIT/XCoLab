package org.xcolab.client.proposals.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.user.pojo.Member;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalTeamMember implements Serializable {

    public static final TypeProvider<ProposalTeamMember> TYPES =
            new TypeProvider<>(ProposalTeamMember.class,
                    new ParameterizedTypeReference<List<ProposalTeamMember>>() {});

    private final Proposal proposal;
    private final Member member;
    private ProposalMemberType memberType;

    public ProposalTeamMember(Proposal proposal, Member member) {
        super();
        this.proposal = proposal;
        this.member = member;
    }
    
    public String getFullName() {
        return member.getFullName();
    }
    
    public String getScreenName() {
        return member.getDisplayName();
    }
    
    public long getUserId() {
        return member.getId();
    }

    public Member getMember() {
        return member;
    }

    public long getPortraitId() {
        return member.getPortraitId();
    }
    
    public ProposalMemberType getMemberType() {
        if (memberType == null) {
            if (proposal.getAuthorUserId() == member.getId()) {
                memberType = ProposalMemberType.OWNER;
            } else {
                memberType = ProposalMemberType.MEMBER;
            }
        }
        return memberType;
    }

    public String getMemberTypeForDisplay() {
        return getMemberType().getDescription();
    }
    
    public enum ProposalMemberType {
        OWNER("Owner"),
        MEMBER("Member");
        
        private final String description;

        ProposalMemberType(String description) {
            this.description = description;
        }
        
        public String getDescription() { return description; }
    }

    

}
