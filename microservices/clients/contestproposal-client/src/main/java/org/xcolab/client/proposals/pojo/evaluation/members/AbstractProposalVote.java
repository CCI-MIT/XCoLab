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
    private String voterIp;
    private String voterUserAgent;
    private Boolean isvalid;
    private Timestamp confirmationemailsenddate;
    private String confirmationtoken;
    private String initialValidationResult;
    private String lastValidationResult;
    private Boolean isValidOverride;
    private String manualValidationResult;


    public AbstractProposalVote() {}

    public AbstractProposalVote(AbstractProposalVote value) {
        this.proposalid = value.proposalid;
        this.contestphaseid = value.contestphaseid;
        this.userid = value.userid;
        this.value = value.value;
        this.createdate = value.createdate;
        this.voterIp = value.voterIp;
        this.voterUserAgent = value.voterUserAgent;
        this.isvalid = value.isvalid;
        this.confirmationemailsenddate = value.confirmationemailsenddate;
        this.confirmationtoken = value.confirmationtoken;
        this.initialValidationResult = value.initialValidationResult;
        this.lastValidationResult = value.lastValidationResult;
        this.isValidOverride = value.isValidOverride;
        this.manualValidationResult = value.manualValidationResult;
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

    public String getVoterIp() {
        return voterIp;
    }

    public void setVoterIp(String voterIp) {
        this.voterIp = voterIp;
    }

    public String getVoterUserAgent() {
        return voterUserAgent;
    }

    public void setVoterUserAgent(String voterUserAgent) {
        this.voterUserAgent = voterUserAgent;
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

    public String getInitialValidationResult() {
        return initialValidationResult;
    }

    public void setInitialValidationResult(String initialValidationResult) {
        this.initialValidationResult = initialValidationResult;
    }

    public String getLastValidationResult() {
        return lastValidationResult;
    }

    public void setLastValidationResult(String lastValidationResult) {
        this.lastValidationResult = lastValidationResult;
    }

    public Boolean getValidOverride() {
        return isValidOverride;
    }

    public void setValidOverride(Boolean validOverride) {
        isValidOverride = validOverride;
    }

    public String getManualValidationResult() {
        return manualValidationResult;
    }

    public void setManualValidationResult(String manualValidationResult) {
        this.manualValidationResult = manualValidationResult;
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
                && Objects.equals(getValue(), that.getValue())
                && Objects.equals(createdate, that.createdate)
                && Objects.equals(voterIp, that.voterIp)
                && Objects.equals(voterUserAgent, that.voterUserAgent)
                && Objects.equals(isvalid, that.isvalid)
                && Objects.equals(confirmationemailsenddate, that.confirmationemailsenddate)
                && Objects.equals(confirmationtoken, that.confirmationtoken)
                && Objects.equals(getInitialValidationResult(), that.getInitialValidationResult())
                && Objects.equals(lastValidationResult, that.lastValidationResult)
                && Objects.equals(isValidOverride, that.isValidOverride)
                && Objects.equals(manualValidationResult, that.manualValidationResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposalid, contestphaseid, userid, getValue(), createdate, voterIp,
                voterUserAgent, isvalid, confirmationemailsenddate, confirmationtoken,
                getInitialValidationResult(), lastValidationResult, isValidOverride,
                manualValidationResult);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("proposalid", proposalid)
                .append("contestphaseid", contestphaseid)
                .append("userid", userid)
                .append("value", value)
                .append("createdate", createdate)
                .append("voterIp", voterIp)
                .append("voterUserAgent", voterUserAgent)
                .append("isvalid", isvalid)
                .append("confirmationemailsenddate", confirmationemailsenddate)
                .append("confirmationtoken", confirmationtoken)
                .append("initialValidationResult", initialValidationResult)
                .append("lastValidationResult", lastValidationResult)
                .append("isValidOverride", isValidOverride)
                .append("manualValidationResult", manualValidationResult)
                .toString();
    }
}
