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
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseRibbonType;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphaseribbontype.ContestPhaseRibbonTypeDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ContestPhaseController {
    @Autowired
    private ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao;

    @Autowired
    private ContestPhaseDao contestPhaseDao;

    @Autowired
    private ContestPhaseTypeDao contestPhaseTypeDao;

    @RequestMapping(value = "/contestPhases", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhase> getContestPhases(@RequestParam(required = false) Long contestPK,
                                               @RequestParam(required = false) Long contestScheduleId) {
        return contestPhaseDao.findByGiven(contestPK, contestScheduleId);
    }
    @GetMapping(value = "/contestPhaseRibbonTypes/{contestPhaseRibbonTypeId}")
    public ContestPhaseRibbonType getContestPhaseRibbonType(@PathVariable long contestPhaseRibbonTypeId)
            throws NotFoundException {
        return contestPhaseRibbonTypeDao.get(contestPhaseRibbonTypeId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestPhaseRibbonTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhaseRibbonType> getContestPhaseRibbonTypes() {
        return contestPhaseRibbonTypeDao.findByGiven();
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
}
