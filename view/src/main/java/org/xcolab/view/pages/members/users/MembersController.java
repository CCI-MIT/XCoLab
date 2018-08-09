package org.xcolab.view.pages.members.users;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
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

@Controller
public class MembersController {

    private static final int USERS_PER_PAGE = 20;

    private static final int AUTOCOMPLETE_MAX_USERS = 15;

    @RequestMapping("/members")
    public String showUsers(HttpServletRequest request, HttpServletResponse response, Model model,
            SortFilterPage sortFilterPage,
            @RequestParam(value = "page", required = false) Long pageParam,
            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam) {

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

        MembersPermissions membersPermissions = new MembersPermissions(request);
        final String emailFilterParam = membersPermissions.getCanAdminAll() ? filterParam : null;
        List<Member> members = MembersClient.listMembers(memberCategoryParam, filterParam,
                emailFilterParam, sortFilterPage.getSortColumn(), sortFilterPage.isSortAscending(),
                firstUser, endUser);

        List<MemberItem> users = new ArrayList<>();
        for (Member member : members) {
            MemberItem memberItem = new MemberItem(member, memberCategoryParam);
            users.add(memberItem);
        }

        Locale locale = LocaleContextHolder.getLocale();


        int usersCount = MembersClient.countMembers(memberCategoryParam, filterParam);
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

        model.addAttribute("memberCategories", MembersClient.getVisibleMemberCategories());
        if (StringUtils.isNotEmpty(memberCategoryParam)) {
            model.addAttribute("memberCategory", getMemberCategoryInLocale(memberCategoryParam));
        }

        model.addAttribute("permissions", membersPermissions);
        model.addAttribute("_activePageLink", "community");
        return "members/users";
    }

    private MemberCategory getMemberCategoryInLocale(
            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam) {
        final MemberCategory memberCategory = MembersClient.getMemberCategory(memberCategoryParam);
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
        final List<Member> members = MembersClient
                .findMembersMatching(partialName, AUTOCOMPLETE_MAX_USERS);
        for (Member member : members) {
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
        final List<Member> members = MembersClient.findByScreenNameOrName(term);
        final JSONArray jsonArray = new JSONArray();
        for(Member member: members) {
            final JSONObject jsonMember = new JSONObject();
            if (member != null) {
                /*jsonMember.put("userId", member.getId());
                jsonMember.put("screenName", member.getScreenName());

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

        MembersPermissions membersPermissions = new MembersPermissions(request);

        if (membersPermissions.getCanDownloadMemberList()) {
            try (MemberListCsvWriter csvWriter = new MemberListCsvWriter(response)) {
                List<Member> memberList = MembersClient.listAllMembers();
                csvWriter.writeMembers(removeDuplicates(memberList));
            }
        }
    }
    private List<Member>  removeDuplicates(List<Member> members) {
        HashMap<String,Member> finalMembers = new HashMap<>();
        for (Member temp : members) {
            if (!finalMembers.containsKey(temp.getScreenName())) {
                finalMembers.put(temp.getScreenName(),temp);
            }
        }
        return new ArrayList<>(finalMembers.values());
    }
}
