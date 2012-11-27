package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the PlanModelRun service. Represents a row in the &quot;plansProposalsFacade_PlanModelRun&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanModelRun} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanModelRunImpl extends PlanModelRunBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan model run model instance should use the {@link com.ext.portlet.plans.model.PlanModelRun} interface instead.
     */
    public PlanModelRunImpl() {
    }
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanModelRunLocalServiceUtil.addPlanModelRun(this);
        }
        else {
            PlanModelRunLocalServiceUtil.updatePlanModelRun(this);
        }
    }
    
    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }
}
