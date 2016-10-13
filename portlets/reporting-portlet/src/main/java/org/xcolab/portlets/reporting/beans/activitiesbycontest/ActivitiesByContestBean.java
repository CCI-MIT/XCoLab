package org.xcolab.portlets.reporting.beans.activitiesbycontest;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.pojo.Comment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author pdeboer
 *         First created on 20/06/14 at 15:50
 */
public class ActivitiesByContestBean {
    public List<UserActivityByContest> get() throws Exception {
        List<UserActivityByContest> ret = new LinkedList<>();

        //this is madness!
        List<ProposalSupporter> proposalSupporters = ProposalSupporterLocalServiceUtil.getProposalSupporters(0, Integer.MAX_VALUE);
        List<ProposalVote> proposalVotes = ProposalVoteLocalServiceUtil.getProposalVotes(0, Integer.MAX_VALUE);
        List<User> users = UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
        List<Proposal> proposals = ProposalLocalServiceUtil.getProposals(0, Integer.MAX_VALUE);
        List<Comment> comments = CommentClientUtil.listComments(0, Integer.MAX_VALUE);

        Map<Long, Contest> proposalContestMap = getProposalContestMap();

        for (User user : users) {
            Map<Long, ContestActivity> contestActivityMap = new HashMap<>();

            try {
                calculateProposalCount(proposalContestMap, user, contestActivityMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                calculateCommentCount(proposals, comments, proposalContestMap, user, contestActivityMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                calculateSupportCount(proposalSupporters, proposalContestMap, user, contestActivityMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                calculateContestVotes(proposalVotes, proposalContestMap, user, contestActivityMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ret.add(new UserActivityByContest(user, new LinkedList<ContestActivity>(contestActivityMap.values())));
        }
        return ret;
    }

    private Map<Long, Contest> getProposalContestMap() throws com.liferay.portal.kernel.exception.SystemException, com.liferay.portal.kernel.exception.PortalException {
        Map<Long, Contest> proposalContestMap = new HashMap<>();
        List<Contest> contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        for (Contest contest : contests) {
            try {
                List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContest(contest.getContestPK());
                for (Proposal proposal : proposals) {
                    proposalContestMap.put(proposal.getProposalId(), contest);
                }
            } catch (Exception e) {
                System.out.println("no proposals in contest " + contest.getContestPK());
            }
        }
        return proposalContestMap;
    }

    private void calculateProposalCount(Map<Long, Contest> proposalContestMap, User user, Map<Long, ContestActivity> contestActivityMap) throws com.liferay.portal.kernel.exception.PortalException, com.liferay.portal.kernel.exception.SystemException {
        List<Proposal> userProposals = ProposalLocalServiceUtil.getUserProposals(user.getUserId());
        for (Proposal proposal : userProposals) {
            Contest contest = proposalContestMap.get(proposal.getProposalId());
            if (contest == null) continue;
            ContestActivity target = getOrAddContestActivity(contestActivityMap, contest);
            target.setAuthoredProposalCount(target.getAuthoredProposalCount() + 1);
        }
    }

    private void calculateCommentCount(List<Proposal> proposals, List<Comment> comments, Map<Long, Contest> proposalContestMap, User user, Map<Long, ContestActivity> contestActivityMap) {
        List<Comment> targetMessages = new LinkedList<>();
        for (Comment comment : comments) {
            if (comment.getAuthorId() == user.getUserId()) {
                targetMessages.add(comment);
            }
        }

        //comments on proposals
        for (Proposal proposal : proposals) {
            for (Comment targetComment : targetMessages) {
                if (proposal.getDiscussionId() == targetComment.getThreadId()) {
                    Contest contest = proposalContestMap.get(proposal.getProposalId());
                    if (contest == null) continue;
                    ContestActivity target = getOrAddContestActivity(contestActivityMap, contest);
                    target.setCommentCount(target.getCommentCount() + 1);
                }
            }
        }
    }

    private void calculateSupportCount(List<ProposalSupporter> proposalSupporters, Map<Long, Contest> proposalContestMap, User user, Map<Long, ContestActivity> contestActivityMap) {
        for (ProposalSupporter proposalSupporter : proposalSupporters) {
            if (proposalSupporter.getUserId() == user.getUserId()) {
                Contest contest = proposalContestMap.get(proposalSupporter.getProposalId());
                if (contest == null) continue;
                ContestActivity target = getOrAddContestActivity(contestActivityMap, contest);
                target.setSupportedProposalCount(target.getSupportedProposalCount() + 1);
            }
        }
    }

    private void calculateContestVotes(List<ProposalVote> proposalVotes, Map<Long, Contest> proposalContestMap, User user, Map<Long, ContestActivity> contestActivityMap) {
        for (ProposalVote proposalVote : proposalVotes) {
            if (proposalVote.getUserId() == user.getUserId()) {
                Contest contest = proposalContestMap.get(proposalVote.getProposalId());
                if (contest == null) continue;
                ContestActivity target = getOrAddContestActivity(contestActivityMap, contest);
                target.setVotedProposalCount(target.getVotedProposalCount() + 1);
            }
        }
    }

    private ContestActivity getOrAddContestActivity(Map<Long, ContestActivity> contestActivityMap, Contest contest) {
        if (contestActivityMap.containsKey(contest.getContestPK())) {
            return contestActivityMap.get(contest.getContestPK());
        } else {
            ContestActivity target = new ContestActivity(contest);
            contestActivityMap.put(contest.getContestPK(), target);
            return target;
        }
    }
}
