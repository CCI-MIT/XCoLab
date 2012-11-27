package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.NoSuchPlanTypeAttributeException;
import com.ext.portlet.plans.NoSuchPlanTypeException;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.service.base.PlanTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanTypeLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanTypeLocalServiceUtil
 */
public class PlanTypeLocalServiceImpl extends PlanTypeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanTypeLocalServiceUtil} to access the plan type local service.
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
}
