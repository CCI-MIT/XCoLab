package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanSectionPlanMap;
import com.ext.portlet.service.PlanSectionPlanMapLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanSectionPlanMapActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanSectionPlanMapActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanSectionPlanMapLocalServiceUtil.getService());
        setClass(PlanSectionPlanMap.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.sectionId");
    }
}
