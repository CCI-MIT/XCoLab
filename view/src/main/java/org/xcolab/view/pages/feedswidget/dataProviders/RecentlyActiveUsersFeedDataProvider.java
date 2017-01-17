package org.xcolab.view.pages.feedswidget.dataProviders;

import org.springframework.ui.Model;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.ActivityUtil;
import org.xcolab.view.pages.feedswidget.FeedTypeDataProvider;
import org.xcolab.view.pages.feedswidget.FeedsPreferences;
import org.xcolab.view.pages.feedswidget.wrappers.MemberWrapper;
import org.xcolab.view.pages.feedswidget.wrappers.SocialActivityWrapper;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RecentlyActiveUsersFeedDataProvider implements FeedTypeDataProvider {

	@Override
	public String populateModel(HttpServletRequest request,
			HttpServletResponse response, SortFilterPage sortFilterPage,
			FeedsPreferences feedsPreferences, Model model) {

			List<MemberWrapper> recentlyActiveUsers = new ArrayList<>();
			Set<Long> usersAlreadyAdded = new HashSet<>();
			int activitiesCount = ActivityUtil.getAllActivitiesCount();
			int currentStart = 0;
			int feedSize = feedsPreferences.getFeedSize();

			while (usersAlreadyAdded.size() < feedSize
					&& currentStart < activitiesCount) {
				int currentEnd = currentStart + 10 * feedSize;
				// get latest
				for (ActivityEntry activity : ActivityUtil.retrieveAllActivities(
						currentStart, currentEnd)) {
					if (usersAlreadyAdded.contains(activity.getMemberId())
							|| (feedsPreferences.getRemoveAdmin()
							&& PermissionsClient.canAdminAll(activity.getMemberId()))
							|| SocialActivityWrapper.isEmpty(activity, request)
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
}
