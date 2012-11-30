package org.climatecollaboratorium.plans.migration;

import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanCopyItem {
    
    private PlanItem plan;
    private boolean selected;
    
    public PlanCopyItem(PlanItem plan) {
        this.plan = plan;
        
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean val) {
        selected = val;
    }
    
    public String getPlanName() throws SystemException {
        return plan.getName();
    }
    
    public Long getPlanId() {
        return plan.getPlanId();
    }
    
    public PlanItem getPlan() {
        return plan;
    }

}
