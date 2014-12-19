package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelGlobalPreferenceActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelGlobalPreferenceActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ModelGlobalPreferenceLocalServiceUtil.getService());
        setClass(ModelGlobalPreference.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelGlobalPreferencePK");
    }
}
