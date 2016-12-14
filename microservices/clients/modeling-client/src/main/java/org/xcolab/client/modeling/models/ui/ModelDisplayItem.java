package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * Top level class for a layout element in a simulation.  All visual elements in
 * a model (aka simulation) inherit from this class
 */
public abstract class ModelDisplayItem implements Comparable<ModelDisplayItem> {

    private final Simulation simulation;
    private Scenario scenario;
    public ModelDisplayItem(Simulation s) {
        this.simulation = s;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public Scenario getScenario() {
        return scenario;
    }

    /**
     * Setting the scenario is merely a convenience function, enabling functions
     * for retrieving scenario data via these layout classes.
     */
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        if (scenario != null && !scenario.getSimulation().equals(simulation)) {
            throw new IncompatibleScenarioException(
                    "Simulation " + simulation.getName() + " does not generate scenario " + scenario
                            .getId());
        }
        this.scenario = s;
    }

    @Override
    public int compareTo(ModelDisplayItem o) {
        return this.getOrder() - o.getOrder();
    }

    public abstract int getOrder();

    public abstract void setOrder(int o);

    public JsonObjectBuilder toJson() {
        return Json.createObjectBuilder()
                .add("name", getName())
                .add("order", getOrder());
    }

    public abstract String getName();
}
