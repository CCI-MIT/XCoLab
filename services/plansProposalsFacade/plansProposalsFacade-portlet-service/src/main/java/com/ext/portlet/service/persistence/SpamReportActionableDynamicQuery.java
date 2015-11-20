package com.ext.portlet.service.persistence;

import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.SpamReportLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class SpamReportActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public SpamReportActionableDynamicQuery() throws SystemException {
        setBaseLocalService(SpamReportLocalServiceUtil.getService());
        setClass(SpamReport.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id_");
    }
}
