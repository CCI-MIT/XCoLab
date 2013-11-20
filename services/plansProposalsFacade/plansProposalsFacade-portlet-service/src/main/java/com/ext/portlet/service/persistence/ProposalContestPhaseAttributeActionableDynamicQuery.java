package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalContestPhaseAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalContestPhaseAttributeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProposalContestPhaseAttributeLocalServiceUtil.getService());
        setClass(ProposalContestPhaseAttribute.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
