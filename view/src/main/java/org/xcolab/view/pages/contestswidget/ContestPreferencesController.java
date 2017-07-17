package org.xcolab.view.pages.contestswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestPreferencesController {
	
    @GetMapping("contestswidget/editPreferences")
    public String showPreferences(@RequestParam(required = false) String preferenceId, HttpServletRequest request, HttpServletResponse response, Model model) {

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

    	model.addAttribute("contestPreferences", new ContestPreferences(preferenceId));
        return "contestswidget/editPreferences";
    }
	

    @PostMapping("contestswidget/savePreferences")
    public void savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, ContestPreferences contestPreferences)
            throws  IOException {
        contestPreferences.submit();
        AlertMessage.success("Contest widget preferences has been saved.").flash(request);
        response.sendRedirect("/contestswidget/editPreferences?preferenceId="+contestPreferences.getPreferenceId());

    }

}
