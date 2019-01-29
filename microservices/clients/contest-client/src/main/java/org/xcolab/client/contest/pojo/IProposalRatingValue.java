package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalRatingValue;

@JsonDeserialize(as = ProposalRatingValue.class)
public interface IProposalRatingValue {

    Long getId();

    void setId(Long id);

    Long getRatingTypeId();

    void setRatingTypeId(Long ratingTypeId);

    Long getValue();

    void setValue(Long value);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);
}
