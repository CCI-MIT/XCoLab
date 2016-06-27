package org.xcolab.service.contest.domain.contestphase;


import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestPhaseDao {

    ContestPhase create(ContestPhase contestPhase);

    boolean update(ContestPhase contestPhase);

    int delete(Long contestPhasePK);

    List<ContestPhase> findByGiven(Long contestPK, Long contestScheduleId);

    ContestPhase get(Long contestPhasePK) throws NotFoundException;
}
