package org.xcolab.service.contest.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ContestCollectionCard;
import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestDiscussion;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestType;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestcollectioncard.ContestCollectionCardDao;
import org.xcolab.service.contest.domain.contestdiscussion.ContestDiscussionDao;
import org.xcolab.service.contest.domain.contesttype.ContestTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.collectioncard.CollectionCardService;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private CollectionCardService collectionCardService;

    @Autowired
    private ContestDao contestDao;

    @Autowired
    private ContestCollectionCardDao contestCollectionCardDao;

    @Autowired
    private final ContestTypeDao contestTypeDao;

    @Autowired
    private final ContestDiscussionDao contestDiscussionDao;


    @Autowired
    public ContestController(ContestService contestService, ContestDao contestDao,
            ContestTypeDao contestTypeDao, ContestDiscussionDao contestDiscussionDao) {
        this.contestService = contestService;
        this.contestDao = contestDao;
        this.contestTypeDao = contestTypeDao;
        this.contestDiscussionDao = contestDiscussionDao;
    }

    @GetMapping(value = "/contestCollectionCards/{contestCollectionCardId}")
    public ContestCollectionCard getContestCollectionCard( @PathVariable long contestCollectionCardId) throws NotFoundException {
        return contestCollectionCardDao.get(contestCollectionCardId);
    }

    @PostMapping(value = "/contestCollectionCards")
    public ContestCollectionCard createContestCollectionCard(@RequestBody ContestCollectionCard contestCollectionCard) {
        return contestCollectionCardDao.create(contestCollectionCard);
    }

    @PutMapping(value = "/contestCollectionCards/{contestCollectionCardId}")
    public boolean updateContestCollectionCard(@RequestBody ContestCollectionCard contestCollectionCard,
            @PathVariable long contestCollectionCardId) throws NotFoundException {

        if (contestCollectionCardDao.get(contestCollectionCardId) == null) {
            throw new NotFoundException("No ContestCollectionCard with id " + contestCollectionCardId);
        } else {
            return contestCollectionCardDao.update(contestCollectionCard);
        }
    }

    @GetMapping(value = "/contestCollectionCards")
    public List<ContestCollectionCard> getContestCollectionCards(@RequestParam(required=false) Long parentCollectionCardId) throws NotFoundException{
        return contestCollectionCardDao.findByGiven(parentCollectionCardId);
    }

    @DeleteMapping(value = "/contestCollectionCards/{contestCollectionCardId}")
    public boolean deleteContestCollectionCard(@PathVariable long contestCollectionCardId) {
        return collectionCardService.deleteContestCollectionCardAndMoveChildren(contestCollectionCardId);
    }

    @RequestMapping(value = "/contests", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Contest> getContests(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String contestUrlName,
            @RequestParam(required = false) Long contestYear,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) Long contestTier,
            @RequestParam(required = false) Long contestScheduleId,
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) List<Long> focusAreaOntologyTerms,
            @RequestParam(required = false) Long contestTypeId,
            @RequestParam(required = false) Boolean contestPrivate){
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return contestDao.findByGiven(paginationHelper, contestUrlName, contestYear, active, featured, contestTier,
                focusAreaOntologyTerms, contestScheduleId, planTemplateId, contestTypeId,contestPrivate);
    }

    @RequestMapping(value = "/contests/getContestMatchingOntologyTerms", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Contest> getContestMatchingOntologyTerms(
            @RequestParam(required = false) List<Long> focusAreaOntologyTerms){
        return contestService.getContestsMatchingOntologyTerms(focusAreaOntologyTerms);
    }

    @RequestMapping(value = "/contests/getContestsByOntologyTerm", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Contest> getContestsByOntologyTerm(
            @RequestParam(required = false) Long focusAreaOntologyTerm, @RequestParam(required = false) Boolean getActive, @RequestParam(required = false) Boolean onlyPrivate){
        return contestService.getContestsByOntologyTerm(focusAreaOntologyTerm, getActive, onlyPrivate);
    }

    @RequestMapping(value = "/contests/getNumberOfContestsByOntologyTerm", method = {RequestMethod.GET, RequestMethod.HEAD})
    public int getNumberOfContestsByOntologyTerm(
            @RequestParam(required = false) Long focusAreaOntologyTerm){
        return contestService.getNumberOfContestsByOntologyTerm(focusAreaOntologyTerm);
    }

    @RequestMapping(value = "contests/getNumberOfActiveContestsInCollectionCard", method = {RequestMethod.GET, RequestMethod.HEAD})
    public int getNumberOfActiveContestsInCollectionCard(@RequestParam(required = true) long collectionCardId,
            @RequestParam(required = true) boolean onlyFeatured,
            @RequestParam(required = true) String viewType) {
        return collectionCardService.getNumberOfContestsInCollectionCard(collectionCardId, true, viewType, onlyFeatured);
    }

    @RequestMapping(value = "contests/getNumberOfPriorContestsInCollectionCard", method = {RequestMethod.GET, RequestMethod.HEAD})
    public int getNumberOfPriorContestsInCollectionCard(@RequestParam(required = true) long collectionCardId,
            @RequestParam(required = true) boolean onlyFeatured,
            @RequestParam(required = true) String viewType) {
        return collectionCardService.getNumberOfContestsInCollectionCard(collectionCardId, false, viewType, onlyFeatured);
    }

    @RequestMapping(value = "contests/getNumberOfAllContestsInCollectionCard", method = {RequestMethod.GET, RequestMethod.HEAD})
    public int getNumberOfAllContestsInCollectionCard(@RequestParam(required = true) long collectionCardId,
            @RequestParam(required = true) boolean onlyFeatured,
            @RequestParam(required = true) String viewType) {
        return collectionCardService.getNumberOfContestsInCollectionCard(collectionCardId, null, viewType, onlyFeatured);
    }

    @RequestMapping(value = "/contests/{contestId}/subContestsByOntologySpaceId", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Contest> getSubContestsByOntologySpaceId(
            @PathVariable long contestId,
            @RequestParam Long ontologySpaceId) {

        return contestService.getSubContestsByOntologySpaceId(contestId, ontologySpaceId);
    }

    @PostMapping(value = "/contests")
    public Contest createContest(@RequestBody Contest contest) {
        contest.setCreated(new Timestamp(new Date().getTime()));
        contest.setUpdated(new Timestamp(new Date().getTime()));
        return this.contestDao.create(contest);
    }

    @PutMapping(value = "/contests/{contestPK}")
    public boolean updateContest(@RequestBody Contest contest,
                                 @PathVariable long contestPK) throws NotFoundException {

        if (contestDao.get(contestPK) == null) {
            throw new NotFoundException("No Contest with id " + contestPK);
        } else {
            contest.setUpdated(new Timestamp(new Date().getTime()));
            return contestDao.update(contest);
        }
    }

    @RequestMapping(value = "/contestDiscussions", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestDiscussion> getContestDiscussions(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long contestId,
            @RequestParam(required = false) String tab){
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return contestDiscussionDao.findByGiven(paginationHelper, contestId, tab);
    }

    @PostMapping(value = "/contestDiscussions")
    public ContestDiscussion createContestDiscussion(@RequestBody ContestDiscussion contestDiscussion) {
        return this.contestDiscussionDao.create(contestDiscussion);
    }

    @GetMapping("/contestDiscussions/{discussionId}")
    public ContestDiscussion getContestDiscussion(@PathVariable long discussionId) throws NotFoundException {
        return contestDiscussionDao.get(discussionId).orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/contestDiscussions/{discussionId}")
    public boolean updateContestDiscussion(@RequestBody ContestDiscussion contestDiscussion,
            @PathVariable long discussionId) throws NotFoundException {

        if (contestDiscussionDao.get(discussionId) == null) {
            throw new NotFoundException("No ContestDiscussion with id " + discussionId);
        } else {
            return contestDiscussionDao.update(contestDiscussion);
        }
    }

    @RequestMapping(value = "/contests/countByContestType", method = RequestMethod.GET)
    public Integer countByContestType(@RequestParam("contestTypeId") Long contestTypeId) throws NotFoundException {
        return contestDao.countByGiven(null, null, null, null, null, null, null, null, contestTypeId, null);
    }


    @RequestMapping(value = "/contests/{contestId}/activePhase", method = RequestMethod.GET)
    public ContestPhase getActivePhaseForContest(@PathVariable long contestId) throws NotFoundException {

        ContestPhase activePhase = contestService.getActiveOrLastPhase(contestId);
        if (activePhase == null) {
            throw new NotFoundException();
        }
        return activePhase;

    }

    @GetMapping("/contests/{contestId}")
    public Contest getContest(@PathVariable long contestId) throws NotFoundException {
        return contestDao.get(contestId);
    }

    @GetMapping("/contests/{contestId}/isShared")
    public boolean isContestShared(@PathVariable long contestId) {
        return contestDao.isShared(contestId);
    }

    @GetMapping("/contests/{contestId}/visiblePhases")
    public List<ContestPhase> getVisiblePhases(@PathVariable Long contestId) {
        return contestService.getVisiblePhases(contestId);
    }

    @GetMapping(value = "/contestTypes/{contestTypeId}")
    public ContestType getContestType(@PathVariable long contestTypeId) throws NotFoundException {
        return contestTypeDao.get(contestTypeId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestType> getContestTypes() {
        return contestTypeDao.findByGiven();
    }



    @RequestMapping(value = "contests/findContestsByName", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Contest> findContestsByName(
            @RequestParam("contestName") String contestName,
            @RequestParam("ontologyTermIds") List<Long> ontologyTermIds,
            @RequestParam("contestTypeIds") List<Long> contestTypeIds){

        return contestService.findContestsByName(contestName, ontologyTermIds, contestTypeIds);
    }
}
