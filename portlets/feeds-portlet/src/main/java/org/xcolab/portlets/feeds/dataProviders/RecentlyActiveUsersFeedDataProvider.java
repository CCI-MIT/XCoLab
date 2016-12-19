package org.xcolab.portlets.feeds.dataProviders;

import org.springframework.ui.Model;

import com.ext.portlet.Activity.ActivityUtil;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.feeds.FeedTypeDataProvider;
import org.xcolab.portlets.feeds.FeedsPreferences;
import org.xcolab.portlets.feeds.wrappers.MemberWrapper;
import org.xcolab.portlets.feeds.wrappers.SocialActivityWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public class RecentlyActiveUsersFeedDataProvider implements
		FeedTypeDataProvider {

	@Override
	public String populateModel(PortletRequest request,
			PortletResponse response, SortFilterPage sortFilterPage,
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

			return "recentlyActiveUsers";

	}
}
