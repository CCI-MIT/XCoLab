package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ClientRepository;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.pojo.ModelOutputItem;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class ModelOutputSeriesDisplayItem extends ModelOutputDisplayItem {

    private final MetaData metaData;
    private final Map<TupleStatus, ModelOutputErrorBehavior> errorBehaviors = new HashMap<>();
    ModelOutputItem item;

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
            item = ModelingClientUtil.getOutputItem(metaData);
        } catch (UncheckedEntityNotFoundException e) {
            createPersistence();
        }
    }

    private void createPersistence() {
        item = new ModelOutputItem();
        item.setModelId(getSimulation().getId());
        item.setModelOutputItemId(metaData.getId());
        item.setModelItemIsVisible(true);
        item = ModelingClientUtil.createModelOutputItem(item);
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
        ModelingClientUtil.updateModelOutputItem(item);
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
            ModelingClientUtil.updateModelOutputItem(item);
        }
    }

    /**
     * If this metadata is "about" another piece of meta data within the same simulation,
     * this method will return that metadata.
     */
    public MetaData getAssociatedMetaData() throws IOException {
        long l = item.getRelatedOutputItem();
        // FIXME CollaboratoriumModelingService won't work as PropsUtil can't be found from portlet
        //return l==null?null:CollaboratoriumModelingService.repository().getMetaData(item
        // .getRelatedOutputItem());
        return l <= 0 ? null : ClientRepository.instance().getMetaData(item.getRelatedOutputItem());
    }

    public void setAssociatedMetaData(MetaData md) {
        item.setRelatedOutputItem(md.getId());
        ModelingClientUtil.updateModelOutputItem(item);
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
        if (item.getModelItemIsVisible()) {
            setVisible(true);
        }

        return item.getModelItemIsVisible();
    }

    public void setVisible(boolean b) {
        item.setModelItemIsVisible(b);
        ModelingClientUtil.updateModelOutputItem(item);

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
        ModelingClientUtil.updateModelOutputItem(item);
    }

    public String getLabelFormatString() {
        return item.getModelItemLabelFormat();
    }

    public void setLabelFormatString(String format) {
        item.setModelItemLabelFormat(format);
        ModelingClientUtil.updateModelOutputItem(item);
    }

    public void setErrorBehavior(TupleStatus status, ErrorPolicy policy, String msg) {
        if (status == TupleStatus.OUT_OF_RANGE) {
            item.setModelItemRangeMessage(msg);
            item.setModelItemRangePolicy(policy != null ? policy.name() : null);
        } else if (status == TupleStatus.INVALID) {
            item.setModelItemErrorMessage(msg);
            item.setModelItemErrorPolicy(policy != null ? policy.name() : null);
        }
        errorBehaviors.remove(status);
        ModelingClientUtil.updateModelOutputItem(item);
    }

}
