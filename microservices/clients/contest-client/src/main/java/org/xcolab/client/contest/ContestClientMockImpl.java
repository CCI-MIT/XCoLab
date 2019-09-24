package org.xcolab.client.contest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ContestClientMockImpl implements IContestClient {

    @Override
    public ContestWrapper getContest(Long contestId, String lang) {
        return null;
    }

    @Override
    public ContestWrapper createContest(ContestWrapper contest) {
        return null;
    }

    @Override
    public boolean deleteContest(Long contestId) {
        return false;
    }

    @Override
    public boolean updateContest(ContestWrapper contest) {
        return false;
    }

    @Override
    public IContestDiscussion createContestDiscussion(IContestDiscussion contestDiscussion) {
        return null;
    }

    @Override
    public List<IContestDiscussion> getContestDiscussions(Integer startRecord, Integer limitRecord,
            String sort, Long contestId, String tab) {
        return Collections.emptyList();
    }

    @Override
    public List<ContestTranslation> getTranslationsForContestId(Long contestId) {
        return Collections.emptyList();
    }

    @Override
    public boolean saveTranslation(ContestTranslation contestTranslation) {
        return false;
    }

    @Override
    public boolean isContestTitleYearUnique(String contestShortName, Long year,
            Long currentContestId) {
        return false;
    }

    @Override
    public List<ContestWrapper> getContestMatchingOntologyTerms(List<Long> focusAreaOntologyTerms) {
        return Collections.emptyList();
    }

    @Override
    public List<Long> getContestYears() {
        return Collections.emptyList();
    }

    @Override
    public ContestWrapper getContestByThreadId(Long threadId) {
        return null;
    }

    @Override
    public ContestWrapper getContestByResourceArticleId(Long resourceArticleId) {
        return null;
    }

    @Override
    public int getNumberOfAllContestsInCollectionCard(Long collectionCardId, String viewType,
            Boolean onlyFeatured) {
        return 0;
    }

    @Override
    public int getNumberOfActiveContestsInCollectionCard(Long collectionCardId, String viewType,
            Boolean onlyFeatured) {
        return 0;
    }

    @Override
    public int getNumberOfPriorContestsInCollectionCard(Long collectionCardId, String viewType,
            Boolean onlyFeatured) {
        return 0;
    }

    @Override
    public boolean updateContestCollectionCard(IContestCollectionCard contestCollectionCard) {
        return false;
    }

    @Override
    public boolean deleteContestCollectionCard(Long contestCollectionCardId) {
        return false;
    }

    @Override
    public IContestCollectionCard createContestCollectionCard(
            IContestCollectionCard contestCollectionCard) {
        return null;
    }

    @Override
    public List<ContestWrapper> getContestByOntologyTerm(Long focusAreaOntologyTerm,
            Boolean getActive) {
        return Collections.emptyList();
    }

    @Override
    public int getNumberOfContestsByOntologyTerm(Long focusAreaOntologyTerm) {
        return 0;
    }

    @Override
    public List<ContestWrapper> getSubContestsByOntologySpaceId(Long contestId,
            Long ontologySpaceId) {
        return Collections.emptyList();
    }

    @Override
    public int autoPromoteProposals() {
        return 0;
    }

    @Override
    public boolean forcePromotionOfProposalInPhase(Long contestPhaseId, Long proposalId) {
        return false;
    }

    @Override
    public List<ContestWrapper> getContests(Integer startRecord, Integer limitRecord, String sort,
            String lang, String contestUrlName, Long contestYear, Boolean active, Boolean featured,
            List<Long> contestTiers, Long contestScheduleId, Long proposalTemplateId,
            List<Long> focusAreaIds, List<Long> ontologyTermIds, List<Long> contestTypeIds,
            Boolean contestPrivate, String searchTerm) {
        return Collections.emptyList();
    }

    @Override
    public int countContests(Boolean contestActive, Boolean contestPrivate, Long contestTypeId) {
        return 0;
    }

    @Override
    public IContestSchedule createContestSchedule(IContestSchedule contestSchedule) {
        return null;
    }

    @Override
    public boolean updateContestSchedule(IContestSchedule contestSchedule) {
        return false;
    }

    @Override
    public IContestSchedule getContestSchedule(Long contestScheduleId) {
        return null;
    }

    @Override
    public boolean isContestScheduleUsed(Long contestScheduleId) {
        return false;
    }

    @Override
    public List<IContestSchedule> getAllContestSchedules() {
        return Collections.emptyList();
    }

    @Override
    public boolean deleteContestSchedule(Long id) {
        return false;
    }

    @Override
    public List<ContestPhaseWrapper> getVisibleContestPhases(Long contestId) {
        return Collections.emptyList();
    }

    @Override
    public List<Long> getProposalDiscussionThreads(Long phaseId) {
        return Collections.emptyList();
    }

    @Override
    public ContestPhaseWrapper getActivePhase(Long contestId) {
        return null;
    }

    @Override
    public IContestPhaseType getContestPhaseType(Long contestPhaseTypeId) {
        return null;
    }

    @Override
    public boolean deleteContestPhase(Long contestPhaseId) {
        return false;
    }

    @Override
    public boolean updateContestPhase(ContestPhaseWrapper contestPhase) {
        return false;
    }

    @Override
    public ContestPhaseWrapper createContestPhase(ContestPhaseWrapper contestPhase) {
        return null;
    }

    @Override
    public List<ContestPhaseWrapper> getContestPhases(Long contestId, Long contestScheduleId,
            Long contestPhaseTypeId) {
        return Collections.emptyList();
    }

    @Override
    public ContestPhaseWrapper getContestPhase(Long phaseId) {
        return null;
    }

    @Override
    public List<IContestPhaseType> getAllContestPhaseTypes() {
        return Collections.emptyList();
    }

    @Override
    public IContestPhaseRibbonType getContestPhaseRibbonType(Long contestPhaseRibbonTypeId) {
        return null;
    }

    @Override
    public List<IContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return Collections.emptyList();
    }

    @Override
    public List<IContestCollectionCard> getAllContestCollectionCards(Long parentCollectionCardId) {
        return Collections.emptyList();
    }

    @Override
    public IContestCollectionCard getContestCollectionCard(Long contestCollectionCardId) {
        return null;
    }

    @Override
    public boolean getMemberAgreedToTos(Long contestId, Long memberId) {
        return false;
    }

    @Override
    public void setMemberAgreedToTos(Long contestId, Long memberId, boolean agreed) {
        // empty
    }
}
