package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PointsDistributionConfigurationActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PointsDistributionConfigurationActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(PointsDistributionConfigurationLocalServiceUtil.getService());
        setClass(PointsDistributionConfiguration.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
