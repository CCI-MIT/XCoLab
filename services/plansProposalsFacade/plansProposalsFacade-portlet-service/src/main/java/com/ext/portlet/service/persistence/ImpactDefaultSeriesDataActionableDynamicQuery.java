package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactDefaultSeriesData;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ImpactDefaultSeriesDataActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImpactDefaultSeriesDataActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImpactDefaultSeriesDataLocalServiceUtil.getService());
        setClass(ImpactDefaultSeriesData.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.seriesId");
    }
}
