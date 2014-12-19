package com.ext.portlet.service.persistence;

import com.ext.portlet.model.EmailList;
import com.ext.portlet.service.EmailListLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class EmailListActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public EmailListActionableDynamicQuery() throws SystemException {
        setBaseLocalService(EmailListLocalServiceUtil.getService());
        setClass(EmailList.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
