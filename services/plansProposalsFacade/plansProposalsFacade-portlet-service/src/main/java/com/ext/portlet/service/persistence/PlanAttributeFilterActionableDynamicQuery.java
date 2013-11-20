package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanAttributeFilter;
import com.ext.portlet.service.PlanAttributeFilterLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanAttributeFilterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanAttributeFilterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(PlanAttributeFilterLocalServiceUtil.getService());
        setClass(PlanAttributeFilter.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planAttributeFilterId");
    }
}
