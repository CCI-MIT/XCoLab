package org.xcolab.view.util.entity;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.GroupingHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityUtil {

    private static final long AGGREGATION_TIME_WINDOW = Duration.ofHours(1).getSeconds() * 1000;

    public static List<ActivityEntry> retrieveAllActivities(int pagestart, int next) {
        return ActivitiesClientUtil.getActivityEntries(pagestart, next, null, null);
    }

    public static List<ActivityEntry> groupActivities(List<ActivityEntry> activities) {
        Map<String, Set<ActivityEntry>> activitiesMap = new GroupingHelper<>(activities)
                .groupWithDuplicateValues(ActivityUtil::getSocialActivityKey);
        return clusterActivities(activitiesMap);
    }

    public static int getAllActivitiesCount() {
        return ActivitiesClientUtil.countActivities(null, null);
    }

    public static int getActivitiesCount(long userId) {
        return ActivitiesClientUtil.countActivities(userId, null);
    }

    private static List<ActivityEntry> clusterActivities(
            Map<String, Set<ActivityEntry>> activitiesMap) {
        List<ActivityEntry> aggregatedActivities = new LinkedList<>();
        Comparator<ActivityEntry> sorter =
                Comparator.comparingLong(o -> o.getCreateDate().getTime());
        for (Set<ActivityEntry> activitiesMapValue : activitiesMap.values()) {
            List<ActivityEntry> sortedActivities = new ArrayList<>(activitiesMapValue);
            sortedActivities.sort(sorter);

            ActivityEntry curMin = null;
            for (ActivityEntry socialActivity : sortedActivities) {
                if (curMin == null ||
                        socialActivity.getCreateDate().getTime() - curMin.getCreateDate().getTime()
                                < AGGREGATION_TIME_WINDOW) {
                    curMin = socialActivity;
                } else {
                    aggregatedActivities.add(curMin);
                    curMin = socialActivity;
                }
            }
            aggregatedActivities.add(curMin);
        }

        // Sort the activities in reverse order (sort by date DESC)
        aggregatedActivities.sort(Collections.reverseOrder(sorter));

        return aggregatedActivities;
    }

    private static String getSocialActivityKey(ActivityEntry sa) {
        return sa.getActivityCategory() + "_" + sa.getCategoryId() + "_" + sa.getActivityType()
                + "_" + sa.getMemberId();
    }
}
