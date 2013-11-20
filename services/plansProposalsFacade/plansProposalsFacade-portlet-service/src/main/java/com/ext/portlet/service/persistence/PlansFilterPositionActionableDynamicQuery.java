package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlansFilterPosition;
import com.ext.portlet.service.PlansFilterPositionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlansFilterPositionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlansFilterPositionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(PlansFilterPositionLocalServiceUtil.getService());
        setClass(PlansFilterPosition.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
