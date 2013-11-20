package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalVersionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalVersionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalVersionLocalServiceUtil.getService());
        setClass(ProposalVersion.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.proposalId");
    }
}
