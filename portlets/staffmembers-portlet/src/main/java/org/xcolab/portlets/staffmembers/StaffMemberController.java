package org.xcolab.portlets.staffmembers;

import com.ext.portlet.model.StaffMember;
import com.ext.portlet.service.StaffMemberLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.util.exceptions.DatabaseAccessException;

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

        try {
            //retrieve staff members which belong to the category indicated in the portlet's preferences
            List<StaffMember> results = StaffMemberLocalServiceUtil
                    .getStaffMembersByCategoryId(preferences.getCategoryId());

            List<StaffMemberWrapper> staffMembers = new ArrayList<StaffMemberWrapper>();

            for (StaffMember staffMember : results) {
                staffMembers.add(new StaffMemberWrapper(staffMember));
            }

            model.addAttribute("staffMembers", staffMembers);

            //return the name of the view to be rendered
            return "staffmembers";
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }
}
