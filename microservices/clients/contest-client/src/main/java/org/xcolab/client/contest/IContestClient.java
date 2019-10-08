package org.xcolab.client.contest;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.contest.pojo.IContestTranslation;
import org.xcolab.client.contest.pojo.tables.pojos.ContestDiscussion;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@FeignClient("xcolab-contest-service")
public interface IContestClient {

    default ContestWrapper getContest(long contestId) {
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestId, lang);
    }

    @GetMapping("/contests/{contestId}")
    ContestWrapper getContest(@PathVariable("contestId") Long contestId,
            @RequestParam(value = "lang", required = false) String lang);

    default ContestWrapper createContest(Long userId, String name) {
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
        c.setEmailTemplateUrl("");
        c.setReadOnlyComments(false);
        c.setResourceArticleId(0L);
        return createContest(c);
    }

    @PostMapping("/contests")
    ContestWrapper createContest(@RequestBody ContestWrapper contest);

    @DeleteMapping("/contests/{contestId}")
    boolean deleteContest(@PathVariable("contestId") Long contestId);

    default List<ContestWrapper> getContestsMatchingTier(Long contestTier) {
        return getContests(null, Integer.MAX_VALUE, null, null, null, null, null, null,
                Collections.singletonList(contestTier), null, null, null, null, null, null, null);
    }

    @PutMapping("/contests")
    boolean updateContest(@RequestBody ContestWrapper contest);

    @PostMapping("/contestDiscussions")
    IContestDiscussion createContestDiscussion(@RequestBody IContestDiscussion contestDiscussion);

    default IContestDiscussion createContestDiscussion(Long threadId, Long contestId, String tab) {
        IContestDiscussion contestDiscussion = new ContestDiscussion(threadId, contestId, tab);
        return createContestDiscussion(contestDiscussion);
    }

    @GetMapping("/contestDiscussions")
    public List<IContestDiscussion> getContestDiscussions(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "contestId", required = false) Long contestId,
            @RequestParam(value = "tab", required = false) String tab);

    default IContestDiscussion getContestDiscussion(Long contestId, String tab) {
        List<IContestDiscussion> contestDiscussions =
                getContestDiscussions(null, null, null, contestId, tab);
        if (!contestDiscussions.isEmpty()) {
            return contestDiscussions.get(0);
        }
        throw new IndexOutOfBoundsException();
    }

    default ContestWrapper getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        final String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContest(contestUrlName, contestYear, lang);
    }

    default ContestWrapper getContest(String contestUrlName, long contestYear, String lang) {
        List<ContestWrapper> list =
                getContests(null, null, null, lang, contestUrlName, contestYear, null, null, null,
                        null, null, null, null, null, null, null);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        throw new ContestNotFoundException(contestUrlName, contestYear);
    }

    @GetMapping("/contests/{contestId}/translations")
    List<ContestTranslation> getTranslationsForContestId(
            @PathVariable("contestId") Long contestId);

    @PutMapping("/contests/translations")
    boolean saveTranslation(@RequestBody ContestTranslation contestTranslation);

    @GetMapping("/contests/isContestTitleYearUnique")
    boolean isContestTitleYearUnique(
            @RequestParam(value = "contestShortName", required = false) String contestShortName,
            @RequestParam(value = "year", required = false) Long year,
            @RequestParam(value = "currentContestId", required = false) Long currentContestId);

    default List<ContestWrapper> findContestsByActiveFeatured(Boolean active, Boolean featured) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, null, null, lang, null, null, active, featured, null,
                null, null, null, null, null, null, null);
    }

    default List<ContestWrapper> findContestsByActive(boolean active) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, null, null, lang, null, null, active, null, null, null, null, null,
                null, null, false, null);
    }

    default List<ContestWrapper> findPublicContests(String contestName,
            List<Long> ontologyTermIds, List<Long> contestTypeIds, List<Long> contestTiers) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(0, Integer.MAX_VALUE, null, lang, null, null, null, null,
                contestTiers, null, null, null, ontologyTermIds, contestTypeIds, null, contestName);
    }

    default List<ContestWrapper> findContestsByTierAndOntologyTermIds(Long contestTier,
            List<Long> focusAreaOntologyTerms) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, null, null, lang, null, null, null, null,
                Collections.singletonList(contestTier), null, null, null, focusAreaOntologyTerms,
                null, false, null);
    }

    //TODO COLAB-2595: Confusing Variable naming
    @GetMapping("/contests/getContestMatchingOntologyTerms")
    List<ContestWrapper> getContestMatchingOntologyTerms(
            @RequestParam(value = "focusAreaOntologyTerms", required = false)
                    List<Long> focusAreaOntologyTerms);

    @GetMapping("/contestyears")
    List<Long> getContestYears();


    @GetMapping("/contests/getContestByThreadId")
    ContestWrapper getContestByThreadId(
            @RequestParam(value = "threadId", required = false) Long threadId);

    @GetMapping("/contests/getContestByResourceArticleId")
    ContestWrapper getContestByResourceArticleId(
            @RequestParam(value = "resourceArticleId", required = false) Long resourceArticleId);


    @GetMapping("contests/getNumberOfAllContestsInCollectionCard")
    int getNumberOfAllContestsInCollectionCard(
            @RequestParam(value = "collectionCardId") Long collectionCardId,
            @RequestParam(value = "viewType", required = false) String viewType,
            @RequestParam(value = "onlyFeatured", required = false) Boolean onlyFeatured);

    @GetMapping("contests/getNumberOfActiveContestsInCollectionCard")
    int getNumberOfActiveContestsInCollectionCard(
            @RequestParam(value = "collectionCardId") Long collectionCardId,
            @RequestParam(value = "viewType", required = false) String viewType,
            @RequestParam(value = "onlyFeatured", required = false) Boolean onlyFeatured);

    @GetMapping("contests/getNumberOfPriorContestsInCollectionCard")
    int getNumberOfPriorContestsInCollectionCard(
            @RequestParam(value = "collectionCardId") Long collectionCardId,
            @RequestParam(value = "viewType", required = false) String viewType,
            @RequestParam(value = "onlyFeatured", required = false) Boolean onlyFeatured);

    @PutMapping("/contestCollectionCards")
    boolean updateContestCollectionCard(@RequestBody IContestCollectionCard contestCollectionCard);

    @DeleteMapping("/contestCollectionCards/{contestCollectionCardId}")
    boolean deleteContestCollectionCard(
            @PathVariable("contestCollectionCardId") Long contestCollectionCardId);

    @PostMapping("/contestCollectionCards")
    IContestCollectionCard createContestCollectionCard(
            @RequestBody IContestCollectionCard contestCollectionCard);

    @GetMapping("/contests/getContestsByOntologyTerm")
    List<ContestWrapper> getContestByOntologyTerm(
            @RequestParam(value = "focusAreaOntologyTerm") Long focusAreaOntologyTerm,
            @RequestParam(value = "getActive", required = false) Boolean getActive);

    @GetMapping("/contests/getNumberOfContestsByOntologyTerm")
    int getNumberOfContestsByOntologyTerm(
            @RequestParam(value = "focusAreaOntologyTerm") Long focusAreaOntologyTerm);

    @GetMapping("/contests/{contestId}/subContestsByOntologySpaceId")
    List<ContestWrapper> getSubContestsByOntologySpaceId(@PathVariable("contestId") Long contestId,
            @RequestParam(value = "ontologySpaceId") Long ontologySpaceId);

    @GetMapping("/contestPhases/autoPromoteProposals")
    int autoPromoteProposals();

    @PutMapping("/contestPhases/{contestPhaseId}/forcePromotionOfProposalInContestPhaseId")
    boolean forcePromotionOfProposalInPhase(@PathVariable("contestPhaseId") Long contestPhaseId,
            @RequestParam(value = "proposalId") Long proposalId);

    default List<ContestWrapper> getAllContests() {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(0, Integer.MAX_VALUE, "weight", lang, null, null, null, null, null,
                null, null, null, null, null, null, null);
    }

    default List<ContestWrapper> getAllContestsInYear(Long contestYear) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(0, Integer.MAX_VALUE, null, lang, null, contestYear, null, null, null,
                null, null, null, null, null, null, "ContestShortName");
    }

    default Map<Long, String> getModelIdsAndNames(long contestId) {
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

    default List<Long> getModelIds(long contestId) throws ContestNotFoundException {
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

    default List<ContestWrapper> getContestsByProposalTemplateId(Long proposalTemplateId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, null, null, lang, null, null, null, null, null,
                null, proposalTemplateId, null, null, null, null, null);
    }

    default List<ContestWrapper> getContestsByContestScheduleId(Long contestScheduleId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, null, null, lang, null, null, null, null, null,
                contestScheduleId, null, null, null, null, null, null);
    }

    default List<ContestWrapper> getContestsByActivePrivate(boolean contestActive,
            boolean contestPrivate) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(0, Integer.MAX_VALUE, null, lang, null, null, contestActive, null, null,
                null, null, null, null, null, contestPrivate, null);
    }

    @GetMapping("/contests")
    List<ContestWrapper> getContests(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "lang", required = false) String lang,
            @RequestParam(value = "contestUrlName", required = false) String contestUrlName,
            @RequestParam(value = "contestYear", required = false) Long contestYear,
            @RequestParam(value = "active", required = false) Boolean active,
            @RequestParam(value = "featured", required = false) Boolean featured,
            @RequestParam(value = "contestTiers", required = false) List<Long> contestTiers,
            @RequestParam(value = "contestScheduleId", required = false) Long contestScheduleId,
            @RequestParam(value = "proposalTemplateId", required = false) Long proposalTemplateId,
            @RequestParam(value = "focusAreaIds", required = false) List<Long> focusAreaIds,
            @RequestParam(value = "ontologyTermIds", required = false) List<Long> ontologyTermIds,
            @RequestParam(value = "contestTypeIds", required = false) List<Long> contestTypeIds,
            @RequestParam(value = "contestPrivate", required = false) Boolean contestPrivate,
            @RequestParam(value = "searchTerm", required = false) String searchTerm);

    default List<ContestWrapper> getContests(Boolean contestActive, Boolean contestPrivate,
            Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, Integer.MAX_VALUE, null, lang, null, null, contestActive, null, null, null,
                null, null, null,
                Collections.singletonList(contestTypeId), contestPrivate, null);
    }

    @GetMapping("/count/contests")
    int countContests(
            @RequestParam(value = "contestActive", required = false) Boolean contestActive,
            @RequestParam(value = "contestPrivate", required = false) Boolean contestPrivate,
            @RequestParam(value = "contestTypeId", required = false) Long contestTypeId);

    default List<ContestWrapper> getContestsByContestTypeId(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, Integer.MAX_VALUE, null, lang, null, null, null, null, null, null,
                null, null, null,
                Collections.singletonList(contestTypeId), null, null);
    }

    @PostMapping("/contestSchedules")
    IContestSchedule createContestSchedule(@RequestBody IContestSchedule contestSchedule);

    @PutMapping("/contestSchedules")
    public boolean updateContestSchedule(@RequestBody IContestSchedule contestSchedule);

    @GetMapping("/contestSchedules/{contestScheduleId}")
    IContestSchedule getContestSchedule(@PathVariable("contestScheduleId") Long contestScheduleId);

    @GetMapping("/contestSchedules/{contestScheduleId}/isUsed")
    boolean isContestScheduleUsed(@PathVariable("contestScheduleId") Long contestScheduleId);

    @GetMapping("/contestSchedules")
    List<IContestSchedule> getAllContestSchedules();

    @DeleteMapping("/contestSchedules/{id}")
    boolean deleteContestSchedule(@PathVariable("id") Long id);

    @GetMapping("/contests/{contestId}/visiblePhases")
    List<ContestPhaseWrapper> getVisibleContestPhases(@PathVariable("contestId") Long contestId);

    @GetMapping("/contestPhases/{phaseId}/proposalDiscussionThreads")
    List<Long> getProposalDiscussionThreads(@PathVariable("phaseId") Long phaseId);

    default int getPointsAccessibleForActivePhaseOfContest(ContestWrapper contest) {
        ContestPhaseWrapper activePhase = getActivePhase(contest.getId());
        if (activePhase != null) {
            IContestPhaseType cpType = getContestPhaseType(activePhase.getContestPhaseTypeId());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return 0;
    }

    @GetMapping("/contests/{contestId}/activePhase")
    ContestPhaseWrapper getActivePhase(@PathVariable("contestId") Long contestId);

    @GetMapping("/contestPhaseTypes/{contestPhaseTypeId}")
    IContestPhaseType getContestPhaseType(
            @PathVariable("contestPhaseTypeId") Long contestPhaseTypeId);

    @DeleteMapping("/contestPhases/{contestPhaseId}")
    boolean deleteContestPhase(@PathVariable("contestPhaseId") Long contestPhaseId);

    @PutMapping("/contestPhases")
    boolean updateContestPhase(@RequestBody ContestPhaseWrapper contestPhase);

    @PostMapping("/contestPhases")
    ContestPhaseWrapper createContestPhase(@RequestBody ContestPhaseWrapper contestPhase);

    @GetMapping("/contestPhases")
    List<ContestPhaseWrapper> getContestPhases(
            @RequestParam(value = "contestId", required = false) Long contestId,
            @RequestParam(value = "contestScheduleId", required = false) Long contestScheduleId,
            @RequestParam(value = "contestPhaseTypeId", required = false) Long contestPhaseTypeId);

    default List<ContestPhaseWrapper> getAllContestPhases(Long contestId) {
        return getContestPhases(contestId, null, null);
    }

    default List<ContestPhaseWrapper> getPhasesForContestScheduleIdAndContest(
            Long contestScheduleId, Long contestId) {
        return getContestPhases(contestId, contestScheduleId, null);
    }

    default List<ContestPhaseWrapper> getTemplatePhasesForContestScheduleId(
            Long contestScheduleId) {
        return getContestPhases(ContestPhaseWrapper.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID,
                contestScheduleId, null);
    }

    default List<ContestPhaseWrapper> getContestPhasesByType(Long contestPhaseTypeId) {
        return getContestPhases(null, null, contestPhaseTypeId);
    }

    @GetMapping("/contestPhases/{phaseId}")
    ContestPhaseWrapper getContestPhase(@PathVariable("phaseId") Long phaseId);

    @RequestMapping("/contestPhaseTypes")
    public List<IContestPhaseType> getAllContestPhaseTypes();

    default List<ContestWrapper> getContestsByContestType(Long contestTypeId) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return getContests(null, null, null, lang, null, null, null, null, null,
                null, null, null, null, Collections.singletonList(contestTypeId), null, null);
    }

    default int countContestsByContestType(long contestTypeId) {
        return countContests(false, false, contestTypeId);
    }

    default String getContestPhaseName(ContestPhaseWrapper ck) {
        return getContestPhaseType(ck.getContestPhaseTypeId()).getName();
    }

    @GetMapping("/contestPhaseRibbonTypes/{contestPhaseRibbonTypeId}")
    IContestPhaseRibbonType getContestPhaseRibbonType(
            @PathVariable("contestPhaseRibbonTypeId") Long contestPhaseRibbonTypeId);

    @GetMapping("/contestPhaseRibbonTypes")
    List<IContestPhaseRibbonType> getAllContestPhaseRibbonType();

    default boolean isMemberSubscribedToContest(long contestId, long userId) {
        return StaticActivityContext.getActivityClient().isSubscribed(userId,
                ActivityCategory.CONTEST, contestId);
    }

    default void subscribeMemberToContest(long contestId, long userId) {
        StaticActivityContext.getActivityClient()
                .addSubscription(userId, ActivityCategory.CONTEST, contestId, "");
    }

    default void unsubscribeMemberFromContest(long contestId, long userId) {
        StaticActivityContext
                .getActivityClient()
                .deleteSubscription(userId, ActivityCategory.CONTEST, contestId);
    }

    @GetMapping("/contestCollectionCards")
    List<IContestCollectionCard> getAllContestCollectionCards(
            @RequestParam(value = "parentCollectionCardId", required = false)
                    Long parentCollectionCardId);

    default List<IContestCollectionCard> getSubContestCollectionCards(Long parentCollectionCardId) {
        return getAllContestCollectionCards(parentCollectionCardId);
    }

    default List<IContestCollectionCard> getAllContestCollectionCards() {
        return getAllContestCollectionCards(null);
    }

    @GetMapping("/contestCollectionCards/{contestCollectionCardId}")
    IContestCollectionCard getContestCollectionCard(
            @PathVariable("contestCollectionCardId") Long contestCollectionCardId);

    @GetMapping("/contests/{contestId}/memberAgreedToTos/{memberId}")
    boolean getMemberAgreedToTos(@PathVariable("contestId") Long contestId,
            @PathVariable("memberId") Long memberId);

    @PostMapping("/contests/{contestId}/memberAgreedToTos")
    void setMemberAgreedToTos(@PathVariable("contestId") Long contestId,
            @RequestParam(value = "memberId") Long memberId, @RequestBody boolean agreed);
}
