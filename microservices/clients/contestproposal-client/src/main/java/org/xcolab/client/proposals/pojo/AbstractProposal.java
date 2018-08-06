package org.xcolab.client.proposals.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long authorUserId;
    private Boolean visible;
    private Long discussionid;
    private Long resultsdiscussionid;
    private Long groupid;

    public AbstractProposal() {}

    public AbstractProposal(AbstractProposal value) {
        this.id = value.id;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
        this.authorUserId = value.authorUserId;
        this.visible = value.visible;
        this.discussionid = value.discussionid;
        this.resultsdiscussionid = value.resultsdiscussionid;
        this.groupid = value.groupid;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long proposalId) {
        this.id = proposalId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getAuthorUserId() {
        return this.authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
    }

    public Long getDiscussionId() {
        return this.discussionid;
    }

    public void setDiscussionId(Long discussionid) {
        this.discussionid = discussionid;
    }

    public Long getResultsDiscussionId() {
        return this.resultsdiscussionid;
    }

    public void setResultsDiscussionId(Long resultsdiscussionid) {
        this.resultsdiscussionid = resultsdiscussionid;
    }

    public Long getGroupId() {
        return this.groupid;
    }

    public void setGroupId(Long groupid) {
        this.groupid = groupid;
    }


    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Proposal (" + id + ", " + createdAt + ", " + updatedAt + ", "
                + authorUserId + ", " + visible + ", " + discussionid + ", "
                + resultsdiscussionid + ", " + groupid + ")";
    }

}
