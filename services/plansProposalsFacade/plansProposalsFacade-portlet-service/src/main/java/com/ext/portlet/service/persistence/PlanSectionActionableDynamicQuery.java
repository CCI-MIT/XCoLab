package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanSection;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanSectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanSectionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanSectionLocalServiceUtil.getService());
        setClass(PlanSection.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
