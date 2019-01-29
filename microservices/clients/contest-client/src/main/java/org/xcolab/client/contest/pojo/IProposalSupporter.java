package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalSupporter;

import java.sql.Timestamp;

@JsonDeserialize(as = ProposalSupporter.class)
public interface IProposalSupporter {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getUserId();

    void setUserId(Long userId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
