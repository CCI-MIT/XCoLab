package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelPosition;
import com.ext.portlet.service.ModelPositionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelPositionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelPositionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModelPositionLocalServiceUtil.getService());
        setClass(ModelPosition.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
