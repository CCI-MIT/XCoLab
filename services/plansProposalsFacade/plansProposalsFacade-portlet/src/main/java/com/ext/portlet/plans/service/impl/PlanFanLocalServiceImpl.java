package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanFanException;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.service.base.PlanFanLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan fan local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanFanLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanFanLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanFanLocalServiceUtil
 */
public class PlanFanLocalServiceImpl extends PlanFanLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanFanLocalServiceUtil} to access the plan fan local service.
     */

    public List<PlanFan> getPlanFansForPlan(Long planId) throws SystemException {
        return planFanPersistence.findByPlanId(planId);
    }
    
    public int countPlanFansForPlan(Long planId) throws SystemException {
        return planFanPersistence.countByPlanId(planId);
    }
    
    public List<PlanFan> getPlanFansForUser(Long userId) throws SystemException {
        return planFanPersistence.findByUserId(userId);
    }
    
    public PlanFan addFan(Long planId, Long userId) throws SystemException {
        long planFanId = CounterLocalServiceUtil.increment(PlanFan.class.getName());
        
        PlanFan planFan = createPlanFan(planFanId);
        
        planFan.setPlanId(planId);
        planFan.setUserId(userId);
        planFan.setCreated(new Date());
        
        planFan.store();
        
        return planFan;
    }
    
    public void removePlanFan(Long planId, Long userId) throws SystemException {
        try {
            PlanFan planFan = planFanPersistence.findByPlanIdUserId(planId, userId);
            planFan.setDeleted(new Date());
            planFan.store();
            
            // flush the cache
            planFanPersistence.fetchByPlanIdUserId(planId, userId, false);
        } catch (NoSuchPlanFanException e) {
            // ignore
        }
    }
    
    public PlanFan getPlanFanByPlanIdUserId(Long planId, Long userId) throws SystemException, NoSuchPlanFanException {
        PlanFan planFan = planFanPersistence.findByPlanIdUserId(planId, userId);
        return planFan;
    }   
    
    public int countByUserId(Long userId) throws SystemException {
        return planFanPersistence.countByUserId(userId);
    }
    
    public List<PlanFan> getByUserId(Long userId, int start, int end) throws SystemException {
        return planFanPersistence.findByUserId(userId, start, end);
    }
}
