package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Plan2Proposal;
import com.ext.portlet.service.Plan2ProposalLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class Plan2ProposalActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public Plan2ProposalActionableDynamicQuery() throws SystemException {
        setBaseLocalService(Plan2ProposalLocalServiceUtil.getService());
        setClass(Plan2Proposal.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("planId");
    }
}
