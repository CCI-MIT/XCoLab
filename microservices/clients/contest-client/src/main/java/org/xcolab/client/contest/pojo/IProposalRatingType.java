package org.xcolab.client.contest.pojo;

public interface IProposalRatingType {

    Long getId();

    void setId(Long id);

    String getLabel();

    void setLabel(String label);

    String getDescription();

    void setDescription(String description);

    Integer getJudgeType();

    void setJudgeType(Integer judgeType);

    Boolean getIsActive();

    void setIsActive(Boolean isActive);
}
