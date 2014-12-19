package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ActivitySubscriptionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ActivitySubscriptionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ActivitySubscriptionLocalServiceUtil.getService());
        setClass(ActivitySubscription.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("pk");
    }
}
