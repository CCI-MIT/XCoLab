package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelCategory;
import com.ext.portlet.service.ModelCategoryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelCategoryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelCategoryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ModelCategoryLocalServiceUtil.getService());
        setClass(ModelCategory.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelCategoryPK");
    }
}
