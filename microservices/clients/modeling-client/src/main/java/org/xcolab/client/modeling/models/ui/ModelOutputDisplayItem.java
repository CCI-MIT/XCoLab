package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Tuple;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;

import org.xcolab.util.json.JsonUtil;

import java.io.Serializable;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * All model output display items (charts, scalar items, and series items) extend
 * from this class.
 */
public abstract class ModelOutputDisplayItem extends ModelDisplayItem implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelOutputDisplayItem(Simulation s) {
        super(s);
    }
    
    public abstract ModelOutputDisplayItemType getDisplayItemType();

    /**
     * Specifies the error behavior for this display item given an error status.
     * Returns null if no associated behavior.
     */
    public abstract ModelOutputErrorBehavior getErrorBehavior(TupleStatus status);


    /**
     * If the scenario is set, returns the current range error on this set.  This method
     * walks the list of tuples, searching for errors and returns the appropriate behavior
     * if an error that has an associated behavior is encountered.
     *
     * Returns null if no error is found or no behavior is found for an encountered error
     */

    public abstract ModelOutputErrorBehavior getRangeError();


    /**
     * If the scenario is set, returns the current invalid error on this set.  This method
     * walks the list of tuples, searching for errors and returns the appropriate behavior
     * if an error that has an associated behavior is encountered.
     *
     * Returns null if no error is found or no behavior is found for an encountered error
     */

    public abstract ModelOutputErrorBehavior getInvalidError();

    /**
     * Whether or not this display element should be displayed
     */
    public abstract boolean isVisible();

    /**
     * Utility function for subclasses
     */
     protected ModelOutputErrorBehavior getError(Variable v,TupleStatus status, int index) {
        if (getScenario() == null || getErrorBehavior(status)==null || v == null) {
            return null;
        }
        for (Tuple e:v.getValue()) {
            if (e.getStatus(index)==status) {
                return getErrorBehavior(status);
            }
        }
        return null;
    }
    
    public ModelOutputChartType getChartType() {
        return ModelOutputChartType.FREE;
    }

    @Override
    public JsonObjectBuilder toJson() {
        return JsonUtil.nullsafe(super.toJson())
                .add("displayItemType", getDisplayItemType().name())
                .add("chartType", getChartType().name())
                .add("visible", isVisible())
                .add("invalidErrorPolicy", convertErrorBehaviorToJson(getInvalidError()))
                .add("rangeErrorPolicy", convertErrorBehaviorToJson(getRangeError()));
    }

    
    protected JsonObjectBuilder convertErrorBehaviorToJson(ModelOutputErrorBehavior errorBehavior) {
        if (errorBehavior == null) {
            return null;
        }

        final JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        if (errorBehavior.getPolicy() != null) {
            jsonBuilder.add("policy", errorBehavior.getPolicy().name());
        }
        if (errorBehavior.getMessage() != null) { 
            jsonBuilder.add("message", errorBehavior.getMessage());
        }
        return jsonBuilder;
    }

}
