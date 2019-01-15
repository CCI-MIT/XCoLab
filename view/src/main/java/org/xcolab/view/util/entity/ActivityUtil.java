package org.xcolab.view.util.entity;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.commons.GroupingHelper;

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

    public static List<IActivityEntry> retrieveAllActivities(int pagestart, int next) {
        return StaticActivityContext.getActivityClient().getActivityEntries(pagestart, next, null, null);
    }

    public static List<IActivityEntry> groupActivities(List<IActivityEntry> activities) {
        Map<String, Set<IActivityEntry>> activitiesMap = new GroupingHelper<>(activities)
                .groupWithDuplicateValues(ActivityUtil::getSocialActivityKey);
        return clusterActivities(activitiesMap);
    }

    public static int getAllActivitiesCount() {
        return StaticActivityContext.getActivityClient().countActivities(null, null);
    }

    public static int getActivitiesCount(long userId) {
        return StaticActivityContext.getActivityClient().countActivities(userId, null);
    }

    private static List<IActivityEntry> clusterActivities(
            Map<String, Set<IActivityEntry>> activitiesMap) {
        List<IActivityEntry> aggregatedActivities = new LinkedList<>();
        Comparator<IActivityEntry> sorter =
                Comparator.comparingLong(o -> o.getCreatedAt().getTime());
        for (Set<IActivityEntry> activitiesMapValue : activitiesMap.values()) {
            List<IActivityEntry> sortedActivities = new ArrayList<>(activitiesMapValue);
            sortedActivities.sort(sorter);

            IActivityEntry curMin = null;
            for (IActivityEntry socialActivity : sortedActivities) {
                if (curMin == null ||
                        socialActivity.getCreatedAt().getTime() - curMin.getCreatedAt().getTime()
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

    private static String getSocialActivityKey(IActivityEntry sa) {
        return sa.getActivityCategory() + "_" + sa.getCategoryId() + "_" + sa.getActivityType()
                + "_" + sa.getUserId();
    }
}
