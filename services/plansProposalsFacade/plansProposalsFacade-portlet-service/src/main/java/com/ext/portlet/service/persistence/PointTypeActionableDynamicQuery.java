package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointType;
import com.ext.portlet.service.PointTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PointTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PointTypeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PointTypeLocalServiceUtil.getService());
        setClass(PointType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
