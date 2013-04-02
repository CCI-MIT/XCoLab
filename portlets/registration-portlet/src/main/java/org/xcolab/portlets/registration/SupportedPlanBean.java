package org.xcolab.portlets.registration;

import java.util.Date;

import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.model.PlanFan;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class SupportedPlanBean {
    
    private PlanFan supportedPlanInfo;
    private PlanItem supportedPlan;
    
    public SupportedPlanBean(PlanFan supportedPlanInfo) throws NoSuchPlanItemException, SystemException {
        this.supportedPlanInfo = supportedPlanInfo;
        supportedPlan = PlanItemLocalServiceUtil.getPlan(supportedPlanInfo.getPlanId());
    }
    
    public String getPlanName() throws SystemException {
        return PlanItemLocalServiceUtil.getName(supportedPlan);
    }
    
    public Date getCreatedDate() {
        return supportedPlanInfo.getCreated();
    }
    
    public Long getPlanId() {
        return supportedPlan.getPlanId();
    }
    
    public Long getContestId() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.getContest(supportedPlan).getContestPK();
    }
    
    

}
