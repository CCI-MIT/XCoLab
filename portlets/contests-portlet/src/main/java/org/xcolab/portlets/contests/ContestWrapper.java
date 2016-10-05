package org.xcolab.portlets.contests;

import com.ext.portlet.contests.ContestStatus;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.proposals.ProposalVoteClient;

import org.xcolab.wrappers.BaseContestWrapper;

import java.io.Serializable;

public class ContestWrapper extends BaseContestWrapper implements Serializable {

    private static final Log _log = LogFactoryUtil.getLog(ContestWrapper.class);

    private static final long serialVersionUID = 1L;


    public ContestWrapper(Contest contest) throws ContestNotFoundException {
            super(contest.getContestPK());
    }

    public long getTotalCommentsCount() {

        Integer contestComments = CommentClientUtil.countComments(contest.getDiscussionGroupId());
        ContestPhase phase = ContestClient.getActivePhase(contest.getContestPK());
        contestComments += CommentClientUtil.countCommentsInContestPhase(
                phase.getContestPhasePK(), phase.getContestPK());

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
        ContestPhase phase = ContestClient.getActivePhase(contest.getContestPK());
        return ProposalVoteClient.countProposalVotesInContestPhase(phase.getContestPhasePK());
    }
}

