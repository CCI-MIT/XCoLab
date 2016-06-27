package org.xcolab.service.contest.web;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ContestPhaseController {

    @Autowired
    private ContestPhaseDao contestPhaseDao;


    @Autowired
    private ContestPhaseTypeDao contestPhaseTypeDao;

    // getContestStatusStr()

    //ContestPhaseLocalServiceUtil.createContestPhase
    //ContestPhaseLocalServiceUtil.createFromContestPhase
    //ContestPhaseLocalServiceUtil.addContestPhase
    //public ContestPhase create(ContestPhase contestPhase) {
    @RequestMapping(value = "/contestPhases", method = RequestMethod.POST)
    public ContestPhase createContestPhase(@RequestBody ContestPhase contestPhase) {
        return this.contestPhaseDao.create(contestPhase);
    }


    // ContestPhaseLocalServiceUtil.updateContestPhase
    //public boolean update(ContestPhase contestPhase) {
    @RequestMapping(value = "/contestPhases/{contestPhasePK}", method = RequestMethod.PUT)
    public boolean updateContestPhase(@RequestBody ContestPhase contestPhase,
                                      @PathVariable("contestPhasePK") Long contestPhasePK) throws NotFoundException {

        if (contestPhasePK == null || contestPhasePK == 0 || contestPhaseDao.get(contestPhasePK) == null) {
            throw new NotFoundException("No ContestPhase with id " + contestPhasePK);
        } else {
            return contestPhaseDao.update(contestPhase);
        }
    }

    //ContestPhaseLocalServiceUtil.deleteContestPhase
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

    /*
    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest
    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId
    ContestPhaseLocalServiceUtil.getPhasesForContest
    */
    @RequestMapping(value = "/contests", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhase> getContestPhases(
            @RequestParam(required = false) Long contestPK,
            @RequestParam(required = false) Long contestScheduleId) {
        return contestPhaseDao.findByGiven(contestPK, contestScheduleId);
    }

    //ContestPhaseLocalServiceUtil.getContestPhase
    @RequestMapping(value = "/contestPhases/{contestPhaseId}", method = RequestMethod.GET)
    public ContestPhase getContestPhase(@PathVariable("contestPhaseId") Long contestPhaseId) throws NotFoundException {
        if (contestPhaseId == null || contestPhaseId == 0) {
            throw new NotFoundException("No contestPhaseId given");
        } else {
            return contestPhaseDao.get(contestPhaseId);
        }
    }

    @RequestMapping(value = "/contestPhaseTypes", method = RequestMethod.POST)
    public ContestPhaseType createContestPhaseType(@RequestBody ContestPhaseType contestPhaseType) {
        return this.contestPhaseTypeDao.create(contestPhaseType);
    }

    @RequestMapping(value = "/contestPhaseTypes/{contestPhaseTypeId}", method = RequestMethod.GET)
    public ContestPhaseType getContestPhaseType(@PathVariable("contestPhaseTypeId") Long contestPhaseTypeId) throws NotFoundException {
        if (contestPhaseTypeId == null || contestPhaseTypeId == 0) {
            throw new NotFoundException("No contestPhaseTypeId given");
        } else {
            return contestPhaseTypeDao.get(contestPhaseTypeId);
        }
    }

    @RequestMapping(value = "/contestPhaseTypes/{id_}", method = RequestMethod.PUT)
    public boolean updateContestPhaseType(@RequestBody ContestPhaseType contestPhaseType,
                                          @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || contestPhaseTypeDao.get(id_) == null) {
            throw new NotFoundException("No ContestPhaseType with id " + id_);
        } else {
            return contestPhaseTypeDao.update(contestPhaseType);
        }
    }

    @RequestMapping(value = "/contestPhaseTypes/{id_}", method = RequestMethod.DELETE)
    public String deleteContestPhaseType(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No ContestPhaseType with id given");
        } else {
            ContestPhaseType contestPhaseType = this.contestPhaseTypeDao.get(id_);
            if (contestPhaseType != null) {
                this.contestPhaseTypeDao.delete(contestPhaseType.getId_());
                return "ContestPhaseType deleted successfully";
            } else {
                throw new NotFoundException("No ContestPhaseType with id given");
            }
        }
    }

}

