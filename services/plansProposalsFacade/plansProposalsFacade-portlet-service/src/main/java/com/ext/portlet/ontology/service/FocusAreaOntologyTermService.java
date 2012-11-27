package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the focus area ontology term remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermServiceUtil
 * @see com.ext.portlet.ontology.service.base.FocusAreaOntologyTermServiceBaseImpl
 * @see com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface FocusAreaOntologyTermService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FocusAreaOntologyTermServiceUtil} to access the focus area ontology term remote service. Add custom service methods to {@link com.ext.portlet.ontology.service.impl.FocusAreaOntologyTermServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
