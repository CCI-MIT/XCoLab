package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelInputGroup;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelInputGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelInputGroupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModelInputGroupLocalServiceUtil.getService());
        setClass(ModelInputGroup.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelInputGroupPK");
    }
}
