package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalLocalServiceUtil.getService());
        setClass(Proposal.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("proposalId");
    }
}
