package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanDescription;
import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanDescriptionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanDescriptionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanDescriptionLocalServiceUtil.getService());
        setClass(PlanDescription.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
