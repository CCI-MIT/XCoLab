package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.StaticContestContext;

import java.util.Date;

public interface IProposalContestPhaseAttribute {

    Long getId();

    void setId(Long id);

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getContestPhaseId();

    void setContestPhaseId(Long contestPhaseId);

    String getName();

    void setName(String name);

    Long getAdditionalId();

    void setAdditionalId(Long additionalId);

    Long getNumericValue();

    void setNumericValue(Long numericValue);

    String getStringValue();

    void setStringValue(String stringValue);

    Double getRealValue();

    void setRealValue(Double realValue);

    default Date getStartDate() {
        return StaticContestContext.getContestClient().getContestPhase(getContestPhaseId())
                .getPhaseStartDateDt();
    }
}
