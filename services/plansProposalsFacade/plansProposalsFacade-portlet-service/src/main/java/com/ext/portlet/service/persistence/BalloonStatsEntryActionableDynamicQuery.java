package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonStatsEntry;
import com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BalloonStatsEntryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public BalloonStatsEntryActionableDynamicQuery() throws SystemException {
        setBaseLocalService(BalloonStatsEntryLocalServiceUtil.getService());
        setClass(BalloonStatsEntry.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
