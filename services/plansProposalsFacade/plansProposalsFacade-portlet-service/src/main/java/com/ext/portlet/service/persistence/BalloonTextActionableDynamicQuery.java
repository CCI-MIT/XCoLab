package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonText;
import com.ext.portlet.service.BalloonTextLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BalloonTextActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public BalloonTextActionableDynamicQuery() throws SystemException {
        setBaseLocalService(BalloonTextLocalServiceUtil.getService());
        setClass(BalloonText.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
