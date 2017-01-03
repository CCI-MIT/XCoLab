package org.xcolab.view.pages.feedback;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ContactPreferencesController {
	
    @GetMapping("/feedback/edit")
    public String showFeed(HttpServletRequest request, HttpServletResponse response, Model model) {
    	model.addAttribute("contactPreferences", new ContactPreferences(request));
        
        return "edit";
    }
	

    @GetMapping("/feedback/savePref")
    public void savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, ContactPreferences preferences) throws  IOException {;
        //TODO: IMPLEMENT CONFIG VARIABLE SETUP
        preferences.store(new JSONObject());
	}

}
