package org.xcolab.wrappers;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringUtils;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.helpers.ProposalContestPhaseAttributeHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by johannes on 10/27/15.
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

    public BaseProposalWrapper(Proposal proposal) {
        this(proposal, proposal.getCurrentVersion(), null, null, null);
    }

    public BaseProposalWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        this.proposal = proposal;
        this.version = version;
        this.contest = contest == null ? fetchContest() : contest;
        this.contestPhase = contestPhase == null ? fetchContestPhase() : contestPhase;
        this.proposal2Phase = proposal2Phase == null ? fetchProposal2Phase() : proposal2Phase;

        proposalContestPhaseAttributeHelper = new ProposalContestPhaseAttributeHelper(proposal, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(proposal, version);
    }

    public BaseProposalWrapper(Proposal proposal, ContestPhase contestPhase) throws SystemException, PortalException {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, null);
    }

    private Contest fetchContest() {
        try {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
        } catch (PortalException | SystemException e) {
            return null;
        }
    }

    private ContestPhase fetchContestPhase() {
        try {
            return ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
        } catch (SystemException | PortalException e) {
            _log.warn(String.format("Could not fetch active contest phase for contest %d", contest.getContestPK()));
        }
        return null;
    }

    private Proposal2Phase fetchProposal2Phase() {
        if (proposal.getProposalId() == 0 || contestPhase.getContestPhasePK() == 0) {
            return null;
        }
        try {
            return Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
        } catch (PortalException | SystemException e) {
            _log.warn(String.format("Could not fetch p2p for proposal %d, contest phase %d",
                    proposal.getProposalId(), contestPhase.getContestPhasePK()));
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

    public boolean isVisibleInPhase() throws PortalException, SystemException {
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

    public long getFellowDiscussionId() throws PortalException, SystemException {
        final long fellowDiscussionId = proposal.getFellowDiscussionId();
        if (fellowDiscussionId == 0) {
            DiscussionCategoryGroup discussionCategoryGroup = DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup(proposal.getProposalId() + "_fellowReview");
            return discussionCategoryGroup.getId();
        }
        return fellowDiscussionId;
    }

    public long getAdvisorDiscussionId() {
        return proposal.getAdvisorDiscussionId();
    }

    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        proposal.setAdvisorDiscussionId(advisorDiscussionId);
    }

    public String getPitch() throws PortalException, SystemException {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.PITCH, "");
    }

    public String getName() throws PortalException, SystemException {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");
    }

    public String getDescription() throws SystemException, PortalException {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.DESCRIPTION, "");
    }

    public boolean isUserAmongFellows(User userInQuestion) throws SystemException, PortalException {
        for (User fellow : ContestLocalServiceUtil.getFellowsForContest(contest)) {
            if (fellow.getUserId() == userInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserAmongJudges(User userInQuestion) throws SystemException, PortalException {
        for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
            if (judge.getUserId() == userInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isJudgingContestPhase() {
        return ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote()) == ContestPhasePromoteType.PROMOTE_JUDGED;
    }

    public String getTeam() throws PortalException, SystemException {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.TEAM, "");
    }

    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(proposal.getAuthorId());
    }

    public long getSupportersCount() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getSupportersCount(proposal.getProposalId());
    }

    public long getCommentsCount() throws PortalException, SystemException {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.getCommentsCount(proposal.getProposalId());
        }
        return 0;
    }

    public long getFellowReviewCommentsCount() throws PortalException, SystemException {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.getFellowReviewCommentsCount(proposal.getProposalId());
        }
        return 0;
    }

    public long getEvaluationCommentsCount() throws PortalException, SystemException {
        return 0;
    }

    public Date getLastModifiedDate() {
        return proposal.getUpdatedDate();
    }

    public Date getLastModifiedDateForContestPhase() throws PortalException, SystemException {
        if (proposal2Phase.getVersionTo() == -1) {
            return getLastModifiedDate();
        }
        return ProposalVersionLocalServiceUtil.getByProposalIdVersion(proposal.getProposalId(), version).getCreateDate();
    }

    public boolean isOpen() throws PortalException, SystemException {
        return proposal.getProposalId() > 0 && ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
    }

    public long getImageId() throws PortalException, SystemException {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }

    public String getProposalURL() {
        return ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal);
    }

    public String getProposalURL(ContestPhase inPhase) {
        return ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal, inPhase);
    }

    public List<User> getSupporters() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getSupporters(proposal.getProposalId());
    }

    public String getAuthorName() throws PortalException, SystemException {
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

    public Contest getWasMovedToContest() {
        try{
            Contest c = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            if (c.getContestPK() != contest.getContestPK()) {
                return c;
            }
            return null;
        } catch (PortalException e) { return null; }
        catch (SystemException e) { return null; }
    }

    public ProposalVersion getSelectedVersion() {
        try {
            for (ProposalVersion pv : ProposalVersionLocalServiceUtil.getByProposalId(proposal.getProposalId(), 0, Integer.MAX_VALUE)) {
                if (pv.getVersion() == version) {
                    return pv;
                }
            }
        } catch (SystemException e) {
            return null;
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
        } catch (PortalException | SystemException ignored) { }
        return true;
    }

    public boolean isVisibleInContest(long contestId) {
        try {
            return !ProposalLocalServiceUtil.isVisibleInContest(proposal, contestId);
        } catch (PortalException | SystemException ignored) { }
        return true;
    }

    public ContestPhase getContestPhase() {
        return contestPhase;
    }
}

