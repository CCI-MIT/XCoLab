package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelInputItem;

@JsonDeserialize(as = ModelInputItem.class)
public interface IModelInputItem {

    Long getId();

    void setId(Long id);

    Long getModelId();

    void setModelId(Long modelId);

    Long getModelInputItemId();

    void setModelInputItemId(Long modelInputItemId);

    Long getModelGroupId();

    void setModelGroupId(Long modelGroupId);

    Integer getDisplayItemOrder();

    void setDisplayItemOrder(Integer displayItemOrder);

    String getType();

    void setType(String type);

    String getProperties();

    void setProperties(String properties);
}
