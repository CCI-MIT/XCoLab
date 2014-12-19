/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.attributes;

import com.ext.portlet.model.PlanItem;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author: jintrone
 * @date: Feb 28, 2010
 */
public interface AttributeFunction<T> {

    /**
     * This method should be used to fetch value of attribute for scenario.
     * @param scenarioId id of scenario for which attribute value is to be fetched
     * @return value of an attribute
     * @throws SystemException
     */
    public T process(String scenarioId) throws SystemException;

    /**
     * This method should be used to fetch value of attribute for plan property.
     * @param plan TODO
     * @return value of an attribute
     * @throws SystemException
     */
    public T process(PlanItem plan) throws SystemException;
    
    /**
     * Returns true if value of an attribute is fetched from scenario.
     * @return true if value of an attribute is fetched from scenario.
     */
    public boolean isFromScenario();
   
    /**
     * Returns true if value of an attribute is fetched from plan property.
     * @return true if value of an attribute is fetched from plan property.
     */
    public boolean isFromPlan();
    
}
