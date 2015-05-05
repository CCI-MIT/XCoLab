package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.proposals.utils.ProposalAttributeUtil;

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
    private ContestPhaseRibbonType contestPhaseRibbonType;
    private ProposalWrapper baseProposal;
    private List<ProposalTeamMemberWrapper> members;
    private List<ProposalSectionWrapper> sections;
    private List<MembershipRequestWrapper> membershipRequests;
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

    public Class<?> getModelClass() {
        return proposal.getModelClass();
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
    	ProposalContestPhaseAttribute visibleInPhase = getContestPhaseAttributeOrNull(ProposalContestPhaseAttributeKeys.VISIBLE, 0);
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
        return proposal.getFellowDiscussionId();
    }

    public void setFellowDiscussionId(long fellowDiscussionId) {
        proposal.setFellowDiscussionId(fellowDiscussionId);
    }

    public long getAdvisorDiscussionId() {
        return proposal.getAdvisorDiscussionId();
    }

    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        proposal.setAdvisorDiscussionId(advisorDiscussionId);
    }

    public long getGroupId() {
        return proposal.getGroupId();
    }

    public void setGroupId(long groupId) {
        proposal.setGroupId(groupId);
    }

    public void setCachedModel(boolean cachedModel) {
        proposal.setCachedModel(cachedModel);
    }

    public ExpandoBridge getExpandoBridge() {
        return proposal.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        proposal.setExpandoBridgeAttributes(serviceContext);
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
        return getRibbon() > 0;
    }

    public int getRibbon() throws PortalException, SystemException {
        getRibbonType();
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getRibbon();
        }
        return 0;
    }
    public long getRibbonId() throws PortalException, SystemException {
        getRibbonType();
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getId();
        }
        return 0L;
    }

    public String getRibbonText() throws PortalException, SystemException {
        getRibbonType();
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getHoverText();
        }
        return null;
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
        List<Long> selectedJudges = new ArrayList<Long>();

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

    public List<User> getSelectedJudgeUsers() throws SystemException, PortalException {
        List<User> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
                selectedJudges.add(judge);
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
            for (String element : s.split(";")) selectedJudges.add(UserLocalServiceUtil.getUser(Long.parseLong(element)));
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

    public long getDiscussionCommentsCount() throws PortalException, SystemException {
        return 0;
    }

    public Date getLastModifiedDate() {
        return proposal.getUpdatedDate();
    }

    public Date getLastModifiedDateForContestPhase() throws PortalException, SystemException {
        if (proposal2Phase.getVersionTo() == -1) return getLastModifiedDate();
        return ProposalVersionLocalServiceUtil.getByProposalIdVersion(proposal.getProposalId(), version).getCreateDate();
    }

    public boolean isOpen() throws PortalException, SystemException {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
        }
        return false;
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
            sections = new ArrayList<ProposalSectionWrapper>();
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
            members = new ArrayList<ProposalTeamMemberWrapper>();
            for (User user : ProposalLocalServiceUtil.getMembers(proposal.getProposalId())) {
                members.add(new ProposalTeamMemberWrapper(proposal, user));
            }
        }
        return members;
    }

    public List<User> getSupporters() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getSupporters(proposal.getProposalId());
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
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    private double getContestPhaseAttributeValueReal(String attributeName, long additionalId, double defaultVal) throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = getContestPhaseAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }

    private ProposalContestPhaseAttribute getContestPhaseAttributeOrNull(String attributeName, long additionalId) throws PortalException, SystemException {
        try {
        	if (contestPhaseAttributes == null) {
        		contestPhaseAttributes = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(proposal.getProposalId(), contestPhase.getContestPhasePK());
        	}
        	for (ProposalContestPhaseAttribute attr: contestPhaseAttributes) {
        		if (attr.getName().equals(attributeName) && attr.getAdditionalId() == additionalId) return attr;
        	}
        } catch (Exception e) {
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

    private ContestPhaseRibbonType getRibbonType() throws PortalException, SystemException {
        if (contestPhaseRibbonType == null) {
        	long typeId = getContestPhaseAttributeLongValue(ProposalContestPhaseAttributeKeys.RIBBON, 0, -1);
        	if (typeId >= 0) {
        		contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
        	}
        }
        return contestPhaseRibbonType;
    }


    public List<MembershipRequestWrapper> getMembershipRequests() {
        if (this.membershipRequests == null) {
            // get all Membershiprequests
            membershipRequests = new ArrayList<MembershipRequestWrapper>();
            try {
                for (MembershipRequest m : ProposalLocalServiceUtil.getMembershipRequests(proposal.getProposalId())) {
                    membershipRequests.add(new MembershipRequestWrapper(m));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return this.membershipRequests;

    }

    public Long getModelId() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getDefaultModelId(contest.getContestPK());
    }
    
    

    public Long getScenarioId() throws PortalException, SystemException {
        ProposalAttribute attr = proposalAttributeUtil.getLatestAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
	if (attr == null) return 0L;
        return attr.getNumericValue();
    }

    /**
     * Determine if fellow are done with proposal
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
     * @return
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

    public boolean getIsLatestVersion() {
        try {
            return getCurrentVersion() == version;
        } catch (Exception e) {
            return false;
        }
    }

     /*
    public boolean getIsLatestVersionInContest() throws SystemException, PortalException {
        if (getWasMovedToContest() == null) return getIsLatestVersion();
         //Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(proposal.getProposalId());
        List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil.getByProposalId(proposal.getProposalId());
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(contest);

        long maxVersion = proposal.getCurrentVersion();
        for (Proposal2Phase p2p : proposal2Phases){
            if (contestPhases.)
            if (p2p.getVersionTo() <= proposal.getCurrentVersion()) continue;
            if ()
        }

        return false;
    }
    */
    public Contest getWasMovedToContest() {
        try{
            Contest c = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            if (c.getContestPK() != contest.getContestPK())
                return c;
            return null;
        } catch (PortalException e) { return null; }
        catch (SystemException e) { return null; }
    }

    public ProposalVersion getSelectedVersion() {
        try {
            for (ProposalVersion pv : ProposalVersionLocalServiceUtil.getByProposalId(proposal.getProposalId(), 0, Integer.MAX_VALUE)) {
                if (pv.getVersion() == version) return pv;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public User getUserForSelectedVersion() {
        try {
            return UserLocalServiceUtil.getUser(getSelectedVersion().getAuthorId());
        } catch (Exception e) {
            return null;
        }
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

    public boolean getJudgeReviewFinishedStatusUserId(Long userId) throws SystemException, PortalException {

        List<ProposalRating> proposalRatings = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposalAndUser(
                userId,
                this.proposal.getProposalId(),
                this.contestPhase.getContestPhasePK());
        ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(userId, proposalRatings);
        if (!wrapper.isReviewComplete()) {
            return false;
        }

        return true;
    }

    public ProposalAttributeUtil getProposalAttributeUtil() {
        return proposalAttributeUtil;
    }

	public List<ProposalContestPhaseAttribute> getContestPhaseAttributes() {
		return contestPhaseAttributes;
	}

	public void setContestPhaseAttributes(List<ProposalContestPhaseAttribute> contestPhaseAttributes) {
		this.contestPhaseAttributes = contestPhaseAttributes;
	}

	public int getVersion() {
		return version;
	}
	
	public ProposalWrapper getBaseProposal() throws PortalException, SystemException {
		if (baseProposal == null) {
			long baseProposalId = proposalAttributeUtil.getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_ID, 0);
			long baseProposalContestId = proposalAttributeUtil.getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0);
			if (baseProposalId > 0 && baseProposalContestId > 0) {
				Proposal p = ProposalLocalServiceUtil.getProposal(baseProposalId);
				Contest c = ContestLocalServiceUtil.getContest(baseProposalContestId);
				baseProposal = new ProposalWrapper(p, p.getCurrentVersion(), c, null, null);
			}
		}
		return baseProposal;
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
        if (this.proposalRatings != null) {
            return this.proposalRatings.getRatings();
        } else {
            return new ArrayList<ProposalRatingWrapper>();
        }
    }

    public String getRatingComment() throws SystemException, PortalException {
        if (this.proposalRatings != null) {
            return this.proposalRatings.getComment();
        } else {
            return "";
        }
    }

    public ContestPhase getContestPhase() {
        return contestPhase;
    }

    public enum GenericJudgingStatus {
        STATUS_UNKNOWN(0),
        STATUS_REJECTED(1),
        STATUS_ACCEPTED(2);

        private int statusValue;

        GenericJudgingStatus(int statusValue) {
            this.statusValue = statusValue;
        }

        public int getStatusValue() {
            return statusValue;
        }
    }
}
