package com.ext.portlet.service.persistence;

import com.ext.portlet.model.OntologyTermEntity;
import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class OntologyTermEntityActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public OntologyTermEntityActionableDynamicQuery() throws SystemException {
        setBaseLocalService(OntologyTermEntityLocalServiceUtil.getService());
        setClass(OntologyTermEntity.class);

        setClassLoader(com.ext.portlet.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("id");
    }
}
