package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ImpactDefaultSeriesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImpactDefaultSeriesActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImpactDefaultSeriesLocalServiceUtil.getService());
        setClass(ImpactDefaultSeries.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.seriesId");
    }
}
