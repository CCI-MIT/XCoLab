package org.xcolab.view.widgets.feeds.dataProviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.util.entity.ActivityUtil;
import org.xcolab.view.util.pagination.SortFilterPage;
import org.xcolab.view.widgets.feeds.FeedTypeDataProvider;
import org.xcolab.view.widgets.feeds.FeedsPreferences;
import org.xcolab.view.widgets.feeds.wrappers.MemberWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RecentlyActiveUsersFeedDataProvider implements FeedTypeDataProvider {

    @Autowired
    private ActivityEntryHelper activityEntryHelper;

    @Override
    public String populateModel(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, FeedsPreferences feedsPreferences, Model model) {

        List<MemberWrapper> recentlyActiveUsers = new ArrayList<>();
        Set<Long> usersAlreadyAdded = new HashSet<>();
        int activitiesCount = ActivityUtil.getAllActivitiesCount();
        int currentStart = 0;
        int feedSize = feedsPreferences.getFeedSize();

        while (usersAlreadyAdded.size() < feedSize && currentStart < activitiesCount) {
            int currentEnd = currentStart + 10 * feedSize;
            // get latest

            for (ActivityEntry activity : ActivityUtil
                    .retrieveAllActivities(currentStart, currentEnd)) {
                String body = activityEntryHelper.getActivityBody(activity);
                if (usersAlreadyAdded.contains(activity.getMemberId()) || (
                        feedsPreferences.getRemoveAdmin() && PermissionsClient
                                .canAdminAll(activity.getMemberId())) || body.isEmpty()
                        || PermissionsClient.canStaff(activity.getMemberId())) {
                    continue;
                }

                usersAlreadyAdded.add(activity.getMemberId());

                recentlyActiveUsers.add(new MemberWrapper(activity));

                if (recentlyActiveUsers.size() == feedSize) {
                    break;
                }
            }
            currentStart = currentEnd;
        }
        model.addAttribute("recentlyActiveUsers", recentlyActiveUsers);

        return "feedswidget/recentlyActiveUsers";

    }

    @Override
    public String getFeedTypeName() {
        return "Recently active users";
    }
}
