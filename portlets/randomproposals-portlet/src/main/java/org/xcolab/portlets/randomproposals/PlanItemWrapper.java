package org.xcolab.portlets.randomproposals;


import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanItemWrapper {
    
    private PlanItem wrapped;
    
    
    public PlanItemWrapper(PlanItem plan) {
        wrapped = plan;
    }
    
    public String getName() throws SystemException {
        return PlanItemLocalServiceUtil.getName(wrapped);
    }
    
    public Long getImage() throws SystemException {
        return PlanItemLocalServiceUtil.getImageId(wrapped);
    }

    public String getPitch() throws SystemException {
        return PlanItemLocalServiceUtil.getPitch(wrapped);
    }
    
    public Long getContestId() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.getContest(wrapped).getContestPK();
    }
    
    public Long getPlanId() {
        return wrapped.getPlanId();
    }
}
