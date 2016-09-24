package org.xcolab.client.contest;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.ContestPhaseType;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestTeamMember;
import org.xcolab.client.contest.pojo.ContestTeamMemberRole;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.ImpactIteration;
import org.xcolab.client.contest.pojo.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.ImpactTemplateSeries;
import org.xcolab.client.contest.pojo.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.PlanTemplate;
import org.xcolab.client.contest.pojo.PlanTemplateSection;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource<Contest> contestResource = new RestResource<>(contestService,
            "contests", Contest.TYPES);

    private static final RestResource<ContestPhase> contestPhasesResource = new RestResource<>(contestService,
            "contestPhases", ContestPhase.TYPES);

    private static final RestResource<ContestPhaseType> contestPhaseTypesResource = new RestResource<>(contestService,
            "contestPhaseTypes", ContestPhaseType.TYPES);

    private static final RestResource<ContestType> contestTypeResource = new RestResource<>(contestService,
            "contestTypes", ContestType.TYPES);

    private static final RestResource<ContestTeamMember> contestTeamMemberResource = new RestResource<>(contestService,
            "contestTeamMembers", ContestTeamMember.TYPES);

    private static final RestResource<ContestTeamMemberRole> contestTeamMemberRoleResource = new RestResource<>(contestService,
            "contestTeamMemberRoles", ContestTeamMemberRole.TYPES);

    private static final RestResource<ContestSchedule> contestScheduleResource = new RestResource<>(contestService,
            "contestSchedules", ContestSchedule.TYPES);

    private static final RestResource<ImpactIteration> impactIterationResource = new RestResource<>(contestService,
            "impactIterations", ImpactIteration.TYPES);

    private static final RestResource<ImpactTemplateSeries> impactTemplateSeriesResource = new RestResource<>(contestService,
            "impactTemplateSeries", ImpactTemplateSeries.TYPES);

    private static final RestResource<ContestPhaseRibbonType> contestPhaseRibbonTypeResource = new RestResource<>(contestService,
            "contestPhaseRibbonTypes", ContestPhaseRibbonType.TYPES);

    private static final RestResource<ImpactTemplateMaxFocusArea> impactTemplateMaxFocusAreaResource = new RestResource<>(contestService,
            "impactTemplateMaxFocusAreas", ImpactTemplateMaxFocusArea.TYPES);

    private static final RestResource<ImpactTemplateFocusAreaList> impactTemplateFocusAreaListResource = new RestResource<>(contestService,
            "impactTemplateFocusAreaLists", ImpactTemplateFocusAreaList.TYPES);

    private static final RestResource<PlanTemplate> planTemplateResource = new RestResource<>(contestService,
            "planTemplates", PlanTemplate.TYPES);

    private static final RestResource<PlanSectionDefinition> planSectionDefinitionResource = new RestResource<>(contestService,
            "planSectionDefinition", PlanSectionDefinition.TYPES);


    private static final RestResource<PlanTemplateSection> planTemplateSectionResource = new RestResource<>(contestService,
            "planTemplateSections", PlanTemplateSection.TYPES);

    public static Contest getContest(long contestId) throws ContestNotFoundException {
        try {
            return contestResource.get(contestId)
                    .withCache(CacheKeys.of(Contest.class, contestId), CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public static Contest getContestByContestUrlNameContestYear(String contestUrlName, Long contestYear) {
        List<Contest> list = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .execute();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static Contest createContest(Long userId, String name) {
        Contest c = new Contest();
        c.setAuthorId(userId);
        c.setContestName(name);
        c.setContestShortName(name);
        c.setContestUrlName(c.generateContestUrlName());
        c.setContestDescription("");
        c.setContestModelDescription("");
        c.setContestPositionsDescription("");
        c.setContestActive(false);
        c.setProposalCreationTemplateString("");
        c.setProposalVoteTemplateString("");
        c.setProposalVoteConfirmationTemplateString("");
        c.setVoteQuestionTemplateString("");
        c.setVoteTemplateString("");
        c.setFocusAreaId(0l);
        c.setContestTier(0l);
        c.setContestLogoId(0l);
        c.setFeatured_(false);
        c.setPlansOpenByDefault(false);
        c.setSponsorLogoId(0l);
        c.setSponsorLink("");
        c.setSponsorText("");
        c.setFlag(0);
        c.setFlagText("");
        c.setFlagTooltip("");
        c.setWeight(0);
        c.setResourcesUrl("");
        c.setUsePermissions(false);
        c.setContestCreationStatus("");
        c.setDefaultModelId(0l);
        c.setOtherModels("");
        c.setPoints(0.0);
        c.setDefaultParentPointType(0l);
        c.setPointDistributionStrategy("");
        c.setEmailTemplateUrl("");
        c.setHideRibbons(false);
        c.setResourceArticleId(0l);
        return createContest(c);
    }

    public static Contest createContest(Contest contest) {
        return contestResource.create(contest).execute();
    }

    public static List<Contest> getContestsMatchingTier(Long contestTier) {
        return contestResource.list().queryParam("contestTier", contestTier).execute();
    }

    public static boolean updateContest(Contest contest) {
        return contestResource.update(contest, contest.getContestPK())
                .execute();
    }

    public static Integer getProposalCount(Long contestId) {
        try {
            return contestResource.<Proposal, Integer>service(contestId, "proposalCountForActivePhase", Integer.class)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestId", contestId).asCount(), CacheRetention.MEDIUM)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        final Contest contest = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .executeWithResult().getFirstIfExists();
        if (contest == null) {
            throw new ContestNotFoundException(contestUrlName, contestYear);
        }
        return contest;
    }

    public static List<Contest> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        return contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .execute();
    }

    public static List<Contest> findContestsTierLevelAndOntologyTermIds(Long contestTier, List<Long> focusAreaOntologyTerms) {
        return contestResource.list()
                .optionalQueryParam("contestTier", contestTier)
                .optionalQueryParam("focusAreaOntologyTerms", focusAreaOntologyTerms)
                .execute();
    }

    public static List<Contest> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return contestResource.service("getContestMatchingOntologyTerms", List.class)
                .queryParam("ontologyTermIds", ontologyTermIds)
                .get();
    }

    public static List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {

        try {
            return contestResource.service(contestId, "getSubContestsByOntologySpaceId", List.class)
                    .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestPhasesResource.service(proposalId, "forcePropomotionOfProposalInContestPhaseId", Boolean.class)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();
        //TODO: NEEDS TO MIGRATE A TON OF THINGS.

    }

    public static List<Contest> getAllContests() {
        return contestResource.list().execute();
    }

    public static List<Contest> getContestsByPlanTemplateId(Long planTemplateId) {
        return contestResource
                .list()
                .queryParam("planTemplateId", planTemplateId)
                .execute();
    }

    public static List<Contest> getContestsByContestScheduleId(Long contestScheduleId) {
        return contestResource
                .list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public static List<Contest> getContestsByActivePrivate(boolean contestActive, boolean contestPrivate) {
        return contestResource
                .list()
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .execute();
    }


    public static List<Contest> getContestsByActivePrivateType(boolean contestActive, boolean contestPrivate, Long contestTypeId) {
        return contestResource
                .list()
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .queryParam("contestTypeId", contestTypeId)
                .execute();
    }

    public static List<Contest> getContestsByContestTypeId(Long contestTypeId) {
        return contestResource
                .list()
                .queryParam("contestTypeId", contestTypeId)
                .execute();
    }

    public static ContestSchedule createContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.create(contestSchedule).execute();
    }

    public static boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.update(contestSchedule, contestSchedule.getId_())
                .execute();
    }

    public static ContestSchedule getContestSchedule(long id) {
        try {
            return contestScheduleResource.get(id)
                    .withCache(CacheKeys.of(ContestSchedule.class, id), CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static List<ContestSchedule> getAllContestSchedules() {
        return contestScheduleResource.list().execute();
    }


    public static List<ContestPhase> getVisibleContestPhases(Long contestPK) {
        List<ContestPhase> allPhases = getAllContestPhases(contestPK);

        List<ContestPhase> visiblePhases = new ArrayList<>();
        for (ContestPhase phase : allPhases) {
            ContestPhaseType phaseType = getContestPhaseType(phase.getContestPhaseType());
            if (!phaseType.getInvisible()) {
                visiblePhases.add(phase);
            }
        }

        return visiblePhases;
    }

    public static Integer getPointsAccessibleForActivePhaseOfContest(Contest contest) {
        //check if the phase, the contest is currently in, allows for editing
        ContestPhase activePhase = getActivePhase(contest.getContestPK());
        if (activePhase != null) {
            ContestPhaseType cpType = getContestPhaseType(activePhase.getContestPhaseType());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return null;
    }

    public static void deleteContestPhase(Long contestPhasePK) {
        contestPhasesResource.delete(contestPhasePK).execute();
    }

    public static boolean updateContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.update(contestPhase, contestPhase.getContestPhasePK())
                .execute();
    }

    public static ContestPhase createContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.create(contestPhase).execute();
    }

    public static List<ContestPhase> getAllContestPhases(Long contestPK) {
        return contestPhasesResource.list().queryParam("contestPK", contestPK).execute();
    }

    public static List<ContestPhase> getPhasesForContestScheduleId(Long contestScheduleId) {
        return contestPhasesResource.list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public static List<ContestPhase> getPhasesForContestScheduleIdAndContest(Long contestScheduleId, Long contestPK) {
        return contestPhasesResource.list()
                .queryParam("contestPK", contestPK)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }


    public static ContestPhase getContestPhase(Long contestPhaseId) {
        try {
            return contestPhasesResource.get(contestPhaseId).executeChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }

    public static ContestPhase getActivePhase(Long contestId) {
        try {
            return contestResource.service(contestId, "activePhase", ContestPhase.class).getChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }


    public static ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        try {
            return contestPhaseTypesResource.get(contestPhaseTypeId).executeChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }

    public static List<ContestPhaseType> getAllContestPhaseTypes() {
        return contestPhaseTypesResource.list()
                .execute();
    }

    public static String getContestStatusStr(Long contestPhaseId) {
        return null;
    }

    public static ContestType getContestType(long id) {
        try {
            return contestTypeResource.get(id)
                    .withCache(CacheKeys.of(ContestType.class, id), CacheRetention.RUNTIME)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static List<ContestType> getAllContestTypes() {
        return contestTypeResource.list()
                .withCache(CacheKeys.withClass(ContestType.class).asList(), CacheRetention.LONG)
                .execute();
    }

    public static List<ContestType> getActiveContestTypes() {
        final List<ContestType> contestTypes = getAllContestTypes();
        List<ContestType> activeContestTypes = new ArrayList<>();
        for (ContestType contestType : contestTypes) {
            if (countContestsByContestType(contestType.getId_()) > 0) {
                activeContestTypes.add(contestType);
            }
        }
        return activeContestTypes;
    }

    public static Integer countContestsByContestType(Long contestTypeId) {
        return contestResource.service("countByContestType", Integer.class)
                .queryParam("contestTypeId", contestTypeId)
                .get();
    }

    public static List<Contest> getContestsByContestType(Long contestTypeId) {
        return contestResource.list()
                .queryParam("contestTypeId", contestTypeId)
                .execute();
    }


    public static List<Long> getRoleForContestTeam(Long contestId, Long roleId) {
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        List<Long> members = teamRoleToUsersMap.get(roleId);
        if (members == null) {
            return new ArrayList<>();
        } else {
            return members;
        }
    }

    public static List<Long> getAdvisorsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.ADVISOR.getRoleId());

    }

    public static List<Long> getJudgesForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.JUDGE.getRoleId());
    }

    public static List<Long> getFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.FELLOW.getRoleId());
    }

    public static List<Long> getContestManagersForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.CONTEST_MANAGER.getRoleId());

    }

    public static Map<Long, List<Long>> getContestTeamMembersByRole(Long contestId) {
        Map<Long, List<Long>> teamRoleToUsersMap = new TreeMap<>();
        for (ContestTeamMember ctm : getTeamMembers(contestId)) {
            List<Long> roleUsers = teamRoleToUsersMap.get(ctm.getRoleId());

            if (roleUsers == null) {
                roleUsers = new ArrayList<>();
                teamRoleToUsersMap.put(ctm.getRoleId(), roleUsers);
            }

            roleUsers.add(ctm.getUserId());

        }
        return teamRoleToUsersMap;
    }

    public static List<ContestTeamMember> getTeamMembers(Long contestId) {
        return contestTeamMemberResource.list()
                .optionalQueryParam("contestId", contestId)
                .execute();
    }

    public static ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        return contestTeamMemberResource.create(contestTeamMember).execute();
    }

    public static void deleteContestTeamMember(Long contestTeamMemberId) {
        contestTeamMemberResource.delete(contestTeamMemberId).execute();
    }

    public static ContestTeamMemberRole getContestTeamMemberRole(long id) {
        try {
            return contestTeamMemberRoleResource.get(id)
                    .withCache(CacheKeys.of(ContestTeamMemberRole.class, id), CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public static String getContestPhaseName(ContestPhase ck) {
        return getContestPhaseType(ck.getContestPhaseType()).getName();
    }

    public static List<ImpactIteration> getContestImpactIterations(Contest contest) {
        ImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return getContestImpactIterations(impactSeries.getIterationId());
    }

    public static List<ImpactIteration> getContestImpactIterations(Long iterationId) {
        return impactIterationResource.list()
                .optionalQueryParam("iterationId", iterationId)
                .execute();
    }

    public static ImpactTemplateSeries getContestImpactTemplateSeries(Contest contest) {

        PlanTemplate planTemplate = getPlanTemplate(contest.getPlanTemplateId());
        return getImpactTemplateSeries(planTemplate.getImpactSeriesTemplateId());

    }

    public static ImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return impactTemplateSeriesResource.get(seriesId)
                .execute();
    }

    public static ContestPhaseRibbonType getContestPhaseRibbonType(long id_) {
        return contestPhaseRibbonTypeResource.get(id_)
                .execute();
    }

    public static List<ContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return contestPhaseRibbonTypeResource.list()
                .execute();
    }

    public static boolean isMemberSubscribedToContest(long contestPK, long userId) {
        return ActivitiesClient.isSubscribedToActivity(userId, ActivityEntryType.CONTEST.getPrimaryTypeId(), contestPK, 0, "");
    }

    public static void subscribeMemberToContest(long contestPK, long userId) {
        ActivitiesClient.addSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    public static void unsubscribeMemberFromContest(long contestPK, long userId) {
        ActivitiesClient.deleteSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    public static ImpactTemplateFocusAreaList getContestImpactFocusAreaList(Contest contest) {
        PlanTemplate planTemplate = getPlanTemplate(contest.getPlanTemplateId());
        return getImpactTemplateFocusAreaList(planTemplate.getFocusAreaListTemplateId());
    }

    public static ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(long focusAreaListId) {
        return impactTemplateFocusAreaListResource.get(focusAreaListId)
                .execute();
    }

    public static List<ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(Contest contest) {
        ImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return getImpactTemplateMaxFocusArea(focusAreaList.getFocusAreaListId());
    }

    public static List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return impactTemplateMaxFocusAreaResource.list()
                .optionalQueryParam("focusAreaListId", focusAreaListId)
                .execute();
    }

    public static PlanTemplate getPlanTemplate(long Id_) {
        return planTemplateResource.get(Id_)
                .execute();
    }

    public static List<PlanTemplate> getPlanTemplates() {
        return planTemplateResource.list()
                .execute();
    }


    public static PlanTemplate createPlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.create(planTemplate).execute();
    }


    public static boolean updatePlanTemplate(PlanTemplate planTemplate) {
        return planTemplateResource.update(planTemplate, planTemplate.getId_())
                .execute();
    }


    public static PlanSectionDefinition getPlanSectionDefinition(long Id_) {
        return planSectionDefinitionResource.get(Id_)
                .execute();


    }

    public static boolean updatePlanSectionDefinition(PlanSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource.update(planSectionDefinition, planSectionDefinition.getId_())
                .execute();
    }


    public static PlanSectionDefinition createPlanSectionDefinition(PlanSectionDefinition planSectionDefinition) {
        return planSectionDefinitionResource.create(planSectionDefinition).execute();
    }

    public static List<PlanSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(Long planTemplateId, Boolean weight) {

        return planSectionDefinitionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute();
    }

    public static Boolean deletePlanSectionDefinition(Long id_) {
        return planSectionDefinitionResource.delete(id_).execute();
    }


    public static Boolean deletePlanTemplateSection(Long planTemplateId, Long planSectionDefinitionId) {
        return planTemplateSectionResource.service("deletePlanTemplateSection", Boolean.class)
                .queryParam("planTemplateId", planTemplateId)
                .queryParam("planSectionDefinitionId", planSectionDefinitionId)
                .delete();
    }


    public static List<PlanTemplateSection> getPlanTemplateSectionByPlanTemplateId(Long planTemplateId) {
        return planTemplateSectionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .execute();
    }

    public static boolean updatePlanTemplateSection(PlanTemplateSection planTemplateSection) {
        return planTemplateSectionResource.update(planTemplateSection, planTemplateSection.getPlanTemplateId())
                .execute();
    }


    public static List<PlanTemplateSection> getPlanTemplateSectionByPlanSectionDefinitionId(Long planSectionId) {
        return planTemplateSectionResource.list()
                .optionalQueryParam("planSectionId", planSectionId)
                .execute();
    }


}
