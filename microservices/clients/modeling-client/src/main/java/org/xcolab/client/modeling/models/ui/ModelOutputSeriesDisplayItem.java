package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;

import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.StaticModelingContext;
import org.xcolab.client.modeling.pojo.IModelOutputItem;
import org.xcolab.client.modeling.pojo.tables.pojos.ModelOutputItem;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class ModelOutputSeriesDisplayItem extends ModelOutputDisplayItem {

    private final MetaData metaData;
    private final Map<TupleStatus, ModelOutputErrorBehavior> errorBehaviors = new HashMap<>();
    private IModelOutputItem item;

    /**
     * Clients should not need to call this method directly.  Instead, {@link
     * ModelUIFactory} should be used to generate the display classes, and
     * any subsequent modifications made there.
     *
     * Note that this constructor implicitly creates a backing store.
     */
    ModelOutputSeriesDisplayItem(Simulation s, MetaData metaData) {
        super(s);
        this.metaData = metaData;
        try {
            item = StaticModelingContext.getModelingClient().getOutputItem(metaData.getId());
        } catch (UncheckedEntityNotFoundException e) {
            createPersistence();
        }
    }

    private void createPersistence() {
        item = new ModelOutputItem();
        item.setModelId(getSimulation().getId());
        item.setModelOutputItemId(metaData.getId());
        item.setModelItemIsVisible(true);
        item = StaticModelingContext.getModelingClient().createModelOutputItem(item);
    }

    @Override
    public int getOrder() {
        return item != null ? item.getModelOutputItemOrder() : -1;
    }

    /**
     * Sets the display order for this display item within its parent container.  Generally
     * this will boil down to the order in the legend.
     */
    @Override
    public void setOrder(int i) {
        item.setModelOutputItemOrder(i);
        StaticModelingContext.getModelingClient().updateModelOutputItem(item);
    }

    @Override
    public String getName() {
        return metaData.getName();
    }

    /**
     * Get additional information about this series
     */
    public ModelOutputSeriesType getSeriesType() {
        return (item.getItemType() == null || item.getItemType().trim().equals("")) ?
                ModelOutputSeriesType.NORMAL : ModelOutputSeriesType
                .valueOf(item.getItemType());
    }

    /**
     * Sets additional information about this series; this setting is persisted
     * in the backing store.
     */
    public void setSeriesType(ModelOutputSeriesType type) {
        if (item != null) {
            item.setItemType(type.name());
            StaticModelingContext.getModelingClient().updateModelOutputItem(item);
        }
    }

    public void setAssociatedMetaData(MetaData md) {
        item.setRelatedOutputItem(md.getId());
        StaticModelingContext.getModelingClient().updateModelOutputItem(item);
    }

    @Override
    public ModelOutputDisplayItemType getDisplayItemType() {
        return ModelOutputDisplayItemType.SERIES;
    }

    @Override
    public ModelOutputErrorBehavior getErrorBehavior(TupleStatus status) {
        if (!errorBehaviors.containsKey(status)) {
            ModelOutputErrorBehavior behavior = ModelOutputErrorBehavior.getBehavior(status, item);
            errorBehaviors.put(status, behavior);
        }
        return errorBehaviors.get(status);
    }

    @Override
    public ModelOutputErrorBehavior getRangeError() {
        return getError(getVariable(), TupleStatus.OUT_OF_RANGE, 1);
    }

    public Variable getVariable() {
        if (getScenario() == null || getMetaData() == null) {
            return null;
        }
        return ModelUIFactory
                .getVariableForMetaData(getScenario(), getMetaData(), false);
    }

    public MetaData getMetaData() {
        return metaData;
    }

    @Override
    public ModelOutputErrorBehavior getInvalidError() {
        return getError(getVariable(), TupleStatus.INVALID, 1);
    }

    @Override
    public boolean isVisible() {
        if (item.isModelItemIsVisible()) {
            setVisible(true);
        }

        return item.isModelItemIsVisible();
    }

    public void setVisible(boolean b) {
        item.setModelItemIsVisible(b);
        StaticModelingContext.getModelingClient().updateModelOutputItem(item);

    }

    @Override
    public ModelOutputChartType getChartType() {
        return ModelOutputChartType.TIME_SERIES;
    }

    @Override
    public JsonObjectBuilder toJson() {
        JsonObjectBuilder jsonBuilder = super.toJson();
        if (getVariable() != null) {
            jsonBuilder.add("variable", ModelUIFactory.convertToJson(getVariable()));
        } else {
            jsonBuilder.add("variable", Json.createObjectBuilder()
                    .add("metaData", ModelUIFactory.convertToJson(getMetaData())));
        }
        jsonBuilder.add("outputType", "SERIES");
        jsonBuilder.add("labelFormatString", getLabelFormatString());
        Long associatedMetaDataId = getAssociatedMetaDataId();
        if (associatedMetaDataId != null && associatedMetaDataId > 0) {
            jsonBuilder.add("associatedMetaDataId", associatedMetaDataId);
        }

        return jsonBuilder;
    }

    public Long getAssociatedMetaDataId() {
        return item.getRelatedOutputItem();
    }

    public void setAssociatedMetaDataId(Long id) {
        item.setRelatedOutputItem(id);
        StaticModelingContext.getModelingClient().updateModelOutputItem(item);
    }

    private String getLabelFormatString() {
        return item.getModelItemLabelFormat();
    }
}
