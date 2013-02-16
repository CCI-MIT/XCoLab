/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;

/**
 * Top level class for a layout element in a simulation.  All visual elements in
 * a model (aka simulation) inherit from this class
 *
 *
 *
 * @author: jintrone
 * @date: May 24, 2010
 */
public abstract class ModelDisplayItem implements Comparable<ModelDisplayItem> {

    public abstract String getName();


    private Simulation sim;
    private Scenario scen;

    public ModelDisplayItem(Simulation s) {
      this.sim = s;
    }

    public Simulation getSimulation() {
       return sim;
    }


    /**
     * Setting the scenario is merely a convenience function, enabling functions
     * for retrieving scenario data via these layout classes.
     *
     * @param s
     * @throws IncompatibleScenarioException
     */
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        if (scen != null && !scen.getSimulation().equals(sim)) {
            throw new IncompatibleScenarioException("Simulation "+sim.getName()+" does not generate scenario "+scen.getId());
        }
      this.scen = s;
    }

    public Scenario getScenario() {
      return scen;
    }



     public int compareTo(ModelDisplayItem o) {
         return this.getOrder() - o.getOrder();
     }

    public abstract int getOrder();

    public abstract void setOrder(int o) throws SystemException;
}
