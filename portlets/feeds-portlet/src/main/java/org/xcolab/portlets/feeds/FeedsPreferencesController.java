package org.xcolab.portlets.feeds;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

@Controller
@RequestMapping("edit")
public class FeedsPreferencesController {
	
    @RequestMapping
    public String showFeed(RenderRequest request, RenderResponse response, Model model) {
    	model.addAttribute("feedsPreferences", new FeedsPreferences(request));
        
    	// populate feed types
    	Map<String, String> feedTypes = new HashMap<>();
    	for (FeedType feedType: FeedType.values()) {
    		feedTypes.put(feedType.name(), feedType.name());
    	}
    	model.addAttribute("feedTypes", feedTypes);

    	Map<String, String> feedDisplayStyles = new HashMap<>();
    	feedDisplayStyles.put("FULL", "FULL");
    	feedDisplayStyles.put("SHORT", "SHORT");
    	model.addAttribute("feedDisplayStyles", feedDisplayStyles);
    	
        return "edit";
    }
	

    @RequestMapping(params = {"action=savePreferences"})
    public void savePreferences(ActionRequest request, ActionRequest response, Model model, FeedsPreferences preferences) throws ReadOnlyException, ValidatorException, IOException {
    	preferences.store(request);
	}

}
