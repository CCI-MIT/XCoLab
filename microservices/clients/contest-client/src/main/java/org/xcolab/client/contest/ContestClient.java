package org.xcolab.client.contest;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ContestCollectionCardDto;
import org.xcolab.client.contest.pojo.ContestDto;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestScheduleDto;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.ContestTypeDto;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseDto;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonTypeDto;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.contest.pojo.phases.ContestPhaseTypeDto;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContestClient {

    private static final Map<RestService, ContestClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource1<ContestDto, Long> contestResource;
    private final RestResource<ContestTypeDto, Long> contestTypeResource;

    private final RestResource2L<ContestDto, ContestPhaseDto> visiblePhasesResource;
    private final RestResource<ContestPhaseDto, Long> contestPhasesResource;
    private final RestResource<ContestPhaseTypeDto, Long> contestPhaseTypesResource;
    private final RestResource1<ContestPhaseRibbonTypeDto, Long> contestPhaseRibbonTypeResource;

    private final RestResource1<ContestScheduleDto, Long> contestScheduleResource;
    private final RestResource1<ContestCollectionCardDto, Long> contestCollectionCardRestResource;

    private ContestClient(RestService contestService) {
        this.contestService = contestService;
        contestPhaseRibbonTypeResource = new RestResource1<>(this.contestService,
                "contestPhaseRibbonTypes", ContestPhaseRibbonTypeDto.TYPES);
        contestScheduleResource = new RestResource1<>(this.contestService,
                "contestSchedules", ContestScheduleDto.TYPES);
        contestTypeResource = new RestResource1<>(this.contestService,
                "contestTypes", ContestTypeDto.TYPES);
        contestPhaseTypesResource = new RestResource1<>(this.contestService,
                "contestPhaseTypes", ContestPhaseTypeDto.TYPES);
        contestPhasesResource = new RestResource1<>(this.contestService,
                "contestPhases", ContestPhaseDto.TYPES);
        contestResource = new RestResource1<>(this.contestService,
                "contests", ContestDto.TYPES);
        visiblePhasesResource = new RestResource2L<>(
                contestResource, "visiblePhases", ContestPhaseDto.TYPES);
        contestCollectionCardRestResource =
                new RestResource1<>(contestService, "contestCollectionCards", ContestCollectionCardDto.TYPES);
    }

    public static ContestClient fromService(RestService contestService) {
        ContestClient client = instances.get(contestService);
        if (client == null) {
            client = new ContestClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }

    public Contest getContest(long contestId) throws ContestNotFoundException {
        try {
            return contestResource.get(contestId)
                    .withCache(CacheKeys.of(ContestDto.class, contestId), CacheRetention.REQUEST)
                    .executeChecked().toPojo(contestService);
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public Contest getContestByContestUrlNameContestYear(String contestUrlName, Long contestYear) {
        List<ContestDto> list = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0).toPojo(contestService);
        }
        return null;
    }

    public Contest createContest(Long userId, String name) {
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

    public Contest createContest(Contest contest) {
        return contestResource.create(new ContestDto(contest)).execute().toPojo(contestService);
    }

    public List<Contest> getContestsMatchingTier(Long contestTier) {
        return DtoUtil.toPojos(
                contestResource.list().queryParam("contestTier", contestTier).execute(),
                contestService);
    }

    public boolean updateContest(Contest contest) {
        return contestResource.update(new ContestDto(contest), contest.getContestPK())
                .execute();
    }

    public Integer getProposalCount(Long contestId) {
        try {
            return contestResource.<Proposal, Integer>service(contestId,
                    "proposalCountForActivePhase", Integer.class)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestId", contestId).asCount(), CacheRetention.MEDIUM)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        final Contest contest = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .withCache(CacheKeys.withClass(ContestDto.class)
                        .withParameter("contestUrlName", contestUrlName)
                        .withParameter("contestYear", contestYear).asList(), CacheRetention.LONG)
                .executeWithResult().getFirstIfExists().toPojo(contestService);
        if (contest == null) {
            throw new ContestNotFoundException(contestUrlName, contestYear);
        }
        return contest;
    }

    public boolean isContestShared(long contestId) {
        return contestResource.<Contest, Boolean>service(contestId, "isShared", Boolean.class)
                .withCache(CacheKeys.withClass(Contest.class)
                        .withParameter("contestId", contestId)
                        .withParameter("service", "isShared")
                        .build(Boolean.class), CacheRetention.LONG)
                .get();
    }

    public List<Contest> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        return DtoUtil.toPojos(contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .execute(), contestService);
    }

    public List<Contest> findContestsTierLevelAndOntologyTermIds(Long contestTier,
            List<Long> focusAreaOntologyTerms) {
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("contestTier", contestTier)
                .queryParam("focusAreaOntologyTerms", focusAreaOntologyTerms.toArray())
                .execute(), contestService);
    }

    public List<Contest> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return DtoUtil.toPojos(contestResource
                .service("getContestMatchingOntologyTerms", ContestDto.TYPES.getTypeReference())
                .queryParam("ontologyTermIds", ontologyTermIds)
                .getList(), contestService);
    }

    public int getNumberOfContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return contestResource.service("getNumberOfContestMatchingOntologyTerms", Integer.class)
                .queryParam("ontologyTermIds", ontologyTermIds)
                .execute();
    }

    public List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {
        return DtoUtil.toPojos(contestResource.service(contestId, "getSubContestsByOntologySpaceId",
                ContestDto.TYPES.getTypeReference())
                .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                .getList(), contestService);

    }

    public void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestPhasesResource
                .service(proposalId, "forcePropomotionOfProposalInContestPhaseId", Boolean.class)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();
        //TODO: NEEDS TO MIGRATE A TON OF THINGS.

    }

    public List<Contest> getAllContests() {
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("sort", "weight")
                .execute(), contestService);
    }

    public List<Contest> getContestsByPlanTemplateId(Long planTemplateId) {
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("planTemplateId", planTemplateId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByContestScheduleId(Long contestScheduleId) {
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByActivePrivate(boolean contestActive, boolean contestPrivate) {
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .execute(), contestService);
    }


    public List<Contest> getContestsByActivePrivateType(boolean contestActive,
            boolean contestPrivate, Long contestTypeId) {
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .queryParam("contestTypeId", contestTypeId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByContestTypeId(Long contestTypeId) {
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("contestTypeId", contestTypeId)
                .execute(), contestService);
    }

    public ContestSchedule createContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.create(new ContestScheduleDto(contestSchedule))
                .execute().toPojo(contestService);
    }

    public boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource
                .update(new ContestScheduleDto(contestSchedule), contestSchedule.getId_())
                .execute();
    }

    public ContestSchedule getContestSchedule(long id) {
        return contestScheduleResource.get(id)
                .withCache(CacheKeys.of(ContestScheduleDto.class, id), CacheRetention.REQUEST)
                .execute().toPojo(contestService);
    }

    public boolean isContestScheduleUsed(long contestScheduleId) {
        return contestScheduleResource.service(contestScheduleId, "isUsed", Boolean.class)
                .get();
    }

    public List<ContestSchedule> getAllContestSchedules() {
        return DtoUtil.toPojos(contestScheduleResource.list().execute(), contestService);
    }


    public List<ContestPhase> getVisibleContestPhases(Long contestId) {
        return DtoUtil.toPojos(visiblePhasesResource.resolveParent(contestResource.id(contestId))
                .list()
                .withCache(CacheKeys.withClass(ContestPhaseDto.class)
                                .withParameter("contestId", contestId)
                                .withParameter("visible", true).asList(),
                        CacheRetention.MEDIUM)
                .execute(), contestService);
    }

    public Integer getPointsAccessibleForActivePhaseOfContest(Contest contest) {
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

    public ContestPhase getActivePhase(Long contestId) {
        return contestResource.service(contestId, "activePhase", ContestPhase.class).get();
    }

    public ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return contestPhaseTypesResource.get(contestPhaseTypeId)
                .withCache(CacheKeys.of(ContestPhaseTypeDto.class, contestPhaseTypeId),
                        CacheRetention.MEDIUM)
                .execute().toPojo(contestService);
    }

    public void deleteContestPhase(Long contestPhasePK) {
        contestPhasesResource.delete(contestPhasePK).execute();
    }

    public boolean updateContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource
                .update(new ContestPhaseDto(contestPhase), contestPhase.getContestPhasePK())
                .execute();
    }

    public ContestPhase createContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.create(new ContestPhaseDto(contestPhase))
                .execute().toPojo(contestService);
    }

    public List<ContestPhase> getAllContestPhases(Long contestPK) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestPK", contestPK)
                .execute(), contestService);
    }

    public List<ContestPhase> getPhasesForContestScheduleId(Long contestScheduleId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), contestService);
    }

    public List<ContestPhase> getPhasesForContestScheduleIdAndContest(Long contestScheduleId,
            Long contestPK) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestPK", contestPK)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), contestService);
    }

    public List<ContestPhase> getTemplatePhasesForContestScheduleId(Long contestScheduleId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestPK", ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), contestService);
    }

    public ContestPhase getContestPhase(Long contestPhaseId) {
        return contestPhasesResource.get(contestPhaseId)
                .execute().toPojo(contestService);
    }

    public List<ContestPhaseType> getAllContestPhaseTypes() {
        return DtoUtil.toPojos(contestPhaseTypesResource.list()
                .execute(), contestService);
    }

    public String getContestStatusStr(Long contestPhaseId) {
        return null;
    }

    public ContestType getContestType(long id) {
        return contestTypeResource.get(id)
                .withCache(CacheKeys.of(ContestTypeDto.class, id), CacheRetention.RUNTIME)
                .execute().toPojo(contestService);
    }

    public List<ContestType> getActiveContestTypes() {
        final List<ContestType> contestTypes = getAllContestTypes();
        List<ContestType> activeContestTypes = new ArrayList<>();
        for (ContestType contestType : contestTypes) {
            if (countContestsByContestType(contestType.getId_()) > 0) {
                activeContestTypes.add(contestType);
            }
        }
        return activeContestTypes;
    }

    public List<ContestType> getAllContestTypes() {
        return DtoUtil.toPojos(contestTypeResource.list()
                .withCache(CacheKeys.withClass(ContestTypeDto.class).asList(), CacheRetention.LONG)
                .execute(), contestService);
    }

    public Integer countContestsByContestType(Long contestTypeId) {
        return contestResource.service("countByContestType", Integer.class)
                .queryParam("contestTypeId", contestTypeId)
                .get();
    }

    public List<Contest> getContestsByContestType(Long contestTypeId) {
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("contestTypeId", contestTypeId)
                .execute(), contestService);
    }


    public String getContestPhaseName(ContestPhase ck) {
        return getContestPhaseType(ck.getContestPhaseType()).getName();
    }

    public ContestPhaseRibbonType getContestPhaseRibbonType(long id_) {
        return contestPhaseRibbonTypeResource.get(id_)
                .execute().toPojo(contestService);
    }

    public List<ContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return DtoUtil.toPojos(contestPhaseRibbonTypeResource.list()
                .execute(), contestService);
    }

    public boolean isMemberSubscribedToContest(long contestPK, long userId) {
        return ActivitiesClient.isSubscribedToActivity(userId,
                ActivityEntryType.CONTEST.getPrimaryTypeId(), contestPK, 0, "");
    }

    public void subscribeMemberToContest(long contestPK, long userId) {
        ActivitiesClient.addSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    public void unsubscribeMemberFromContest(long contestPK, long userId) {
        ActivitiesClient.deleteSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    public List<ContestCollectionCard> getSubContestCollectionCards(long parentCollectionCardId) {
        return DtoUtil.toPojos(contestCollectionCardRestResource.list()
                .queryParam("parentCollectionCardId", parentCollectionCardId)
                .execute(),contestService);
    }

    public List<ContestCollectionCard> getSubContestCollectionCards() {
        return DtoUtil.toPojos(contestCollectionCardRestResource.list()
                .execute(), contestService);
    }

    public ContestCollectionCard getContestCollectionCard(long id) {
        return contestCollectionCardRestResource.get(id)
                .execute().toPojo(contestService);
    }
}
