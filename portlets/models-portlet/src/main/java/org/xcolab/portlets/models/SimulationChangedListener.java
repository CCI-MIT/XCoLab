package org.xcolab.portlets.models;

import com.ext.portlet.models.ui.ModelDisplay;

import edu.mit.cci.simulation.client.Simulation;


public interface SimulationChangedListener {
    public void onSimulationChanged(Simulation s, ModelDisplay md);

}
