package org.xcolab.wrappers;

import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.UsersGroupsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.helpers.ProposalContestPhaseAttributeHelper;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A wrapper class for proposals to be shared across portlets. More specific wrappers should inherit
 * from this one.
 */
public class BaseProposalWrapper {

    private static final Log _log = LogFactoryUtil.getLog(BaseProposalWrapper.class);

    protected final Proposal proposal;
    protected final int version;
    protected final Contest contest;

    protected final ContestPhase contestPhase;
    protected final Proposal2Phase proposal2Phase;
    protected final ProposalContestPhaseAttributeHelper proposalContestPhaseAttributeHelper;
    protected final ProposalAttributeHelper proposalAttributeHelper;

    protected List<BaseProposalTeamMemberWrapper> members;

    public BaseProposalWrapper(Proposal proposal) {
        this(proposal, proposal.getCurrentVersion(), null, null, null);
    }

    public BaseProposalWrapper(Proposal proposal, int version, Contest contest,
                               ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        this.proposal = proposal;
        this.version = version;
        this.contest = contest == null ? fetchContest(contestPhase) : contest;
        this.contestPhase = contestPhase == null ? fetchContestPhase() : contestPhase;
        this.proposal2Phase = proposal2Phase == null ? fetchProposal2Phase() : proposal2Phase;

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(proposal, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(proposal, version);
    }

    public BaseProposalWrapper(Proposal proposal, ContestPhase contestPhase) {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, null);
    }

    public BaseProposalWrapper(Proposal proposal, int version) {
        this(proposal, version, null, null, null);
    }

    private Contest fetchContest(ContestPhase contestPhase) {
        try {
            if (contestPhase != null) {
                return ContestClientUtil.getContest(contestPhase.getContestPK());
            }
            return ProposalClientUtil.getCurrentContestForProposal(proposal.getProposalId());
        } catch (ContestNotFoundException e) {
            throw new IllegalStateException("Could not find a contest for proposal "
                    + proposal.getProposalId(), e);
        }
    }

    private ContestPhase fetchContestPhase() {
        if (proposal2Phase != null) {
            return ContestClientUtil.getContestPhase(proposal2Phase.getContestPhaseId());
        }
        if (contest != null) {
            return ContestClientUtil.getActivePhase(contest.getContestPK());
        }
        _log.error(String.format("Could not get contest phase for proposal %d", proposal.getProposalId()));
        return null;
    }

    private Proposal2Phase fetchProposal2Phase() {
        if (proposal.getProposalId() == 0 || contestPhase == null || contestPhase.getContestPhasePK() == 0) {
            return null;
        }
        try {
            return ProposalPhaseClientUtil.getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
        } catch (Proposal2PhaseNotFoundException e) {
            _log.warn(String.format("Could not fetch p2p for proposal %d, contest phase %d",
                    proposal.getProposalId(), contestPhase.getContestPhasePK()));
        }
        return null;
    }

    public Long getProposalId() {
        return proposal.getProposalId();
    }

    public Date getCreateDate() {
        return proposal.getCreateDate();
    }

    public void setCreateDate(Date createDate) {
        proposal.setCreateDate(new Timestamp(createDate.getTime()));
    }

    public int getCurrentVersion() {
        return proposal.getCurrentVersion();
    }

    public void setCurrentVersion(int currentVersion) {
        proposal.setCurrentVersion(currentVersion);
    }

    public long getAuthorId() {
        return proposal.getAuthorId();
    }

    public void setAuthorId(long authorId) {
        proposal.setAuthorId(authorId);
    }

    public boolean getVisible() {
        return proposal.getVisible();
    }

    public boolean isVisibleInPhase() {
        ProposalContestPhaseAttribute visibleInPhase = proposalContestPhaseAttributeHelper.getAttributeOrNull(ProposalContestPhaseAttributeKeys.VISIBLE, 0);
        return proposal.getVisible() && (visibleInPhase == null || visibleInPhase.getNumericValue() > 0);
    }

