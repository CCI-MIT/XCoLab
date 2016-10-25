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


import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseRibbonType;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.model.tables.pojos.ContestSchedule;
import org.xcolab.model.tables.pojos.ContestTeamMember;
import org.xcolab.model.tables.pojos.ContestTeamMemberRole;
import org.xcolab.model.tables.pojos.ContestType;
import org.xcolab.model.tables.pojos.ImpactTemplateSeries;
import org.xcolab.model.tables.pojos.ImpactIteration;


import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphaseribbontype.ContestPhaseRibbonTypeDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.domain.contestschedule.ContestScheduleDao;
import org.xcolab.service.contest.domain.contestteammember.ContestTeamMemberDao;
import org.xcolab.service.contest.domain.contestteammemberrole.ContestTeamMemberRoleDao;
import org.xcolab.service.contest.domain.contesttype.ContestTypeDao;
import org.xcolab.service.contest.domain.impactiteration.ImpactIterationDao;
import org.xcolab.service.contest.domain.impacttemplateseries.ImpactTemplateSeriesDao;

import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;
import org.xcolab.service.utils.PaginationHelper;


import java.util.List;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private ContestDao contestDao;

    @Autowired
    private ContestTypeDao contestTypeDao;



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



    @RequestMapping(value = "/contests/{contestId}/subContestsByOntologySpaceId", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Contest> getSubContestsByOntologySpaceId(
            @PathVariable long contestId,
            @RequestParam Long ontologySpaceId) {

        return contestService.getSubContestsByOntologySpaceId(contestId, ontologySpaceId);
    }

    @PostMapping(value = "/contests")
    public Contest createContest(@RequestBody Contest contest) {
        return this.contestDao.create(contest);
    }

    @PutMapping(value = "/contests/{contestPK}")
    public boolean updateContest(@RequestBody Contest contest,
                                 @PathVariable long contestPK) throws NotFoundException {

        if (contestDao.get(contestPK) == null) {
            throw new NotFoundException("No Contest with id " + contestPK);
        } else {
            return contestDao.update(contest);
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


}
