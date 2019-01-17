package org.xcolab.service.contest.domain.contestphaseribbontype;

import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseRibbonTypeDao {

    List<IContestPhaseRibbonType> findByGiven();

    Optional<IContestPhaseRibbonType> get(Long id) throws NotFoundException ;
}
