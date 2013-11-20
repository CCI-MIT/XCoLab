package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanTemplateActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanTemplateActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanTemplateLocalServiceUtil.getService());
        setClass(PlanTemplate.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
