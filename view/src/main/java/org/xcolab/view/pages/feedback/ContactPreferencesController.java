package org.xcolab.view.pages.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ContactPreferencesController {
	
    @GetMapping("/feedback/editPreferences")
    public String showFeed(HttpServletRequest request, HttpServletResponse response, Model model,
            Member member, @RequestParam String language) {
    	model.addAttribute("contactPreferences", new ContactPreferences(null,language));

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        return "feedback/editPreferences";
    }
	

    @PostMapping("/feedback/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam String language,
            ContactPreferences preferences)
            throws  IOException {
        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if(language==null){

        }
        preferences.save();
        AlertMessage.success("Feedback preferences has been saved.").flash(request);
        return "feedback/editPreferences";
	}

}
