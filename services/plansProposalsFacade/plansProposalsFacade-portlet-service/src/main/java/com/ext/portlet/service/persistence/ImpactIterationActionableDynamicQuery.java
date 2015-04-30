package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.service.ImpactIterationLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ImpactIterationActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImpactIterationActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ImpactIterationLocalServiceUtil.getService());
        setClass(ImpactIteration.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.iterationId");
    }
}
