package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanVote;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class PlanVoteActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PlanVoteActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PlanVoteLocalServiceUtil.getService());
        setClass(PlanVote.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
