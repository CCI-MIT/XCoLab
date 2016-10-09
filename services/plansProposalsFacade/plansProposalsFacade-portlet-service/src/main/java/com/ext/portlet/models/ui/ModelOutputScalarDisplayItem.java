package com.ext.portlet.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Very simple wrapper class around a singleton output variable.  Not backed,
 * no additional display information is necessary (currently).
 */
public class ModelOutputScalarDisplayItem extends ModelOutputDisplayItem {

    private static final Log _log = LogFactoryUtil.getLog(ModelOutputScalarDisplayItem.class);
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
            return ModelUIFactory.getVariableForMetaData(getScenario(), getMetaData(), false);
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

    public void setVisible(boolean visible) {
        _log.warn("Setting visibility on scalar items is not currently supported");
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("id", md.getId());
        jsonObject.put("outputType", "SCALAR");

        return jsonObject;
    }
}
