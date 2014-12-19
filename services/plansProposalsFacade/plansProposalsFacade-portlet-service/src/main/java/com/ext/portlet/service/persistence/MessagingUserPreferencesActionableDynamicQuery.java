package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingUserPreferencesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingUserPreferencesActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessagingUserPreferencesLocalServiceUtil.getService());
        setClass(MessagingUserPreferences.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("messagingPreferencesId");
    }
}
