package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanItemGroup;
import com.ext.portlet.service.PlanItemGroupLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanItemGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanItemGroupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanItemGroupLocalServiceUtil.getService());
        setClass(PlanItemGroup.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planId");
    }
}
