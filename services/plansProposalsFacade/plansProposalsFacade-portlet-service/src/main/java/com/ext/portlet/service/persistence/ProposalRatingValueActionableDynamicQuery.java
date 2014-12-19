package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalRatingValueActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalRatingValueActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProposalRatingValueLocalServiceUtil.getService());
        setClass(ProposalRatingValue.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
