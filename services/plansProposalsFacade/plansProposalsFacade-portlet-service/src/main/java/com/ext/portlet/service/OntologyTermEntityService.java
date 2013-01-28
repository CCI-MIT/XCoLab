package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the ontology term entity remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityServiceUtil
 * @see com.ext.portlet.service.base.OntologyTermEntityServiceBaseImpl
 * @see com.ext.portlet.service.impl.OntologyTermEntityServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface OntologyTermEntityService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link OntologyTermEntityServiceUtil} to access the ontology term entity remote service. Add custom service methods to {@link com.ext.portlet.service.impl.OntologyTermEntityServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