    public long getDiscussionId() {
        return proposal.getDiscussionId();
    }

    public void setDiscussionId(long discussionId) {
        proposal.setDiscussionId(discussionId);
    }

    public long getJudgeDiscussionId() {
        return proposal.getJudgeDiscussionId();
    }

    public void setJudgeDiscussionId(long judgeDiscussionId) {
        proposal.setJudgeDiscussionId(judgeDiscussionId);
    }

    public long getResultsDiscussionId() {
        return proposal.getResultsDiscussionId();
    }

    public void setResultsDiscussionId(long resultsDiscussionId) {
        proposal.setResultsDiscussionId(resultsDiscussionId);
    }

    public long getFellowDiscussionId() {
        long fellowDiscussionId = proposal.getFellowDiscussionId();
        if (fellowDiscussionId == 0) {
            CommentThread commentThread = new CommentThread();
            commentThread.setAuthorId(proposal.getAuthorId());
            commentThread.setIsQuiet(true);
            commentThread.setTitle(proposal.getProposalId() + "_fellowReview");
            commentThread = ThreadClientUtil.createThread(commentThread);
            fellowDiscussionId =  commentThread.getThreadId();
            proposal.setFellowDiscussionId(fellowDiscussionId);
            ProposalClientUtil.updateProposal(proposal);

        }
        return fellowDiscussionId;
    }

