package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MemberCategory;
import com.ext.portlet.service.MemberCategoryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class MemberCategoryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MemberCategoryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MemberCategoryLocalServiceUtil.getService());
        setClass(MemberCategory.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("roleId");
    }
}
