package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageRecipient;
import com.ext.portlet.service.MessagingMessageRecipientLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingMessageRecipientActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingMessageRecipientActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessagingMessageRecipientLocalServiceUtil.getService());
        setClass(MessagingMessageRecipient.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("recipientId");
    }
}
