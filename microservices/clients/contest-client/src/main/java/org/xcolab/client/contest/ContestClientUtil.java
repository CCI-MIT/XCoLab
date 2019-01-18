package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.contest.pojo.IContestTranslation;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IContestPhaseType;

import java.util.List;
import java.util.Map;

public final class ContestClientUtil {

    private static final ContestClient contestClient = new ContestClient();

    private ContestClientUtil() {
    }

    public static ContestClient getClient() {
        return contestClient;
    }

    public static ContestWrapper getContest(long contestId) {
        return contestClient.getContest(contestId);
    }

    public static ContestWrapper getContest(long contestId, String lang) {
        return contestClient.getContest(contestId, lang);
    }

    public static ContestWrapper createContest(Long userId, String name) {
        return contestClient.createContest(userId, name);
    }

    public static ContestWrapper createContest(
            ContestWrapper contest) {
        return contestClient.createContest(contest);
    }

    public static List<ContestWrapper> getContestsMatchingTier(
            Long contestTier) {
        return contestClient.getContestsMatchingTier(contestTier);
    }

    public static boolean updateContest(ContestWrapper contest) {
        return contestClient.updateContest(contest);
    }

    public static Integer getProposalCount(Long contestId) {
        return contestClient.getProposalCount(contestId);
    }

