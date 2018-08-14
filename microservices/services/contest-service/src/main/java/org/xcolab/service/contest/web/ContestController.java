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

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.model.tables.pojos.ContestDiscussion;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestTranslation;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.domain.contestdiscussion.ContestDiscussionDao;
import org.xcolab.service.contest.domain.contesttranslation.ContestTranslationDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.collectioncard.CollectionCardService;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.contest.service.ontology.OntologyService;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.spring.web.annotation.ListMapping;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ContestController {

    private final ContestDao contestDao;
    private final ContestTranslationDao contestTranslationDao;
    private final ContestDiscussionDao contestDiscussionDao;
    private final ContestCollectionCardDao contestCollectionCardDao;

    private final ContestService contestService;
    private final CollectionCardService collectionCardService;
    private final OntologyService ontologyService;

    @Autowired
    public ContestController(ContestTranslationDao contestTranslationDao,
            ContestService contestService, CollectionCardService collectionCardService,
            ContestDao contestDao, ContestCollectionCardDao contestCollectionCardDao,
            ContestDiscussionDao contestDiscussionDao, OntologyService ontologyService) {
        this.contestTranslationDao = contestTranslationDao;
        this.contestService = contestService;
        this.collectionCardService = collectionCardService;
        this.contestDao = contestDao;
        this.contestCollectionCardDao = contestCollectionCardDao;
        this.contestDiscussionDao = contestDiscussionDao;
        this.ontologyService = ontologyService;
    }

    @GetMapping("/contestCollectionCards/{contestCollectionCardId}")
    public ContestCollectionCard getContestCollectionCard(
            @PathVariable long contestCollectionCardId) throws NotFoundException {
        return contestCollectionCardDao.get(contestCollectionCardId);
    }

    @PostMapping("/contestCollectionCards")
    public ContestCollectionCard createContestCollectionCard(
            @RequestBody ContestCollectionCard contestCollectionCard) {
        return contestCollectionCardDao.create(contestCollectionCard);
    }

    @PutMapping("/contestCollectionCards/{contestCollectionCardId}")
    public boolean updateContestCollectionCard(
            @RequestBody ContestCollectionCard contestCollectionCard,
            @PathVariable long contestCollectionCardId) throws NotFoundException {

        if (contestCollectionCardDao.get(contestCollectionCardId) == null) {
            throw new NotFoundException(
                    "No ContestCollectionCard with id " + contestCollectionCardId);
        } else {
            return contestCollectionCardDao.update(contestCollectionCard);
        }
    }

    @GetMapping("/contestCollectionCards")
    public List<ContestCollectionCard> getContestCollectionCards(
            @RequestParam(required = false) Long parentCollectionCardId) throws NotFoundException {
        return contestCollectionCardDao.findByGiven(parentCollectionCardId);
    }

    @DeleteMapping("/contestCollectionCards/{contestCollectionCardId}")
    public boolean deleteContestCollectionCard(@PathVariable long contestCollectionCardId) {
        return collectionCardService
                .deleteContestCollectionCardAndMoveChildren(contestCollectionCardId);
    }

    @ListMapping("/contests")
    public List<Contest> getContests(HttpServletResponse response,
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
        response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                Integer.toString(contestDao.countByGiven(contestUrlName, contestYear, active, featured,
                        contestTiers, focusAreaIds, contestScheduleId, proposalTemplateId,
                        contestTypeIds, contestPrivate, searchTerm)));
        final List<Contest> contests = contestDao
                .findByGiven(paginationHelper, contestUrlName, contestYear, active, featured,
                        contestTiers, focusAreaIds, contestScheduleId, proposalTemplateId,
                        contestTypeIds, contestPrivate, searchTerm);
        if (StringUtils.isNotEmpty(lang) && !"en".equalsIgnoreCase(lang)) {
            return contestService.resolveTranslations(contests, lang);
        }
        return contests;
    }

    @ListMapping("/contests/getContestMatchingOntologyTerms")
    public List<Contest> getContestMatchingOntologyTerms(
            @RequestParam(required = false) List<Long> focusAreaOntologyTerms) {
        return contestService.getContestsMatchingOntologyTerms(focusAreaOntologyTerms);
    }

    @ListMapping("/contestyears")
    public List<Long> getContestYears() {
        return contestDao.getContestYears();
    }

    @GetMapping("/contests/isContestTitleYearUnique")
    public Boolean isContestTitleYearUnique(@RequestParam(required = false) String contestShortName,
            @RequestParam(required = false) Long year,
            @RequestParam(required = false) Long currentContestId) {

        return contestService.isContestTitleYearUnique(contestShortName, year, currentContestId);
    }

    @ListMapping("/contests/getContestsByOntologyTerm")
    public List<Contest> getContestsByOntologyTerm(
            @RequestParam(required = false) Long focusAreaOntologyTerm,
            @RequestParam(required = false) Boolean getActive,
            @RequestParam(required = false) Boolean onlyPrivate) {
        return contestService
                .getContestsByOntologyTerm(focusAreaOntologyTerm, getActive, onlyPrivate);
    }

    @GetMapping("/contests/getNumberOfContestsByOntologyTerm")
    public int getNumberOfContestsByOntologyTerm(
            @RequestParam(required = false) Long focusAreaOntologyTerm) {
        return contestService.getNumberOfContestsByOntologyTerm(focusAreaOntologyTerm);
    }

    @GetMapping("contests/getNumberOfActiveContestsInCollectionCard")
    public int getNumberOfActiveContestsInCollectionCard(@RequestParam long collectionCardId,
            @RequestParam boolean onlyFeatured, @RequestParam String viewType) {
        return collectionCardService
                .getNumberOfContestsInCollectionCard(collectionCardId, true, viewType,
                        onlyFeatured);
    }

    @GetMapping("contests/getNumberOfPriorContestsInCollectionCard")
    public int getNumberOfPriorContestsInCollectionCard(@RequestParam long collectionCardId,
            @RequestParam boolean onlyFeatured, @RequestParam String viewType) {
        return collectionCardService
                .getNumberOfContestsInCollectionCard(collectionCardId, false, viewType,
                        onlyFeatured);
    }

    @GetMapping("contests/getNumberOfAllContestsInCollectionCard")
    public int getNumberOfAllContestsInCollectionCard(@RequestParam long collectionCardId,
            @RequestParam boolean onlyFeatured, @RequestParam String viewType) {
        return collectionCardService
                .getNumberOfContestsInCollectionCard(collectionCardId, null, viewType,
                        onlyFeatured);
    }

    @ListMapping("/contests/{contestId}/subContestsByOntologySpaceId")
    public List<Contest> getSubContestsByOntologySpaceId(@PathVariable long contestId,
            @RequestParam Long ontologySpaceId) {

        return contestService.getSubContestsByOntologySpaceId(contestId, ontologySpaceId);
    }

    @PostMapping("/contests")
    public Contest createContest(@RequestBody Contest contest) {
        contest.setCreatedAt(new Timestamp(new Date().getTime()));
        contest.setUpdatedAt(new Timestamp(new Date().getTime()));
        return this.contestDao.create(contest);
    }

    @GetMapping("/contests/{contestId}")
    public Contest getContest(@PathVariable long contestId,
            @RequestParam(required = false) String lang) throws NotFoundException {
        final Contest contest = contestDao.get(contestId);
        if (StringUtils.isNotEmpty(lang) && !"en".equalsIgnoreCase(lang)) {
            return contestService.resolveTranslation(contest, lang);
        }
        return contest;
    }

    @PutMapping("/contests/{contestId}")
    public boolean updateContest(@RequestBody Contest contest, @PathVariable long contestId)
            throws NotFoundException {

        if (contestDao.get(contestId) == null) {
            throw new NotFoundException("No Contest with id " + contestId);
        } else {
            contest.setUpdatedAt(new Timestamp(new Date().getTime()));
            return contestDao.update(contest);
        }
    }

    @DeleteMapping("/contests/{contestId}")
    public boolean deleteContest(@PathVariable long contestId) throws NotFoundException {
        if (contestDao.get(contestId) == null) {
            throw new NotFoundException("No Contest with id " + contestId);
        } else {
            return contestDao.delete(contestId);
        }
    }

    @PutMapping("/contests/{contestId}/translations/{lang}")
    public boolean updateTranslation(@RequestBody ContestTranslation contestTranslation,
            @PathVariable long contestId, @PathVariable String lang) {
        contestTranslation.setContestId(contestId);
        contestTranslation.setLang(lang);
        if (contestTranslationDao.exists(contestId, lang)) {
            return contestTranslationDao.update(contestTranslation);
        } else {
            contestTranslationDao.create(contestTranslation);
            return true;
        }
    }

    @GetMapping("/contests/{contestId}/translations")
    public List<ContestTranslation> getTranslations(@RequestParam long contestId) {
        return contestTranslationDao.listByContestId(contestId);
    }

    @GetMapping("/contests/getContestByThreadId")
    public Contest getContestByThreadId(@RequestParam(required = false) Long threadId)
            throws NotFoundException {
        return contestDao.getByThreadId(threadId);
    }

    @GetMapping("/contests/getContestByResourceArticleId")
    public Contest getContestByResourceArticleId(
            @RequestParam(required = false) Long resourceArticleId) throws NotFoundException {
        return contestDao.getByResourceId(resourceArticleId);
    }

    @ListMapping("/contestDiscussions")
    public List<ContestDiscussion> getContestDiscussions(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) String tab) {
        final PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, sort);
        return contestDiscussionDao.findByGiven(paginationHelper, contestId, tab);
    }

    @PostMapping("/contestDiscussions")
    public ContestDiscussion createContestDiscussion(
            @RequestBody ContestDiscussion contestDiscussion) {
        return this.contestDiscussionDao.create(contestDiscussion);
    }

    @GetMapping("/contestDiscussions/{discussionId}")
    public ContestDiscussion getContestDiscussion(@PathVariable long discussionId)
            throws NotFoundException {
        return contestDiscussionDao.get(discussionId).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/contestDiscussions/{discussionId}")
    public boolean updateContestDiscussion(@RequestBody ContestDiscussion contestDiscussion,
            @PathVariable long discussionId) throws NotFoundException {

        if (!contestDiscussionDao.get(discussionId).isPresent()) {
            throw new NotFoundException("No ContestDiscussion with id " + discussionId);
        } else {
            return contestDiscussionDao.update(contestDiscussion);
        }
    }

    @GetMapping("/contests/{contestId}/activePhase")
    public ContestPhase getActivePhaseForContest(@PathVariable long contestId)
            throws NotFoundException {

        ContestPhase activePhase = contestService.getActiveOrLastPhase(contestId);
        if (activePhase == null) {
            throw new NotFoundException();
        }
        return activePhase;

    }

    @GetMapping("/contests/{contestId}/visiblePhases")
    public List<ContestPhase> getVisiblePhases(@PathVariable Long contestId) {
        return contestService.getVisiblePhases(contestId);
    }
}
