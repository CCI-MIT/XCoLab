package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObjectBuilder;

/**
 * Very simple wrapper class around a singleton output variable.  Not backed,
 * no additional display information is necessary (currently).
 */
public class ModelOutputScalarDisplayItem extends ModelOutputDisplayItem {

    private static final Logger _log = LoggerFactory.getLogger(ModelOutputScalarDisplayItem.class);
    private final MetaData md;

    /**
     * Clients should not need to call this constructor directly.
     */
    ModelOutputScalarDisplayItem(Simulation s, MetaData d) {
        super(s);
        this.md = d;
    }

    public Variable getVariable() {
        if (getScenario() != null) {
            return ModelUIFactory
                    .getVariableForMetaData(getScenario(), getMetaData(), false);
        }
        return null;
    }

    public MetaData getMetaData() {
        return md;
    }

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public void setOrder(int o) {
        _log.warn("Setting order on scalar items is not currently supported");
    }

    @Override
    public String getName() {
        return md.getName();
    }

    @Override
    public ModelOutputDisplayItemType getDisplayItemType() {
        return ModelOutputDisplayItemType.SCALAR;
    }

    @Override
    public ModelOutputErrorBehavior getErrorBehavior(TupleStatus status) {
        return null;
    }

    @Override
    public ModelOutputErrorBehavior getRangeError() {
        return null;
    }

    @Override
    public ModelOutputErrorBehavior getInvalidError() {
        return null;
    }


    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public JsonObjectBuilder toJson() {
        return super.toJson()
                .add("id", md.getId())
                .add("outputType", "SCALAR");
    }
}
