package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateSeries;
import com.ext.portlet.service.ImpactTemplateSeriesLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ImpactTemplateSeriesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImpactTemplateSeriesActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImpactTemplateSeriesLocalServiceUtil.getService());
        setClass(ImpactTemplateSeries.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("seriesId");
    }
}
