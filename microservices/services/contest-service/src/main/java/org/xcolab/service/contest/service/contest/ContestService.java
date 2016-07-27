package org.xcolab.service.contest.service.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestphase.ContestPhaseDao;
import org.xcolab.service.contest.domain.contestphasetype.ContestPhaseTypeDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContestService {

    private final ContestDao contestDao;

    private final ContestPhaseDao contestPhaseDao;

    private final ContestPhaseTypeDao contestPhaseTypeDao;



    @Autowired
    public ContestService(ContestDao contestDao, ContestPhaseDao contestPhaseDao, ContestPhaseTypeDao contestPhaseTypeDao) {

        this.contestDao = contestDao;
        this.contestPhaseDao = contestPhaseDao;
        this.contestPhaseTypeDao = contestPhaseTypeDao;
    }

    public List<ContestPhase> getAllContestPhases(Long contestId) {
        return this.contestPhaseDao.findByGiven(contestId, null);
    }

    public ContestPhase getActiveOrLastPhase(Long contestId){
        ContestPhase lastPhase = null;
        for (ContestPhase phase : getAllContestPhases(contestId)) {
            if (lastPhase == null || lastPhase.getPhaseStartDate().before(phase.getPhaseStartDate())) {
                lastPhase = phase;
            }
            if (contestPhaseDao.isPhaseActive(phase)) {
                return phase;
            }
        }
        return lastPhase;
    }

    public List<ContestPhase> getVisiblePhases(Long contestId) {
        List<ContestPhase> visiblePhases = new ArrayList<>();
        for (ContestPhase phase : getAllContestPhases(contestId)) {
            try {
                ContestPhaseType phaseType = contestPhaseTypeDao.get(phase.getContestPhaseType());
                if (!phaseType.getInvisible()) {
                    visiblePhases.add(phase);
                }
            }catch (NotFoundException ignored){
            }

        }
        return visiblePhases;
    }

}
