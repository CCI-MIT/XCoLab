package com.ext.portlet.plans.service.impl;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanSectionDefinitionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan section definition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanSectionDefinitionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanSectionDefinitionLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil
 */
public class PlanSectionDefinitionLocalServiceImpl
    extends PlanSectionDefinitionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil} to access the plan section definition local service.
     */

    public void store(PlanSectionDefinition psd) throws SystemException {
        if (psd.isNew()) {
            if (psd.getId() == 0L || psd.getId() <= 0) {
                psd.setId(CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));
            }
            
            PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(psd);
        }
        else {
            PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(psd);
        }
    }
    
    public FocusArea getFocusArea(PlanSectionDefinition psd) throws PortalException, SystemException {
        if (psd.getFocusAreaId() > 0L) {
            return FocusAreaLocalServiceUtil.getFocusArea(psd.getFocusAreaId());
        }
        return null;
    }
}
