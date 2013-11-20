package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTypeAttribute;
import com.ext.portlet.service.PlanTypeAttributeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanTypeAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanTypeAttributeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanTypeAttributeLocalServiceUtil.getService());
        setClass(PlanTypeAttribute.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planTypeAttributeId");
    }
}
