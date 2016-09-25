package org.xcolab.wrappers;

import com.ext.portlet.NoSuchProposalVersionException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.helpers.ProposalContestPhaseAttributeHelper;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A wrapper class for proposals to be shared across portlets.
 * More specific wrappers should inherit from this one.
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
                return ContestLocalServiceUtil.fetchContest(contestPhase.getContestPK());
            }
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
        } catch (PortalException e) {
            throw new IllegalStateException("Could not find a contest for proposal "
                    + proposal.getProposalId(), e);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private ContestPhase fetchContestPhase() {
        try {
            if (proposal2Phase != null) {
                return ContestPhaseLocalServiceUtil.fetchContestPhase(proposal2Phase.getContestPhaseId());
            }
            if (contest != null) {
                return ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error(String.format("Could not fetch active contest phase for contest %d", contest.getContestPK()), e);
        }
        _log.error(String.format("Could not get contest phase for proposal %d", proposal.getProposalId()));
        return null;
    }

    private Proposal2Phase fetchProposal2Phase() {
        if (proposal.getProposalId() == 0 || contestPhase == null || contestPhase.getContestPhasePK() == 0) {
            return null;
        }
        try {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
        } catch (PortalException e) {
            _log.warn(String.format("Could not fetch p2p for proposal %d, contest phase %d",
                    proposal.getProposalId(), contestPhase.getContestPhasePK()));
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
        return null;
    }

    public long getProposalId() {
        return proposal.getProposalId();
    }

    public Date getCreateDate() {
        return proposal.getCreateDate();
    }

    public void setCreateDate(Date createDate) {
        proposal.setCreateDate(createDate);
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
        return proposal.isVisible() && (visibleInPhase == null || visibleInPhase.getNumericValue() > 0);
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
            CommentThread commentThread =  new CommentThread();
            commentThread.setAuthorId(proposal.getAuthorId());
            commentThread.setIsQuiet(true);
            commentThread.setTitle(proposal.getProposalId() + "_fellowReview");
            commentThread = ThreadClientUtil.createThread(commentThread);
            fellowDiscussionId =  commentThread.getThreadId();
            proposal.setFellowDiscussionId(fellowDiscussionId);
            try {
                ProposalLocalServiceUtil.updateProposal(proposal);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            }
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

    public boolean isUserAmongFellows(User userInQuestion) {
        try {
            for (User fellow : ContestLocalServiceUtil.getFellowsForContest(contest)) {
                if (fellow.getUserId() == userInQuestion.getUserId()) {
                    return true;
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new InternalException("Could not get contest team members for contest "
                    + contest.getContestPK(), e);
        }
        return false;
    }

    public boolean isUserAmongJudges(Member userInQuestion) {
        try {
            for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
                if (judge.getUserId() == userInQuestion.getUserId()) {
                    return true;
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new InternalException("Could not get contest team members for contest "
                    + contest.getContestPK(), e);
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
        try {
            return ProposalLocalServiceUtil.getSupportersCount(proposal.getProposalId());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            //should never happen
            throw new IllegalArgumentException("Proposal " + proposal.getProposalId()
                    + " does not exist");
        }
    }

    public long getCommentsCount() {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.getCommentsCount(proposal.getProposalId());
        }
        return 0;
    }

    public long getFellowReviewCommentsCount() {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.getFellowReviewCommentsCount(proposal.getProposalId());
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
        try {
            return ProposalVersionLocalServiceUtil.getByProposalIdVersion(proposal.getProposalId(), version).getCreateDate();
        } catch (NoSuchProposalVersionException e) {
            throw new IllegalStateException("No version " + version
                    + " for proposal " + proposal.getProposalId());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public boolean isOpen() {
        try {
            return proposal.getProposalId() > 0 && ProposalLocalServiceUtil
                    .isOpen(proposal.getProposalId());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.error("Failed to check if proposal is open: " + proposal.getProposalId());
            return false;
        }
    }

    public long getImageId() {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }

    public String getProposalUrl() {
        return ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal);
    }

    public String getProposalUrl(org.xcolab.client.contest.pojo.ContestPhase inPhase) {
        try {
            ContestPhase liferayCP = ContestPhaseLocalServiceUtil.fetchContestPhase(inPhase.getContestPhasePK());
            return ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal, liferayCP);
        }catch (SystemException ignored){

        }
        return null;
    }

    public List<User> getSupporters() {
        try {
            return ProposalLocalServiceUtil.getSupporters(proposal.getProposalId());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            //shouldn't happen
            _log.error("Failed to get supports for proposal " + proposal.getProposalId());
            return Collections.emptyList();
        }
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
        try{
            Contest c = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            if (c.getContestPK() != contest.getContestPK()) {
                try{
                    return new BaseContestWrapper(ContestClient.getContest(c.getContestPK()));
                }catch (ContestNotFoundException ignored){
                }

            }
            return null;
        } catch (PortalException e) {
            return null;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public ProposalVersion getSelectedVersion() {
        try {
            for (ProposalVersion pv : ProposalVersionLocalServiceUtil.getByProposalId(proposal.getProposalId(), 0, Integer.MAX_VALUE)) {
                if (pv.getVersion() == version) {
                    return pv;
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
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
        try {
            return !ProposalLocalServiceUtil.isDeleted(proposal);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            return true;
        }
    }

    public boolean isVisibleInContest(long contestId) {
        try {
            return !ProposalLocalServiceUtil.isVisibleInContest(proposal, contestId);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException ignored) {
            return true;
        }
    }

    public org.xcolab.client.contest.pojo.ContestPhase getContestPhase() {

        return ContestClient.getContestPhase(contestPhase.getContestPhasePK());
    }

    public List<BaseProposalTeamMemberWrapper> getMembers() {
        if (members == null) {
            try {
                members = new ArrayList<>();
                boolean hasOwner = false;
                for (User user : ProposalLocalServiceUtil.getMembers(proposal.getProposalId())) {
                    final BaseProposalTeamMemberWrapper teamMemberWrapper = new BaseProposalTeamMemberWrapper(
                            proposal, user);
                    members.add(teamMemberWrapper);
                    if (teamMemberWrapper.getMemberType().equalsIgnoreCase("owner")) {
                        hasOwner = true;
                    }
                }
                if (!hasOwner) {
                    //TODO: remove debug email
                    GroupLocalServiceUtil.addUserGroups(proposal.getAuthorId(),
                            new long[]{proposal.getGroupId()});
                    final User owner = UserLocalServiceUtil.fetchUser(proposal.getAuthorId());
                    members.add(new BaseProposalTeamMemberWrapper(proposal, owner));
                    new EmailToAdminDispatcher(String.format("Owner %s not in proposal %d's group",
                            owner.getScreenName(), proposal.getProposalId()),
                            String.format(
                                    "The owner %s (%d) of proposal %d is not in its group %d and was just re-added.",
                                    owner.getScreenName(), owner.getUserId(),
                                    proposal.getProposalId(), proposal.getGroupId())
                    ).sendMessage();
                }
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            } catch (PortalException ignored) {
               //can't happen, we know the proposal exists
            }
        }

        return members;
    }
}

