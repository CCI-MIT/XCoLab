package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMemberWrapper;

@JsonDeserialize(as = ProposalTeamMemberWrapper.class)
public interface IProposalTeamMember {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getUserId();

    void setUserId(Long userId);
}
