package org.xcolab.portlets.staffmembers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.members.StaffMemberClient;
import org.xcolab.client.members.pojo.StaffMember;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class StaffMemberController {
    @RequestMapping
    public String showStaffMembers(PortletRequest request, PortletResponse response, Model model) {
        StaffMembersPreferences preferences = new StaffMembersPreferences(request);

        model.addAttribute("portletTitle", preferences.getPortletTitle());
        model.addAttribute("columnAmount", preferences.getColumnAmount());
        model.addAttribute("displayPhoto", preferences.isDisplayPhoto());
        model.addAttribute("displayUrl", preferences.isDisplayUrl());

        final int categoryId = preferences.getCategoryId();
        List<StaffMember> results = StaffMemberClient.getStaffMembersByCategoryId(categoryId);

        List<StaffMemberWrapper> staffMembers = new ArrayList<>();

        for (StaffMember staffMember : results) {
            staffMembers.add(new StaffMemberWrapper(staffMember));
        }

        model.addAttribute("staffMembers", staffMembers);

        return "staffmembers";
    }
}
