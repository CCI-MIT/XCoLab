package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPosition;
import com.ext.portlet.service.PlanPositionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanPositionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanPositionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanPositionLocalServiceUtil.getService());
        setClass(PlanPosition.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.planId");
    }
}
