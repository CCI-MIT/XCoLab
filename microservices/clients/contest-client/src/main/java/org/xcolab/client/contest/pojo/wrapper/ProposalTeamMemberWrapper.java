package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMember;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalTeamMemberWrapper extends ProposalTeamMember implements Serializable {

    private final ProposalWrapper proposal;
    private final UserWrapper member;
    private ProposalMemberType memberType;

    public ProposalTeamMemberWrapper(ProposalWrapper proposal, UserWrapper member) {
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
    public Long getProposalId() {
        return proposal.getId();
    }

    @Override
    public Long getUserId() {
        return member.getId();
    }

    public UserWrapper getMember() {
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
