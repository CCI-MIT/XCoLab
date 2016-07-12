package org.xcolab.service.contest.domain.contestschedule;

import org.xcolab.model.tables.pojos.ContestSchedule;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestScheduleDao {

    ContestSchedule create(ContestSchedule contestSchedule);

    ContestSchedule get(Long id_) throws NotFoundException;

    boolean update(ContestSchedule contestSchedule);

    List<ContestSchedule> findByGiven();

    int delete(Long id_);
}
