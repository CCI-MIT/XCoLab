package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestPhaseTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestPhaseTypeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestPhaseTypeLocalServiceUtil.getService());
        setClass(ContestPhaseType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
