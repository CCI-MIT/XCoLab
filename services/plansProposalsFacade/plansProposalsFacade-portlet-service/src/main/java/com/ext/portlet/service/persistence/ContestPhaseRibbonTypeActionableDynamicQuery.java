package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestPhaseRibbonTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestPhaseRibbonTypeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ContestPhaseRibbonTypeLocalServiceUtil.getService());
        setClass(ContestPhaseRibbonType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
