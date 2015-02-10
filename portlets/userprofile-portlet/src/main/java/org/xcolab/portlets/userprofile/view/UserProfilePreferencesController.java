package org.xcolab.portlets.userprofile.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.userprofile.beans.UserProfilePreferencesBean;

import javax.portlet.*;
import java.io.IOException;

@Controller
@RequestMapping("edit")
public class UserProfilePreferencesController {
	
    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model) {
    	model.addAttribute("UserProfilePreferencesBean", new UserProfilePreferencesBean(request));
        return "editUserProfilePreferences";
    }
	

    @RequestMapping(params = {"action=savePreferences"})
    public void savePreferences(ActionRequest request, ActionRequest response, Model model,
                                UserProfilePreferencesBean preferences)
            throws ReadOnlyException, ValidatorException, IOException {
        preferences.store(request);
	}

}
