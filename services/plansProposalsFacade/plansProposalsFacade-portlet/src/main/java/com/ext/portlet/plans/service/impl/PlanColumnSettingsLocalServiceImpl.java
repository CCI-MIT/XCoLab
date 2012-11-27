package com.ext.portlet.plans.service.impl;

import com.ext.portlet.plans.NoSuchPlanColumnSettingsException;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.service.base.PlanColumnSettingsLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan column settings local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanColumnSettingsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanColumnSettingsLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil
 */
public class PlanColumnSettingsLocalServiceImpl
    extends PlanColumnSettingsLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil} to access the plan column settings local service.
     */
    
    public PlanColumnSettings findByPlanUserSettingsIdColumnName(Long planUserSettingsId, String columnName) 
        throws NoSuchPlanColumnSettingsException, SystemException {
        return planColumnSettingsPersistence.findByPlanUserSettingsIdColumnName(planUserSettingsId, columnName);
    }
    
}
