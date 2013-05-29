package org.climatecollaboratorium.plans.migration;

import java.io.Serializable;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanCopyItem implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
