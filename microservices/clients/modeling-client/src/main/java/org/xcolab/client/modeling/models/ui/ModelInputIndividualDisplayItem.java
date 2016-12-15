package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.pojo.ModelInputItem;

import java.io.IOException;
import java.io.Serializable;

import javax.json.JsonObjectBuilder;

/**
 * Layout information for individual (non-group) display elements.
 */
public class ModelInputIndividualDisplayItem extends ModelInputDisplayItem implements Serializable {

    private static final long serialVersionUID = 1L;
    ModelInputItem item;


    public ModelInputIndividualDisplayItem(ModelInputItem item) throws IOException {
        super(ModelingClientUtil.getModel(item),
                ModelingClientUtil.getMetaData(item));
        this.item = item;
    }

    /**
     * This is the preferred means for creating a new, non-group display item. Note that calling
     * {@link ModelInputGroupDisplayItem#addDisplayItem(MetaData,
     * ModelInputWidgetType)} will call this code and then set the group id on the item.
     */
    public static ModelInputIndividualDisplayItem create(Simulation sim, MetaData md,
            ModelInputWidgetType type) throws IOException {
        ModelInputItem item = new ModelInputItem();
        item.setModelId(sim.getId());
        item.setModelInputItemID(md.getId());
        item.setType_(type.name());
        ModelingClientUtil.createModelInputItem(item);
        return new ModelInputIndividualDisplayItem(item);
    }

    /**
     * The order of this element within its parent container, which may either be a group
     * or the top level display element.
     */
    @Override
    public int getOrder() {
        return item.getDisplayItemOrder();
    }

    /**
     * Set the display position of this element.  Updates the backing store.
     */
    @Override
    public void setOrder(int order) {
        item.setDisplayItemOrder(order);
        ModelingClientUtil.updateModelInputItem(item);
    }

    @Override
    public JsonObjectBuilder toJson() {
        JsonObjectBuilder jsonBuilder = super.toJson();

        jsonBuilder.add("description", getDescription());
        jsonBuilder.add("groupId", getGroupId());
        if (getVariable() != null) {
            jsonBuilder.add("value", ModelUIFactory.convertToJson(getVariable()));
        }
        if (getMetaData() != null) {
            jsonBuilder.add("metaData", ModelUIFactory.convertToJson(getMetaData()));
        }

        String maxLabel = getProperty(ModelWidgetProperty.Slider.MAX_LABEL);
        String minLabel = getProperty(ModelWidgetProperty.Slider.MIN_LABEL);

        if (maxLabel != null) {
            jsonBuilder.add("maxLabel", maxLabel);
        }
        if (minLabel != null) {
            jsonBuilder.add("minLabel", minLabel);
        }
        return jsonBuilder;
    }

    /**
     * Returns the variable associated with this element if a "setScenario" has
     * been called with a valid scenario.
     */
    public Variable getVariable() {
        if (getScenario() != null) {
            return ModelUIFactory
                    .getVariableForMetaData(getScenario(), getMetaData(), true);
        }
        return null;
    }

    /**
     * Returns display item type.
     */
    @Override
    public ModelInputDisplayItemType getDisplayItemType() {
        return ModelInputDisplayItemType.INDIVIDUAL;
    }

    /**
     * The widget type for this element
     */
    @Override
    public ModelInputWidgetType getType() {
        return item.getType_() == null ? ModelInputWidgetType.TEXT_FIELD
                : ModelInputWidgetType.valueOf(item.getType_());
    }

    /**
     * Sets the widget type for this element.  Updates the backing store.
     */
    @Override
    public void setType(ModelInputWidgetType type) {
        item.setType_(type.name());
        ModelingClientUtil.updateModelInputItem(item);
    }

    /**
     * Returns group ID for given input (can be null).
     */
    public Long getGroupId() {
        return item.getModelGroupId();
    }

    /**
     * Sets group ID.
     */
    public void setGroupId(Long modelGroupId) {
        if (modelGroupId != null && modelGroupId <= 0) {
            modelGroupId = 0L;
        }
        item.setModelGroupId(modelGroupId);
        ModelingClientUtil.updateModelInputItem(item);
    }

    public String getProperty(ModelWidgetProperty prop) {
        return ModelingClientUtil.getPropertyMap(item).get(prop.toString());
    }

}
