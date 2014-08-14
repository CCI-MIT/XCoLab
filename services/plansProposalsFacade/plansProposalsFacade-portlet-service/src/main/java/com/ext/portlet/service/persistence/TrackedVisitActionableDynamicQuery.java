package com.ext.portlet.service.persistence;

import com.ext.portlet.model.TrackedVisit;
import com.ext.portlet.service.TrackedVisitLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class TrackedVisitActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public TrackedVisitActionableDynamicQuery() throws SystemException {
        setBaseLocalService(TrackedVisitLocalServiceUtil.getService());
        setClass(TrackedVisit.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
