package org.xcolab.service.contest.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.exceptions.ContestPhaseNotFoundException;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.IContestCollectionCard;
import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.commons.spring.web.annotation.ListMapping;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.domain.contestdiscussion.ContestDiscussionDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphaseribbontype.ContestPhaseRibbonTypeDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.domain.contestschedule.ContestScheduleDao;
import org.xcolab.service.contest.domain.contesttranslation.ContestTranslationDao;
import org.xcolab.service.contest.domain.tosagreement.TosAgreementDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.collectioncard.CollectionCardService;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.service.contestphase.ContestPhaseService;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.service.contest.utils.promotion.PromotionService;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class ContestController implements IContestClient {

    private final ContestDao contestDao;
    private final ContestTranslationDao contestTranslationDao;
    private final ContestDiscussionDao contestDiscussionDao;
    private final ContestCollectionCardDao contestCollectionCardDao;

    private final ContestScheduleDao contestScheduleDao;
    private final ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao;
    private final ContestPhaseTypeDao contestPhaseTypeDao;
    private final ContestPhaseDao contestPhaseDao;
    private final TosAgreementDao tosAgreementDao;

    private final ContestService contestService;
    private final OntologyService ontologyService;
    private final CollectionCardService collectionCardService;
    private final PromotionService promotionService;
    private final ContestPhaseService contestPhaseService;

    @Autowired
    public ContestController(ContestTranslationDao contestTranslationDao,
            ContestService contestService, CollectionCardService collectionCardService,
            ContestDao contestDao, ContestCollectionCardDao contestCollectionCardDao,
            ContestDiscussionDao contestDiscussionDao,
            TosAgreementDao tosAgreementDao,
            OntologyService ontologyService,
            ContestScheduleDao contestScheduleDao,
            ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao,
            ContestPhaseTypeDao contestPhaseTypeDao,
            ContestPhaseDao contestPhaseDao,
            PromotionService promotionService,
            ContestPhaseService contestPhaseService) {
        this.contestTranslationDao = contestTranslationDao;
        this.contestService = contestService;
        this.collectionCardService = collectionCardService;
        this.contestDao = contestDao;
        this.contestCollectionCardDao = contestCollectionCardDao;
        this.contestDiscussionDao = contestDiscussionDao;
        this.tosAgreementDao = tosAgreementDao;
        this.ontologyService = ontologyService;
        this.contestScheduleDao = contestScheduleDao;
        this.contestPhaseRibbonTypeDao = contestPhaseRibbonTypeDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
        this.contestPhaseDao = contestPhaseDao;
        this.promotionService = promotionService;
        this.contestPhaseService = contestPhaseService;
    }

    @Override
    @GetMapping("/contestCollectionCards/{contestCollectionCardId}")
    public IContestCollectionCard getContestCollectionCard(
            @PathVariable Long contestCollectionCardId) {
        try {
            return contestCollectionCardDao.get(contestCollectionCardId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "No ContestCollectionCard found with id " + contestCollectionCardId);
        }
    }

    @Override
    @PostMapping("/contestCollectionCards")
    public IContestCollectionCard createContestCollectionCard(
            @RequestBody IContestCollectionCard contestCollectionCard) {
        return contestCollectionCardDao.create(contestCollectionCard);
    }

    @Override
    @PutMapping("/contestCollectionCards")
    public boolean updateContestCollectionCard(
            @RequestBody IContestCollectionCard contestCollectionCard) {

        Long id = contestCollectionCard.getId();
        try {
            if (contestCollectionCardDao.get(id) == null) {
                throw new RuntimeEntityNotFoundException(
                        "No ContestCollectionCard found with id " + id);
            } else {
                return contestCollectionCardDao.update(contestCollectionCard);
            }
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "No ContestCollectionCard found with id " + id);
        }
    }

    @Override
    @GetMapping("/contestCollectionCards")
    public List<IContestCollectionCard> getAllContestCollectionCards(
            @RequestParam(required = false) Long parentCollectionCardId) {
        try {
            return contestCollectionCardDao.findByGiven(parentCollectionCardId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "ContestCollectionCard not found with parent id " + parentCollectionCardId);
        }
    }

    @Override
    @DeleteMapping("/contestCollectionCards/{contestCollectionCardId}")
    public boolean deleteContestCollectionCard(@PathVariable Long contestCollectionCardId) {
        return collectionCardService
                .deleteContestCollectionCardAndMoveChildren(contestCollectionCardId);
    }

    @Override
    @GetMapping("/contests")
    public List<ContestWrapper> getContests(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String lang,
            @RequestParam(required = false) String contestUrlName,
            @RequestParam(required = false) Long contestYear,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) List<Long> contestTiers,
            @RequestParam(required = false) Long contestScheduleId,
            @RequestParam(required = false) Long proposalTemplateId,
            @RequestParam(required = false) List<Long> focusAreaIds,
            @RequestParam(required = false) List<Long> ontologyTermIds,
            @RequestParam(required = false) List<Long> contestTypeIds,
            @RequestParam(required = false) Boolean contestPrivate,
            @RequestParam(required = false) String searchTerm) {
        final PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, sort);
        if (ontologyTermIds != null) {
            List<Long> descendantFocusAreas =
                    ontologyService.getFocusAreaIdsForDescendantTerms(ontologyTermIds);
            if (focusAreaIds != null) {
                focusAreaIds.addAll(descendantFocusAreas);
            } else {
                focusAreaIds = descendantFocusAreas;
            }
        }
        final List<ContestWrapper> contests = contestDao
                .findByGiven(paginationHelper, contestUrlName, contestYear, active, featured,
                        contestTiers, focusAreaIds, contestScheduleId, proposalTemplateId,
                        contestTypeIds, contestPrivate, searchTerm);
        if (StringUtils.isNotEmpty(lang) && !"en".equalsIgnoreCase(lang)) {
            return contestService.resolveTranslations(contests, lang);
        }
        return contests;
    }

    @Override
    @GetMapping("/count/contests")
    public int countContests(
            @RequestParam(required = false) Boolean contestActive,
            @RequestParam(required = false) Boolean contestPrivate,
            @RequestParam(required = false) Long contestTypeId) {
        return contestDao.countByGiven(null, null, contestActive, null,
                null, null, null, null,
                Collections.singletonList(contestTypeId), contestPrivate, null);
    }

    @Override
    @GetMapping("/contests/getContestMatchingOntologyTerms")
    public List<ContestWrapper> getContestMatchingOntologyTerms(
            @RequestParam(required = false) List<Long> focusAreaOntologyTerms) {
        return contestService.getContestsMatchingOntologyTerms(focusAreaOntologyTerms);
    }

    @Override
    @ListMapping("/contestyears")
    public List<Long> getContestYears() {
        return contestDao.getContestYears();
    }

    @Override
    @GetMapping("/contests/isContestTitleYearUnique")
    public boolean isContestTitleYearUnique(@RequestParam(required = false) String contestShortName,
            @RequestParam(required = false) Long year,
            @RequestParam(required = false) Long currentContestId) {
        return contestService.isContestTitleYearUnique(contestShortName, year, currentContestId);
    }

    @Override
    @GetMapping("/contests/getContestsByOntologyTerm")
    public List<ContestWrapper> getContestByOntologyTerm(
            @RequestParam(required = false) Long focusAreaOntologyTerm,
            @RequestParam(required = false) Boolean getActive) {
        return contestService
                .getContestsByOntologyTerm(focusAreaOntologyTerm, getActive, false);
    }

    @Override
    @GetMapping("/contests/getNumberOfContestsByOntologyTerm")
    public int getNumberOfContestsByOntologyTerm(
            @RequestParam(required = false) Long focusAreaOntologyTerm) {
        return contestService.getNumberOfContestsByOntologyTerm(focusAreaOntologyTerm);
    }

    @Override
    @GetMapping("contests/getNumberOfActiveContestsInCollectionCard")
    public int getNumberOfActiveContestsInCollectionCard(@RequestParam Long collectionCardId,
            @RequestParam String viewType, @RequestParam Boolean onlyFeatured) {
        return collectionCardService
                .getNumberOfContestsInCollectionCard(collectionCardId, true, viewType,
                        onlyFeatured);
    }

    @Override
    @GetMapping("contests/getNumberOfPriorContestsInCollectionCard")
    public int getNumberOfPriorContestsInCollectionCard(@RequestParam Long collectionCardId,
            @RequestParam String viewType,
            @RequestParam Boolean onlyFeatured) {
        return collectionCardService
                .getNumberOfContestsInCollectionCard(collectionCardId, false, viewType,
                        onlyFeatured);
    }

    @Override
    @GetMapping("contests/getNumberOfAllContestsInCollectionCard")
    public int getNumberOfAllContestsInCollectionCard(@RequestParam Long collectionCardId,
            @RequestParam String viewType, @RequestParam Boolean onlyFeatured) {
        return collectionCardService
                .getNumberOfContestsInCollectionCard(collectionCardId, null, viewType,
                        onlyFeatured);
    }

    @Override
    @GetMapping("/contests/{contestId}/subContestsByOntologySpaceId")
    public List<ContestWrapper> getSubContestsByOntologySpaceId(@PathVariable Long contestId,
            @RequestParam Long ontologySpaceId) {
        return contestService.getSubContestsByOntologySpaceId(contestId, ontologySpaceId);
    }

    @Override
    @PostMapping("/contests")
    public ContestWrapper createContest(@RequestBody ContestWrapper contest) {
        contest.setCreatedAt(new Timestamp(new Date().getTime()));
        contest.setUpdatedAt(new Timestamp(new Date().getTime()));
        return this.contestDao.create(contest);
    }

    @Override
    @GetMapping("/contests/{contestId}")
    public ContestWrapper getContest(@PathVariable Long contestId,
            @RequestParam(required = false) String lang) {
        try {
            final ContestWrapper contest = contestDao.get(contestId);
            if (StringUtils.isNotEmpty(lang) && !"en".equalsIgnoreCase(lang)) {
                return contestService.resolveTranslation(contest, lang);
            }
            return contest;
        } catch (NotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    @Override
    @PutMapping("/contests")
    public boolean updateContest(@RequestBody ContestWrapper contest) {
        Long contestId = contest.getId();
        try {
            if (contestDao.get(contestId) == null) {
                throw new RuntimeEntityNotFoundException("No ContestWrapper with id " + contestId);
            } else {
                contest.setUpdatedAt(new Timestamp(new Date().getTime()));
                return contestDao.update(contest);
            }
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("No ContestWrapper with id " + contestId);
        }
    }

    @Override
    @DeleteMapping("/contests/{contestId}")
    public boolean deleteContest(@PathVariable Long contestId) {
        try {
            if (contestDao.get(contestId) == null) {
                throw new RuntimeEntityNotFoundException("Contest not found with id " + contestId);
            } else {
                return contestDao.delete(contestId);
            }
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("Contest not found with id " + contestId);
        }
    }

    @Override
    @PutMapping("/contests/translations")
    public boolean saveTranslation(@RequestBody
            ContestTranslation contestTranslation) {
        Long contestId = contestTranslation.getContestId();
        String lang = contestTranslation.getLang();

        if (contestTranslationDao.exists(contestId, lang)) {
            return contestTranslationDao.update(contestTranslation);
        } else {
            contestTranslationDao.create(contestTranslation);
            return true;
        }
    }

    @Override
    @GetMapping("/contests/{contestId}/translations")
    public List<ContestTranslation> getTranslationsForContestId(@PathVariable Long contestId) {
        return contestTranslationDao.listByContestId(contestId);
    }

    @Override
    @GetMapping("/contests/getContestByThreadId")
    public ContestWrapper getContestByThreadId(@RequestParam(required = false) Long threadId) {
        try {
            return contestDao.getByThreadId(threadId);
        } catch (NotFoundException e) {
            throw new ContestNotFoundException("No contest with threadId " + threadId);
        }
    }

    @Override
    @GetMapping("/contests/getContestByResourceArticleId")
    public ContestWrapper getContestByResourceArticleId(
            @RequestParam(required = false) Long resourceArticleId) {
        try {
            return contestDao.getByResourceId(resourceArticleId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "Contest not found for resource article id " + resourceArticleId);
        }
    }

    @Override
    @GetMapping("/contestDiscussions")
    public List<IContestDiscussion> getContestDiscussions(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) String tab) {
        final PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, sort);
        return contestDiscussionDao.findByGiven(paginationHelper, contestId, tab);
    }

    @Override
    @PostMapping("/contestDiscussions")
    public IContestDiscussion createContestDiscussion(
            @RequestBody IContestDiscussion contestDiscussion) {
        return this.contestDiscussionDao.create(contestDiscussion);
    }

    @Override
    @GetMapping("/contests/{contestId}/activePhase")
    public ContestPhaseWrapper getActivePhase(@PathVariable Long contestId) {
        ContestPhaseWrapper activePhase = contestService.getActiveOrLastPhase(contestId);
        if (activePhase == null) {
            throw new RuntimeEntityNotFoundException(
                    "ContestPhase not found for contest id " + contestId);
        }
        return activePhase;
    }

    @Override
    @GetMapping("/contests/{contestId}/visiblePhases")
    public List<ContestPhaseWrapper> getVisibleContestPhases(@PathVariable Long contestId) {
        return contestService.getVisiblePhases(contestId);
    }

    @Override
    @PostMapping("/contestSchedules")
    public IContestSchedule createContestSchedule(@RequestBody IContestSchedule contestSchedule) {
        return this.contestScheduleDao.create(contestSchedule);
    }

    @Override
    @GetMapping("/contestSchedules/{contestScheduleId}")
    public IContestSchedule getContestSchedule(@PathVariable Long contestScheduleId) {
        try {
            return contestScheduleDao.get(contestScheduleId)
                    .orElseThrow(() -> new ContestScheduleNotFoundException(contestScheduleId));
        } catch (NotFoundException e) {
            throw new ContestScheduleNotFoundException(contestScheduleId);
        }
    }

    @Override
    @GetMapping("/contestSchedules/{contestScheduleId}/isUsed")
    public boolean isContestScheduleUsed(@PathVariable Long contestScheduleId) {
        return contestDao.existsWithScheduleId(contestScheduleId);
    }

    @Override
    @PutMapping("/contestSchedules")
    public boolean updateContestSchedule(@RequestBody IContestSchedule contestSchedule) {
        Long id = contestSchedule.getId();
        if (contestScheduleDao.exists(id)) {
            return contestScheduleDao.update(contestSchedule);
        } else {
            throw new RuntimeEntityNotFoundException("No ContestSchedule found with id " + id);
        }
    }

    @Override
    @GetMapping("/contestSchedules")
    public List<IContestSchedule> getAllContestSchedules() {
        return contestScheduleDao.findByGiven();
    }

    @Override
    @DeleteMapping("/contestSchedules/{id}")
    public boolean deleteContestSchedule(@PathVariable Long id) {
        if (contestScheduleDao.exists(id)) {
            return contestScheduleDao.delete(id);
        }
        throw new RuntimeEntityNotFoundException("No ContestSchedule found with id " + id);
    }

    @Override
    @GetMapping("/contestPhaseRibbonTypes/{contestPhaseRibbonTypeId}")
    public IContestPhaseRibbonType getContestPhaseRibbonType(
            @PathVariable Long contestPhaseRibbonTypeId) {
        try {
            return contestPhaseRibbonTypeDao.get(contestPhaseRibbonTypeId)
                    .orElseThrow(() -> new RuntimeEntityNotFoundException(
                            "ContestPhaseRibbonType not found with id "
                                    + contestPhaseRibbonTypeId));
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException(
                    "ContestPhaseRibbonType not found with id " + contestPhaseRibbonTypeId);
        }
    }

    @Override
    @GetMapping("/contestPhaseRibbonTypes")
    public List<IContestPhaseRibbonType> getAllContestPhaseRibbonType() {
        return contestPhaseRibbonTypeDao.findByGiven();
    }


    @Override
    @GetMapping("/contestPhaseTypes/{contestPhaseTypeId}")
    public IContestPhaseType getContestPhaseType(@PathVariable Long contestPhaseTypeId) {
        return contestPhaseTypeDao.get(contestPhaseTypeId)
                .orElseThrow(() -> new RuntimeEntityNotFoundException(
                        "ContestPhaseType not found with id " + contestPhaseTypeId));
    }

    @Override
    @GetMapping("/contestPhaseTypes")
    public List<IContestPhaseType> getAllContestPhaseTypes() {
        return contestPhaseTypeDao.findByGiven();
    }

    @Override
    @GetMapping("/contestPhases")
    public List<ContestPhaseWrapper> getContestPhases(
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long contestScheduleId,
            @RequestParam(required = false) Long contestPhaseTypeId) {
        return contestPhaseDao.findByGiven(contestId, contestScheduleId, contestPhaseTypeId);
    }

    @Override
    @GetMapping("/contestPhases/{phaseId}")
    public ContestPhaseWrapper getContestPhase(@PathVariable Long phaseId) {
        try {
            return contestPhaseDao.get(phaseId)
                    .orElseThrow(() -> new ContestPhaseNotFoundException(phaseId));
        } catch (NotFoundException e) {
            throw new ContestPhaseNotFoundException(phaseId);
        }
    }

    @Override
    @PostMapping("/contestPhases")
    public ContestPhaseWrapper createContestPhase(@RequestBody ContestPhaseWrapper contestPhase) {
        return this.contestPhaseDao.create(contestPhase);
    }

    @Override
    @PutMapping("/contestPhases")
    public boolean updateContestPhase(@RequestBody ContestPhaseWrapper contestPhase) {
        if (contestPhaseDao.exists(contestPhase.getId())) {
            return contestPhaseDao.update(contestPhase);
        } else {
            throw new RuntimeEntityNotFoundException(
                    "ContestPhase not found with id " + contestPhase.getId());
        }
    }

    @Override
    @DeleteMapping("/contestPhases/{contestPhaseId}")
    public boolean deleteContestPhase(@PathVariable Long contestPhaseId) {
        if (contestPhaseDao.exists(contestPhaseId)) {
            return contestPhaseDao.delete(contestPhaseId);
        }
        throw new RuntimeEntityNotFoundException(
                "ContestPhase not found with id " + contestPhaseId);
    }

    @Override
    @GetMapping("/contestPhases/{phaseId}/proposalDiscussionThreads")
    public List<Long> getProposalDiscussionThreads(@PathVariable Long phaseId) {
        if (!contestPhaseDao.exists(phaseId)) {
            throw new RuntimeEntityNotFoundException("ContestPhase not found with id " + phaseId);
        }
        return contestPhaseDao.getProposalDiscussionThreadsInPhase(phaseId);
    }

    @Override
    @PutMapping("/contestPhases/{contestPhaseId}/forcePromotionOfProposalInContestPhaseId")
    public boolean forcePromotionOfProposalInPhase(@PathVariable Long contestPhaseId,
            @RequestParam Long proposalId) {
        try {
            if (contestPhaseDao.exists(contestPhaseId)) {
                contestPhaseService.forcePromotionOfProposalInPhase(proposalId, contestPhaseId);
                return true;
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException(
                "ContestPhase not found with id " + contestPhaseId);
    }

    @Override
    @GetMapping("/contestPhases/autoPromoteProposals")
    public int autoPromoteProposals() {

        Date now = new Date();
        return promotionService.doPromotion(now);
    }

    @Override
    @GetMapping("/contests/{contestId}/memberAgreedToTos/{memberId}")
    public boolean getMemberAgreedToTos(@PathVariable Long contestId, @PathVariable Long memberId) {
        return tosAgreementDao.hasMemberAgreedToContestTos(contestId, memberId);
    }

    @Override
    @PostMapping("/contests/{contestId}/memberAgreedToTos")
    public void setMemberAgreedToTos(@PathVariable Long contestId, @RequestParam Long memberId,
            @RequestBody boolean agreed) {
        tosAgreementDao.setMemberAgreedToContestTos(contestId, memberId, agreed);
    }
}
