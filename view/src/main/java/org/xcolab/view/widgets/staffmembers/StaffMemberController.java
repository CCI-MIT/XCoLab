package org.xcolab.view.widgets.staffmembers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.IStaffUserClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.legacy.enums.StaffMemberCategoryRole;
import org.xcolab.client.user.legacy.enums.StaffMemberCategoryRole.NoSuchCategoryRoleException;
import org.xcolab.client.user.pojo.wrapper.StaffUserWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(StaffMemberController.BASE_URL)
public class StaffMemberController extends AbstractWidgetController<StaffMembersPreferences> {

    private static final String VIEW_BASE_PATH = "/widgets/staffmembers";

    public static final String BASE_URL = "/widgets/staffmembers";

    @Autowired
    private IUserClient userClient;

    @Autowired
    private IStaffUserClient staffUserClient;

    protected StaffMemberController() {
        super(BASE_URL, StaffMembersPreferences::new);
    }

    @GetMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {
        model.addAttribute("categories", StaffMembersPreferences.getCategories());
        return showPreferencesInternal(response, model, member, preferenceId, language,
                VIEW_BASE_PATH + "/editPreferences");
    }


    @PostMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, StaffMembersPreferences preferences) {
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
        StaffMemberCategoryRole categoryRole;
        try {
            categoryRole = StaffMemberCategoryRole.fromCategoryId(categoryId);
        } catch (NoSuchCategoryRoleException e) {
            categoryRole = null;
        }

        List<StaffMemberWrapper> staffMembersOverrides = getStaffMembers(categoryId);

        if (categoryRole == null || categoryRole.getRole() == null) {

            if (categoryId == StaffMemberCategoryRole.ALUMNI.getCategoryId().longValue()) {
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
                List<Long> years = contestClient.getContestYears();

                for (Long year : years) {
                    Set<Long> usersInYear = new HashSet<>();
                    List<StaffMemberWrapper> membersWithRolesInYear = new ArrayList<>();
                    List<IContestTeamMember> contestTeamMembers = contestTeamMemberClient
                            .getTeamMembers(categoryRole.getRole().getRoleId(), year);
                    for (IContestTeamMember ctm : contestTeamMembers) {
                        boolean alreadyInStaffMembers = false;
                        for (StaffMemberWrapper smw : staffMembersOverrides) {
                            if (smw.getMember() != null) {
                                if (ctm.getUserId() == smw.getMember().getId()) {
                                    alreadyInStaffMembers = true;
                                    if (!usersInYear.contains(ctm.getUserId())) {
                                        usersInYear.add(ctm.getUserId());
                                        membersWithRolesInYear.add(smw);
                                        break;
                                    }
                                }
                            }
                        }
                        if (!alreadyInStaffMembers) {
                            final ContestWrapper contest =
                                    contestClient.getContest(ctm.getContestId());
                            if (!contest.isContestPrivate() &&
                                    !usersInYear.contains(ctm.getUserId())) {
                                UserWrapper member = userClient.getMemberUnchecked(ctm.getUserId());
                                usersInYear.add(ctm.getUserId());
                                membersWithRolesInYear.add(getNewStaffMember(member, categoryRole));
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

                List<UserWrapper> allMembersWithRole = userClient
                        .listMembers(categoryRole.getRole().name(), null, null, null, true, 0,
                                Integer.MAX_VALUE);
                staffMembersOverrides = getStaffMembers(categoryId);

                for (UserWrapper member : allMembersWithRole) {
                    boolean alreadyInStaffMembers = false;
                    for (StaffMemberWrapper smw : staffMembersOverrides) {
                        if (smw.getMember() != null) {
                            if (member.getId() == smw.getMember().getId()) {
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

    private StaffMemberWrapper getNewStaffMember(UserWrapper member, StaffMemberCategoryRole categoryRole) {
        StaffUserWrapper sm = new StaffUserWrapper();
        sm.setUserId(member.getId());
        sm.setCategoryId(categoryRole.getCategoryId());
        final String userImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        sm.setPhotoUrl(userImageDomain + "/image/member/" + member.getPortraitId());
        sm.setFirstNames(member.getFirstName());
        sm.setLastName(member.getLastName());
        sm.setSortOrder(0);
        return new StaffMemberWrapper(sm);
    }

    private List<StaffMemberWrapper> getStaffMembers(long categoryId) {

        List<StaffMemberWrapper> staffMembers = new ArrayList<>();

        for (StaffUserWrapper staffMember : staffUserClient.getStaffUsersByCategoryId(categoryId)) {
            staffMembers.add(new StaffMemberWrapper(staffMember));
        }

        return staffMembers;
    }
}
