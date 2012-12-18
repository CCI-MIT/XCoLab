package com.ext.portlet.plans.service.impl;

import java.util.List;

import com.ext.portlet.plans.NoSuchPlanAttributeException;
import com.ext.portlet.plans.TypedValueConverter;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.service.base.PlanAttributeLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan attribute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanAttributeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil
 */
public class PlanAttributeLocalServiceImpl
    extends PlanAttributeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil} to access the plan attribute local service.
     */

    
    public void addPlanAttribute(long planId, String attributeName, String attributeValue) throws SystemException {
        long id = CounterLocalServiceUtil.increment(PlanAttribute.class.getName());
        PlanAttribute att = planAttributeLocalService.createPlanAttribute(id);
        att.setPlanId(planId);
        att.setAttributeName(attributeName);
        att.setAttributeValue(attributeValue);
        planAttributeLocalService.addPlanAttribute(att);
    }
    
    public PlanAttribute findPlanAttribute(long planId, String attributeName) throws SystemException {
        try {
            return planAttributePersistence.findByattributeForPlan(planId, attributeName);
        } catch (NoSuchPlanAttributeException e) {
            return null;
        } 
    }
    
    public List<PlanAttribute> getPlanAttributes(Long planId) throws SystemException {
        return planAttributePersistence.findByplanAttributes(planId);
    }
    
    public List<PlanAttribute> getPlanAttributesByNameValue(String attributeName, String attributeValue) throws SystemException {
        return planAttributePersistence.findByattributeByNameValue(attributeName, attributeValue);
    }
    
    
    public Object getTypedValue(PlanAttribute pa) {
        Attribute attribute = Attribute.valueOf(pa.getAttributeName());
        return TypedValueConverter.getValue(attribute.getAttributeClass(), pa.getAttributeValue());
    }
        
}
