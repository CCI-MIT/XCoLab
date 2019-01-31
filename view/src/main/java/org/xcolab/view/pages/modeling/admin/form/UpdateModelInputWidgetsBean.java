package org.xcolab.view.pages.modeling.admin.form;

import org.xcolab.client.modeling.StaticModelingContext;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputIndividualDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelInputWidgetType;

import java.util.HashMap;
import java.util.Map;

public class UpdateModelInputWidgetsBean {

    private Map<Long, ModelInputWidgetType> widgets = new HashMap<>();
    private Map<Long, Long> groups = new HashMap<>();
    private Map<Long, Integer> orders = new HashMap<>();
    private String customInputWidgets;

    public UpdateModelInputWidgetsBean() {
    }

    public UpdateModelInputWidgetsBean(ModelDisplay display, long modelId) {
        for (ModelInputDisplayItem item : display.getAllIndividualInputs()) {
            widgets.put(item.getMetaData().getId(), item.getType());
            orders.put(item.getMetaData().getId(), item.getOrder());
            groups.put(item.getMetaData().getId(),
                    ((ModelInputIndividualDisplayItem) item).getGroupId());
        }
        customInputWidgets = StaticModelingContext.getModelingClient()
                .getModelPreference(modelId).getCustomInputsDefinition();
    }

    public Map<Long, ModelInputWidgetType> getWidgets() {
        return widgets;
    }

    public void setWidgets(Map<Long, ModelInputWidgetType> widgets) {
        this.widgets = widgets;
    }

    public Map<Long, Long> getGroups() {
        return groups;
    }

    public void setGroups(Map<Long, Long> groups) {
        this.groups = groups;
    }

    public Map<Long, Integer> getOrders() {
        return orders;
    }

    public void setOrders(Map<Long, Integer> orders) {
        this.orders = orders;
    }

    public String getCustomInputWidgets() {
        return customInputWidgets;
    }

    public void setCustomInputWidgets(String customInputWidgets) {
        this.customInputWidgets = customInputWidgets;
    }
}
