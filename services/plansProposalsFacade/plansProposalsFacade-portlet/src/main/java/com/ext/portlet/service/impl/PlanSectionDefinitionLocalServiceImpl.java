package com.ext.portlet.service.impl;

import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.base.PlanSectionDefinitionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.enums.ContestTier;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the plan section definition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanSectionDefinitionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanSectionDefinitionLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil
 */
public class PlanSectionDefinitionLocalServiceImpl
    extends PlanSectionDefinitionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil} to access the plan section definition local service.
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

    /**
     * Returns a PlanSectionDefinition objects which match a given FocusArea, PlanSectionDefinition type and ContestTier
     * If there are multiple matches, returns the first match only.
     *
     * @param focusArea         The focusArea which should be matched
     * @param type              The PlanSectionDefinition type which should be matched
     * @param contestTierType   The Contest tier type which should be matched
     * @return                  The matched PlanSectionDefinition object or null, if it does not exist
     * @throws SystemException
     */
    public PlanSectionDefinition getPlanSectionDefinition(FocusArea focusArea, String type, long contestTierType) throws SystemException {
        DynamicQuery sectionDefinitionQuery = DynamicQueryFactoryUtil.forClass(PlanSectionDefinition.class);
        sectionDefinitionQuery.add(RestrictionsFactoryUtil.eq("focusAreaId", focusArea.getId()));
        sectionDefinitionQuery.add(RestrictionsFactoryUtil.eq("type", type));
        sectionDefinitionQuery.add(RestrictionsFactoryUtil.eq("tier", contestTierType));

        List<PlanSectionDefinition> sectionDefinitions = dynamicQuery(sectionDefinitionQuery);
        if (Validator.isNotNull(sectionDefinitions) && sectionDefinitions.size() > 0) {
            return sectionDefinitions.get(0);
        }

        return null;
    }
}
