package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.*;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.*;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import edu.mit.cci.roma.client.Scenario;
import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.proposals.utils.GenericJudgingStatus;
import org.xcolab.portlets.proposals.utils.ProposalAttributeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProposalWrapper {

    protected static final Long LONG_DEFAULT_VAL = -1L;
    protected static final String STRING_DEFAULT_VAL = "";

    private final Proposal proposal;
    private final int version;
    private final Contest contest;

    private final ContestPhase contestPhase;
    private final Proposal2Phase proposal2Phase;
    protected ProposalRatingsWrapper proposalRatings;
    private RibbonWrapper ribbonWrapper;
    private List<ProposalTeamMemberWrapper> members;
    private List<ProposalSectionWrapper> sections;
    private List<ProposalContestPhaseAttribute> contestPhaseAttributes;
    private ProposalAttributeUtil proposalAttributeUtil;

    public ProposalWrapper(Proposal proposal) {
        this(proposal, proposal.getCurrentVersion());
    }

    public ProposalWrapper(Proposal proposal, int version) {
        this(proposal, version, null, null, null);
    }

    public ProposalWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        this.proposal = proposal;
        this.version = version;
        this.contest = contest == null ? fetchContest() : contest;
        this.contestPhase = contestPhase;
        this.proposal2Phase = proposal2Phase;
        this.ribbonWrapper = new RibbonWrapper(this);

        proposalAttributeUtil = new ProposalAttributeUtil(proposal, version);
        initializeContestPhaseAttributes();
    }

    public ProposalWrapper(Proposal proposal, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, proposal2Phase);
    }

    public ProposalWrapper(Proposal proposal, ContestPhase contestPhase) throws SystemException, PortalException {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK()));
    }

    public ProposalWrapper(ProposalWrapper proposalWrapper) {
        this(proposalWrapper.getWrapped(),
                proposalWrapper.getCurrentVersion(),
                proposalWrapper.getContest(),
                proposalWrapper.contestPhase,
                proposalWrapper.proposal2Phase);
    }

    public long getProposalId() {
        return proposal.getProposalId();
    }

    public int getCurrentVersion() {
        return proposal.getCurrentVersion();
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

    public long getDiscussionId() {
        return proposal.getDiscussionId();
    }

    public void setDiscussionId(long discussionId) {
        proposal.setDiscussionId(discussionId);
    }

    public long getFellowDiscussionId() throws PortalException, SystemException {
        final long fellowDiscussionId = proposal.getFellowDiscussionId();
        if (fellowDiscussionId == 0) {
            DiscussionCategoryGroup discussionCategoryGroup = DiscussionCategoryGroupLocalServiceUtil.createDiscussionCategoryGroup(proposal.getProposalId() + "_fellowReview");
            return discussionCategoryGroup.getId();
        }
        return fellowDiscussionId;

    }

    public String getPitch() throws PortalException, SystemException {
        return proposalAttributeUtil.getAttributeValueString(ProposalAttributeKeys.PITCH, "");
    }


    public String getName() throws PortalException, SystemException {
        return proposalAttributeUtil.getAttributeValueString(ProposalAttributeKeys.NAME, "");
    }

    public String getDescription() throws PortalException, SystemException {
        return proposalAttributeUtil.getAttributeValueString(ProposalAttributeKeys.DESCRIPTION, "");
    }

    public boolean isFeatured() throws PortalException, SystemException {
        // contest hideRibbons property overrides getRibbon by proposal
        return getRibbonWrapper().getRibbon() > 0 && !contest.getHideRibbons();
    }

    public JudgingSystemActions.AdvanceDecision getJudgeDecision() throws SystemException, PortalException {
        long judgingDecision = getContestPhaseAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.AdvanceDecision.fromInt((int) judgingDecision);
    }

    public JudgingSystemActions.FellowAction getFellowAction() throws SystemException, PortalException {
        Long action = getContestPhaseAttributeLongValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.FellowAction.fromInt(action.intValue());
    }

    public String getFellowActionComment() throws SystemException, PortalException {
        return getContestPhaseAttributeStringValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, 0, STRING_DEFAULT_VAL);
    }


    public String getProposalReview() throws SystemException, PortalException {
        return getContestPhaseAttributeStringValue(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, 0, STRING_DEFAULT_VAL);
    }

    public List<Long> getSelectedJudges() throws SystemException, PortalException {
        List<Long> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
                selectedJudges.add(judge.getUserId());
            }
        }
        else {
            String s;
            try {
                s = getContestPhaseAttributeStringValue(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0, STRING_DEFAULT_VAL);
            } catch (Exception e) {
                return selectedJudges;
            }
            if (s == null || s.length() == 0) return selectedJudges;
            for (String element : s.split(";")) selectedJudges.add(Long.parseLong(element));
        }

        return selectedJudges;
    }

    public boolean isUserAmongSelectedJudge(User user) throws PortalException, SystemException {
        if (!getFellowScreeningNeccessary()) {
            return isUserAmongJudges(user);
        }

        for (Long userId : getSelectedJudges()) {
            if (userId == user.getUserId()) {
                return true;
            }
        }
        return false;
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
        return proposalAttributeUtil.getAttributeValueString(ProposalAttributeKeys.TEAM, "");
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

    public boolean isOpen() throws PortalException, SystemException {
        return proposal.getProposalId() > 0 && ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
    }

    public long getVotesCount() throws SystemException, PortalException {
        if (proposal.getProposalId() > 0) {
            long votingPhasePK = new ContestWrapper(contest).getVotingPhasePK();
            return ProposalLocalServiceUtil.getVotesCount(proposal.getProposalId(), votingPhasePK);
        }
        return 0;
    }

    public long getImageId() throws PortalException, SystemException {
        return proposalAttributeUtil.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }

    public String getProposalURL() {
        return String.format("/web/guest/plans/-/plans/contestId/%s/planId/%s", contest.getContestPK(), proposal.getProposalId());
    }

    public List<ProposalSectionWrapper> getSections() throws PortalException, SystemException {
        if (sections == null) {
            sections = new ArrayList<>();
            if (contest != null) {
                PlanTemplate planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);
                if (planTemplate != null) {
                    for (PlanSectionDefinition psd : PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
                        sections.add(new ProposalSectionWrapper(psd, proposal, version, this));
                    }
                }
            }
        }
        return sections;
    }

    public Contest fetchContest() {
        try {
            return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProposalTeamMemberWrapper> getMembers() throws PortalException, SystemException {
        if (members == null) {
            members = new ArrayList<>();
            for (User user : ProposalLocalServiceUtil.getMembers(proposal.getProposalId())) {
                members.add(new ProposalTeamMemberWrapper(proposal, user));
            }
        }
        return members;
    }

    protected boolean getFellowScreeningNeccessary() {
        return contestPhase.getFellowScreeningActive();
    }

    protected String getContestPhaseAttributeStringValue(String attributeName, long additionalId, String defaultVal) throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = getContestPhaseAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    protected long getContestPhaseAttributeLongValue(String attributeName, long additionalId, long defaultVal) throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = getContestPhaseAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    private void initializeContestPhaseAttributes() {
        if (Validator.isNull(contestPhase)) {
            return;
        }
        try {
            List<ProposalContestPhaseAttribute> attributes = ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseProposalAttributes(contestPhase.getContestPhasePK(), proposal.getProposalId());
            setContestPhaseAttributes(attributes);
        } catch (NoSuchProposalContestPhaseAttributeException | SystemException e) {
            e.printStackTrace();
        }
    }

    private ProposalContestPhaseAttribute getContestPhaseAttributeOrNull(String attributeName, long additionalId) throws PortalException, SystemException {
        try {
        	if (contestPhaseAttributes == null) {
        		contestPhaseAttributes = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(proposal.getProposalId(), contestPhase.getContestPhasePK());
        	}
        	for (ProposalContestPhaseAttribute attr: contestPhaseAttributes) {
        		if (attr.getName().equals(attributeName) && attr.getAdditionalId() == additionalId) return attr;
        	}
        } catch (Exception ignored) {
        }
        return null;
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

    public Long getModelId() throws PortalException, SystemException {
        Long modelId = 0L;
        try {
            modelId = ContestLocalServiceUtil.getDefaultModelId(contest.getContestPK());
        } catch(Exception ignored){
        }
        return modelId;
    }

    public void setScenarioId(Long scenarioId, Long modelId, Long userId) throws PortalException, SystemException {
        ProposalLocalServiceUtil.setAttribute(userId, proposal.getProposalId(), ProposalAttributeKeys.SCENARIO_ID, modelId, scenarioId);
    }

    public Long getScenarioId() throws PortalException, SystemException {
        ProposalAttribute attr = proposalAttributeUtil.getLatestAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
	    if (attr == null) {
            return 0L;
        }
        return attr.getNumericValue();
    }

    public Scenario getScenario() throws IOException, SystemException{
        return getScenarioByProposalId(proposal.getProposalId());
    }

    public Scenario getScenarioByProposalId(Long proposalId) throws IOException, SystemException{
        return CollaboratoriumModelingService.repository().getScenario(proposalId);
    }

    /**
     * Determine if fellows are done with proposal
     */
    public GenericJudgingStatus getScreeningStatus() {
        try {
            switch (getFellowAction()) {
                case INCOMPLETE: case OFFTOPIC: case NOT_ADVANCE_OTHER:
                    return GenericJudgingStatus.STATUS_REJECTED;
                case PASS_TO_JUDGES:
                    return GenericJudgingStatus.STATUS_ACCEPTED;
                default:
                    return GenericJudgingStatus.STATUS_UNKNOWN;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  GenericJudgingStatus.STATUS_UNKNOWN;
    }

    /**
     * Determine if judges are done with proposal
     *
     */
    public GenericJudgingStatus getJudgeStatus() {
        try {
            if (getFellowAction() == JudgingSystemActions.FellowAction.INCOMPLETE || getFellowAction() == JudgingSystemActions.FellowAction.OFFTOPIC || getFellowAction() == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER)
                return GenericJudgingStatus.STATUS_REJECTED;
            if (getSelectedJudges().size() > 0 && getAllJudgesReviewFinished()) {
                return GenericJudgingStatus.STATUS_ACCEPTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GenericJudgingStatus.STATUS_UNKNOWN;
    }


    /**
     * Determines whether the screening/advance decision of the proposal is done
     */
    public GenericJudgingStatus getOverallStatus() {
        try {
            if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON && Validator.isNotNull(getProposalReview())) {
                return GenericJudgingStatus.STATUS_ACCEPTED;
            } else if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON && Validator.isNotNull(getProposalReview()) ||
                    getScreeningStatus() == GenericJudgingStatus.STATUS_REJECTED) {
                return GenericJudgingStatus.STATUS_REJECTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return GenericJudgingStatus.STATUS_UNKNOWN;
    }

    public boolean getAllJudgesReviewFinished() throws SystemException, PortalException {
        if (getSelectedJudges().size() > 0) {
            for (long userId : getSelectedJudges()) {
                List<ProposalRating> proposalRatings = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposalAndUser(
                        userId,
                        this.proposal.getProposalId(),
                        this.contestPhase.getContestPhasePK());
                ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(userId, proposalRatings);
                if (!wrapper.isReviewComplete()) {
                    return false;
                }

            }
        }
        return true;
    }

    public ProposalAttributeUtil getProposalAttributeUtil() {
        return proposalAttributeUtil;
    }

	public void setContestPhaseAttributes(List<ProposalContestPhaseAttribute> contestPhaseAttributes) {
		this.contestPhaseAttributes = contestPhaseAttributes;
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

    protected ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(long proposalId, long contestPhaseId, String attributeName, long additionalId) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            try {
                ProposalContestPhaseAttribute attribute = ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                        CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
                attribute.setProposalId(proposalId);
                attribute.setContestPhaseId(contestPhaseId);
                attribute.setName(attributeName);
                ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute(attribute);
                return attribute;
            } catch (Exception e2) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProposalRatingWrapper> getRatings() throws SystemException, PortalException {
        if (this.proposalRatings == null) {
            return new ArrayList<>();
        }
        return this.proposalRatings.getRatings();
    }

    public String getRatingComment() throws SystemException, PortalException {
        if (this.proposalRatings == null) {
            return "";
        }
        return this.proposalRatings.getComment();
    }

    public ContestPhase getContestPhase() {
        return contestPhase;
    }

    public RibbonWrapper getRibbonWrapper() {
        return ribbonWrapper;
    }
}
