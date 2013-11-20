package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanItemActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanItemActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanItemLocalServiceUtil.getService());
        setClass(PlanItem.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
