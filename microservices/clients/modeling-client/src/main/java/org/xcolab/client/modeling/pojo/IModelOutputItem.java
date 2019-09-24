package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.modeling.pojo.tables.pojos.ModelOutputItem;

@JsonDeserialize(as = ModelOutputItem.class)
public interface IModelOutputItem {

    Long getModelOutputItemModifierPk();

    void setModelOutputItemModifierPk(Long modelOutputItemModifierPk);

    Long getModelId();

    void setModelId(Long modelId);

    Long getModelOutputItemId();

    void setModelOutputItemId(Long modelOutputItemId);

    Integer getModelOutputItemOrder();

    void setModelOutputItemOrder(Integer modelOutputItemOrder);

    String getModelItemRangePolicy();

    void setModelItemRangePolicy(String modelItemRangePolicy);

    String getModelItemRangeMessage();

    void setModelItemRangeMessage(String modelItemRangeMessage);

    String getModelItemErrorPolicy();

    void setModelItemErrorPolicy(String modelItemErrorPolicy);

    String getModelItemErrorMessage();

    void setModelItemErrorMessage(String modelItemErrorMessage);

    String getModelItemLabelFormat();

    void setModelItemLabelFormat(String modelItemLabelFormat);

    Boolean isModelItemIsVisible();

    void setModelItemIsVisible(Boolean modelItemIsVisible);

    String getItemType();

    void setItemType(String itemType);

    Long getRelatedOutputItem();

    void setRelatedOutputItem(Long relatedOutputItem);
}
