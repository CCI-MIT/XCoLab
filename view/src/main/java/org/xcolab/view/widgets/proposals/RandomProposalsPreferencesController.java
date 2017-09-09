package org.xcolab.view.widgets.proposals;

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

public class RandomProposalsPreferencesController {

    @GetMapping("/randomproposalswidget/editPreferences")
    public String showPreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        model.addAttribute("preferences", new RandomProposalsPreferences(preferenceId, language));

        return "/randomproposalswidget/editPreferences";
    }


    @PostMapping("/randomproposalswidget/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, RandomProposalsPreferences preferences) throws IOException {
        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        preferences.submit();

        AlertMessage.success("Random proposals widget preferences has been saved.").flash(request);
        return "redirect:/randomproposalswidget/editPreferences?preferenceId=" + preferences
                .getPreferenceId();
    }

}
