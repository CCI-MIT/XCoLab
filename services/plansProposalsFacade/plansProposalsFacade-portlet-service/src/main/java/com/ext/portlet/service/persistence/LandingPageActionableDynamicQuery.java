package com.ext.portlet.service.persistence;

import com.ext.portlet.model.LandingPage;
import com.ext.portlet.service.LandingPageLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class LandingPageActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public LandingPageActionableDynamicQuery() throws SystemException {
        setBaseLocalService(LandingPageLocalServiceUtil.getService());
        setClass(LandingPage.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
