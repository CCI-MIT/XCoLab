package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class DiscussionCategoryGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DiscussionCategoryGroupActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(DiscussionCategoryGroupLocalServiceUtil.getService());
        setClass(DiscussionCategoryGroup.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
