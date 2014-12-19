package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingRedirectLink;
import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingRedirectLinkActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingRedirectLinkActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessagingRedirectLinkLocalServiceUtil.getService());
        setClass(MessagingRedirectLink.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("redirectId");
    }
}
