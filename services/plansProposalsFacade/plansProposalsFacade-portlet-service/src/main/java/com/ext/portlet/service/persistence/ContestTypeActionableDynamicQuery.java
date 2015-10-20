package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestTypeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestTypeLocalServiceUtil.getService());
        setClass(ContestType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
