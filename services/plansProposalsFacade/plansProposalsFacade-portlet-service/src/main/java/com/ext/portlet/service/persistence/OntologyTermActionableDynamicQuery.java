package com.ext.portlet.service.persistence;

import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class OntologyTermActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public OntologyTermActionableDynamicQuery() throws SystemException {
        setBaseLocalService(OntologyTermLocalServiceUtil.getService());
        setClass(OntologyTerm.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
