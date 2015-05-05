package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Users_Roles;
import com.ext.portlet.service.Users_RolesLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class Users_RolesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public Users_RolesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(Users_RolesLocalServiceUtil.getService());
        setClass(Users_Roles.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
