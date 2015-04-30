package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateFocusAreaList;
import com.ext.portlet.service.ImpactTemplateFocusAreaListLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ImpactTemplateFocusAreaListActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ImpactTemplateFocusAreaListActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ImpactTemplateFocusAreaListLocalServiceUtil.getService());
        setClass(ImpactTemplateFocusAreaList.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("focusAreaListId");
    }
}
