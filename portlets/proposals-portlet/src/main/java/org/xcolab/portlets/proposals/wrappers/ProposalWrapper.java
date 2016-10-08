package org.xcolab.portlets.proposals.wrappers;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;

import com.ext.portlet.JudgingSystemActions;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.PlanTemplateClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.PlanTemplate;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.MembershipRequestClient;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalRatingClient;
import org.xcolab.client.proposals.ProposalVoteClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.MembershipRequest;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.client.proposals.pojo.ProposalAttribute;
import org.xcolab.client.proposals.pojo.ProposalRating;
import org.xcolab.enums.MembershipRequestStatus;
import org.xcolab.enums.ModelRegions;
import org.xcolab.portlets.proposals.utils.GenericJudgingStatus;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.wrappers.BaseProposalWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalWrapper extends BaseProposalWrapper {

    private static final Log _log = LogFactoryUtil.getLog(ProposalWrapper.class);

    private static final Long LONG_DEFAULT_VAL = -1L;
    private static final String STRING_DEFAULT_VAL = "";

    protected ProposalRatingsWrapper proposalRatings;
    private RibbonWrapper ribbonWrapper;
    private ProposalWrapper baseProposal;
    private List<ProposalSectionWrapper> sections;
    private List<MembershipRequestWrapper> membershipRequests;

    public ProposalWrapper(long proposalId) throws ProposalNotFoundException {
        this(ProposalsClient.getProposal(proposalId));
    }

    public ProposalWrapper(Proposal proposal) {
        this(proposal, proposal.getCurrentVersion());
    }

    public ProposalWrapper(Proposal proposal, int version) {
        super(proposal, version, null, null, null);
    }

    public ProposalWrapper(Proposal proposal, int version, Contest contest,
                           ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
    }

    public ProposalWrapper(Proposal proposal, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        super(proposal, proposal.getCurrentVersion(), null, contestPhase, proposal2Phase);
    }

    public ProposalWrapper(Proposal proposal, ContestPhase contestPhase) {
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

//    public void setCachedModel(boolean cachedModel) {
//        proposal.setCachedModel(cachedModel);
//    }
//
//    public ExpandoBridge getExpandoBridge() {
//        return proposal.getExpandoBridge();
//    }
//
//    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
//        proposal.setExpandoBridgeAttributes(serviceContext);
//    }

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

    public List<Long> getSelectedJudges() {
        List<Long> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (Long judge : ContestTeamMemberClient.getJudgesForContest(contest.getContestPK())) {

                selectedJudges.add(judge);
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

    public List<Member> getSelectedJudgeUsers() throws SystemException, PortalException {
        List<Member> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (Long judgeId : ContestTeamMemberClient.getJudgesForContest(contest.getContestPK())) {

                try {
                    Member judge = MembersClient.getMember(judgeId);
                    selectedJudges.add(judge);
                } catch (MemberNotFoundException ignored) {

                }
            }
        } else {
            String s = proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0, STRING_DEFAULT_VAL);
            if (s.isEmpty()) {
                return selectedJudges;
            }
            for (String element : s.split(";")) {
                try {
                    Member judge = MembersClient.getMember(Long.parseLong(element));
                    selectedJudges.add(judge);
                } catch (MemberNotFoundException ignored) {

                }
            }
        }
        return selectedJudges;
    }

    public boolean isUserAmongSelectedJudge(Member user) throws PortalException, SystemException {
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

    public long getVotesCount() {
        if (proposal.getProposalId() > 0) {
            try {
                org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
                long votingPhasePK = new ContestWrapper(contestMicro).getVotingPhasePK();
                return ProposalVoteClient.countProposalVotesInContestPhaseProposalId(proposal.getProposalId(), votingPhasePK);
            } catch (ContestNotFoundException ignored) {

            }

        }
        return 0;
    }

    public List<ProposalSectionWrapper> getSections() {
        if (sections == null) {
            sections = new ArrayList<>();
            if (contest != null) {
                    PlanTemplate planTemplate = PlanTemplateClient.getPlanTemplate(contest.getPlanTemplateId());
                    if (planTemplate != null) {
                        for (PlanSectionDefinition psd : PlanTemplateClient.getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId_(),true)) {
                            sections.add(new ProposalSectionWrapper(psd, this));
                        }
                    }
            }
        }
        return sections;
    }

    protected boolean getFellowScreeningNecessary() {
        return contestPhase.getFellowScreeningActive();
    }

    public List<MembershipRequestWrapper> getMembershipRequests() {
        if (this.membershipRequests == null) {
            membershipRequests = new ArrayList<>();
                for (MembershipRequest m : MembershipRequestClient.getMembershipRequests(proposal.getProposalId())) {
                    if (m.getStatusId() == MembershipRequestStatus.STATUS_PENDING_REQUESTED) {
                        membershipRequests.add(new MembershipRequestWrapper(m));
                    }
                }
        }
        return this.membershipRequests;
    }

    public List<String[]> getAllModelRegions() {
        List<String[]> modelRegions = new ArrayList<>();
        for (ModelRegions modelRegion : ModelRegions.values()) {
            modelRegions.add(new String[]{modelRegion.getModelRegionName(), modelRegion.getModelRegionTitle()});
        }
        return modelRegions;
    }

    public String getModelRegion() {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.REGION);
        if (attr == null) {
            return "";
        }
        return attr.getStringValue();
    }

    public void setModelRegion(String region, Long userId) {
        ProposalAttributeClient.setProposalAttribute(createProposalAttribute(proposal.getProposalId(), ProposalAttributeKeys.REGION, region),userId);
    }
    private static ProposalAttribute createProposalAttribute(Long proposalId, String name, String stringValue){
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setStringValue(stringValue);
        return proposalAttribute;
    }

    public Long getModelId() {
        return contest.getDefaultModelId();
    }

    public void setScenarioId(Long scenarioId, Long isConsolidatedScenario, Long userId) {
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposal.getProposalId());
        proposalAttribute.setName(ProposalAttributeKeys.SCENARIO_ID);
        proposalAttribute.setNumericValue(isConsolidatedScenario);
        proposalAttribute.setAdditionalId(scenarioId);
        ProposalAttributeClient.setProposalAttribute(proposalAttribute,userId);
    }

    public Long getScenarioId() {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        if (attr == null) {
            return 0L;
        }
        return attr.getNumericValue();
    }

    public Boolean isConsolidatedScenario(Long scenarioId) {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        return attr != null && attr.getAdditionalId() == 1;
    }

    public Map<Long, List<ProposalWrapper>> getSubProposalPerModel() {
        Map<Long, List<ProposalWrapper>> subProposalPerModel = new HashMap<>();
        List<Proposal> subProposals = ProposalsClient.getContestIntegrationRelevantSubproposals(proposal.getProposalId());

        for (Proposal subProposal : subProposals) {
            ProposalWrapper subProposalWrapper = new ProposalWrapper(subProposal);
            Long modelId = subProposalWrapper.getModelIdForStoredScenario();
            if (!subProposalPerModel.containsKey(modelId)) {
                subProposalPerModel.put(modelId, Collections.singletonList(subProposalWrapper));
            } else {
                subProposalPerModel.get(modelId).add(subProposalWrapper);
            }
        }
        return subProposalPerModel;
    }

    public Scenario getScenario() throws IOException, SystemException {
        return getScenarioByProposalId(proposal.getProposalId());
    }

    public Scenario getScenarioByProposalId(Long proposalId) throws IOException, SystemException {
        return CollaboratoriumModelingService.repository().getScenario(proposalId);
    }

    private static Long getModelIdForScenarioId(Long scenarioId) {
        Long modelId;

        try {
            Scenario scenario = CollaboratoriumModelingService.repository().getScenario(scenarioId);
            Simulation simulation = scenario.getSimulation();
            modelId = simulation.getId();
        } catch (IOException e) {
            modelId = 0L;
        }

        return modelId;
    }

    public List<Scenario> getSubProposalScenarios() throws SystemException, PortalException, IOException {
        List<Scenario> subProposalScenarios = new ArrayList<>();
        List<Proposal> subProposals = ProposalsClient.getContestIntegrationRelevantSubproposals(proposal.getProposalId());
        for (Proposal subProposal : subProposals) {
            Scenario scenarioForSubProposal = getScenarioByProposalId(subProposal.getProposalId());
            subProposalScenarios.add(scenarioForSubProposal);
        }
        return subProposalScenarios;
    }

    private Long getModelIdForStoredScenario() {
        return getModelIdForScenarioId(proposal.getProposalId());
    }

    public List<Long> getSubProposalScenarioIds() throws PortalException, SystemException {
        List<Long> subProposalScenarioIds = new ArrayList<>();
        Map<Long, List<ProposalWrapper>> subProposalPerModel = getSubProposalPerModel();

        if (!subProposalPerModel.isEmpty()) {
            for (Map.Entry<Long, List<ProposalWrapper>> entry : subProposalPerModel.entrySet()) {
                List<ProposalWrapper> proposalWrappers = entry.getValue();
                for (ProposalWrapper proposalWrapper : proposalWrappers) {
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
            case INCOMPLETE:
            case OFFTOPIC:
            case NOT_ADVANCE_OTHER:
                return GenericJudgingStatus.STATUS_REJECTED;
            case PASS_TO_JUDGES:
                return GenericJudgingStatus.STATUS_ACCEPTED;
            default:
                return GenericJudgingStatus.STATUS_UNKNOWN;
        }
    }

    /**
     * Determine if judges are done with proposal
     */
    public GenericJudgingStatus getJudgeStatus() {
        if (getFellowAction() == JudgingSystemActions.FellowAction.INCOMPLETE
                || getFellowAction() == JudgingSystemActions.FellowAction.OFFTOPIC
                || getFellowAction() == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER) {
            return GenericJudgingStatus.STATUS_REJECTED;
        }
        if (!getSelectedJudges().isEmpty() && getAllJudgesReviewFinished()) {
            return GenericJudgingStatus.STATUS_ACCEPTED;
        }
        return GenericJudgingStatus.STATUS_UNKNOWN;
    }

    /**
     * Determines whether the screening/advance decision of the proposal is done
     */
    public GenericJudgingStatus getOverallStatus() {
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON
                && Validator.isNotNull(getProposalReview())) {
            return GenericJudgingStatus.STATUS_ACCEPTED;
        }
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON
                && Validator.isNotNull(getProposalReview())
                || getScreeningStatus() == GenericJudgingStatus.STATUS_REJECTED) {
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

    public boolean getAllJudgesReviewFinished() {

            if (!getSelectedJudges().isEmpty()) {
                for (long userId : getSelectedJudges()) {
                    List<ProposalRating> proposalRatings = ProposalRatingClient
                    .getProposalRatingsByProposalUserContestPhase(
                                    userId, proposal.getProposalId(),
                                    contestPhase.getContestPhasePK());
                    ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(userId,
                            proposalRatings);
                    if (!wrapper.isReviewComplete()) {
                        return false;
                    }
                }
            }
            return true;

    }

    public boolean getJudgeReviewFinishedStatusUserId(long userId) {

            List<ProposalRating> proposalRatings = ProposalRatingClient
                    .getProposalRatingsByProposalUserContestPhase(
                            userId, proposal.getProposalId(), contestPhase.getContestPhasePK());
            ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(userId, proposalRatings);
            return wrapper.isReviewComplete();

    }

    public ProposalWrapper getBaseProposal() throws PortalException, SystemException {
        try {
            if (baseProposal == null) {
                long baseProposalId = proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_ID, 0);
                long baseProposalContestId = proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0);
                if (baseProposalId > 0 && baseProposalContestId > 0) {
                    Proposal p = ProposalsClient.getProposal(baseProposalId);
                    baseProposal = new ProposalWrapper(p);
                }
            }
            return baseProposal;
        }catch (ProposalNotFoundException ignored){
            return null;
        }
    }

    public List<ProposalRatingWrapper> getRatings() {
        if (this.proposalRatings == null) {
            return new ArrayList<>();
        }
        return this.proposalRatings.getRatings();
    }

    public String getRatingComment() {
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
