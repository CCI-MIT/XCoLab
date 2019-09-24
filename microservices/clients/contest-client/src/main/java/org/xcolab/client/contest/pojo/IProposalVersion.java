package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;

import java.sql.Timestamp;

@JsonDeserialize(as = ProposalVersionWrapper.class)
public interface IProposalVersion {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Integer getVersion();

    void setVersion(Integer version);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getUpdateType();

    void setUpdateType(String updateType);

    Long getUpdateAdditionalId();

    void setUpdateAdditionalId(Long updateAdditionalId);
}
