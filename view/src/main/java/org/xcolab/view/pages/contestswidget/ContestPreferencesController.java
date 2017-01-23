package org.xcolab.view.pages.contestswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestPreferencesController {
	
    @GetMapping("contestswidget/editPreferences")
    public String showPreferences(HttpServletRequest request, HttpServletResponse response, Model model) {

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

    	model.addAttribute("preferences", new ContestPreferences());
        return "contestswidget/editPreferences";
    }
	

    @PostMapping("contestswidget/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, ContestPreferences preferences)
            throws  IOException {
    	preferences.submit();
        AlertMessage.success("Contest widget preferences has been saved.").flash(request);
        return "contestswidget/editPreferences";
    }

}
