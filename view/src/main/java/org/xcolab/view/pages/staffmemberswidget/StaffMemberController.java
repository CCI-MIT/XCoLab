package org.xcolab.view.pages.staffmemberswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.StaffMemberClient;
import org.xcolab.client.members.legacy.enums.CategoryRole;
import org.xcolab.client.members.legacy.enums.CategoryRole.NoSuchCategoryRoleException;
import org.xcolab.client.members.pojo.Member;
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
        try {
            CategoryRole categoryRole = CategoryRole.fromCategoryId(categoryId);

            if(categoryRole.getRole() == null){
                List<StaffMemberWrapper> staffMembers = getStaffMembers(categoryId);
            } else {
               //get from database
                List<Member> allMembersWithRole = MembersClient.listMembers(categoryRole.name(), null,
                        null, null, true,
                        0, Integer.MAX_VALUE);
                List<StaffMemberWrapper> staffMembersOverrides = getStaffMembers(categoryId);
                for(Member member: allMembersWithRole) {
                    boolean alreadyInStaffMembers = false;
                    for(StaffMemberWrapper smw: staffMembersOverrides){
                        if(member.getId_() == smw.getMember().getId_()){
                            alreadyInStaffMembers = true;
                        }
                    }
                    if(!alreadyInStaffMembers){
                        staffMembersOverrides.add(getNewStaffMember(member,categoryRole));
                    }
                }

            }

            List<StaffMemberWrapper> staffMembers = getStaffMembers(categoryId);

            model.addAttribute("staffMembers", staffMembers);
        }catch (NoSuchCategoryRoleException e){

        }

        return "staffmemberswidget/staffmembers";
    }
    private StaffMemberWrapper getNewStaffMember(Member member, CategoryRole categoryRole){
        StaffMember sm = new StaffMember();
        sm.setUserId(member.getId_());
        sm.setCategoryId(categoryRole.getCategoryId());
        sm.setPhotoUrl("image/user_male_portrait?img_id="+member.getId_());
        sm.setFirstNames(member.getFirstName() + " " + member.getLastName());
        return new StaffMemberWrapper(sm);
    }

    private List<StaffMemberWrapper> getStaffMembers(@RequestParam long categoryId) {
        List<StaffMember> results = StaffMemberClient.getStaffMembersByCategoryId(categoryId);

        List<StaffMemberWrapper> staffMembers = new ArrayList<>();

        for (StaffMember staffMember : results) {
            staffMembers.add(new StaffMemberWrapper(staffMember));
        }

        staffMembers.sort(Comparator.comparing(StaffMemberWrapper::getSort));
        return staffMembers;
    }
}
