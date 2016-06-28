package org.xcolab.portlets.contests;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.wrappers.BaseContestWrapper;

import java.io.Serializable;

public class ContestWrapper extends BaseContestWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ContestWrapper(Contest contest) {
        super(contest);
    }

    public long getTotalCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getTotalCommentsCount(contest);
    }

    public String getLogoPath() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getLogoPath(contest);
    }

    public long getTotalComments() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getTotalCommentsCount(contest);
    }
    
    public boolean getContestInVotingPhase() throws SystemException, PortalException {
        ContestPhase phase = ContestLocalServiceUtil.getActivePhase(contest);
        if (phase == null) {
            return false;
        }

        String status = ContestPhaseLocalServiceUtil.getContestStatusStr(phase);
        return status != null && ContestStatus.valueOf(status).isCanVote();
    }
    
    public long getVotesCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getVotesCount(contest);
    }
}

