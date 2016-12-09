package org.xcolab.portlets.users;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.portlets.users.utils.MemberItem;
import org.xcolab.portlets.users.utils.MemberListCsvConverter;
import org.xcolab.portlets.users.utils.MembersPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class MembersController {

    private static final int USERS_PER_PAGE = 20;

    private static final int AUTOCOMPLETE_MAX_USERS = 15;

    @RequestMapping
    public String showUsers(PortletRequest request, PortletResponse response, SortFilterPage sortFilterPage,
                            @RequestParam(value = "page", required = false) Long pageParam,
                            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam,
                            Model model) {
        int page = 1;
        if (pageParam != null) {
            page = pageParam.intValue();
        }

        int startPage = 1;
        if (page - 5 > 0) {
            startPage = page - 5;
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

        final boolean isPointsActive = ConfigurationAttributeKey.IS_POINTS_ACTIVE.get();
        if (StringUtils.isEmpty(sortFilterPage.getSortColumn())) {
            String sortColumn = ConfigurationAttributeKey.MEMBERS_DEFAULT_SORT_COLUMN.get();
            if (StringUtils.isEmpty(sortColumn)) {
                sortColumn = isPointsActive ? "POINTS" : "ACTIVITY";
            }
            sortFilterPage.setSortColumn(sortColumn);
            sortFilterPage.setSortAscending(false);
        }

        String categoryFilterValue =
                "Judge".equalsIgnoreCase(memberCategoryParam) ? "Judges" : memberCategoryParam;
        if (StringUtils.isEmpty(categoryFilterValue)) {
            categoryFilterValue = "Member";
        }
        List<Member> members = MembersClient.listMembers(categoryFilterValue, filterParam,
                sortFilterPage.getSortColumn(), sortFilterPage.isSortAscending(),
                firstUser, endUser);

        List<MemberItem> users = new ArrayList<>();
        for (Member member : members) {
            MemberItem memberItem = new MemberItem(member, memberCategoryParam);
            users.add(memberItem);
        }

        int usersCount = MembersClient.countMembers(categoryFilterValue, filterParam);
        int pagesCount = (int) Math.ceil(usersCount / (double) USERS_PER_PAGE);
        int endPage = pagesCount;
        if (startPage + 10 < pagesCount) {
            endPage = startPage + 10;
        }

        model.addAttribute("pageNumber", page);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("users", users);
        model.addAttribute("usersCount", usersCount);
        if (StringUtils.isNotEmpty(memberCategoryParam)) {
            final MemberCategory memberCategory = MembersClient.getMemberCategory(memberCategoryParam);
            memberCategory.setDescription(TemplateReplacementUtil
                    .replacePlatformConstants(memberCategory.getDescription()));
            model.addAttribute("memberCategory", memberCategory);
        }
        model.addAttribute("memberCategoryParam", memberCategoryParam);
        model.addAttribute("memberCategories", MembersClient.getVisibleMemberCategories());

        model.addAttribute("colabName",
                ConfigurationAttributeKey.COLAB_NAME.get());
        model.addAttribute("colabShortName",
                ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
        model.addAttribute("pointsActive", isPointsActive);

        MembersPermissions membersPermissions = new MembersPermissions(request);
        model.addAttribute("permissions", membersPermissions);

        return "users";
    }

    @ResourceMapping("getUsersByPartialName")
    public void getUsersByPartialName(ResourceRequest request, ResourceResponse response,
            @RequestParam String partialName)
            throws IOException {
        //final JSONArray jsonMembers = JSONFactoryUtil.createJSONArray();
        final JSONArray jsonMembers = new JSONArray();
        final List<Member> members = MembersClient
                .findMembersMatching(partialName, AUTOCOMPLETE_MAX_USERS);
        for (Member member : members) {
            final JSONObject jsonMember = new JSONObject();
            jsonMember.put("userId", member.getId_());
            jsonMember.put("screenName", member.getScreenName());
            jsonMember.put("firstName", member.getFirstName());
            jsonMember.put("lastName", member.getLastName());
            jsonMembers.put(jsonMember);
        }
        response.getPortletOutputStream().write(jsonMembers.toString().getBytes());
    }

    @ResourceMapping("downloadMembersList")
    public void downloadMembersList(ResourceRequest request, ResourceResponse response)
            throws IOException {

        MembersPermissions membersPermissions = new MembersPermissions(request);

        if (membersPermissions.getCanDownloadMemberList()) {
            MemberListCsvConverter csvConverter = new MemberListCsvConverter();
            csvConverter.initiateDownload("membersList", response);
        }
    }
}