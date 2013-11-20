package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanAttribute;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanAttributeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanAttributeLocalServiceUtil.getService());
        setClass(PlanAttribute.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("attributeId");
    }
}
