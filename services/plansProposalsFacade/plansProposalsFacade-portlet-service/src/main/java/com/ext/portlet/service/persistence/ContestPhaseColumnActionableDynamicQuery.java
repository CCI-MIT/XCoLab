package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseColumn;
import com.ext.portlet.service.ContestPhaseColumnLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestPhaseColumnActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestPhaseColumnActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestPhaseColumnLocalServiceUtil.getService());
        setClass(ContestPhaseColumn.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
