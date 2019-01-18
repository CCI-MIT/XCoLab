package org.xcolab.client.contest.pojo;

import java.sql.Timestamp;

public interface IProposal {

    Long getId();

    void setId(Long id);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Boolean getVisible();

    void setVisible(Boolean visible);

    Long getDiscussionId();

    void setDiscussionId(Long discussionId);

    Long getResultsDiscussionId();

    void setResultsDiscussionId(Long resultsDiscussionId);
}
