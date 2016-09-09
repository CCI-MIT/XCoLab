package org.xcolab.service.contest.domain.contestphase;


import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseDao {

    ContestPhase create(ContestPhase contestPhase);

    boolean update(ContestPhase contestPhase);

    boolean delete(Long contestPhasePK);

    List<ContestPhase> findByGiven(Long contestPK, Long contestScheduleId);

    Optional<ContestPhase> get(Long contestPhasePK) throws NotFoundException;

    boolean exists(Long contestPhasePK);

    boolean isPhaseActive(ContestPhase contestPhase);
}
