package org.xcolab.service.contest.domain.contestschedule;

import org.xcolab.model.tables.pojos.ContestSchedule;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestScheduleDao {

    ContestSchedule create(ContestSchedule contestSchedule);

    Optional<ContestSchedule> get(Long id_) throws NotFoundException;

    boolean exists(Long id_);

    boolean update(ContestSchedule contestSchedule);

    List<ContestSchedule> findByGiven();

    boolean delete(Long id_);
}
