package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTeamHistory;
import com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanTeamHistoryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanTeamHistoryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanTeamHistoryLocalServiceUtil.getService());
        setClass(PlanTeamHistory.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
