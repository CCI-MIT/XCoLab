package org.xcolab.service.contest.domain.contestphasetype;

import org.xcolab.model.tables.pojos.ContestPhaseType;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseTypeDao {

    ContestPhaseType create(ContestPhaseType contestPhaseType);

    Optional<ContestPhaseType> get(Long id);

    boolean update(ContestPhaseType contestPhaseType);

    int delete(Long id);

    List<ContestPhaseType> findByGiven();
}
