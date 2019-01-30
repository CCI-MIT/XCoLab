package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.Proposal2Phase;

@JsonDeserialize(as = Proposal2Phase.class)
public interface IProposal2Phase {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getContestPhaseId();

    void setContestPhaseId(Long contestPhaseId);

    Integer getVersionFrom();

    void setVersionFrom(Integer versionFrom);

    Integer getVersionTo();

    void setVersionTo(Integer versionTo);

    Integer getSortWeight();

    void setSortWeight(Integer sortWeight);

    Boolean isAutopromoteCandidate();

    void setAutopromoteCandidate(Boolean autopromoteCandidate);
}
