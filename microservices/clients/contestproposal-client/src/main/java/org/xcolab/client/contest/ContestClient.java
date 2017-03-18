package org.xcolab.client.contest;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ContestCollectionCardDto;
import org.xcolab.client.contest.pojo.ContestDiscussion;
import org.xcolab.client.contest.pojo.ContestDiscussionDto;
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
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.Plurality;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContestClient {

    private static final Map<RestService, ContestClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource1<ContestDto, Long> contestResource;
    private final RestResource<ContestTypeDto, Long> contestTypeResource;
    private final RestResource<ContestDiscussionDto, Long> contestDiscussionResource;

    private final RestResource2L<ContestDto, ContestPhaseDto> visiblePhasesResource;
    private final RestResource<ContestPhaseDto, Long> contestPhasesResource;
    private final RestResource<ContestPhaseTypeDto, Long> contestPhaseTypesResource;
    private final RestResource1<ContestPhaseRibbonTypeDto, Long> contestPhaseRibbonTypeResource;

    private final RestResource1<ContestScheduleDto, Long> contestScheduleResource;
    private final RestResource1<ContestCollectionCardDto, Long> contestCollectionCardRestResource;
    private final RestResource<Long, Long> contestYearResource;

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
                new RestResource1<>(this.contestService, "contestCollectionCards", ContestCollectionCardDto.TYPES);
        contestDiscussionResource =
                new RestResource1<>(this.contestService, "contestDiscussions", ContestDiscussionDto.TYPES);

        contestYearResource = new RestResource1<>(
                contestService, "contestyears", new TypeProvider<>(Long.class,
                new ParameterizedTypeReference<List<Long>>() {
                })
        );
    }

    public static ContestClient fromService(RestService contestService) {
        return instances.computeIfAbsent(contestService, ContestClient::new);
    }

    public Contest getContest(long contestId) {
        try {
            return contestResource.get(contestId)
                    //.withCache(CacheName.CONTEST_DETAILS)
                    .executeChecked().toPojo(contestService);
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public Contest createContest(Long contestId, Long userId, String name) {
        Contest c = new Contest();
        c.setContestPK(contestId);
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
        final Contest result =
                contestResource.create(new ContestDto(contest)).execute().toPojo(contestService);
        //TODO: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public boolean deleteContest(long contestId) {
        final Boolean result = contestResource.delete(contestId)
                .execute();
        //TODO: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public List<Contest> getContestsMatchingTier(Long contestTier) {
        return DtoUtil.toPojos(
                contestResource.list().queryParam("contestTier", contestTier).queryParam("limitRecord", Integer.MAX_VALUE).execute(),
                contestService);
    }

    public boolean updateContest(Contest contest) {
        final Boolean result =
                contestResource.update(new ContestDto(contest), contest.getContestPK())
                        .execute();
        //TODO: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public ContestDiscussion createContestDiscussion(long threadId, long contestId, String tab) {
        ContestDiscussion contestDiscussion = new ContestDiscussion(threadId, contestId, tab);
        return contestDiscussionResource.create(new ContestDiscussionDto(contestDiscussion)).execute()
                .toPojo(contestService);
    }

    public ContestDiscussion getContestDiscussion(long contestId, String tab) {
        return contestDiscussionResource.list()
                .queryParam("contestId", contestId)
                .queryParam("tab", tab)
                .executeWithResult()
                .getFirst().toPojo(contestService);
    }

    public Integer getProposalCount(Long contestId) {
        try {
            return contestResource.<Proposal, Integer>service(contestId,
                    "proposalCountForActivePhase", Integer.class)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestId", contestId).asCount(), CacheName.MISC_MEDIUM)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        List<ContestDto> list = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0).toPojo(contestService);
        }
        throw new ContestNotFoundException(contestUrlName, contestYear);
    }

    public boolean isContestShared(long contestId) {
        return contestResource.<Contest, Boolean>service(contestId, "isShared", Boolean.class)
                .withCache(CacheKeys.withClass(Contest.class)
                        .withParameter("contestId", contestId)
                        .withParameter("service", "isShared")
                        .build(Boolean.class), CacheName.CONTEST_DETAILS)
                .get();
    }

    public List<Contest> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        return DtoUtil.toPojos(contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> findContestsByActive(boolean active) {
        return DtoUtil.toPojos(contestResource.list()
                .optionalQueryParam("active", active)
                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> findContestsByName(String contestName, List<Long> ontologyTermIds, List<Long> contestTypeIds) {
        return DtoUtil.toPojos(contestResource
                .service("findContestsByName", ContestDto.TYPES.getTypeReference())
                .queryParam("contestName", contestName)
                .queryParam("ontologyTermIds",  convertListToGetParameter(ontologyTermIds, "ontologyTermIds"))
                .queryParam("contestTypeIds",  convertListToGetParameter(contestTypeIds, "contestTypeIds"))
                .getList(), contestService);
    }

    public List<Contest> findContestsTierLevelAndOntologyTermIds(Long contestTier,
            List<Long> focusAreaOntologyTerms) {
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("contestTier", contestTier)
                .queryParam("focusAreaOntologyTerms", focusAreaOntologyTerms.toArray())
                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    //TODO:Confusing Variable naming
    public List<Contest> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return DtoUtil.toPojos(contestResource
                .service("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaOntologyTerms", ontologyTermIds.toArray())
                .getList(), contestService);
    }

    public List<Long> getContestYears() {
        return contestYearResource.list().execute();
    }


    public Contest getContestByThreadId(Long threadId) {
        return contestResource
                .service("getContestByThreadId", ContestDto.class)
                .queryParam("threadId", threadId)
                .execute().toPojo(contestService);
    }

    public Contest getContestByResourceArticleId(Long resourceArticleId) {
        return contestResource
                .service("getContestByResourceArticleId", ContestDto.class)
                .queryParam("resourceArticleId", resourceArticleId)
                .execute().toPojo(contestService);
    }


    public int getNumberOfAllContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestResource.service("getNumberOfAllContestsInCollectionCard", Integer.class)
                .queryParam("collectionCardId", collectionCardId)
                .queryParam("viewType", viewType)
                .queryParam("onlyFeatured", onlyFeatured)
                .execute();
    }

    public int getNumberOfActiveContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestResource.service("getNumberOfActiveContestsInCollectionCard", Integer.class)
                .queryParam("collectionCardId", collectionCardId)
                .queryParam("viewType", viewType)
                .queryParam("onlyFeatured", onlyFeatured)
                .execute();
    }

    public int getNumberOfPriorContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestResource.service("getNumberOfPriorContestsInCollectionCard", Integer.class)
                .queryParam("collectionCardId", collectionCardId)
                .queryParam("viewType", viewType)
                .queryParam("onlyFeatured", onlyFeatured)
                .execute();
    }

    public boolean updateContestCollectionCard(ContestCollectionCard contestCollectionCard) {
        return contestCollectionCardRestResource.update(new ContestCollectionCardDto(contestCollectionCard),contestCollectionCard.getId_())
                .execute();
    }

    public boolean deleteContestCollectionCard(long id) {
        return contestCollectionCardRestResource.delete(id).execute();
    }

    public ContestCollectionCard createContestCollectionCard(ContestCollectionCard contestCollectionCard) {
        return contestCollectionCardRestResource.create(new ContestCollectionCardDto(contestCollectionCard)).execute().toPojo(contestService);

    }

    public List<Contest> getContestByOntologyTerm(Long ontologyTermId, Boolean getActive) {
        return DtoUtil.toPojos(contestResource
                .service("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaOntologyTerm", ontologyTermId)
                .queryParam("getActive", getActive)
                .getList(), contestService);
    }

    public int getNumberOfContestsByOntologyTerm(Long ontologyTermId) {
        return contestResource.service("getNumberOfContestsByOntologyTerm", Integer.class)
                .queryParam("ontologyTermId", ontologyTermId)
                .execute();
    }

    public List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {
        return DtoUtil.toPojos(contestResource.service(contestId, "getSubContestsByOntologySpaceId",
                ContestDto.TYPES.getTypeReference())
                .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                .getList(), contestService);

    }

    public void autoPromoteProposals() {
        contestPhasesResource.service("autoPromoteProposals", Boolean.class).get();
    }

    public void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestPhasesResource
                .service(contestPhaseId, "forcePromotionOfProposalInContestPhaseId", Boolean.class)
                .queryParam("proposalId", proposalId)
                .put();

    }

    public List<Contest> getAllContests() {
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("sort", "weight")
                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> getAllContestsInYear(Integer contestYear) {
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("contestYear",contestYear)
                .queryParam("sort", "ContestShortName")
                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public Map<Long, String> getModelIdsAndNames(long contestPK) {
        try {
            List<Long> modelIds = getModelIds(contestPK);

            Map<Long, String> ret = new HashMap<>();
            for (Long modelId : modelIds) {
                try {
                    Simulation s = RomaClientUtil.client().getSimulation(modelId);
                    ret.put(s.getId(), s.getName());

                } catch (IOException e) {
                }
            }
            return ret;
        } catch (ContestNotFoundException ignored) {
            return new LinkedHashMap<>();
        }
    }

    public List<Long> getModelIds(long contestPK) throws ContestNotFoundException {
        Contest contest = getContest(contestPK);
        List<Long> modelIds = new ArrayList<>();

        if (StringUtils.isNotBlank(contest.getOtherModels())) {
            modelIds.addAll(IdListUtil.getIdsFromString(contest.getOtherModels()));
        }
        if (!modelIds.contains(contest.getDefaultModelId())) {
            modelIds.add(contest.getDefaultModelId());
        }

        return modelIds;
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
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .queryParam("contestTypeId", contestTypeId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByContestTypeId(Long contestTypeId) {
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("contestTypeId", contestTypeId)
                .queryParam("limitRecord", Integer.MAX_VALUE)
                .execute(), contestService);
    }

    public ContestSchedule createContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.create(new ContestScheduleDto(contestSchedule))
                .execute().toPojo(contestService);
    }

    public boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource
                .update(new ContestScheduleDto(contestSchedule), contestSchedule.getId_())
                .cacheName(CacheName.MISC_REQUEST)
                .execute();
    }

    public ContestSchedule getContestSchedule(long id) {
        try {
            return contestScheduleResource.get(id)
                    .withCache(CacheName.MISC_REQUEST)
                    .execute().toPojo(contestService);
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestScheduleNotFoundException(id);
        }
    }

    public boolean isContestScheduleUsed(long contestScheduleId) {
        return contestScheduleResource.service(contestScheduleId, "isUsed", Boolean.class)
                .get();
    }

    public List<ContestSchedule> getAllContestSchedules() {
        return DtoUtil.toPojos(contestScheduleResource.list().execute(), contestService);
    }

    public boolean deleteContestSchedule(long contestScheduleId) {
        return contestScheduleResource.delete(contestScheduleId)
                .execute();
    }

    public List<ContestPhase> getVisibleContestPhases(Long contestId) {
        return DtoUtil.toPojos(visiblePhasesResource.resolveParent(contestResource.id(contestId))
                .list()
                .withCache(CacheKeys.withClass(ContestPhaseDto.class)
                                .withParameter("contestId", contestId)
                                .withParameter("visible", true).asList(),
                        CacheName.MISC_MEDIUM)
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
        return contestResource.<ContestPhaseDto, ContestPhaseDto>service(contestId, "activePhase", ContestPhaseDto.class)
                .withCache(CacheKeys.withClass(ContestPhaseDto.class)
                        .withParameter("contestId", contestId)
                        .withParameter("active", true).build(), CacheName.MISC_REQUEST)
                .get()
                .toPojo(contestService);
    }

    public ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return contestPhaseTypesResource.get(contestPhaseTypeId)
                .withCache(CacheKeys.of(ContestPhaseTypeDto.class, contestPhaseTypeId),
                        CacheName.MISC_MEDIUM)
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

    public List<ContestPhase> getContestPhasesByType(long contestPhaseTypeId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestPhaseTypeId", contestPhaseTypeId)
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
		return getContestPhase(contestPhaseId).getContestStatusStr();
    }

    public ContestType getContestType(long id) {
        return contestTypeResource.get(id)
                .withCache(CacheName.CONFIGURATION)
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
                .withCache(CacheName.CONFIGURATION)
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
                .withCache(CacheName.CONTEST_LIST)
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
        return ActivitiesClientUtil.isSubscribedToActivity(userId,
                ActivityEntryType.CONTEST.getPrimaryTypeId(), contestPK, 0, "");
    }

    public void subscribeMemberToContest(long contestPK, long userId) {
        ActivitiesClientUtil.addSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    public void unsubscribeMemberFromContest(long contestPK, long userId) {
        ActivitiesClientUtil.deleteSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    public String getProposalNames(List<Long> contestTypeIds, String plurality,
            String conjunction) {
        return getJoinedNameString(contestTypeIds, true, plurality, conjunction);
    }

    public String getContestNames(List<Long> contestTypeIds, String plurality, String conjunction) {
        return getJoinedNameString(contestTypeIds, false, plurality, conjunction);
    }

    private String getJoinedNameString(List<Long> contestTypeIds, boolean isProposal,
            String plurality, String conjuction) {
        String proposalsString;
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Long> iterator = contestTypeIds.iterator();
        int currentWord = 1, totalWords = contestTypeIds.size();
        while (iterator.hasNext()) {
            ContestType contestType = getContestType(iterator.next());
            if (currentWord > 1) {
                if (currentWord == totalWords) {
                    stringBuilder.append(String.format(" %s ", conjuction));
                } else {
                    stringBuilder.append(", ");
                }
            }
            if (isProposal) {
                if (plurality.equals(Plurality.SINGULAR.name())) {
                    stringBuilder.append(contestType.getProposalName());
                } else {
                    stringBuilder.append(contestType.getProposalNamePlural());
                }
            } else {
                if (plurality.equals(Plurality.SINGULAR.name())) {
                    stringBuilder.append(contestType.getContestName());
                } else {
                    stringBuilder.append(contestType.getContestNamePlural());
                }
            }
            currentWord++;
        }
        proposalsString = stringBuilder.toString();

        proposalsString = "Proposals";

        return proposalsString;
    }

    private static String convertListToGetParameter(List<Long> list, String parameterName) {
        if(list.isEmpty()){
            return "";
        }
        String parameterList = "";
        for(int i=0; i<list.size()-2; i++) {
            parameterList += list.get(i) + "&" + parameterName + "=";
        }
        parameterList += list.get(list.size()-1);
        return parameterList;
    }

    public List<ContestCollectionCard> getSubContestCollectionCards(long parentCollectionCardId) {
        return DtoUtil.toPojos(contestCollectionCardRestResource.list()
                .queryParam("parentCollectionCardId", parentCollectionCardId)
                .execute(),contestService);
    }

    public List<ContestCollectionCard> getAllContestCollectionCards() {
        return DtoUtil.toPojos(contestCollectionCardRestResource.list()
                .execute(), contestService);
    }

    public ContestCollectionCard getContestCollectionCard(long id) {
        return contestCollectionCardRestResource.get(id)
                .execute().toPojo(contestService);
    }

    @Override
    public String toString() {
        return "ContestClient[" + contestService + "]";
    }
}
