package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanColumnSettings;
import com.ext.portlet.service.PlanColumnSettingsLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanColumnSettingsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanColumnSettingsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanColumnSettingsLocalServiceUtil.getService());
        setClass(PlanColumnSettings.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planColumnSettingsId");
    }
}
