package com.ext.portlet.plans.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanDescriptionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the plan description local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanDescriptionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanDescriptionLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil
 */
public class PlanDescriptionLocalServiceImpl
    extends PlanDescriptionLocalServiceBaseImpl {


    public PlanDescription createPlanDescription(PlanItem plan, String name) throws SystemException {
        return createPlanDescription(plan, name, true);
    }
        
    public PlanDescription createPlanDescription(PlanItem plan, String name, boolean store) throws SystemException {
        long planDescriptionId = CounterLocalServiceUtil.increment(PlanDescription.class.getName());
        PlanDescription description = PlanDescriptionLocalServiceUtil.createPlanDescription(planDescriptionId);
        description.setPlanId(plan.getPlanId());
        description.setPlanVersion(plan.getVersion());
        description.setCreated(new Date());
        description.setUpdateAuthorId(plan.getUpdateAuthorId());
        description.setName(name);
        description.setVersion(0L);
        
        if (store) {
            description = PlanDescriptionLocalServiceUtil.addPlanDescription(description);
        }
        return description;
    }
    
    public PlanDescription getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planDescriptionPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
    }
    
    public PlanDescription getForPlan(PlanItem plan) throws SystemException {
       return this.planDescriptionPersistence.fetchByPlanIdPlanVersion(plan.getPlanId(), plan.getVersion());
    }
    
    public List<PlanDescription> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planDescriptionPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanDescription createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanDescription createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanDescription currentDescription = this.planDescriptionPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        return createNewVersionForPlanFrom(plan, currentDescription, store);
    }
    
    public PlanDescription createNewVersionForPlanFrom(PlanItem plan, PlanDescription from, boolean store) throws SystemException {
        
        PlanDescription newDescription = (PlanDescription) from.clone();
        PlanDescription currentDescription = this.planDescriptionPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        
        newDescription.setVersion(currentDescription.getVersion()+1);
        newDescription.setId(CounterLocalServiceUtil.increment(PlanDescription.class.getName()));
        newDescription.setPlanVersion(plan.getVersion());
        newDescription.setUpdateAuthorId(plan.getUpdateAuthorId());
        newDescription.setCreated(new Date());

        if (store) {
            newDescription = PlanDescriptionLocalServiceUtil.addPlanDescription(newDescription);
        }
        
        return newDescription;
    }
    

    public void store(PlanDescription pd) throws SystemException {
        if (pd.isNew()) {
            PlanDescriptionLocalServiceUtil.addPlanDescription(pd);
        }
        else {
            PlanDescriptionLocalServiceUtil.updatePlanDescription(pd);
        }
    }
    
    public User getUpdateAuthor(PlanDescription pd) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(pd.getUpdateAuthorId());
    }
}
