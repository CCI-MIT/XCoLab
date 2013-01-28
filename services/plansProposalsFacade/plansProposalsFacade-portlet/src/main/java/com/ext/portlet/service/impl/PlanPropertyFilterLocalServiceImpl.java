package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchPlanPropertyFilterException;
import com.ext.portlet.model.PlanPropertyFilter;
import com.ext.portlet.service.base.PlanPropertyFilterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan property filter local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanPropertyFilterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanPropertyFilterLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanPropertyFilterLocalServiceUtil
 */
public class PlanPropertyFilterLocalServiceImpl
    extends PlanPropertyFilterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanPropertyFilterLocalServiceUtil} to access the plan property filter local service.
     */
    public PlanPropertyFilter getByPlanPlanUserSettingsIdPropertyName(Long planUserSettingsId, String propertyName)
    throws SystemException, NoSuchPlanPropertyFilterException {
        return planPropertyFilterPersistence.findByPlanUserSettingsIdPropertyName(planUserSettingsId, propertyName);
    }
}
