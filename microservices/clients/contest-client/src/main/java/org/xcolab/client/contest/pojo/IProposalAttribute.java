package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalAttribute;

@JsonDeserialize(as = ProposalAttribute.class)
public interface IProposalAttribute {

    Long getId();

    void setId(Long id);

    Long getProposalId();

    void setProposalId(Long proposalId);

    Integer getVersion();

    void setVersion(Integer version);

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
}
