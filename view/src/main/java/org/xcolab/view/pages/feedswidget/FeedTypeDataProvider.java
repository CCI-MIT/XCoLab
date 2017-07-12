package org.xcolab.view.pages.feedswidget;

import org.springframework.ui.Model;

import org.xcolab.view.util.pagination.SortFilterPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FeedTypeDataProvider {

	String populateModel(HttpServletRequest request, HttpServletResponse response,
			SortFilterPage sortFilterPage, FeedsPreferences feedsPreferences, Model model);

}
