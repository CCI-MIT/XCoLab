package org.xcolab.service.contest.domain.contestphasetype;

import org.xcolab.client.contest.pojo.IContestPhaseType;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseTypeDao {

    IContestPhaseType create(IContestPhaseType contestPhaseType);

    Optional<IContestPhaseType> get(Long id);

    boolean update(IContestPhaseType contestPhaseType);

    int delete(Long id);

    List<IContestPhaseType> findByGiven();
}
