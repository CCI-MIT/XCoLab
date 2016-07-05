package org.xcolab.portlets.feeds;

import org.springframework.ui.Model;
import org.xcolab.commons.beans.SortFilterPage;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public interface FeedTypeDataProvider {
	String populateModel(PortletRequest request, PortletResponse response,
			SortFilterPage sortFilterPage, FeedsPreferences feedsPreferences,
			Model model);
}
