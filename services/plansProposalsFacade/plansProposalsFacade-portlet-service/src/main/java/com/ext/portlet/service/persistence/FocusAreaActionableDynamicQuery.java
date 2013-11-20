package com.ext.portlet.service.persistence;

import com.ext.portlet.model.FocusArea;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class FocusAreaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FocusAreaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(FocusAreaLocalServiceUtil.getService());
        setClass(FocusArea.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
