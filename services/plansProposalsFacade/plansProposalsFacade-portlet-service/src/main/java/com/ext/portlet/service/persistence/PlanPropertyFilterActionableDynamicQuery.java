package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPropertyFilter;
import com.ext.portlet.service.PlanPropertyFilterLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanPropertyFilterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanPropertyFilterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanPropertyFilterLocalServiceUtil.getService());
        setClass(PlanPropertyFilter.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planPropertyFilterId");
    }
}
