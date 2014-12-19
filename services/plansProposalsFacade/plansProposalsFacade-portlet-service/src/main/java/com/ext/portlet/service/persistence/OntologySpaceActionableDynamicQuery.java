package com.ext.portlet.service.persistence;

import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class OntologySpaceActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public OntologySpaceActionableDynamicQuery() throws SystemException {
        setBaseLocalService(OntologySpaceLocalServiceUtil.getService());
        setClass(OntologySpace.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
