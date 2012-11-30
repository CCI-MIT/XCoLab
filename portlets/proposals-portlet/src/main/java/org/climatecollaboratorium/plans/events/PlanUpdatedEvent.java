package org.climatecollaboratorium.plans.events;

import org.climatecollaboratorium.events.Event;

import com.ext.portlet.plans.model.PlanItem;

public class PlanUpdatedEvent implements Event {
    private PlanItem plan;
    
    public PlanUpdatedEvent(PlanItem plan) {
        this.plan = plan;
    }
    
    public void setPlan(PlanItem plan) {
        this.plan = plan;
    }
    public PlanItem getPlan() {
        return plan;
    }

}
