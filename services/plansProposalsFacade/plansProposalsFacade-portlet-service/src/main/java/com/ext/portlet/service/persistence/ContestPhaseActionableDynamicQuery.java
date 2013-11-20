package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestPhaseActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestPhaseActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestPhaseLocalServiceUtil.getService());
        setClass(ContestPhase.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ContestPhasePK");
    }
}
