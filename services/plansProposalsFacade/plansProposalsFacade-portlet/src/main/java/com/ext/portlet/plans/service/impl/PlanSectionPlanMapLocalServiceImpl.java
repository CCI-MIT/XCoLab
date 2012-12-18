package com.ext.portlet.plans.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanSectionPlanMapLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan section plan map local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanSectionPlanMapLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanSectionPlanMapLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil
 */
public class PlanSectionPlanMapLocalServiceImpl
    extends PlanSectionPlanMapLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil} to access the plan section plan map local service.
     */

    public List<Long> findPlanIdsForSection(Long sectionId) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (PlanSectionPlanMap pspm: planSectionPlanMapPersistence.findBySectionId(sectionId)) {
            ret.add(pspm.getRelatedPlanId());
        }
        
        return ret;
    }
    

    public void store(PlanSectionPlanMap pspm) throws SystemException {
        if (pspm.isNew()) {
            PlanSectionPlanMapLocalServiceUtil.addPlanSectionPlanMap(pspm);
        }
        else {
            PlanSectionPlanMapLocalServiceUtil.updatePlanSectionPlanMap(pspm);
        }
    }
}
