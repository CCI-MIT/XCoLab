package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalVote;

import java.sql.Timestamp;

@JsonDeserialize(as = ProposalVote.class)
public interface IProposalVote {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getContestPhaseId();

    void setContestPhaseId(Long contestPhaseId);

    Long getUserId();

    void setUserId(Long userId);

    Integer getValue();

    void setValue(Integer value);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getVoterIp();

    void setVoterIp(String voterIp);

    String getVoterUserAgent();

    void setVoterUserAgent(String voterUserAgent);

    Boolean isIsValid();

    void setIsValid(Boolean isValid);

    Timestamp getConfirmationEmailSendDate();

    void setConfirmationEmailSendDate(Timestamp confirmationEmailSendDate);

    String getConfirmationToken();

    void setConfirmationToken(String confirmationToken);

    String getInitialValidationResult();

    void setInitialValidationResult(String initialValidationResult);

    String getLastValidationResult();

    void setLastValidationResult(String lastValidationResult);

    String getManualValidationResult();

    void setManualValidationResult(String manualValidationResult);

    Boolean isIsValidOverride();

    void setIsValidOverride(Boolean isValidOverride);
}
