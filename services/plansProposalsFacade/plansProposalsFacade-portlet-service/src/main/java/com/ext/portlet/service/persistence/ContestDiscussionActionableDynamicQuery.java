package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestDiscussion;
import com.ext.portlet.service.ContestDiscussionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestDiscussionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestDiscussionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestDiscussionLocalServiceUtil.getService());
        setClass(ContestDiscussion.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("DiscussionId");
    }
}
