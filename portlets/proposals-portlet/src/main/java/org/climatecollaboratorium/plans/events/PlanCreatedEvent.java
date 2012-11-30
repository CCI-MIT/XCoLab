package org.climatecollaboratorium.plans.events;

import org.climatecollaboratorium.events.Event;

import com.ext.portlet.plans.model.PlanItem;

public class PlanCreatedEvent implements Event {
    private PlanItem planItem;

    public PlanCreatedEvent(PlanItem planItem) {
        this.planItem = planItem;
    }

    public void setPlanItem(PlanItem planItem) {
        this.planItem = planItem;
    }

    public PlanItem getPlanItem() {
        return planItem;
    }

    
}
