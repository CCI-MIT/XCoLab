package org.climatecollaboratorium.plans.migration;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
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
        return PlanItemLocalServiceUtil.getName(plan);
    }
    
    public Long getPlanId() {
        return plan.getPlanId();
    }
    
    public PlanItem getPlan() {
        return plan;
    }

}
