package org.xcolab.view.pages.staffmemberswidget;

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
public class StaffMembersPreferencesController {

    @GetMapping("/staffmemberswidget/editPreferences")
    public String showStaffMembers(@RequestParam(required = false) String preferenceId, HttpServletRequest request, HttpServletResponse response, Model model) {
    	model.addAttribute("staffMembersPreferences", new StaffMembersPreferences(preferenceId));
    	model.addAttribute("categories", StaffMembersPreferences.getCategories());

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        return "/staffmemberswidget/editPreferences";
    }


    @PostMapping("/staffmemberswidget/savePreferences")
    public void savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, StaffMembersPreferences preferences) throws  IOException {
    	preferences.store();
        AlertMessage.success("Staff members widget preferences has been saved.").flash(request);

        response.sendRedirect("/staffmemberswidget/editPreferences?preferenceId="+preferences.getPreferenceId());
	}

}
