package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;

@JsonDeserialize(as = OntologyTermWrapper.class)
public interface IOntologyTerm {

    Long getId();

    void setId(Long id);

    Long getParentId();

    void setParentId(Long parentId);

    Long getOntologySpaceId();

    void setOntologySpaceId(Long ontologySpaceId);

    String getName();

    void setName(String name);

    String getDescriptionUrl();

    void setDescriptionUrl(String descriptionUrl);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
