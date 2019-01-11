package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelDiscussion;

@JsonDeserialize(as = ModelDiscussion.class)
public interface IModelDiscussion {

    Long getId();

    void setId(Long id);

    Long getModelId();

    void setModelId(Long modelId);

    Long getCategoryId();

    void setCategoryId(Long categoryId);
}
