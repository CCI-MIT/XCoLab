package org.xcolab.client.proposals.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalid;
    private Timestamp createdAt;
    private Timestamp updateddate;
    private Long authorid;
    private Boolean visible;
    private Long discussionid;
    private Long resultsdiscussionid;
    private Long groupid;

    public AbstractProposal() {}

    public AbstractProposal(AbstractProposal value) {
        this.proposalid = value.proposalid;
        this.createdAt = value.createdAt;
        this.updateddate = value.updateddate;
        this.authorid = value.authorid;
        this.visible = value.visible;
        this.discussionid = value.discussionid;
        this.resultsdiscussionid = value.resultsdiscussionid;
        this.groupid = value.groupid;
    }


    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedDate() {
        return this.updateddate;
    }

    public void setUpdatedDate(Timestamp updateddate) {
        this.updateddate = updateddate;
    }

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
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
        return "Proposal (" + proposalid + ", " + createdAt + ", " + updateddate + ", "
                + authorid + ", " + visible + ", " + discussionid + ", "
                + resultsdiscussionid + ", " + groupid + ")";
    }

}
