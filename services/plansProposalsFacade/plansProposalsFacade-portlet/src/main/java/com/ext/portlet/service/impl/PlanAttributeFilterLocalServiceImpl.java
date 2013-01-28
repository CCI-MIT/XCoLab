package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchPlanAttributeFilterException;
import com.ext.portlet.model.PlanAttributeFilter;
import com.ext.portlet.service.base.PlanAttributeFilterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan attribute filter local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanAttributeFilterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanAttributeFilterLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanAttributeFilterLocalServiceUtil
 */
public class PlanAttributeFilterLocalServiceImpl
    extends PlanAttributeFilterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanAttributeFilterLocalServiceUtil} to access the plan attribute filter local service.
     */
    public PlanAttributeFilter getByPlansUserSettingsIdAttributeName(Long planUserSettingsId, String attributeName) throws SystemException, NoSuchPlanAttributeFilterException {
        return this.planAttributeFilterPersistence.findByPlanUserSettingsIdAttributeName(planUserSettingsId, attributeName);
    }
}
