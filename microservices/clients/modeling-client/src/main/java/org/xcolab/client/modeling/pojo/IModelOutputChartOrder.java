package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelOutputChartOrder;

@JsonDeserialize(as = ModelOutputChartOrder.class)
public interface IModelOutputChartOrder {

    Long getId();

    void setId(Long id);

    Long getModelId();

    void setModelId(Long modelId);

    String getModelOutputLabel();

    void setModelOutputLabel(String modelOutputLabel);

    Integer getModelOutputChartOrder();

    void setModelOutputChartOrder(Integer modelOutputChartOrder);

    String getModelIndexRangePolicy();

    void setModelIndexRangePolicy(String modelIndexRangePolicy);

    String getModelIndexRangeMessage();

    void setModelIndexRangeMessage(String modelIndexRangeMessage);

    String getModelIndexErrorPolicy();

    void setModelIndexErrorPolicy(String modelIndexErrorPolicy);

    String getModelIndexErrorMessage();

    void setModelIndexErrorMessage(String modelIndexErrorMessage);

    Boolean isModelChartIsVisible();

    void setModelChartIsVisible(Boolean modelChartIsVisible);
}
