package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.NoSuchPlanFanException;
import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.model.PlanFan;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanFanLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.base.PlanFanLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the plan fan local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanFanLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanFanLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanFanLocalServiceUtil
 */
public class PlanFanLocalServiceImpl extends PlanFanLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanFanLocalServiceUtil} to access the plan fan local service.
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
        
        store(planFan);
        
        return planFan;
    }
    
    public void removePlanFan(Long planId, Long userId) throws SystemException {
        try {
            PlanFan planFan = planFanPersistence.findByPlanIdUserId(planId, userId);
            planFan.setDeleted(new Date());
            store(planFan);
            
            // flush the cache
            planFanPersistence.fetchByPlanIdUserId(planId, userId, false);
            planFanPersistence.clearCache();
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
    
    public void store(PlanFan pf) throws SystemException {
        if (pf.isNew()) {
            PlanFanLocalServiceUtil.addPlanFan(pf);
        }
        else {
            PlanFanLocalServiceUtil.updatePlanFan(pf);
        }
    }
    
    public User getUser(PlanFan pf) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(pf.getUserId());
    }
    
    public PlanItem getPlan(PlanFan pf) throws NoSuchPlanItemException, SystemException {
        return PlanItemLocalServiceUtil.getPlan(pf.getPlanId());
    }
}
