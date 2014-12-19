package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalVoteActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalVoteActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalVoteLocalServiceUtil.getService());
        setClass(ProposalVote.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.contestPhaseId");
    }
}
