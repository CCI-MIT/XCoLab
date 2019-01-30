package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;

import java.sql.Timestamp;

@JsonDeserialize(as = ProposalWrapper.class)
public interface IProposal {

    Long getId();

    void setId(Long id);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Boolean isVisible();

    void setVisible(Boolean visible);

    Long getDiscussionId();

    void setDiscussionId(Long discussionId);

    Long getResultsDiscussionId();

    void setResultsDiscussionId(Long resultsDiscussionId);
}
