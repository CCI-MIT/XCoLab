package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the contest team member remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberServiceUtil
 * @see com.ext.portlet.contests.service.base.ContestTeamMemberServiceBaseImpl
 * @see com.ext.portlet.contests.service.impl.ContestTeamMemberServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ContestTeamMemberService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestTeamMemberServiceUtil} to access the contest team member remote service. Add custom service methods to {@link com.ext.portlet.contests.service.impl.ContestTeamMemberServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
}
