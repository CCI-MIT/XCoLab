package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.model.PlanPositionItem;
import com.ext.portlet.plans.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the PlanPositions service. Represents a row in the &quot;plansProposalsFacade_PlanPositions&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanPositions} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanPositionsImpl extends PlanPositionsBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan positions model instance should use the {@link com.ext.portlet.plans.model.PlanPositions} interface instead.
     */
    public PlanPositionsImpl() {
    }

    public List<Long> getPositionsIds() throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (PlanPositionItem item: PlanPositionItemLocalServiceUtil.getAllIdsForPlanPositions(this)) {
            ret.add(item.getPositionId());
        }
        return ret;
    }
    
    
    public void store() throws SystemException {
        if (this.isNew()) {
            PlanPositionsLocalServiceUtil.addPlanPositions(this);
        }
        else {
            PlanPositionsLocalServiceUtil.updatePlanPositions(this);
        }
    }
    
    public void setPositionsIds(List<Long> positionsIds) throws PortalException, SystemException {
        // delete egzisting associations
        List<Long> actual = getPositionsIds();
        for (Long id: actual) {
            PlanPositionItem planPositionItem = PlanPositionItemLocalServiceUtil.getPlanPositionItem(new PlanPositionItemPK(this.getId(), id));
            PlanPositionItemLocalServiceUtil.deletePlanPositionItem(planPositionItem);
        }
        
        // add new associations) 
        for (Long id: positionsIds) {
            PlanPositionItem planPositionItem = PlanPositionItemLocalServiceUtil.createPlanPositionItem(new PlanPositionItemPK(this.getId(), id));
            PlanPositionItemLocalServiceUtil.addPlanPositionItem(planPositionItem);       
        }
    }
    
    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }
}
