package org.climatecollaboratorium.facelets.simulations;

import edu.mit.cci.roma.client.*;

import org.climatecollaboratorium.events.Event;

public class ScenarioSavedEvent implements Event {
    private Scenario scenario;

    public ScenarioSavedEvent(Scenario scenario) {
        this.setScenario(scenario);
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

}
