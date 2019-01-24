package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.OntologySpace;

@JsonDeserialize(as = OntologySpace.class)
public interface IOntologySpace {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
