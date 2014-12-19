package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class DiscussionMessageActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DiscussionMessageActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DiscussionMessageLocalServiceUtil.getService());
        setClass(DiscussionMessage.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("pk");
    }
}
