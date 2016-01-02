package org.xcolab.portlets.feeds;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.ui.Model;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.feeds.dataProviders.ActivitiesFeedDataProvider;
import org.xcolab.portlets.feeds.dataProviders.RecentlyActiveUsersFeedDataProvider;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public enum FeedType {
	ACTIVITIES("Activities", new ActivitiesFeedDataProvider()), 
	RECENTLY_ACTIVE("Recently active users", new RecentlyActiveUsersFeedDataProvider());
	/*
	RECENTLY_JOINED("Recently joined users", null), 
	MOST_ACTIVE("Most active users", null);
	*/

	private final String description;
	private final FeedTypeDataProvider feedTypeDataProvider;

	FeedType(String description, FeedTypeDataProvider feedTypeDataProvider) {
		this.description = description;
		this.feedTypeDataProvider = feedTypeDataProvider;
	}

	public String getDescription() {
		return description;
	}

	public String getViewAndpopulateModel(PortletRequest request,
			PortletResponse response, SortFilterPage sortFilterPage,
			FeedsPreferences feedsPreferences, Model model) throws SystemException, PortalException {
		return feedTypeDataProvider.populateModel(request, response,
				sortFilterPage, feedsPreferences, model);
	}

}
