package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanSectionDefinitionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanSectionDefinitionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(PlanSectionDefinitionLocalServiceUtil.getService());
        setClass(PlanSectionDefinition.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
