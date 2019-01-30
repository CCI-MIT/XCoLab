package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ContestPhaseRibbonType;

@JsonDeserialize(as = ContestPhaseRibbonType.class)
public interface IContestPhaseRibbonType {

    Long getId();

    void setId(Long id);

    Integer getRibbon();

    void setRibbon(Integer ribbon);

    String getTitle();

    void setTitle(String title);

    String getHoverText();

    void setHoverText(String hoverText);

    Boolean isShowText();

    void setShowText(Boolean showText);

    String getDescription();

    void setDescription(String description);

    Boolean isCopyOnPromote();

    void setCopyOnPromote(Boolean copyOnPromote);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
