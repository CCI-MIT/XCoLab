package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessage;
import com.ext.portlet.service.MessagingMessageLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingMessageActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingMessageActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MessagingMessageLocalServiceUtil.getService());
        setClass(MessagingMessage.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("messageId");
    }
}
