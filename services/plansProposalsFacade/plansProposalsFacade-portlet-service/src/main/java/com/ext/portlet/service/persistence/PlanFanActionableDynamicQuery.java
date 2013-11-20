package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanFan;
import com.ext.portlet.service.PlanFanLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanFanActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanFanActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanFanLocalServiceUtil.getService());
        setClass(PlanFan.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
