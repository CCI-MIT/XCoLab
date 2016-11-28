package com.ext.portlet.service.impl;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.base.ContestServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.ac.AccessControlled;

import java.util.List;

/**
 * The implementation of the contest remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestServiceBaseImpl
 * @see com.ext.portlet.service.ContestServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class ContestServiceImpl extends ContestServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ContestServiceUtil} to access the contest remote service.
     */

    /**
     * Returns a list of open contest for regular users and returns all contests for staff users
     * @throws PortalException
     * @throws SystemException
     */
    @Override
    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    //TODO: still used
    public List<Contest> getContestsOpenForProposals() throws PortalException, SystemException {
//        User user = getUser();
//        List<Role> roles = user.getRoles();
//
//        boolean admin = false;
//        for (Role role : roles) {
//            if (role.getName().equals("Administrator")) {
//                admin = true;
//                break;
//            }
//        }
//
//    	List<Contest> returnList = new ArrayList<>();
//    	for (Contest contest: contestLocalService.findByActive(true)) {
//    		ContestPhase cp = contestLocalService.getActiveOrLastPhase(contest);
//    		if (contestPhaseLocalService.getPhaseActive(cp)) {
//                String statusStr = ContestPhaseLocalServiceUtil.getContestStatusStr(cp);
//                ContestStatus status = null;
//                if (statusStr != null) {
//                    status = ContestStatus.valueOf(statusStr);
//                }
//                if (admin || (status != null && status.isCanCreate())) {
//                    returnList.add(contest);
//                }
//    		}
//    	}
//
//        // Add non active contests
//        if (admin) {
//            returnList.addAll(contestLocalService.findByActive(false));
//        }
//
//    	return returnList;
        return null;
    }
}
