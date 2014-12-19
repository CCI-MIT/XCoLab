package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanModelRun;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanModelRunActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanModelRunActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanModelRunLocalServiceUtil.getService());
        setClass(PlanModelRun.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
