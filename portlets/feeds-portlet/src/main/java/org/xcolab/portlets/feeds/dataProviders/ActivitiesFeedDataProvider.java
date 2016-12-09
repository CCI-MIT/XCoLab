package org.xcolab.portlets.feeds.dataProviders;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.springframework.ui.Model;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.feeds.FeedTypeDataProvider;
import org.xcolab.portlets.feeds.FeedsPreferences;
import org.xcolab.portlets.feeds.wrappers.SocialActivityWrapper;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

public class ActivitiesFeedDataProvider implements FeedTypeDataProvider {

	@Override
	public String populateModel(PortletRequest request,
								PortletResponse response, SortFilterPage sortFilterPage,
								FeedsPreferences feedsPreferences, Model model) {

		try {
            HttpServletRequest originalRequest = PortalUtil
                    .getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

            List<SocialActivityWrapper> activities = new ArrayList<>();
            int lastDaysBetween = -1;
            Date now = new Date();
            int i = 0;
            Map<String, String[]> parameters = request.getParameterMap();
            long filterUserId = 0L;
            Member filterUser;
            final int pageSize = feedsPreferences.getFeedSize();
            String userIdStr = null;
            if (parameters.containsKey("userId")) {
                userIdStr = parameters.get("userId")[0];
            } else if (originalRequest.getParameter("userId") != null) {
                userIdStr = originalRequest.getParameter("userId");
            }
            if (userIdStr != null) {
                try {
                    filterUserId = Long.parseLong(userIdStr);
                    filterUser = MembersClient.getMember(filterUserId);
                    model.addAttribute("filterUserId", filterUserId);
                    model.addAttribute("filterUser", filterUser);
                } catch (Throwable ignored) {
                }
            }

            List<ActivityEntry> windowedActivities;
            int startRetrievalAt = sortFilterPage.getPage() * pageSize;
            int endRetrievalAt = (sortFilterPage.getPage() + 1) * pageSize;
            if (filterUserId == 0) {
                windowedActivities = ActivitiesClientUtil
                        .getActivityEntries(startRetrievalAt, endRetrievalAt, null, null);

            } else {
                windowedActivities = ActivitiesClientUtil
                        .getActivityEntries(startRetrievalAt, endRetrievalAt, filterUserId, null);
            }

            for (ActivityEntry activity : windowedActivities) {

                if (SocialActivityWrapper.isEmpty(activity, request)) {
                    continue;
                }
                if (!feedsPreferences.getRemoveAdmin() && PermissionsClient.canAdminAll(activity.getMemberId())) {
                    continue;
                }
                if (RoleLocalServiceUtil
                        .hasUserRole(activity.getMemberId(), MemberRole.STAFF.getRoleId())) {
                    continue;
                }
                if (i >= feedsPreferences.getFeedSize()) {
                    break;
                }

                int curDaysBetween = DateUtil
                        .getDaysBetween(new Date(activity.getCreateDate().getTime()), now,
                                TimeZone.getDefault());
                activities.add(new SocialActivityWrapper(activity, curDaysBetween,
                        lastDaysBetween < curDaysBetween, i % 2 == 1, request,
                        feedsPreferences.getFeedMaxLength()));
                lastDaysBetween = curDaysBetween;
                i++;
            }

            model.addAttribute("activities", activities);

            if (filterUserId == 0) {
                model.addAttribute("isLastPage",
                        ((pageSize * (sortFilterPage.getPage() + 1)) >= ActivityUtil
                                .getAllActivitiesCount()));
            } else {
                model.addAttribute("isLastPage",
                        ((pageSize * (sortFilterPage.getPage() + 1) >= ActivityUtil
                                .getActivitiesCount(filterUserId))));
            }

            return "activities";
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new InternalException(e);
        }
    }
}
