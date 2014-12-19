package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTypeColumn;
import com.ext.portlet.service.PlanTypeColumnLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanTypeColumnActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanTypeColumnActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanTypeColumnLocalServiceUtil.getService());
        setClass(PlanTypeColumn.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planTypeColumnId");
    }
}
