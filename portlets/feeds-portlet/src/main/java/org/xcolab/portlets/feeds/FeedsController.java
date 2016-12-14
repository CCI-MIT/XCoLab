package org.xcolab.portlets.feeds;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.commons.beans.SortFilterPage;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class FeedsController {
	
    @RequestMapping
    public String showFeed(PortletRequest request, PortletResponse response,
			SortFilterPage sortFilterPage, Model model) {

    	FeedsPreferences preferences = new FeedsPreferences(request);
    	ThemeDisplay themeDisplay= (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
    	PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
    	String portletId= portletDisplay.getId();
    	model.addAttribute("portletId", portletId);
    	model.addAttribute("feedType", preferences.getFeedType());
    	model.addAttribute("feedStyle", preferences.getFeedStyle());
		model.addAttribute("portletTitle", preferences.getPortletTitle());
		model.addAttribute("seeMoreLinkShown", preferences.getSeeMoreLinkShown());
    	return preferences.getFeedType().getViewAndpopulateModel(request, response, sortFilterPage, preferences, model);
    	
    }
	
	

}
