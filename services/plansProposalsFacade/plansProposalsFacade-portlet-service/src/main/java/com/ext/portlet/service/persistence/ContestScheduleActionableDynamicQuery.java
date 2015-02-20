package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestSchedule;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestScheduleActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestScheduleActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestScheduleLocalServiceUtil.getService());
        setClass(ContestSchedule.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
