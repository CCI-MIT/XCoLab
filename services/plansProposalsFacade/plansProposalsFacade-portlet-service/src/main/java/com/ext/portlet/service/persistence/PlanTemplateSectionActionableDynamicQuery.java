package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanTemplateSectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanTemplateSectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(PlanTemplateSectionLocalServiceUtil.getService());
        setClass(PlanTemplateSection.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.planTemplateId");
    }
}
