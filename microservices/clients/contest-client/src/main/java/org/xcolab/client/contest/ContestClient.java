package org.xcolab.client.contest;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.exceptions.ContestPhaseNotFoundException;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestDto;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.contest.pojo.IContestTranslation;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.tables.pojos.ContestDiscussion;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContestClient {

    private final RestResource1<ContestDto, Long> contestResource = null; // contests
    private final RestResource2<ContestDto, Long, Boolean, Long> tosAgreementResource = null; // contests / memberAgreedToTos
    private final RestResource2<ContestDto, Long, IContestTranslation, String> contestTranslationResource = null; // contests / translations
    private final RestResource<IContestDiscussion, Long> contestDiscussionResource = null; // contestDiscussions

    private final RestResource2L<ContestDto, ContestPhaseWrapper> visiblePhasesResource = null; // contests / visiblePhases
    private final RestResource2<ContestPhaseWrapper, Long, Long, Void> proposalThreadsInPhaseResource = null; // contest / proposalDiscussionThreads
    private final RestResource1<ContestPhaseWrapper, Long> contestPhasesResource = null; // contestPhases
    private final RestResource<IContestPhaseType, Long> contestPhaseTypesResource = null; // contestPhaseTypes
    private final RestResource1<IContestPhaseRibbonType, Long> contestPhaseRibbonTypeResource = null; // contestPhaseRibbonTypes

    private final RestResource1<IContestSchedule, Long> contestScheduleResource = null; // contestSchedules
    private final RestResource1<IContestCollectionCard, Long> contestCollectionCardRestResource = null; // contestCollectionCards
    private final RestResource<Long, Long> contestYearResource = null; // contestyears

    public ContestWrapper getContest(long contestId) {
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestId, lang);
    }

    public ContestWrapper getContest(long contestId, String lang) {
        try {
            return contestResource.get(contestId)
                    .optionalQueryParam("lang", lang)
                    .withCache(CacheName.CONTEST_DETAILS)
                    .executeChecked().toContest();
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public ContestWrapper createContest(Long userId, String name) {
        ContestWrapper c = new ContestWrapper();
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
        c.setReadOnlyComments(false);
        c.setResourceArticleId(0L);
        return createContest(c);
    }

    public ContestWrapper createContest(ContestWrapper contest) {
        final ContestWrapper result = contestResource
                .create(new ContestDto(contest)).execute().toContest();
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

    public List<ContestWrapper> getContestsMatchingTier(Long contestTier) {
        return ContestDto.toContests(contestResource.list()
                .queryParam("contestTiers", contestTier)
                .queryParam("limitRecord", Integer.MAX_VALUE).execute());
    }

    public boolean updateContest(ContestWrapper contest) {
        final Boolean result =
                contestResource.update(new ContestDto(contest), contest.getId())
                        .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_LIST);
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public IContestDiscussion createContestDiscussion(long threadId, long contestId, String tab) {
        IContestDiscussion contestDiscussion = new ContestDiscussion(threadId, contestId, tab);
        return contestDiscussionResource.create(contestDiscussion).execute();
    }

    public IContestDiscussion getContestDiscussion(long contestId, String tab) {
        return contestDiscussionResource.list()
                .queryParam("contestId", contestId)
                .queryParam("tab", tab)
                .executeWithResult()
                .getFirst();
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

    public ContestWrapper getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestUrlName, contestYear, lang);
    }

    public ContestWrapper getContest(String contestUrlName, long contestYear, String lang)
            throws ContestNotFoundException {
        List<ContestDto> list = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .optionalQueryParam("lang", lang)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0).toContest();
        }
        throw new ContestNotFoundException(contestUrlName, contestYear);
    }

    public List<IContestTranslation> getTranslationsForContestId(long contestId) {
        return contestTranslationResource.resolveParentId(contestResource.id(contestId))
                .list()
                .queryParam("contestId", contestId)
                .execute();
    }

    public boolean saveTranslation(IContestTranslation contestTranslation) {
        return contestTranslationResource
                .resolveParentId(contestResource.id(contestTranslation.getContestId()))
                .update(contestTranslation, contestTranslation.getLang())
                .execute();
    }


    public boolean isContestTitleYearUnique(String contestShortName, Long year,Long currentContestId) {

        return contestResource.collectionService("isContestTitleYearUnique", Boolean.class)
                .queryParam("contestShortName", contestShortName)
                .queryParam("year",year)
                .queryParam("currentContestId",currentContestId)
                .execute();
    }
    public List<ContestWrapper> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured", featured)
                .queryParam("lang", lang)
                .withCache(CacheName.CONTEST_LIST)
                .execute());
    }

    public List<ContestWrapper> findContestsByActive(boolean active) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .queryParam("lang", lang)
                .optionalQueryParam("active", active)
                .withCache(CacheName.CONTEST_LIST)
                .execute());
    }

    public List<ContestWrapper> findPublicContests(String contestName,
            List<Long> ontologyTermIds, List<Long> contestTypeIds, List<Long> contestTiers) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .optionalQueryParam("searchTerm", contestName)
                .optionalQueryParam("ontologyTermIds",  ontologyTermIds)
                .optionalQueryParam("contestTypeIds",  contestTypeIds)
                .optionalQueryParam("contestTiers",  contestTiers)
                .queryParam("contestPrivate", false)
                .withCache(CacheName.CONTEST_LIST)
                .execute());
    }

    public List<ContestWrapper> findContestsByTierAndOntologyTermIds(Long contestTier,
            List<Long> focusAreaOntologyTerms) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .queryParam("lang", lang)
                .queryParam("contestTiers", contestTier)
                .queryParam("focusAreaIds", focusAreaOntologyTerms.toArray())
                .withCache(CacheName.CONTEST_LIST)
                .execute());
    }

    //TODO COLAB-2595: Confusing Variable naming
    public List<ContestWrapper> getContestMatchingOntologyTerms(List<Long> ontologyTermIds) {
        return ContestDto.toContests(contestResource
                .collectionService("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaIds", ontologyTermIds.toArray())
                .getList());
    }

    public List<Long> getContestYears() {
        return contestYearResource.list().execute();
    }


    public ContestWrapper getContestByThreadId(Long threadId) {
        try {
            return contestResource.collectionService("getContestByThreadId", ContestWrapper.class)
                    .queryParam("threadId", threadId).execute();
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestNotFoundException("No contest with threadId = " + threadId);
        }
    }

    public ContestWrapper getContestByResourceArticleId(Long resourceArticleId) {
        return contestResource
                .collectionService("getContestByResourceArticleId", ContestWrapper.class)
                .queryParam("resourceArticleId", resourceArticleId)
                .execute();
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

    public boolean updateContestCollectionCard(IContestCollectionCard contestCollectionCard) {
        return contestCollectionCardRestResource.update(contestCollectionCard,contestCollectionCard.getId())
                .execute();
    }

    public boolean deleteContestCollectionCard(long id) {
        return contestCollectionCardRestResource.delete(id).execute();
    }

    public IContestCollectionCard createContestCollectionCard(
            IContestCollectionCard contestCollectionCard) {
        return contestCollectionCardRestResource
                .create(contestCollectionCard)
                .execute();

    }

    public List<ContestWrapper> getContestByOntologyTerm(Long ontologyTermId, Boolean getActive) {
        return ContestDto.toContests(contestResource
                .collectionService("getContestsByOntologyTerm", ContestDto.TYPES.getTypeReference())
                .queryParam("focusAreaOntologyTerm", ontologyTermId)
                .queryParam("getActive", getActive)
                .getList());
    }

    public int getNumberOfContestsByOntologyTerm(Long ontologyTermId) {
        return contestResource.collectionService("getNumberOfContestsByOntologyTerm", Integer.class)
                .queryParam("ontologyTermId", ontologyTermId)
                .execute();
    }

    public List<ContestWrapper> getSubContestsByOntologySpaceId(Long contestId, Long ontologySpaceId) {
        return ContestDto.toContests(contestResource.elementService(contestId, "getSubContestsByOntologySpaceId",
                ContestDto.TYPES.getTypeReference())
                .optionalQueryParam("ontologySpaceId", ontologySpaceId)
                .getList());

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

    public List<ContestWrapper> getAllContests() {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("sort", "weight")
                .withCache(CacheName.CONTEST_LIST)
                .execute());
    }

    public List<ContestWrapper> getAllContestsInYear(Integer contestYear) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("contestYear",contestYear)
                .queryParam("sort", "ContestShortName")
                .withCache(CacheName.CONTEST_LIST)
                .execute());
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
        ContestWrapper contest = getContest(contestId);
        List<Long> modelIds = new ArrayList<>();

        if (StringUtils.isNotBlank(contest.getOtherModels())) {
            modelIds.addAll(IdListUtil.getIdsFromString(contest.getOtherModels()));
        }
        if (!modelIds.contains(contest.getDefaultModelId())) {
            modelIds.add(contest.getDefaultModelId());
        }

        return modelIds;
    }

    public List<ContestWrapper> getContestsByProposalTemplateId(Long proposalTemplateId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("proposalTemplateId", proposalTemplateId)
                .execute());
    }

    public List<ContestWrapper> getContestsByContestScheduleId(Long contestScheduleId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute());
    }

    public List<ContestWrapper> getContestsByActivePrivate(boolean contestActive, boolean contestPrivate) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource
                .list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .queryParam("active", contestActive)
                .queryParam("contestPrivate", contestPrivate)
                .execute());
    }


    public List<ContestWrapper> getContests(Boolean contestActive,
            Boolean contestPrivate, Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("lang", lang)
                .optionalQueryParam("active", contestActive)
                .optionalQueryParam("contestPrivate", contestPrivate)
                .optionalQueryParam("contestTypeIds", contestTypeId)
                .execute());
    }

    public int countContests(Boolean contestActive, Boolean contestPrivate, Long contestTypeId) {
        return contestResource.count()
                .optionalQueryParam("active", contestActive)
                .optionalQueryParam("contestPrivate", contestPrivate)
                .optionalQueryParam("contestTypeIds", contestTypeId)
                .withCache(CacheName.CONTEST_LIST)
                .execute();
    }

    public List<ContestWrapper> getContestsByContestTypeId(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource
                .list()
                .queryParam("lang", lang)
                .queryParam("contestTypeIds", contestTypeId)
                .queryParam("limitRecord", Integer.MAX_VALUE)
                .execute());
    }

    public IContestSchedule createContestSchedule(IContestSchedule contestSchedule) {
        return contestScheduleResource.create(contestSchedule)
                .execute();
    }

    public boolean updateContestSchedule(IContestSchedule contestSchedule) {
        return contestScheduleResource
                .update(contestSchedule, contestSchedule.getId())
                .cacheName(CacheName.MISC_REQUEST)
                .execute();
    }

    public IContestSchedule getContestSchedule(long id) {
        try {
            return contestScheduleResource.get(id)
                    .withCache(CacheName.MISC_REQUEST)
                    .execute();
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestScheduleNotFoundException(id);
        }
    }

    public boolean isContestScheduleUsed(long contestScheduleId) {
        return contestScheduleResource.elementService(contestScheduleId, "isUsed", Boolean.class)
                .get();
    }

    public List<IContestSchedule> getAllContestSchedules() {
        return contestScheduleResource.list().execute();
    }

    public boolean deleteContestSchedule(long contestScheduleId) {
        return contestScheduleResource.delete(contestScheduleId)
                .execute();
    }

    public List<ContestPhaseWrapper> getVisibleContestPhases(Long contestId) {
        return visiblePhasesResource.resolveParentId(contestResource.id(contestId))
                .list()
                .withCache(CacheKeys.withClass(ContestPhaseWrapper.class)
                                .withParameter("contestId", contestId)
                                .withParameter("visible", true).asList(),
                        CacheName.CONTEST_DETAILS)
                .execute();
    }

    public List<Long> getProposalDiscussionThreads(long contestPhaseId) {
        return proposalThreadsInPhaseResource
                .resolveParentId(contestPhasesResource.id(contestPhaseId))
                .list()
                .withCache(CacheName.CONTEST_LIST)
                .execute();
    }

    public int getPointsAccessibleForActivePhaseOfContest(ContestWrapper contest) {
        ContestPhaseWrapper activePhase = getActivePhase(contest.getId());
        if (activePhase != null) {
            IContestPhaseType cpType = getContestPhaseType(activePhase.getContestPhaseTypeId());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return 0;
    }

    public ContestPhaseWrapper getActivePhase(Long contestId) {
        return contestResource.<ContestPhaseWrapper, ContestPhaseWrapper>elementService(contestId, "activePhase", ContestPhaseWrapper.class)
                .withCache(CacheKeys.withClass(ContestPhaseWrapper.class)
                        .withParameter("contestId", contestId)
                        .withParameter("active", true).build(), CacheName.MISC_REQUEST)
                .get();
    }

    public IContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return contestPhaseTypesResource.get(contestPhaseTypeId)
                .withCache(CacheKeys.of(IContestPhaseType.class, contestPhaseTypeId),
                        CacheName.MISC_MEDIUM)
                .execute();
    }

    public void deleteContestPhase(Long contestPhaseId) {
        contestPhasesResource.delete(contestPhaseId).execute();
    }

    public boolean updateContestPhase(ContestPhaseWrapper contestPhase) {
        return contestPhasesResource
                .update(new ContestPhaseWrapper(contestPhase), contestPhase.getId())
                .execute();
    }

    public ContestPhaseWrapper createContestPhase(ContestPhaseWrapper contestPhase) {
        return contestPhasesResource.create(new ContestPhaseWrapper(contestPhase))
                .execute();
    }

    public List<ContestPhaseWrapper> getAllContestPhases(Long contestId) {
        return contestPhasesResource.list()
                .queryParam("contestId", contestId)
                .execute();
    }

    public List<ContestPhaseWrapper> getPhasesForContestScheduleIdAndContest(Long contestScheduleId,
            Long contestId) {
        return contestPhasesResource.list()
                .queryParam("contestId", contestId)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public List<ContestPhaseWrapper> getTemplatePhasesForContestScheduleId(Long contestScheduleId) {
        return contestPhasesResource.list()
                .queryParam("contestId", ContestPhaseWrapper.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID)
                .queryParam("contestScheduleId", contestScheduleId)
                .execute();
    }

    public List<ContestPhaseWrapper> getContestPhasesByType(long contestPhaseTypeId) {
        return contestPhasesResource.list()
                .queryParam("contestPhaseTypeId", contestPhaseTypeId)
                .execute();
    }

    public ContestPhaseWrapper getContestPhase(Long contestPhaseId) {
        try {
            return contestPhasesResource.get(contestPhaseId)
                    .execute();
        } catch (UncheckedEntityNotFoundException e) {
            throw new ContestPhaseNotFoundException(contestPhaseId);
        }
    }

    public List<IContestPhaseType> getAllContestPhaseTypes() {
        return contestPhaseTypesResource.list()
                .execute();
    }

    public List<ContestWrapper> getContestsByContestType(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return ContestDto.toContests(contestResource.list()
                .queryParam("contestTypeIds", contestTypeId)
                .queryParam("lang", lang)
                .withCache(CacheName.CONTEST_LIST)
                .execute());
    }

    public int countContestsByContestType(long contestTypeId) {
        return contestResource.count()
                .queryParam("contestTypeIds", contestTypeId)
                .withCache(CacheName.CONTEST_LIST)
                .execute();
    }

    public String getContestPhaseName(ContestPhaseWrapper ck) {
        return getContestPhaseType(ck.getContestPhaseTypeId()).getName();
    }

    public IContestPhaseRibbonType getContestPhaseRibbonType(long id) {
        return contestPhaseRibbonTypeResource.get(id)
                .execute();
    }

    public List<IContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return contestPhaseRibbonTypeResource.list()
                .execute();
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

    public List<IContestCollectionCard> getSubContestCollectionCards(long parentCollectionCardId) {
        return contestCollectionCardRestResource.list()
                .queryParam("parentCollectionCardId", parentCollectionCardId)
                .execute();
    }

    public List<IContestCollectionCard> getAllContestCollectionCards() {
        return contestCollectionCardRestResource.list()
                .execute();
    }

    public IContestCollectionCard getContestCollectionCard(long id) {
        return contestCollectionCardRestResource.get(id)
                .execute();
    }

    public boolean getMemberAgreedToTos(long contestId, Member member) {
        return tosAgreementResource.resolveParentId(contestResource.id(contestId))
                .get(member.getId())
                .execute();
    }

    public void setMemberAgreedToTos(long contestId, Member member, boolean agreed) {
        tosAgreementResource.resolveParentId(contestResource.id(contestId))
                .create(agreed)
                .queryParam("memberId", member.getId())
                .execute();
    }

    @Override
    public String toString() {
        return "ContestClient[]";
    }
}
