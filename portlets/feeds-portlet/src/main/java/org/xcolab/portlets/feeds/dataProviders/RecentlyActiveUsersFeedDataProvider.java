package org.xcolab.portlets.feeds.dataProviders;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import org.springframework.ui.Model;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.feeds.FeedTypeDataProvider;
import org.xcolab.portlets.feeds.FeedsPreferences;
import org.xcolab.portlets.feeds.Helper;
import org.xcolab.portlets.feeds.wrappers.MemberWrapper;
import org.xcolab.portlets.feeds.wrappers.SocialActivityWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecentlyActiveUsersFeedDataProvider implements
		FeedTypeDataProvider {

	@Override
	public String populateModel(PortletRequest request,
			PortletResponse response, SortFilterPage sortFilterPage,
			FeedsPreferences feedsPreferences, Model model)
			throws SystemException, PortalException {

		List<MemberWrapper> recentlyActiveUsers = new ArrayList<>();
		Set<Long> usersAlreadyAdded = new HashSet<>();
		int activitiesCount = ActivityUtil.getAllActivitiesCount();
		int currentStart = 0;
		int feedSize = feedsPreferences.getFeedSize();

		while (usersAlreadyAdded.size() < feedSize
				&& currentStart < activitiesCount) {
			int currentEnd = currentStart + 10 * feedSize;
			// get latest
			for (SocialActivity activity : ActivityUtil.retrieveAllActivities(
					currentStart, currentEnd)) {
				if (usersAlreadyAdded.contains(activity.getUserId())
						|| (feedsPreferences.getRemoveAdmin() && Helper.isUserAnAdmin(request, activity.getUserId()))
						|| SocialActivityWrapper.isEmpty(activity, request)
						|| RoleLocalServiceUtil.hasUserRole(activity.getUserId(), MemberRole.STAFF.getRoleId())) {
					continue;
				}

				usersAlreadyAdded.add(activity.getUserId());

				recentlyActiveUsers.add(new MemberWrapper(activity, request));

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
