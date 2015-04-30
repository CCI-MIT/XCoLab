package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateMaxFocusArea;
import com.ext.portlet.service.ImpactTemplateMaxFocusAreaLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ImpactTemplateMaxFocusAreaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImpactTemplateMaxFocusAreaActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImpactTemplateMaxFocusAreaLocalServiceUtil.getService());
        setClass(ImpactTemplateMaxFocusArea.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.focusAreaListId");
    }
}
