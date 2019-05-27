package org.xcolab.view.pages.members.users;

import io.micrometer.core.instrument.Metrics;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.IUserCategoryClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.pages.members.users.utils.MemberItem;
import org.xcolab.view.pages.members.users.utils.MemberListCsvWriter;
import org.xcolab.view.pages.members.users.utils.MembersNavigation;
import org.xcolab.view.pages.members.users.utils.MembersPermissions;
import org.xcolab.view.util.pagination.PageNavigation;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

@Controller
public class MembersController {

    private static final int USERS_PER_PAGE = 20;

    private static final int AUTOCOMPLETE_MAX_USERS = 15;

    @Autowired
    private IUserClient userClient;

    @Autowired
    private IUserCategoryClient userCategoryClient;

    @GetMapping("/members")
    public String showUsers(HttpServletRequest request, Model model,
            SortFilterPage sortFilterPage,
            @RequestParam(value = "page", required = false) Long pageParam,
            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam) {

        // Metrics code
        Metrics.counter("xcolab-view","endpoint","/members", "function", "/members").increment();

        long startTime = System.nanoTime();

        model.addAttribute("communityTopContentArticleId",
                ConfigurationAttributeKey.MEMBERS_CONTENT_ARTICLE_ID.get());
        int page = 1;
        if (pageParam != null) {
            page = pageParam.intValue();
        }

        int firstUser = 0;
        if (page > 1) {
            firstUser = (page - 1) * USERS_PER_PAGE;
        }

        int endUser = firstUser + USERS_PER_PAGE;


        String filterParam = request.getParameter("filter");
        if (filterParam != null) {
            sortFilterPage.setFilter(filterParam);
        }

        final boolean isPointsActive = ConfigurationAttributeKey.POINTS_IS_ACTIVE.get();
        if (StringUtils.isEmpty(sortFilterPage.getSortColumn())) {
            String sortColumn = ConfigurationAttributeKey.MEMBERS_DEFAULT_SORT_COLUMN.get();
            if (StringUtils.isEmpty(sortColumn)) {
                sortColumn = isPointsActive ? "POINTS" : "ACTIVITY";
            }
            sortFilterPage.setSortColumn(sortColumn);
            sortFilterPage.setSortAscending(false);
        }

        MembersPermissions membersPermissions = new MembersPermissions();
        final String emailFilterParam = membersPermissions.getCanAdminAll() ? filterParam : null;
        List<UserWrapper> members = userClient.listMembers(memberCategoryParam, filterParam,
                emailFilterParam, sortFilterPage.getSortColumn(), sortFilterPage.isSortAscending(),
                firstUser, endUser);

        List<MemberItem> users = new ArrayList<>();
        for (UserWrapper member : members) {
            MemberItem memberItem = new MemberItem(member, memberCategoryParam);
            users.add(memberItem);
        }

        Locale locale = LocaleContextHolder.getLocale();


        int usersCount = userClient.countMembers(memberCategoryParam, filterParam);
        int pagesCount = (int) Math.ceil(usersCount / (double) USERS_PER_PAGE);

        final MembersNavigation membersNavigation =
                new MembersNavigation(sortFilterPage, memberCategoryParam, filterParam);
        model.addAttribute("membersNavigation", membersNavigation);
        final PageNavigation pageNavigation =
                new PageNavigation(membersNavigation.getCurrentUrl(), page, pagesCount);
        model.addAttribute("pageNavigation", pageNavigation);
        model.addAttribute("sortFilterPage", sortFilterPage);

        model.addAttribute("users", users);
        model.addAttribute("usersCount", I18nUtils.formatNumberDefaultLocale(locale,usersCount));

        model.addAttribute("memberCategories", userCategoryClient.getVisibleMemberCategories());
        if (StringUtils.isNotEmpty(memberCategoryParam)) {
            model.addAttribute("memberCategory", getMemberCategoryInLocale(memberCategoryParam));
        }

        model.addAttribute("permissions", membersPermissions);
        model.addAttribute("_activePageLink", "community");

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        Metrics.timer("xcolab-view_timer","endpoint","/members", "function", "/members").record(duration, NANOSECONDS);

        return "members/users";
    }

    private MemberCategoryWrapper getMemberCategoryInLocale(
            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam) {
        final MemberCategoryWrapper memberCategory = userCategoryClient.getMemberCategory(memberCategoryParam);
        memberCategory.setDescription(TemplateReplacementUtil
                .replacePlatformConstants(memberCategory.getDescription()));
        return memberCategory;
    }


    @GetMapping("/api/members/getUsersByPartialName/{partialName}")
    public void getUsersByPartialName(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String partialName)
            throws IOException {
        //final JSONArray jsonMembers = JSONFactoryUtil.createJSONArray();
        final JSONArray jsonMembers = new JSONArray();
        final List<UserWrapper> members = userClient
                .findMembersMatching(partialName, AUTOCOMPLETE_MAX_USERS);
        for (UserWrapper member : members) {
            final JSONObject jsonMember = new JSONObject();
            jsonMember.put("userId", member.getId());
            jsonMember.put("screenName", member.getScreenName());
            jsonMember.put("firstName", member.getFirstName());
            jsonMember.put("lastName", member.getLastName());
            jsonMembers.put(jsonMember);
        }
        response.getOutputStream().write(jsonMembers.toString().getBytes());
    }


    @GetMapping("/api/members/getUserByScreenName")
    public void getUserByScreenName(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String term)
            throws IOException, MemberNotFoundException {
        final List<UserWrapper> members = userClient.getUserByScreenNameName(term);
        final JSONArray jsonArray = new JSONArray();
        for(UserWrapper member: members) {
            final JSONObject jsonMember = new JSONObject();
            if (member != null) {
                /*jsonMember.put("userId", member.getId());
                jsonMember.put("screenName", member.getDisplayName());

                jsonMember.put("firstName", member.getFirstName());
                jsonMember.put("lastName", member.getLastName());*/
                jsonMember.put("id", member.getId());
                jsonMember.put("value", member.getScreenName() + " " + member.getFirstName() + " " + member.getLastName());
                jsonArray.put(jsonMember);
            }
        }
        response.getOutputStream().write(jsonArray.toString().getBytes());
    }

    @GetMapping("/api/members/downloadMembersList")
    public void downloadMembersList(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        MembersPermissions membersPermissions = new MembersPermissions();

        if (membersPermissions.getCanDownloadMemberList()) {
            try (MemberListCsvWriter csvWriter = new MemberListCsvWriter(response)) {
                List<UserWrapper> memberList = userClient.listAllMembers();
                csvWriter.writeMembers(removeDuplicates(memberList));
            }
        }
    }
    private List<UserWrapper>  removeDuplicates(List<UserWrapper> members) {
        HashMap<String, UserWrapper> finalMembers = new HashMap<>();
        for (UserWrapper temp : members) {
            if (!finalMembers.containsKey(temp.getScreenName())) {
                finalMembers.put(temp.getScreenName(),temp);
            }
        }
        return new ArrayList<>(finalMembers.values());
    }
}
