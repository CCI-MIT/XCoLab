package org.xcolab.service.activities.domain.activityEntry;

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.Date;
import java.util.List;

public interface ActivityEntryDao {

    ActivityEntry create(ActivityEntry activityEntry);
    List<ActivityEntry> findByGiven(PaginationHelper paginationHelper,
                                    Long memberId, List<Long> memberIdsToExclude);
    Integer findByGivenCount(Long memberId, List<Long> memberIdsToExclude);
    ActivityEntry get(Long activityEntryId) throws NotFoundException;
    List<ActivityEntry> getActivitiesAfter(Date date);
}
