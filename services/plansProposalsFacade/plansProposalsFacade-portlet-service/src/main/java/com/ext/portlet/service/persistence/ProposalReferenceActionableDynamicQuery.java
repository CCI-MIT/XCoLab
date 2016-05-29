package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalReferenceActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalReferenceActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalReferenceLocalServiceUtil.getService());
        setClass(ProposalReference.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.proposalId");
    }
}
