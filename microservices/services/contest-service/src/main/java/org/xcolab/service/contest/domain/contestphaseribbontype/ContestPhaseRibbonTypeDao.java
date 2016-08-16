package org.xcolab.service.contest.domain.contestphaseribbontype;

import org.xcolab.model.tables.pojos.ContestPhaseRibbonType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestPhaseRibbonTypeDao {
    List<ContestPhaseRibbonType> findByGiven();
    ContestPhaseRibbonType get(Long id_) throws NotFoundException ;
}
