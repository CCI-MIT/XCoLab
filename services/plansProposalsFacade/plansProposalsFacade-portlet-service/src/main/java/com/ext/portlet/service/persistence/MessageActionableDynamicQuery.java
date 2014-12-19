package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Message;
import com.ext.portlet.service.MessageLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessageActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessageActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MessageLocalServiceUtil.getService());
        setClass(Message.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("messageId");
    }
}
