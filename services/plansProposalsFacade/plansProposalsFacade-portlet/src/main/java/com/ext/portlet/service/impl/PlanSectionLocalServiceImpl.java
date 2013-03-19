package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.NoSuchPlanSectionException;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanSection;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanSectionPlanMap;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPK;
import com.ext.portlet.service.base.PlanSectionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan section local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanSectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanSectionLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanSectionLocalServiceUtil
 */
public class PlanSectionLocalServiceImpl extends PlanSectionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanSectionLocalServiceUtil} to access the plan section local service.
     */
    
    public PlanSection getCurrentForPlanSectionDef(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return getCurrentForPlanSectionDef(plan, def, true);
    }
    

    public PlanSection getCurrentForPlanSectionDef(PlanItem plan, PlanSectionDefinition def, boolean createOnEmpty) 
    throws SystemException {
        return getForPlanSectionDef(plan, def, true, createOnEmpty);
    }
    
    public PlanSection getForPlanSectionDef(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return getForPlanSectionDef(plan, def, false, true);
    }
    
    
    public PlanSection getForPlanSectionDef(PlanItem plan, PlanSectionDefinition def, boolean current, boolean createOnEmpty) throws SystemException {
        PlanSection ps = null;
        try {
            if (current) {
                ps = this.planSectionPersistence.findByCurrentPlanIdSectionDefinitionId(plan.getPlanId(), def.getId());
            }
            else {
                ps = this.planSectionPersistence.findByPlanIdPlanVersion(plan.getPlanId(), def.getId(), plan.getVersion());
            }
        }
        catch (NoSuchPlanSectionException e) {
            // ignore
        }
        if (ps == null && createOnEmpty) {
            ps = createPlanSection(0L);
            ps.setPlanId(plan.getPlanId());
            ps.setPlanSectionDefinitionId(def.getId());
            
            ps.setContent(def.getDefaultText());
            
            ps.setVersion(0L);
            ps.setPlanVersion(plan.getVersion());
            ps.setCreated(new Date());
            ps.setUpdateAuthorId(0L);
        }
        return ps;
    }
    
    public PlanSection createForPlanFrom(PlanItem plan, PlanSection from, boolean store) throws SystemException, PortalException {
        PlanSection current = getCurrentForPlanSectionDef(plan, getDefinition(from), false);
        PlanSection ps = createPlanSection(0L);
        
        ps.setPlanId(plan.getPlanId());
        ps.setPlanSectionDefinitionId(from.getPlanSectionDefinitionId());
        
        ps.setContent(from.getContent());
        
        ps.setVersion(current.getVersion()+1);
        ps.setPlanVersion(plan.getVersion());
        ps.setCreated(new Date());
        ps.setUpdateAuthorId(0L);
        
        if (store) {
            store(ps);
        }
        planSectionPersistence.clearCache(ps);
        return ps;
    }
    

    public PlanSection createNewVersionForPlanSectionDefinition(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return createNewVersionForPlanSectionDefinition(plan, def, true);
    }
    
    public PlanSection createNewVersionForPlanSectionDefinition(PlanItem plan, PlanSectionDefinition def, boolean store) throws SystemException {
        PlanSection current = getCurrentForPlanSectionDef(plan, def, true);
        
        PlanSection newSection = (PlanSection) current.clone();
        
        newSection.setVersion(current.getVersion()+1);
        newSection.setId(CounterLocalServiceUtil.increment(PlanSection.class.getName()));
        newSection.setPlanVersion(plan.getVersion());
        newSection.setUpdateAuthorId(plan.getUpdateAuthorId());
        newSection.setCreated(new Date());
        newSection.setNew(true);

        if (store) {
            store(newSection);
        }
        planSectionPersistence.clearCache(newSection);
        
        return newSection;
    }
    
    public List<PlanSection> getAllForPlanDefinition(PlanItem plan, PlanSectionDefinition def) throws SystemException {
        return this.planSectionPersistence.findByPlanIdSectionDefinitionId(plan.getPlanId(), def.getId());
    }
    
    

    public void store(PlanSection ps) throws SystemException {
        if (ps.isNew()) {
            if (ps.getId() <= 0L) {
                ps.setId(CounterLocalServiceUtil.increment(PlanSection.class.getName()));
            }
            
            PlanSectionLocalServiceUtil.addPlanSection(ps);
        }
        else {
            PlanSectionLocalServiceUtil.updatePlanSection(ps);
        }
    }
    
    public PlanSectionDefinition getDefinition(PlanSection ps) throws PortalException, SystemException {
        return PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(ps.getPlanSectionDefinitionId());
    }
    
    public void addPlanReference(PlanSection ps, Long planId) throws SystemException {
        PlanSectionPlanMap pspm = 
            PlanSectionPlanMapLocalServiceUtil.createPlanSectionPlanMap(new PlanSectionPlanMapPK(ps.getId(), planId));
        PlanSectionPlanMapLocalServiceUtil.store(pspm);
    }
    
    public List<PlanItem> getReferencedPlans(PlanSection ps) throws SystemException, NoSuchPlanItemException  {
        List<PlanItem> ret = new ArrayList<PlanItem>();
        
        for (Long planId: PlanSectionPlanMapLocalServiceUtil.findPlanIdsForSection(ps.getId())) {
            ret.add(PlanItemLocalServiceUtil.getPlan(planId));
        }
        return ret;
    }
}
