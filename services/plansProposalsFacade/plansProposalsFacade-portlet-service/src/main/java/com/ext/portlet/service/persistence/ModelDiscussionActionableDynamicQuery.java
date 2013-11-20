package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelDiscussion;
import com.ext.portlet.service.ModelDiscussionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelDiscussionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelDiscussionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModelDiscussionLocalServiceUtil.getService());
        setClass(ModelDiscussion.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelDiscussionId");
    }
}
