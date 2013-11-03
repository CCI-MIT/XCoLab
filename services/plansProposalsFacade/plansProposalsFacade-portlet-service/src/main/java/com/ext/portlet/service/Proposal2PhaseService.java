package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the proposal2 phase remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2PhaseServiceUtil
 * @see com.ext.portlet.service.base.Proposal2PhaseServiceBaseImpl
 * @see com.ext.portlet.service.impl.Proposal2PhaseServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface Proposal2PhaseService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link Proposal2PhaseServiceUtil} to access the proposal2 phase remote service. Add custom service methods to {@link com.ext.portlet.service.impl.Proposal2PhaseServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
