package org.xcolab.client.contest.pojo;

import java.sql.Timestamp;

public interface IProposalSupporter {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getUserId();

    void setUserId(Long userId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
