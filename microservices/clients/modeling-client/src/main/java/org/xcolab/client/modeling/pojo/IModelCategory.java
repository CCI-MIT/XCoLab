package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = org.xcolab.client.modeling.pojo.tables.pojos.ModelCategory.class)
public interface IModelCategory {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Integer getDisplayWeight();

    void setDisplayWeight(Integer displayWeight);
}
