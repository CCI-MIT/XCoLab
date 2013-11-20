package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingIgnoredRecipientsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingIgnoredRecipientsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessagingIgnoredRecipientsLocalServiceUtil.getService());
        setClass(MessagingIgnoredRecipients.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ignoredRecipientId");
    }
}
