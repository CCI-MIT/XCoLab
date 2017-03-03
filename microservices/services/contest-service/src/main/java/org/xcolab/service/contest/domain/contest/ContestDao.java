package org.xcolab.service.contest.domain.contest;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContestDao {

    Contest create(Contest contest);

    boolean update(Contest contest);

    Contest get(Long contestId) throws NotFoundException;
    Contest getByThreadId(Long threadId) throws NotFoundException;
    List<Contest> findByGiven(PaginationHelper paginationHelper, String contestUrlName, Long contestYear, Boolean active, Boolean featured, Long contestTier, List<Long> focusAreaOntologyTerms, Long contestScheduleId, Long planTemplateId, Long contestTypeId, Boolean contestPrivate);
    List<Contest> findByGiven(String contestName, List<Long> focusAreaOntologyTermsIds, List<Long> contestTierIds);
    Integer countByGiven(String contestUrlName, Long contestYear, Boolean active, Boolean featured, Long contestTier, List<Long> focusAreaOntologyTerms, Long contestScheduleId, Long planTemplateId, Long contestTypeId, Boolean contestPrivate);
    boolean isShared(long contestId);
    boolean existsWithScheduleId(long contestScheduleId);

    boolean delete(long contestPK);
}
