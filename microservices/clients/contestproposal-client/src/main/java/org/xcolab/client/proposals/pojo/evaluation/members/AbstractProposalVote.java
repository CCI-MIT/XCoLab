package org.xcolab.client.proposals.pojo.evaluation.members;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

class AbstractProposalVote implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalid;
    private Long contestphaseid;
    private Long userid;
    private Integer value;
    private Timestamp createdate;
    private Boolean isvalid;
    private Timestamp confirmationemailsenddate;
    private String confirmationtoken;

    public AbstractProposalVote() {}

    public AbstractProposalVote(AbstractProposalVote value) {
        this.proposalid = value.proposalid;
        this.contestphaseid = value.contestphaseid;
        this.userid = value.userid;
        this.value = value.value;
        this.createdate = value.createdate;
        this.isvalid = value.isvalid;
        this.confirmationemailsenddate = value.confirmationemailsenddate;
        this.confirmationtoken = value.confirmationtoken;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getContestPhaseId() {
        return this.contestphaseid;
    }

    public void setContestPhaseId(Long contestphaseid) {
        this.contestphaseid = contestphaseid;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Boolean getIsValid() {
        return this.isvalid;
    }

    public void setIsValid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    public Timestamp getConfirmationEmailSendDate() {
        return this.confirmationemailsenddate;
    }

    public void setConfirmationEmailSendDate(Timestamp confirmationemailsenddate) {
        this.confirmationemailsenddate = confirmationemailsenddate;
    }

    public String getConfirmationToken() {
        return this.confirmationtoken;
    }

    public void setConfirmationToken(String confirmationtoken) {
        this.confirmationtoken = confirmationtoken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractProposalVote)) {
            return false;
        }
        AbstractProposalVote that = (AbstractProposalVote) o;
        return Objects.equals(proposalid, that.proposalid)
                && Objects.equals(contestphaseid, that.contestphaseid)
                && Objects.equals(userid, that.userid)
                && Objects.equals(value, that.value)
                && Objects.equals(createdate, that.createdate)
                && Objects.equals(isvalid, that.isvalid)
                && Objects.equals(confirmationemailsenddate, that.confirmationemailsenddate)
                && Objects.equals(confirmationtoken, that.confirmationtoken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposalid, contestphaseid, userid, value, createdate, isvalid,
                confirmationemailsenddate, confirmationtoken);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("proposalid", proposalid)
                .append("contestphaseid", contestphaseid)
                .append("userid", userid)
                .append("value", value)
                .append("createdate", createdate)
                .append("isvalid", isvalid)
                .append("confirmationemailsenddate", confirmationemailsenddate)
                .append("confirmationtoken", confirmationtoken)
                .toString();
    }
}
