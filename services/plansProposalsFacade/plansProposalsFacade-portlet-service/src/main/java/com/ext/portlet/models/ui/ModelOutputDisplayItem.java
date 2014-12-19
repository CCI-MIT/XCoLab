/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import java.io.Serializable;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Tuple;
import edu.mit.cci.roma.client.TupleStatus;
import edu.mit.cci.roma.client.Variable;

/**
 * All model output display items (charts, scalar items, and series items) extend
 * from this class.
 * 
 *
 * @author: jintrone
 * @date: May 25, 2010
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
     *
     * @param status
     * @return
     */
    public abstract ModelOutputErrorBehavior getErrorBehavior(TupleStatus status);


    /**
     * If the scenario is set, returns the current range error on this set.  This method
     * walks the list of tuples, searching for errors and returns the appropriate behavior
     * if an error that has an associated behavior is encountered.
     *
     * Returns null if no error is found or no behavior is found for an encountered error
     *
     * @return
     */

    public abstract ModelOutputErrorBehavior getRangeError();


    /**
     * If the scenario is set, returns the current invalid error on this set.  This method
     * walks the list of tuples, searching for errors and returns the appropriate behavior
     * if an error that has an associated behavior is encountered.
     *
     * Returns null if no error is found or no behavior is found for an encountered error
     *
     * @return
     */

    public abstract ModelOutputErrorBehavior getInvalidError();

    /**
     * Whether or not this display element should be displayed
     *
     * @return
     */
    public abstract boolean isVisible();

    /**
     * Utility function for subclasses
     * 
     * @param v
     * @param status
     * @return
     */
     protected ModelOutputErrorBehavior getError(Variable v,TupleStatus status, int index) {
        if (getScenario() == null || getErrorBehavior(status)==null || v == null) return null;
        for (Tuple e:v.getValue()) {
            if (e.getStatus(index)==status) return getErrorBehavior(status);
        }
        return null;
    }
    
    public ModelOutputChartType getChartType() {
        return ModelOutputChartType.FREE;
    }
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        
        jsonObject.put("displayItemType", getDisplayItemType().name());
        jsonObject.put("chartType", getChartType().name());
        jsonObject.put("visible", isVisible());
        
        convertErrorBehaviorToJson(jsonObject, "invalidErrorPolicy", getInvalidError());
        convertErrorBehaviorToJson(jsonObject, "rangeErrorPolicy", getRangeError());

        return jsonObject;
    }
    
    protected void convertErrorBehaviorToJson(JSONObject jsonObject, String key, ModelOutputErrorBehavior errorBehavior) {
        if (errorBehavior == null) return;
        
        JSONObject errorObject = JSONFactoryUtil.createJSONObject();
        if (errorBehavior.getPolicy() != null) {
            errorObject.put("policy", errorBehavior.getPolicy().name());
        }
        if (errorBehavior.getMessage() != null) { 
            errorObject.put("message", errorBehavior.getMessage());
        }
        
        jsonObject.put(key, errorObject);
    }

}
