package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestDebate;
import com.ext.portlet.service.ContestDebateLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestDebateActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestDebateActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestDebateLocalServiceUtil.getService());
        setClass(ContestDebate.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
