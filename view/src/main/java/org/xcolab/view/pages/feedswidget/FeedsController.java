package org.xcolab.view.pages.feedswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.view.util.pagination.SortFilterPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsController {
	
    @GetMapping("/feedswidget")
    public String showFeed(HttpServletRequest request, HttpServletResponse response,
			SortFilterPage sortFilterPage, Model model) {

    	FeedsPreferences preferences = new FeedsPreferences(request);

    	//model.addAttribute("portletId", portletId);
    	model.addAttribute("feedType", preferences.getFeedType());
    	model.addAttribute("feedStyle", preferences.getFeedStyle());
		model.addAttribute("portletTitle", preferences.getPortletTitle());
		model.addAttribute("seeMoreLinkShown", preferences.getSeeMoreLinkShown());
    	return preferences.getFeedType().getViewAndpopulateModel(request, response, sortFilterPage, preferences, model);
    	
    }
	
	

}
