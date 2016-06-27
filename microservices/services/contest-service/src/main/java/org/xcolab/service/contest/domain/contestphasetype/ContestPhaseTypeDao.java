package org.xcolab.service.contest.domain.contestphasetype;

import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface ContestPhaseTypeDao {
    ContestPhaseType create(ContestPhaseType contestPhaseType);
    ContestPhaseType get(Long id_) throws NotFoundException;
    boolean update(ContestPhaseType contestPhaseType);
    int delete(Long id_);
}
