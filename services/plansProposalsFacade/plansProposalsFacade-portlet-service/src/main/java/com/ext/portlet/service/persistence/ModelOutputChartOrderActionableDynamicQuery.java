package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelOutputChartOrder;
import com.ext.portlet.service.ModelOutputChartOrderLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ModelOutputChartOrderActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ModelOutputChartOrderActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ModelOutputChartOrderLocalServiceUtil.getService());
        setClass(ModelOutputChartOrder.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("modelOutputChartOrderPK");
    }
}
