package org.xcolab.view.pages.staffmemberswidget;

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
public class StaffMembersPreferencesController {

    @GetMapping("/staffmemberswidget/editPreferences")
    public String showStaffMembers(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {
        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("staffMembersPreferences",
                new StaffMembersPreferences(preferenceId, language));
        model.addAttribute("categories", StaffMembersPreferences.getCategories());

        return "/staffmemberswidget/editPreferences";
    }


    @PostMapping("/staffmemberswidget/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, StaffMembersPreferences preferences) throws IOException {
        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        preferences.store();

        AlertMessage.success("Staff members widget preferences has been saved.").flash(request);
        return "redirect:/staffmemberswidget/editPreferences?preferenceId=" + preferences
                .getPreferenceId();
    }

}
