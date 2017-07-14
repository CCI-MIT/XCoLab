package org.xcolab.view.activityentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;

import java.util.List;

@Component
public class ActivityEntryHelper {

    @Autowired
    private List<ActivityEntryContentProvider> providerList;

    public String getActivityBody(ActivityEntry ae){

        for (ActivityEntryContentProvider dir : providerList) {

            if(dir.getPrimaryType().longValue() == ae.getPrimaryType().longValue() &&
                    dir.getSecondaryType().longValue() == ae.getSecondaryType().longValue()){
                dir.setActivityEntry(ae);
                return dir.getBody();
            }
        }
        return "";

    }
}
