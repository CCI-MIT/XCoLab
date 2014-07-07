package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalRatingTypeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalRatingTypeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalRatingTypeLocalServiceUtil.getService());
        setClass(ProposalRatingType.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
