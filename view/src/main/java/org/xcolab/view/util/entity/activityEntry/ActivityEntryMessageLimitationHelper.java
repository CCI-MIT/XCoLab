package org.xcolab.view.util.entity.activityEntry;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityEntryMessageLimitationHelper {

    private final Set<ActivityCategory> limitedCategories = new HashSet<>();

    public ActivityEntryMessageLimitationHelper(ActivityCategory... limitedCategories) {
        this.limitedCategories.addAll(Arrays.asList(limitedCategories));
    }

    public List<ActivityEntry> process(List data) {
        Map<String, ActivityEntry> ret = new HashMap<>(data.size() * 2);
        for (Object o : data) {
            ActivityEntry sa = (ActivityEntry) o;
            ret.put(getKey(sa), sa);
        }
        return new LinkedList<>(ret.values());
    }

    private String getKey(ActivityEntry sa) {
        if (limitedCategories.contains(sa.getActivityCategoryEnum())) {
            return sa.getActivityCategory() + "_" + sa.getActivityType();
        } else {
            return "0_" + sa.getId();
        }
    }
}
