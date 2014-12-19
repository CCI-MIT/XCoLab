package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageConversion;
import com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MessagingMessageConversionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MessagingMessageConversionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MessagingMessageConversionLocalServiceUtil.getService());
        setClass(MessagingMessageConversion.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("conversionId");
    }
}
