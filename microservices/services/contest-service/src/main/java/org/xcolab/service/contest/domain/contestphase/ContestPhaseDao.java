package org.xcolab.service.contest.domain.contestphase;


import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestPhaseDao {

    ContestPhaseWrapper create(ContestPhaseWrapper contestPhase);

    boolean update(ContestPhaseWrapper contestPhase);

    boolean delete(Long contestPhaseId);

    List<ContestPhaseWrapper> findByGiven(Long contestId, Long contestScheduleId, Long contestPhaseTypeId);

    List<ContestPhaseWrapper> findByPhaseAutopromote(String contestPhaseAutoPromote);

    Optional<ContestPhaseWrapper> get(Long contestPhaseId) throws NotFoundException;

    boolean exists(Long contestPhaseId);

    boolean isPhaseActive(ContestPhaseWrapper contestPhase);

    List<Long> getProposalDiscussionThreadsInPhase(long phaseId);
}
