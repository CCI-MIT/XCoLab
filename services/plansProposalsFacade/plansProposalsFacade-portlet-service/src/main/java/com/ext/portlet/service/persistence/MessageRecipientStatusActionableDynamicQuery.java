package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessageRecipientStatusActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessageRecipientStatusActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessageRecipientStatusLocalServiceUtil.getService());
        setClass(MessageRecipientStatus.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("messageRecipientId");
    }
}
