package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelPosition;

@JsonDeserialize(as = ModelPosition.class)
public interface IModelPosition {

    Long getId();

    void setId(Long id);

    Long getPositionId();

    void setPositionId(Long positionId);

    Long getModelId();

    void setModelId(Long modelId);
}
