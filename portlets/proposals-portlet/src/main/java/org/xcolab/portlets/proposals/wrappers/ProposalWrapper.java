package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import org.xcolab.enums.ModelRegions;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.portlets.proposals.utils.GenericJudgingStatus;
import org.xcolab.wrappers.BaseProposalWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalWrapper extends BaseProposalWrapper {

    private static final Log _log = LogFactoryUtil.getLog(ProposalWrapper.class);

    protected static final Long LONG_DEFAULT_VAL = -1L;
    protected static final String STRING_DEFAULT_VAL = "";

    protected ProposalRatingsWrapper proposalRatings;
    private RibbonWrapper ribbonWrapper;
    private ProposalWrapper baseProposal;
    private List<ProposalTeamMemberWrapper> members;
    private List<ProposalSectionWrapper> sections;
    private List<MembershipRequestWrapper> membershipRequests;

    public ProposalWrapper(Proposal proposal) {
        this(proposal, proposal.getCurrentVersion());
    }

    public ProposalWrapper(Proposal proposal, int version) {
        super(proposal, version, null, null, null);
    }

    public ProposalWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
    }

    public ProposalWrapper(Proposal proposal, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        super(proposal, proposal.getCurrentVersion(), null, contestPhase, proposal2Phase);
    }
    public ProposalWrapper(Proposal proposal, ContestPhase contestPhase) throws SystemException, PortalException {
        super(proposal, contestPhase);
    }

    public ProposalWrapper(ProposalWrapper proposalWrapper) {
        super(proposalWrapper.getWrapped(),
                proposalWrapper.getCurrentVersion(),
                proposalWrapper.getContest(),
                proposalWrapper.contestPhase,
                proposalWrapper.proposal2Phase);
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

    public boolean isFeatured() {
        return getRibbonWrapper().getRibbon() > 0;
    }

    public JudgingSystemActions.AdvanceDecision getJudgeDecision() {
        long judgingDecision = proposalContestPhaseAttributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.AdvanceDecision.fromInt((int) judgingDecision);
    }

    public JudgingSystemActions.FellowAction getFellowAction() {
        Long action = proposalContestPhaseAttributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.FellowAction.fromInt(action.intValue());
    }

    public String getFellowActionComment() {
        return proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, 0, STRING_DEFAULT_VAL);
    }

    public String getProposalReview() {
        return proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, 0, STRING_DEFAULT_VAL);
    }

    public List<Long> getSelectedJudges() throws SystemException, PortalException {
        List<Long> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
                selectedJudges.add(judge.getUserId());
            }
        } else {
            String s = proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0, STRING_DEFAULT_VAL);
            if (s.isEmpty()) {
                return selectedJudges;
            }
            for (String element : s.split(";")) {
                selectedJudges.add(Long.parseLong(element));
            }
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
        } else {
            String s = proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0, STRING_DEFAULT_VAL);
            if (s.isEmpty()) {
                return selectedJudges;
            }
            for (String element : s.split(";")) {
                selectedJudges.add(UserLocalServiceUtil.getUser(Long.parseLong(element)));
            }
        }
        return selectedJudges;
    }

    public boolean isUserAmongSelectedJudge(User user) throws PortalException, SystemException {
        if (!getFellowScreeningNecessary()) {
            return isUserAmongJudges(user);
        }

        for (Long userId : getSelectedJudges()) {
            if (userId == user.getUserId()) {
                return true;
            }
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

    public List<ProposalSectionWrapper> getSections() throws PortalException, SystemException {
        if (sections == null) {
            sections = new ArrayList<>();
            if (contest != null) {
                PlanTemplate planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);
                if (planTemplate != null) {
                    for (PlanSectionDefinition psd : PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
                        sections.add(new ProposalSectionWrapper(psd, this));
                    }
                }
            }
        }
        return sections;
    }

    public List<ProposalTeamMemberWrapper> getMembers() throws PortalException, SystemException {
        if (members == null) {
            members = new ArrayList<>();
            boolean hasOwner = false;
            for (User user : ProposalLocalServiceUtil.getMembers(proposal.getProposalId())) {
                final ProposalTeamMemberWrapper teamMemberWrapper = new ProposalTeamMemberWrapper(proposal, user);
                members.add(teamMemberWrapper);
                if (teamMemberWrapper.getMemberType().equalsIgnoreCase("owner")) {
                    hasOwner = true;
                }
            }
            if (!hasOwner) {
                //TODO: remove debug email
                GroupLocalServiceUtil.addUserGroups(proposal.getAuthorId(), new long[]{proposal.getGroupId()});
                final User owner = UserLocalServiceUtil.fetchUser(proposal.getAuthorId());
                members.add(new ProposalTeamMemberWrapper(proposal, owner));
                new EmailToAdminDispatcher(String.format("Owner %s not in proposal %d's group", owner.getScreenName(), proposal.getProposalId()),
                        String.format("The owner %s (%d) of proposal %d is not in its group %d and was just re-added.",
                                owner.getScreenName(), owner.getUserId(), proposal.getProposalId(), proposal.getGroupId())
                ).sendMessage();
            }
        }

        return members;
    }

    protected boolean getFellowScreeningNecessary() {
        return contestPhase.getFellowScreeningActive();
    }
    
    public List<MembershipRequestWrapper> getMembershipRequests() {
        if (this.membershipRequests == null) {
            membershipRequests = new ArrayList<>();
            try {
                for (MembershipRequest m : ProposalLocalServiceUtil.getMembershipRequests(proposal.getProposalId())) {
                    membershipRequests.add(new MembershipRequestWrapper(m));
                }
            } catch (SystemException | PortalException e) {
                _log.warn(String.format("Could not retrieve membership requests for proposal %d", proposal.getProposalId()));
            }
        }
        return this.membershipRequests;
    }

    public List<String[]> getAllModelRegions() {
        List<String[]> modelRegions = new ArrayList<>();
        for (ModelRegions modelRegion : ModelRegions.values()) {
            modelRegions.add(new String[] {modelRegion.getModelRegionName(), modelRegion.getModelRegionTitle()});
        }
        return modelRegions;
    }

    public String getModelRegion() throws PortalException, SystemException {
        ProposalAttribute attr = proposalAttributeHelper.getLatestAttributeOrNull(ProposalAttributeKeys.REGION);
        if (attr == null) {
            return "";
        }
        return attr.getStringValue();
    }

    public void setModelRegion(String region, Long userId) throws PortalException, SystemException {
        ProposalLocalServiceUtil.setAttribute(userId, proposal.getProposalId(), ProposalAttributeKeys.REGION, region);
    }

    public Long getModelId() throws PortalException, SystemException {
        Long modelId = 0L;
        try {
            modelId = ContestLocalServiceUtil.getDefaultModelId(contest.getContestPK());
        } catch (PortalException | SystemException ignored) { }
        return modelId;
    }

    public void setScenarioId(Long scenarioId, Long isConsolidatedScenario, Long userId) throws PortalException, SystemException {
        ProposalLocalServiceUtil.setAttribute(userId, proposal.getProposalId(), ProposalAttributeKeys.SCENARIO_ID, isConsolidatedScenario, scenarioId);
    }

    public Long getScenarioId() throws PortalException, SystemException {
        ProposalAttribute attr = proposalAttributeHelper.getLatestAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
	    if (attr == null) {
            return 0L;
        }
        return attr.getNumericValue();
    }

    public Boolean isConsolidatedScenario(Long scenarioId) throws PortalException, SystemException {
        ProposalAttribute attr = proposalAttributeHelper.getLatestAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        return attr != null && attr.getAdditionalId() == 1;
    }

    public Map<Long,List<ProposalWrapper>> getSubProposalPerModel() throws PortalException, SystemException {
        Map<Long,List<ProposalWrapper>> subProposalPerModel = new HashMap<>();
        List<Proposal> subProposals =  ProposalLocalServiceUtil.getContestIntegrationRelevantSubproposals(proposal.getProposalId());

        for(Proposal subProposal : subProposals){
            ProposalWrapper subProposalWrapper = new ProposalWrapper(subProposal);
            Long modelId = subProposalWrapper.getModelIdForStoredScenario();
            if(!subProposalPerModel.containsKey(modelId)){
                subProposalPerModel.put(modelId, Arrays.asList(subProposalWrapper));
            } else {
                subProposalPerModel.get(modelId).add(subProposalWrapper);
            }
        }
        return subProposalPerModel;
    }

    public Scenario getScenario() throws IOException, SystemException{
        return getScenarioByProposalId(proposal.getProposalId());
    }

    public Scenario getScenarioByProposalId(Long proposalId) throws IOException, SystemException{
        return CollaboratoriumModelingService.repository().getScenario(proposalId);
    }

    private static Long getModelIdForScenarioId(Long scenarioId) throws SystemException{
        Long modelId;

        try {
            Scenario scenario = CollaboratoriumModelingService.repository().getScenario(scenarioId);
            Simulation simulation = scenario.getSimulation();
            modelId = simulation.getId();
        }
        catch (IOException e) {
            modelId = 0L;
        }

        return modelId;
    }

    public List<Scenario> getSubProposalScenarios() throws SystemException, PortalException, IOException{
        List<Scenario> subProposalScenarios = new ArrayList<>();
        List<Proposal> subProposals =  ProposalLocalServiceUtil.getContestIntegrationRelevantSubproposals(proposal.getProposalId());
        for(Proposal subProposal : subProposals) {
            Scenario scenarioForSubProposal = getScenarioByProposalId(subProposal.getProposalId());
            subProposalScenarios.add(scenarioForSubProposal);
        }
        return subProposalScenarios;
    }

    private Long getModelIdForStoredScenario() throws SystemException{
        return getModelIdForScenarioId(proposal.getProposalId());
    }

    public List<Long> getSubProposalScenarioIds() throws PortalException, SystemException {
        List<Long> subProposalScenarioIds = new ArrayList<>();
        Map<Long,List<ProposalWrapper>> subProposalPerModel = getSubProposalPerModel();

        if(!subProposalPerModel.isEmpty()){
            for(Long modelId : subProposalPerModel.keySet()){
                List<ProposalWrapper> proposalWrappers = subProposalPerModel.get(modelId);
                for(ProposalWrapper proposalWrapper : proposalWrappers){
                    subProposalScenarioIds.add(proposalWrapper.getScenarioId());
                }
            }
        }

        return subProposalScenarioIds;
    }

    /**
     * Determine if fellows are done with proposal
     */
    public GenericJudgingStatus getScreeningStatus() {
        switch (getFellowAction()) {
            case INCOMPLETE: case OFFTOPIC: case NOT_ADVANCE_OTHER:
                return GenericJudgingStatus.STATUS_REJECTED;
            case PASS_TO_JUDGES:
                return GenericJudgingStatus.STATUS_ACCEPTED;
            default:
                return GenericJudgingStatus.STATUS_UNKNOWN;
        }
    }

    /**
     * Determine if judges are done with proposal
     *
     */
    public GenericJudgingStatus getJudgeStatus() {
        try {
            if (getFellowAction() == JudgingSystemActions.FellowAction.INCOMPLETE || getFellowAction() == JudgingSystemActions.FellowAction.OFFTOPIC || getFellowAction() == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER) {
                return GenericJudgingStatus.STATUS_REJECTED;
            }
            if (!getSelectedJudges().isEmpty() && getAllJudgesReviewFinished()) {
                return GenericJudgingStatus.STATUS_ACCEPTED;
            }
        } catch (SystemException | PortalException e) {
            e.printStackTrace();
        }
        return GenericJudgingStatus.STATUS_UNKNOWN;
    }

    /**
     * Determines whether the screening/advance decision of the proposal is done
     */
    public GenericJudgingStatus getOverallStatus() {
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON && Validator.isNotNull(getProposalReview())) {
            return GenericJudgingStatus.STATUS_ACCEPTED;
        }
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON && Validator.isNotNull(getProposalReview()) ||
                getScreeningStatus() == GenericJudgingStatus.STATUS_REJECTED) {
            return GenericJudgingStatus.STATUS_REJECTED;
        }

        return GenericJudgingStatus.STATUS_UNKNOWN;
    }

    public User getUserForSelectedVersion() {
        try {
            return UserLocalServiceUtil.getUser(getSelectedVersion().getAuthorId());
        } catch (SystemException | PortalException e) {
            return null;
        }
    }

    public boolean getAllJudgesReviewFinished() throws SystemException, PortalException {
        if (!getSelectedJudges().isEmpty()) {
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
        return wrapper.isReviewComplete();

    }
	
	public ProposalWrapper getBaseProposal() throws PortalException, SystemException {
		if (baseProposal == null) {
			long baseProposalId = proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_ID, 0);
			long baseProposalContestId = proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0);
			if (baseProposalId > 0 && baseProposalContestId > 0) {
				Proposal p = ProposalLocalServiceUtil.getProposal(baseProposalId);
				baseProposal = new ProposalWrapper(p);
			}
		}
		return baseProposal;
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

    public RibbonWrapper getRibbonWrapper() {
        if (ribbonWrapper == null) {
            ribbonWrapper = new RibbonWrapper(this);
        }
        return ribbonWrapper;
    }
}
