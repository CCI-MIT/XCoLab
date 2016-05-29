package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalMoveHistory;
import com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalMoveHistoryActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalMoveHistoryActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProposalMoveHistoryLocalServiceUtil.getService());
        setClass(ProposalMoveHistory.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id_");
    }
}
