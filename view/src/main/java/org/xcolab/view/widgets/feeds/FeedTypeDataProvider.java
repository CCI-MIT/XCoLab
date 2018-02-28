package org.xcolab.view.widgets.feeds;

import org.springframework.ui.Model;

import org.xcolab.view.util.pagination.SortFilterPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FeedTypeDataProvider {

    void populateModel(HttpServletRequest request, HttpServletResponse response,
            SortFilterPage sortFilterPage, FeedsPreferences feedsPreferences, Model model);

    String getViewName();

    FeedType getFeedType();
}
