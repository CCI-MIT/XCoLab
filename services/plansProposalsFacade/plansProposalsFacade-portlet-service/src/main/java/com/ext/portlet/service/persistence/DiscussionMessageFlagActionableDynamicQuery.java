package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class DiscussionMessageFlagActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DiscussionMessageFlagActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(DiscussionMessageFlagLocalServiceUtil.getService());
        setClass(DiscussionMessageFlag.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("pk");
    }
}
