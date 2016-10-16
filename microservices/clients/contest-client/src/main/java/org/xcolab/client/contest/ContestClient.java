package org.xcolab.client.contest;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.ContestPhaseType;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ContestClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource1<Contest, Long> contestResource = new RestResource1<>(contestService,
            "contests", Contest.TYPES);

    private static final RestResource2L<Contest, ContestPhase> visiblePhasesResource =
            new RestResource2L<>(contestResource, "visiblePhases", ContestPhase.TYPES);

    private static final RestResource<ContestPhase, Long> contestPhasesResource = new RestResource1<>(contestService,
            "contestPhases", ContestPhase.TYPES);

    private static final RestResource<ContestPhaseType, Long> contestPhaseTypesResource = new RestResource1<>(contestService,
            "contestPhaseTypes", ContestPhaseType.TYPES);

    private static final RestResource<ContestType, Long> contestTypeResource = new RestResource1<>(contestService,
            "contestTypes", ContestType.TYPES);



    private static final RestResource1<ContestSchedule, Long> contestScheduleResource = new RestResource1<>(contestService,
            "contestSchedules", ContestSchedule.TYPES);




    private static final RestResource1<ContestPhaseRibbonType,Long> contestPhaseRibbonTypeResource = new RestResource1<>(contestService,
            "contestPhaseRibbonTypes", ContestPhaseRibbonType.TYPES);


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
        c.setFocusAreaId(0L);
        c.setContestTier(0L);
        c.setContestLogoId(0L);
        c.setFeatured_(false);
        c.setPlansOpenByDefault(false);
        c.setSponsorLogoId(0L);
        c.setSponsorLink("");
        c.setSponsorText("");
        c.setFlag(0);
        c.setFlagText("");
        c.setFlagTooltip("");
        c.setWeight(0);
        c.setResourcesUrl("");
        c.setUsePermissions(false);
        c.setContestCreationStatus("");
        c.setDefaultModelId(0L);
        c.setOtherModels("");
        c.setPoints(0.0);
        c.setDefaultParentPointType(0L);
        c.setPointDistributionStrategy("");
        c.setEmailTemplateUrl("");
        c.setHideRibbons(false);
        c.setResourceArticleId(0L);
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
                .withCache(CacheKeys.withClass(Contest.class)
                        .withParameter("contestUrlName", contestUrlName)
                        .withParameter("contestYear", contestYear).asList(), CacheRetention.LONG)
                .executeWithResult().getFirstIfExists();
        if (contest == null) {
            throw new ContestNotFoundException(contestUrlName, contestYear);
        }
        return contest;
    }

    public static boolean isContestShared(long contestId) {
        return contestResource.<Contest, Boolean>service(contestId, "isShared", Boolean.class)
                .withCache(CacheKeys.withClass(Contest.class)
                        .withParameter("contestId", contestId)
                        .withParameter("service", "isShared")
                        .build(Boolean.class), CacheRetention.LONG)
                .get();
    }

    public static List<Contest> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        return contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .execute();
    }

    public static List<Contest> findContestsTierLevelAndOntologyTermIds(Long contestTier, List<Long> focusAreaOntologyTerms) {
        return contestResource.list()
                .queryParam("contestTier", contestTier)
                .queryParam("focusAreaOntologyTerms", focusAreaOntologyTerms.toArray())
                .execute();
    }

    public static List<Contest> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return contestResource.service("getContestMatchingOntologyTerms", Contest.TYPES.getTypeReference())
                .queryParam("ontologyTermIds", ontologyTermIds)
                .getList();
    }

    public static List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {
            return contestResource.service(contestId, "getSubContestsByOntologySpaceId", Contest.TYPES.getTypeReference())
                    .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                    .getList();

    }

    public static void autoPromoteProposals(){
        contestPhasesResource.service("autoPromoteProposals", Boolean.class).get();
    }


    public static void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestPhasesResource.service(proposalId, "forcePropomotionOfProposalInContestPhaseId", Boolean.class)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();
        //TODO: NEEDS TO MIGRATE A TON OF THINGS.

    }

    public static List<Contest> getAllContests() {
        return contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("sort", "weight")
                .execute();
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
        return contestScheduleResource.get(id)
                .withCache(CacheKeys.of(ContestSchedule.class, id), CacheRetention.REQUEST)
                .execute();
    }

    public static boolean isContestScheduleUsed(long contestScheduleId) {
        return contestScheduleResource.service(contestScheduleId, "isUsed", Boolean.class)
                .get();
    }

    public static List<ContestSchedule> getAllContestSchedules() {
        return contestScheduleResource.list().execute();
    }


    public static List<ContestPhase> getVisibleContestPhases(Long contestId) {
        return visiblePhasesResource.resolveParent(contestResource.id(contestId))
                .list()
                .withCache(CacheKeys.withClass(ContestPhase.class)
                    .withParameter("contestId", contestId)
                    .withParameter("visible", true).asList(),
                        CacheRetention.MEDIUM)
                .execute();
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
        return contestPhasesResource.list()
                .queryParam("contestPK", contestPK)
                .execute();
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

    public static List<ContestPhase> getTemplatePhasesForContestScheduleId(Long contestScheduleId) {
        return contestPhasesResource.list()
                .queryParam("contestPK", ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }


    public static ContestPhase getContestPhase(Long contestPhaseId) {
        return contestPhasesResource.get(contestPhaseId).execute();
    }

    public static ContestPhase getActivePhase(Long contestId) {
        return contestResource.service(contestId, "activePhase", ContestPhase.class).get();
    }

    public static ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return contestPhaseTypesResource.get(contestPhaseTypeId)
                .withCache(CacheKeys.of(ContestPhaseType.class, contestPhaseTypeId),
                        CacheRetention.MEDIUM)
                .execute();
    }

    public static List<ContestPhaseType> getAllContestPhaseTypes() {
        return contestPhaseTypesResource.list()
                .execute();
    }

    public static String getContestStatusStr(Long contestPhaseId) {
        return null;
    }

    public static ContestType getContestType(long id) {
        return contestTypeResource.get(id)
                .withCache(CacheKeys.of(ContestType.class, id), CacheRetention.RUNTIME)
                .execute();
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


    public static String getContestPhaseName(ContestPhase ck) {
        return getContestPhaseType(ck.getContestPhaseType()).getName();
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


}
