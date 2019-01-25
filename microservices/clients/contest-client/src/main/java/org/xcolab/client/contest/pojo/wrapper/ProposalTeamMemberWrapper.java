package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMember;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ProposalTeamMemberWrapper extends ProposalTeamMember {

    public static final TypeProvider<ProposalTeamMemberWrapper> TYPES =
            new TypeProvider<>(ProposalTeamMemberWrapper.class,
                    new ParameterizedTypeReference<List<ProposalTeamMemberWrapper>>() {});

    private final ProposalWrapper proposal;
    private final Member member;
    private ProposalMemberType memberType;

    public ProposalTeamMemberWrapper(ProposalWrapper proposal, Member member) {
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

    @Override
    public void setProposalId(Long proposalId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long getUserId() {
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
