package org.xcolab.client.contest.pojo;

import java.sql.Timestamp;

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