    public static ContestWrapper getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        return contestClient.getContest(contestUrlName, contestYear);
    }

    public static ContestWrapper getContest(String contestUrlName, long contestYear, String lang)
            throws ContestNotFoundException {
        return contestClient.getContest(contestUrlName, contestYear, lang);
    }

    public static List<IContestTranslation> getTranslationsForContestId(long contestId) {
        return contestClient.getTranslationsForContestId(contestId);
    }

    public static boolean saveTranslation(IContestTranslation contestTranslation) {
        return contestClient.saveTranslation(contestTranslation);
    }

    public static IContestDiscussion createContestDiscussion(long threadId, long contestId,
            String tab) {
        return contestClient.createContestDiscussion(threadId, contestId, tab);
    }

    public static IContestDiscussion getContestDiscussion(long contestId,
            String tab) {
        return contestClient.getContestDiscussion(contestId, tab);
    }

    public static ContestWrapper getContestByResourceArticleId(Long resourceArticleId) {
        return contestClient.getContestByResourceArticleId(resourceArticleId);
    }
    public static ContestWrapper getContestByThreadId(Long threadId){
        return contestClient.getContestByThreadId(threadId);
    }

    public static List<ContestWrapper> findContestsByActiveFeatured(
            Boolean active, Boolean featured) {
        return contestClient.findContestsByActiveFeatured(active, featured);
    }

    public static List<ContestWrapper> findContestsByActive(boolean active) {
        return contestClient.findContestsByActive(active);
    }

    public static List<ContestWrapper> findContestsByTierAndOntologyTermIds(
            Long contestTier, List<Long> focusAreaOntologyTerms) {
        return contestClient
                .findContestsByTierAndOntologyTermIds(contestTier, focusAreaOntologyTerms);
    }

    public static List<ContestWrapper> getContestMatchingOntologyTerms(
            List<Long> ontologyTermIds) {
        return contestClient.getContestMatchingOntologyTerms(ontologyTermIds);
    }

    public static List<ContestWrapper> getContestByOntologyTerm(
            Long ontologyTermId, Boolean getActive) {
        return contestClient.getContestByOntologyTerm(ontologyTermId, getActive);
    }

    public static int getNumberOfContestsByOntologyTerm(
            Long ontologyTermId) {
        return contestClient.getNumberOfContestsByOntologyTerm(ontologyTermId);
    }

    public static int getNumberOfAllContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestClient.getNumberOfAllContestsInCollectionCard(collectionCardId, viewType, onlyFeatured);
    }

    public static int getNumberOfPriorContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestClient.getNumberOfPriorContestsInCollectionCard(collectionCardId, viewType, onlyFeatured);
    }

    public static int getNumberOfActiveContestsInCollectionCard(Long collectionCardId, String viewType, boolean onlyFeatured) {
        return contestClient.getNumberOfActiveContestsInCollectionCard(collectionCardId, viewType, onlyFeatured);
    }

    public static boolean updateContestCollectionCard(IContestCollectionCard contestCollectionCard) {
        return contestClient.updateContestCollectionCard(contestCollectionCard);
    }

    public static IContestCollectionCard createContestCollectionCard(
            IContestCollectionCard contestCollectionCard) {
        return contestClient.createContestCollectionCard(contestCollectionCard);
    }

    public static List<ContestWrapper> getSubContestsByOntologySpaceId(
            Long contestId, Long ontologySpaceId) {
        return contestClient.getSubContestsByOntologySpaceId(contestId, ontologySpaceId);
    }

    public static void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestClient.forcePromotionOfProposalInPhase(proposalId, contestPhaseId);
    }

    public static List<ContestWrapper> getAllContests() {
        return contestClient.getAllContests();
    }
    public static List<ContestWrapper> getAllContestsInYear(Integer contestYear) {
        return contestClient.getAllContestsInYear(contestYear);
    }
    public static List<ContestWrapper> getContestsByProposalTemplateId(
            Long proposalTemplateId) {
        return contestClient.getContestsByProposalTemplateId(proposalTemplateId);
    }

    public static List<ContestWrapper> getContestsByContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getContestsByContestScheduleId(contestScheduleId);
    }

    public static List<ContestWrapper> getContestsByActivePrivate(
            boolean contestActive, boolean contestPrivate) {
        return contestClient.getContestsByActivePrivate(contestActive, contestPrivate);
    }

    public static List<ContestWrapper> getContests(Boolean contestActive,
            Boolean contestPrivate, Long contestTypeId) {
        return contestClient
                .getContests(contestActive, contestPrivate, contestTypeId);
    }

    public static int countContests(Boolean contestActive,
            Boolean contestPrivate, Long contestTypeId) {
        return contestClient.countContests(contestActive, contestPrivate, contestTypeId);
    }

    public static List<ContestWrapper> getContestsByContestTypeId(
            Long contestTypeId) {
        return contestClient.getContestsByContestTypeId(contestTypeId);
    }

    public static IContestSchedule createContestSchedule(
            IContestSchedule contestSchedule) {
        return contestClient.createContestSchedule(contestSchedule);
    }

    public static boolean updateContestSchedule(IContestSchedule contestSchedule) {
        return contestClient.updateContestSchedule(contestSchedule);
    }

    public static IContestSchedule getContestSchedule(long id) {
        return contestClient.getContestSchedule(id);
    }

    public static boolean isContestScheduleUsed(long contestScheduleId) {
        return contestClient.isContestScheduleUsed(contestScheduleId);
    }

    public static List<IContestSchedule> getAllContestSchedules() {
        return contestClient.getAllContestSchedules();
    }

    public static boolean deleteContestSchedule(long contestScheduleId) {
        return contestClient.deleteContestSchedule(contestScheduleId);
    }

    public static List<Long> getModelIds(long contestId) throws ContestNotFoundException {
        return contestClient.getModelIds(contestId);
    }

    public static List<ContestPhaseWrapper> getVisibleContestPhases(
            Long contestId) {
        return contestClient.getVisibleContestPhases(contestId);
    }

    public static Integer getPointsAccessibleForActivePhaseOfContest(
            ContestWrapper contest) {
        return contestClient.getPointsAccessibleForActivePhaseOfContest(contest);
    }

    public static ContestPhaseWrapper getActivePhase(Long contestId) {
        return contestClient.getActivePhase(contestId);
    }

    public static IContestPhaseType getContestPhaseType(
            Long contestPhaseTypeId) {
        return contestClient.getContestPhaseType(contestPhaseTypeId);
    }

    public static void deleteContestPhase(Long contestPhaseId) {
        contestClient.deleteContestPhase(contestPhaseId);
    }

    public static boolean updateContestPhase(ContestPhaseWrapper contestPhase) {
        return contestClient.updateContestPhase(contestPhase);
    }
    public static List<Long> getContestYears() {
        return contestClient.getContestYears();
    }

    public static ContestPhaseWrapper createContestPhase(
            ContestPhaseWrapper contestPhase) {
        return contestClient.createContestPhase(contestPhase);
    }

    public static List<ContestPhaseWrapper> getAllContestPhases(
            Long contestId) {
        return contestClient.getAllContestPhases(contestId);
    }

    public static List<ContestPhaseWrapper> getPhasesForContestScheduleIdAndContest(
            Long contestScheduleId, Long contestId) {
        return contestClient.getPhasesForContestScheduleIdAndContest(contestScheduleId, contestId);
    }

    public static List<ContestPhaseWrapper> getTemplatePhasesForContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getTemplatePhasesForContestScheduleId(contestScheduleId);
    }

    public static List<ContestPhaseWrapper> getContestPhasesByType(long contestPhaseTypeId) {
        return contestClient.getContestPhasesByType(contestPhaseTypeId);
    }

    public static ContestPhaseWrapper getContestPhase(Long contestPhaseId) {
        return contestClient.getContestPhase(contestPhaseId);
    }

    public static List<IContestPhaseType> getAllContestPhaseTypes() {
        return contestClient.getAllContestPhaseTypes();
    }

    public static Integer countContestsByContestType(Long contestTypeId) {
        return contestClient.countContestsByContestType(contestTypeId);
    }

    public static List<ContestWrapper> getContestsByContestType(
            Long contestTypeId) {
        return contestClient.getContestsByContestType(contestTypeId);
    }
    public static Map<Long, String> getModelIdsAndNames(long contestId){
        return contestClient.getModelIdsAndNames(contestId);
    }

    public static String getContestPhaseName(ContestPhaseWrapper ck) {
        return contestClient.getContestPhaseName(ck);
    }

    public static IContestPhaseRibbonType getContestPhaseRibbonType(long id) {
        return contestClient.getContestPhaseRibbonType(id);
    }

    public static List<IContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return contestClient.getAllContestPhaseRibbonType();
    }

    public static boolean isMemberSubscribedToContest(long contestId, long userId) {
        return contestClient.isMemberSubscribedToContest(contestId, userId);
    }

    public static void subscribeMemberToContest(long contestId, long userId) {
        contestClient.subscribeMemberToContest(contestId, userId);
    }

    public static void unsubscribeMemberFromContest(long contestId, long userId) {
        contestClient.unsubscribeMemberFromContest(contestId, userId);
    }

    public static boolean isContestTitleYearUnique(String contestShortName, Long year,Long currentContestId) {
        return contestClient.isContestTitleYearUnique(contestShortName, year, currentContestId);
    }

    public static List<IContestCollectionCard> getSubContestCollectionCards(
            long parentCollectionCardId) {
        return contestClient.getSubContestCollectionCards(parentCollectionCardId);
    }

    public static List<IContestCollectionCard> getAllContestCollectionCards() {
        return contestClient.getAllContestCollectionCards();
    }

    public static IContestCollectionCard getContestCollectionCard(long id) {
        return contestClient.getContestCollectionCard(id);
    }

    public static boolean deleteContestCollectionCard(long id) {
        return contestClient.deleteContestCollectionCard(id);
    }

    public static int autoPromoteProposals() {
        return contestClient.autoPromoteProposals();
    }

    public static List<ContestWrapper> findPublicContests(String contestName, List<Long> ontologyTermIds,
            List<Long> contestTypeIds, List<Long> contestTiers){
        return contestClient.findPublicContests(contestName, ontologyTermIds,
                contestTypeIds, contestTiers);
    }

    public static boolean deleteContest(long contestId) {
        return contestClient.deleteContest(contestId);
    }
}
