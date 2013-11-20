package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanPositionItem;
import com.ext.portlet.service.PlanPositionItemLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanPositionItemActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanPositionItemActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanPositionItemLocalServiceUtil.getService());
        setClass(PlanPositionItem.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.planPositionsId");
    }
}
