package com.xcolab.services.sample.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the sample entity remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntityServiceUtil
 * @see com.xcolab.services.sample.service.base.SampleEntityServiceBaseImpl
 * @see com.xcolab.services.sample.service.impl.SampleEntityServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface SampleEntityService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SampleEntityServiceUtil} to access the sample entity remote service. Add custom service methods to {@link com.xcolab.services.sample.service.impl.SampleEntityServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
