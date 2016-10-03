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

    @Autowired
    private ContestScheduleDao contestScheduleDao;

    @Autowired
    private ContestTeamMemberDao contestTeamMemberDao;

    @Autowired
    private ContestTeamMemberRoleDao contestTeamMemberRoleDao;

    @Autowired
    private ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao;

    @Autowired
    private ContestPhaseDao contestPhaseDao;

    @Autowired
    private ContestPhaseTypeDao contestPhaseTypeDao;


    @Autowired
    private ImpactTemplateSeriesDao impactTemplateSeriesDao;

    @Autowired
    private ImpactIterationDao impactIterationDao;

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

    @RequestMapping(value = "/contestPhases", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhase> getContestPhases(@RequestParam(required = false) Long contestPK,
                                               @RequestParam(required = false) Long contestScheduleId) {
        return contestPhaseDao.findByGiven(contestPK, contestScheduleId);
    }

    @GetMapping(value = "/contestTypes/{contestTypeId}")
    public ContestType getContestType(@PathVariable long contestTypeId) throws NotFoundException {
        return contestTypeDao.get(contestTypeId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestType> getContestTypes() {
        return contestTypeDao.findByGiven();
    }

    @GetMapping(value = "/contestPhaseRibbonTypes/{contestPhaseRibbonTypeId}")
    public ContestPhaseRibbonType getContestPhaseRibbonType(@PathVariable long contestPhaseRibbonTypeId)
            throws NotFoundException {
        return contestPhaseRibbonTypeDao.get(contestPhaseRibbonTypeId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestPhaseRibbonTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhaseRibbonType> getContestPhaseRibbonTypes(
    ) {
        return contestPhaseRibbonTypeDao.findByGiven();
    }

    @PostMapping(value = "/contestTeamMembers")
    public ContestTeamMember createContestTeamMember(@RequestBody ContestTeamMember contestTeamMember) {
        return this.contestTeamMemberDao.create(contestTeamMember);
    }

    @GetMapping(value = "/contestTeamMembers/{contestTeamMemberId}")
    public ContestTeamMember getContestTeamMember(@PathVariable("contestTeamMemberId") Long contestTeamMemberId) throws NotFoundException {
        return contestTeamMemberDao.get(contestTeamMemberId)
                .orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = "/contestTeamMembers/{id_}")
    public boolean updateContestTeamMember(@RequestBody ContestTeamMember contestTeamMember,
                                           @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || contestTeamMemberDao.get(id_) == null) {
            throw new NotFoundException("No ContestTeamMember with id " + id_);
        } else {
            return contestTeamMemberDao.update(contestTeamMember);
        }
    }

    @RequestMapping(value = "/contestTeamMembers", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestTeamMember> getContestTeamMembers(
            @RequestParam(required = false) Long contestId
    ) {
        return contestTeamMemberDao.findByGiven(contestId);
    }

    @DeleteMapping(value = "/contestTeamMembers/{id_}")
    public boolean deleteContestTeamMember(@PathVariable long id_)
            throws NotFoundException {
        if (contestTeamMemberDao.exists(id_)) {
            return contestTeamMemberDao.delete(id_);
        } else {
            throw new NotFoundException("No ContestTeamMember with id given");
        }
    }

    @GetMapping(value = "/contestTeamMemberRoles/{contestTeamMemberRoleId}")
    public ContestTeamMemberRole getContestTeamMemberRole(@PathVariable long contestTeamMemberRoleId)
            throws NotFoundException {
        return contestTeamMemberRoleDao.get(contestTeamMemberRoleId)
                .orElseThrow(NotFoundException::new);
    }


    @PostMapping(value = "/contestSchedules")
    public ContestSchedule createContestSchedule(@RequestBody ContestSchedule contestSchedule) {
        return this.contestScheduleDao.create(contestSchedule);
    }

    @GetMapping(value = "/contestSchedules/{contestScheduleId}")
    public ContestSchedule getContestSchedule(@PathVariable long contestScheduleId)
            throws NotFoundException {
        return contestScheduleDao.get(contestScheduleId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/contestSchedules/{contestScheduleId}/isUsed")
    public boolean isContestScheduleUsed(@PathVariable long contestScheduleId) {
        return contestDao.existsWithScheduleId(contestScheduleId);
    }

    @PutMapping(value = "/contestSchedules/{id_}")
    public boolean updateContestSchedule(@RequestBody ContestSchedule contestSchedule,
                                         @PathVariable long id_) throws NotFoundException {

        if (contestScheduleDao.exists(id_)) {
            return contestScheduleDao.update(contestSchedule);
        } else {
            throw new NotFoundException("No ContestSchedule with id " + id_);
        }
    }

    @RequestMapping(value = "/contestSchedules", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestSchedule> getContestSchedules() {
        return contestScheduleDao.findByGiven();
    }

    @DeleteMapping(value = "/contestSchedules/{id_}")
    public boolean deleteContestSchedule(@PathVariable long id_)
            throws NotFoundException {
        if (contestScheduleDao.exists(id_)) {
            return contestScheduleDao.delete(id_);

        }
        throw new NotFoundException();
    }

    @GetMapping(value = "/contestPhases/{contestPhaseId}")
    public ContestPhase getContestPhase(@PathVariable long contestPhaseId)
            throws NotFoundException {
        return contestPhaseDao.get(contestPhaseId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/contestPhases")
    public ContestPhase createContestPhase(@RequestBody ContestPhase contestPhase) {
        return this.contestPhaseDao.create(contestPhase);
    }

    @PutMapping(value = "/contestPhases/{contestPhasePK}")
    public boolean updateContestPhase(@PathVariable long contestPhasePK,
                                @RequestBody ContestPhase contestPhase) throws NotFoundException {

        if (contestPhaseDao.exists(contestPhasePK)) {
            return contestPhaseDao.update(contestPhase);
        } else {
            throw new NotFoundException("No ContestPhase with id " + contestPhasePK);
        }
    }


    @DeleteMapping(value = "/contestPhases/{contestPhasePK}")
    public Boolean deleteContestPhase(@PathVariable("contestPhasePK") Long contestPhasePK)
            throws NotFoundException {

        if (contestPhaseDao.exists(contestPhasePK)) {
            return contestPhaseDao.delete(contestPhasePK);
        }
        throw new NotFoundException();
    }

    @GetMapping(value = "/contestPhaseTypes/{contestPhaseTypeId}")
    public ContestPhaseType getContestPhaseType(@PathVariable long contestPhaseTypeId)
            throws NotFoundException {
        return contestPhaseTypeDao.get(contestPhaseTypeId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestPhaseTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhaseType> getContestPhaseTypes() {
        return contestPhaseTypeDao.findByGiven();
    }

    @RequestMapping(value = "/impactTemplateSeries/{impactTemplateSeriesId}", method = RequestMethod.GET)
    public ImpactTemplateSeries getImpactTemplateSeries(@PathVariable("impactTemplateSeriesId") Long impactTemplateSeriesId) throws NotFoundException {
        if (impactTemplateSeriesId == null || impactTemplateSeriesId == 0) {
            throw new NotFoundException("No impactTemplateSeriesId given");
        } else {
            return impactTemplateSeriesDao.get(impactTemplateSeriesId);
        }
    }

    @RequestMapping(value = "/impactIterations", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ImpactIteration> getImpactIterations(
            @RequestParam(required = false) Long iterationId
    ) {
        return impactIterationDao.findByGiven(iterationId);
    }



}
