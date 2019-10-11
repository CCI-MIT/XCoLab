package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.tables.pojos.Proposal;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.enums.ProposalUnversionedAttributeName;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.contest.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.contest.proposals.helpers.ProposalContestPhaseAttributeHelper;
import org.xcolab.client.contest.proposals.helpers.ProposalUnversionedAttributeHelper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.membershiprequest.MembershipRequestStatus;
import org.xcolab.util.enums.modeling.ModelRegions;
import org.xcolab.util.enums.promotion.AssessmentStatus;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.util.http.caching.CacheName;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalWrapper extends Proposal implements Serializable {

    private static final Long LONG_DEFAULT_VAL = -1L;
    private static final String STRING_DEFAULT_VAL = "";

    private final Clients clients;

    protected final ContestWrapper contest;
    protected final ContestPhaseWrapper contestPhase;
    protected final IProposal2Phase proposal2Phase;

    protected final ProposalContestPhaseAttributeHelper proposalContestPhaseAttributeHelper;
    private final ProposalAttributeHelper proposalAttributeHelper;
    private final ProposalUnversionedAttributeHelper unversionedAttributeHelper;

    protected List<ProposalTeamMemberWrapper> members;
    private List<ProposalTemplateSectionDefinitionWrapper> sections;


    protected ProposalRatings proposalRatings;
    private ProposalRibbon ribbonWrapper;
    private List<ProposalTeamMembershipRequestWrapper> membershipRequests;
    private List<UserWrapper> supporters;

    public ProposalWrapper(ProposalWrapper proposal, ContestPhaseWrapper contestPhase) {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, null);
    }

    public ProposalWrapper(ProposalWrapper proposal, int version) {
        this(proposal, version, null, null, null);
    }

    public ProposalWrapper(ProposalWrapper value) {
        super(value);
        this.clients = new Clients();

        ContestAssociation contestAssociation = new ContestAssociation();
        this.contestPhase =  contestAssociation.getContestPhase();
        this.contest =  contestAssociation.getContest();
        this.proposal2Phase = contestAssociation.getProposal2Phase();

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);

        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(),
                clients.proposalAttribute);
        unversionedAttributeHelper = new ProposalUnversionedAttributeHelper(this,
                clients.proposalAttribute);
    }

    public ProposalWrapper(ProposalWrapper proposal, ContestPhaseWrapper contestPhase, IProposal2Phase proposal2Phase) {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, proposal2Phase);
    }

    public ProposalWrapper(ProposalWrapper proposal, Integer version, ContestWrapper contest) {
        this(proposal, version, contest, null, null);
    }

    public ProposalWrapper(ProposalWrapper proposal, Integer version, ContestWrapper contest,
            ContestPhaseWrapper contestPhase, IProposal2Phase proposal2Phase) {
        super(proposal);
        this.clients = new Clients();
        ContestAssociation contestAssociation =
                new ContestAssociation(contest, contestPhase, proposal2Phase);
        this.contestPhase =  contestAssociation.getContestPhase();
        this.contest =  contestAssociation.getContest();
        this.proposal2Phase = contestAssociation.getProposal2Phase();

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, this.contestPhase);
        final int currentVersion = version != null ? version : proposal.getVersion();
        proposalAttributeHelper = new ProposalAttributeHelper(this, currentVersion,
                clients.proposalAttribute);
        unversionedAttributeHelper = new ProposalUnversionedAttributeHelper(this,
                clients.proposalAttribute);
    }

    public ProposalWrapper() {
        super();
        this.clients = new Clients();
        this.setId(0L);

        ContestAssociation contestAssociation = new ContestAssociation();
        this.contestPhase =  contestAssociation.getContestPhase();
        this.contest =  contestAssociation.getContest();
        this.proposal2Phase = contestAssociation.getProposal2Phase();

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(),
                clients.proposalAttribute);
        unversionedAttributeHelper = new ProposalUnversionedAttributeHelper(this,
                clients.proposalAttribute);
    }

    public ProposalWrapper(Proposal proposal) {
        super(proposal);

        this.clients = new Clients();

        ContestAssociation contestAssociation = new ContestAssociation();
        this.contestPhase =  contestAssociation.getContestPhase();
        this.contest =  contestAssociation.getContest();
        this.proposal2Phase = contestAssociation.getProposal2Phase();

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(),
                clients.proposalAttribute);
        unversionedAttributeHelper = new ProposalUnversionedAttributeHelper(this,
                clients.proposalAttribute);
    }

    @JsonIgnore
    public String getProposalLinkUrl(ContestWrapper contest) {
        return getProposalLinkUrl(contest, 0L);
    }

    @JsonIgnore
    public String getProposalLinkUrl(ContestWrapper contest, long contestPhaseId) {
        Long proposalId = this.getId();
        ContestType contestType = StaticAdminContext.getContestTypeClient()
                .getContestType(contest.getContestTypeId());
        String link = contestType.getContestBaseUrl();

        String friendlyUrlStringProposal = contestType.getFriendlyUrlStringProposal();

        if (contestPhaseId > 0) {
            long activePhaseId =
                    clients.contest.getActivePhase(contest.getId()).getId();
            if (activePhaseId == contestPhaseId) {
                link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
                return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                        proposalId);
            }

            link += "/%d/%s/phase/%d/" + friendlyUrlStringProposal + "/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                    contestPhaseId, proposalId);
        }

        link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
        return String
                .format(link, contest.getContestYear(), contest.getContestUrlName(), proposalId);
    }

    @JsonIgnore
    public boolean isDeleted() {
        if (this.getId() == 0) {
            return false;
        }
        final ContestPhaseWrapper contestPhase = clients.contest.getContestPhase(
                clients.proposal.getLatestContestPhaseIdInProposal(this.getId()));
        long visibleAttributeValue = 1;
        if (contestPhase != null) {
            IProposalContestPhaseAttribute pcpa = clients.proposalPhase
                    .getProposalContestPhaseAttribute(contestPhase.getId(),this.getId(),
                            ProposalContestPhaseAttributeKeys.VISIBLE);
            if (pcpa != null) {
                visibleAttributeValue = pcpa.getNumericValue();
            }
        }
        return !this.isVisible() || visibleAttributeValue == 0;

    }

    @JsonIgnore
    public String getCleanPitch() {
        return HtmlUtil.cleanAll(getPitch());
    }

    @JsonIgnore
    public String getPitch() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.PITCH, "");
    }

    @JsonIgnore
    public String getName() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");
    }

    @JsonIgnore
    public String getDescription() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.DESCRIPTION, "");
    }

    @JsonIgnore
    public boolean isUserAmongFellows(long userId) {
        for (Long fellowId : clients.contestTeamMember.getFellowsForContest(contest.getId())) {
            if (fellowId == userId) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public boolean isUserAmongJudges(long userId) {
        for (Long judge : clients.contestTeamMember.getJudgesForContest(contest.getId())) {
            if (judge == userId) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public boolean isJudgingContestPhase() {
        return ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote())
                == ContestPhasePromoteType.PROMOTE_JUDGED;
    }

    @JsonIgnore
    public String getTeam() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.TEAM, "");
    }

    @JsonIgnore
    public UserWrapper getAuthor() {
        try {
            return StaticUserContext.getUserClient().getMember(this.getAuthorUserId());
        } catch (MemberNotFoundException e) {
            throw new IllegalStateException("Author " + this.getAuthorUserId()
                    + " of proposal " + this.getId() + " does not exist");
        }
    }

    @JsonIgnore
    public long getSupportersCount() {
        return clients.proposalMemberRating.getProposalSupportersCount(this.getId());
    }

    @JsonIgnore
    public long getSupportersCountCached() {
        return clients.proposalMemberRating.getProposalSupportersCount(this.getId());
    }

    @JsonIgnore
    public long getCommentsCount() {
        if (this.getId() > 0) {
            return StaticCommentContext.getCommentClient().countComments(this.getDiscussionId());
        }
        return 0;
    }

    @JsonIgnore
    public long getEvaluationCommentsCount() {
        return 0;
    }

    @JsonIgnore
    private long createDiscussionThread(String threadTitleSuffix, boolean isQuiet) {
        final ContestType contestType = getContest().getContestType();
        IThread thread = new org.xcolab.client.comment.pojo.tables.pojos.Thread();
        thread.setAuthorUserId(getAuthorUserId());
        thread.setTitle(contestType.getProposalName() + getName() + threadTitleSuffix);
        thread.setIsQuiet(isQuiet);
        return StaticCommentContext.getThreadClient().createThread(thread).getId();
    }

    @JsonIgnore
    public long getDiscussionIdOrCreate() {
        Long discussionId = getDiscussionId();
        if (discussionId == null) {
            discussionId = createDiscussionThread(" comments", false);
            setDiscussionId(discussionId);
            clients.proposal.updateProposal(this);
        }
        return discussionId;
    }

    @JsonIgnore
    public long getResultsDiscussionIdOrCreate() {
        Long discussionId = getResultsDiscussionId();
        if (discussionId == null) {
            discussionId = createDiscussionThread(" results discussion", true);
            setResultsDiscussionId(discussionId);
            clients.proposal.updateProposal(this);
        }
        return discussionId;
    }

    @JsonIgnore
    public Date getLastupdatedAt() {
        return this.getUpdatedAt();
    }

    @JsonIgnore
    public Date getLastupdatedAtForContestPhase() {
        if (proposal2Phase.getVersionTo() == -1) {
            return getLastupdatedAt();
        }
        return clients.proposal
                .getProposalVersionByProposalIdVersion(this.getId(), this.getVersion()).getCreatedAt();

    }

    @JsonIgnore
    public boolean isOpen() {
        if (this.getId() > 0) {
            ProposalAttribute attribute = clients.proposalAttribute
                    .getProposalAttribute(this.getId(), ProposalAttributeKeys.OPEN, 0L);
            return attribute != null && attribute.getNumericValue() > 0;
        } else {
            return false;
        }
    }

    @JsonIgnore
    public long getImageId() {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }

    @JsonIgnore
    public String getLogoPath() {
        String imageDomain = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        if (getImageId() == 0 && getContest().getDefaultProposalLogoId() != null) {
            return imageDomain + "/image/proposal/" + getContest().getDefaultProposalLogoId();
        }
        return imageDomain + "/image/proposal/" + getImageId();
    }

    @JsonIgnore
    public String getProposalUrl() {
        return this.getProposalLinkUrl(contest);
    }

    @JsonIgnore
    public String getAbsoluteProposalUrl() {
        return ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get() + getProposalUrl();
    }

    @JsonIgnore
    public String getProposalDiscussionUrl() {
        boolean isSeparateTab = ConfigurationAttributeKey.PROPOSALS_COMMENTS_IN_SEPARATE_TAB.get();
        if (isSeparateTab) {
            return this.getProposalLinkUrl(contest) + "/tab/COMMENTS";
        }
        return getProposalUrl() + "#Comments";
    }

    @JsonIgnore
    public String getProposalUrl(ContestPhaseWrapper inPhase) {
        return this.getProposalLinkUrl(contest, inPhase.getId());
    }

    @JsonIgnore
    public List<UserWrapper> getSupporters() {
        if (supporters == null) {
            supporters = clients.proposalMemberRating.getProposalSupporters(getId()).stream()
                    .map(supporter -> StaticUserContext.getUserClient().getMemberOrNull(supporter.getUserId()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return supporters;
    }

    @JsonIgnore
    public String getAuthorName() {
        String authorName = getTeam();
        if (StringUtils.isBlank(authorName)) {
            authorName = getAuthor().getDisplayName();
        }
        return authorName;
    }

    @JsonIgnore
    public ContestWrapper getContest() {
        return contest;
    }

    @JsonIgnore
    public boolean isContestMatchesLatestContest() {
        final long contestId = getContest().getId();
        final ContestWrapper currentContestForProposal =
                clients.proposal.getCurrentContestForProposal(getId());
        return contestId == currentContestForProposal.getId();
    }

    @JsonIgnore
    public boolean getIsLatestVersion() {
        return getCurrentVersion() == this.getVersion();
    }

    @JsonIgnore
    public ContestWrapper getWasMovedToContest() {
        try {
            ContestWrapper c = clients.proposal.getCurrentContestForProposal(this.getId());
            if (c.getId() != contest.getId().longValue()) {
                try {
                    return clients.contest.getContest(c.getId());
                } catch (ContestNotFoundException ignored) {
                }

            }
            return null;
        } catch (ContestNotFoundException e) {
            return null;
        }
    }

    @JsonIgnore
    public ProposalVersionWrapper getSelectedVersion() {
        for (ProposalVersionWrapper pv : clients.proposal.getAllProposalVersions(this.getId())) {
            if (pv.getVersion() == this.getVersion()) {
                return pv;
            }
        }
        return null;
    }

    @JsonIgnore
    public ProposalAttributeHelper getProposalAttributeHelper() {
        return proposalAttributeHelper;
    }

    @JsonIgnore
    public Integer getCurrentVersion() {
        return clients.proposal.getMaxVersion(getId());
    }

    @JsonIgnore
    public int getVersion() {
        return this.getCurrentVersion() != null ? this.getCurrentVersion() : 0;
    }

    @JsonIgnore
    public long getContestId() {
        return contest.getId();
    }

    @JsonIgnore
    public boolean isVisibleInContest(long contestId) {
        try {
            if (this.getId() == 0) {
                return true;
            }
            final ContestWrapper currentContest =
                    clients.proposal.getCurrentContestForProposal(this.getId());
            return !isDeleted() && currentContest.getId() == contestId;
        } catch (ContestNotFoundException ignored) {
            return false;
        }
    }

    @JsonIgnore
    public ContestPhaseWrapper getContestPhase() {
        return contestPhase;
    }

    @JsonIgnore
    public List<ProposalTeamMemberWrapper> getMembers() {
        if (members == null) {
            members = new ArrayList<>();

            for (UserWrapper member : clients.proposal.getProposalMembers(getId())) {
                final ProposalTeamMemberWrapper teamMemberWrapper = new ProposalTeamMemberWrapper(
                        this, member);
                members.add(teamMemberWrapper);
            }
        }
        return members;
    }

    @JsonIgnore
    public boolean isFeatured() {
        return getRibbonWrapper().getRibbon() > 0;
    }

    @JsonIgnore
    public JudgingSystemActions.AdvanceDecision getJudgeDecision() {
        long judgingDecision = proposalContestPhaseAttributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.AdvanceDecision.fromInt((int) judgingDecision);
    }

    @JsonIgnore
    public JudgingSystemActions.FellowAction getFellowAction() {
        Long action = proposalContestPhaseAttributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.FellowAction.fromInt(action.intValue());
    }

    @JsonIgnore
    public String getFellowActionComment() {
        return proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, 0, STRING_DEFAULT_VAL);
    }

    @JsonIgnore
    public String getProposalReview() {
        return proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, 0, STRING_DEFAULT_VAL);
    }

    @JsonIgnore
    public List<Long> getSelectedJudges() {
        List<Long> selectedJudges = new ArrayList<>();


        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            selectedJudges
                    .addAll(clients.contestTeamMember.getJudgesForContest(contest.getId()));
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

    @JsonIgnore
    public List<UserWrapper> getSelectedJudgeUsers() {
        List<UserWrapper> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (Long judgeId : clients.contestTeamMember.getJudgesForContest(contest.getId())) {

                try {
                    UserWrapper judge = StaticUserContext.getUserClient().getMember(judgeId);
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
                    UserWrapper judge = StaticUserContext.getUserClient().getMember(Long.parseLong(element));
                    selectedJudges.add(judge);
                } catch (MemberNotFoundException ignored) {

                }
            }
        }
        return selectedJudges;
    }

    @JsonIgnore
    public boolean getIsUserAmongSelectedJudges(long judgeId) {
        if (!getFellowScreeningNecessary()) {
            return isUserAmongJudges(judgeId);
        }

        return getSelectedJudges().stream()
                .anyMatch(userId -> Objects.equals(userId, judgeId));
    }

    @JsonIgnore
    public long getVotesCount() {
        return getVotesCountInternal(CacheName.NONE);
    }

    @JsonIgnore
    public long getVotesCountFromCache() {
        return getVotesCountInternal(CacheName.MEMBER_RATING);
    }

    private long getVotesCountInternal(CacheName cacheName) {
        if (this.getId() > 0) {

            long votingPhasePK = contest.getVotingPhasePK();
            return clients.proposalMemberRating.countProposalVotes(
                    votingPhasePK, this.getId(),null,true);
        }
        return 0;
    }

    @JsonIgnore
    public List<ProposalTemplateSectionDefinitionWrapper> getSections() {
        if (sections == null) {
            sections = new ArrayList<>();
            if (contest != null) {
                IProposalTemplate proposalTemplate = clients.proposalTemplate.getProposalTemplate(contest.getProposalTemplateId());
                if (proposalTemplate != null) {
                    for (ProposalTemplateSectionDefinitionWrapper psd : clients.proposalTemplate
                            .getProposalTemplateSectionDefinitionByProposalTemplateId(proposalTemplate.getId(),true)) {

                        sections.add(new ProposalTemplateSectionDefinitionWrapper(psd, this));
                    }
                }
            }
        }
        return sections;
    }

    protected boolean getFellowScreeningNecessary() {
        return contestPhase.getFellowScreeningActive();
    }

    @JsonIgnore
    public List<ProposalTeamMembershipRequestWrapper> getMembershipRequests() {
        if (this.membershipRequests == null) {
            membershipRequests = new ArrayList<>();
            for (ProposalTeamMembershipRequestWrapper m : clients.membership.getMembershipRequests(this.getId())) {
                if (m.getStatusId() == MembershipRequestStatus.STATUS_PENDING_REQUESTED) {
                    membershipRequests.add((m));
                }
            }
        }
        return this.membershipRequests;
    }

    @JsonIgnore
    public List<String[]> getAllModelRegions() {
        List<String[]> modelRegions = new ArrayList<>();
        for (ModelRegions modelRegion : ModelRegions.values()) {
            modelRegions.add(new String[]{modelRegion.getModelRegionName(), modelRegion.getModelRegionTitle()});
        }
        return modelRegions;
    }

    @JsonIgnore
    public String getModelRegion() {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.REGION);
        if (attr == null) {
            return "";
        }
        return attr.getStringValue();
    }

    @JsonIgnore
    public void setModelRegion(String region, Long userId) {
        StaticProposalContext.getProposalAttributeClient()
                .setProposalAttribute(createProposalAttribute(this.getId(), ProposalAttributeKeys.REGION, region),userId);
    }

    private static ProposalAttribute createProposalAttribute(Long proposalId, String name, String stringValue){
        ProposalAttribute
                proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setStringValue(stringValue);
        return proposalAttribute;
    }

    @JsonIgnore
    public Long getModelId() {
        return contest.getDefaultModelId();
    }

    @JsonIgnore
    public void setScenarioId(Long scenarioId, Long isConsolidatedScenario, Long userId) {
        ProposalAttribute
                proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(this.getId());
        proposalAttribute.setName(ProposalAttributeKeys.SCENARIO_ID);
        proposalAttribute.setNumericValue(isConsolidatedScenario);
        proposalAttribute.setAdditionalId(scenarioId);
        StaticProposalContext.getProposalAttributeClient()
                .setProposalAttribute(proposalAttribute,userId);
    }

    @JsonIgnore
    public Long getScenarioId() {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        if (attr == null) {
            return 0L;
        }
        return attr.getNumericValue();
    }

    @JsonIgnore
    public Boolean isConsolidatedScenario(Long scenarioId) {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        return attr != null && attr.getAdditionalId() == 1;
    }

    @JsonIgnore
    public Map<Long, List<ProposalWrapper>> getSubProposalPerModel() {
        Map<Long, List<ProposalWrapper>> subProposalPerModel = new HashMap<>();
        List<ProposalWrapper> subProposals = StaticProposalContext.getProposalClient()
                .getContestIntegrationRelevantSubproposals(this.getId());

        for (ProposalWrapper subProposal : subProposals) {
            Long modelId = subProposal.getModelIdForStoredScenario();
            if (!subProposalPerModel.containsKey(modelId)) {
                subProposalPerModel.put(modelId, Collections.singletonList(subProposal));
            } else {
                subProposalPerModel.get(modelId).add(subProposal);
            }
        }
        return subProposalPerModel;
    }

    @JsonIgnore
    public Scenario getScenario() throws IOException {
        return getScenarioByProposalId(this.getId());
    }

    @JsonIgnore
    public Scenario getScenarioByProposalId(Long proposalId) throws IOException {
        return RomaClientUtil.client().getScenario(proposalId);
    }

    private static Long getModelIdForScenarioId(Long scenarioId) {
        Long modelId;

        try {
            Scenario scenario = RomaClientUtil.client().getScenario(scenarioId);
            Simulation simulation = scenario.getSimulation();
            modelId = simulation.getId();
        } catch (IOException e) {
            modelId = 0L;
        }

        return modelId;
    }

    @JsonIgnore
    public List<Scenario> getSubProposalScenarios() throws IOException {
        List<Scenario> subProposalScenarios = new ArrayList<>();
        List<ProposalWrapper> subProposals = StaticProposalContext.getProposalClient()
                .getContestIntegrationRelevantSubproposals(this.getId());
        for (ProposalWrapper subProposal : subProposals) {
            Scenario scenarioForSubProposal = getScenarioByProposalId(subProposal.getId());
            subProposalScenarios.add(scenarioForSubProposal);
        }
        return subProposalScenarios;
    }

    private Long getModelIdForStoredScenario() {
        return getModelIdForScenarioId(this.getId());
    }

    @JsonIgnore
    public List<Long> getSubProposalScenarioIds() {
        List<Long> subProposalScenarioIds = new ArrayList<>();
        Map<Long, List<ProposalWrapper>> subProposalPerModel = getSubProposalPerModel();

        if (!subProposalPerModel.isEmpty()) {
            for (Map.Entry<Long, List<ProposalWrapper>> entry : subProposalPerModel.entrySet()) {
                List<ProposalWrapper> proposals = entry.getValue();
                for (ProposalWrapper proposal : proposals) {
                    subProposalScenarioIds.add(proposal.getScenarioId());
                }
            }
        }

        return subProposalScenarioIds;
    }

    @JsonIgnore
    public String getImpactCommentAuthor() {
        ProposalUnversionedAttribute authorCommentAttribute = unversionedAttributeHelper
                .getAttributeOrNull(ProposalUnversionedAttributeName.IMPACT_AUTHOR_COMMENT
                        .toString(), 0);
        if (authorCommentAttribute != null) {
            final String authorComment = authorCommentAttribute.getStringValue();
            if (StringUtils.isNotBlank(authorComment)) {
                return authorComment;
            }
        }
        return "";
    }

    @JsonIgnore
    public String getImpactCommentIaf() {
        ProposalUnversionedAttribute iafCommentAttribute = unversionedAttributeHelper
                .getAttributeOrNull(ProposalUnversionedAttributeName.IMPACT_IAF_COMMENT
                        .toString(), 0);
        if (iafCommentAttribute != null) {
            final String iafComment = iafCommentAttribute.getStringValue();
            if (StringUtils.isNotBlank(iafComment)) {
                return iafComment;
            }
        }
        return "";
    }

    /**
     * Determine if fellows are done with proposal
     */
    @JsonIgnore
    public AssessmentStatus getScreeningStatus() {
        switch (getFellowAction()) {
            case INCOMPLETE:
            case OFFTOPIC:
            case NOT_ADVANCE_OTHER:
                return AssessmentStatus.NEGATIVE;
            case PASS_TO_JUDGES:
                return AssessmentStatus.POSITIVE;
            default:
                return AssessmentStatus.UNKNOWN;
        }
    }

    /**
     * Determine if IA fellows are done with proposal
     */
    @JsonIgnore
    public AssessmentStatus getImpactAssessmentStatus() {
        final String impactCommentIaf = getImpactCommentIaf();
        if (impactCommentIaf.startsWith("Done")) {
            return AssessmentStatus.POSITIVE;
        }
        if (impactCommentIaf.startsWith("Started")) {
            return AssessmentStatus.NEGATIVE;
        }
        return AssessmentStatus.UNKNOWN;
    }

    /**
     * Determine if judges are done with proposal
     */
    @JsonIgnore
    public AssessmentStatus getJudgeStatus() {
        if (getFellowAction() == JudgingSystemActions.FellowAction.INCOMPLETE
                || getFellowAction() == JudgingSystemActions.FellowAction.OFFTOPIC
                || getFellowAction() == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER) {
            return AssessmentStatus.NEGATIVE;
        }
        if (!getSelectedJudges().isEmpty() && getAllJudgesReviewFinished()) {
            return AssessmentStatus.POSITIVE;
        }
        return AssessmentStatus.UNKNOWN;
    }

    /**
     * Determines whether the screening/advance decision of the proposal is done
     */
    @JsonIgnore
    public AssessmentStatus getOverallStatus() {
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON
                && (getProposalReview()!=null)) {
            return AssessmentStatus.POSITIVE;
        }
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON
                && (getProposalReview()!=null)
                || getScreeningStatus() == AssessmentStatus.NEGATIVE) {
            return AssessmentStatus.NEGATIVE;
        }

        return AssessmentStatus.UNKNOWN;
    }

    @JsonIgnore
    public UserWrapper getUserForSelectedVersion() {
        try {
            return StaticUserContext.getUserClient().getMember(getSelectedVersion().getAuthorUserId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }

    @JsonIgnore
    public boolean getAllJudgesReviewFinished() {

        if (!getSelectedJudges().isEmpty()) {
            for (long userId : getSelectedJudges()) {
                List<ProposalRatingWrapper> proposalRatings = StaticProposalContext
                        .getProposalJudgeRatingClient()
                        .getProposalRatingsByProposalUserContestPhase(this.getId(),
                                contestPhase.getId(),userId);
                ProposalRatings wrapper = new UserProposalRatings(userId, proposalRatings);
                if (!wrapper.isReviewComplete()) {
                    return false;
                }
            }
        }
        return true;
    }

    @JsonIgnore
    public boolean getIsReviewFinishedForJudge(long judgeId) {
        List<ProposalRatingWrapper> proposalRatings = StaticProposalContext
                .getProposalJudgeRatingClient()
                .getProposalRatingsByProposalUserContestPhase(
                        getId(), contestPhase.getId(), judgeId);
        ProposalRatings wrapper = new UserProposalRatings(judgeId, proposalRatings);
        return wrapper.isReviewComplete();
    }

    @JsonIgnore
    public ProposalWrapper getBaseProposal() {
        long baseProposalId = proposalAttributeHelper
                .getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_ID, 0);
        if (baseProposalId > 0) {
            long baseProposalContestId = proposalAttributeHelper
                    .getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0);
            if (baseProposalContestId > 0) {
                return new ProposalWrapper(clients.proposal.getProposal(baseProposalId));
            }
        }
        return null;
    }

    @JsonIgnore
    public List<ProposalRatingWrapper> getRatings() {
        if (this.proposalRatings == null) {
            return new ArrayList<>();
        }
        return this.proposalRatings.getRatings();
    }

    @JsonIgnore
    public String getRatingComment() {
        if (this.proposalRatings == null) {
            return "";
        }
        return this.proposalRatings.getComment();
    }

    @JsonIgnore
    public Boolean isRatingShouldAdvance() {
        if (this.proposalRatings == null) {
            return null;
        }
        return this.proposalRatings.isShouldAdvance();
    }

    @JsonIgnore
    public ProposalRibbon getRibbonWrapper() {
        if (ribbonWrapper == null) {
            ribbonWrapper = new ProposalRibbon(this);
        }
        return ribbonWrapper;
    }

    @JsonIgnore
    public boolean hasRibbon() {
        return getRibbonWrapper().getRibbon() > 0;
    }

    @JsonIgnore
    public Long getImage() {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0);
    }

    private static class Clients {

        final IContestClient contest;
        final IProposalClient proposal;

        final IProposalMemberRatingClient proposalMemberRating;
        final IProposalJudgeRatingClient proposalJudgeRating;

        final IMembershipClient membership;

        final IProposalAttributeClient proposalAttribute;
        final IProposalPhaseClient proposalPhase;
        final IContestTeamMemberClient contestTeamMember;
        final IProposalTemplateClient proposalTemplate;

        Clients() {
            contest = StaticContestContext.getContestClient();
            proposal = StaticProposalContext.getProposalClient();
            proposalAttribute = StaticProposalContext.getProposalAttributeClient();
            proposalPhase = StaticProposalContext.getProposalPhaseClient();
            contestTeamMember = StaticContestContext.getContestTeamMemberClient();
            proposalMemberRating = StaticProposalContext.getProposalMemberRatingClient();
            membership = StaticProposalContext.getMembershipClient();
            proposalTemplate = StaticContestContext.getProposalTemplateClient();
            proposalJudgeRating = StaticProposalContext.getProposalJudgeRatingClient();
        }
    }

    private class ContestAssociation {
        private final ContestWrapper contest;
        private final ContestPhaseWrapper contestPhase;
        private final IProposal2Phase proposal2Phase;

        ContestAssociation() {
            this(null, null, null);
        }

        ContestAssociation(ContestWrapper contest, ContestPhaseWrapper contestPhase,
                IProposal2Phase proposal2Phase) {
            boolean proposalExists = getId() != null && getId() != 0L;
            if (contest == null && proposalExists) {
                contest = fetchContestFromProposal();
            }
            if (contestPhase == null && contest != null) {
                contestPhase = fetchPhaseFromContest(contest);
            }
            if (proposal2Phase == null && contestPhase != null && proposalExists) {
                proposal2Phase = fetchProposal2Phase(contestPhase);
            }
            if (contest == null && contestPhase != null) {
                contest = fetchContestFromPhase(contestPhase);
            }
            if (contestPhase == null && proposal2Phase != null) {
                contestPhase = fetchPhaseFromProposal2Phase(proposal2Phase);
            }
            this.contestPhase = contestPhase;
            this.contest = contest;
            this.proposal2Phase = proposal2Phase;
        }

        private ContestPhaseWrapper fetchPhaseFromProposal2Phase(IProposal2Phase proposal2Phase) {
            return clients.contest.getContestPhase(proposal2Phase.getContestPhaseId());
        }

        private ContestWrapper fetchContestFromPhase(ContestPhaseWrapper contestPhase) {
            return clients.contest.getContest(contestPhase.getContestId());
        }

        private IProposal2Phase fetchProposal2Phase(ContestPhaseWrapper contestPhase) {
            try {
                return clients.proposalPhase.getProposal2PhaseByProposalIdContestPhaseId(
                        getId(), contestPhase.getId());
            } catch (Proposal2PhaseNotFoundException e) {
                return null;
            }
        }

        private ContestPhaseWrapper fetchPhaseFromContest(ContestWrapper contest) {
            return clients.contest.getActivePhase(contest.getId());
        }

        private ContestWrapper fetchContestFromProposal() {
            return clients.proposal.getCurrentContestForProposal(getId());
        }

        public ContestWrapper getContest() {
            return contest;
        }

        public ContestPhaseWrapper getContestPhase() {
            return contestPhase;
        }

        public IProposal2Phase getProposal2Phase() {
            return proposal2Phase;
        }
    }
}
