package org.xcolab.view.widgets.staffmembers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
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
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(StaffMemberController.BASE_URL)
public class StaffMemberController extends AbstractWidgetController<StaffMembersPreferences> {

    private static final String VIEW_BASE_PATH = "/widgets/staffmembers";

    public static final String BASE_URL = "/widgets/staffmembers";

    protected StaffMemberController() {
        super(BASE_URL, StaffMembersPreferences::new);
    }

    @GetMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, Member member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {
        model.addAttribute("categories", StaffMembersPreferences.getCategories());
        return showPreferencesInternal(response, model, member, preferenceId, language,
                VIEW_BASE_PATH + "/editPreferences");
    }


    @PostMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Member member, StaffMembersPreferences preferences) {
        return savePreferencesInternal(request, response, member, preferences);
    }

    @GetMapping
    public String show(Model model, @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer columnAmount,
            @RequestParam(required = false) Boolean displayPhoto,
            @RequestParam(required = false) Boolean displayUrl) {

        StaffMembersPreferences preferences = getPreferences(preferenceId);
        model.addAttribute("preferences", preferences);

        categoryId = (categoryId != null) ? (categoryId) : (preferences.getCategoryId());

        model.addAttribute("widgetTitle", ((title != null) ? (title) : (preferences.getTitle())));
        model.addAttribute("columnAmount",
                ((columnAmount != null) ? (columnAmount) : (preferences.getColumnAmount())));
        model.addAttribute("displayPhoto",
                ((displayPhoto != null) ? (displayPhoto) : (preferences.isDisplayPhoto())));
        model.addAttribute("displayUrl",
                ((displayUrl != null) ? (displayUrl) : (preferences.isDisplayUrl())));
        CategoryRole categoryRole;
        try {
            categoryRole = CategoryRole.fromCategoryId(categoryId);
        } catch (NoSuchCategoryRoleException e) {
            categoryRole = null;
        }

        List<StaffMemberWrapper> staffMembersOverrides = getStaffMembers(categoryId);

        if (categoryRole == null || categoryRole.getRole() == null) {

            if (categoryId == CategoryRole.ALUMNI.getCategoryId().longValue()) {
                staffMembersOverrides.sort(Comparator.comparing(StaffMemberWrapper::getLastName));
            } else {
                staffMembersOverrides.sort(Comparator.comparing(StaffMemberWrapper::getSort));
            }
            model.addAttribute("staffMembers", staffMembersOverrides);
            return VIEW_BASE_PATH + "/staffmembers";
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
                return VIEW_BASE_PATH + "/staffmembersGroupedByYear";
            } else {

                List<Member> allMembersWithRole = MembersClient
                        .listMembers(categoryRole.getRole().name(), null, null, null, true, 0,
                                Integer.MAX_VALUE);
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
                staffMembersOverrides.sort(Comparator.comparing(StaffMemberWrapper::getLastName));
                model.addAttribute("staffMembers", staffMembersOverrides);
                return VIEW_BASE_PATH + "/staffmembers";
            }

        }

    }

    private StaffMemberWrapper getNewStaffMember(Member member, CategoryRole categoryRole) {
        StaffMember sm = new StaffMember();
        sm.setUserId(member.getId_());
        sm.setCategoryId(categoryRole.getCategoryId());
        final String userImageDomain = PlatformAttributeKey.IMAGES_UPLOADED_DOMAIN.get();
        sm.setPhotoUrl(userImageDomain + "/image/member/" + member.getPortraitId());
        sm.setFirstNames(member.getFirstName());
        sm.setLastName(member.getLastName());
        sm.setSort(0);
        return new StaffMemberWrapper(sm);
    }

    private List<StaffMemberWrapper> getStaffMembers(long categoryId) {

        List<StaffMemberWrapper> staffMembers = new ArrayList<>();

        for (StaffMember staffMember : StaffMemberClient.getStaffMembersByCategoryId(categoryId)) {
            staffMembers.add(new StaffMemberWrapper(staffMember));
        }

        return staffMembers;
    }
}
