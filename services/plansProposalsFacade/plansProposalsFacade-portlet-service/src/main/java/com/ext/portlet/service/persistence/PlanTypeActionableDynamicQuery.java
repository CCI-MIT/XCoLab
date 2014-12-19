package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanType;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanTypeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanTypeLocalServiceUtil.getService());
        setClass(PlanType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planTypeId");
    }
}
