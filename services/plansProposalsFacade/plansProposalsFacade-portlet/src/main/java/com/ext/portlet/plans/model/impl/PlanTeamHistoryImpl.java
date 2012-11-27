package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the PlanTeamHistory service. Represents a row in the &quot;plansProposalsFacade_PlanTeamHistory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanTeamHistory} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanTeamHistoryImpl extends PlanTeamHistoryBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan team history model instance should use the {@link com.ext.portlet.plans.model.PlanTeamHistory} interface instead.
     */
    public PlanTeamHistoryImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            PlanTeamHistoryLocalServiceUtil.addPlanTeamHistory(this);
        }
        else {
            PlanTeamHistoryLocalServiceUtil.updatePlanTeamHistory(this);
        }
    }
    
    public User getUser() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUserId());
    }
    
    public PlanItem getPlan() throws NoSuchPlanItemException, SystemException {
        return PlanItemLocalServiceUtil.getPlan(getPlanId());
    }
}
