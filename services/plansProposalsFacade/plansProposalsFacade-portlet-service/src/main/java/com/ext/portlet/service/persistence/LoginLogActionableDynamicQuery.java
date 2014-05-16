package com.ext.portlet.service.persistence;

import com.ext.portlet.model.LoginLog;
import com.ext.portlet.service.LoginLogLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class LoginLogActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public LoginLogActionableDynamicQuery() throws SystemException {
        setBaseLocalService(LoginLogLocalServiceUtil.getService());
        setClass(LoginLog.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("pk");
    }
}
