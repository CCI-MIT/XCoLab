package com.ext.portlet.service.persistence;

import com.ext.portlet.model.TrackedVisitor2User;
import com.ext.portlet.service.TrackedVisitor2UserLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class TrackedVisitor2UserActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public TrackedVisitor2UserActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(TrackedVisitor2UserLocalServiceUtil.getService());
        setClass(TrackedVisitor2User.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
