package org.xcolab.view.pages.staffmemberswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.StaffMemberClient;
import org.xcolab.client.members.pojo.StaffMember;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/staffmemberswidget")
public class StaffMemberController {

    @GetMapping
    public String showStaffMembers(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam long categoryId, @RequestParam String title,
            @RequestParam(required = false, defaultValue = "3") int columnAmount,
            @RequestParam(required = false, defaultValue = "true") boolean displayPhoto,
            @RequestParam(required = false, defaultValue = "true") boolean displayUrl) {

        //TODO: turn this into widget-specific preferences
//        StaffMembersPreferences preferences = new StaffMembersPreferences();
//
//        model.addAttribute("portletTitle", preferences.getPortletTitle());
//        model.addAttribute("columnAmount", preferences.getColumnAmount());
//        model.addAttribute("displayPhoto", preferences.isDisplayPhoto());
//        model.addAttribute("displayUrl", preferences.isDisplayUrl());
//
//        final int categoryId = preferences.getCategoryId();

        model.addAttribute("widgetTitle", title);
        model.addAttribute("columnAmount", columnAmount);
        model.addAttribute("displayPhoto", displayPhoto);
        model.addAttribute("displayUrl", displayUrl);

        List<StaffMember> results = StaffMemberClient.getStaffMembersByCategoryId(categoryId);

        List<StaffMemberWrapper> staffMembers = new ArrayList<>();

        for (StaffMember staffMember : results) {
            staffMembers.add(new StaffMemberWrapper(staffMember));
        }

        staffMembers.sort(Comparator.comparing(StaffMemberWrapper::getSort));

        model.addAttribute("staffMembers", staffMembers);

        return "staffmemberswidget/staffmembers";
    }
}
