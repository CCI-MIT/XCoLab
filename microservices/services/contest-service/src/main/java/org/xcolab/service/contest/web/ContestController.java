package org.xcolab.service.contest.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.xcolab.model.tables.pojos.*;
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
import org.xcolab.service.contest.domain.plantemplate.PlanTemplateDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contest.ContestService;


import java.util.List;

@RestController
public class ContestController {

    private static final int DEFAULT_PAGE_SIZE = 20;



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
            @RequestParam(required = false) String contestUrlName,
            @RequestParam(required = false) Long contestYear,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(required = false) Long contestTier,
            @RequestParam(required = false) Long contestScheduleId,
            @RequestParam(required = false) Long planTemplateId,
            @RequestParam(required = false) List<Long> focusAreaOntologyTerms,
            @RequestParam(required = false) Long contestTypeId,
            @RequestParam(required = false) Boolean contestPrivate
    ) {
        return contestDao.findByGiven(contestUrlName, contestYear, active, featured, contestTier, focusAreaOntologyTerms, contestScheduleId, planTemplateId, contestTypeId,contestPrivate);
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

    @RequestMapping(value = "/contests", method = RequestMethod.POST)
    public Contest createContest(@RequestBody Contest contest) {
        return this.contestDao.create(contest);
    }

    @RequestMapping(value = "/contests/{contestPK}", method = RequestMethod.PUT)
    public boolean updateContest(@RequestBody Contest contest,
                                 @PathVariable("contestPK") Long contestPK) throws NotFoundException {

        if (contestPK == null || contestPK == 0 || contestDao.get(contestPK) == null) {
            throw new NotFoundException("No Contest with id " + contestPK);
        } else {
            return contestDao.update(contest);
        }
    }

    @RequestMapping(value = "/contests/countByContestType", method = RequestMethod.PUT)
    public Integer countByContestType(@RequestBody Contest contest,
                                      @PathVariable("contestTypeId") Long contestTypeId) throws NotFoundException {


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


    @RequestMapping(value = "/contests/{contestId}", method = RequestMethod.GET)
    public Contest getContest(@PathVariable long contestId) throws NotFoundException {
        return contestDao.get(contestId);
    }

    @RequestMapping(value = "/contestPhases", method = {RequestMethod.GET})
    public List<ContestPhase> getContestPhases(@RequestParam(required = false) Long contestPK,
                                               @RequestParam(required = false) Long contestScheduleId) {
        return contestPhaseDao.findByGiven(contestPK, contestScheduleId);
    }

    @RequestMapping(value = "/contestTypes/{contestTypeId}", method = RequestMethod.GET)
    public ContestType getContestType(@PathVariable("contestTypeId") Long contestTypeId) throws NotFoundException {
        if (contestTypeId == null) {
            throw new NotFoundException("No contestTypeId given");
        } else {
            return contestTypeDao.get(contestTypeId);
        }
    }

    @RequestMapping(value = "/contestTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestType> getContestTypes(
    ) {
        return contestTypeDao.findByGiven();
    }

    @RequestMapping(value = "/contestPhaseRibbonTypes/{contestPhaseRibbonTypeId}", method = RequestMethod.GET)
    public ContestPhaseRibbonType getContestPhaseRibbonType(@PathVariable("contestPhaseRibbonTypeId") Long contestPhaseRibbonTypeId) throws NotFoundException {
        if (contestPhaseRibbonTypeId == null || contestPhaseRibbonTypeId == 0) {
            throw new NotFoundException("No contestPhaseRibbonTypeId given");
        } else {
            return contestPhaseRibbonTypeDao.get(contestPhaseRibbonTypeId);
        }
    }

    @RequestMapping(value = "/contestPhaseRibbonTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhaseRibbonType> getContestPhaseRibbonTypes(
    ) {
        return contestPhaseRibbonTypeDao.findByGiven();
    }

    @RequestMapping(value = "/contestTeamMembers", method = RequestMethod.POST)
    public ContestTeamMember createContestTeamMember(@RequestBody ContestTeamMember contestTeamMember) {
        return this.contestTeamMemberDao.create(contestTeamMember);
    }

    @RequestMapping(value = "/contestTeamMembers/{contestTeamMemberId}", method = RequestMethod.GET)
    public ContestTeamMember getContestTeamMember(@PathVariable("contestTeamMemberId") Long contestTeamMemberId) throws NotFoundException {
        if (contestTeamMemberId == null || contestTeamMemberId == 0) {
            throw new NotFoundException("No contestTeamMemberId given");
        } else {
            return contestTeamMemberDao.get(contestTeamMemberId);
        }
    }

    @RequestMapping(value = "/contestTeamMembers/{id_}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/contestTeamMembers/{id_}", method = RequestMethod.DELETE)
    public String deleteContestTeamMember(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No ContestTeamMember with id given");
        } else {
            ContestTeamMember contestTeamMember = this.contestTeamMemberDao.get(id_);
            if (contestTeamMember != null) {
                this.contestTeamMemberDao.delete(contestTeamMember.getId_());
                return "ContestTeamMember deleted successfully";
            } else {
                throw new NotFoundException("No ContestTeamMember with id given");
            }
        }
    }

    @RequestMapping(value = "/contestTeamMemberRoles/{contestTeamMemberRoleId}", method = RequestMethod.GET)
    public ContestTeamMemberRole getContestTeamMemberRole(@PathVariable("contestTeamMemberRoleId") Long contestTeamMemberRoleId) throws NotFoundException {
        if (contestTeamMemberRoleId == null || contestTeamMemberRoleId == 0) {
            throw new NotFoundException("No contestTeamMemberRoleId given");
        } else {
            return contestTeamMemberRoleDao.get(contestTeamMemberRoleId);
        }
    }


    @RequestMapping(value = "/contestSchedules", method = RequestMethod.POST)
    public ContestSchedule createContestSchedule(@RequestBody ContestSchedule contestSchedule) {
        return this.contestScheduleDao.create(contestSchedule);
    }

    @RequestMapping(value = "/contestSchedules/{contestScheduleId}", method = RequestMethod.GET)
    public ContestSchedule getContestSchedule(@PathVariable("contestScheduleId") Long contestScheduleId) throws NotFoundException {
        if (contestScheduleId == null || contestScheduleId == 0) {
            throw new NotFoundException("No contestScheduleId given");
        } else {
            return contestScheduleDao.get(contestScheduleId);
        }
    }

    @RequestMapping(value = "/contestSchedules/{id_}", method = RequestMethod.PUT)
    public boolean updateContestSchedule(@RequestBody ContestSchedule contestSchedule,
                                         @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || contestScheduleDao.get(id_) == null) {
            throw new NotFoundException("No ContestSchedule with id " + id_);
        } else {
            return contestScheduleDao.update(contestSchedule);
        }
    }

    @RequestMapping(value = "/contestSchedules", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestSchedule> getContestSchedules() {
        return contestScheduleDao.findByGiven();
    }

    @RequestMapping(value = "/contestSchedules/{id_}", method = RequestMethod.DELETE)
    public String deleteContestSchedule(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No ContestSchedule with id given");
        } else {
            ContestSchedule contestSchedule = this.contestScheduleDao.get(id_);
            if (contestSchedule != null) {
                this.contestScheduleDao.delete(contestSchedule.getId_());
                return "ContestSchedule deleted successfully";
            } else {
                throw new NotFoundException("No ContestSchedule with id given");
            }
        }
    }

    @RequestMapping(value = "/contestPhases/{contestPhaseId}", method = RequestMethod.GET)
    public ContestPhase getContestPhase(@PathVariable("contestPhaseId") Long contestPhaseId) throws NotFoundException {
        if (contestPhaseId == null || contestPhaseId == 0) {
            throw new NotFoundException("No contestPhaseId given");
        } else {
            return contestPhaseDao.get(contestPhaseId);
        }
    }

    @RequestMapping(value = "/contestPhases", method = RequestMethod.POST)
    public ContestPhase createContestPhase(@RequestBody ContestPhase contestPhase) {
        return this.contestPhaseDao.create(contestPhase);
    }

    @RequestMapping(value = "/contestPhases/{contestPhasePK}", method = RequestMethod.PUT)
    public boolean updateContestPhase(@RequestBody ContestPhase contestPhase,
                                      @PathVariable("contestPhasePK") Long contestPhasePK) throws NotFoundException {

        if (contestPhasePK == null || contestPhasePK == 0 || contestPhaseDao.get(contestPhasePK) == null) {
            throw new NotFoundException("No ContestPhase with id " + contestPhasePK);
        } else {
            return contestPhaseDao.update(contestPhase);
        }
    }

    @RequestMapping(value = "/contestPhases/{contestPhasePK}", method = RequestMethod.DELETE)
    public String deleteContestPhase(@PathVariable("contestPhasePK") Long contestPhasePK)
            throws NotFoundException {

        if (contestPhasePK == null || contestPhasePK == 0) {
            throw new NotFoundException("No ContestPhase with id given");
        } else {
            ContestPhase contestPhase = this.contestPhaseDao.get(contestPhasePK);
            if (contestPhase != null) {
                this.contestPhaseDao.delete(contestPhase.getContestPhasePK());
                return "ContestPhase deleted successfully";
            } else {
                throw new NotFoundException("No ContestPhase with id given");
            }
        }
    }

    @RequestMapping(value = "/contestPhaseTypes/{contestPhaseTypeId}", method = RequestMethod.GET)
    public ContestPhaseType getContestPhaseType(@PathVariable("contestPhaseTypeId") Long contestPhaseTypeId) throws NotFoundException {
        if (contestPhaseTypeId == null || contestPhaseTypeId == 0) {
            throw new NotFoundException("No contestPhaseTypeId given");
        } else {
            return contestPhaseTypeDao.get(contestPhaseTypeId);
        }
    }

    @RequestMapping(value = "/contestPhaseTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhaseType> getContestPhaseTypes(
    ) {
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
