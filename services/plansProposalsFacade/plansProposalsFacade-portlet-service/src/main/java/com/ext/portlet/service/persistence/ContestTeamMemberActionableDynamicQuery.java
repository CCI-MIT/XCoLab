package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ContestTeamMemberActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ContestTeamMemberActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ContestTeamMemberLocalServiceUtil.getService());
        setClass(ContestTeamMember.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
