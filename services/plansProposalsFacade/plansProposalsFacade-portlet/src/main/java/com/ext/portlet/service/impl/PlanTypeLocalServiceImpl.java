package com.ext.portlet.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.NoSuchPlanTypeAttributeException;
import com.ext.portlet.NoSuchPlanTypeException;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.PlanTypeAttribute;
import com.ext.portlet.model.PlanTypeColumn;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.base.PlanTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.simulation.client.Simulation;

/**
 * The implementation of the plan type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanTypeLocalServiceUtil
 */
public class PlanTypeLocalServiceImpl extends PlanTypeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanTypeLocalServiceUtil} to access the plan type local service.
     */
    public PlanType getDefaultPlanType() throws NoSuchPlanTypeException, SystemException {
        return planTypePersistence.findBydefault(true);
    }
    
    public List<PlanTypeColumn> getColumnsByPlanTypeId(long planTypeId) throws SystemException {
        return planTypePersistence.getPlanTypeColumns(planTypeId);
    }
    
    public List<PlanTypeAttribute> getAttributesByPlanTypeId(long planTypeId) throws SystemException {
        return planTypePersistence.getPlanTypeAttributes(planTypeId);
    }
    
    public boolean isRegionalType(long planTypeId) throws SystemException {
        try {
            return planTypeAttributePersistence.findByPlanTypeIdAttributeName(planTypeId, "REGION") != null;
        }
        catch (NoSuchPlanTypeAttributeException e) {
        }
        return false;
    }
    

    public List<Simulation> getAvailableModels(PlanType planType) throws SystemException {

        if (planType.getModelTypeName()!=null && planType.getModelTypeName().trim().length() > 0) {
               //return new ArrayList<Simulation>(CollaboratoriumModelingService.repository().getSimulationsOfType(planType.getModelTypeName()));
            return new ArrayList<Simulation>();
        } else if (planType.getModelId()>0) {
            try {
                return Collections.singletonList(CollaboratoriumModelingService.repository().getSimulation(planType.getModelId()));
            }
            catch (IOException e) {
                throw new SystemException(e);
            }
        } else return Collections.emptyList();
       }

    public Simulation getDefaultModel(PlanType planType) throws SystemException {
        if (planType.getDefaultModelId() <= 0L) {
            return null;
        } else {
            try {
                return CollaboratoriumModelingService.repository().getSimulation(planType.getDefaultModelId());
            }
            catch (IOException e) {
                throw new SystemException(e);
            }
        }
    }

    
    public List<PlanTypeColumn> getColumns(PlanType planType) throws SystemException {
        List<PlanTypeColumn> cols= PlanTypeLocalServiceUtil.getColumnsByPlanTypeId(planType.getPlanTypeId());
        return cols == null?Collections.<PlanTypeColumn>emptyList():cols;
    }
    
    public List<PlanTypeAttribute> getAttributes(PlanType planType) throws SystemException {
        List<PlanTypeAttribute> atts = PlanTypeLocalServiceUtil.getAttributesByPlanTypeId(planType.getPlanTypeId());
        return atts==null? Collections.<PlanTypeAttribute>emptyList() :atts;
    }
    
    public boolean isRegional(PlanType planType) throws SystemException {
        return PlanTypeLocalServiceUtil.isRegionalType(planType.getPlanTypeId());
    }
}
