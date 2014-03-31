package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.NoSuchMessageRecipientStatusException;
import com.ext.portlet.service.base.ContestServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.security.auth.PrincipalThreadLocal;

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

    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public List<Contest> getContestsOpenForProposals() throws PortalException, SystemException {
    	List<Contest> contests = new ArrayList<>();
    	for (Contest activeContest: contestLocalService.findByActive(true)) {
    		ContestPhase cp = contestLocalService.getActiveOrLastPhase(activeContest);
    		if (contestPhaseLocalService.getPhaseActive(cp)) {
                String statusStr = ContestPhaseLocalServiceUtil.getContestStatusStr(cp);
                ContestStatus status = null;
                if (statusStr != null) {
                    status = ContestStatus.valueOf(statusStr);
                }
                if (status != null && status.isCanCreate()) {
                	/*
                	JSONObject obj = JSONFactoryUtil.createJSONObject();
                	obj.put("contestPK", activeContest.getContestPK());
                	obj.put("shortName", activeContest.getContestShortName());
                	obj.put("name", activeContest.getContestName());
                	obj.put("description", activeContest.getContestDescription());
                	*/
                	contests.add(activeContest);
                }
    		}
    	}
    	return contests;
    }

    @JSONWebService
     @AccessControlled(guestAccessEnabled=true)
     public int getNumberOfUnreadMessages() throws PortalException, SystemException {
        int unreadMessages = 0;
        if (getUserId() != 0) {
            try {
                unreadMessages = MessageRecipientStatusLocalServiceUtil.countUnreadMessages(getUserId());
            } catch (NoSuchMessageRecipientStatusException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();
            }
        }

        return unreadMessages;

    }
}
