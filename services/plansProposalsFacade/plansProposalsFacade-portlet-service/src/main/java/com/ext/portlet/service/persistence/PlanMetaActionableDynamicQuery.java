package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanMeta;
import com.ext.portlet.service.PlanMetaLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanMetaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanMetaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanMetaLocalServiceUtil.getService());
        setClass(PlanMeta.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
