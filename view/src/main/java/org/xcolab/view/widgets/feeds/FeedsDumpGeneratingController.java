package org.xcolab.view.widgets.feeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.pages.contestmanagement.utils.ActivityCsvWriter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsDumpGeneratingController {

    private final ActivityEntryHelper activityEntryHelper;

    @Autowired
    public FeedsDumpGeneratingController(ActivityEntryHelper activityEntryHelper) {
        this.activityEntryHelper = activityEntryHelper;
    }

    @GetMapping("/activities/downloadCsv")
    public void showFeed(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        final List<ActivityEntry> activityEntries = ActivitiesClientUtil
                .getActivityEntries(0, Integer.MAX_VALUE, null, null);

        try (ActivityCsvWriter csvWriter = new ActivityCsvWriter(response, activityEntryHelper)) {
            csvWriter.writeActivities(activityEntries);
        }
    }
}
