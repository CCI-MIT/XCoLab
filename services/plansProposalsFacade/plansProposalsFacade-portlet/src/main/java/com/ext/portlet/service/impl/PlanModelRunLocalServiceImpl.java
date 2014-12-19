package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.NoSuchPlanModelRunException;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanModelRun;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.service.base.PlanModelRunLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the plan model run local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanModelRunLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanModelRunLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanModelRunLocalServiceUtil
 */
public class PlanModelRunLocalServiceImpl
    extends PlanModelRunLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanModelRunLocalServiceUtil} to access the plan model run local service.
     */

    public PlanModelRun createPlanModelRun(PlanItem plan) throws SystemException {
        Long id = CounterLocalServiceUtil.increment(PlanModelRun.class.getName());
        
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createPlanModelRun(id);
        planModelRun.setPlanId(plan.getPlanId());
        planModelRun.setPlanVersion(plan.getVersion());
        planModelRun.setVersion(0L);
        planModelRun.setUpdateAuthorId(plan.getUpdateAuthorId());
        planModelRun.setCreated(new Date());
        
        return PlanModelRunLocalServiceUtil.addPlanModelRun(planModelRun);
    }
    
    public PlanModelRun getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planModelRunPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
    }
    
    public List<PlanModelRun> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planModelRunPersistence.findByAllByPlanId(plan.getPlanId());
    }
    
    public PlanModelRun getForPlan(PlanItem plan) throws SystemException {
        List<PlanModelRun> runs = this.planModelRunPersistence.findByPlanIdPlanVersion(plan.getPlanId(), plan.getVersion());
        return runs.isEmpty() ? null : runs.get(0); 
     }
    

    public PlanModelRun createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanModelRun createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanModelRun currentModelRun = this.planModelRunPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        
        return createNewVersionForPlanFrom(plan, currentModelRun, store);
        
    }
    
    public PlanModelRun createNewVersionForPlanFrom(PlanItem plan, PlanModelRun from, boolean store) throws SystemException {
        PlanModelRun newModelRun = (PlanModelRun) from.clone();
        PlanModelRun currentModelRun = this.planModelRunPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        
        
        newModelRun.setVersion(currentModelRun.getVersion()+1);
        newModelRun.setId(CounterLocalServiceUtil.increment(PlanModelRun.class.getName()));
        newModelRun.setPlanVersion(plan.getVersion());
        newModelRun.setUpdateAuthorId(plan.getUpdateAuthorId());
        newModelRun.setCreated(new Date());

        if (store) {
            newModelRun = PlanModelRunLocalServiceUtil.addPlanModelRun(newModelRun);
        }
        planModelRunPersistence.clearCache(newModelRun);
        
        return newModelRun;
    }
    
    
    public void store(PlanModelRun pmr) throws SystemException {
        if (pmr.isNew()) {
            PlanModelRunLocalServiceUtil.addPlanModelRun(pmr);
        }
        else {
            PlanModelRunLocalServiceUtil.updatePlanModelRun(pmr);
        }
    }
    
    public User getUpdateAuthor(PlanModelRun pmr) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(pmr.getUpdateAuthorId());
    }
}
