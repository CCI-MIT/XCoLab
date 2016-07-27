package org.xcolab.portlets.contests;

import com.ext.portlet.contests.ContestStatus;

import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.wrapperNewService.BaseContestWrapper;

import java.io.Serializable;

public class ContestWrapper extends BaseContestWrapper implements Serializable {

    private static final Log _log = LogFactoryUtil.getLog(ContestWrapper.class);

    private static final long serialVersionUID = 1L;

    private org.xcolab.client.contest.pojo.Contest localContestPojo;

    public ContestWrapper(Contest contest) throws ContestNotFoundException {
            super(contest.getContestPK());
    }

    public long getTotalCommentsCount() {

        Integer contestComments = CommentClient.countComments(contest.getDiscussionGroupId());

        return contestComments;
    }

    public String getLogoPath() {
        return contest.getLogoPath();
    }

    public long getTotalComments() { // What is the difference?
        return getTotalCommentsCount();
    }

    public boolean getContestInVotingPhase() {
            ContestPhase phase = ContestClient.getActivePhase(contest.getContestPK());
            if (phase == null) {
                return false;
            }

            String status = phase.getContestStatusStr();
            return status != null && ContestStatus.valueOf(status).isCanVote();

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

