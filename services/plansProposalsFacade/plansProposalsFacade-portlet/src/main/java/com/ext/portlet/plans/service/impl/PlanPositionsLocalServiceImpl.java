package com.ext.portlet.plans.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositionItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanPositionsLocalServiceBaseImpl;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the plan positions local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanPositionsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanPositionsLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil
 */
public class PlanPositionsLocalServiceImpl
    extends PlanPositionsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil} to access the plan positions local service.
     */

    public PlanPositions getCurrentForPlan(PlanItem plan) throws SystemException, NoSuchPlanPositionsException {
        return this.planPositionsPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
    }
    
    public PlanPositions createPlanPositions(PlanItem plan) throws SystemException {
        Long planPositionsId = CounterLocalServiceUtil.increment(PlanPositions.class.getName());
        PlanPositions planPositions = PlanPositionsLocalServiceUtil.createPlanPositions(planPositionsId);
        planPositions.setPlanId(plan.getPlanId());
        planPositions.setPlanVersion(plan.getVersion());
        planPositions.setCreated(new Date());
        planPositions.setUpdateAuthorId(plan.getUpdateAuthorId());
        planPositions.setVersion(0L);
        
        store(planPositions);
        return planPositions;
    }
    
    public List<PlanPositions> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planPositionsPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanPositions createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanPositions createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanPositions currentPositions = this.planPositionsPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        PlanPositions newPositions = (PlanPositions) currentPositions.clone();
        
        newPositions.setVersion(currentPositions.getVersion()+1);
        newPositions.setId(CounterLocalServiceUtil.increment(PlanPositions.class.getName()));
        newPositions.setPlanVersion(plan.getVersion());
        newPositions.setUpdateAuthorId(plan.getUpdateAuthorId());
        newPositions.setNew(true);
        newPositions.setCreated(new Date());

        if (store) {
            store(newPositions);
        }
        
        return newPositions;
    }
    
    public List<Long> getPositionsIds(PlanPositions pp) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (PlanPositionItem item: PlanPositionItemLocalServiceUtil.getAllIdsForPlanPositions(pp)) {
            ret.add(item.getPositionId());
        }
        return ret;
    }
    
    
    public void store(PlanPositions pp) throws SystemException {
        if (pp.isNew()) {
            PlanPositionsLocalServiceUtil.addPlanPositions(pp);
        }
        else {
            PlanPositionsLocalServiceUtil.updatePlanPositions(pp);
        }
    }
    
    public void setPositionsIds(PlanPositions pp, List<Long> positionsIds) throws PortalException, SystemException {
        // delete egzisting associations
        List<Long> actual = getPositionsIds(pp);
        for (Long id: actual) {
            PlanPositionItem planPositionItem = PlanPositionItemLocalServiceUtil.getPlanPositionItem(new PlanPositionItemPK(pp.getId(), id));
            PlanPositionItemLocalServiceUtil.deletePlanPositionItem(planPositionItem);
        }
        
        // add new associations) 
        for (Long id: positionsIds) {
            PlanPositionItem planPositionItem = PlanPositionItemLocalServiceUtil.createPlanPositionItem(new PlanPositionItemPK(pp.getId(), id));
            PlanPositionItemLocalServiceUtil.addPlanPositionItem(planPositionItem);       
        }
    }
    
    public User getUpdateAuthor(PlanPositions pp) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(pp.getUpdateAuthorId());
    }
}
