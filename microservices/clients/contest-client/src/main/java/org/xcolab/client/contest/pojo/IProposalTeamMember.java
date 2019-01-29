package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMember;

@JsonDeserialize(as = ProposalTeamMember.class)
public interface IProposalTeamMember {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getUserId();

    void setUserId(Long userId);
}
