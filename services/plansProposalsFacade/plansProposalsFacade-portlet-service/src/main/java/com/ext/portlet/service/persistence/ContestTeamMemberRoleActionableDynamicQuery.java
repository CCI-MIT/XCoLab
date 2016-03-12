package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestTeamMemberRole;
import com.ext.portlet.service.ContestTeamMemberRoleLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestTeamMemberRoleActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestTeamMemberRoleActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ContestTeamMemberRoleLocalServiceUtil.getService());
        setClass(ContestTeamMemberRole.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
