package org.xcolab.view.pages.modeling.admin.form;

import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputIndexedDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesType;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;

import java.util.HashMap;
import java.util.Map;

public class UpdateModelOutputWidgetsBean {

    private Map<Long, Integer> ordersById = new HashMap<>();
    private Map<String, Integer> ordersByName = new HashMap<>();
    private Map<Long, ModelOutputSeriesType> serieTypes = new HashMap<>();
    private Map<Long, Long> associatedMetaDatas = new HashMap<>();

    public UpdateModelOutputWidgetsBean() {
    }

    public UpdateModelOutputWidgetsBean(ModelDisplay modelDisplay, Long modelId) {
        for (ModelOutputDisplayItem item : ModelsAdminController
                .getAllOutputsFromDisplay(modelDisplay)) {
            if (item instanceof ModelOutputSeriesDisplayItem) {
                ModelOutputSeriesDisplayItem typedItem = (ModelOutputSeriesDisplayItem) item;
                ordersById.put(typedItem.getMetaData().getId(), typedItem.getOrder());
                serieTypes.put(typedItem.getMetaData().getId(), typedItem.getSeriesType());
                associatedMetaDatas
                        .put(typedItem.getMetaData().getId(), typedItem.getAssociatedMetaDataId());

            } else if (item instanceof ModelOutputIndexedDisplayItem) {
                ModelOutputIndexedDisplayItem typedItem = (ModelOutputIndexedDisplayItem) item;
                ordersByName.put(typedItem.getName(), typedItem.getOrder());
            }

        }
    }

    public Map<Long, Integer> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Map<Long, Integer> ordersById) {
        this.ordersById = ordersById;
    }

    public Map<String, Integer> getOrdersByName() {
        return ordersByName;
    }

    public void setOrdersByName(Map<String, Integer> ordersByName) {
        this.ordersByName = ordersByName;
    }

    public Map<Long, ModelOutputSeriesType> getSerieTypes() {
        return serieTypes;
    }

    public void setSerieTypes(Map<Long, ModelOutputSeriesType> serieTypes) {
        this.serieTypes = serieTypes;
    }

    public Map<Long, Long> getAssociatedMetaDatas() {
        return associatedMetaDatas;
    }

    public void setAssociatedMetaDatas(Map<Long, Long> associatedMetaDatas) {
        this.associatedMetaDatas = associatedMetaDatas;
    }

}
