package org.xcolab.view.widgets.feeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.pages.contestmanagement.utils.ActivityCsvConverter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsDumpGeneratingController {

    private final ActivityEntryHelper activityEntryHelper;

    private ActivityCsvConverter lastGeneratedCsv;

    @Autowired
    public FeedsDumpGeneratingController(ActivityEntryHelper activityEntryHelper) {
        this.activityEntryHelper = activityEntryHelper;
    }

    @GetMapping("/activities/downloadCsv")
    public void showFeed(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        final int currentActivityCount = ActivitiesClientUtil.countActivities(null, null);
        ActivityCsvConverter csvConverter;
        if (lastGeneratedCsv != null && lastGeneratedCsv.getRowsCount() == currentActivityCount) {
            csvConverter = lastGeneratedCsv;
        } else {
            csvConverter = new ActivityCsvConverter(activityEntryHelper);
            final List<ActivityEntry> activityEntries = ActivitiesClientUtil
                    .getActivityEntries(0, Integer.MAX_VALUE, null, null);
            csvConverter.addActivities(activityEntries);

            // update the cached CSV (we don't care about potential race conditions as each call
            // independently checks the recency)
            lastGeneratedCsv = csvConverter;
        }

        csvConverter.initiateDownload("activities.csv", response);
    }
}
