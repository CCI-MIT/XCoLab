package org.xcolab.services.proposalsService.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the foo remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FooServiceUtil
 * @see org.xcolab.services.proposalsService.service.base.FooServiceBaseImpl
 * @see org.xcolab.services.proposalsService.service.impl.FooServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface FooService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FooServiceUtil} to access the foo remote service. Add custom service methods to {@link org.xcolab.services.proposalsService.service.impl.FooServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
