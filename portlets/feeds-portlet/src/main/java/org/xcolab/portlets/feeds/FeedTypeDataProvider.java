package org.xcolab.portlets.feeds;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.ui.Model;
import org.xcolab.commons.beans.SortFilterPage;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public interface FeedTypeDataProvider {
	String populateModel(PortletRequest request, PortletResponse response,
			SortFilterPage sortFilterPage, FeedsPreferences feedsPreferences,
			Model model) throws SystemException, PortalException;
}
