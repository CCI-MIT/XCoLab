package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.enums.ContestRole;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.IContestTeamMemberRole;
import org.xcolab.client.contest.pojo.tables.pojos.Contest;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.util.ContestScheduleChangeHelper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.permissions.SystemRole;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.time.DateUtil;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContestWrapper extends Contest implements Serializable {

    private final IContestClient contestClient;
    private final IContestTeamMemberClient contestTeamMemberClient;
    private final IOntologyClient ontologyClient;
    private final IProposalTemplateClient proposalTemplateClient;

    private static final Map<Long, FocusAreaWrapper> faCache = new HashMap<>();
    private final Map<String, List<OntologyTermWrapper>> ontologySpaceCache = new HashMap<>();

    protected static final String WHERE = "where";
    protected static final String WHAT = "what";
    protected static final String WHO = "who";
    protected static final String HOW = "how";
    private static final long ONTOLOGY_SPACE_ID_WHERE = 104L;
    private static final long ONTOLOGY_SPACE_ID_WHO = 102L;
    private static final long ONTOLOGY_SPACE_ID_WHAT = 103L;
    private static final long ONTOLOGY_SPACE_ID_HOW = 103L;
    private static final long CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS = 3L;
    private static final String EMAIL_TEMPLATE_URL = "/resources/-/wiki/Main/Judging+Mail+Templates";

    private final Map<String, String> ontologyJoinedNames = new HashMap<>();
    private List<ContestPhaseWrapper> visiblePhases;

    protected List<ContestPhaseWrapper> phases;
    protected ContestType contestType;
    protected Map<IContestTeamMemberRole, List<UserWrapper>> contestTeamMembersByRole;

    protected ContestPhaseWrapper activePhase;

    public ContestWrapper() {
        contestClient = StaticContestContext.getContestClient();
        contestTeamMemberClient = StaticContestContext.getContestTeamMemberClient();
        ontologyClient = StaticContestContext.getOntologyClient();
        proposalTemplateClient = StaticContestContext.getProposalTemplateClient();
    }

    public ContestWrapper(Contest value) {
        super(value);
        contestClient = StaticContestContext.getContestClient();
        contestTeamMemberClient = StaticContestContext.getContestTeamMemberClient();
        ontologyClient = StaticContestContext.getOntologyClient();
        proposalTemplateClient = StaticContestContext.getProposalTemplateClient();
    }

    @JsonIgnore
    public String getContestDiscussionLinkUrl() {
        return getContestLinkUrl() + "/discussion";
    }

    @JsonIgnore
    public String getContestLinkUrl() {
        String link = getContestType().getContestBaseUrl();
        link += "/%d/%s";
        return String.format(link, this.getContestYear(), this.getContestUrlName());
    }

    @JsonIgnore
    public String getContestLinkUrl(long contestPhaseId) {
        return getContestLinkUrl() + "/phase/" + contestPhaseId;
    }

    @JsonIgnore
    public String getSponsorLogoPath() {
        Long imgId = this.getSponsorLogoId();
        if (imgId != null) {
            return "/image/contest/" + imgId;
        }
        return "";
    }

    @JsonIgnore
    public String getCleanContestDescription() {
        return HtmlUtil.cleanAll(this.getDescription());
    }

    @JsonIgnore
    public String getLogoPath() {
        long imgId = this.getContestLogoId() != null ? this.getContestLogoId() : 0;
        return "/image/contest/" + imgId;
    }

    @JsonIgnore
    public String generateContestUrlName() {
        String contestUrlName = this.getTitle().toLowerCase();
        return contestUrlName.replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
    }

    @JsonIgnore
    public void persist() {
        contestClient.updateContest(this);
    }

    @JsonIgnore
    public String getTitleWithEndYear() {
        final String contestShortName = getTitle();
        if (ConfigurationAttributeKey.CONTESTS_SHOW_YEAR_WHEN_COMPLETED.get()) {
            final char lastCharOfName = contestShortName.charAt(contestShortName.length() - 1);
            final boolean nameEndsInNumber = Character.isDigit(lastCharOfName);
            if (isContestCompleted() && !nameEndsInNumber) {
                ContestPhaseWrapper activePhase = getActivePhase();
                if (activePhase != null) {
                    int phaseEndYear = DateUtil.getYearFromDate(activePhase.getPhaseStartDate());
                    return contestShortName + " " + phaseEndYear;
                }
            }
        }
        return contestShortName;
    }

    @JsonIgnore
    public boolean getContestInVotingPhase() {
        ContestPhaseWrapper phase = contestClient.getActivePhase(this.getId());
        if (phase == null) {
            return false;
        }

        String status = phase.getContestStatusStr();
        return status != null && ContestStatus.valueOf(status).isCanVote();

    }

    @JsonIgnore
    public boolean isContestCompleted(){
        ContestPhaseWrapper activePhase = getActivePhase();
        if (activePhase != null) {
            return activePhase.isCompleted();
        }
        return getLastPhase().isEnded();
    }

    @JsonIgnore
    public boolean isShowColoredFlag() {
        if (getFlag() > 0) {
            return getFlag() == 1;
        }
        return getActivePhase().getStatus().isCanAnything();
    }

    @JsonIgnore
    public boolean getSponsorLinkAvailable() {
        return !StringUtils.isEmpty(this.getSponsorLink());
    }

    @JsonIgnore
    public long getProposalsCount() {
        try {
            ContestPhaseWrapper cp = contestClient.getActivePhase(this.getId());
            if (cp != null) {
                return StaticProposalContext.getProposalPhaseClient()
                        .getProposalCountForActiveContestPhase(cp.getId());
            }
        } catch (UncheckedEntityNotFoundException e) {
            //fall through - return 0
        }
        return 0L;
    }

    @JsonIgnore
    public long getCommentsCount() {
        return StaticCommentContext.getCommentClient().countComments(this.getOrCreateDiscussionGroupId());
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getWho() {
        return getTermFromSpace(WHO);
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getWhat() {
        return getTermFromSpace(WHAT);
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getWhere() {
        return getTermFromSpace(WHERE);
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getHow() {
        return getTermFromSpace(HOW);
    }

    protected List<OntologyTermWrapper> getTermFromSpace(String space) {

        if (!ontologySpaceCache.containsKey(space) && getHasFocusArea()) {
            final Long focusAreaId = getFocusAreaId();
            if (!faCache.containsKey(focusAreaId)) {
                FocusAreaWrapper fa = ontologyClient.getFocusArea(focusAreaId);
                if (fa == null) {
                    ontologySpaceCache.put(space, null);
                    return null;
                }
                faCache.put(fa.getId(), fa);
            }
            List<OntologyTermWrapper> terms = new ArrayList<>();
            terms.addAll(ontologyClient
                    .getOntologyTermsByFocusAreaOntologySpaceName(focusAreaId, space));
            ontologySpaceCache.put(space, terms.isEmpty() ? null : terms);
        }
        return ontologySpaceCache.get(space);

    }

    @JsonIgnore
    public long getFocusAreaIdOrZero() {
        return getFocusAreaId() != null ? getFocusAreaId() : 0;
    }

    @JsonIgnore
    public List<ContestPhaseWrapper> getPhases() {
        if (phases == null) {
            phases = new ArrayList<>();
            phases.addAll(contestClient.getAllContestPhases(this.getId()));
        }
        return phases;
    }

    @JsonIgnore
    public Map<IContestTeamMemberRole, List<UserWrapper>> getContestTeamMembersByRole() {
        if (contestTeamMembersByRole == null) {
            contestTeamMembersByRole = new TreeMap<>();
            final List<IContestTeamMember> teamMembers =
                    contestTeamMemberClient.getTeamMembers(null, this.getId(), null);
            for (IContestTeamMember teamMember : teamMembers) {
                try {
                    IContestTeamMemberRole role = contestTeamMemberClient
                            .getContestTeamMemberRole(teamMember.getRoleId());
                    List<UserWrapper> roleUsers = contestTeamMembersByRole
                            .computeIfAbsent(role, k -> new ArrayList<>());
                    roleUsers.add(StaticUserContext.getUserClient().getMember(teamMember.getUserId()));
                } catch (MemberNotFoundException e) {
                    //_log.warn("Contest team member " + teamMember.getUserId() + " does not have an account");
                }
            }
        }
        return contestTeamMembersByRole;
    }

    @JsonIgnore
    public boolean getHasUserRoleInContest(long userId, long roleId) {
        for (Entry<IContestTeamMemberRole, List<UserWrapper>> entry
                : getContestTeamMembersByRole().entrySet()) {
            final IContestTeamMemberRole role = entry.getKey();
            final List<UserWrapper> members = entry.getValue();
            if (role.getId() == roleId) {
                return members.stream()
                        .anyMatch(p -> p.getId() == userId);
            }
        }
        return false;
    }

    @JsonIgnore
    public boolean getCanFellow(long userId) {
        return getHasUserRoleInContest(userId, SystemRole.FELLOW.getRoleId());
    }

    @JsonIgnore
    public ContestPhaseWrapper getActivePhase() {
        if (activePhase == null) {
            ContestPhaseWrapper phase = contestClient.getActivePhase(this.getId());
            if (phase == null) {
                return null;
            }
            activePhase = phase;
        }
        return activePhase;
    }

    @JsonIgnore
    public List<UserWrapper> getContestImpactAssessmentFellows() {
        return getMembersWithRole(ContestRole.IAF);
    }

    @JsonIgnore
    public List<UserWrapper> getContestJudges() {
        return getMembersWithRole(ContestRole.JUDGE);
    }

    @JsonIgnore
    public List<UserWrapper> getContestFellows() {
        return getMembersWithRole(ContestRole.FELLOW);
    }

    @JsonIgnore
    public List<UserWrapper> getContestAdvisors() {
        return getMembersWithRole(ContestRole.ADVISOR);
    }

    private List<UserWrapper> getMembersWithRole(ContestRole contestRole) {
        return getContestTeamMembersByRole().entrySet().stream()
                .filter(e -> e.getKey().getContestRole() == contestRole)
                .map(Entry::getValue)
                .findAny().orElse(Collections.emptyList());
    }

    @JsonIgnore
    public long getTotalProposalsCount() {
        Set<ProposalWrapper> proposalList = new HashSet<>();

        List<ContestPhaseWrapper> contestPhases = contestClient.getAllContestPhases(this.getId());
        for (ContestPhaseWrapper contestPhase : contestPhases) {
            List<ProposalWrapper> proposals = StaticProposalContext.getProposalClient()
                    .getActiveProposalsInContestPhase(contestPhase.getId());
            proposalList.addAll(proposals);

        }

        return proposalList.size();
    }

    @JsonIgnore
    public ContestType getContestType() {
        if (contestType == null) {
            contestType = StaticAdminContext.getContestTypeClient().getContestType(this.getContestTypeId());
        }
        return contestType;
    }

    @JsonIgnore
    public String getContestUrl() {
        return this.getContestLinkUrl();
    }

    @JsonIgnore
    public String getResourceArticleUrl() {
        return "/resources/" + this.getContestYear() + "/" + this.getContestUrlName();
    }

    @Override
    @JsonIgnore
    public String getEmailTemplateUrl() {
        if (super.getEmailTemplateUrl()==null || super.getEmailTemplateUrl().isEmpty()) {
            return EMAIL_TEMPLATE_URL;
        } else {
            return super.getEmailTemplateUrl();
        }
    }

    @JsonIgnore
    public long getTotalCommentsCount() {
        int contestComments = StaticCommentContext.getCommentClient()
                .countComments(this.getOrCreateDiscussionGroupId());
        ContestPhaseWrapper phase = contestClient.getActivePhase(this.getId());
        final List<Long> proposalDiscussionThreads =
                contestClient.getProposalDiscussionThreads(phase.getId());
        contestComments += StaticCommentContext.getCommentClient()
                .countComments(proposalDiscussionThreads);

        return contestComments;
    }

    @JsonIgnore
    public long getVotesCount() {
        ContestPhaseWrapper phase = contestClient.getActivePhase(this.getId());

        return StaticProposalContext.getProposalMemberRatingClient()
                .countProposalVotesInContestPhase(phase.getId());
    }

    @JsonIgnore
    public long getCreatedTime(){
        if (this.getCreatedAt() != null) {
            return this.getCreatedAt().getTime();
        }
        if (this.getUpdatedAt() != null) {
            return this.getUpdatedAt().getTime();
        }
        return 0;
    }

    @JsonIgnore
    public ContestPhaseWrapper getLastPhase() {
        ContestPhaseWrapper last = null;
        for (ContestPhaseWrapper ph : getPhases()) {
            if (last == null || (ph.getPhaseReferenceDate() != null
                    && ph.getPhaseReferenceDate().compareTo(last.getPhaseReferenceDate()) > 0)) {
                last = ph;
            }
        }
        return last;
    }

    @JsonIgnore
    public String getWhoName() {
        return getTermNameFromSpace(WHO);
    }

    @JsonIgnore
    public String getWhatName() {
        return getTermNameFromSpace(WHAT);
    }

    @JsonIgnore
    public String getWhereName() {
        return getTermNameFromSpace(WHERE);
    }

    @JsonIgnore
    public String getHowName() {
        return getTermNameFromSpace(HOW);
    }

    @JsonIgnore
    private String getTermNameFromSpace(String space) {
        String ontologyJoinedName = ontologyJoinedNames.get(space);
        if (ontologyJoinedName == null) {
            getTermFromSpace(space);
            ontologyJoinedName = ontologyJoinedNames.get(space);
        }

        if (ontologyJoinedName == null) {
            return "";
        }
        return ontologyJoinedName;
    }

    @JsonIgnore
    public boolean getShowSubContests(){
        // Removed due to COLAB-518; keep the functionality in the code base for the case that we need it again.
        //        return contest.getContestTier() == CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS;
        return false;
    }

    @JsonIgnore
    public boolean getShowParentContest(){
        return false;
        //        return this.getContestTier() == 3 - 1;
    }

    @JsonIgnore
    public List<ContestWrapper> getSubContests() {
        List <ContestWrapper> subContests = contestClient
                .getSubContestsByOntologySpaceId(this.getId(), ONTOLOGY_SPACE_ID_WHERE);
        subContests.sort(Comparator.comparingInt(ContestWrapper::getWeight));
        return subContests;
    }

    @JsonIgnore
    public ContestWrapper getParentContest() {
        final Long focusAreaId = getFocusAreaId();
        if (!getHasFocusArea()) {
            return null;
        }
        List<OntologyTermWrapper> list = ontologyClient.getAllOntologyTermsFromFocusAreaWithOntologySpace(
                ontologyClient.getFocusArea(focusAreaId), ontologyClient.getOntologySpace(ONTOLOGY_SPACE_ID_WHERE));
        List<Long> focusAreaOntologyTermIds = new ArrayList<>();
        for (OntologyTermWrapper ot : list) {
            focusAreaOntologyTermIds.add(ot.getId());
        }

        List<ContestWrapper> contests = contestClient
                .findContestsByTierAndOntologyTermIds(3L, focusAreaOntologyTermIds);
        if (!contests.isEmpty()) {
            return contests.get(0);
        } else {
            return null;
        }
    }

    @JsonIgnore
    public Long getVotingPhasePK() {
        ContestPhaseWrapper lastVotingPhase = null;
        for (ContestPhaseWrapper ph : getPhases()) {
            final IContestPhaseType contestPhaseType = ph.getContestPhaseTypeObject();
            if (contestPhaseType != null && "VOTING".equals(
                    contestPhaseType.getStatus())) {
                lastVotingPhase = ph;
            }
        }
        return lastVotingPhase != null ? lastVotingPhase.getId() : 0;
    }

    @JsonIgnore
    public List<ContestPhaseWrapper> getVisiblePhases() {
        if (visiblePhases == null) {
            visiblePhases = new ArrayList<>();
            visiblePhases.addAll(contestClient
                    .getVisibleContestPhases(this.getId()));
        }
        return visiblePhases;
    }

    @JsonIgnore
    public boolean getHasFocusArea() {
        return getFocusAreaId() != null;
    }

    @JsonIgnore
    public boolean isUserAmongAdvisors(long userId) {
        for (UserWrapper judge : getContestAdvisors()) {
            if (judge.getId() == userId) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public Long getOrCreateDiscussionGroupId() {
        Long discussionGroupId = getDiscussionGroupId();
        if (discussionGroupId == null) {
            ContestType contestType = getContestType();
            IThread thread = new org.xcolab.client.comment.pojo.tables.pojos.Thread();
            thread.setAuthorUserId(getAuthorUserId());
            thread.setTitle(contestType.getContestName() + " discussion");
            thread.setIsQuiet(false);
            thread = StaticCommentContext.getThreadClient().createThread(thread);
            discussionGroupId = thread.getId();
            setDiscussionGroupId(discussionGroupId);
        }
        return discussionGroupId;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return !isNotEmpty();
    }

    @JsonIgnore
    public boolean isNotEmpty() {
        return getTotalProposalsCount() > 0;
    }

    @JsonIgnore
    public boolean isCompatibleWithSchedule(long contestScheduleId) {
        if (isEmpty()) {
            return true;
        }
        final ContestScheduleChangeHelper contestScheduleChangeHelper =
                new ContestScheduleChangeHelper(getId(), contestScheduleId);
        return contestScheduleChangeHelper.isValidChange();
    }

    @JsonIgnore
    public boolean isCompatibleWithSchedulePhases(List<ContestPhaseWrapper> schedulePhases) {
        if (isEmpty()) {
            return true;
        }
        final ContestScheduleChangeHelper contestScheduleChangeHelper =
                new ContestScheduleChangeHelper(getId(), schedulePhases);
        return contestScheduleChangeHelper.isValidChange();
    }

    @JsonIgnore
    public void changeScheduleTo(long contestScheduleId) {
        final ContestScheduleChangeHelper contestScheduleChangeHelper =
                new ContestScheduleChangeHelper(getId(), contestScheduleId);

        if (isEmpty()) {
            contestScheduleChangeHelper.changeScheduleForBlankContest();
        } else {
            contestScheduleChangeHelper.changeScheduleForStartedContest();
        }
        setContestScheduleId(contestScheduleId);
        contestClient.updateContest(this);
    }

    /**
     * Determine if judges are done with proposal
     *
     * @return 0 if judge action is incomplete, 1 judge actions completed
     */
    @JsonIgnore
    public boolean getJudgeStatus() {
        //TODO COLAB-2421: this code does nothing - remove?
//        try {
//            ContestPhase contestPhase = contestClient.getActivePhase(this.getId());
//            for (Proposal proposal : ProposalClient.fromNamespace(serviceNamespace)
//                    .getProposalsInContestPhase(contestPhase.getId())) {
//                Proposal2Phase p2p = IProposalPhaseClient.fromNamespace(serviceNamespace)
//                        .getProposal2PhaseByProposalIdContestPhaseId(proposal.getId(),
//                                contestPhase.getId());
//                /*
//                final Proposal proposalWrapper =
//                        new ProposalWrapper(proposal, proposal.getCurrentVersion(), this,
//                                contestPhase, p2p);
//                if (proposalWrapper.getJudgeStatus() == GenericJudgingStatus.STATUS_ACCEPTED) {
//                    return false;
//                }
//                */
//            }
//        } catch (Exception e) {
//            return false;
//        }
        return true;
    }

    /**
     * Determine if fellows are done with proposal
     *
     * @return 0 if fellow action is incomplete, 1 fellow action completed
     */
    @JsonIgnore
    public boolean getScreeningStatus() {
        //TODO COLAB-2421: this code does nothing - remove?
//        try {
//            ContestPhase contestPhase = contestClient.getActivePhase(this.getId());
//
//            for (Proposal proposal : ProposalClient.fromNamespace(serviceNamespace)
//                    .getProposalsInContestPhase(contestPhase.getId())) {
//                Proposal2Phase p2p = IProposalPhaseClient.fromNamespace(serviceNamespace)
//                        .getProposal2PhaseByProposalIdContestPhaseId(proposal.getId(), contestPhase.getId());
//                /*
//                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), this, contestPhase, p2p)).getScreeningStatus() == GenericJudgingStatus.STATUS_UNKNOWN) {
//                    return false;
//                }*/
//            }
//        } catch (Exception e) {
//            return false;
//        }
        return true;
    }

    @JsonIgnore
    public String getNewProposalLinkUrl() {
        final String portletUrl = getContestType().getContestBaseUrl();
        return String.format("%s/%s/%s/createProposal",
                portletUrl, this.getContestYear(), this.getContestUrlName());
    }

    @JsonIgnore
    public List<ProposalTemplateSectionDefinitionWrapper> getSections() {
        return proposalTemplateClient.getProposalTemplateSectionDefinitionByProposalTemplateId(getProposalTemplateId(),
                        true);
    }

    @JsonIgnore
    public int getMaxVotesPerContest() {
        return ConfigurationAttributeKey.PROPOSALS_MAX_VOTES_PER_CONTEST.get().intValue();
    }

    @JsonIgnore
    public int getMaxVotesPerProposal() {
        return ConfigurationAttributeKey.PROPOSALS_MAX_VOTES_PER_PROPOSAL.get().intValue();
    }

    @JsonIgnore
    public boolean getMemberAgreedToTos(UserWrapper member) {
        return contestClient.getMemberAgreedToTos(getId(), member.getId());
    }

    @JsonIgnore
    public void setMemberAgreedToTos(UserWrapper member, boolean agreed) {
        contestClient.setMemberAgreedToTos(getId(), member.getId(), agreed);
    }

    @JsonIgnore
    public void deleteResourceArticle() {
        setResourceArticleId(0L);
        contestClient.updateContest(this);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
    }
}
