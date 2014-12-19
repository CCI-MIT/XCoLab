package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlansFilter;
import com.ext.portlet.service.PlansFilterLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlansFilterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlansFilterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlansFilterLocalServiceUtil.getService());
        setClass(PlansFilter.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
