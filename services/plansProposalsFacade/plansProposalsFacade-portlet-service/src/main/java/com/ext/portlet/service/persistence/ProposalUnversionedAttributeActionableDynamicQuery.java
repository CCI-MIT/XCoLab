package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalUnversionedAttribute;
import com.ext.portlet.service.ProposalUnversionedAttributeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class ProposalUnversionedAttributeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProposalUnversionedAttributeActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProposalUnversionedAttributeLocalServiceUtil.getService());
        setClass(ProposalUnversionedAttribute.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
