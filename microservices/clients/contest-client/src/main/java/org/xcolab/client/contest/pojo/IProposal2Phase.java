package org.xcolab.client.contest.pojo;

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

    Boolean getAutopromoteCandidate();

    void setAutopromoteCandidate(Boolean autopromoteCandidate);
}
