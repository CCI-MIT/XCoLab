package org.xcolab.view.pages.staffmemberswidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.pojo.team.ContestTeamMember;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.StaffMemberClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.legacy.enums.CategoryRole;
import org.xcolab.client.members.legacy.enums.CategoryRole.NoSuchCategoryRoleException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.StaffMember;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        CategoryRole categoryRole;
        try {
            categoryRole = CategoryRole.fromCategoryId(categoryId);
        } catch (NoSuchCategoryRoleException e) {
            categoryRole = null;
        }

        List<StaffMemberWrapper> staffMembersOverrides = getStaffMembers(categoryId);

        if (categoryRole == null || categoryRole.getRole() == null) {

            if (categoryId == CategoryRole.ALUMNI.getCategoryId()) {
                staffMembersOverrides.sort(Comparator.comparing(StaffMemberWrapper::getLastName));
            } else {
                staffMembersOverrides.sort(Comparator.comparing(StaffMemberWrapper::getSort));
            }
            model.addAttribute("staffMembers", staffMembersOverrides);
            return "staffmemberswidget/staffmembers";
        } else {
            if (categoryRole.getGroupByYear()) {
                Map<String, List<StaffMemberWrapper>> membersPerYearInCategory =
                        new LinkedHashMap<>();
                List<Long> years = ContestClientUtil.getContestYears();

                for (Long year : years) {
                    Map<Long, String> oneEntryPerYear = new HashMap<>();
                    List<StaffMemberWrapper> membersWithRolesInYear = new ArrayList<>();
                    List<ContestTeamMember> contestTeamMembers = ContestTeamMemberClientUtil
                            .getTeamMembers(categoryRole.getRole().getRoleId(), year);
                    for (ContestTeamMember ctm : contestTeamMembers) {
                        boolean alreadyInStaffMembers = false;
                        for (StaffMemberWrapper smw : staffMembersOverrides) {
                            if (smw.getMember() != null) {
                                if (ctm.getUserId() == smw.getMember().getId_()) {
                                    alreadyInStaffMembers = true;
                                    if (oneEntryPerYear.get(ctm.getUserId()) == null) {
                                        oneEntryPerYear.put(ctm.getUserId(), "");
                                        membersWithRolesInYear.add(smw);
                                        break;
                                    }
                                }
                            }
                        }
                        if (!alreadyInStaffMembers) {
                            try {
                                if (oneEntryPerYear.get(ctm.getUserId()) == null) {
                                    Member member = MembersClient.getMember(ctm.getUserId());
                                    oneEntryPerYear.put(ctm.getUserId(), "");
                                    membersWithRolesInYear
                                            .add(getNewStaffMember(member, categoryRole));
                                }
                            } catch (MemberNotFoundException mnfe) {

                            }
                        }
                    }
                    membersWithRolesInYear
                            .sort(Comparator.comparing(StaffMemberWrapper::getLastName));
                    membersPerYearInCategory.put(year.toString(), membersWithRolesInYear);
                }
                model.addAttribute("staffMembersMap", membersPerYearInCategory);
                return "staffmemberswidget/staffmembersGroupedByYear";
            } else {

                List<Member> allMembersWithRole =
                        MembersClient.listMembers(categoryRole.getRole().name(), null,
                                null, null, true,
                                0, Integer.MAX_VALUE);
                staffMembersOverrides = getStaffMembers(categoryId);

                for (Member member : allMembersWithRole) {
                    boolean alreadyInStaffMembers = false;
                    for (StaffMemberWrapper smw : staffMembersOverrides) {
                        if (smw.getMember() != null) {
                            if (member.getId_() == smw.getMember().getId_()) {
                                alreadyInStaffMembers = true;
                                break;
                            }
                        }
                    }
                    if (!alreadyInStaffMembers) {
                        staffMembersOverrides.add(getNewStaffMember(member, categoryRole));
                    }
                }
                staffMembersOverrides
                        .sort(Comparator.comparing(StaffMemberWrapper::getLastName));
                model.addAttribute("staffMembers", staffMembersOverrides);
                return "staffmemberswidget/staffmembers";
            }

        }

    }

    private StaffMemberWrapper getNewStaffMember(Member member, CategoryRole categoryRole) {
        StaffMember sm = new StaffMember();
        sm.setUserId(member.getId_());
        sm.setCategoryId(categoryRole.getCategoryId());
        sm.setPhotoUrl("/image/user_male_portrait?userId=" + member.getId_()
                + "&screenName=carlosbpf&portraitId=" + member.getPortraitId() + "");
        sm.setFirstNames(member.getFirstName());
        sm.setLastName(member.getLastName());
        sm.setSort(0);
        return new StaffMemberWrapper(sm);
    }

    private List<StaffMemberWrapper> getStaffMembers(@RequestParam long categoryId) {

        List<StaffMemberWrapper> staffMembers = new ArrayList<>();

        for (StaffMember staffMember : StaffMemberClient.getStaffMembersByCategoryId(categoryId)) {
            staffMembers.add(new StaffMemberWrapper(staffMember));
        }

        return staffMembers;
    }
}
