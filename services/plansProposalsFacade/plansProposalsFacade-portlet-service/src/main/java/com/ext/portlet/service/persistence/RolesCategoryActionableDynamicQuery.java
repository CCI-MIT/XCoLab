package com.ext.portlet.service.persistence;

import com.ext.portlet.model.RolesCategory;
import com.ext.portlet.service.RolesCategoryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class RolesCategoryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RolesCategoryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(RolesCategoryLocalServiceUtil.getService());
        setClass(RolesCategory.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("roleId");
    }
}
