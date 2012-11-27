package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the PlanDescription service. Represents a row in the &quot;plansProposalsFacade_PlanDescription&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanDescription} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanDescriptionImpl extends PlanDescriptionBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan description model instance should use the {@link com.ext.portlet.plans.model.PlanDescription} interface instead.
     */
    public PlanDescriptionImpl() {
    }
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanDescriptionLocalServiceUtil.addPlanDescription(this);
        }
        else {
            PlanDescriptionLocalServiceUtil.updatePlanDescription(this);
        }
    }
    
    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }
}
