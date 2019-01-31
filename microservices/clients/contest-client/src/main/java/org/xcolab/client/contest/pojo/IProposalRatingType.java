package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalRatingType;

@JsonDeserialize(as = ProposalRatingType.class)
public interface IProposalRatingType {

    Long getId();

    void setId(Long id);

    String getLabel();

    void setLabel(String label);

    String getDescription();

    void setDescription(String description);

    Integer getJudgeType();

    void setJudgeType(Integer judgeType);

    Boolean isIsActive();

    void setIsActive(Boolean isActive);
}
