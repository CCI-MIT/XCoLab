package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPositions;
import com.ext.portlet.service.PlanPositionsLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanPositionsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanPositionsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanPositionsLocalServiceUtil.getService());
        setClass(PlanPositions.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
