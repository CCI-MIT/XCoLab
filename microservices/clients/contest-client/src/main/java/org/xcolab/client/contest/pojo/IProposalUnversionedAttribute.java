package org.xcolab.client.contest.pojo;

import java.sql.Timestamp;

public interface IProposalUnversionedAttribute {

    Long getId();

    void setId(Long id);

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getFirstAuthorUserId();

    void setFirstAuthorUserId(Long firstAuthorUserId);

    Long getLastAuthorUserId();

    void setLastAuthorUserId(Long lastAuthorUserId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

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
