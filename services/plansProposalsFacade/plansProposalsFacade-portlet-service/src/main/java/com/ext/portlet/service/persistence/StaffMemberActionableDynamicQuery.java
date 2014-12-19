package com.ext.portlet.service.persistence;

import com.ext.portlet.model.StaffMember;
import com.ext.portlet.service.StaffMemberLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class StaffMemberActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StaffMemberActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StaffMemberLocalServiceUtil.getService());
        setClass(StaffMember.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
