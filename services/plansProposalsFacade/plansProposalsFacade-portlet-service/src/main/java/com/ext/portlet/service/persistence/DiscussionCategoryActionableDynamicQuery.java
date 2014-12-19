package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class DiscussionCategoryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DiscussionCategoryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DiscussionCategoryLocalServiceUtil.getService());
        setClass(DiscussionCategory.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("pk");
    }
}
