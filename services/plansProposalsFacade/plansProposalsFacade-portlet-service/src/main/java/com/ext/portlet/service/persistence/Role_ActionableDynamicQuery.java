package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Role_;
import com.ext.portlet.service.Role_LocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class Role_ActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public Role_ActionableDynamicQuery() throws SystemException {
        setBaseLocalService(Role_LocalServiceUtil.getService());
        setClass(Role_.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("roleId");
    }
}
