package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalRatingActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalRatingActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalRatingLocalServiceUtil.getService());
        setClass(ProposalRating.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
