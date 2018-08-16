package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.ContestDiscussion;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestTranslation;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;
import java.util.Map;

public final class ContestClientUtil {

    private static final ContestClient contestClient = ContestClient.fromNamespace(
            ServiceNamespace.instance());

    private ContestClientUtil() {
    }

    public static ContestClient getClient() {
        return contestClient;
    }

    public static Contest getContest(long contestId) {
        return contestClient.getContest(contestId);
    }

    public static Contest getContest(long contestId, String lang) {
        return contestClient.getContest(contestId, lang);
    }

    public static Contest createContest(Long userId, String name) {
        return contestClient.createContest(userId, name);
    }

    public static Contest createContest(
            Contest contest) {
        return contestClient.createContest(contest);
    }

    public static List<Contest> getContestsMatchingTier(
            Long contestTier) {
        return contestClient.getContestsMatchingTier(contestTier);
    }

    public static boolean updateContest(Contest contest) {
        return contestClient.updateContest(contest);
    }

    public static Integer getProposalCount(Long contestId) {
        return contestClient.getProposalCount(contestId);
    }

    public static Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        return contestClient.getContest(contestUrlName, contestYear);
    }

    public static Contest getContest(String contestUrlName, long contestYear, String lang)
            throws ContestNotFoundException {
        return contestClient.getContest(contestUrlName, contestYear, lang);
    }

    public static List<ContestTranslation> getTranslationsForContestId(long contestId) {
        return contestClient.getTranslationsForContestId(contestId);
    }

    public static boolean saveTranslation(ContestTranslation contestTranslation) {
        return contestClient.saveTranslation(contestTranslation);
    }

    public static ContestDiscussion createContestDiscussion(long threadId, long contestId,
            String tab) {
        return contestClient.createContestDiscussion(threadId, contestId, tab);
    }

    public static ContestDiscussion getContestDiscussion(long contestId,
            String tab) {
        return contestClient.getContestDiscussion(contestId, tab);
    }

    public static Contest getContestByResourceArticleId(Long resourceArticleId) {
        return contestClient.getContestByResourceArticleId(resourceArticleId);
    }
    public static Contest getContestByThreadId(Long threadId){
        return contestClient.getContestByThreadId(threadId);
    }

    public static List<Contest> findContestsByActiveFeatured(
            Boolean active, Boolean featured) {
        return contestClient.findContestsByActiveFeatured(active, featured);
    }

    public static List<Contest> findContestsByActive(boolean active) {
        return contestClient.findContestsByActive(active);
    }

    public static List<Contest> findContestsByTierAndOntologyTermIds(
            Long contestTier, List<Long> focusAreaOntologyTerms) {
        return contestClient
                .findContestsByTierAndOntologyTermIds(contestTier, focusAreaOntologyTerms);
    }

    public static List<Contest> getContestMatchingOntologyTerms(
            List<Long> ontologyTermIds) {
        return contestClient.getContestMatchingOntologyTerms(ontologyTermIds);
    }

    public static List<Contest> getContestByOntologyTerm(
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

    public static boolean updateContestCollectionCard(ContestCollectionCard contestCollectionCard) {
        return contestClient.updateContestCollectionCard(contestCollectionCard);
    }

    public static ContestCollectionCard createContestCollectionCard(ContestCollectionCard contestCollectionCard) {
        return contestClient.createContestCollectionCard(contestCollectionCard);
    }

    public static List<Contest> getSubContestsByOntologySpaceId(
            Long contestId, Long ontologySpaceId) {
        return contestClient.getSubContestsByOntologySpaceId(contestId, ontologySpaceId);
    }

    public static void forcePromotionOfProposalInPhase(Long proposalId, Long contestPhaseId) {
        contestClient.forcePromotionOfProposalInPhase(proposalId, contestPhaseId);
    }

    public static List<Contest> getAllContests() {
        return contestClient.getAllContests();
    }
    public static List<Contest> getAllContestsInYear(Integer contestYear) {
        return contestClient.getAllContestsInYear(contestYear);
    }
    public static List<Contest> getContestsByProposalTemplateId(
            Long proposalTemplateId) {
        return contestClient.getContestsByProposalTemplateId(proposalTemplateId);
    }

    public static List<Contest> getContestsByContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getContestsByContestScheduleId(contestScheduleId);
    }

    public static List<Contest> getContestsByActivePrivate(
            boolean contestActive, boolean contestPrivate) {
        return contestClient.getContestsByActivePrivate(contestActive, contestPrivate);
    }

    public static List<Contest> getContests(Boolean contestActive,
            Boolean contestPrivate, Long contestTypeId) {
        return contestClient
                .getContests(contestActive, contestPrivate, contestTypeId);
    }

    public static int countContests(Boolean contestActive,
            Boolean contestPrivate, Long contestTypeId) {
        return contestClient.countContests(contestActive, contestPrivate, contestTypeId);
    }

    public static List<Contest> getContestsByContestTypeId(
            Long contestTypeId) {
        return contestClient.getContestsByContestTypeId(contestTypeId);
    }

    public static ContestSchedule createContestSchedule(
            ContestSchedule contestSchedule) {
        return contestClient.createContestSchedule(contestSchedule);
    }

    public static boolean updateContestSchedule(ContestSchedule contestSchedule) {
        return contestClient.updateContestSchedule(contestSchedule);
    }

    public static ContestSchedule getContestSchedule(long id) {
        return contestClient.getContestSchedule(id);
    }

    public static boolean isContestScheduleUsed(long contestScheduleId) {
        return contestClient.isContestScheduleUsed(contestScheduleId);
    }

    public static List<ContestSchedule> getAllContestSchedules() {
        return contestClient.getAllContestSchedules();
    }

    public static boolean deleteContestSchedule(long contestScheduleId) {
        return contestClient.deleteContestSchedule(contestScheduleId);
    }

    public static List<Long> getModelIds(long contestId) throws ContestNotFoundException {
        return contestClient.getModelIds(contestId);
    }

    public static List<ContestPhase> getVisibleContestPhases(
            Long contestId) {
        return contestClient.getVisibleContestPhases(contestId);
    }

    public static Integer getPointsAccessibleForActivePhaseOfContest(
            Contest contest) {
        return contestClient.getPointsAccessibleForActivePhaseOfContest(contest);
    }

    public static ContestPhase getActivePhase(Long contestId) {
        return contestClient.getActivePhase(contestId);
    }

    public static ContestPhaseType getContestPhaseType(
            Long contestPhaseTypeId) {
        return contestClient.getContestPhaseType(contestPhaseTypeId);
    }

    public static void deleteContestPhase(Long contestPhaseId) {
        contestClient.deleteContestPhase(contestPhaseId);
    }

    public static boolean updateContestPhase(ContestPhase contestPhase) {
        return contestClient.updateContestPhase(contestPhase);
    }
    public static List<Long> getContestYears() {
        return contestClient.getContestYears();
    }

    public static ContestPhase createContestPhase(
            ContestPhase contestPhase) {
        return contestClient.createContestPhase(contestPhase);
    }

    public static List<ContestPhase> getAllContestPhases(
            Long contestId) {
        return contestClient.getAllContestPhases(contestId);
    }

    public static List<ContestPhase> getPhasesForContestScheduleIdAndContest(
            Long contestScheduleId, Long contestId) {
        return contestClient.getPhasesForContestScheduleIdAndContest(contestScheduleId, contestId);
    }

    public static List<ContestPhase> getTemplatePhasesForContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getTemplatePhasesForContestScheduleId(contestScheduleId);
    }

    public static List<ContestPhase> getContestPhasesByType(long contestPhaseTypeId) {
        return contestClient.getContestPhasesByType(contestPhaseTypeId);
    }

    public static ContestPhase getContestPhase(Long contestPhaseId) {
        return contestClient.getContestPhase(contestPhaseId);
    }

    public static List<ContestPhaseType> getAllContestPhaseTypes() {
        return contestClient.getAllContestPhaseTypes();
    }

    public static Integer countContestsByContestType(Long contestTypeId) {
        return contestClient.countContestsByContestType(contestTypeId);
    }

    public static List<Contest> getContestsByContestType(
            Long contestTypeId) {
        return contestClient.getContestsByContestType(contestTypeId);
    }
    public static Map<Long, String> getModelIdsAndNames(long contestId){
        return contestClient.getModelIdsAndNames(contestId);
    }

    public static String getContestPhaseName(ContestPhase ck) {
        return contestClient.getContestPhaseName(ck);
    }

    public static ContestPhaseRibbonType getContestPhaseRibbonType(long id) {
        return contestClient.getContestPhaseRibbonType(id);
    }

    public static List<ContestPhaseRibbonType> getAllContestPhaseRibbonType() {
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

    public static ContestClient fromService(ServiceNamespace serviceNamespace) {
        return ContestClient.fromNamespace(serviceNamespace);
    }

    public static List<ContestCollectionCard> getSubContestCollectionCards(
            long parentCollectionCardId) {
        return contestClient.getSubContestCollectionCards(parentCollectionCardId);
    }

    public static List<ContestCollectionCard> getAllContestCollectionCards() {
        return contestClient.getAllContestCollectionCards();
    }

    public static ContestCollectionCard getContestCollectionCard(long id) {
        return contestClient.getContestCollectionCard(id);
    }

    public static boolean deleteContestCollectionCard(long id) {
        return contestClient.deleteContestCollectionCard(id);
    }

    public static int autoPromoteProposals() {
        return contestClient.autoPromoteProposals();
    }

    public static List<Contest> findPublicContests(String contestName, List<Long> ontologyTermIds,
            List<Long> contestTypeIds, List<Long> contestTiers){
        return contestClient.findPublicContests(contestName, ontologyTermIds,
                contestTypeIds, contestTiers);
    }

    public static boolean deleteContest(long contestId) {
        return contestClient.deleteContest(contestId);
    }
}
