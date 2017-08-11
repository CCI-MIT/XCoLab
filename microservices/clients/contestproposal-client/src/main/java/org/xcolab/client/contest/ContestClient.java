package org.xcolab.client.contest;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.exceptions.ContestPhaseNotFoundException;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ContestCollectionCardDto;
import org.xcolab.client.contest.pojo.ContestDiscussion;
import org.xcolab.client.contest.pojo.ContestDiscussionDto;
import org.xcolab.client.contest.pojo.ContestDto;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestScheduleDto;
import org.xcolab.client.contest.pojo.ContestTranslation;
import org.xcolab.client.contest.pojo.ContestTranslationDto;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseDto;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonTypeDto;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.contest.pojo.phases.ContestPhaseTypeDto;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContestClient {

    private static final Map<RestService, ContestClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource1<ContestDto, Long> contestResource;
    private final RestResource2<ContestDto, Long, ContestTranslationDto, String> contestTranslationResource;
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
        contestTranslationResource = new RestResource2<>(contestResource,
                "translations", ContestTranslationDto.TYPES);
    }

    public static ContestClient fromService(RestService contestService) {
        return instances.computeIfAbsent(contestService, ContestClient::new);
    }

    public Contest getContest(long contestId) {
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestId, lang);
    }

    public Contest getContest(long contestId, String lang) {
        try {
            return contestResource.get(contestId)
                    .optionalQueryParam("lang", lang)
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
        c.setFocusAreaId(null);
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
                contestResource.list().queryParam("contestTiers", contestTier).queryParam("limitRecord", Integer.MAX_VALUE).execute(),
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
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestUrlName, contestYear, lang);
    }

    public Contest getContest(String contestUrlName, long contestYear, String lang)
            throws ContestNotFoundException {
        List<ContestDto> list = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .optionalQueryParam("lang", lang)
//                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0).toPojo(contestService);
        }
        throw new ContestNotFoundException(contestUrlName, contestYear);
    }

    public List<ContestTranslation> getTranslationsForContestId(long contestId) {
        final List<ContestTranslationDto> dtos =
                contestTranslationResource.resolveParent(contestResource.id(contestId))
                        .list()
                        .queryParam("contestId", contestId)
                        .execute();
        return DtoUtil.toPojos(dtos, contestService);
    }

    public boolean saveTranslation(ContestTranslation contestTranslation) {
        return contestTranslationResource
                .resolveParent(contestResource.id(contestTranslation.getContestId()))
                .update(new ContestTranslationDto(contestTranslation), contestTranslation.getLang())
                .execute();
    }

    public boolean isContestShared(long contestId) {
        return contestResource.<Contest, Boolean>service(contestId, "isShared", Boolean.class)
                .withCache(CacheKeys.withClass(Contest.class)
                        .withParameter("contestId", contestId)
                        .withParameter("service", "isShared")
                        .build(Boolean.class), CacheName.CONTEST_DETAILS)
                .get();
    }


    public boolean isContestNameYearUnique(String contestShortName, String year,Long currentContestId) {

        return contestResource.service("isContestNameYearUnique", Boolean.class)
                .queryParam("contestShortName", contestShortName)
                .queryParam("year",year)
                .queryParam("currentContestId",currentContestId)
                .execute();
    }
    public List<Contest> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .queryParam("lang", lang)
//                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> findContestsByActive(boolean active) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("lang", lang)
                .optionalQueryParam("active", active)
//                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> findPublicContests(String contestName,
            List<Long> ontologyTermIds, List<Long> contestTypeIds, List<Long> contestTiers) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .optionalQueryParam("searchTerm", contestName)
                .optionalQueryParam("ontologyTermIds",  ontologyTermIds)
                .optionalQueryParam("contestTypeIds",  contestTypeIds)
                .optionalQueryParam("contestTiers",  contestTiers)
                .queryParam("contestPrivate", false)
//                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> findContestsByTierAndOntologyTermIds(Long contestTier,
            List<Long> focusAreaOntologyTerms) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("lang", lang)
                .queryParam("contestTiers", contestTier)
                .queryParam("focusAreaIds", focusAreaOntologyTerms.toArray())
//                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    //TODO:Confusing Variable naming
    public List<Contest> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return DtoUtil.toPojos(contestResource
                .service("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaIds", ontologyTermIds.toArray())
                .getList(), contestService);
    }

    public List<Long> getContestYears() {
        return contestYearResource.list().execute();
    }


    public Contest getContestByThreadId(Long threadId) {
        try {
            return contestResource.service("getContestByThreadId", ContestDto.class)
                    .queryParam("threadId", threadId).execute().toPojo(contestService);
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestNotFoundException("No contest with threadId = " + threadId);
        }
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

    public int autoPromoteProposals() {
        return contestPhasesResource.service("autoPromoteProposals", Integer.class).get();
    }

    public void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestPhasesResource
                .service(contestPhaseId, "forcePromotionOfProposalInContestPhaseId", Boolean.class)
                .queryParam("proposalId", proposalId)
                .put();

    }

    public List<Contest> getAllContests() {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("sort", "weight")
                .withCache(CacheName.CONTEST_LIST)
                .execute(), contestService);
    }

    public List<Contest> getAllContestsInYear(Integer contestYear) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
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
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("planTemplateId", planTemplateId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByContestScheduleId(Long contestScheduleId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByActivePrivate(boolean contestActive, boolean contestPrivate) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .execute(), contestService);
    }


    public List<Contest> getContestsByActivePrivateType(boolean contestActive,
            boolean contestPrivate, Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .queryParam("contestTypeIds", contestTypeId)
                .execute(), contestService);
    }

    public List<Contest> getContestsByContestTypeId(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("contestTypeIds", contestTypeId)
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
                        CacheName.CONTEST_DETAILS)
                .execute(), contestService);
    }

    public int getPointsAccessibleForActivePhaseOfContest(Contest contest) {
        ContestPhase activePhase = getActivePhase(contest.getContestPK());
        if (activePhase != null) {
            ContestPhaseType cpType = getContestPhaseType(activePhase.getContestPhaseType());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return 0;
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
        try {
            return contestPhasesResource.get(contestPhaseId)
                    .execute().toPojo(contestService);
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestPhaseNotFoundException(contestPhaseId);
        }
    }

    public List<ContestPhaseType> getAllContestPhaseTypes() {
        return DtoUtil.toPojos(contestPhaseTypesResource.list()
                .execute(), contestService);
    }

    public String getContestStatusStr(Long contestPhaseId) {
		return getContestPhase(contestPhaseId).getContestStatusStr();
    }

    public Integer countContestsByContestType(long contestTypeId) {
        return contestResource.service("countByContestType", Integer.class)
                .queryParam("contestTypeId", contestTypeId)
                .get();
    }

    public List<Contest> getContestsByContestType(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("contestTypeIds", contestTypeId)
                .queryParam("lang", lang)
//                .withCache(CacheName.CONTEST_LIST)
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
