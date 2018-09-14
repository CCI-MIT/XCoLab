package org.xcolab.client.contest;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

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
import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.enums.ServiceNamespace;
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

    private static final Map<ServiceNamespace, ContestClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<ContestDto, Long> contestResource;
    private final RestResource2<ContestDto, Long, Boolean, Long> tosAgreementResource;
    private final RestResource2<ContestDto, Long, ContestTranslationDto, String> contestTranslationResource;
    private final RestResource<ContestDiscussionDto, Long> contestDiscussionResource;

    private final RestResource2L<ContestDto, ContestPhaseDto> visiblePhasesResource;
    private final RestResource2<ContestPhaseDto, Long, Long, Void> proposalThreadsInPhaseResource;
    private final RestResource1<ContestPhaseDto, Long> contestPhasesResource;
    private final RestResource<ContestPhaseTypeDto, Long> contestPhaseTypesResource;
    private final RestResource1<ContestPhaseRibbonTypeDto, Long> contestPhaseRibbonTypeResource;

    private final RestResource1<ContestScheduleDto, Long> contestScheduleResource;
    private final RestResource1<ContestCollectionCardDto, Long> contestCollectionCardRestResource;
    private final RestResource<Long, Long> contestYearResource;

    private ContestClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        contestPhaseRibbonTypeResource = new RestResource1<>(ContestResource.CONTEST_PHASE_RIBBON_TYPE, ContestPhaseRibbonTypeDto.TYPES, serviceNamespace);
        contestScheduleResource = new RestResource1<>(ContestResource.CONTEST_SCHEDULE, ContestScheduleDto.TYPES, serviceNamespace);
        contestPhaseTypesResource = new RestResource1<>(ContestResource.CONTEST_PHASE_TYPE, ContestPhaseTypeDto.TYPES, serviceNamespace);
        contestPhasesResource = new RestResource1<>(ContestResource.CONTEST_PHASE, ContestPhaseDto.TYPES, serviceNamespace);
        contestResource = new RestResource1<>(ContestResource.CONTEST, ContestDto.TYPES, serviceNamespace);
        tosAgreementResource = contestResource.nestedResource("memberAgreedToTos", TypeProvider.BOOLEAN);
        visiblePhasesResource = new RestResource2L<>(
                contestResource, "visiblePhases", ContestPhaseDto.TYPES);
        contestCollectionCardRestResource =
                new RestResource1<>(ContestResource.CONTEST_COLLECTION_CARDS, ContestCollectionCardDto.TYPES, serviceNamespace);
        contestDiscussionResource =
                new RestResource1<>(ContestResource.CONTEST_DISCUSSION, ContestDiscussionDto.TYPES, serviceNamespace);

        contestYearResource = new RestResource1<>(ContestResource.CONTEST_YEAR, TypeProvider.LONG, serviceNamespace);
        contestTranslationResource = new RestResource2<>(contestResource,
                "translations", ContestTranslationDto.TYPES);
        proposalThreadsInPhaseResource = contestPhasesResource
                .nestedResource("proposalDiscussionThreads", TypeProvider.LONG);
    }

    public static ContestClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ContestClient::new);
    }

    public Contest getContest(long contestId) {
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestId, lang);
    }

    public Contest getContest(long contestId, String lang) {
        try {
            return contestResource.get(contestId)
                    .optionalQueryParam("lang", lang)
                    .withCache(CacheName.CONTEST_DETAILS)
                    .executeChecked().toPojo(serviceNamespace);
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public Contest createContest(Long userId, String name) {
        Contest c = new Contest();
        c.setAuthorUserId(userId);
        c.setQuestion(name);
        c.setTitle(name);
        c.setContestUrlName(c.generateContestUrlName());
        c.setDescription("");
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
        c.setFeatured(false);
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
                contestResource.create(new ContestDto(contest)).execute().toPojo(serviceNamespace);
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public boolean deleteContest(long contestId) {
        final Boolean result = contestResource.delete(contestId)
                .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public List<Contest> getContestsMatchingTier(Long contestTier) {
        return DtoUtil.toPojos(
                contestResource.list().queryParam("contestTiers", contestTier).queryParam("limitRecord", Integer.MAX_VALUE).execute(),
                serviceNamespace);
    }

    public boolean updateContest(Contest contest) {
        final Boolean result =
                contestResource.update(new ContestDto(contest), contest.getId())
                        .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public ContestDiscussion createContestDiscussion(long threadId, long contestId, String tab) {
        ContestDiscussion contestDiscussion = new ContestDiscussion(threadId, contestId, tab);
        return contestDiscussionResource.create(new ContestDiscussionDto(contestDiscussion)).execute()
                .toPojo(serviceNamespace);
    }

    public ContestDiscussion getContestDiscussion(long contestId, String tab) {
        return contestDiscussionResource.list()
                .queryParam("contestId", contestId)
                .queryParam("tab", tab)
                .executeWithResult()
                .getFirst().toPojo(serviceNamespace);
    }

    public Integer getProposalCount(Long contestId) {
        try {
            return contestResource.<Proposal, Integer>elementService(contestId,
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
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0).toPojo(serviceNamespace);
        }
        throw new ContestNotFoundException(contestUrlName, contestYear);
    }

    public List<ContestTranslation> getTranslationsForContestId(long contestId) {
        final List<ContestTranslationDto> dtos =
                contestTranslationResource.resolveParentId(contestResource.id(contestId))
                        .list()
                        .queryParam("contestId", contestId)
                        .execute();
        return DtoUtil.toPojos(dtos, serviceNamespace);
    }

    public boolean saveTranslation(ContestTranslation contestTranslation) {
        return contestTranslationResource
                .resolveParentId(contestResource.id(contestTranslation.getContestId()))
                .update(new ContestTranslationDto(contestTranslation), contestTranslation.getLang())
                .execute();
    }


    public boolean isContestTitleYearUnique(String contestShortName, Long year,Long currentContestId) {

        return contestResource.collectionService("isContestTitleYearUnique", Boolean.class)
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
                .withCache(CacheName.CONTEST_LIST)
                .execute(), serviceNamespace);
    }

    public List<Contest> findContestsByActive(boolean active) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("lang", lang)
                .optionalQueryParam("active", active)
                .withCache(CacheName.CONTEST_LIST)
                .execute(), serviceNamespace);
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
                .withCache(CacheName.CONTEST_LIST)
                .execute(), serviceNamespace);
    }

    public List<Contest> findContestsByTierAndOntologyTermIds(Long contestTier,
            List<Long> focusAreaOntologyTerms) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("lang", lang)
                .queryParam("contestTiers", contestTier)
                .queryParam("focusAreaIds", focusAreaOntologyTerms.toArray())
                .withCache(CacheName.CONTEST_LIST)
                .execute(), serviceNamespace);
    }

    //TODO COLAB-2595: Confusing Variable naming
    public List<Contest> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return DtoUtil.toPojos(contestResource
                .collectionService("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaIds", ontologyTermIds.toArray())
                .getList(), serviceNamespace);
    }

    public List<Long> getContestYears() {
        return contestYearResource.list().execute();
    }


    public Contest getContestByThreadId(Long threadId) {
        try {
            return contestResource.collectionService("getContestByThreadId", ContestDto.class)
                    .queryParam("threadId", threadId).execute().toPojo(serviceNamespace);
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestNotFoundException("No contest with threadId = " + threadId);
        }
    }

    public Contest getContestByResourceArticleId(Long resourceArticleId) {
        return contestResource
                .collectionService("getContestByResourceArticleId", ContestDto.class)
                .queryParam("resourceArticleId", resourceArticleId)
                .execute().toPojo(serviceNamespace);
    }


    public int getNumberOfAllContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestResource.collectionService("getNumberOfAllContestsInCollectionCard", Integer.class)
                .queryParam("collectionCardId", collectionCardId)
                .queryParam("viewType", viewType)
                .queryParam("onlyFeatured", onlyFeatured)
                .execute();
    }

    public int getNumberOfActiveContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestResource.collectionService("getNumberOfActiveContestsInCollectionCard", Integer.class)
                .queryParam("collectionCardId", collectionCardId)
                .queryParam("viewType", viewType)
                .queryParam("onlyFeatured", onlyFeatured)
                .execute();
    }

    public int getNumberOfPriorContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestResource.collectionService("getNumberOfPriorContestsInCollectionCard", Integer.class)
                .queryParam("collectionCardId", collectionCardId)
                .queryParam("viewType", viewType)
                .queryParam("onlyFeatured", onlyFeatured)
                .execute();
    }

    public boolean updateContestCollectionCard(ContestCollectionCard contestCollectionCard) {
        return contestCollectionCardRestResource.update(new ContestCollectionCardDto(contestCollectionCard),contestCollectionCard.getId())
                .execute();
    }

    public boolean deleteContestCollectionCard(long id) {
        return contestCollectionCardRestResource.delete(id).execute();
    }

    public ContestCollectionCard createContestCollectionCard(ContestCollectionCard contestCollectionCard) {
        return contestCollectionCardRestResource.create(new ContestCollectionCardDto(contestCollectionCard)).execute().toPojo(serviceNamespace);

    }

    public List<Contest> getContestByOntologyTerm(Long ontologyTermId, Boolean getActive) {
        return DtoUtil.toPojos(contestResource
                .collectionService("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaOntologyTerm", ontologyTermId)
                .queryParam("getActive", getActive)
                .getList(), serviceNamespace);
    }

    public int getNumberOfContestsByOntologyTerm(Long ontologyTermId) {
        return contestResource.collectionService("getNumberOfContestsByOntologyTerm", Integer.class)
                .queryParam("ontologyTermId", ontologyTermId)
                .execute();
    }

    public List<Contest> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {
        return DtoUtil.toPojos(contestResource.elementService(contestId, "getSubContestsByOntologySpaceId",
                ContestDto.TYPES.getTypeReference())
                .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                .getList(), serviceNamespace);

    }

    public int autoPromoteProposals() {
        return contestPhasesResource.collectionService("autoPromoteProposals", Integer.class).get();
    }

    public void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestPhasesResource
                .elementService(contestPhaseId, "forcePromotionOfProposalInContestPhaseId", Boolean.class)
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
                .execute(), serviceNamespace);
    }

    public List<Contest> getAllContestsInYear(Integer contestYear) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("contestYear",contestYear)
                .queryParam("sort", "ContestShortName")
                .withCache(CacheName.CONTEST_LIST)
                .execute(), serviceNamespace);
    }

    public Map<Long, String> getModelIdsAndNames(long contestId) {
        try {
            List<Long> modelIds = getModelIds(contestId);

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

    public List<Long> getModelIds(long contestId) throws ContestNotFoundException {
        Contest contest = getContest(contestId);
        List<Long> modelIds = new ArrayList<>();

        if (StringUtils.isNotBlank(contest.getOtherModels())) {
            modelIds.addAll(IdListUtil.getIdsFromString(contest.getOtherModels()));
        }
        if (!modelIds.contains(contest.getDefaultModelId())) {
            modelIds.add(contest.getDefaultModelId());
        }

        return modelIds;
    }

    public List<Contest> getContestsByProposalTemplateId(Long proposalTemplateId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("proposalTemplateId", proposalTemplateId)
                .execute(), serviceNamespace);
    }

    public List<Contest> getContestsByContestScheduleId(Long contestScheduleId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), serviceNamespace);
    }

    public List<Contest> getContestsByActivePrivate(boolean contestActive, boolean contestPrivate) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .execute(), serviceNamespace);
    }


    public List<Contest> getContests(Boolean contestActive,
            Boolean contestPrivate, Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .optionalQueryParam("active", contestActive)
                .optionalQueryParam("contestPrivate", contestPrivate)
                .optionalQueryParam("contestTypeIds", contestTypeId)
                .execute(), serviceNamespace);
    }

    public int countContests(Boolean contestActive, Boolean contestPrivate, Long contestTypeId) {
        return contestResource.count()
                .optionalQueryParam("active", contestActive)
                .optionalQueryParam("contestPrivate", contestPrivate)
                .optionalQueryParam("contestTypeIds", contestTypeId)
                .withCache(CacheName.CONTEST_LIST)
                .execute();
    }

    public List<Contest> getContestsByContestTypeId(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("contestTypeIds", contestTypeId)
                .queryParam("limitRecord", Integer.MAX_VALUE)
                .execute(), serviceNamespace);
    }

    public ContestSchedule createContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource.create(new ContestScheduleDto(contestSchedule))
                .execute().toPojo(serviceNamespace);
    }

    public boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestScheduleResource
                .update(new ContestScheduleDto(contestSchedule), contestSchedule.getId())
                .cacheName(CacheName.MISC_REQUEST)
                .execute();
    }

    public ContestSchedule getContestSchedule(long id) {
        try {
            return contestScheduleResource.get(id)
                    .withCache(CacheName.MISC_REQUEST)
                    .execute().toPojo(serviceNamespace);
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestScheduleNotFoundException(id);
        }
    }

    public boolean isContestScheduleUsed(long contestScheduleId) {
        return contestScheduleResource.elementService(contestScheduleId, "isUsed", Boolean.class)
                .get();
    }

    public List<ContestSchedule> getAllContestSchedules() {
        return DtoUtil.toPojos(contestScheduleResource.list().execute(), serviceNamespace);
    }

    public boolean deleteContestSchedule(long contestScheduleId) {
        return contestScheduleResource.delete(contestScheduleId)
                .execute();
    }

    public List<ContestPhase> getVisibleContestPhases(Long contestId) {
        return DtoUtil.toPojos(visiblePhasesResource.resolveParentId(contestResource.id(contestId))
                .list()
                .withCache(CacheKeys.withClass(ContestPhaseDto.class)
                                .withParameter("contestId", contestId)
                                .withParameter("visible", true).asList(),
                        CacheName.CONTEST_DETAILS)
                .execute(), serviceNamespace);
    }

    public List<Long> getProposalDiscussionThreads(long contestPhaseId) {
        return proposalThreadsInPhaseResource
                .resolveParentId(contestPhasesResource.id(contestPhaseId))
                .list()
                .withCache(CacheName.CONTEST_LIST)
                .execute();
    }

    public int getPointsAccessibleForActivePhaseOfContest(Contest contest) {
        ContestPhase activePhase = getActivePhase(contest.getId());
        if (activePhase != null) {
            ContestPhaseType cpType = getContestPhaseType(activePhase.getContestPhaseTypeId());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return 0;
    }

    public ContestPhase getActivePhase(Long contestId) {
        return contestResource.<ContestPhaseDto, ContestPhaseDto>elementService(contestId, "activePhase", ContestPhaseDto.class)
                .withCache(CacheKeys.withClass(ContestPhaseDto.class)
                        .withParameter("contestId", contestId)
                        .withParameter("active", true).build(), CacheName.MISC_REQUEST)
                .get()
                .toPojo(serviceNamespace);
    }

    public ContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return contestPhaseTypesResource.get(contestPhaseTypeId)
                .withCache(CacheKeys.of(ContestPhaseTypeDto.class, contestPhaseTypeId),
                        CacheName.MISC_MEDIUM)
                .execute().toPojo(serviceNamespace);
    }

    public void deleteContestPhase(Long contestPhaseId) {
        contestPhasesResource.delete(contestPhaseId).execute();
    }

    public boolean updateContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource
                .update(new ContestPhaseDto(contestPhase), contestPhase.getId())
                .execute();
    }

    public ContestPhase createContestPhase(ContestPhase contestPhase) {
        return contestPhasesResource.create(new ContestPhaseDto(contestPhase))
                .execute().toPojo(serviceNamespace);
    }

    public List<ContestPhase> getAllContestPhases(Long contestId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestId", contestId)
                .execute(), serviceNamespace);
    }

    public List<ContestPhase> getPhasesForContestScheduleIdAndContest(Long contestScheduleId,
            Long contestId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestId", contestId)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), serviceNamespace);
    }

    public List<ContestPhase> getTemplatePhasesForContestScheduleId(Long contestScheduleId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestId", ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute(), serviceNamespace);
    }

    public List<ContestPhase> getContestPhasesByType(long contestPhaseTypeId) {
        return DtoUtil.toPojos(contestPhasesResource.list()
                .queryParam("contestPhaseTypeId", contestPhaseTypeId)
                .execute(), serviceNamespace);
    }

    public ContestPhase getContestPhase(Long contestPhaseId) {
        try {
            return contestPhasesResource.get(contestPhaseId)
                    .execute().toPojo(serviceNamespace);
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestPhaseNotFoundException(contestPhaseId);
        }
    }

    public List<ContestPhaseType> getAllContestPhaseTypes() {
        return DtoUtil.toPojos(contestPhaseTypesResource.list()
                .execute(), serviceNamespace);
    }

    public List<Contest> getContestsByContestType(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return DtoUtil.toPojos(contestResource.list()
                .queryParam("contestTypeIds", contestTypeId)
                .queryParam("lang", lang)
                .withCache(CacheName.CONTEST_LIST)
                .execute(), serviceNamespace);
    }

    public int countContestsByContestType(long contestTypeId) {
        return contestResource.count()
                .queryParam("contestTypeIds", contestTypeId)
                .withCache(CacheName.CONTEST_LIST)
                .execute();
    }

    public String getContestPhaseName(ContestPhase ck) {
        return getContestPhaseType(ck.getContestPhaseTypeId()).getName();
    }

    public ContestPhaseRibbonType getContestPhaseRibbonType(long id) {
        return contestPhaseRibbonTypeResource.get(id)
                .execute().toPojo(serviceNamespace);
    }

    public List<ContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return DtoUtil.toPojos(contestPhaseRibbonTypeResource.list()
                .execute(), serviceNamespace);
    }

    public boolean isMemberSubscribedToContest(long contestId, long userId) {
        return ActivitiesClientUtil.isSubscribedToActivity(userId,
                ActivityCategory.CONTEST, contestId, "");
    }

    public void subscribeMemberToContest(long contestId, long userId) {
        ActivitiesClientUtil.addSubscription(userId, ActivityCategory.CONTEST, contestId, "");
    }

    public void unsubscribeMemberFromContest(long contestId, long userId) {
        ActivitiesClientUtil.deleteSubscription(userId, ActivityCategory.CONTEST, contestId);
    }

    public List<ContestCollectionCard> getSubContestCollectionCards(long parentCollectionCardId) {
        return DtoUtil.toPojos(contestCollectionCardRestResource.list()
                .queryParam("parentCollectionCardId", parentCollectionCardId)
                .execute(),serviceNamespace);
    }

    public List<ContestCollectionCard> getAllContestCollectionCards() {
        return DtoUtil.toPojos(contestCollectionCardRestResource.list()
                .execute(), serviceNamespace);
    }

    public ContestCollectionCard getContestCollectionCard(long id) {
        return contestCollectionCardRestResource.get(id)
                .execute().toPojo(serviceNamespace);
    }

    public boolean getMemberAgreedToTos(long contestId, Member member) {
        return tosAgreementResource.resolveParentId(contestResource.id(contestId))
                .get(member.getId())
                .execute()
                .booleanValue();
    }

    public void setMemberAgreedToTos(long contestId, Member member, boolean agreed) {
        tosAgreementResource.resolveParentId(contestResource.id(contestId))
                .create(agreed)
                .queryParam("memberId", member.getId())
                .execute();
    }

    public void deleteResourceArticle(long id) {
        Contest currentContest = getContest(id);
        currentContest.setResourceArticleId(0L);
        contestResource.update(new ContestDto(currentContest), id).execute();
    }

    @Override
    public String toString() {
        return "ContestClient[" + serviceNamespace + "]";
    }
}
