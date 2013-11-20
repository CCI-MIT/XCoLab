package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanRelated;
import com.ext.portlet.service.PlanRelatedLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanRelatedActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanRelatedActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanRelatedLocalServiceUtil.getService());
        setClass(PlanRelated.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.sectionId");
    }
}
