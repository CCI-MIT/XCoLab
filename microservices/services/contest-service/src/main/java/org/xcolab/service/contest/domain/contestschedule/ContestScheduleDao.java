package org.xcolab.service.contest.domain.contestschedule;

import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestScheduleDao {

    IContestSchedule create(IContestSchedule contestSchedule);

    Optional<IContestSchedule> get(Long id) throws NotFoundException;

    boolean exists(Long id);

    boolean update(IContestSchedule contestSchedule);

    List<IContestSchedule> findByGiven();

    boolean delete(Long id);
}
