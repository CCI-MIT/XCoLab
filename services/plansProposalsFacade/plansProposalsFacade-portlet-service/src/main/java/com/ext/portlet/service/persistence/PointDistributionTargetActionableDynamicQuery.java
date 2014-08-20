package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.service.PointDistributionTargetLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PointDistributionTargetActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PointDistributionTargetActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(PointDistributionTargetLocalServiceUtil.getService());
        setClass(PointDistributionTarget.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
