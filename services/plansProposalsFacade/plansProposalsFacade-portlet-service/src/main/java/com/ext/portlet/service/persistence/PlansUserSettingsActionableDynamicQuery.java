package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlansUserSettings;
import com.ext.portlet.service.PlansUserSettingsLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlansUserSettingsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlansUserSettingsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlansUserSettingsLocalServiceUtil.getService());
        setClass(PlansUserSettings.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planUserSettingsId");
    }
}
