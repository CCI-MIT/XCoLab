package org.xcolab.portlets.feeds.dataProviders;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.prefs.PreferenceChangeEvent;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.portal.service.RoleLocalServiceUtil;
import org.springframework.ui.Model;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.feeds.FeedTypeDataProvider;
import org.xcolab.portlets.feeds.FeedsPreferences;
import org.xcolab.portlets.feeds.Helper;
import org.xcolab.portlets.feeds.wrappers.MemberWrapper;
import org.xcolab.portlets.feeds.wrappers.SocialActivityWrapper;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.social.model.SocialActivity;

public class RecentlyActiveUsersFeedDataProvider implements
		FeedTypeDataProvider {

	@Override
	public String populateModel(PortletRequest request,
			PortletResponse response, SortFilterPage sortFilterPage,
			FeedsPreferences feedsPreferences, Model model)
			throws SystemException, PortalException {

		
		List<MemberWrapper> recentlyActiveUsers = new ArrayList<>();
		recentlyActiveUsers = new ArrayList<MemberWrapper>();
		Set<Long> usersAlreadyAdded = new HashSet<Long>();
		int activitiesCount = ActivityUtil.getAllActivitiesCount();
		int currentStart = 0;
		int feedSize = feedsPreferences.getFeedSize();

		int lastDaysBetween = -1;
		Date now = new Date();

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

				int curDaysBetween = DateUtil.getDaysBetween(
						new Date(activity.getCreateDate()), now,
						TimeZone.getDefault());
				recentlyActiveUsers.add(new MemberWrapper(activity, request));
				lastDaysBetween = curDaysBetween;

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
