package org.xcolab.view.pages.staffmemberswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StaffMembersPreferencesController {

    @GetMapping("/staffmemberswidget/editPreferences")
    public String showStaffMembers(HttpServletRequest request, HttpServletResponse response, Model model) {
    	model.addAttribute("staffMembersPreferences", new StaffMembersPreferences());
    	model.addAttribute("categories", StaffMembersPreferences.getCategories());

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return "notAllowed";
        }
        return "/staffmemberswidget/editPreferences";
    }


    @PostMapping("/staffmemberswidget/savePreferences")
    public String savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, StaffMembersPreferences preferences) throws  IOException {
    	preferences.store();
        AlertMessage.success("Staff members widget preferences has been saved.").flash(request);
        return "/staffmemberswidget/editPreferences";
	}

}
