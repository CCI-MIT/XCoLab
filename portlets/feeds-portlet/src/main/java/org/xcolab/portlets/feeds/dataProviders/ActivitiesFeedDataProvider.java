package org.xcolab.portlets.feeds.dataProviders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.feeds.FeedTypeDataProvider;
import org.xcolab.portlets.feeds.FeedsPreferences;
import org.xcolab.portlets.feeds.Helper;
import org.xcolab.portlets.feeds.wrappers.SocialActivityWrapper;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivity;

public class ActivitiesFeedDataProvider implements FeedTypeDataProvider {

	@Override
	public String populateModel(PortletRequest request,
								PortletResponse response, SortFilterPage sortFilterPage,
								FeedsPreferences feedsPreferences, Model model) throws SystemException, PortalException {

		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

		List<SocialActivityWrapper >activities = new ArrayList<SocialActivityWrapper>();
		int lastDaysBetween = -1;
		Date now = new Date();
		int i = 0;
		Map<String, String[]> parameters = request.getParameterMap();
		long filterUserId = 0L;
		User filterUser = null;
		final int pageSize = feedsPreferences.getFeedSize();
		String userIdStr = null;
		if (parameters.containsKey("userId")) {
			userIdStr = parameters.get("userId")[0];
		}
		else if (originalRequest.getParameter("userId") != null){
			userIdStr = originalRequest.getParameter("userId");
		}
		if (userIdStr != null) {
			try {
				filterUserId = Long.parseLong(userIdStr);
				filterUser = UserLocalServiceUtil.getUser(filterUserId);
				model.addAttribute("filterUserId", filterUserId);
				model.addAttribute("filterUser", filterUser);
			}
			catch (Throwable t) {
				// ignore
			}
		}

		for (SocialActivity activity : filterUserId == 0 ?
				ActivityUtil.retrieveWindowedActivities(sortFilterPage.getPage() * pageSize, (sortFilterPage.getPage()+1) * pageSize) :
				ActivityUtil.retrieveWindowedActivities(filterUserId, sortFilterPage.getPage() * pageSize, (sortFilterPage.getPage()+1) * pageSize)) {
			if (SocialActivityWrapper.isEmpty(activity, request)) {
				continue;
			}
			if (!feedsPreferences.getRemoveAdmin() && Helper.isUserAnAdmin(request, activity.getUserId())) {
				continue;
			}

			if (i >= feedsPreferences.getFeedSize()) {
				break;
			}

			int curDaysBetween = DateUtil.getDaysBetween(new Date(activity.getCreateDate()), now, TimeZone.getDefault());
			activities.add(new SocialActivityWrapper(activity, curDaysBetween, lastDaysBetween < curDaysBetween, i % 2 == 1, request, feedsPreferences.getFeedMaxLength()));
			lastDaysBetween = curDaysBetween;
			i++;
		}

		model.addAttribute("activities", activities);
		model.addAttribute("maxPage", (int) Math.ceil((double)ActivityUtil.getAllActivitiesCount()/ pageSize));

		return "activities";

	}

}
