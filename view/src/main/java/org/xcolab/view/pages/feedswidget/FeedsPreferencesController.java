package org.xcolab.view.pages.feedswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsPreferencesController {
	
    @GetMapping("/feedswidget/editPreferences")
    public String showFeed(HttpServletRequest request, HttpServletResponse response, Model model) {

		long memberId = MemberAuthUtil.getMemberId(request);
		if (!PermissionsClient.canAdminAll(memberId)) {
			return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
		}

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
    	
        return "/feedswidget/editPreferences";
    }
	

    @PostMapping("/feedswidget/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, FeedsPreferences preferences) throws  IOException {
    	preferences.store();
		AlertMessage.success("Feeds widget preferences has been saved.").flash(request);
		return "/feedswidget/editPreferences";
	}

}
