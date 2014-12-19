package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class Proposal2PhaseActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public Proposal2PhaseActionableDynamicQuery() throws SystemException {
        setBaseLocalService(Proposal2PhaseLocalServiceUtil.getService());
        setClass(Proposal2Phase.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.proposalId");
    }
}
