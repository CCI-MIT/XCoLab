package com.ext.portlet.service.persistence;

import com.ext.portlet.model.SocialActivity;
import com.ext.portlet.service.SocialActivityLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class SocialActivityActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public SocialActivityActionableDynamicQuery() throws SystemException {
        setBaseLocalService(SocialActivityLocalServiceUtil.getService());
        setClass(SocialActivity.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("activityId");
    }
}