    public long getAdvisorDiscussionId() {
        return proposal.getAdvisorDiscussionId();
    }

    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        proposal.setAdvisorDiscussionId(advisorDiscussionId);
    }

    public String getPitch() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.PITCH, "");
    }

    public String getName() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");
    }

    public String getDescription() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.DESCRIPTION, "");
    }

    public boolean isUserAmongFellows(Member memberInQuestion) {
        for (Long fellowId : ContestTeamMemberClientUtil.getFellowsForContest(contest.getContestPK())) {
            if (fellowId == memberInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserAmongJudges(Member userInQuestion) {
        for (Long judge : ContestTeamMemberClientUtil.getJudgesForContest(contest.getContestPK())) {
            if (judge == userInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isJudgingContestPhase() {
        return ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote())
                == ContestPhasePromoteType.PROMOTE_JUDGED;
    }

    public String getTeam() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.TEAM, "");
    }

    public Member getAuthor() {
        try {
            return MembersClient.getMember(proposal.getAuthorId());
        } catch (MemberNotFoundException e) {
            throw new IllegalStateException("Author " + proposal.getAuthorId()
                    + " of proposal " + proposal.getProposalId() + " does not exist");
        }
    }

    public long getSupportersCount() {
        return ProposalMemberRatingClientUtil.getProposalSupportersCount(proposal.getProposalId());
    }

    public long getCommentsCount() {
        if (proposal.getProposalId() > 0) {
            return CommentClientUtil.countComments(proposal.getDiscussionId());
        }
        return 0;
    }

    public long getFellowReviewCommentsCount() {
        if (proposal.getProposalId() > 0) {
            return CommentClientUtil.countComments(proposal.getFellowDiscussionId());
        }
        return 0;
    }

    public long getEvaluationCommentsCount() {
        return 0;
    }

    public Date getLastModifiedDate() {
        return proposal.getUpdatedDate();
    }

    public Date getLastModifiedDateForContestPhase() {
        if (proposal2Phase.getVersionTo() == -1) {
            return getLastModifiedDate();
        }
        return ProposalClientUtil
                .getProposalVersionByProposalIdVersion(proposal.getProposalId(), version).getCreateDate();

    }

    public boolean isOpen() {
        return proposal.getProposalId() > 0 && proposal.isOpen();
    }

    public long getImageId() {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }

    public String getProposalUrl() {
        return proposal.getProposalLinkUrl(contest);
    }

    public String getProposalUrl(ContestPhase inPhase) {
        return proposal.getProposalLinkUrl(contest, inPhase.getContestPhasePK());
    }

    public List<Member> getSupporters() {
        List<Member> supporters = new ArrayList<>();
        for (ProposalSupporter ps : ProposalMemberRatingClientUtil.getProposalSupporters(proposal.getProposalId())) {
            try {
                supporters.add(MembersClient.getMember(ps.getUserId()));
            } catch (MemberNotFoundException ignored) {

            }
        }
        return supporters;
    }


    public String getAuthorName() {
        String authorName = getTeam();
        if (StringUtils.isBlank(authorName)) {
            authorName = getAuthor().getScreenName();
        }
        return authorName;
    }

    public Contest getContest() {
        return contest;
    }

    public boolean getIsLatestVersion() {
        return getCurrentVersion() == version;
    }

    public BaseContestWrapper getWasMovedToContest() {
        try {
            Contest c = ProposalClientUtil.getCurrentContestForProposal(proposal.getProposalId());
            if (c.getContestPK() != contest.getContestPK().longValue()) {
                try {
                    return new BaseContestWrapper(ContestClientUtil.getContest(c.getContestPK()));
                } catch (ContestNotFoundException ignored) {
                }

            }
            return null;
        } catch (ContestNotFoundException e) {
            return null;
        }
    }

    public ProposalVersion getSelectedVersion() {
        for (ProposalVersion pv : ProposalClientUtil.getAllProposalVersions(proposal.getProposalId())) {
            if (pv.getVersion() == version) {
                return pv;
            }
        }
        return null;
    }

    public ProposalAttributeHelper getProposalAttributeHelper() {
        return proposalAttributeHelper;
    }

    public int getVersion() {
        return version;
    }

    public long getContestPK() {
        return contest.getContestPK();
    }

    public Proposal getWrapped() {
        return proposal;
    }

    public boolean isVisible() {
        return !proposal.isDeleted();
    }

    public boolean isVisibleInContest(long contestId) {
        return !proposal.isVisibleInContest(contestId);
    }

    public ContestPhase getContestPhase() {

        return ContestClientUtil.getContestPhase(contestPhase.getContestPhasePK());
    }

    public List<BaseProposalTeamMemberWrapper> getMembers() {
        if (members == null) {
            members = new ArrayList<>();
            boolean hasOwner = false;
            for (UsersGroups user : UsersGroupsClient.getUserGroupsByUserIdGroupId(null, proposal.getGroupId())) {

                try {
                    Member member = MembersClient.getMember(user.getUserId());

                    final BaseProposalTeamMemberWrapper teamMemberWrapper = new BaseProposalTeamMemberWrapper(
                            proposal, member);
                    members.add(teamMemberWrapper);
                    if (teamMemberWrapper.getMemberType().equalsIgnoreCase("owner")) {
                        hasOwner = true;
                    }
                } catch (MemberNotFoundException ignored) {

                }
            }
            if (!hasOwner) {
                //TODO: remove debug email
                UsersGroups usersGroups = new UsersGroups();
                usersGroups.setGroupId(proposal.getGroupId());
                usersGroups.setUserId(proposal.getAuthorId());

                try {
                    Member owner = MembersClient.getMember(usersGroups.getUserId());
                    members.add(new BaseProposalTeamMemberWrapper(proposal, owner));
                    new EmailToAdminDispatcher(String.format("Owner %s not in proposal %d's group",
                            owner.getScreenName(), proposal.getProposalId()),
                            String.format(
                                    "The owner %s (%d) of proposal %d is not in its group %d and was just re-added.",
                                    owner.getScreenName(), owner.getUserId(),
                                    proposal.getProposalId(), proposal.getGroupId())
                    ).sendMessage();
                } catch (MemberNotFoundException ignored) {

                }
            }

        }

        return members;
    }
}

