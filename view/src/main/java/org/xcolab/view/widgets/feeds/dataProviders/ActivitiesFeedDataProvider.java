package org.xcolab.view.widgets.feeds.dataProviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import org.xcolab.client.activity.ActivityClient;
import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.permissions.SystemRole;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.util.entity.ActivityUtil;
import org.xcolab.view.util.pagination.SortFilterPage;
import org.xcolab.view.widgets.feeds.FeedType;
import org.xcolab.view.widgets.feeds.FeedTypeDataProvider;
import org.xcolab.view.widgets.feeds.FeedsPreferences;
import org.xcolab.view.widgets.feeds.wrappers.SocialActivityWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ActivitiesFeedDataProvider implements FeedTypeDataProvider {

    private final ActivityEntryHelper activityEntryHelper;
    private final ActivityClient activityClient;

    @Autowired
    public ActivitiesFeedDataProvider(ActivityEntryHelper activityEntryHelper, ActivityClient activityClient) {
        this.activityEntryHelper = activityEntryHelper;
        this.activityClient = activityClient;
    }

    @Override
    public void populateModel(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, FeedsPreferences feedsPreferences, Model model) {

        Map<String, String[]> parameters = request.getParameterMap();
        final int pageSize = feedsPreferences.getFeedSize();
        String userIdStr = null;
        if (parameters.containsKey("userId")) {
            userIdStr = parameters.get("userId")[0];
        } else if (request.getParameter("userId") != null) {
            userIdStr = request.getParameter("userId");
        }
        long filterUserId = 0L;
        if (userIdStr != null) {
            try {
                filterUserId = Long.parseLong(userIdStr);
                Member filterUser = MembersClient.getMember(filterUserId);
                model.addAttribute("filterUserId", filterUserId);
                model.addAttribute("filterUser", filterUser);
            } catch (Throwable ignored) {
            }
        }
        HashMap<Long, Long> idsToExclude = new HashMap<>();
        if (feedsPreferences.getRemoveAdmin()) {//STAFF
            final MemberCategory memberCategory =
                    MembersClient.getMemberCategory(SystemRole.ADMINISTRATOR.getRoleId());

            List<Member> adminList = MembersClient.listMembers(memberCategory.getCategoryName(),
                    null, null, null, true,
                    0, Integer.MAX_VALUE);
            for (Member m : adminList) {
                idsToExclude.put(m.getId(), m.getId());
            }
        }

        final MemberCategory memberCategory =
                MembersClient.getMemberCategory(SystemRole.STAFF.getRoleId());

        List<Member> staffList = MembersClient
                .listMembers(memberCategory.getCategoryName(), null, null, null, true,
                        0, Integer.MAX_VALUE);
        if (staffList != null && !staffList.isEmpty()) {
            for (Member m : staffList) {
                idsToExclude.put(m.getId(), m.getId());
            }
        }

        int startRetrievalAt = sortFilterPage.getPage() * pageSize;
        int endRetrievalAt = (sortFilterPage.getPage() + 1) * pageSize;
        List<IActivityEntry> windowedActivities = activityClient.getActivityEntries(
                startRetrievalAt, endRetrievalAt, filterUserId > 0 ? filterUserId : null,
                new ArrayList<>(idsToExclude.keySet()));

        List<SocialActivityWrapper> activities = new ArrayList<>();
        for (IActivityEntry activity : windowedActivities) {
            if (activities.size() >= feedsPreferences.getFeedSize()) {
                break;
            }

            String activityBody = activityEntryHelper.getActivityBody(activity);
            if (activityBody.isEmpty()) {
                continue;
            }

            activities.add(new SocialActivityWrapper(activity, activityBody));
        }

        model.addAttribute("activities", activities);

        final int allActivitiesCount = filterUserId > 0
                ? ActivityUtil.getActivitiesCount(filterUserId)
                : ActivityUtil.getAllActivitiesCount();

        model.addAttribute("isLastPage",
                ((pageSize * (sortFilterPage.getPage() + 1)) >= allActivitiesCount));
    }

    @Override
    public String getViewName() {
        return "feedswidget/activities";
    }

    @Override
    public FeedType getFeedType() {
        return FeedType.ACTIVITIES;
    }
}
