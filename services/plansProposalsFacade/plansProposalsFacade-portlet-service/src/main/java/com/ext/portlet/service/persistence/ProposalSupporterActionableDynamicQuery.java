package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalSupporterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalSupporterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalSupporterLocalServiceUtil.getService());
        setClass(ProposalSupporter.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.proposalId");
    }
}
