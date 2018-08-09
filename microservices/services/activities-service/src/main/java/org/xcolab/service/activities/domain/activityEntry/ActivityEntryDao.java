package org.xcolab.service.activities.domain.activityEntry;

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.Date;
import java.util.List;

public interface ActivityEntryDao {

    ActivityEntry create(ActivityEntry activityEntry);

    List<ActivityEntry> findByGiven(PaginationHelper paginationHelper,
            Long userId, List<Long> userIdsToExclude);

    Integer countByGiven(Long userId, List<Long> userIdsToExclude);

    ActivityEntry get(Long activityEntryId) throws NotFoundException;
    List<ActivityEntry> getActivitiesAfter(Date date);

    boolean delete(ActivityCategory activityCategory, List<Long> categoryIds);
}
