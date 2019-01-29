package org.xcolab.service.contest.domain.contest;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContestDao {

    ContestWrapper create(ContestWrapper contest);

    boolean update(ContestWrapper contest);

    ContestWrapper get(Long contestId) throws NotFoundException;

    ContestWrapper getByThreadId(Long threadId) throws NotFoundException;

    List<ContestWrapper> findByGiven(PaginationHelper paginationHelper, String contestUrlName,
        Long contestYear, Boolean active, Boolean featured, List<Long> contestTiers,
        List<Long> focusAreaIds, Long contestScheduleId, Long proposalTemplateId,
        List<Long> contestTypeIds, Boolean contestPrivate, String searchTerm);

    int countByGiven(String contestUrlName, Long contestYear, Boolean active, Boolean featured,
            List<Long> contestTiers, List<Long> focusAreaOntologyTerms, Long contestScheduleId,
            Long proposalTemplateId, List<Long> contestTypeIds, Boolean contestPrivate,
            String searchTerm);

    boolean isContestTitleYearUnique(String contestShortName, Long year, Long currentContestId);

    boolean existsWithScheduleId(long contestScheduleId);

    ContestWrapper getByResourceId(Long resourceId) throws NotFoundException;

    boolean delete(long contestId);

    List<Long> getContestYears();
}
