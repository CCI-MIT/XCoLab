package org.xcolab.view.pages.contestswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.AdminClient;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("edit")
public class ContestPreferencesController {
	
    @RequestMapping
    public String showPreferences(HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("preferences", new ContestPreferences());
        //AdminClient.updateConfigurationAttribute();
        return "editPreferences";
    }
	

    @RequestMapping(params = "action=save")
    public void savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, ContestPreferences preferences)
            throws  IOException {
    	preferences.submit(request);
    }

}
