package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ContestCollectionCard;

@JsonDeserialize(as = ContestCollectionCard.class)
public interface IContestCollectionCard {

    Long getId();

    void setId(Long id);

    Long getParent();

    void setParent(Long parent);

    Long getBigOntologyTerm();

    void setBigOntologyTerm(Long bigOntologyTerm);

    Long getSmallOntologyTerm();

    void setSmallOntologyTerm(Long smallOntologyTerm);

    String getDescription();

    void setDescription(String description);

    String getShortName();

    void setShortName(String shortName);

    Boolean isVisible();

    void setVisible(Boolean visible);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);

    Long getOntologyTermToLoad();

    void setOntologyTermToLoad(Long ontologyTermToLoad);

    Boolean isOnlyFeatured();

    void setOnlyFeatured(Boolean onlyFeatured);
}
