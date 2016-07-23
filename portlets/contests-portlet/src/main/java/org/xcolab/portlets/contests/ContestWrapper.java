package org.xcolab.portlets.contests;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.wrappers.BaseContestWrapper;

import java.io.Serializable;

public class ContestWrapper extends BaseContestWrapper implements Serializable {

    private static final Log _log = LogFactoryUtil.getLog(ContestWrapper.class);

    private static final long serialVersionUID = 1L;

    private org.xcolab.client.contest.pojo.Contest localContestPojo;

    public ContestWrapper(org.xcolab.client.contest.pojo.Contest contest) {
        try {
            super(contest.getContestPK());
        } catch(SystemException | PortalException ignored){

        }
    }

    public long getTotalCommentsCount() {
        try {
            return ContestLocalServiceUtil.getTotalCommentsCount(contest);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error("Could not find total comments count for contest " + contest.getContestPK());
            return 0L;
        }
    }

    public String getLogoPath() {
        try {
            return ContestLocalServiceUtil.getLogoPath(contest);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error("Could not find logo path for contest " + contest.getContestPK());
            return "";
        }
    }

    public long getTotalComments() {
        try {
            return ContestLocalServiceUtil.getTotalCommentsCount(contest);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error("Could not find total comments for contest " + contest.getContestPK());
            return 0L;
        }
    }

    public boolean getContestInVotingPhase() {
        try {
            ContestPhase phase = ContestLocalServiceUtil.getActivePhase(contest);
            if (phase == null) {
                return false;
            }

            String status = ContestPhaseLocalServiceUtil.getContestStatusStr(phase);
            return status != null && ContestStatus.valueOf(status).isCanVote();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error("Could not find active phase for contest " + contest.getContestPK());
            return false;
        }
    }

    public long getVotesCount() {
        try {
            return ContestLocalServiceUtil.getVotesCount(contest);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error("Could not find votes count for contest " + contest.getContestPK());
            return 0L;
        }
    }
}

