package org.xcolab.service.activities.domain.activityEntry;

import org.xcolab.client.activities.pojo.IActivityEntry;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.Date;
import java.util.List;

public interface ActivityEntryDao {

    IActivityEntry create(IActivityEntry activityEntry);

    List<IActivityEntry> findByGiven(PaginationHelper paginationHelper,
            String activityCategory, Long categoryId, Long userId,
            List<Long> userIdsToExclude);

    Integer countByGiven(Long userId, List<Long> userIdsToExclude);

    IActivityEntry get(Long activityEntryId) throws NotFoundException;

    List<IActivityEntry> getActivitiesAfter(Date date);

    boolean delete(ActivityCategory activityCategory, List<Long> categoryIds);
}
