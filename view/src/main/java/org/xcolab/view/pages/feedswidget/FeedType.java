package org.xcolab.view.pages.feedswidget;

import org.springframework.ui.Model;

import org.xcolab.view.pages.feedswidget.dataProviders.ActivitiesFeedDataProvider;
import org.xcolab.view.pages.feedswidget.dataProviders.RecentlyActiveUsersFeedDataProvider;
import org.xcolab.view.util.pagination.SortFilterPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public String getViewAndpopulateModel(HttpServletRequest request,
			HttpServletResponse response, SortFilterPage sortFilterPage,
			FeedsPreferences feedsPreferences, Model model) {
		return feedTypeDataProvider.populateModel(request, response,
				sortFilterPage, feedsPreferences, model);
	}

}
