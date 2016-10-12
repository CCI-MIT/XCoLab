package org.xcolab.client.proposals.pojo;

import java.sql.Timestamp;

class AbstractProposal {

    private Long proposalid;
    private Timestamp createdate;
    private Timestamp updateddate;
    private Integer currentversion;
    private Long authorid;
    private Boolean visible;
    private Long discussionid;
    private Long resultsdiscussionid;
    private Long judgediscussionid;
    private Long fellowdiscussionid;
    private Long advisordiscussionid;
    private Long groupid;

    public AbstractProposal() {}

    public AbstractProposal(AbstractProposal value) {
        this.proposalid = value.proposalid;
        this.createdate = value.createdate;
        this.updateddate = value.updateddate;
        this.currentversion = value.currentversion;
        this.authorid = value.authorid;
        this.visible = value.visible;
        this.discussionid = value.discussionid;
        this.resultsdiscussionid = value.resultsdiscussionid;
        this.judgediscussionid = value.judgediscussionid;
        this.fellowdiscussionid = value.fellowdiscussionid;
        this.advisordiscussionid = value.advisordiscussionid;
        this.groupid = value.groupid;
    }

    public AbstractProposal(
            Long proposalid,
            Timestamp createdate,
            Timestamp updateddate,
            Integer currentversion,
            Long authorid,
            Boolean visible,
            Long discussionid,
            Long resultsdiscussionid,
            Long judgediscussionid,
            Long fellowdiscussionid,
            Long advisordiscussionid,
            Long groupid
    ) {
        this.proposalid = proposalid;
        this.createdate = createdate;
        this.updateddate = updateddate;
        this.currentversion = currentversion;
        this.authorid = authorid;
        this.visible = visible;
        this.discussionid = discussionid;
        this.resultsdiscussionid = resultsdiscussionid;
        this.judgediscussionid = judgediscussionid;
        this.fellowdiscussionid = fellowdiscussionid;
        this.advisordiscussionid = advisordiscussionid;
        this.groupid = groupid;
    }


    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getUpdatedDate() {
        return this.updateddate;
    }

    public void setUpdatedDate(Timestamp updateddate) {
        this.updateddate = updateddate;
    }

    public Integer getCurrentVersion() {
        return this.currentversion;
    }

    public void setCurrentVersion(Integer currentversion) {
        this.currentversion = currentversion;
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

    public Long getJudgeDiscussionId() {
        return this.judgediscussionid;
    }

    public void setJudgeDiscussionId(Long judgediscussionid) {
        this.judgediscussionid = judgediscussionid;
    }

    public Long getFellowDiscussionId() {
        return this.fellowdiscussionid;
    }

    public void setFellowDiscussionId(Long fellowdiscussionid) {
        this.fellowdiscussionid = fellowdiscussionid;
    }

    public Long getAdvisorDiscussionId() {
        return this.advisordiscussionid;
    }

    public void setAdvisorDiscussionId(Long advisordiscussionid) {
        this.advisordiscussionid = advisordiscussionid;
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
        StringBuilder sb = new StringBuilder("Proposal (");

        sb.append(proposalid);
        sb.append(", ").append(createdate);
        sb.append(", ").append(updateddate);
        sb.append(", ").append(currentversion);
        sb.append(", ").append(authorid);
        sb.append(", ").append(visible);
        sb.append(", ").append(discussionid);
        sb.append(", ").append(resultsdiscussionid);
        sb.append(", ").append(judgediscussionid);
        sb.append(", ").append(fellowdiscussionid);
        sb.append(", ").append(advisordiscussionid);
        sb.append(", ").append(groupid);

        sb.append(")");
        return sb.toString();
    }

}
