package org.xcolab.portlets.feeds;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.commons.beans.SortFilterPage;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("view")
public class FeedsController {
	
    @RequestMapping
    public String showFeed(PortletRequest request, PortletResponse response, SortFilterPage sortFilterPage, Model model) throws SystemException, PortalException {

    	FeedsPreferences preferences = new FeedsPreferences(request);
    	ThemeDisplay themeDisplay= (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
    	PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
    	String portletId= portletDisplay.getId();
    	model.addAttribute("portletId", portletId);
    	model.addAttribute("feedType", preferences.getFeedType());
    	model.addAttribute("feedStyle", preferences.getFeedStyle());
    	return preferences.getFeedType().getViewAndpopulateModel(request, response, sortFilterPage, preferences, model);
    	
    }
	
	

}
