package org.climatecollaboratorium.plans.events;

import org.climatecollaboratorium.events.Event;

import com.ext.portlet.model.PlanItem;

public class PlanDeletedEvent implements Event {
    private PlanItem plan;

    public PlanDeletedEvent(PlanItem plan) {
        this.plan = plan;
    }

    public PlanItem getPlan() {
        return plan;
    }

    public void setPlan(PlanItem plan) {
        this.plan = plan;
    }
    
}
