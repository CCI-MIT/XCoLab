package com.ext.portlet.service.persistence;

import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class FocusAreaOntologyTermActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FocusAreaOntologyTermActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(FocusAreaOntologyTermLocalServiceUtil.getService());
        setClass(FocusAreaOntologyTerm.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.focusAreaId");
    }
}
