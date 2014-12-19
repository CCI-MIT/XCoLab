package com.ext.portlet.service.persistence;

import com.ext.portlet.model.AnalyticsUserEvent;
import com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class AnalyticsUserEventActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AnalyticsUserEventActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AnalyticsUserEventLocalServiceUtil.getService());
        setClass(AnalyticsUserEvent.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
