package org.xcolab.portlets.reporting.beans;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author pdeboer
 *         First created on 19/06/14 at 20:23
 */
public class AuthorAttractionBean {
    List<ProposalVote> proposalVotes = ProposalVoteLocalServiceUtil.getProposalVotes(0, Integer.MAX_VALUE);
    private List<ProposalSupporter> proposalSupporters= ProposalSupporterLocalServiceUtil.getProposalSupporters(0, Integer.MAX_VALUE);

    public AuthorAttractionBean() throws Exception {

    }

    public class UserAttractionPairs {
        final User author, voter;

        private UserAttractionPairs(User author, User voter) {
            this.author = author;
            this.voter = voter;
        }

        public User getAuthor() {
            return author;
        }

        public User getVoter() {
            return voter;
        }
    }

    public class ProposalSupporterPair {
        private final Proposal proposal;
        private final User supporter;

        public ProposalSupporterPair(Proposal proposal, User supporter) {
            this.proposal = proposal;
            this.supporter = supporter;
        }

        public Proposal getProposal() {
            return proposal;
        }

        public User getSupporter() {
            return supporter;
        }
    }

    public List<ProposalSupporterPair> getSupportersRegisteredBeforeVoting() throws Exception {
        List<ProposalSupporterPair> ret = new LinkedList<>();
        List<Contest> contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        for (Contest contest : contests) {
            ContestPhase votingPhase = getVotingPhase(contest);
            if(votingPhase!=null) {

                List<Proposal> proposalsInContest = ProposalLocalServiceUtil.getProposalsInContest(contest.getContestPK());

                for (Proposal proposal : proposalsInContest) {
                    for (ProposalSupporter proposalSupporter : getProposalSupporters(proposal)) {
                        if(proposalSupporter.getCreateDate().before(votingPhase.getPhaseStartDate())) {
                            User user = UserLocalServiceUtil.getUser(proposalSupporter.getUserId());
                            ret.add(new ProposalSupporterPair(proposal, user));
                        }
                    }
                }

            }

        }
        return ret;
    }

    private ContestPhase getVotingPhase(Contest contest) throws SystemException {
        List<ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for (ContestPhase phase : phases) {
            long type = phase.getContestPhaseType();
            if(type == 13L || type==3) {
                return phase;
            }
        }
        return null;
    }

    public List<UserAttractionPairs> getSupportersOfFinalistsThatRegisteredBeforeVoting() throws Exception {

        List<UserAttractionPairs> ret = new LinkedList<>();
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getContestPhases(0, 9999);


        for (ContestPhase contestPhase : contestPhases) {
            long phaseType = contestPhase.getContestPhaseType();
            if(phaseType == 13 || phaseType == 3) {
                List<Proposal> proposalsInContestPhase = ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK());

                for (Proposal p : proposalsInContestPhase) {
                    List<User> members = ProposalLocalServiceUtil.getMembers(p.getProposalId());

                    //addUserVotePairs(ret, p, members);
                    for (ProposalSupporter proposalSupporter : getProposalSupporters(p)) {
                        User supporter = UserLocalServiceUtil.getUser(proposalSupporter.getUserId());
                        if(supporter.getCreateDate().before(contestPhase.getPhaseStartDate())) {
                            for (User member : members) {
                                ret.add(new UserAttractionPairs(member, supporter));
                            }
                        }
                    }

                }

            }
        }
        return ret;
    }

    private void addUserVotePairs(List<UserAttractionPairs> ret, Proposal p, List<User> members) throws PortalException, SystemException {
        for (ProposalVote proposalVote : getVotesForProposal(p)) {
            User voter = UserLocalServiceUtil.getUser(proposalVote.getUserId());
            if(voter.getCreateDate().before(p.getCreateDate())) {
                for (User member : members) {
                    ret.add(new UserAttractionPairs(member, voter));
                }
            }
        }
    }

    private List<ProposalSupporter> getProposalSupporters(Proposal p) {
        List<ProposalSupporter> ret = new LinkedList<>();
        for (ProposalSupporter proposalSupporter : proposalSupporters) {
            if(proposalSupporter.getProposalId() == p.getProposalId()) {
                ret.add(proposalSupporter);
            }
        }
        return ret;
    }

    private List<ProposalVote> getVotesForProposal(Proposal p) {
        List<ProposalVote> targetVotes = new LinkedList<>();
        for (ProposalVote proposalVote : proposalVotes) {
            if(proposalVote.getProposalId() == p.getProposalId()) {
                targetVotes.add(proposalVote);
            }
        }
        return targetVotes;
    }
}
