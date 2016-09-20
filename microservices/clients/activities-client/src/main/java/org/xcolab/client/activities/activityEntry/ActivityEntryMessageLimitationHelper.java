package org.xcolab.client.activities.activityEntry;

import org.xcolab.client.activities.pojo.ActivityEntry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityEntryMessageLimitationHelper {
        private final Set<Long> limitedClassesIds = new HashSet<>();

        public ActivityEntryMessageLimitationHelper(Long... limitedClasses) {
            for (Long c : limitedClasses) {
                long id = c;
                limitedClassesIds.add(id);
            }
        }

        public List<ActivityEntry> process(List data) {
            Map<String,ActivityEntry> ret = new HashMap<>(data.size()*2);
            for (Object o : data) {
                ActivityEntry sa  = (ActivityEntry) o;
                ret.put(getKey(sa),sa);
            }
            return new LinkedList<>(ret.values());
        }

        private String getKey(ActivityEntry sa) {
            if (limitedClassesIds.contains(sa.getPrimaryType())) {
                return sa.getPrimaryType()+"_"+sa.getClassPrimaryKey();
            }
            else{
                return "0_" + sa.getActivityEntryId();
            }
        }
}
