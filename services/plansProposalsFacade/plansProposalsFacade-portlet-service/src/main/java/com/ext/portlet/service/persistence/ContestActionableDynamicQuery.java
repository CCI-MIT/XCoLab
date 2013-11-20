package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestLocalServiceUtil.getService());
        setClass(Contest.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ContestPK");
    }
}
