package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelOutputItem;
import com.ext.portlet.service.ModelOutputItemLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelOutputItemActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelOutputItemActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModelOutputItemLocalServiceUtil.getService());
        setClass(ModelOutputItem.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelOutputItemModifierPK");
    }
}
