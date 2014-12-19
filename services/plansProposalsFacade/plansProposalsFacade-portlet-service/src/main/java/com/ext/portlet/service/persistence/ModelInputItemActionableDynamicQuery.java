package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelInputItemActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelInputItemActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModelInputItemLocalServiceUtil.getService());
        setClass(ModelInputItem.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelInputItemPK");
    }
}
