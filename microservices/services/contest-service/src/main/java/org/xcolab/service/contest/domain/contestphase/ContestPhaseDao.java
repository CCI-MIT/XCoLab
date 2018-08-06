package org.xcolab.service.contest.domain.contestphase;


import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseDao {

    ContestPhase create(ContestPhase contestPhase);

    boolean update(ContestPhase contestPhase);

    boolean delete(Long contestPhaseId);

    List<ContestPhase> findByGiven(Long contestId, Long contestScheduleId, Long contestPhaseTypeId);

    List<ContestPhase> findByPhaseAutopromote(String contestPhaseAutoPromote);

    Optional<ContestPhase> get(Long contestPhaseId) throws NotFoundException;

    boolean exists(Long contestPhaseId);

    boolean isPhaseActive(ContestPhase contestPhase);

    List<Long> getProposalDiscussionThreadsInPhase(long phaseId);
}
