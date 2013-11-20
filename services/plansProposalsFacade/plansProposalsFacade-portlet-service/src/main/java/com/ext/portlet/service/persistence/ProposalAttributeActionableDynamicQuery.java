package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalAttributeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProposalAttributeLocalServiceUtil.getService());
        setClass(ProposalAttribute.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
