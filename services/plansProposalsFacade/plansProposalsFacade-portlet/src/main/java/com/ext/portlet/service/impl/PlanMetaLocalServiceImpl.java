package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.PlanStatus;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanMeta;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.base.PlanMetaLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan meta local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanMetaLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanMetaLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanMetaLocalServiceUtil
 */
public class PlanMetaLocalServiceImpl extends PlanMetaLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanMetaLocalServiceUtil} to access the plan meta local service.
     */

    public PlanMeta createPlanMeta(PlanItem plan, Long planTypeId) throws SystemException, PortalException {
        Long planMetaId = CounterLocalServiceUtil.increment(PlanMeta.class.getName());
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createPlanMeta(planMetaId);
        planMeta.setPlanId(plan.getPlanId());
        planMeta.setPlanTypeId(planTypeId);
         //set a default model


            PlanType type = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
            /*List<Simulation> models = type.getAvailableModels();
            if (models.size() > 0) planMeta.setModelId(models.get(0).getId());*/
        planMeta.setPlanVersion(plan.getVersion());
        planMeta.setCreated(new Date());
        planMeta.setVotes(0);
        planMeta.setOpen(false);
        planMeta.setUpdateAuthorId(plan.getUpdateAuthorId());
        planMeta.setAuthorId(plan.getUpdateAuthorId());
        planMeta.setVersion(0L);
        planMeta.setCategoryGroupId(0L);
        planMeta.setStatus(PlanStatus.UNDER_DEVELOPMENT.name());
        
        return PlanMetaLocalServiceUtil.addPlanMeta(planMeta);
    }
    
    public PlanMeta getCurrentForPlan(PlanItem plan) throws SystemException {
        return this.planMetaPersistence.fetchByCurrentByPlanId(plan.getPlanId(), false);
    }
    
    public List<PlanMeta> getAllForPlan(PlanItem plan) throws SystemException {
        return this.planMetaPersistence.findByAllByPlanId(plan.getPlanId());
    }
    

    public PlanMeta createNewVersionForPlan(PlanItem plan) throws SystemException {
        return createNewVersionForPlan(plan, true);
    }
    
    public PlanMeta createNewVersionForPlan(PlanItem plan, boolean store) throws SystemException {
        PlanMeta currentMeta = this.planMetaPersistence.fetchByCurrentByPlanId(plan.getPlanId());
        PlanMeta newMeta = (PlanMeta) currentMeta.clone();
        
        newMeta.setVersion(currentMeta.getVersion()+1);
        newMeta.setId(CounterLocalServiceUtil.increment(PlanMeta.class.getName()));
        newMeta.setPlanVersion(plan.getVersion());
        newMeta.setUpdateAuthorId(plan.getUpdateAuthorId());
        newMeta.setCreated(new Date());

        if (store) {
            newMeta = PlanMetaLocalServiceUtil.addPlanMeta(newMeta);
        }
        
        return newMeta;
    }
    

    public void store(PlanMeta pm) throws SystemException {
        if (pm.isNew()) {
            PlanMetaLocalServiceUtil.addPlanMeta(pm);
        }
        else {
            PlanMetaLocalServiceUtil.updatePlanMeta(pm);
        }
    }
    
    public void vote(PlanMeta pm) throws SystemException {
        int votes = pm.getVotes();
        pm.setVotes(votes + 1);
        store(pm);
    }
    
    public void unvote(PlanMeta pm) throws SystemException {
        int votes = pm.getVotes();
        pm.setVotes(Math.max(votes - 1, 0));
        store(pm);
    }
}
