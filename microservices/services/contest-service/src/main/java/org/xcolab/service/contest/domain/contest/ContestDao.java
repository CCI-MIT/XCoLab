package org.xcolab.service.contest.domain.contest;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContestDao {

    Contest create(Contest contest);
    boolean update(Contest contest);
    Contest get(Long contestId) throws NotFoundException;
    List<Contest> findByGiven(PaginationHelper paginationHelper, String contestUrlName,
            Long contestYear, Boolean active, Boolean featured, Long contestTier,
            List<Long> focusAreaOntologyTerms, Long contestScheduleId, Long planTemplateId);
}
