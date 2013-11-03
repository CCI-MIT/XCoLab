package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the proposal attribute remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeServiceUtil
 * @see com.ext.portlet.service.base.ProposalAttributeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalAttributeServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ProposalAttributeService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalAttributeServiceUtil} to access the proposal attribute remote service. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalAttributeServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
