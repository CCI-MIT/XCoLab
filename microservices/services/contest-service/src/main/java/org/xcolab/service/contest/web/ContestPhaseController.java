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

import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphaseribbontype.ContestPhaseRibbonTypeDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.service.contestphase.ContestPhaseService;
import org.xcolab.service.contest.utils.promotion.PromotionService;

import java.util.Date;
import java.util.List;

@RestController
public class ContestPhaseController {

    private final ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao;
    private final ContestPhaseDao contestPhaseDao;
    private final ContestPhaseTypeDao contestPhaseTypeDao;
    private final PromotionService promotionService;
    private final ContestPhaseService contestPhaseService;

    @Autowired
    public ContestPhaseController(ContestPhaseRibbonTypeDao contestPhaseRibbonTypeDao,
            ContestPhaseDao contestPhaseDao, ContestPhaseTypeDao contestPhaseTypeDao,
            PromotionService promotionService, ContestPhaseService contestPhaseService) {
        this.contestPhaseRibbonTypeDao = contestPhaseRibbonTypeDao;
        this.contestPhaseDao = contestPhaseDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
        this.promotionService = promotionService;
        this.contestPhaseService = contestPhaseService;
    }

    @RequestMapping(value = "/contestPhases", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestPhase> getContestPhases(@RequestParam(required = false) Long contestId,
            @RequestParam(required = false) Long contestScheduleId,
            @RequestParam(required = false) Long contestPhaseTypeId) {
        return contestPhaseDao.findByGiven(contestId, contestScheduleId, contestPhaseTypeId);
    }

    @GetMapping(value = "/contestPhaseRibbonTypes/{contestPhaseRibbonTypeId}")
    public IContestPhaseRibbonType getContestPhaseRibbonType(@PathVariable long contestPhaseRibbonTypeId)
            throws NotFoundException {
        return contestPhaseRibbonTypeDao.get(contestPhaseRibbonTypeId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestPhaseRibbonTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IContestPhaseRibbonType> getContestPhaseRibbonTypes() {
        return contestPhaseRibbonTypeDao.findByGiven();
    }

    @GetMapping(value = "/contestPhases/{phaseId}")
    public ContestPhase getContestPhase(@PathVariable long phaseId)
            throws NotFoundException {
        return contestPhaseDao.get(phaseId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/contestPhases")
    public ContestPhase createContestPhase(@RequestBody ContestPhase contestPhase) {
        return this.contestPhaseDao.create(contestPhase);
    }

    @PutMapping(value = "/contestPhases/{contestPhaseId}")
    public boolean updateContestPhase(@PathVariable long contestPhaseId,
            @RequestBody ContestPhase contestPhase) throws NotFoundException {

        if (contestPhaseDao.exists(contestPhaseId)) {
            return contestPhaseDao.update(contestPhase);
        } else {
            throw new NotFoundException("No ContestPhase with id " + contestPhaseId);
        }
    }

    @DeleteMapping(value = "/contestPhases/{contestPhaseId}")
    public Boolean deleteContestPhase(@PathVariable("contestPhaseId") Long contestPhaseId)
            throws NotFoundException {

        if (contestPhaseDao.exists(contestPhaseId)) {
            return contestPhaseDao.delete(contestPhaseId);
        }
        throw new NotFoundException();
    }

    @GetMapping(value = "/contestPhases/{phaseId}/proposalDiscussionThreads")
    public List<Long> getContestPhaseProposalDiscussionIds(@PathVariable long phaseId)
            throws NotFoundException {
        if (!contestPhaseDao.exists(phaseId)) {
            throw new NotFoundException("No ContestPhase with id " + phaseId);
        }
        return contestPhaseDao.getProposalDiscussionThreadsInPhase(phaseId);
    }

    @PutMapping(value = "/contestPhases/{contestPhaseId}/forcePromotionOfProposalInContestPhaseId")
    public boolean forcePromotionOfProposalInContestPhaseId(@PathVariable long contestPhaseId,
                                      @RequestParam Long proposalId) throws NotFoundException {

        if (contestPhaseDao.exists(contestPhaseId)) {
             contestPhaseService.forcePromotionOfProposalInPhase(proposalId, contestPhaseId);
            return true;
        } else {
            throw new NotFoundException("No ContestPhase with id " + contestPhaseId);
        }
    }

    @GetMapping("/contestPhases/autoPromoteProposals")
    public Integer autoPromoteProposals(){
        Date now = new Date();
        return promotionService.doPromotion(now);
    }

    @GetMapping("/contestPhaseTypes/{contestPhaseTypeId}")
    public IContestPhaseType getContestPhaseType(@PathVariable long contestPhaseTypeId)
            throws NotFoundException {
        return contestPhaseTypeDao.get(contestPhaseTypeId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/contestPhaseTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IContestPhaseType> getContestPhaseTypes() {
        return contestPhaseTypeDao.findByGiven();
    }
}
