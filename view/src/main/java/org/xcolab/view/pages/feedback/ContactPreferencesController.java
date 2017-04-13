package org.xcolab.view.pages.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ContactPreferencesController {
	
    @GetMapping("/feedback/editPreferences")
    public String showFeed(HttpServletRequest request, HttpServletResponse response, Model model) {
    	model.addAttribute("contactPreferences", new ContactPreferences());

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        return "feedback/editPreferences";
    }
	

    @PostMapping("/feedback/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, ContactPreferences preferences)
            throws  IOException {
        preferences.save();

        return "feedback/editPreferences";
	}

}
