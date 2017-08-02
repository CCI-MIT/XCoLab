package org.xcolab.view.pages.contestswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestPreferencesController {
	
    @GetMapping("contestswidget/editPreferences")
    public String showPreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (language == null || language.isEmpty()) {
            language = I18nUtils.DEFAULT_LANGUAGE;
        }

    	model.addAttribute("contestPreferences", new ContestPreferences(preferenceId, language));
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
