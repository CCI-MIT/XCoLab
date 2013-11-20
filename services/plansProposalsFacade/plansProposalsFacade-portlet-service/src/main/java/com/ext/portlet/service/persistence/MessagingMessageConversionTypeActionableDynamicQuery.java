package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageConversionType;
import com.ext.portlet.service.MessagingMessageConversionTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingMessageConversionTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingMessageConversionTypeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessagingMessageConversionTypeLocalServiceUtil.getService());
        setClass(MessagingMessageConversionType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("typeId");
    }
}
