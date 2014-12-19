package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Points;
import com.ext.portlet.service.PointsLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PointsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PointsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PointsLocalServiceUtil.getService());
        setClass(Points.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
