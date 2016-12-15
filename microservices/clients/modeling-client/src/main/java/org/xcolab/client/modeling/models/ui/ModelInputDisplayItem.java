package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;

import org.xcolab.util.json.NullsafeJsonObjectBuilder;

import java.io.Serializable;

import javax.json.JsonObjectBuilder;

/**
 * Top level class for all input elements in a model / simulation
 */
public abstract class ModelInputDisplayItem extends ModelDisplayItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MetaData md;

    public ModelInputDisplayItem(Simulation sim, MetaData md) {
        super(sim);
        this.md = md;
    }

    /**
     * All input elements may have meta data associated with them.
     */
    public MetaData getMetaData() {
        return md;
    }

    @Override
    public JsonObjectBuilder toJson() {
        return NullsafeJsonObjectBuilder.of(super.toJson())
                .add("description", getDescription())
                .add("displayItemType", getDisplayItemType().name())
                .add("widgetType", getType().name());
    }

    /**
     * Convenience method - reaches through to associated metadata
     */
    @Override
    public String getName() {
        return md.getName();
    }

    /**
     * Convenience method - reaches through to associated metadata
     */
    public String getDescription() {
        return md.getDescription();
    }

    /**
     * Returns display item type.
     *
     * @return ModelDisplayItemType
     */
    public abstract ModelInputDisplayItemType getDisplayItemType();

    /**
     * Gets input widget type
     */
    public ModelInputWidgetType getType() {
        return ModelInputWidgetType.TEXT_FIELD;
    }

    /**
     * Sets input widget type
     */
    public void setType(ModelInputWidgetType type) {
        // by default do nothing, in individual display item appropriate action should be taken
    }
}
