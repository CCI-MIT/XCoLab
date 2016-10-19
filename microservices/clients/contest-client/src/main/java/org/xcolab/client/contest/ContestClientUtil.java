package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestCollectionCard;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ContestClientUtil {

    private static final RestService contestService = new RestService("contest-service");

    private static final ContestClient contestClient = ContestClient.fromService(contestService);

    private ContestClientUtil() {
    }

    public static ContestClient getContestClient() {
        return contestClient;
    }

    public static Contest getContest(long contestId) throws ContestNotFoundException {
        return contestClient.getContest(contestId);
    }

    public static Contest getContestByContestUrlNameContestYear(
            String contestUrlName, Long contestYear) {
        return contestClient.getContestByContestUrlNameContestYear(contestUrlName, contestYear);
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

    public static boolean isContestShared(long contestId) {
        return contestClient.isContestShared(contestId);
    }

    public static List<Contest> findContestsByActiveFeatured(
            Boolean active, Boolean featured) {
        return contestClient.findContestsByActiveFeatured(active, featured);
    }

    public static List<Contest> findContestsTierLevelAndOntologyTermIds(
            Long contestTier, List<Long> focusAreaOntologyTerms) {
        return contestClient
                .findContestsTierLevelAndOntologyTermIds(contestTier, focusAreaOntologyTerms);
    }

    public static List<Contest> getContestMatchingOntologyTerms(
            List<Long> ontologyTermIds) {
        return contestClient.getContestMatchingOntologyTerms(ontologyTermIds);
    }

    public static List<Contest> getContestByOntologyTerm(
            Long ontologyTermId) {
        return contestClient.getContestByOntologyTerm(ontologyTermId);
    }

    public static int getNumberOfContestsByOntologyTerm(
            Long ontologyTermId) {
        return contestClient.getNumberOfContestsByOntologyTerm(ontologyTermId);
    }

    public static int getNumberOfContestsInCollectionCard(Long collectionCardId, Boolean countOnlyActive) {
        return contestClient.getNumberOfContestsInCollectionCard(collectionCardId, countOnlyActive);
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

    public static List<Contest> getContestsByPlanTemplateId(
            Long planTemplateId) {
        return contestClient.getContestsByPlanTemplateId(planTemplateId);
    }

    public static List<Contest> getContestsByContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getContestsByContestScheduleId(contestScheduleId);
    }

    public static List<Contest> getContestsByActivePrivate(
            boolean contestActive, boolean contestPrivate) {
        return contestClient.getContestsByActivePrivate(contestActive, contestPrivate);
    }

    public static List<Contest> getContestsByActivePrivateType(
            boolean contestActive, boolean contestPrivate, Long contestTypeId) {
        return contestClient
                .getContestsByActivePrivateType(contestActive, contestPrivate, contestTypeId);
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

    public static void deleteContestPhase(Long contestPhasePK) {
        contestClient.deleteContestPhase(contestPhasePK);
    }

    public static boolean updateContestPhase(ContestPhase contestPhase) {
        return contestClient.updateContestPhase(contestPhase);
    }

    public static ContestPhase createContestPhase(
            ContestPhase contestPhase) {
        return contestClient.createContestPhase(contestPhase);
    }

    public static List<ContestPhase> getAllContestPhases(
            Long contestPK) {
        return contestClient.getAllContestPhases(contestPK);
    }

    public static List<ContestPhase> getPhasesForContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getPhasesForContestScheduleId(contestScheduleId);
    }

    public static List<ContestPhase> getPhasesForContestScheduleIdAndContest(
            Long contestScheduleId, Long contestPK) {
        return contestClient.getPhasesForContestScheduleIdAndContest(contestScheduleId, contestPK);
    }

    public static List<ContestPhase> getTemplatePhasesForContestScheduleId(
            Long contestScheduleId) {
        return contestClient.getTemplatePhasesForContestScheduleId(contestScheduleId);
    }

    public static ContestPhase getContestPhase(Long contestPhaseId) {
        return contestClient.getContestPhase(contestPhaseId);
    }

    public static List<ContestPhaseType> getAllContestPhaseTypes() {
        return contestClient.getAllContestPhaseTypes();
    }

    public static String getContestStatusStr(Long contestPhaseId) {
        return contestClient.getContestStatusStr(contestPhaseId);
    }

    public static ContestType getContestType(long id) {
        return contestClient.getContestType(id);
    }

    public static List<ContestType> getActiveContestTypes() {
        return contestClient.getActiveContestTypes();
    }

    public static List<ContestType> getAllContestTypes() {
        return contestClient.getAllContestTypes();
    }

    public static Integer countContestsByContestType(Long contestTypeId) {
        return contestClient.countContestsByContestType(contestTypeId);
    }

    public static List<Contest> getContestsByContestType(
            Long contestTypeId) {
        return contestClient.getContestsByContestType(contestTypeId);
    }

    public static String getContestPhaseName(ContestPhase ck) {
        return contestClient.getContestPhaseName(ck);
    }

    public static ContestPhaseRibbonType getContestPhaseRibbonType(long id_) {
        return contestClient.getContestPhaseRibbonType(id_);
    }

    public static List<ContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return contestClient.getAllContestPhaseRibbonType();
    }

    public static boolean isMemberSubscribedToContest(long contestPK, long userId) {
        return contestClient.isMemberSubscribedToContest(contestPK, userId);
    }

    public static void subscribeMemberToContest(long contestPK, long userId) {
        contestClient.subscribeMemberToContest(contestPK, userId);
    }

    public static void unsubscribeMemberFromContest(long contestPK, long userId) {
        contestClient.unsubscribeMemberFromContest(contestPK, userId);
    }

    public static ContestClient fromService(RestService contestService) {
        return ContestClient.fromService(contestService);
    }

    public static List<ContestCollectionCard> getSubContestCollectionCards(
            long parentCollectionCardId) {
        return contestClient.getSubContestCollectionCards(parentCollectionCardId);
    }

    public static List<ContestCollectionCard> getSubContestCollectionCards() {
        return contestClient.getSubContestCollectionCards();
    }

    public static ContestCollectionCard getContestCollectionCard(long id) {
        return contestClient.getContestCollectionCard(id);
    }


}
