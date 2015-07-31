package com.ext.portlet.service.persistence;

import com.ext.portlet.model.User_;
import com.ext.portlet.service.User_LocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class User_ActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public User_ActionableDynamicQuery() throws SystemException {
        setBaseLocalService(User_LocalServiceUtil.getService());
        setClass(User_.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("userId");
    }
}
