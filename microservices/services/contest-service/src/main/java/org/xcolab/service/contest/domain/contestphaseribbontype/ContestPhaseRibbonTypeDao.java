package org.xcolab.service.contest.domain.contestphaseribbontype;

import org.xcolab.model.tables.pojos.ContestPhaseRibbonType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseRibbonTypeDao {

    List<ContestPhaseRibbonType> findByGiven();

    Optional<ContestPhaseRibbonType> get(Long id) throws NotFoundException ;
}
